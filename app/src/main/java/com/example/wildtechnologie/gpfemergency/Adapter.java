package com.example.wildtechnologie.gpfemergency;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context mcontext;
    List<item> mdata;
    Bitmap bitmap;

    public Adapter(Context c, List<item> l) {
        mcontext = c;
        mdata = l;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View v = inflater.inflate(R.layout.card_item, parent, false);
        return new myViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.profile_photo.setImageResource(mdata.get(position).getProfilephoto());
        holder.tv_title.setText(mdata.get(position).getProfilename());
        holder.tv_villequartier.setText(mdata.get(position).getProfileville());
        holder.bind(mdata.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.getAdapterPosition();
                listedevloper.ShowPopup(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_photo, background_img;
        TextView tv_title, tv_villequartier;
        Button b;


        public myViewHolder(View itemview) {
            super(itemview);
            profile_photo = itemview.findViewById(R.id.profile_image);
            background_img = itemview.findViewById(R.id.card_background);
            tv_title = itemview.findViewById(R.id.card_title);
            tv_villequartier = itemview.findViewById(R.id.card_villequartier);


        }

        public void bind(item i){
            Picasso.with(background_img.getContext()).load(i.getBackground()).centerCrop().fit().into(background_img);
        }

    }

}



