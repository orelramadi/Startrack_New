package com.upstore.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.upstore.myapplication.Adapters.ServiceAdapter;

import java.util.ArrayList;
import java.util.List;

public class BusinessActivity extends AppCompatActivity {
    //RecyclerView
    ServiceAdapter serviceAdapter;
    RecyclerView recyclerViewService;
    List<String> businessesList;
    ArrayList<Services> servicesList;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        //Send Intent to next activity

        final Intent intent = this.getIntent();
        final Bundle bundle=intent.getExtras();
        final Businesses businesses=(Businesses)bundle.getSerializable("key");

        ImageView business_logo = (ImageView) findViewById(R.id.banner);
        TextView business_name = (TextView) findViewById(R.id.text_business);
        TextView business_description = (TextView) findViewById(R.id.business_description);
        ImageView ServiceOne = (ImageView) findViewById(R.id.service_01);
        ImageView ServiceTwo = (ImageView) findViewById(R.id.service_02);
        ImageView ServiceThree = (ImageView) findViewById(R.id.service_03);



        if(businesses.getBanner() != null){
            Glide.with(this).load(businesses.getLogo()).into(business_logo);
            business_name.setText(businesses.getName());
            business_description.setText(businesses.getDescription());
        }else{

        }

        if(businesses.getService1() != null && businesses.getService2() != null && businesses.getService3() != null){
            Glide.with(this).load(businesses.getService1()).into(ServiceOne);
            Glide.with(this).load(businesses.getService2()).into(ServiceTwo);
            Glide.with(this).load(businesses.getService3()).into(ServiceThree);
        }else {

        }

        ServiceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceOne = new Intent(BusinessActivity.this, ServiceBusinessActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("service1",businesses);
                serviceOne.putExtras(bundle);
                startActivity(serviceOne);
            }
        });
        ServiceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceTwo = new Intent(BusinessActivity.this, ServiceBusinessActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("service2",businesses);
                serviceTwo.putExtras(bundle);
                startActivity(serviceTwo);
            }
        });
        ServiceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceThree = new Intent(BusinessActivity.this, ServiceBusinessActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("service3",businesses);
                serviceThree.putExtras(bundle);
                startActivity(serviceThree);
            }
        });
    }
}
