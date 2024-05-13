package com.example.tastetrek;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewRecipeActivity extends AppCompatActivity {

    Button view, view2, view3;
    DbHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DB = new DbHandler(this);
        view = findViewById(R.id.buttonViewOurRecipes);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= String.valueOf('0');
                Cursor res = DB.getRecipe(id);
                if(res.getCount()==0){
                    Toast.makeText(ViewRecipeActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Title:\n"+res.getString(1)+"\n");
                    buffer.append("Ingredients:\n"+res.getString(2)+"\n");
                    buffer.append("Recipe:\n"+res.getString(3)+"\n\n\n");
                    buffer.append("********************************************\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewRecipeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Our Recipes");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        view2 = findViewById(R.id.buttonViewMemberRecipes);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= String.valueOf('1');
                Cursor res = DB.getRecipe(id);
                if(res.getCount()==0){
                    Toast.makeText(ViewRecipeActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Title:\n"+res.getString(1)+"\n");
                    buffer.append("Ingredients:\n"+res.getString(2)+"\n");
                    buffer.append("Recipe:\n"+res.getString(3)+"\n\n\n");
                    buffer.append("********************************************\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewRecipeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Member Recipes");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        view3 = findViewById(R.id.buttonViewAllRecipes);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getAllRecipe();
                if(res.getCount()==0){
                    Toast.makeText(ViewRecipeActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Title:\n"+res.getString(1)+"\n");
                    buffer.append("Ingredients:\n"+res.getString(2)+"\n");
                    buffer.append("Recipe:\n"+res.getString(3)+"\n\n\n");
                    buffer.append("********************************************\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewRecipeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("All Recipes");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}