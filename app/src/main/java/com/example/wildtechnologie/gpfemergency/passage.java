package com.example.wildtechnologie.gpfemergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class passage extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    private TextView textViewUsername;
    private CardView button1,button2;
    ImageView home;
    Integer dist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage);
        openDialog();

        textViewUsername = findViewById(R.id.textview_username);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                Bundle myDataBundle = new Bundle();
                // add <key,value> data items to the container
                myDataBundle.putString("val", String.valueOf(dist));
                // attach the container to the intent
                intent.putExtras(myDataBundle);
                startActivity(intent);
            }
        });
        button2 =findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplicationContext().startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }


    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String username) {
        dist=Integer.parseInt(username);
        textViewUsername.setText("La distance choisie est :"+dist/1000+" km");

    }

}