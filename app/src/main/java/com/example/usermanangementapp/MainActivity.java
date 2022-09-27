package com.example.usermanangementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText txtFirstName, txtLastName, txtdob, txtGender, txtNationality, txtUsername, txtPassword;
    private Button btnInsert, btnUpdate, btnDelete, btnView;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtdob = findViewById(R.id.txtdobLv);
        txtGender = findViewById(R.id.txtGender);
        txtNationality = findViewById(R.id.txtNationality);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnView);

        dbManager = new DBManager(this);
        dbManager.open();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Firstnametxt = txtFirstName.getText().toString();
                String Lastnametxt = txtLastName.getText().toString();
                String DOBtxt = txtdob.getText().toString();
                String Gendertxt = txtGender.getText().toString();
                String Nationalitytxt = txtNationality.getText().toString();
                String Usernametxt = txtUsername.getText().toString();
                String Passwordtxt = txtPassword.getText().toString();

                boolean info = dbManager.insert(Firstnametxt,
                        Lastnametxt,
                        DOBtxt,
                        Gendertxt,
                        Nationalitytxt,
                        Usernametxt,
                        Passwordtxt);
                if(info == true)
                    Toast.makeText(MainActivity.this,"New entry inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long _id = 3;
                String Firstnametxt = txtFirstName.getText().toString();
                String Lastnametxt = txtLastName.getText().toString();
                String DOBtxt = txtdob.getText().toString();
                String Gendertxt = txtGender.getText().toString();
                String Nationalityttxt = txtNationality.getText().toString();
                String Usernametxt = txtUsername.getText().toString();
                String Passwordtxt = txtPassword.getText().toString();

                int checkupdate_data = dbManager.update(_id, Firstnametxt, Lastnametxt, DOBtxt, Gendertxt, Nationalityttxt, Usernametxt, Passwordtxt);
//                if (checkupdatedata == true){
//                    Toast.makeText(MainActivity.this, " Entry Updated", Toast.LENGTH_SHORT).show();
//                } else {
//                Toast.makeText(MainActivity.this, " Entry Not Updated", Toast.LENGTH_SHORT).show();
//                }
            }
        });



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Firstnametxt = txtFirstName.getText().toString();


//                dbManager.delete(Firstnametxt);
//                if(checkdeletedata==true)
//                    Toast.makeText(MainActivity.this," Entry Deleted", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(MainActivity.this," Entry Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= dbManager.fetch();
                if (res.getCount()==0){//check if there are records to display
                    Toast.makeText(MainActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){ //loop through the records
                    buffer.append("Firstname : "+res.getString(0)+"\n");
                    buffer.append("Lastname : "+res.getString(1)+"\n");
                    buffer.append("DOB : "+res.getString(2)+"\n");
                    buffer.append("Gender : "+res.getString(3)+"\n");
                    buffer.append("Nationality: "+res.getString(4)+"\n");
                    buffer.append("Username : "+res.getString(5)+"\n");
                    buffer.append("Password: "+res.getString(6)+"\n\n");
                }

//                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Entries");
//                builder.setMessage(buffer.toString());
//                builder.show();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);

            }
        });


    }
}

