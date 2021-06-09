package com.sssakib.bootapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mobileLogin, passwordLogin;
    private Button loginButton;
    private TextView registerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileLogin = findViewById(R.id.mobileLogIn);
        passwordLogin = findViewById(R.id.passwordLogIn);

        loginButton = findViewById(R.id.logInButton);
        registerId = findViewById(R.id.registerId);

        loginButton.setOnClickListener(this);
        registerId.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String mobile = mobileLogin.getText().toString().trim();
        String password = passwordLogin.getText().toString().trim();



        if(v.getId()==R.id.logInButton){

            if(mobile.isEmpty() || password.isEmpty() ){
                Toast.makeText(getApplicationContext(),"Please give Mobile and Password",Toast.LENGTH_LONG).show();
            }
            else {
                Call<User> call = RetrofitClient
                        .getInstance()
                        .getAPI()
                        .checkUser( new User(mobile, password));
                call.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {



                        String name = response.body().getName();
                        String mob = response.body().getMobile();
                        String email = response.body().getEmail();
                        String address = response.body().getAddress();
                        String pass = response.body().getPassword();
                        Long id = response.body().getId();
                        String img=response.body().getImage();


                        if(mob.equals(mobile)&& pass.equals(password)){
                            Intent i = new Intent(MainActivity.this,Welcome.class);
                            i.putExtra("name",name);
                            i.putExtra("mobile",mob);
                            i.putExtra("email",email);
                            i.putExtra("address",address);
                            i.putExtra("id",id);
                            i.putExtra("img",img);
                            startActivity(i);
                            cleanFields();
                        }




                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });



            }
        }
        else if(v.getId()==R.id.registerId){
           startActivity(new Intent(getApplicationContext(),Registration.class));
        }


    }


    private void cleanFields(){
        mobileLogin.setText(null);
        passwordLogin.setText(null);
    }
}