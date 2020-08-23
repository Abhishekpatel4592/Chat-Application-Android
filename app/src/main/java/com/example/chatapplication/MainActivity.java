package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.StringNode;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView mName,mPhone,mEmail;
    FirebaseAuth fAuth;
    private DatabaseReference fDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = findViewById(R.id.yourNameTextView);
        mPhone = findViewById(R.id.phoneTextView);
        mEmail = findViewById(R.id.emailTextView);
        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance().getReference();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("Users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Setting data using userclass.
//                User user = dataSnapshot.getValue(User.class);
//                mName.setText(user.getFirstName() + " " + user.getLastName());
//                mPhone.setText(user.getPhone());


                //Setting data using DataSnapshot
                HashMap<String, String> dataMap = (HashMap<String, String>) dataSnapshot.getValue();
                mName.setText(dataMap.get("firstName"));
                mPhone.setText(dataMap.get("phone"));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Button mLogout = (Button) findViewById(R.id.logoutButton);

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
