package adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;
import com.rokiel.james.rokieldashboard.R;
import custom_classes.RestaurantListItem;

public class RestaurantListAdapter extends ArrayAdapter<RestaurantListItem>{
    View customView;
    private Context lContext;
    private ArrayList<RestaurantListItem> lRestaurantListItem;
    private int lTextViewResourceId;
    private static LayoutInflater inflater = null;

    public RestaurantListAdapter(Context context, int textViewResourceId, ArrayList<RestaurantListItem> _lRestaurantListItem) {
        super(context, R.layout.custom_restaurant_list_row, _lRestaurantListItem);
        lRestaurantListItem = _lRestaurantListItem;
        lContext = context;
        lTextViewResourceId = textViewResourceId;
    }

    public int getCount() {
        return lRestaurantListItem.size();
    }

    public RestaurantListItem getItem(RestaurantListItem position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_number;

    }

    public void udpateAdapter(ArrayList<RestaurantListItem> _lRestaurantListItem){
        lRestaurantListItem = _lRestaurantListItem;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lInflater = LayoutInflater.from(getContext());
        customView = lInflater.inflate(R.layout.custom_restaurant_list_row,parent,false);

        RestaurantListItem lRestaurantListItem = getItem(position);
        TextView restaurantText = (TextView) customView.findViewById(R.id.restaurantNameText);
        TextView restaurantAddressText = (TextView) customView.findViewById(R.id.restaurantAddressText);

        restaurantText.setText(lRestaurantListItem.getRestaurantName());
        restaurantAddressText.setText(lRestaurantListItem.getAddress());
        customView.setTag(lRestaurantListItem.getID());

        return customView;

    }

    @Override
    public boolean areAllItemsEnabled(){
        return true;
    }

    public boolean isEnabled(int arg0){
        return true;
    }
}
