package com.example.ncccadet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Addstudents extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String fname,lname,place,phone,email;
    String [] first_name,last_name,pla,pho,mail,value,student_id;
    ListView l1;

    public static  String fn,ln,pl,ph,em,sid;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudents);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.place);
        e4=(EditText)findViewById(R.id.phone);
        e5=(EditText) findViewById(R.id.email);
        b1=(Button) findViewById(R.id.student);
        l1=(ListView) findViewById(R.id.list);

        l1.setOnItemClickListener(this);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Addstudents.this;
        String q = "/ViewStudent?login_id="+ sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);




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
                    JR.json_response = (JsonResponse) Addstudents.this;
                    String q = "/Addstudents?first_name=" + fname + "&last_name=" + lname + "&place=" + place + "&phone=" + phone + "&email=" + email + "&login_id=" + sh.getString("log_id", "");
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }

            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Addstudent")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Addstudents.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("ViewStudent"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    first_name=new String[ja1.length()];
                    last_name=new String[ja1.length()];
                    pla=new String[ja1.length()];
                    pho=new String[ja1.length()];
                    mail=new String[ja1.length()];
                    value=new String[ja1.length()];
                    student_id=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        first_name[i]=ja1.getJSONObject(i).getString("first_name");
                        last_name[i]=ja1.getJSONObject(i).getString("last_name");
                        pla[i]=ja1.getJSONObject(i).getString("place");
                        pho[i]=ja1.getJSONObject(i).getString("phone");
                        mail[i]=ja1.getJSONObject(i).getString("email");
                        student_id[i]=ja1.getJSONObject(i).getString("student_id");

                        value[i]="first name: "+first_name[i]+"\nlast name: "+last_name[i]+"\nplace: "+pla[i] +"\nphone:"+pho[i] +"\nemail:"+mail[i];

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        fn=first_name[i];
        ln=last_name[i];
        pl=pla[i];
        ph=pho[i];
        em=mail[i];
        sid=student_id[i];
        final CharSequence[] items = {"Update","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Addstudents.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Update")) {


                    startActivity(new Intent(getApplicationContext(),Updatestudent.class));


                }



                else if (items[item].equals("Cancel")) {


                    dialog.dismiss();
                }
            }

        });
        builder.show();


    }
}