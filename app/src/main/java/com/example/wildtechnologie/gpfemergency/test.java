package com.example.wildtechnologie.gpfemergency;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class test extends AppCompatActivity {
    private static AutoCompleteTextView edit;
    static Dialog myDialog;
    String destination;
    static String jsonrep="";
    ArrayList<String> names;
    String tab="";
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        myDialog = new Dialog(this);
        names=new ArrayList<>();
        new Operation().execute();
        edit = findViewById(R.id.text);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
        edit.setAdapter(adapter);

        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplicationContext().startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    public void toMap(View v) {
        /*Intent intent = new Intent(getApplicationContext(), MainActiv.class);
        Bundle myDataBundle = new Bundle();
        myDataBundle.putString("tab",destination);
        intent.putExtras(myDataBundle);
        startActivity(intent);*/
        Intent intent = new Intent(getApplicationContext(),MainActiv.class);
        Bundle myDataBundle = new Bundle();
        // add <key,value> data items to the container
        myDataBundle.putString("str",tab);
        // attach the container to the intent
        intent.putExtras(myDataBundle);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class TroisiemeOperation extends AsyncTask<String, Void, Void> {
        String nomp="",villep="",ruep="",quartierp="",telp="",gardep="",longitude="",latitude="";
        String jsonrep1="";
        @Override
        protected Void doInBackground(String... urls) {
            //*************************************************************************************************************
            try {
                URL url = new URL("https://ma-pharmacie.000webhostapp.com/test.php?ph="+destination);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while (line != null) {
                    line = bufferedReader.readLine();
                    jsonrep1 += line;
                }

                JSONArray ja = new JSONArray(jsonrep1);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    nomp= (String) jo.get("nom");
                    telp= (String) jo.get("tel");
                    villep= (String) jo.get("ville");
                    quartierp= (String) jo.get("quartier");
                    ruep= (String) jo.get("rue");
                    gardep= (String) jo.get("garde");
                    longitude= (String) jo.get("longitude");
                    latitude= (String) jo.get("latitude");
                }

            } catch (Exception e) {
                Log.e("RESPONSE Error", e.getMessage());
            }
            return null;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        protected void onPostExecute(Void unused) {
            TextView nom, tel, rue,ville,garde;
            TextView txtclose;
            //tab.add(longitude);
            //tab.add(latitude);
            tab=nomp;
            myDialog.setContentView(R.layout.activity_popup_pharmacie);
            txtclose =myDialog.findViewById(R.id.txtclose);
            txtclose.setText("X");
            ville = myDialog.findViewById(R.id.VC);
            ville.setText(villep+" - " +quartierp);
            nom = myDialog.findViewById(R.id.popupPharmacie);
            nom.setText("Pharmacie :" +nomp);
            tel=myDialog.findViewById(R.id.tel);
            tel.setText("Téléphone : "+telp);
            rue=myDialog.findViewById(R.id.rue);
            rue.setText("Rue : "+ruep);
            garde= myDialog.findViewById(R.id.garde);
            if(gardep.equals("True")){
                garde.setText("La pharmacie est en garde");
            }else {
                garde.setText("La pharmacie n'est pas en garde");
                //garde.setTextColor();
            }
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        }

    }


    @SuppressLint("StaticFieldLeak")
    public class Operation extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            //*************************************************************************************************************
            try {
                URL url = new URL("https://ma-pharmacie.000webhostapp.com/nom.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while (line != null) {
                    line = bufferedReader.readLine();
                    jsonrep += line;
                }

                JSONArray ja = new JSONArray(jsonrep);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    names.add((String) jo.get("nom"));
                }

            } catch (Exception e) {
                Log.e("RESPONSE Error", e.getMessage());
            }
            return null;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        protected void onPostExecute(Void unused) {
            CardView b=findViewById(R.id.AAA);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    destination = String.valueOf(edit.getText());
                    int s=0;
                    boolean trouve=false;
                    if(names.contains(destination))
                        trouve=true;


                    if(trouve==false){
                        edit.setTextColor(Color.RED);
                        Toast.makeText(getApplicationContext(),"Pharmacie introuvable", Toast.LENGTH_LONG).show();

                    }
                    else {
                        edit.setTextColor(Color.GRAY);
                        new TroisiemeOperation().execute();
                    }

                }
            });
        }

    }

}

