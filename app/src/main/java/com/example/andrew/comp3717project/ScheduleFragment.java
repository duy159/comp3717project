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

/**
 * Created by Andrew on 1/21/2015.
 */
public class ScheduleFragment extends Fragment implements MongoAdapter{
    // WB apikey, dbname and collection
    private static final String API_KEY = "11h4wQ_5jg2QpLBxQ8mIM0C2HYJ54iyE";
    private static final String DB_NAME = "workoutbuddies";
    private static final String COLLECTION_NAME = "registeredUsers";
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        // Inflate the layout for this fragment\
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        Button b = (Button) view.findViewById(R.id.setScheduleBtn);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText startTimeEdit = ((EditText) v.findViewById(R.id.startTimeInput));
                EditText endTimeEdit = ((EditText) v.findViewById(R.id.endTimeInput));
                EditText dayEdit = ((EditText) v.findViewById(R.id.dayInput));
                String success = "Schedule Edited!";
                String collection = COLLECTION_NAME;
                JSONObject editUser = new JSONObject();
                String myUser = Login.getGlobalUser();

                String dbStartTime = startTimeEdit.getText().toString();
                String dbEndTime = endTimeEdit.getText().toString();
                String dbDay = dayEdit.getText().toString();
                try {
                    Mongo.put(ScheduleFragment.this, collection, editUser.put("user", myUser), editUser.put("starttime", dbStartTime));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    Mongo.put(ScheduleFragment.this, collection, editUser.put("user", myUser), editUser.put("endtime", dbEndTime));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    Mongo.put(ScheduleFragment.this, collection, editUser.put("user", myUser), editUser.put("day", dbDay));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Login.setGlobalDay(dbDay);
                Login.setGlobalTimeStart(Integer.parseInt(dbStartTime));
                Login.setGlobalTimeEnd(Integer.parseInt(dbEndTime));
                Toast.makeText(getActivity(), success, Toast.LENGTH_SHORT).show();

            }
        });
        // Inflate the layout for this fragment\
        return view;
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

}
