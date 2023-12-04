package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Mylogin extends AppCompatActivity {
    EditText passlog,maillog;
    TextView newuser;
    Button login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylogin);
        passlog=findViewById(R.id.passlog);
        maillog=findViewById(R.id.maillog);
        newuser=findViewById(R.id.newaccount);
        login=findViewById(R.id.bulog);
        Backendless.initApp(this,"08E80386-F8C9-372E-FF69-2C2C07F73C00","20DEE612-F342-4718-8754-1A021AA9AD5C");


    }

    public void login(View view) {
        Backendless.UserService.login(maillog.getText().toString(), passlog.getText().toString(), new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(Mylogin.this, "login success", Toast.LENGTH_SHORT).show();
                Intent in =new Intent(Mylogin.this,MainActivity.class);
                startActivity(in);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                if (fault.getCode().equals("3003"));
                Toast.makeText(Mylogin.this, "invalid data", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void newuser(View view) {
        Intent intent=new Intent(this, Register.class);
        startActivity(intent);
    }
}