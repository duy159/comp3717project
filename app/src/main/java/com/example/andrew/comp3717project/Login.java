package com.example.andrew.comp3717project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends Activity implements MongoAdapter {

    // WB apikey, dbname and collection
    private static final String API_KEY = "11h4wQ_5jg2QpLBxQ8mIM0C2HYJ54iyE";
    private static final String DB_NAME = "workoutbuddies";
    private static final String COLLECTION_NAME = "registeredUsers";
    //username and pass
    private String user, pass;
    //boolean for login
    public boolean loginSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        JSONObject user = new JSONObject();
        String collection = COLLECTION_NAME;
        String myUserName =((EditText) findViewById(R.id.editText)).getText().toString();
        String myPass = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String incorrect = "Incorrect user/pass.";

        Button login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(loginSuccess== true) {
                    Intent loginIntent = new Intent(view.getContext(), MainActivity.class);
                    startActivityForResult(loginIntent, 0);
                }
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
    // Method should return the name of the database you want to access
    public String dbName()
    {
        return DB_NAME;
    }

    // Method should return the API Key as shown at the bottom of the MongoLab user page
    public String apiKey()
    {
        return API_KEY;
    }

    @Override
    public void processResult(String result) {

    }

    // Callback method that tests the MongoLab REST api
    public void goMongo(View v) throws JSONException {
        JSONObject user = new JSONObject();
        String collection = COLLECTION_NAME;
        String myUserName =((EditText) findViewById(R.id.editText)).getText().toString();
        String myPass = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String incorrect = "Incorrect user/pass.";
        // Passing null as the third parameter will return all documents in the collection

       if(Mongo.getUser(this, collection, user.put("user", myUserName)))
       {
            if(Mongo.getUser(this, collection, user.put("pass", myPass)))
            {
                loginSuccess = true;
            }
           else
            {
                Toast.makeText( this, incorrect, Toast.LENGTH_LONG ).show();
            }
       }
        else
       {
           Toast.makeText(this, incorrect, Toast.LENGTH_LONG).show();
       }


    }
}
