package com.l124320.cloudprojectfinal;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class profile extends AppCompatActivity implements View.OnClickListener{

    private Button SignoutBut;

    private FirebaseAuth firebaseauth;

    private Context c;
    DatabaseReference hotelRooms;
    RecyclerView RecyclerVierRooms;
    //DataSnapshot roomsSnapshot;
    List<hotelroom> RoomsList;
    MyAdapter RoomsListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        c = this;
        firebaseauth = FirebaseAuth.getInstance();



        if(firebaseauth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, signin.class));
        }

        FirebaseUser User = firebaseauth.getCurrentUser();

        RecyclerVierRooms=findViewById(R.id.recyclerViewRooms);
        RoomsList=new ArrayList<>();
        RoomsListAdapter = new MyAdapter(RoomsList, 0);
        RecyclerVierRooms.setLayoutManager(new LinearLayoutManager(c));
        RecyclerVierRooms.setAdapter(RoomsListAdapter);
        SignoutBut=findViewById(R.id.homeSignout);

        SignoutBut.setOnClickListener(this);
    }

   @Override
    protected void onStart() {
        super.onStart();

        hotelRooms = FirebaseDatabase.getInstance().getReference();
           hotelRooms.getRef().addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   Toast.makeText(c, "no of Children: " + Long.toString(dataSnapshot.getChildrenCount()), Toast.LENGTH_SHORT).show();
                   hotelroom myRoom = new hotelroom();
                   DataSnapshot roomsSnapshot;
//                   Iterable<DataSnapshot>  i = roomsSnapshot.getChildren();
//                   Consumer<DataSnapshot> action;
//                   while(i.iterator().hasNext()){
//                       roomsSnapshot = i.iterator().next();
//                       myRoom = roomsSnapshot.getValue(hotelroom.class);
//                       RoomsList.add(myRoom);
//
//                       Log.d("Message Saad: --- ", "calling");
//
//                   }

                   for(int i = 0; i < dataSnapshot.getChildrenCount(); ++i){
                     roomsSnapshot = dataSnapshot.getChildren().iterator().next();
                     myRoom = roomsSnapshot.getValue(hotelroom.class);
                     RoomsList.add(myRoom);
                   }
                    myRoom = new hotelroom("default", "default","default","default","default");
                   RoomsList.add(myRoom);
                   myRoom = new hotelroom("default", "default","default","default","default");
                   RoomsList.add(myRoom);

                   RoomsListAdapter.notifyDataSetChanged();
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });


    }

    @Override
    public void onClick(View v) {
        if(v==SignoutBut){
            finish();
            firebaseauth.signOut();
            startActivity(new Intent(this, signin.class));
        }
    }
}

