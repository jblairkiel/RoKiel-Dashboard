package com.rokiel.james.rokieldashboard.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rokiel.james.rokieldashboard.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import custom_classes.RestaurantListItem;

public class RestaurantSubmissionListItemActivity extends AppCompatActivity {

    Button denyButton;
    Button approveButton;
    TextView lID;
    TextView lName;
    TextView lLocation;
    TextView lStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(b.getString("name"));
        String rID = b.getString("id");
        String rName = b.getString("name");
        String rLocation = b.getString("location");
        String rStatus = b.getString("status");

        setContentView(R.layout.activity_restaurant__submission_list__item);
        loadRestaurantSubmissionListItem(rID, rName, rLocation, rStatus);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadRestaurantSubmissionListItem(String rID, String rName, String rLocation, String rStatus){
        lID = (TextView) this.findViewById(R.id.restaurant_submission_id_textView_list_item_result);
        lName= (TextView) this.findViewById(R.id.restaurant_submission_name_textView_list_item_result);
        lLocation= (TextView) this.findViewById(R.id.restaurant_submission_location_textView_list_item_result);
        lStatus= (TextView) this.findViewById(R.id.restaurant_status_textView_list_item_result);

        lID.setText(rID);
        lName.setText(rName);
        lLocation.setText(rLocation);
        lStatus.setText(rStatus);

        approveButton = (Button) findViewById(R.id.approve_restaurant_submission_list_item_button);
        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new approveItemHelper().execute();
            }
        });

        denyButton = (Button) findViewById(R.id.deny_restaurant_submission_list_item_button);
        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new denyItemHelper().execute();
            }
        });
    }

    private class denyItemHelper extends AsyncTask<Void, Void, Void> {
        //View lView;
        String itemID = "";
        String result = "";
        String get_Data = "";

        private denyItemHelper(){
            itemID = findViewById(R.id.restaurant_submission_id_textView_list_item_result).toString();
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                String sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?";
                URL url = new URL(sURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                get_Data = "?func=" + URLEncoder.encode("denyRestaurantSubmission","UTF-8");
                bufferedWriter.write(get_Data);
                get_Data = "&ID=" + URLEncoder.encode(itemID,"UTF-8");
                bufferedWriter.write(get_Data);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;
                while((line=bufferedReader.readLine()) != null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid){
            getSupportFragmentManager().popBackStackImmediate();
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

    }

    private class approveItemHelper extends AsyncTask<Void, Void, Void> {
        //View lView;
        String itemID = "";
        String result = "";
        String get_Data = "";

        private approveItemHelper(){
            itemID = findViewById(R.id.restaurant_submission_id_textView_list_item_result).toString();
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                String sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?";
                URL url = new URL(sURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                get_Data = "?func=" + URLEncoder.encode("approveRestaurantSubmission","UTF-8");
                bufferedWriter.write(get_Data);
                get_Data = "&ID=" + URLEncoder.encode(itemID,"UTF-8");
                bufferedWriter.write(get_Data);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;
                while((line=bufferedReader.readLine()) != null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid){
            new restaurantItemHelper().execute();
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

    }

    private class restaurantItemHelper extends AsyncTask<Void, Void, Void> {
        //View lView;
        String itemID = "";
        String result = "";
        String get_Data = "";

        private restaurantItemHelper(){
            itemID = findViewById(R.id.restaurant_submission_id_textView_list_item_result).toString();
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                String sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?";
                URL url = new URL(sURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                get_Data = "?func=" + URLEncoder.encode("deny","UTF-8");
                bufferedWriter.write(get_Data);
                get_Data = "&ID=" + URLEncoder.encode(itemID,"UTF-8");
                bufferedWriter.write(get_Data);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;
                while((line=bufferedReader.readLine()) != null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid){
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

    }
}
