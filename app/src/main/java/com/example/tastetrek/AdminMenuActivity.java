package com.example.tastetrek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminMenuActivity extends AppCompatActivity {

    Button insert, view, manageMembers, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        manageMembers=findViewById(R.id.buttonManageMembers);
        manageMembers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),ManageMembersActivity.class);
                startActivity(i);
            }
        });
        insert=findViewById(R.id.buttonInsertRecipe);
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),InsertRecipeActivity.class);
                startActivity(i);
            }
        });

        view=findViewById(R.id.buttonViewRecipes);
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),ViewRecipeActivity.class);
                startActivity(i);
            }
        });

        delete=findViewById(R.id.buttonDeleteRecipes);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),DeleteRecipesActivity.class);
                startActivity(i);
            }
        });

    }
}