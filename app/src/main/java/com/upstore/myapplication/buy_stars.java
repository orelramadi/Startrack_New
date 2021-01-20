package com.upstore.myapplication;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class buy_stars extends AppCompatActivity {
    private FirebaseUser mAuth;
    private DatabaseReference db_users;
    private String userID, stars_balance_floating;
    Integer y;
    String new_star_quantity;
    EditText stars_buy_quantity;
    Button button_buy_stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_stars);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        button_buy_stars = findViewById(R.id.buy_stars);
        stars_buy_quantity = findViewById(R.id.stars_buy_quantity);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        db_users = FirebaseDatabase.getInstance().getReference("Users");
        userID = mAuth.getUid();
        final TextView stars_balance = (TextView) findViewById(R.id.stars_balance);


        if (mAuth != null) {
            db_users.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    String user_stars = userProfile.stars;
                    stars_balance.setText(user_stars);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(buy_stars.this, "לא קיים מידע עבור משתמש זה", Toast.LENGTH_LONG).show();
                }
            });
        }

        button_buy_stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db_users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);
                        assert userProfile != null;
                        //db_users.child(userID).child("stars").setValue(new_star_quantity);
                        Toast.makeText(buy_stars.this,"The new value is "+new_star_quantity,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}