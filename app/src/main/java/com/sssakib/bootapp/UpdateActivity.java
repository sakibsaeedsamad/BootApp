package com.sssakib.bootapp;

import androidx.appcompat.app.AppCompatActivity;


import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.*;


import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateActivity extends AppCompatActivity {

    private long id;
    private EditText emailUpdateET, addressUpdateET, passwordUpdateET;
    private Button updateButton, captureImageBTN;
    private  ImageView proficePic;
    String imageResult;


    public  static final int RequestPermissionCode  = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        id = intent.getExtras().getLong("id");

        emailUpdateET = findViewById(R.id.emailUpdateET);
        addressUpdateET = findViewById(R.id.addressUpdateET);
        passwordUpdateET = findViewById(R.id.passwordUpdateET);

        updateButton = findViewById(R.id.updateButton);
        captureImageBTN = findViewById(R.id.captureImageBTN);
        proficePic = findViewById(R.id.profilePic);

        EnableRuntimePermission();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailUpdateET.getText().toString().trim();
                String address = addressUpdateET.getText().toString().trim();
                String password = passwordUpdateET.getText().toString().trim();
                String  image=imageResult;



                if (  email.isEmpty() || address.isEmpty() || password.isEmpty()||image.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Fill up all values", Toast.LENGTH_LONG).show();
                } else {
                    User u = new User(email, address, password,image);

                    Call<User> call = RetrofitClient
                            .getInstance()
                            .getAPI()
                            .updateUser(u, id);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            String name = response.body().getName();
                            String mob = response.body().getMobile();
                            String email = response.body().getEmail();
                            String address = response.body().getAddress();
                            String pass = response.body().getPassword();
                            String img=response.body().getImage();
                            Long id = response.body().getId();

                            Intent i = new Intent(UpdateActivity.this, Welcome.class);
                            i.putExtra("name", name);
                            i.putExtra("mobile", mob);
                            i.putExtra("email", email);
                            i.putExtra("address", address);
                            i.putExtra("id", id);
                            i.putExtra("img",img);

                            startActivity(i);


                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });



                }

            }
        });

        captureImageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {

            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            proficePic.setImageBitmap(captureImage);

            imageResult = convertBitmapToString(captureImage);
        }
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(UpdateActivity.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(UpdateActivity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(UpdateActivity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(UpdateActivity.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(UpdateActivity.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
    public static String convertBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 5, stream);
        byte[] byteArray = stream.toByteArray();
        String result = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return result;
    }

}