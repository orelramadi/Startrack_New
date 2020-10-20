package com.startrack.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        ImageView mBusinessLogo = (ImageView) findViewById(R.id.logo);
        TextView mBusinessName = (TextView) findViewById(R.id.text_business);
        TextView mBusinessDescription = (TextView) findViewById(R.id.description);
        TextView mBusinessPhone = (TextView) findViewById(R.id.phone);
        TextView mBusinessCostStars = (TextView) findViewById(R.id.cost_stars);
        TextView mBusinessCostPaypal = (TextView) findViewById(R.id.cost_paypal);

        Intent intent = getIntent();
        int Logo = intent.getExtras().getInt("Logo");
        String Name = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Description");
        String Phone = intent.getExtras().getString("Phone");
        String Star = intent.getExtras().getString("Cost_Star");
        String Paypal = intent.getExtras().getString("Cost_Paypal");


        mBusinessLogo.setImageResource(Logo);
        mBusinessName.setText(Name);
        mBusinessDescription.setText(Description);
        mBusinessPhone.setText(Phone);
        mBusinessCostStars.setText(Star);
        mBusinessCostPaypal.setText(Paypal);

    }
}