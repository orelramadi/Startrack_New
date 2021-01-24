package com.upstore.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.upstore.myapplication.Database.add_credit_card;

public class ServiceBusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicebusiness);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        final Intent intent = this.getIntent();
        final Bundle bundle=intent.getExtras();
        final Businesses service1=(Businesses)bundle.getSerializable("service1");
        final Businesses service2=(Businesses)bundle.getSerializable("service2");
        final Businesses service3 =(Businesses)bundle.getSerializable("service3");



        ImageView current_service_image = (ImageView) findViewById(R.id.current_service);
        TextView current_service_description = (TextView) findViewById(R.id.current_service_description);
        Button pay_star = (Button) findViewById(R.id.service_pay_stars);
        Button pay_paypal = (Button) findViewById(R.id.service_pay_paypal);

        if (getIntent().getExtras().getSerializable("service1") != null) {
            Glide.with(ServiceBusinessActivity.this).load(service1.getService1()).into(current_service_image);
        }
        else if (getIntent().getExtras().getSerializable("service2") != null){
            Glide.with(ServiceBusinessActivity.this).load(service2.getService2()).into(current_service_image);
        }
        else if (getIntent().getExtras().getSerializable("service3") != null){
            Glide.with(ServiceBusinessActivity.this).load(service3.getService3()).into(current_service_image);
        }
        else {
            Toast.makeText(ServiceBusinessActivity.this, "No Service Added", Toast.LENGTH_SHORT).show();
        }





        pay_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceBusinessActivity.this, add_credit_card.class);
                startActivity(intent);
            }
        });



    }
}