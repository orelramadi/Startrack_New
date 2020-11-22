package com.upstore.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Integer x, y;
    String new_star_quantity;
    TextView currect_stars;
    ImageView button_add_stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_stars);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        currect_stars = findViewById(R.id.stars_quantity_add_value);
        currect_stars.getText().toString();
        button_add_stars = findViewById(R.id.stars_quantity_add_button);

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
        button_add_stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = Integer.parseInt(currect_stars.getText().toString());
                y = Integer.parseInt(stars_balance.getText().toString());
                new_star_quantity = Integer.toString(x + y);
                stars_balance.setText(new_star_quantity);


            }
        });
    }
}