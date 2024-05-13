package com.example.tastetrek;

import android.content.Intent;
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

public class ManageMembersActivity extends AppCompatActivity {

    Button view, Updatememberbutton;
    DbHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_members);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        DB = new DbHandler(this);
        view = findViewById(R.id.buttonViewMembers);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getmemberdata();
                if(res.getCount()==0){
                    Toast.makeText(ManageMembersActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Member ID : "+res.getString(0)+"\n");
                    buffer.append("User Name :"+res.getString(1)+"\n");
                    buffer.append("Email Address :"+res.getString(2)+"\n");
                    buffer.append("Contact Number :"+res.getString(3)+"\n");
                    buffer.append("Password:"+res.getString(4)+"\n");
                    buffer.append("Number of Post"+res.getString(5)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ManageMembersActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        Updatememberbutton = findViewById(R.id.buttonUpdateMemberDetails);
        Updatememberbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {
                Intent i = new Intent(getApplicationContext(), UpdateAndDropMemberActivity.class);
                startActivity(i);
            }
        });
    }
}