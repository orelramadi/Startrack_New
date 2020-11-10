package com.upstore.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseUser business;
    private FirebaseAuth mAuth;
    private DatabaseReference db_users, db_businesses;
    FirebaseUser mAuthUser;

    private String userID;
    private String businessID;

    RecyclerView myrecyclerView;
    RecyclerViewAdapter myAdapter;

    List<Businesses> businesses;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settings = new Intent(this, activity_settings.class);
                startActivity(settings);
                return true;
            case R.id.add_stars:
                Intent add_stars = new Intent(this, add_stars.class);
                startActivity(add_stars);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        mAuthUser = FirebaseAuth.getInstance().getCurrentUser();
        user = FirebaseAuth.getInstance().getCurrentUser();
        business = FirebaseAuth.getInstance().getCurrentUser();
        db_users = FirebaseDatabase.getInstance().getReference("Users");
        db_businesses = FirebaseDatabase.getInstance().getReference("Businesses");
        userID = user.getUid();
        businessID = business.getUid();

        final TextView stars_balance = (TextView) findViewById(R.id.stars_balance);

        businesses = new ArrayList<>();
        businesses.add(new Businesses(R.drawable.upneti, "קהילה למפגשים ופיתוח עסקי אונליין", getString(R.string.upneti_description), "050-577-0500", "164", "10$"));
        businesses.add(new Businesses(R.mipmap.magicflex_foreground, "MagicFlex Analysis Software", getString(R.string.magicflex_description), "03-444-5555", "10", "15$"));
        businesses.add(new Businesses(R.mipmap.tovana_foreground, getString(R.string.tovana_name), getString(R.string.tovana_description), getString(R.string.tovana_phone), "Cost Stars", "Cost Paypal"));
        businesses.add(new Businesses(R.mipmap.tzahi_foreground, getString(R.string.Tzahi_Touito_name), getString(R.string.Tzahi_Touito_description), getString(R.string.Tzahi_Touito_phone), "Cost Stars", "Cost Paypal"));
        businesses.add(new Businesses(R.mipmap.ofer_foreground, getString(R.string.ofer_name), getString(R.string.ofer_description), getString(R.string.ofer_phone), "Cost Stars", "Cost Paypal"));
        businesses.add(new Businesses(R.mipmap.tatiana_foreground, getString(R.string.tatyana_name), getString(R.string.tatyana_description), getString(R.string.tatyana_phone), "Cost Stars", "Cost Paypal"));


        myrecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        myAdapter = new RecyclerViewAdapter(this, businesses);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        myrecyclerView.setAdapter(myAdapter);



        if (mAuthUser.getUid().equals(db_users)) {
            db_users.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    String user_stars = userProfile.stars;
                    stars_balance.setText(user_stars);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, "לא קיים מידע עבור משתמש זה", Toast.LENGTH_LONG).show();
                }
            });
        }else if (mAuthUser.getUid().equals(db_businesses)){
            db_businesses.child(businessID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Business businessProfile = snapshot.getValue(Business.class);
                    String business_stars = businessProfile.stars;
                    stars_balance.setText(business_stars);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, "לא קיים מידע עבור משתמש זה", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
