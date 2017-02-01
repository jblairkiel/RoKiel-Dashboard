package fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.rokiel.james.rokieldashboard.R;
import com.rokiel.james.rokieldashboard.activity.RestaurantListItemActivity;
import com.rokiel.james.rokieldashboard.activity.RestaurantSubmissionListItemActivity;

import custom_classes.RestaurantSubmissionListItem;
import adapter.RestaurantSubmissionsListAdapter;
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

public class Restaurant_Submissions_Fragment extends Fragment{

    ArrayList<RestaurantSubmissionListItem> restaurantSubmissionsListListItem = new ArrayList<>();
    private ListView resList;
    private RestaurantSubmissionsListAdapter lAdapter;


    public Restaurant_Submissions_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_restaurant__submissions, container, false);
        new DownloadJSON(view).execute();
        return view;
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
                String sURL = "http://www.jblairkiel.com/JSProjects/ajaxCalls.php?func=" + URLEncoder.encode("getResturantSubmissions","UTF-8");
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
                    String jLocation = res.getString("Location");
                    String jStatus = res.getString("Status");

                    RestaurantSubmissionListItem rest = new RestaurantSubmissionListItem(Integer.parseInt(jID),jResturantName, jLocation, jStatus);
                    restaurantSubmissionsListListItem.add(rest);
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
            resList = (ListView) lView.findViewById(R.id.restaurant_submissions_listview);
            lAdapter = new RestaurantSubmissionsListAdapter(lView.getContext(), R.layout.fragment_restaurant__submissions, restaurantSubmissionsListListItem);
            resList.setAdapter(lAdapter);
            resList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                    selectRestaurantItem(position);
                }
                public void selectRestaurantItem(int position){
                    //RestaurantListItem item = (RestaurantListItem) restaurantListItemList.get(position);
                    resList.setItemChecked(position, true);
                    RestaurantSubmissionListItem rLI = restaurantSubmissionsListListItem.get(position);

                    Intent intent = new Intent(getActivity(), RestaurantSubmissionListItemActivity.class);
                    Bundle b = new Bundle();

                    String lID = Integer.toString(rLI.getID());
                    String lName = rLI.getRestaurantName();
                    String lLocation = rLI.getLocation();
                    String lStatus = rLI.getStatus();

                    b.putString("id", lID);
                    b.putString("name", lName);
                    b.putString("location", lLocation);
                    b.putString("status", lStatus);

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
}
