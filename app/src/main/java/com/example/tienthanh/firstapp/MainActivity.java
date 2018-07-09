package com.example.tienthanh.firstapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tienthanh.firstapp.Model.App;
import com.example.tienthanh.firstapp.adapter.AppAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AppAdapter.Listener {

    private ArrayList<App> apps;
    private ListView listView;
    private AppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new getApps().execute();
        listView = findViewById(R.id.list_view);
        adapter = new AppAdapter(this, 0, apps);
        adapter.setListener(this);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick( String id) {
        goToGooglePlay(this, id);
    }



    private class getApps extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            apps = new ArrayList<>();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String url = "http://ads.liforte.com/api/ads/v1/GetAllActiveForMoreApps";
            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {
                try {
                    JSONArray allApp = new JSONArray(jsonStr);
                    for  (int i = 0; i < allApp.length(); i++) {
                        JSONObject a = allApp.getJSONObject(i);
                        String id = a.getString("AID");
                        String name = a.getString("Name");
                        String link = a.getString("Link_To_App");
                        String iconLink = a.getString("Icon");
                        Bitmap icon;

                        try {
                            icon = drawableFromUrl(iconLink);
                        } catch (IOException e) {
                            icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
                        }
                        String pkg = a.getString("Package");
                        int numberCoin = a.getInt("NumberCoin");
                        String description = a.getString("Description");

                        App app = new App(id, name, link, icon, pkg, numberCoin, description);
                        apps.add(app);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.setApps(apps);
            adapter.notifyDataSetChanged();

        }
    }

    private Bitmap drawableFromUrl(String url) throws  java.io.IOException {

        HttpURLConnection connection = (HttpURLConnection)new URL(url) .openConnection();
        connection.setRequestProperty("User-agent","Mozilla/4.0");

        connection.connect();
        InputStream input = connection.getInputStream();

        return BitmapFactory.decodeStream(input);
    }


    public void goToGooglePlay(Context context, String id) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + id)));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + id)));
        }
    }
}

