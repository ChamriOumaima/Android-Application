package com.example.wildtechnologie.gpfemergency;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class listedevloper extends AppCompatActivity {
    static Dialog myDialog;
    Adapter adapter;
    static ArrayList<Pharmacie> list;
    RecyclerView recyclerView;
    static Pharmacie p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listedevloper);
        Intent intent=getIntent();
        myDialog=new Dialog(this);
        ArrayList<Pharmacie> resultat;
        resultat = intent.getParcelableArrayListExtra("list");
        list=resultat;
        int n=resultat.size();
        int i=0;

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        recyclerView=findViewById(R.id.rv_list);
        List<item> itemList=new ArrayList<>();
        while (i<n) {
            Pharmacie p= resultat.get(i);
            itemList.add(new item(p.img,p.nom, R.drawable.second,p.ville+"-"+p.quartier));
            i++;
        }
        adapter=new Adapter(this,itemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public static void ShowPopup(int i) {

        TextView nom,tel,rue,vc,garde;
        TextView txtclose;
        p = list.get(i);
        myDialog.setContentView(R.layout.activity_popup_pharmacie);
        txtclose =myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        garde=myDialog.findViewById(R.id.garde);
        if(p.garde.equals("True")){
            garde.setText("La pharmacie est en garde");
        }else {
            garde.setText("La pharmacie n'est pas en garde");
            //garde.setTextColor();
        }
        vc=myDialog.findViewById(R.id.VC);
        vc.setText(p.ville+" - "+p.quartier);
        nom = myDialog.findViewById(R.id.popupPharmacie);
        nom.setText("Pharmacie " +p.nom);
        tel=myDialog.findViewById(R.id.tel);
        tel.setText("Téléphone : "+p.tel);
        rue=myDialog.findViewById(R.id.rue);
        rue.setText("Rue : "+p.rue);


        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public  void toMap(View v){
        Intent intent = new Intent(getApplicationContext(),MainActiv.class);
        Bundle myDataBundle = new Bundle();
                // add <key,value> data items to the container
                myDataBundle.putString("str",p.nom);

                // attach the container to the intent
                intent.putExtras(myDataBundle);
        startActivity(intent);
    }


}


