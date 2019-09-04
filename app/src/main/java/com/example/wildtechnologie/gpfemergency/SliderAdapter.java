package com.example.wildtechnologie.gpfemergency;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //Arrays
    public int[] slide_images = {
            R.drawable.firstb,
            R.drawable.second,
            R.drawable.third

    };

    public String[] slide_headings = {
            "Permanence particulière",
            "Permanence environnante",
            "Permanence distante"
    };

    public String[] slide_descs = {
            "Chercher votre pharmacie de garde",
            "Consulter les pharmacies de garde qui \nse trouve dans votre quartier",
            "Chercher des pharmacies à une distance choisie "
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject( View view,  Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position){
        layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container, false);


        ImageView slideImageView = (ImageView) view.findViewById(R.id.gpf);
        TextView slideHeading = (TextView) view.findViewById(R.id.direct);
        TextView slideDescription = (TextView) view.findViewById(R.id.commentaire);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (position==0) context.startActivity(new Intent(context, test.class));
               if (position==1) context.startActivity(new Intent(context, Devlopper.class));
               if (position==2) context.startActivity(new Intent(context, passage.class));
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }

}
