package com.upstore.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_settings extends AppCompatActivity {

    private FirebaseUser mAuth;
    private DatabaseReference db_users;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        db_users = FirebaseDatabase.getInstance().getReference("Users");
        userID = mAuth.getUid();

        final TextView name = (TextView) findViewById(R.id.profile_name);
        final TextView name_change = (TextView) findViewById(R.id.profile_name_change);
        final TextView phone = (TextView) findViewById(R.id.phone);
        final TextView stars = (TextView) findViewById(R.id.stars_balance);
        final TextView email_change = (TextView) findViewById(R.id.email_change);
        Button Save_info_button = (Button) findViewById(R.id.save_info_button);
        Button Add_credit_card_button = (Button) findViewById(R.id.add_credit_card_button);



        if (mAuth != null) {
            db_users.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    assert userProfile != null;
                    name.setText(userProfile.name);
                    phone.setText(userProfile.phone);
                    stars.setText(userProfile.stars);
                    name_change.setText(userProfile.name);
                    email_change.setText(userProfile.email);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(activity_settings.this, "לא קיים מידע עבור משתמש זה", Toast.LENGTH_LONG).show();
                }
            });

            Save_info_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db_users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User userProfile = snapshot.getValue(User.class);
                            if (userProfile.name != name_change.getText().toString()){
                            db_users.child(userID).child("name").setValue(name_change.getEditableText().toString());
                            }
                            if (userProfile.phone != phone.getText().toString()){
                                db_users.child(userID).child("phone").setValue(phone.getEditableText().toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });

            Add_credit_card_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent add_credit_card_activity = new Intent(activity_settings.this,add_credit_card.class);
                    startActivity(add_credit_card_activity);
                }
            });

        }
    }
}
