package com.rokiel.james.rokieldashboard.activity;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;
import com.rokiel.james.rokieldashboard.R;

public class RestaurantSubmissionsAdapter extends ArrayAdapter<RestaurantSubmission> {
    View customView;
    private Context lContext;
    private ArrayList<RestaurantSubmission> lRestaurantSubmission;
    private int lTextViewResourceID;
    private static LayoutInflater lInflater = null;

    public RestaurantSubmissionsAdapter(Context context, int textViewResourceId, ArrayList<RestaurantSubmission> _lRestaurantSubmission) {
        super(context, R.layout.custom_restaurant_submissions_row, _lRestaurantSubmission);
        lRestaurantSubmission = _lRestaurantSubmission;
        lContext = context;
        lTextViewResourceID = textViewResourceId;
    }

    public int getCount() {
        return lRestaurantSubmission.size();
    }

    public RestaurantSubmission getItem(RestaurantSubmission position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView restaurantSubName;
        public TextView restaurantLocation;
        public TextView restaurantStatus;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lInflater = LayoutInflater.from(getContext());
        customView = lInflater.inflate(R.layout.custom_restaurant_submissions_row,parent,false);

        RestaurantSubmission lRestaurant = getItem(position);
        TextView restaurantSubNameText = (TextView) customView.findViewById(R.id.restaurantSubmissionNameText);
        TextView restaurantLocationText = (TextView) customView.findViewById(R.id.restaurantLocationText);
        TextView restaurantStatusText = (TextView) customView.findViewById(R.id.restaurantStatusText);

        restaurantSubNameText.setText(lRestaurant.RestaurantName);
        restaurantLocationText.setText(lRestaurant.Location);
        restaurantStatusText.setText(lRestaurant.Status);
        customView.setTag(lRestaurant.ID);

        return customView;

    }
}
