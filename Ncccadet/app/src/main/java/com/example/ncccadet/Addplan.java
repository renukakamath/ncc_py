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

public class Addplan extends AppCompatActivity implements JsonResponse{
    EditText e1,e2,e3;
    Button b1;
    ListView l1;
    SharedPreferences sh;
    String plan,details,planfor;
    String [] pl,de,plfor,value;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplan);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText) findViewById(R.id.plan);
        e2=(EditText) findViewById(R.id.details);
        e3=(EditText) findViewById(R.id.planfor);
        b1=(Button) findViewById(R.id.planbutton);
        l1=(ListView) findViewById(R.id.list);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Addplan.this;
        String q = "/Viewplan" ;
        q = q.replace(" ", "%20");
        JR.execute(q);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plan=e1.getText().toString();
                details=e2.getText().toString();
                planfor=e3.getText().toString();

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Addplan.this;
                String q = "/Addplan?plan=" + plan + "&details=" + details + "&planfor=" + planfor ;
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Addplan")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Addplan.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Viewplan"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    pl=new String[ja1.length()];
                    de=new String[ja1.length()];
                    plfor=new String[ja1.length()];

                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        pl[i]=ja1.getJSONObject(i).getString("plan");
                        de[i]=ja1.getJSONObject(i).getString("details");
                        plfor[i]=ja1.getJSONObject(i).getString("planed_for");


                        value[i]="plan: "+pl[i]+"\ndetails: "+de[i]+"\nplaned for: "+plfor[i] ;

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