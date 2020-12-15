package com.netflix.app.utlis;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class GlobalConstants {


    public static final DatabaseReference UsersInstance = FirebaseDatabase.getInstance().getReference("Android/UserDetails");
}

