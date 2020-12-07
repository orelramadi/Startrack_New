package com.upstore.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class paypage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypage);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        final Intent intent = this.getIntent();
        final Bundle bundle=intent.getExtras();
        final Businesses businesses=(Businesses)bundle.getSerializable("key");

        ImageView current_service_image = (ImageView) findViewById(R.id.current_service);
        TextView current_service_description = (TextView) findViewById(R.id.current_service_description);
        Button pay_star = (Button) findViewById(R.id.service_pay_stars);
        Button pay_paypal = (Button) findViewById(R.id.service_pay_paypal);

        Glide.with(this).load(businesses.getService1()).into(current_service_image);


        pay_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(paypage.this, add_credit_card.class);
                startActivity(intent);
            }
        });



    }
}