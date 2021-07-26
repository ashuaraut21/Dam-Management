package com.example.dammanagementsystem;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class dam_manage extends AppCompatActivity {
    TextView dam_name,level;
    public static String[] dam1,dam2;
    Bundle b1;
    Timer t = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dam_manage);
        dam_name=(TextView)findViewById(R.id.dam_name) ;
        level=(TextView)findViewById(R.id.level);
        b1=getIntent().getExtras();
        dam_name.setText(b1.getString("name"));
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {
                    getJSON("http://mahavidyalay.in/AcademicDevelopment/IOT2020/DamManagement_show.php");
                }catch (Exception e){
                    //Toast.makeText(show_update_medical.this,""+e,Toast.LENGTH_LONG).show();
                }  }
        }, 200,5000);

    }
    //ADD FOLLOWING line in manifest android:usesCleartextTraffic="true"
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        dam1 = new String[jsonArray.length()];
        dam2 = new String[jsonArray.length()];



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if(b1.getString("name").contentEquals("DAM 1")){
                level.setText(obj.getString("dam1"));
            }else{
                level.setText(obj.getString("dam2"));
            }
            dam1[i] = obj.getString("dam1");
            dam2[i] = obj.getString("dam2");
        }


    }
}
