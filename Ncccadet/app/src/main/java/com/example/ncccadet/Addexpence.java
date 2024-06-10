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

public class Addexpence extends AppCompatActivity implements JsonResponse {
    EditText e1,e2;
    Button b1;
    ListView l1;
    String expence,details;
    String[] exp,det,date,value;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpence);
        e1=(EditText) findViewById(R.id.expence);
        e2=(EditText) findViewById(R.id.details);
        b1=(Button) findViewById(R.id.expbutton);
        l1=(ListView) findViewById(R.id.list);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expence=e1.getText().toString();
                details=e2.getText().toString();

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Addexpence.this;
                String q = "/Addexpence?login_id="+ sh.getString("log_id", "") +"&expence=" +expence +"&details=" +details;
                q = q.replace(" ", "%20");
                JR.execute(q);

            }
        });


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Addexpence.this;
        String q = "/Viewexpence?login_id="+ sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Addexpence")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Addexpence.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Viewexpence"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    exp=new String[ja1.length()];
                    det=new String[ja1.length()];
                    date=new String[ja1.length()];

                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        exp[i]=ja1.getJSONObject(i).getString("title");
                        det[i]=ja1.getJSONObject(i).getString("details");
                        date[i]=ja1.getJSONObject(i).getString("date");




                        value[i]="expence: "+exp[i]+"\ndetails: "+det[i]+"\ndate: "+date[i];

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