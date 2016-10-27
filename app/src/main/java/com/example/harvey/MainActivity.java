package com.example.harvey;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<CrashName> cashNameList = new ArrayList<>();
    private ListView Names;
    private JSONTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Names = (ListView)findViewById(R.id.listView);
    }

    public static class JSONTask extends AsyncTask<String, String, List<CrashName>> {
        private MainActivity activity;
        public JSONTask(MainActivity act) {
            activity = act;
        }

        @Override
        protected List<CrashName> doInBackground(String... params){
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                String basicAuth = "Bearer " + new String("5a839540a09f12373e52c7c82680318e");
                connection.setRequestProperty("Authorization", basicAuth);
                connection.setRequestProperty("Request Method", "POST");
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONArray jsonarray = new JSONArray(finalJson);

                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    String name = jsonobject.getString("name");
                    CrashName crashModel= new CrashName();
                    crashModel.setName(name);
                    if(activity != null) {
                        activity.cashNameList.add(crashModel);
                    }
                }

                if(activity != null) {
                    return activity.cashNameList;
                } else {
                    return null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch(JSONException e){
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<CrashName> result){
            super.onPostExecute(result);
            if(!isCancelled() && activity != null) {
                MyAdapter adapter = new MyAdapter(activity, R.layout.item, result);
                activity.Names.setAdapter(adapter);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_refresh) {
            task = new JSONTask(this);
            task.execute("https://developers.crittercism.com/v1" +
                    ".0/app/519d53101386202089000007/crash/summaries");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(task != null) {
            task.cancel(true);
        }
    }
}
