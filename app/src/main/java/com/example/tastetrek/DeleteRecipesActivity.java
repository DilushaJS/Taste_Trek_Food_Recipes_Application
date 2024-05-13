package com.example.tastetrek;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteRecipesActivity extends AppCompatActivity {

    Button view, delete;
    EditText recId;

    DbHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_recipes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DB = new DbHandler(this);
        view = findViewById(R.id.buttonAllRecipe);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getAllRecipe();
                if(res.getCount()==0){
                    Toast.makeText(DeleteRecipesActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Recipe NUMBER: "+res.getString(0)+"\n");
                    buffer.append("Recipe Title:\n"+res.getString(1)+"\n\n");
                    buffer.append("********************************************\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteRecipesActivity.this);
                builder.setCancelable(true);
                builder.setTitle("All Recipes Numbers and Names");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        delete = findViewById(R.id.buttonRecipeDelete2);
        recId= findViewById(R.id.recipeNumberTxt);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = recId.getText().toString();
                Boolean checkudeletedata = DB.deleteRecipe(idTXT);
                if(checkudeletedata==true)
                    Toast.makeText(DeleteRecipesActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DeleteRecipesActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}