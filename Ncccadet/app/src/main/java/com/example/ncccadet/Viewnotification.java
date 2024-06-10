package com.example.ncccadet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Viewnotification extends AppCompatActivity implements JsonResponse {
    ListView l1;
    SharedPreferences sh;
    String [] title,description,date,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotification);
        l1=(ListView) findViewById(R.id.list);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewnotification.this;
        String q = "/Viewnotification?login_id="+ sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                title = new String[ja1.length()];
                description = new String[ja1.length()];
                date = new String[ja1.length()];

                value = new String[ja1.length()];


                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    title[i] = ja1.getJSONObject(i).getString("title");

                    description[i] = ja1.getJSONObject(i).getString("description");
                    date[i] = ja1.getJSONObject(i).getString("date");




                    value[i] = "title:" + title[i] + "\ndescription: " + description[i] + "\n date: " + date[i] ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }
}