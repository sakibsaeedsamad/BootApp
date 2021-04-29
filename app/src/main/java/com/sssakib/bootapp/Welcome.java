package com.sssakib.bootapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Welcome extends AppCompatActivity implements View.OnClickListener {
    TextView nameTV,mobileTV,emailTV,addressTV;
    ImageView imageView;
    Button clickForUpdateBTN;
    String name;
    String mobile;
    String email;
    String address;
    String  img;
    private long id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        mobile = intent.getExtras().getString("mobile");
        email = intent.getExtras().getString("email");
        address = intent.getExtras().getString("address");

        img = intent.getExtras().getString("img");
        id= intent.getExtras().getLong("id");

        nameTV = findViewById(R.id.nameTV);
        mobileTV = findViewById(R.id.mobileTV);
        emailTV = findViewById(R.id.emailTV);
        addressTV = findViewById(R.id.addressTV);
        clickForUpdateBTN= findViewById(R.id.clickForUpdateBtn);
        imageView= findViewById(R.id.imageView);




        nameTV.setText(name);
        mobileTV.setText(mobile);
        emailTV.setText(email);
        addressTV.setText(address);
        imageView.setImageBitmap(convertStringToBitmap(img));

        clickForUpdateBTN.setOnClickListener(this);






    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.clickForUpdateBtn){
            Intent i = new Intent(Welcome.this,UpdateActivity.class);
            i.putExtra("id",id);
            Welcome.this.startActivity(i);
        }

    }
    public static Bitmap convertStringToBitmap(String string) {
        byte[] byteArray = Base64.decode(string, Base64.DEFAULT);
        Bitmap decodeBmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return decodeBmp;
    }

}