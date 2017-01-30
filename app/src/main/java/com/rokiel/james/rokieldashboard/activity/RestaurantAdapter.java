package com.rokiel.james.rokieldashboard.activity;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;
import com.rokiel.james.rokieldashboard.R;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    View customView;
    private Context lContext;
    private ArrayList<Restaurant> lRestaurant;
    private int lTextViewResourceId;
    private static LayoutInflater inflater = null;

    public RestaurantAdapter(Context context, int textViewResourceId, ArrayList<Restaurant> _lRestaurant) {
        super(context, R.layout.custom_restaurant_list_row, _lRestaurant);
        lRestaurant = _lRestaurant;
        lContext = context;
        lTextViewResourceId = textViewResourceId;
    }

    public int getCount() {
        return lRestaurant.size();
    }

    public Restaurant getItem(Restaurant position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_number;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lInflater = LayoutInflater.from(getContext());
        customView = lInflater.inflate(R.layout.custom_restaurant_list_row,parent,false);

        Restaurant lRestaurant = getItem(position);
        TextView restaurantText = (TextView) customView.findViewById(R.id.restaurantNameText);
        TextView restaurantAddressText = (TextView) customView.findViewById(R.id.restaurantAddressText);

        restaurantText.setText(lRestaurant.RestaurantName);
        restaurantAddressText.setText(lRestaurant.Address);
        customView.setTag(lRestaurant.ID);

        return customView;

    }
}
