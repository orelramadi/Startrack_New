package com.startrack.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView myrecyclerView;
    RecyclerViewAdapter myAdapter;

    List<Businesses> business1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        business1 = new ArrayList<>();
        business1.add(new Businesses(R.drawable.upneti,"Upneti",getString(R.string.service_description),"052-345-6789","5","10$"));
        business1.add(new Businesses(R.mipmap.magicflex_foreground,"MagicFlex",getString(R.string.service_description),"03-444-5555","10","15$"));
        business1.add(new Businesses(R.drawable.ic_launcher_foreground,"Company Name", "Description","Phone Number","15","20$"));


        myrecyclerView = (RecyclerView)findViewById(R.id.RecyclerView);

        myAdapter = new RecyclerViewAdapter(this,business1);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        myrecyclerView.setAdapter(myAdapter);

    }



}