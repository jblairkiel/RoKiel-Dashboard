package adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;
import com.rokiel.james.rokieldashboard.R;
import custom_classes.RestaurantSubmissionListItem;

public class RestaurantSubmissionsListAdapter extends ArrayAdapter<RestaurantSubmissionListItem> {
    View customView;
    private Context lContext;
    private ArrayList<RestaurantSubmissionListItem> lRestaurantSubmissionListItem;
    private int lTextViewResourceID;
    private static LayoutInflater lInflater = null;

    public RestaurantSubmissionsListAdapter(Context context, int textViewResourceId, ArrayList<RestaurantSubmissionListItem> _lRestaurantSubmissionListItem) {
        super(context, R.layout.custom_restaurant_submissions_row, _lRestaurantSubmissionListItem);
        lRestaurantSubmissionListItem = _lRestaurantSubmissionListItem;
        lContext = context;
        lTextViewResourceID = textViewResourceId;
    }

    public int getCount() {
        return lRestaurantSubmissionListItem.size();
    }

    public RestaurantSubmissionListItem getItem(RestaurantSubmissionListItem position) {
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

        RestaurantSubmissionListItem lRestaurant = getItem(position);
        TextView restaurantSubNameText = (TextView) customView.findViewById(R.id.restaurantSubmissionNameText);
        TextView restaurantLocationText = (TextView) customView.findViewById(R.id.restaurantLocationText);
        TextView restaurantStatusText = (TextView) customView.findViewById(R.id.restaurantStatusText);

        restaurantSubNameText.setText(lRestaurant.getRestaurantName());
        restaurantLocationText.setText(lRestaurant.getLocation());
        restaurantStatusText.setText(lRestaurant.getStatus());
        customView.setTag(lRestaurant.getID());

        return customView;

    }
}
