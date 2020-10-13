package com.startrack.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class activity_settings extends AppCompatActivity {

    Button change_nickname_button, change_phone_button, change_email_button;
    ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void change_nickname(View view) {
        TextView change_nickname = findViewById(R.id.nickname);
        change_nickname.setText("Test");
        Toast.makeText(this, "Nickname was changed", Toast.LENGTH_SHORT).show();
    }

    public void change_phone(View view) {
    }

    public void change_email(View view) {
    }

    public void toggle(View view) {
    }

}