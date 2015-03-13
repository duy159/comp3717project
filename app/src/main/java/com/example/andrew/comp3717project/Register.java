package com.example.andrew.comp3717project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;


public class Register extends Activity implements MongoAdapter {

    // WB apikey, dbname and collection
    private static final String API_KEY = "11h4wQ_5jg2QpLBxQ8mIM0C2HYJ54iyE";
    private static final String DB_NAME = "workoutbuddies";
    private static final String COLLECTION_NAME = "registeredUsers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        /*Button register = (Button) findViewById(R.id.registerButton01);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent registerIntent = new Intent(view.getContext(), Login.class);
                startActivityForResult(registerIntent, 0);
                setResult(RESULT_OK, registerIntent);
                finish();
            }
        });*/
    }
    public void registerMongo(View v) throws JSONException {
        JSONObject register = new JSONObject();
        JSONObject registerQuery = new JSONObject();
        String collection = COLLECTION_NAME;
        String registerUser =((EditText) findViewById(R.id.editText)).getText().toString();
        String registerPass = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String registerEmail =  ((EditText) findViewById(R.id.editText3)).getText().toString();
        String registerFname =  ((EditText) findViewById(R.id.firstName)).getText().toString();
        String registerLname =  ((EditText) findViewById(R.id.lastName)).getText().toString();
        // Passing null as the third parameter will return all documents in the collection

        Mongo.post(this, collection, register.put("user", registerUser));
        Mongo.put(this, collection, register.put("user", registerUser), registerQuery.put("pass", registerPass));
        Mongo.put(this, collection, register.put("user", registerUser), registerQuery.put("email", registerEmail));
        Mongo.put(this, collection,register.put("user",registerUser),registerQuery.put("fname",registerFname));
        Mongo.put(this, collection,register.put("user",registerUser),registerQuery.put("lname",registerLname));

        /*
        Intent loginIntent = new Intent(v.getContext(), MainActivity.class);
        startActivityForResult(loginIntent, 0);*/

    }

    @Override
    public String dbName() {
        return DB_NAME;
    }

    @Override
    public String apiKey() {
        return API_KEY;
    }

    @Override
    public void processResult(String result) {

    }

    @Override
    public void processPass(String result) {

    }
}
