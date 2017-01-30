package fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.rokiel.james.rokieldashboard.R;
import com.rokiel.james.rokieldashboard.activity.Restaurant;
import com.rokiel.james.rokieldashboard.activity.RestaurantAdapter;
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

public class Restaurant_List extends ListFragment{

    ArrayList<Restaurant> restaurantList = new ArrayList<>();
    private ListView resList;
    private RestaurantAdapter lAdapter;


    public Restaurant_List() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.fragment_restaurant__list, container, false);
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

                    Restaurant rest = new Restaurant(Integer.parseInt(jID),jResturantName, jAddress, jType,jPrice, jSpinnerGroup);
                    restaurantList.add(rest);
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
            resList = (ListView) lView.findViewById(R.id.restaurant_list__list_view);
            lAdapter = new RestaurantAdapter(lView.getContext(), R.layout.fragment_restaurant__list, restaurantList);
            resList.setAdapter(lAdapter);
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }


    }
}
