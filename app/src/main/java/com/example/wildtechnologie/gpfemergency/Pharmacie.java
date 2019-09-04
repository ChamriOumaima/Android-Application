package com.example.wildtechnologie.gpfemergency;


import android.os.Parcel;
import android.os.Parcelable;


public class Pharmacie implements Parcelable {
    String nom ;
    String tel;
    String ville;
    String quartier;
    String rue;
    String garde;
    String img;

    public Pharmacie(String n,String t,String v,String q,String r,String g,String i){
        nom=n;
        tel=t;
        ville=v;
        quartier=q;
        rue=r;
        garde=g;
        img=i;
    }

    protected Pharmacie(Parcel in) {
        nom = in.readString();
        tel = in.readString();
        ville = in.readString();
        quartier = in.readString();
        rue = in.readString();
        garde=in.readString();
        img=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(tel);
        dest.writeString(ville);
        dest.writeString(quartier);
        dest.writeString(rue);
        dest.writeString(garde);
        dest.writeString(img);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pharmacie> CREATOR = new Creator<Pharmacie>() {
        @Override
        public Pharmacie createFromParcel(Parcel in) {
            return new Pharmacie(in);
        }

        @Override
        public Pharmacie[] newArray(int size) {
            return new Pharmacie[size];
        }
    };
}
