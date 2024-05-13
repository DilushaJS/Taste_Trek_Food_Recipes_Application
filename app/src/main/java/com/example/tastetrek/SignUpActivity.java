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

public class SignUpActivity extends AppCompatActivity {

    EditText memUName, memEmail, memContact, pwd, pwd2;
    Button insert;
    DbHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        memUName = findViewById(R.id.usernamesignuptxt);
        memEmail = findViewById(R.id.emailtxt);
        memContact = findViewById(R.id.contacttxt);
        pwd = findViewById(R.id.pwdsignuptxt);
        pwd2 = findViewById(R.id.pwdsignuptxt2);
        insert = findViewById(R.id.signupbutton2);
        DB = new DbHandler(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uNameTXT = memUName.getText().toString();
                String emailTXT = memEmail.getText().toString();
                String contactTXT =memContact.getText().toString();
                String pwdTXT =pwd.getText().toString();

                Boolean checkinsertdata = DB.insertmemberdata(uNameTXT, emailTXT, contactTXT, pwdTXT);
                if(checkinsertdata==true) {
                    Toast.makeText(SignUpActivity.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Not Registered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}