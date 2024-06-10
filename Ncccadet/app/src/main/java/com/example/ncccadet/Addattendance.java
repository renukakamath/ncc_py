package com.example.ncccadet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Addattendance extends AppCompatActivity implements JsonResponse, AdapterView.OnItemSelectedListener {
    Spinner s1;
    RadioButton r1,r2;
    Button b1;
    ListView l1;
public static String sid;
    String attendance;
    String[] student_id,value,first_name,att,date,stu_id;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addattendance);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        s1=(Spinner) findViewById(R.id.spinner);
        r1=(RadioButton) findViewById(R.id.male);
        r2=(RadioButton) findViewById(R.id.female);
        b1=(Button) findViewById(R.id.button);
        l1=(ListView) findViewById(R.id.list);

        s1.setOnItemSelectedListener(this);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Addattendance.this;
        String q = "/viewproductspinner";
        q = q.replace(" ", "%20");
        JR.execute(q);


        JsonReq JR1 = new JsonReq();
        JR1.json_response = (JsonResponse) Addattendance.this;
        String q1 = "/Viewattendance?login_id=" + sh.getString("log_id", "");
        q1 = q1.replace(" ", "%20");
        JR1.execute(q1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (r1.isChecked()){
                   attendance="present";
               }else  if (r2.isChecked()){
                   attendance="absent";
               }

//                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!"+sid, Toast.LENGTH_LONG).show();
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Addattendance.this;
                String q = "/Addattendance?login_id=" + sh.getString("log_id", "") +"&attendance=" + attendance +"&sid=" +sid;
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });
    }




    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Addattendance")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Addattendance.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("viewproductspinner"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    stu_id=new String[ja1.length()];

                   first_name=new String[ja1.length()];
                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        stu_id[i]=ja1.getJSONObject(i).getString("student_id");
                        first_name[i]=ja1.getJSONObject(i).getString("first_name");





                        value[i]=first_name[i];


                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value);
                    s1.setAdapter(ar);
                }
            }
            else if(method.equalsIgnoreCase("Viewattendance"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    student_id=new String[ja1.length()];

                    first_name=new String[ja1.length()];
                    att=new String[ja1.length()];
                    date=new String[ja1.length()];
                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        student_id[i]=ja1.getJSONObject(i).getString("student_id");
                        first_name[i]=ja1.getJSONObject(i).getString("first_name");
                        att[i]=ja1.getJSONObject(i).getString("status");
                        date[i]=ja1.getJSONObject(i).getString("date");



                        value[i]="first name: "+first_name[i]+"\nattendance: "+att[i]+"\ndate: "+date[i];


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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sid=stu_id[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }
}