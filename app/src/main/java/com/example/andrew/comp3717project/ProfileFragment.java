package com.example.andrew.comp3717project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment  implements MongoAdapter,View.OnClickListener{
    // WB apikey, dbname and collection
    private static final String API_KEY = "11h4wQ_5jg2QpLBxQ8mIM0C2HYJ54iyE";
    private static final String DB_NAME = "workoutbuddies";
    private static final String COLLECTION_NAME = "registeredUsers";
    EditText profileField;
    EditText phoneField;
    EditText emailField;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileField = (EditText)view.findViewById((R.id.editText6));
        phoneField = (EditText)view.findViewById((R.id.editText5));
        emailField = (EditText)view.findViewById((R.id.editText4));
        Button editButton = (Button) view.findViewById(R.id.button4);
        editButton.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }
    public void onClick(View v) {

        String success = "Profile Edited!";
        String collection = COLLECTION_NAME;
        JSONObject editUser = new JSONObject();
        JSONObject editQuery = new JSONObject();
        String myUser = Login.getGlobalUser();

        String editProfile = profileField.getText().toString();
        String editEmail = emailField.getText().toString();
        String editPhone = phoneField.getText().toString();

        try {
            Mongo.put(this, collection, editUser.put("user",myUser), editQuery.put("profile", editProfile));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Mongo.put(this, collection, editUser.put("user",myUser), editQuery.put("email", editEmail));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Mongo.put(this, collection, editUser.put("user",myUser), editQuery.put("phone", editPhone));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Login.setGlobalProfile(editProfile);
        Login.setGlobalEmail(editEmail);
        Login.setGlobalPhone(editPhone);
        Toast.makeText(getActivity(), success, Toast.LENGTH_SHORT).show();
    }


    @Override
    public String dbName() {
        return  DB_NAME;
    }

    @Override
    public String apiKey() {
        return API_KEY;
    }

    @Override
    public void processResult(String result) {

    }
}
