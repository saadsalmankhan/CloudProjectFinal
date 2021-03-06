package com.l124320.cloudprojectfinal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.Manifest;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class addroom extends AppCompatActivity implements View.OnClickListener{

    EditText RoomName;
    EditText RoomPrice;
    EditText RoomDetails;
    Spinner RoomAvail;

    ImageView RoomPicAdd;

    Button UploadImage;
    Button addfinalroom;

    ProgressDialog mProgressDialog;
    private static int IMG_RESULT = 1;
    Intent intent;

    DatabaseReference addroomtofire;
    FirebaseDatabase mydata;

    private DatabaseReference mDatabase;
    public hotelroom myroom;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroom);

        RoomName=findViewById(R.id.editTextRoomName);
        RoomPrice=findViewById(R.id.editTextPrice);
        RoomDetails=findViewById(R.id.editTextDetail);
        RoomAvail=findViewById(R.id.spinnerAvailible);

        RoomPicAdd=findViewById(R.id.imageViewRoom);

        UploadImage=findViewById(R.id.buttonpicadd);
        addfinalroom=findViewById(R.id.buttonAdd);


        mydata=FirebaseDatabase.getInstance();
        addroomtofire=mydata.getReference();
        //addroomtofire.child("foofo");

        //intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        //startActivityForResult(intent, IMG_RESULT);
        UploadImage.setOnClickListener(this);
        addfinalroom.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==UploadImage){
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(intent, IMG_RESULT);
        }
        if(v==addfinalroom){
            addRoomtodatabase();
            Toast.makeText(this, "Room Added", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, adminspace.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                    && null != data) {


                Uri URI = data.getData();
                try {

                    Bitmap bimp=MediaStore.Images.Media.getBitmap(getContentResolver(), URI);
                    RoomPicAdd.setImageBitmap(bimp);


                }
                catch (Exception e) {
                    Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                            .show();
                }

            }
    }

    public void addRoomtodatabase(){
        String Roomnaam=RoomName.getText().toString().trim();
        String RoomP=RoomPrice.getText().toString().trim();
        String IsAvail=RoomAvail.getSelectedItem().toString().trim();
        //String PicFirebase="none";
                //RoomPicAdd.get
        String Roomdet=RoomDetails.getText().toString().trim();

        if(!TextUtils.isEmpty(Roomnaam)){

            try {
                String id = addroomtofire.push().getKey();
                hotelroom myroom = new hotelroom(id, Roomnaam, RoomP, IsAvail, Roomdet);
                //addroomtofire.child(id).setValue(myroom);

                //hotelroom myroom = new hotelroom("111", "111", "111", "yes", "111");

                //mydata=ref.child("rooms");
                //mDatabase = FirebaseDatabase.getInstance().getReference();
                //mDatabase.child("users").child(id).setValue("123", "123");
                //abc aa=new abc("saad");
                /*addroomtofire.child("hotelroom").child(id).child("RoomID").setValue(myroom.getRoomId());
                addroomtofire.child("hotelroom").child(id).child("RoomName").setValue(myroom.getRoomName());
                addroomtofire.child("hotelroom").child(id).child("RoomPrice").setValue(myroom.getPrice());
                addroomtofire.child("hotelroom").child(id).child("RoomAvail").setValue(myroom.getAvailibility());
                addroomtofire.child("hotelroom").child(id).child("RoomDetails").setValue(myroom.getDetail());*/
                //myroom.

                addroomtofire.child("hotelroom").child(id).setValue(myroom);

                Toast.makeText(this, "Room Added", Toast.LENGTH_LONG)
                        .show();
            }
            catch (Exception e) {
                Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                        .show();
            }
        }
        else{
            Toast.makeText(this, "Please try again with a valid name", Toast.LENGTH_LONG)
                    .show();
        }
    }

}
