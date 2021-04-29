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
        String img= "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAKBueIx4ZKCMgoy0qqC+8P//8Nzc8P//////////////\n" +
                "////////////////////////////////////////////2wBDAaq0tPDS8P//////////////////\n" +
                "////////////////////////////////////////////////////////////wAARCAEEAMMDASIA\n" +
                "AhEBAxEB/8QAFwABAQEBAAAAAAAAAAAAAAAAAAECA//EAB8QAQACAgIDAQEAAAAAAAAAAAABEQIx\n" +
                "IUFRYYEyA//EABYBAQEBAAAAAAAAAAAAAAAAAAABAv/EABoRAQEBAQEBAQAAAAAAAAAAAAARARIh\n" +
                "AjH/2gAMAwEAAhEDEQA/AOYAIAAAAAAAAAAAAAAAAAAABC5XfKCgACiNY6RWSm6KKsYopuigjFFN\n" +
                "0UJGKKbooIwU1RQRmhqigjI1RQRkaooIyLMMiKIAoAAADWPbLWOpFxQVGkFAQUBC1+HwEuS1FEst\n" +
                "TgEsteDgEsXg4BmdMN5aYGdABBUAAAVrHtlrDci40AjQcABwcAAAoAAfQAAACgBMvzLm3npgZ0AE\n" +
                "AAAAVrDcstYbFaWvYUjRXsr2UUoV7AAKACigAooAEUBF6AGM9MOmenMZ39ABAAAAFaw/TLWP6gGx\n" +
                "Y2g2AAAABQBZfsoAufIAAAAHQMZ9Mt59MKzqKCIgAAAK1j+oZax3Cja3PkEbLnyXPkALAAA+AB8P\n" +
                "gAfAAAAAHPPbLee2VY1CQlBBUAABqFjaKo6HYqNIKAgoCCgIKAgoCCgIL8QGMtstZbRWUJAEAQQA\n" +
                "GlRpRtUjUKi7swAWMd6AEO9ACHegBDvQAh3oANfO0RURpidos7RWRFQEFQEAQbVFUbjUKkaUN9wA\n" +
                "Wsc6AFOdAA50ASnOgBTnQAa+cgiiNsTtFRWERpBUJiuxctar7YMAINqiqNY6aZx0s8QiqM3K2sKo\n" +
                "llkKolyckKonJyQqiXJckKozclyQrSESSDJwd8mVXFDKIqCnaZrG0yBkBBtUhYUaxWdJioqVJTQU\n" +
                "SilCiVJUqFEqfJU+VCiUUoUSkpQoRFE6EnQIh2DIgCkbZyahnIEAQdMdhAo1irMbaDEtbSpKlfBb\n" +
                "LSijwWy5KKlAuS5OSgSyyigLLkopQiSdGiUVkARAAIZnbTE7AAQdI2vbKztRY20zG2gwARQAAAAA\n" +
                "AABAUCRJAvhCgQRUAYnbp05gAINqi2osNMNAoCKAAAAAAAigAAkqkiCKAgAGWnN0z05gAINqgopY\n" +
                "AogCgAAAAUAAACggAApwCAAznphrPplAABsBQUAAAAAAAUAAAAAAAAAAgAY/ptkEAAH/2Q==\n";
        if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill up all values", Toast.LENGTH_LONG).show();
        } else {
            Call<ResponseModel> call = RetrofitClient
                    .getInstance()
                    .getAPI()
                    .createUser(new User(name, mobile, email, address, password,img));
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

