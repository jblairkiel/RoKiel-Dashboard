package com.rokiel.james.rokieldashboard.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.rokiel.james.rokieldashboard.R;
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

import fragment.Restaurant_List_Fragment;

public class RestaurantSubmissionListItemActivity extends AppCompatActivity {

    private Button denyButton;
    private Button approveButton;
    private TextView lID;
    private TextView lName;
    private TextView lLocation;
    private TextView lStatus;
    private Activity myActivity;
    private String rID;
    private String rName;
    private String rLocation;
    private String rStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(b.getString("name"));
        rID = b.getString("id");
        rName = b.getString("name");
        rLocation = b.getString("location");
        rStatus = b.getString("status");
        myActivity = this;

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
                approveItemHelper myHelper = new approveItemHelper(myActivity);
                myHelper.execute();
            }
        });

        denyButton = (Button) findViewById(R.id.deny_restaurant_submission_list_item_button);
        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denyItemHelper myHelper = new denyItemHelper(myActivity);
                myHelper.execute();
            }
        });
    }

    private class denyItemHelper extends AsyncTask<Void, Void, Void> {
        //View lView;
        private String itemID = "";
        private String result = "";
        private String get_Data = "";
        private Activity mActivity;

        private denyItemHelper(Activity activity){
            mActivity = activity;
            TextView tID = (TextView) findViewById(R.id.restaurant_submission_id_textView_list_item_result);
            itemID = tID.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                String sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?";
                URL url = new URL(sURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                get_Data = URLEncoder.encode("func", "UTF-8") + "=" + URLEncoder.encode("denyRestaurantSubmission","UTF-8") + "&" + URLEncoder.encode("itemID", "UTF-8") + "=" + URLEncoder.encode(itemID,"UTF-8");
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
            mActivity.finish();
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

    }

    private class approveItemHelper extends AsyncTask<Void, Void, Void> {
        String itemID = "";
        String result = "";
        String get_Data = "";
        Activity myActivity;

        private approveItemHelper(Activity activity){
            myActivity = activity;

            TextView tID = (TextView) findViewById(R.id.restaurant_submission_id_textView_list_item_result);
            itemID = tID.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                //Post Approve
                String sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?";
                URL url = new URL(sURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                get_Data = "func=" + URLEncoder.encode("approveRestaurantSubmission","UTF-8") + "&" + URLEncoder.encode("itemID", "UTF-8") + "=" + URLEncoder.encode(itemID,"UTF-8");
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

                result = "";

                //Post New Restaurant
                sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?";
                url = new URL(sURL);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                outputStream = httpURLConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                get_Data = "func=" + URLEncoder.encode("postNewRestaurant","UTF-8") + "&" + URLEncoder.encode("RestaurantName", "UTF-8") + "=" + URLEncoder.encode(rName,"UTF-8") + "&" + URLEncoder.encode("Address") + "=" + URLEncoder.encode(rLocation, "UTF-8");
                bufferedWriter.write(get_Data);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

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
            myActivity.finish();
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

    }
}
