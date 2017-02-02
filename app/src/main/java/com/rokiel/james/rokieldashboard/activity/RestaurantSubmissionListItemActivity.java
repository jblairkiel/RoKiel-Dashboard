package com.rokiel.james.rokieldashboard.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rokiel.james.rokieldashboard.R;

public class RestaurantSubmissionListItemActivity extends AppCompatActivity {

    View lview;
    Button denyButton;
    Button approveButton;

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
        TextView lID = (TextView) this.findViewById(R.id.restaurant_submission_id_textView_list_item_result);
        TextView lName= (TextView) this.findViewById(R.id.restaurant_submission_name_textView_list_item_result);
        TextView lLocation= (TextView) this.findViewById(R.id.restaurant_submission_location_textView_list_item_result);
        TextView lStatus= (TextView) this.findViewById(R.id.restaurant_status_textView_list_item_result);

        lID.setText(rID);
        lName.setText(rName);
        lLocation.setText(rLocation);
        lStatus.setText(rStatus);

        approveButton = (Button) findViewById(R.id.approve_restaurant_submission_list_item_button);
        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(RestaurantSubmissionListItemActivity.this)
                        .setTitle("Did Something")
                        .setMessage("Yay")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        denyButton = (Button) findViewById(R.id.deny_restaurant_submission_list_item_button);
        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(RestaurantSubmissionListItemActivity.this)
                        .setTitle("Did Something")
                        .setMessage("Yay")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
