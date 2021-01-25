package com.upstore.myapplication.Cash;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upstore.myapplication.R;


public class add_credit_card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card);

        DatabaseReference db_users;
        db_users = FirebaseDatabase.getInstance().getReference();

        TextView Credit_Number = (TextView) findViewById(R.id.add_credit_card_number);
        TextView Expiration_Date = (TextView) findViewById(R.id.add_credit_card_date);
        TextView CVC = (TextView) findViewById(R.id.add_credit_card_cvc);
        Button Save_CreditCard = (Button) findViewById(R.id.add_credit_card_save);

    }
}