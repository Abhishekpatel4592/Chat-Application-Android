package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText mFirstName, mLastName, mPhone, mEmail, mPassword, mCity, mState, mCountry;
    Button mRegister;
    TextView mLoginText;
    FirebaseAuth mAuth;
    User mUser;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mFirstName = (EditText) findViewById(R.id.firstNameEditText);
        mLastName = (EditText) findViewById(R.id.lastNameEditText);
        mPhone = (EditText) findViewById(R.id.phoneEditText);
        mEmail = (EditText) findViewById(R.id.emailRegisterEditText);
        mPassword = (EditText) findViewById(R.id.passwordRegisterEditText);
        mCity = (EditText) findViewById(R.id.cityEditText);
        mState = (EditText) findViewById(R.id.stateEditText);
        mCountry = (EditText) findViewById(R.id.countryEditText);
        mRegister = (Button) findViewById(R.id.registerButton);
        mLoginText = (TextView) findViewById(R.id.loginTextView);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                final String fname = mFirstName.getText().toString();
                final String lname = mLastName.getText().toString();
                final String phone = mPhone.getText().toString();
                final String cityname = mCity.getText().toString();
                final String statename = mState.getText().toString();
                final String countryname = mCountry.getText().toString();


                if(TextUtils.isEmpty(fname)){
                    mFirstName.setError("First Name Required");
                }

                if(TextUtils.isEmpty(lname)){
                    mLastName.setError("Last Name Required");
                }

                if(TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone Number Required");
                }

                if(phone.length() < 10){
                    mPhone.setError("Please Enter Valid Mobile Number");
                }

                if(TextUtils.isEmpty(cityname)){
                    mCity.setError("City Name Required");
                }

                if(TextUtils.isEmpty(statename)){
                    mState.setError("State Name Required");
                }

                if(TextUtils.isEmpty(countryname)){
                    mCountry.setError("Country Name Required");
                }

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email Field Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Empty");
                    return;
                }
                if(password.length() >= 8){
                    mPassword.setError("Password should be less then 8 Charecter");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.e("SUCCESSFULL","USER ADDED");
                            Map<String,Object> user = new HashMap<String, Object>();
                            user.put("First Name",fname);
                            user.put("Last Name",lname);
                            user.put("Cell Phone",phone);
                            user.put("Email",email);
                            user.put("City",cityname);
                            user.put("State",statename);
                            user.put("Country",countryname);
                            mDatabase.child("Users").push().setValue(user);
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Log.e("UNSUCCESSFULL", "Some Error");
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void setValue(User user){
        mUser = user;

    }

}
