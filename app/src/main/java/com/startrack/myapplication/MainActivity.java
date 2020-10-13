package com.startrack.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //Onclick settings move to settings activity
            case R.id.settings:
                Intent settings = new Intent(this, activity_settings.class);
                startActivity(settings);
                return true;

                //Onclick categories move to categories activity
            case R.id.categories:
                Intent categories = new Intent(this, activity_categories.class);
                startActivity(categories);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void service_detail(View view) {
        Intent intent = new Intent(this,service_detail.class);
        startActivity(intent);
    }

    public void stars_icon(View view) {
        Intent intent = new Intent(MainActivity.this,add_stars.class);
        startActivity(intent);
    }

}