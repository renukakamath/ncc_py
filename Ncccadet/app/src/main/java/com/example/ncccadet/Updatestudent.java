package com.example.ncccadet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

public class Updatestudent extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String fname,lname,place,phone,email;
    String [] first_name,last_name,pla,pho,mail,value;
    ListView l1;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatestudent);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.place);
        e4=(EditText)findViewById(R.id.phone);
        e5=(EditText) findViewById(R.id.email);
        b1=(Button) findViewById(R.id.student);
        l1=(ListView) findViewById(R.id.list);


        e1.setText(Addstudents.fn);
        e2.setText(Addstudents.ln);
        e3.setText(Addstudents.pl);
        e4.setText(Addstudents.ph);
        e5.setText(Addstudents.em);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                phone=e4.getText().toString();
                email=e5.getText().toString();

                if(fname.equalsIgnoreCase("")|| !fname.matches("[a-zA-Z ]+"))
                {
                    e1.setError("Enter your firstname");
                    e1.setFocusable(true);
                }
                else if(lname.equalsIgnoreCase("")|| !lname.matches("[a-zA-Z ]+"))
                {
                    e2.setError("Enter your lastname");
                    e2.setFocusable(true);
                }

                else if(place.equalsIgnoreCase("")|| !place.matches("[a-zA-Z ]+"))
                {
                    e3.setError("Enter your place");
                    e3.setFocusable(true);
                }

                else if(phone.equalsIgnoreCase("")|| phone.length()!=10)
                {
                    e4.setError("Enter your phone");
                    e4.setFocusable(true);
                }

                else {


                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Updatestudent.this;
                    String q = "/updatestudents?first_name=" + fname + "&last_name=" + lname + "&place=" + place + "&phone=" + phone + "&email=" + email + "&login_id=" + sh.getString("log_id", "")+"&sid="+Addstudents.sid;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }

            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), " SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Userhome.class));

            }

            else if (status.equalsIgnoreCase("already")) {
                Toast.makeText(getApplicationContext(), "NO AMOUNT IN WALLET", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Userhome.class));

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}