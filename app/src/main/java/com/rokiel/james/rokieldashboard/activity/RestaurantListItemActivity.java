package com.rokiel.james.rokieldashboard.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rokiel.james.rokieldashboard.R;

public class RestaurantListItemActivity extends AppCompatActivity {

    Button editButton = null;


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
                new AlertDialog.Builder(RestaurantListItemActivity.this)
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
