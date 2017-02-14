package com.rokiel.james.rokieldashboard.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import custom_classes.RestaurantSubmissionListItem;

public class RestaurantListItemActivity extends AppCompatActivity {

    private Button editButton = null;
    private Activity myActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(b.getString("name"));
        String rID = b.getString("id");
        String rName = b.getString("name");
        String rAddress = b.getString("address");
        String rType = b.getString("type");
        String rPrice = b.getString("price");
        String rGroup = b.getString("group");

        myActivity = this;


        setContentView(R.layout.activity_restaurant__list__item);
        loadRestaurantListItem(rID, rName, rAddress, rType, rPrice, rGroup);
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

    public void loadRestaurantListItem(String rID, String rName, String rAddress, String rType, String rPrice, String rGroup){
        TextView lID = (TextView) this.findViewById(R.id.restaurant_id_textView_list_item_result);
        EditText lName = (EditText) this.findViewById(R.id.restaurant_name_editText_list_item);
        EditText lAddress = (EditText) this.findViewById(R.id.restaurant_address_editText_list_item);
        EditText lType = (EditText) this.findViewById(R.id.restaurant_type_editText_list_item);
        EditText lPrice = (EditText) this.findViewById(R.id.restaurant_price_editText_list_item);
        EditText lGroup = (EditText) this.findViewById(R.id.restaurant_spinnergroup_editText_list_tem);


        lID.setText(rID);
        lName.setText(rName);
        lAddress.setText(rAddress);
        lType.setText(rType);
        lPrice.setText(rPrice);
        lGroup.setText(rGroup);

        editButton = (Button) findViewById(R.id.approve_restaurant_submission_list_item_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editItemHelper myHelper = new editItemHelper(myActivity);
                myHelper.execute();
            }
        });

    }

    private class editItemHelper extends AsyncTask<Void, Void, Void> {
        String strID;
        String strName;
        String strAddress;
        String strType;
        String strPrice;
        String strSpinnerGroup;

        String result = "";
        String get_Data = "";
        Activity myActivity;

        private editItemHelper(Activity activity){
            myActivity = activity;

            TextView tID = (TextView) findViewById(R.id.restaurant_id_textView_list_item_result);
            EditText tName = (EditText) findViewById(R.id.restaurant_name_editText_list_item);
            EditText tAddress = (EditText) findViewById(R.id.restaurant_address_editText_list_item);
            EditText tType = (EditText) findViewById(R.id.restaurant_type_editText_list_item);
            EditText tPrice = (EditText) findViewById(R.id.restaurant_price_editText_list_item);
            EditText tSpinnerGroup = (EditText) findViewById(R.id.restaurant_spinnergroup_editText_list_tem);

            strID = tID.getText().toString();
            strName = tName.getText().toString();
            strAddress = tAddress.getText().toString();
            strType = tType.getText().toString();
            strPrice = tPrice.getText().toString();
            strSpinnerGroup = tSpinnerGroup.getText().toString();

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
                get_Data = "func=" + URLEncoder.encode("editRestaurant","UTF-8")
                        + "&" + URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(strID,"UTF-8")
                        + "&" + URLEncoder.encode("RestaurantName", "UTF-8") + "=" + URLEncoder.encode(strName,"UTF-8")
                        + "&" + URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(strAddress,"UTF-8")
                        + "&" + URLEncoder.encode("Type", "UTF-8") + "=" + URLEncoder.encode(strType,"UTF-8")
                        + "&" + URLEncoder.encode("Price", "UTF-8") + "=" + URLEncoder.encode(strPrice,"UTF-8")
                        + "&" + URLEncoder.encode("SpinnerGroup", "UTF-8") + "=" + URLEncoder.encode(strSpinnerGroup,"UTF-8");
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
            myActivity.finish();
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

    }
}
