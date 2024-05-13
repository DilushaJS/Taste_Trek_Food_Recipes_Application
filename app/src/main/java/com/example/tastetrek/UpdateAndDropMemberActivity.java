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

public class UpdateAndDropMemberActivity extends AppCompatActivity {


    EditText memUName, memEmail, memContact, pwd, memId;
    Button update, delete;
    DbHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_and_drop_member);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        memUName = findViewById(R.id.usernameUpdateTxt);
        memEmail = findViewById(R.id.usernameUpdateTxt);
        memContact = findViewById(R.id.contactUpdateTxt);
        pwd = findViewById(R.id.pwdUpdateTxt);
        memId= findViewById(R.id.dropMemberTxt);
        DB = new DbHandler(this);
        update = findViewById(R.id.updateButton2); // Initialize the update button
        delete = findViewById(R.id.dropMemberButton);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uNameTXT = memUName.getText().toString();
                String emailTXT = memEmail.getText().toString();
                String contactTXT =memContact.getText().toString();
                String pwdTXT =pwd.getText().toString();

                Boolean checkupdatedata = DB.updatememberdata(uNameTXT, emailTXT, contactTXT, pwdTXT);
                if(checkupdatedata==true)
                    Toast.makeText(UpdateAndDropMemberActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateAndDropMemberActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = memId.getText().toString();
                Boolean checkudeletedata = DB.deletememberdata(idTXT);
                if(checkudeletedata==true)
                    Toast.makeText(UpdateAndDropMemberActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateAndDropMemberActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}