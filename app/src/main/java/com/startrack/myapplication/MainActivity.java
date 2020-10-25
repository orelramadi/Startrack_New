package com.startrack.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    RecyclerView myrecyclerView;
    RecyclerViewAdapter myAdapter;

    List<Businesses> businesses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView stars_balance = (TextView) findViewById(R.id.stars_balance);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String stars = userProfile.stars;

                    stars_balance.setText(stars);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        businesses = new ArrayList<>();
        businesses.add(new Businesses(R.drawable.upneti,"Upneti",getString(R.string.upneti_description),"052-345-6789","5","10$"));
        businesses.add(new Businesses(R.mipmap.magicflex_foreground,"MagicFlex",getString(R.string.magicflex_description),"03-444-5555","10","15$"));
        businesses.add(new Businesses(R.drawable.ic_launcher_foreground,"Company Name", "Description","Phone Number","Cost Stars","Cost Paypal"));
        businesses.add(new Businesses(R.drawable.ic_launcher_foreground,"Company Name", "Description","Phone Number","Cost Stars","Cost Paypal"));
        businesses.add(new Businesses(R.drawable.ic_launcher_foreground,"Company Name", "Description","Phone Number","Cost Stars","Cost Paypal"));


        myrecyclerView = (RecyclerView)findViewById(R.id.RecyclerView);

        myAdapter = new RecyclerViewAdapter(this,businesses);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        myrecyclerView.setAdapter(myAdapter);

    }



}