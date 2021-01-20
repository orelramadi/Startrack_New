package com.upstore.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

    private FirebaseUser mAuth;
    private DatabaseReference db_users;
    private String userID, stars_balance_floating;

    //RecyclerView
    RecyclerView recyclerView;
    List<Businesses> businessesList;
    BusinessAdapter businessAdapter;
    DatabaseReference myRef;


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
            case R.id.buy_stars:
                Intent buy_stars = new Intent(this, buy_stars.class);
                startActivity(buy_stars);
                return true;
            case R.id.transaction_history:
                Intent transaction_history = new Intent(this, transaction_history.class);
                startActivity(transaction_history);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        //RecyclerView
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        businessesList=new ArrayList<>();
        myRef=FirebaseDatabase.getInstance().getReference("Businesses");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Businesses businesses=ds.getValue(Businesses.class);
                    businessesList.add(businesses);
                }
                businessAdapter=new BusinessAdapter(businessesList);
                recyclerView.setAdapter(businessAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_settings.class);
                startActivity(intent);
            }
        });
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
                    Toast.makeText(MainActivity.this, "לא קיים מידע עבור משתמש זה", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}