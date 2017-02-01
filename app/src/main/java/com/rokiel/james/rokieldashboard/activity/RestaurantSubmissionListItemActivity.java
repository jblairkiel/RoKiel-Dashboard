package com.rokiel.james.rokieldashboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rokiel.james.rokieldashboard.R;

public class RestaurantSubmissionListItemActivity extends AppCompatActivity {

    View lview;

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
        loadRestaurantListItem(rID, rName, rLocation, rStatus);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadRestaurantListItem(String rID, String rName, String rLocation, String rStatus){
        TextView lID = (TextView) this.findViewById(R.id.restaurant_submission_id_textView_list_item_result);
        TextView lName= (TextView) this.findViewById(R.id.restaurant_submission_name_textView_list_item_result);
        TextView lLocation= (TextView) this.findViewById(R.id.restaurant_submission_location_textView_list_item_result);
        TextView lStatus= (TextView) this.findViewById(R.id.restaurant_status_textView_list_item_result);


        lID.setText(rID);
        lName.setText(rName);
        lLocation.setText(rLocation);
        lStatus.setText(rStatus);
    }
}
