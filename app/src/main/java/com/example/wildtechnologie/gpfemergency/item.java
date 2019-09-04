package com.example.wildtechnologie.gpfemergency;



public class item {

    String background;
    String profilename;
    int profilephoto;
    String profileville;


    public item(String b, String p, int ph, String pv){
        background=b;
        profilename=p;
        profilephoto=ph;
        profileville=pv;

    }

    public String getBackground(){
        return background;
    }

    public int getProfilephoto() {
        return profilephoto;
    }

    public String getProfilename() {
        return profilename;
    }

    public String getProfileville() {
        return profileville;
    }


}

