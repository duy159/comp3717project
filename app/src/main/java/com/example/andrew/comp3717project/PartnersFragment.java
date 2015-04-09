package com.example.andrew.comp3717project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    ArrayList<String> dbUsers = new ArrayList<String>();
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
        int dbStartTime;
        int dbEndTime;
        int myUserStartTime = Login.getGlobalTimeStart();
        int myUserEndTime = Login.getGlobalTimeEnd();
        String myUserDay = Login.getGlobalDay();
        String matchingUser;


        JSONArray resultArray = null;
        try {
            resultArray = new JSONArray(result);
            for(int i = 0; i < resultArray.length();i++) {
                JSONObject resultObject = new JSONObject(resultArray.getString(i));
                dbDays = resultObject.getString("day");

                if(myUserDay.equals(dbDays)) {
                    dbEndTime = resultObject.getInt("timeend");
                    dbStartTime = resultObject.getInt("timestart");

                    if((myUserStartTime < dbEndTime && dbStartTime < myUserEndTime)
                            ||(myUserStartTime == dbStartTime))
                     {
                         matchingUser = resultObject.getString("fname");
                         matchingUser += resultObject.getString("lname");
                         dbUsers.add(matchingUser);
                    }
                }
            }
          if(dbUsers.size() <= 1)
            {
                Toast.makeText(getActivity(),"You have no friends.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        displayPartners(this.getView());
    }

    public void displayPartners(View v)
    {
        ListView partnerView = (ListView)v.findViewById(R.id.partners);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, dbUsers);
        partnerView.setAdapter(adapter);

    }
}
