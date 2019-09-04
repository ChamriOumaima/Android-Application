package com.example.wildtechnologie.gpfemergency;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Devlopper extends AppCompatActivity {

    Spinner spn1,spn2;
    ArrayList<String> list,list2,list3,list4;
    String quartier,ville;
    ImageView home;
    Context context;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devlopper);
        list=new ArrayList<>();
        list2=new ArrayList<>();
        list3=new ArrayList<>();
        list4=new ArrayList<>();
        spn1=findViewById(R.id.spn1);
        spn2=findViewById(R.id.spn2);
        new LongOperation().execute("https://ma-pharmacie.000webhostapp.com/villes.php");

        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplicationContext().startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }


    public class LongOperation extends AsyncTask<String, Void, Void>{

        ArrayAdapter<String> adapter;
        String jsonResponse="";
        @Override
        protected Void doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while (line!=null){
                    line=bufferedReader.readLine();
                    jsonResponse+=line;
                }

                JSONArray ja= new JSONArray(jsonResponse);
                for(int i=0;i<ja.length();i++){
                    JSONObject jo= (JSONObject) ja.get(i);
                    list.add((String) jo.get("nom"));


                }
            } catch (Exception e) {
                Log.e("RESPONSE Error", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            adapter = new ArrayAdapter<String> (Devlopper.this,android.R.layout.simple_spinner_item,list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), list.get(position)+"", Toast.LENGTH_LONG).show();
                    ville=list.get(position);
                    new DeuxiemeOperation().execute("https://ma-pharmacie.000webhostapp.com/bdrest.php?ville="+ville);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            spn1.setAdapter(adapter);



        }

    }


    public class DeuxiemeOperation extends AsyncTask<String, Void, Void> {
        ArrayList<String> list2=new ArrayList<>();
        ArrayAdapter<String> adapter2;
        String jsonrep="";
        @Override
        protected Void doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while (line!=null){
                    line=bufferedReader.readLine();
                    jsonrep+=line;
                }

                JSONArray ja= new JSONArray(jsonrep);
                for(int i=0;i<ja.length();i++){
                    JSONObject jo= (JSONObject) ja.get(i);
                    list2.add((String) jo.get("nomr"));


                }



            } catch (Exception e) {
                Log.e("RESPONSE Error", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            adapter2 = new ArrayAdapter<String> (Devlopper.this,android.R.layout.simple_spinner_item,list2);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), list2.get(position)+"", Toast.LENGTH_LONG).show();
                    quartier=list2.get(position);
                    new TroisiemeOperation().execute("https://ma-pharmacie.000webhostapp.com/pharmacie.php?ville="+ville+"&cartier="+quartier);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            spn2.setAdapter(adapter2);

        }

    }

    public class TroisiemeOperation  extends AsyncTask<String,Void,Void>{
        String jsonrep="", result="";
        ArrayList<Pharmacie> listpharmacie=new ArrayList<>();
        CardView b=findViewById(R.id.buton);
        @Override
        protected Void doInBackground(String... urls) {
            //*************************************************************************************************************
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while (line!=null){
                    line=bufferedReader.readLine();
                    jsonrep+=line;
                }

                JSONArray ja= new JSONArray(jsonrep);
                for(int i=0;i<ja.length();i++){
                    JSONObject jo= (JSONObject) ja.get(i);
                    Pharmacie p=new Pharmacie((String) jo.get("nom"),(String)jo.get("tel"),(String)jo.get("ville"),(String)jo.get("quartier"),(String)jo.get("rue"),(String)jo.get("garde"),(String)jo.get("img"));
                    listpharmacie.add(p);


                }



            } catch (Exception e) {
                Log.e("RESPONSE Error", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),listedevloper.class);
                    intent.putExtra("list",listpharmacie);
                    startActivity(intent);
                }
            });


        }

    }



}






