package com.example.andrew.comp3717project;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements MongoAdapter {
    // WB apikey, dbname and collection
    private static final String API_KEY = "11h4wQ_5jg2QpLBxQ8mIM0C2HYJ54iyE";
    private static final String DB_NAME = "workoutbuddies";
    private static final String COLLECTION_NAME = "registeredUsers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setup action bar for tabs
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        Tab tab = actionBar.newTab()
                .setText(R.string.tab_partners)
                .setTabListener(new TabListener<PartnersFragment>(
                        this, "Partners", PartnersFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText(R.string.tab_schedule)
                .setTabListener(new TabListener<ScheduleFragment>(
                        this, "Workout Schedule", ScheduleFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText(R.string.tab_profile)
                .setTabListener(new TabListener<ProfileFragment>(
                        this, "Profile", ProfileFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText(R.string.tab_location)
                .setTabListener(new TabListener<LocationFragment>(
                        this, "Location", LocationFragment.class));
        actionBar.addTab(tab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



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
    public void editProfile(View v)
    {
        String success = "Profile Edited!";
        String collection = COLLECTION_NAME;
        JSONObject editUser = new JSONObject();
        String myUser = Login.getGlobalUser();
        String editProfile = ((EditText) findViewById(R.id.editText6)).getText().toString();
        String editPhone = ((EditText) findViewById(R.id.editText5)).getText().toString();
        String editEmail = ((EditText) findViewById(R.id.editText4)).getText().toString();
        try {
            Mongo.put(this, collection, editUser.put("user",myUser), editUser.put("profile", editProfile));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Mongo.put(this, collection, editUser.put("user",myUser), editUser.put("email", editEmail));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Mongo.put(this, collection, editUser.put("user",myUser), editUser.put("phone", editPhone));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Login.setGlobalProfile(editProfile);
        Login.setGlobalEmail(editEmail);
        Login.setGlobalPhone(editPhone);
        Toast.makeText(this, success, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState( Bundle outState )
    {
        super.onSaveInstanceState( outState );
        outState.putInt( "tab", getActionBar().getSelectedNavigationIndex() );
    }

    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        /** Constructor used each time a new tab is created.
         * @param activity  The host Activity, used to instantiate the fragment
         * @param tag  The identifier tag for the fragment
         * @param clz  The fragment's Class, used to instantiate the fragment
         */
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }/* The following are each of the ActionBar.TabListener callbacks */

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // Check if the fragment is already initialized
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }

    }


}
