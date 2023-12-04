package com.example.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Register extends AppCompatActivity implements AsyncCallback<BackendlessUser> {
    EditText name,pass,mail;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.nametext);
        pass=findViewById(R.id.passtext);
        mail=findViewById(R.id.mailtext);

    }

    public void save(View view) {
        BackendlessUser user=new BackendlessUser();
        user.setProperty("name",name.getText().toString());
        user.setPassword(pass.getText().toString());
        user.setEmail(mail.getText().toString());
        Backendless.UserService.register(user,this);

    }

    @Override
    public void handleResponse(BackendlessUser response) {
        Toast.makeText(this, "user registered", Toast.LENGTH_SHORT).show();
        Intent in =new Intent(this,MainActivity.class);
        startActivity(in);

    }

    @Override
    public void handleFault(BackendlessFault fault) {

        if (fault.getCode().equals("3033")){
            Toast.makeText(this, "user exist", Toast.LENGTH_SHORT).show();

        } else if (fault.getCode().equals("3013")) {
            Toast.makeText(this, "please enter all data", Toast.LENGTH_SHORT).show();

        }


    }
}