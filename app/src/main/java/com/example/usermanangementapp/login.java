package com.example.usermanangementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class login extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtUsername;
    private EditText txtPassword;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbManager = new DBManager(this);
        dbManager.open();

        btnLogin = findViewById(R.id.btnLogin);
        txtUsername = findViewById(R.id.txtUsernameLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);

        btnLogin.setOnClickListener(view -> {
            String username = txtUsername.getText().toString();
            String password = txtPassword.getText().toString();
            Intent intent = new Intent(getApplicationContext(), details.class);

            boolean permission = dbManager.login(username, password);

            boolean done = false;
            int x = 3;

            while(!done){
                if (x != 0){
                    if (permission){
                        startActivity(intent);
                        break;
                    } else {
                        x--;
                        Toast.makeText(getApplicationContext(), "Wrong Username of password. " + x + "trials remaining", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    done = true;

//                    makes the button dissappear
                    btnLogin.setVisibility(View.GONE);
                }
            }
        });

    }
}