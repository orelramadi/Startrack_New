package com.upstore.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

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
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));
        final Intent intent = this.getIntent();
        final Bundle bundle=intent.getExtras();
        final Businesses businesses=(Businesses)bundle.getSerializable("key");

        ImageView business_logo = (ImageView) findViewById(R.id.banner);
        TextView business_name = (TextView) findViewById(R.id.text_business);
        TextView business_description = (TextView) findViewById(R.id.business_description);
        ImageView ServiceOne = (ImageView) findViewById(R.id.service_01);
        ImageView ServiceTwo = (ImageView) findViewById(R.id.service_02);
        ImageView ServiceThree = (ImageView) findViewById(R.id.service_03);



        if(businesses.getLogo() != null){
            Glide.with(this).load(businesses.getLogo()).into(business_logo);
        }else {
            Glide.with(this).load(businesses.getBanner()).into(business_logo);
        }
        if(businesses.getLogo() != null){
            business_name.setText(businesses.getName());
        }else {
            business_name.setText(businesses.getName());
        }
        if(businesses.getLogo() != null){
            business_description.setText(businesses.getDescription());
        }else {
            business_description.setText(businesses.getDescription());
        }
        if(businesses.getService1() != null){
            Glide.with(this).load(businesses.getService1()).into(ServiceOne);
        }
        if(businesses.getService2() != null){
            Glide.with(this).load(businesses.getService2()).into(ServiceTwo);
        }
        if(businesses.getService3() != null){
            Glide.with(this).load(businesses.getService3()).into(ServiceThree);
        }

        ServiceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceOne = new Intent(BusinessActivity.this,paypage.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("key",businesses);
                serviceOne.putExtras(bundle);
                startActivity(serviceOne);
            }
        });
        ServiceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceTwo = new Intent(BusinessActivity.this,paypage.class);
                startActivity(serviceTwo);
            }
        });
        ServiceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceOne = new Intent(BusinessActivity.this,paypage.class);
                startActivity(serviceOne);
            }
        });

        /*/
        recyclerViewService=findViewById(R.id.recyclerviewservice);
        recyclerViewService.setLayoutManager(new LinearLayoutManager(this));

        servicesList=new ArrayList<>();
        myRef=FirebaseDatabase.getInstance().getReference("Businesses");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Services services=ds.getValue(Services.class);
                    servicesList.add(services);
                }
                serviceAdapter=new ServiceAdapter(servicesList);
                recyclerViewService.setAdapter(serviceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         /*/

    }
}
    /*/
        //GetDataFromFirebase();



        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Firebase
        myRef = FirebaseDatabase.getInstance().getReference();

        // ArrayList
        businessesList = new ArrayList<>();

        // Clear ArrayList
        ClearAll();

        // Get Data Method
        GetDataFromFirebase();

    }


    private void GetDataFromFirebase() {

        DatabaseReference query = myRef.child("Businesses");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Businesses businesses = new Businesses();

                    businesses.setBanner(snapshot.child("service1").getValue().toString());
                    businessesList.add(businesses);
                }
                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), businessesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void ClearAll(){
        if (businessesList != null){
            businessesList.clear();

            if (recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        businessesList = new ArrayList<>();
    }


}
/*/