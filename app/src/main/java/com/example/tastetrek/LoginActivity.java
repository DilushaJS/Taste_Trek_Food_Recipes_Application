package com.example.tastetrek;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    Button Registerbutton, Loginbutton, Forgotbutton, Gorecipes;
    EditText txtun, txtpw;

    DbHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Loginbutton = findViewById(R.id.loginbutton);
        Forgotbutton = findViewById(R.id.forgotpwbutton);
        Gorecipes = findViewById(R.id.buttonGoRecipes);
        txtun = findViewById(R.id.recipenumtxt);
        txtpw = findViewById(R.id.pwdtxt);
        Registerbutton = findViewById(R.id.createaccbutton);
        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });

        DB = new DbHandler(this);
        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtun.getText().toString();
                String password = txtpw.getText().toString();
                if (username.equals("admin") && password.equals("admin123")) {
                    Intent i = new Intent(getApplicationContext(), AdminMenuActivity.class);
                    startActivity(i);
                } else {
                    if (DB.checkMemberCredentials(username, password)) {
                        Intent intent = new Intent(LoginActivity.this, MemberMenuActivity.class);
                        intent.putExtra("MY_UN", username);
                        intent.putExtra("MY_PW", password);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
        Forgotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ForgotPwActivity.class);
                startActivity(i);
            }
        });
        Gorecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewRecipeActivity.class);
                startActivity(i);
            }
        });
    }
}