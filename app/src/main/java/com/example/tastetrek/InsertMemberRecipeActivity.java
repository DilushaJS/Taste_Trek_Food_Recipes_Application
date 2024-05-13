package com.example.tastetrek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertMemberRecipeActivity extends AppCompatActivity {

    EditText ingredients, title, description;
    Button save;
    DbHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_member_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        title = findViewById(R.id.editTextTitle2);
        ingredients = findViewById(R.id.editTextIngredients2);
        description = findViewById(R.id.editTextDescription2);
        save = findViewById(R.id.buttonSave2);
        DB = new DbHandler(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleTXT = title.getText().toString();
                String ingreTXT = ingredients.getText().toString();
                String descTXT = description.getText().toString();
                Integer id= 1;

                Boolean checkinsertdata = DB.insertRecipe(titleTXT, ingreTXT, descTXT,id);
                if(checkinsertdata==true)
                    Toast.makeText(InsertMemberRecipeActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(InsertMemberRecipeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
    }
}