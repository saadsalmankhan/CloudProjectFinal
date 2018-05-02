package com.l124320.cloudprojectfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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



public class editroom extends AppCompatActivity implements View.OnClickListener{


    EditText RoomNameEdit;
    EditText RoomPriceEdit;
    EditText RoomDetailsEdit;
    Spinner RoomAvailEdit;

    ImageView RoomPicAddEdit;

    Button UploadImageEdit;
    Button addfinalroomEdit;

    ProgressDialog mProgressDialog;
    private static int IMG_RESULT = 1;
    Intent intent;

    DatabaseReference addroomtofireEdit;
    FirebaseDatabase mydataEdit;

    private DatabaseReference mDatabase;
    public hotelroom myroom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editroom);

            RoomNameEdit=findViewById(R.id.editTextRoomNameEdit);
            RoomPriceEdit=findViewById(R.id.editTextPriceEdit);
            RoomDetailsEdit=findViewById(R.id.editTextDetailEdit);
            RoomAvailEdit=findViewById(R.id.spinnerAvailibleEdit);

            RoomPicAddEdit=findViewById(R.id.imageViewRoomEdit);

            UploadImageEdit=findViewById(R.id.buttonpicEdit);
            addfinalroomEdit=findViewById(R.id.buttonEdit);


            mydataEdit=FirebaseDatabase.getInstance();
            addroomtofireEdit=mydataEdit.getReference();
            //addroomtofire.child("foofo");

            //intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            //startActivityForResult(intent, IMG_RESULT);
            UploadImageEdit.setOnClickListener(this);
            addfinalroomEdit.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(v==UploadImageEdit){
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(intent, IMG_RESULT);
            }
            if(v==addfinalroomEdit){
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
                    RoomPicAddEdit.setImageBitmap(bimp);


                }
                catch (Exception e) {
                    Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                            .show();
                }

            }
        }


    @Override
    protected void onStart() {
        super.onStart();
    }

    public void addRoomtodatabase(){
        String Roomnaam=RoomNameEdit.getText().toString().trim();
        String RoomP=RoomPriceEdit.getText().toString().trim();
        String IsAvail=RoomAvailEdit.getSelectedItem().toString().trim();
        //String PicFirebase="none";
        //RoomPicAdd.get
        String Roomdet=RoomDetailsEdit.getText().toString().trim();

        if(!TextUtils.isEmpty(Roomnaam)){

            try {
                String id = addroomtofireEdit.push().getKey();
                hotelroom myroom = new hotelroom(id, Roomnaam, RoomP, IsAvail, Roomdet);
                //addroomtofire.child(id).setValue(myroom);

                //hotelroom myroom = new hotelroom("111", "111", "111", "yes", "111");

                //mydata=ref.child("rooms");
                //mDatabase = FirebaseDatabase.getInstance().getReference();
                //mDatabase.child("users").child(id).setValue("123", "123");
                //abc aa=new abc("saad");
                addroomtofireEdit.child("hotelroom").child(id).child("RoomID").setValue(myroom.getRoomId());
                addroomtofireEdit.child("hotelroom").child(id).child("RoomName").setValue(myroom.getRoomName());
                addroomtofireEdit.child("hotelroom").child(id).child("RoomPrice").setValue(myroom.getPrice());
                addroomtofireEdit.child("hotelroom").child(id).child("RoomAvail").setValue(myroom.getAvailibility());
                addroomtofireEdit.child("hotelroom").child(id).child("RoomDetails").setValue(myroom.getDetail());
                //myroom.

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
