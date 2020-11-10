package com.upstore.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.EventListener;

public class BusinessActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        ImageView mBusinessLogo = (ImageView) findViewById(R.id.logo);
        TextView mBusinessName = (TextView) findViewById(R.id.text_business);
        TextView mBusinessDescription = (TextView) findViewById(R.id.description);
        TextView mBusinessPhone = (TextView) findViewById(R.id.phone);
        TextView mBusinessCostStars = (TextView) findViewById(R.id.buy_in_stars);

        final Intent intent = getIntent();
        int Logo = intent.getExtras().getInt("Logo");
        String Name = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Description");
        String Phone = intent.getExtras().getString("Phone");
        final String Star = intent.getExtras().getString("Cost_Star");


        mBusinessLogo.setImageResource(Logo);
        mBusinessName.setText(Name);
        mBusinessDescription.setText(Description);
        mBusinessPhone.setText(Phone);
        mBusinessCostStars.setText(Star);


        Button pay_stars = (Button) findViewById(R.id.pay_stars);

        pay_stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Payment_Stars_Dialog payment_stars_dialog = new Payment_Stars_Dialog();
                payment_stars_dialog.show(getSupportFragmentManager(), "Example Dialog");
            }
        });

        Button image_button_service = (Button) findViewById(R.id.image_card_view_service);
        image_button_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BusinessActivity.this, service.class);
                startActivity(intent1);
            }
        });


    }
}