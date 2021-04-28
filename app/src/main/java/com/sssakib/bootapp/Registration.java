package com.sssakib.bootapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity implements View.OnClickListener  {

    private EditText nameRegistrationET, mobileRegistrationET, passwordRegistrationET, addressRegistrationET, emailRegistrationET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameRegistrationET = findViewById(R.id.nameRegistrationET);
        mobileRegistrationET = findViewById(R.id.mobileRegistrationET);
        passwordRegistrationET = findViewById(R.id.passwordRegistrationET);
        addressRegistrationET = findViewById(R.id.addressRegistrationET);
        emailRegistrationET = findViewById(R.id.emailRegistrationET);


        Button registerButton = findViewById(R.id.registerButton);
        Button goBackButton = findViewById(R.id.goBackButton);
        registerButton.setOnClickListener(this);
        goBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.registerButton){
            doInsert();


        }
        else if(v.getId()==R.id.goBackButton){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

    public void doInsert() {
        String name = nameRegistrationET.getText().toString();
        String mobile = mobileRegistrationET.getText().toString().trim();
        String email = emailRegistrationET.getText().toString().trim();
        String password = passwordRegistrationET.getText().toString().trim();
        String address = addressRegistrationET.getText().toString().trim();
        if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill up all values", Toast.LENGTH_LONG).show();
        } else {
            Call<ResponseModel> call = RetrofitClient
                    .getInstance()
                    .getAPI()
                    .createUser(new User(name, mobile, email, address, password));
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String outCode = response.body().getOutCode();
                    String outMessage = response.body().getOutMessage();

                    if(outCode.equals("1") && outMessage.equals("USER ALREADY EXISTS")){

                        Toast.makeText(Registration.this, "User already Exists!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(Registration.this, "User Save Successfully", Toast.LENGTH_LONG).show();

                    }


                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {

                    Toast.makeText(Registration.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

        }
    }

    public void onBackPressed() {
        startActivity(new Intent(Registration.this,MainActivity.class));
    }
}

