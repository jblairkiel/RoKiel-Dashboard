package fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.rokiel.james.rokieldashboard.R;
import custom_classes.RestaurantListItem;
import adapter.RestaurantListAdapter;
import com.rokiel.james.rokieldashboard.activity.RestaurantListItemActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 */

public class Restaurant_List_Fragment extends ListFragment{

    ArrayList<RestaurantListItem> restaurantListItemList = new ArrayList<>();
    ArrayList<RestaurantListItem> selectedRestaurantListItems = new ArrayList<>();
    private ListView resList;
    private RestaurantListAdapter lAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public Restaurant_List_Fragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        setHasOptionsMenu(true);
        View view  = inflater.inflate(R.layout.fragment_restaurant__list, container, false);
        //swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        //swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //@Override
            //public void onRefresh() {
                //new listRefresher().execute();
            //}
        //});
        new DownloadJSON(view).execute();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //do something
            return true;
        }
        if(id == R.id.trashCanRestaurantListActionBar)
        return super.onOptionsItemSelected(item);
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
        View lView;
        String result = "";

        private DownloadJSON(View view){
            lView = view;
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                String sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?func=" + URLEncoder.encode("getApprovedResturants","UTF-8");
                URL url = new URL(sURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //String get_Data = URLEncoder.encode("getApprovedResturants","UTF-8");
                //bufferedWriter.write(get_Data);
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
            Log.e("MyActivity","result: " + result);

            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject res = jArray.getJSONObject(i);
                    String jID = res.getString("ID");
                    String jResturantName = res.getString("ResturantName");
                    String jAddress = res.getString("Address");
                    String jType = res.getString("Type");
                    String jPrice = res.getString("Price");
                    String jSpinnerGroup = res.getString("SpinnerGroup");

                    RestaurantListItem rest = new RestaurantListItem(Integer.parseInt(jID),jResturantName, jAddress, jType,jPrice, jSpinnerGroup);
                    restaurantListItemList.add(rest);
                }
            } catch (JSONException e) {
                Log.e("JSON Parse","Result: " + result + "|Error parsing data: " + e.toString());
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
            resList = (ListView) lView.findViewById(R.id.restaurant_list_list_view);
            lAdapter = new RestaurantListAdapter(lView.getContext(), R.layout.fragment_restaurant__list, restaurantListItemList);
            resList.setAdapter(lAdapter);

            resList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    RestaurantListItem selectedItem = restaurantListItemList.get(position);
                    if(selectedRestaurantListItems.contains(selectedItem)) {
                        resList.setItemChecked(position, false);
                        selectedRestaurantListItems.remove(selectedItem);
                    }
                    else {
                        resList.setItemChecked(position, true);
                        selectedRestaurantListItems.add(selectedItem);
                    }

                    if(selectedRestaurantListItems.isEmpty()){
                        getActivity().getActionBar().
                    }
                    return false;
                }
            });
            resList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                    selectRestaurantItem(position);
                }
                public void selectRestaurantItem(int position){
                    //RestaurantListItem item = (RestaurantListItem) restaurantListItemList.get(position);
                    getListView().setItemChecked(position, true);
                    RestaurantListItem rLI = restaurantListItemList.get(position);

                    Intent intent = new Intent(getActivity(), RestaurantListItemActivity.class);
                    Bundle b = new Bundle();

                    String lID = Integer.toString(rLI.getID());
                    String lName = rLI.getRestaurantName();
                    String lAddress = rLI.getAddress();
                    String lType = rLI.getType();
                    String lPrice = rLI.getPrice();
                    String lGroup = rLI.getSpinnerGroup();

                    b.putString("id", lID);
                    b.putString("name", lName);
                    b.putString("address", lAddress);
                    b.putString("type", lType);
                    b.putString("price", lPrice);
                    b.putString("group", lGroup);

                    intent.putExtras(b);
                    startActivity(intent);

                    //rLI.loadRestaurantListItem(resList, item);
                }

            });
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

    }

    private class listRefresher extends AsyncTask<Void, Void, Void> {
        //View lView;
        String result = "";

        private listRefresher(){
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                String sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?func=" + URLEncoder.encode("getApprovedResturants","UTF-8");
                URL url = new URL(sURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //String get_Data = URLEncoder.encode("getApprovedResturants","UTF-8");
                //bufferedWriter.write(get_Data);
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
            Log.e("MyActivity","result: " + result);

            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject res = jArray.getJSONObject(i);
                    String jID = res.getString("ID");
                    String jResturantName = res.getString("ResturantName");
                    String jAddress = res.getString("Address");
                    String jType = res.getString("Type");
                    String jPrice = res.getString("Price");
                    String jSpinnerGroup = res.getString("SpinnerGroup");

                    RestaurantListItem rest = new RestaurantListItem(Integer.parseInt(jID),jResturantName, jAddress, jType,jPrice, jSpinnerGroup);
                    restaurantListItemList.add(rest);
                }
            } catch (JSONException e) {
                Log.e("JSON Parse","Result: " + result + "|Error parsing data: " + e.toString());
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
           lAdapter.udpateAdapter(restaurantListItemList);
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

    }


}
