package com.example.andrew.comp3717project;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Register extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = (Button) findViewById(R.id.registerButton01);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent registerIntent = new Intent(view.getContext(), Login.class);
                startActivityForResult(registerIntent, 0);
                setResult(RESULT_OK, registerIntent);
                finish();
            }

        });
    }
}
