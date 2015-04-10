package com.example.andrew.comp3717project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Andrew on 1/21/2015.
 */
public class PartnersFragment extends Fragment implements MongoAdapter, View.OnClickListener{
    // WB apikey, dbname and collection
    private static final String API_KEY = "11h4wQ_5jg2QpLBxQ8mIM0C2HYJ54iyE";
    private static final String DB_NAME = "workoutbuddies";
    private static final String COLLECTION_NAME = "registeredUsers";
    ListView partnerView;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        View view = inflater.inflate( R.layout.fragment_partners, container, false );
        Button b = (Button) view.findViewById(R.id.getPartnerBtn);
        partnerView = (ListView)view.findViewById(R.id.partners);
        b.setOnClickListener(this);

        // Inflate the layout for this fragment\

        return view;
    }
    public void onClick(View v)
    {
        String collection = COLLECTION_NAME;
        Mongo.get(this, collection, null);
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
        ArrayList<String> dbUsers = new ArrayList<String>();
        String dbDays;
        int dbStartTime;
        int dbEndTime;
        int myUserStartTime = Login.getGlobalTimeStart();
        int myUserEndTime = Login.getGlobalTimeEnd();
        String myUserDay = Login.getGlobalDay();
        String globalFirstLast = Login.getGlobalFirst() + Login.getGlobalLast();
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

                         if (!matchingUser.equals(globalFirstLast)) {
                             dbUsers.add(matchingUser);
                         }
                    }
                }
            }
          if(dbUsers.size() <= 0)
            {
                Toast.makeText(getActivity(),"You have no friends.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        displayPartners(this.getView(), dbUsers);
    }

    public void displayPartners(View v, ArrayList<String> dbArray)
    {
        partnerView.setAdapter(null);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dbArray);
        partnerView.setAdapter(adapter);

    }
}
