package com.example.andrew.comp3717project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent loginIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(loginIntent, 0);
            }

        });

        Button register = (Button) findViewById(R.id.button3);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent RegisterIntent = new Intent(view.getContext(), Register.class);
                startActivityForResult(RegisterIntent, 0);
            }

        });
    }
}
