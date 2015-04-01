package com.example.andrew.comp3717project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Login extends Activity implements MongoAdapter {

    // WB apikey, dbname and collection
    private static final String API_KEY = "11h4wQ_5jg2QpLBxQ8mIM0C2HYJ54iyE";
    private static final String DB_NAME = "workoutbuddies";
    private static final String COLLECTION_NAME = "registeredUsers";
    //boolean for login
    public boolean okUser = false;
    public boolean okPass = false;
    //user info
    public static String globalUser;
    public static String getGlobalUser()
    {
        return globalUser;
    }
    public static void setGlobalUser(String setUser)
    {
        globalUser = setUser;
    }
    public static String globalFirst;
    public static String getGlobalFirst()
    {
        return globalFirst;
    }
    public static void setGlobalFirst(String setFirst)
    {
        globalFirst = setFirst;
    }
    public static String globalLast;
    public static String getGlobalLast()
    {
        return globalLast;
    }
    public static void setGlobalLast(String setLast)
    {
        globalLast = setLast;
    }
    public static String globalEmail;
    public static String getGlobalEmail()
    {
        return globalEmail;
    }
    public static void setGlobalEmail(String setEmail)
    {
        globalEmail = setEmail;
    }
    public static String globalPass;
    public static String getGlobalPass()
    {
        return globalPass;
    }
    public static void setGlobalPass(String setPass)
    {
        globalPass = setPass;
    }
    public static String globalDay;
    public static String getGlobalDay()
    {
        return globalDay;
    }
    public static void setGlobalDay(String setDay)
    {
        globalDay = setDay;
    }
    public static String globalTime;
    public static String getGlobalTime()
    {
        return globalTime;
    }
    public static void setGlobalTime(String setTime)
    {
        globalTime = setTime;
    }
    public static String globalPlace;
    public static String getGlobalPlace()
    {
        return globalPlace;
    }
    public static  void setGlobalPlace(String setPlace)
    {
        globalPlace = setPlace;
    }
    public static String globalProfile;
    public static String getGlobalProfile()
    {
        return globalProfile;
    }
    public static void setGlobalProfile(String setProfile)
    {
        globalProfile = setProfile;
    }
    public static String globalPhone;
    public static String getGlobalPhone() {return globalPhone; }
    public static void setGlobalPhone(String setPhone) { globalPhone = setPhone; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

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


    public void processResult( String result )
    {
        String incorrect = "Incorrect user/pass";
        String success = "Login success!";
        String user;
        String pass;
        String enteredUser;
        String enteredPass;
        if(result.equals("[  ]"))
        {
            Toast.makeText(this,incorrect,Toast.LENGTH_SHORT).show();
        }
        try {
            JSONArray resultArray = new JSONArray(result);
            JSONObject resultObject = new JSONObject(resultArray.getString(0));
            user = resultObject.getString("user");
            pass = resultObject.getString("pass");
            enteredUser = ((EditText)findViewById( R.id.editText )).getText().toString();
            enteredPass = ((EditText)findViewById( R.id.editText2 )).getText().toString();
            if(user.equals(enteredUser))
            {
                okUser = true;
            }
            else
            {
                okUser = false;
            }
            if(pass.equals(enteredPass))
            {
                okPass = true;
            }
            else
            {
                okPass = false;
            }

            if(okUser == false)
            {
                Toast.makeText(this,incorrect,Toast.LENGTH_SHORT).show();
            }
            if(okPass == false)
            {
                Toast.makeText(this,incorrect,Toast.LENGTH_SHORT).show();
            }
            if(okUser == true && okPass == true)
            {
                globalUser = resultObject.getString("user");
                globalFirst = resultObject.getString("fname");
                globalLast  = resultObject.getString("lname");
                globalEmail = resultObject.getString("email");
                globalPass = resultObject.getString("pass");
                globalDay = resultObject.getString("day");
                globalTime = resultObject.getString("time");
                globalPlace = resultObject.getString("place");
                globalProfile = resultObject.getString("profile");
                Toast.makeText(this, success,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }
    }

    // Callback method that tests the MongoLab REST api

    public void loginMongo(View v) throws JSONException {
        String collection = COLLECTION_NAME;
        String myUserName =((EditText) findViewById(R.id.editText)).getText().toString();
        JSONObject user = new JSONObject();
        // Passing null as the third parameter will return all documents in the collection
        Mongo.get(this, collection, user.put("user",myUserName));
    }
}
