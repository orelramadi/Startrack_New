package com.upstore.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class activity_settings extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    String User_Name, User_Phone, User_Email;
    Button change_nickname_button, change_phone_button, change_email_button;
    ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void change_nickname(View view) {
        TextView change_nickname = findViewById(R.id.nickname);

        Toast.makeText(this, "Nickname was changed", Toast.LENGTH_SHORT).show();
    }

    public void change_phone(View view) {
    }

    public void change_email(View view) {
    }

    public void toggle(View view) {
    }

}