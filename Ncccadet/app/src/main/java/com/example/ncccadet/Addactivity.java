package com.example.ncccadet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Addactivity extends AppCompatActivity implements JsonResponse {
    EditText e1,e2;
    Button b1;
    ListView l1;
    SharedPreferences sh;
    String Activity,details;
    String[] act,det,date,value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);
        e1=(EditText)findViewById(R.id.Activity);
        l1=(ListView)findViewById(R.id.list);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e2=(EditText) findViewById(R.id.details);

        b1=(Button) findViewById(R.id.actbutton);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Addactivity.this;
        String q = "/Viewactivity?login_id="+ sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity=e1.getText().toString();
                details=e2.getText().toString();

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Addactivity.this;
                String q = "/Addactivity?login_id="+ sh.getString("log_id", "") +"&Activity=" +Activity +"&details=" +details;
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Addactivity")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Addactivity.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Viewactivity"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    act=new String[ja1.length()];
                    det=new String[ja1.length()];
                    date=new String[ja1.length()];

                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        act[i]=ja1.getJSONObject(i).getString("Activites");
                        det[i]=ja1.getJSONObject(i).getString("details");
                        date[i]=ja1.getJSONObject(i).getString("date");




                        value[i]="activity: "+act[i]+"\ndetails: "+det[i]+"\ndate: "+date[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value);
                    l1.setAdapter(ar);
                }
            }

        }

        catch (Exception e) {
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