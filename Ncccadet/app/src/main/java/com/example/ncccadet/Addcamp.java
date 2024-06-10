package com.example.ncccadet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class Addcamp extends AppCompatActivity implements JsonResponse, View.OnClickListener {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    ListView l1;
    SharedPreferences sh;
    String camp,details,place,fdate,todate;
    String[] cam,det,pla,fd,td,value;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcamp);
       e1=(EditText)findViewById(R.id.camp);
       l1=(ListView)findViewById(R.id.list);
       sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e2=(EditText) findViewById(R.id.details);
        e3=(EditText) findViewById(R.id.place);
        e4=(EditText) findViewById(R.id.fdate);

        e5=(EditText) findViewById(R.id.tdate);
        e5.setOnClickListener(this);
        b1=(Button) findViewById(R.id.campbutton);

        final Calendar calendar=Calendar.getInstance() ;
        final int year = calendar.get(calendar.YEAR);
        final int month =calendar.get(calendar.MONTH);
        final  int day =calendar.get(calendar.DAY_OF_MONTH);


        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(Addcamp.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        e4.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
                dialog.show();
            }
        });

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Addcamp.this;
        String q = "/Viewcamp?login_id="+ sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camp=e1.getText().toString();
                details=e2.getText().toString();
                place=e3.getText().toString();
                fdate=e4.getText().toString();
                todate=e5.getText().toString();


                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Addcamp.this;
                String q = "/AddCamp?camp=" + camp + "&details=" + details + "&fromdate=" + fdate + "&todate=" + todate +"&place=" +place +"&login_id=" + sh.getString("log_id", "") ;
                q = q.replace(" ", "%20");
                JR.execute(q);

            }
        });


    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("AddCamp")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Addcamp.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Viewcamp"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    cam=new String[ja1.length()];
                    det=new String[ja1.length()];
                    pla=new String[ja1.length()];
                    fd=new String[ja1.length()];
                    td=new String[ja1.length()];
                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        cam[i]=ja1.getJSONObject(i).getString("camp");
                        det[i]=ja1.getJSONObject(i).getString("details");
                        pla[i]=ja1.getJSONObject(i).getString("place");
                        fd[i]=ja1.getJSONObject(i).getString("fromdate");
                        td[i]=ja1.getJSONObject(i).getString("todate");



                        value[i]="camp: "+cam[i]+"\ndetails: "+det[i]+"\nplace: "+pla[i]+"\nfrom date:"+fd[i] +"\nto date:"+td[i];

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
    public void onClick(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        e5.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }
}