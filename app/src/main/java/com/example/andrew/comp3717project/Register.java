package com.example.andrew.comp3717project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
