package com.example.chatapplication;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    String uFirstName,uLastName;
    DatabaseReference mDatabase;

    User(){

    }

    User(String mFirstName, String mLastName){
        this.uFirstName = mFirstName;
        this.uLastName = mLastName;
    }

    public void setValue(){

        Log.e("USER FIRSTNAME",uFirstName);
        Log.e("USER LASTNAME",uLastName);

//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        User user = new User(uFirstName,uLastName);
//
//        mDatabase.child("users").setValue(user);

    }

}
