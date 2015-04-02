package com.example.andrew.comp3717project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Andrew on 1/21/2015.
 */
public class PartnersFragment extends Fragment implements MongoAdapter{
    // WB apikey, dbname and collection
    private static final String API_KEY = "11h4wQ_5jg2QpLBxQ8mIM0C2HYJ54iyE";
    private static final String DB_NAME = "workoutbuddies";
    private static final String COLLECTION_NAME = "registeredUsers";
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {

        String collection = COLLECTION_NAME;
        Mongo.get(this, collection, null);
        // Inflate the layout for this fragment\
        return inflater.inflate( R.layout.fragment_partners, container, false );
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
        String dbDays;
        String dbStartTime;
        String dbEndTime;
        String myUserStartTime = Login.getGlobalTimeStart();
        String myUserEndTime = Login.getGlobalTimeEnd();
        String myUserDay = Login.getGlobalDay();
        String matchingUser;
        ArrayList<ArrayList> allUsers = new ArrayList<ArrayList>();

        JSONArray resultArray = null;
        try {
            resultArray = new JSONArray(result);
            for(int i = 0; i < resultArray.length();i++) {
                JSONObject resultObject = new JSONObject(resultArray.getString(i));
                dbDays = resultObject.getString("day");
                ArrayList<String> dbUsers = new ArrayList<String>();

                if(myUserDay.equals(dbDays)) {
                    dbEndTime = resultObject.getString("timeend");
                    dbStartTime = resultObject.getString("timestart");
                    /*
                    if((Integer.parseInt(myUserStartTime) < Integer.parseInt(dbEndTime) && Integer.parseInt(dbStartTime) < Integer.parseInt(myUserEndTime))
                            || (Integer.parseInt(myUserStartTime) == Integer.parseInt(dbStartTime)))
                     {
                        matchingUser = resultObject.getString("fname");


                        dbUsers.add(matchingUser);
                    }*/
                }
                allUsers.add(dbUsers);
            }
            if(allUsers.size() == 0)
            {
                Toast.makeText(getActivity(),"You have no friends.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* for(ArrayList x: allUsers) {
            //broken. for some reason dbUsers elements are stored as objects and not string. figure this out later
            for(String y: x) {
                Toast.makeText(getActivity(), y, Toast.LENGTH_SHORT).show();
            }
        }*/

    }
}
