package com.startrack.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class add_stars extends AppCompatActivity {
    String new_star_quantity;
    Integer x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stars);
    }

    public void stars_add_icon(View view) {
        // Importing object from XML
        EditText editText = (EditText) findViewById(R.id.stars_quantity_add_value);
        // Parse string to int
        x = Integer.parseInt(editText.getText().toString());
        // add 1 to current quantity while pushing the button
        y = x + 1;
        // Parse int to string
        new_star_quantity = Integer.toString(y);
        // Showing new result
        editText.setText(new_star_quantity);
    }
}