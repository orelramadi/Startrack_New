package com.upstore.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register_business extends AppCompatActivity implements View.OnClickListener{

    private EditText Business_Name, Business_Email, Business_Password, Business_Phone, Business_Description, Business_Description_Short, Business_Cost_Stars, Business_Stars;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));


        mAuth = FirebaseAuth.getInstance();

        TextView business_Register_Button = (Button) findViewById(R.id.register_button_business);
        business_Register_Button.setOnClickListener(this);

        Business_Name = (EditText) findViewById(R.id.register_name_business);
        Business_Email = (EditText) findViewById(R.id.register_email_business);
        Business_Password = (EditText) findViewById(R.id.register_password_business);
        Business_Phone = (EditText) findViewById(R.id.register_phone_number_business);
        Business_Description = (EditText) findViewById(R.id.registration_business_description);
        Business_Stars = (EditText) findViewById(R.id.register_new_business_stars);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_button_business) {
            RegisterBusiness();
        }

    }

    private void RegisterBusiness() {
        final String email = Business_Email.getText().toString().trim();
        final String password = Business_Password.getText().toString().trim();
        final String phone = Business_Phone.getText().toString().trim();
        final String stars = Business_Stars.getText().toString().trim();
        final String description = Business_Description.getText().toString().trim();
        final String description_short = Business_Description_Short.getText().toString().trim();
        final String cost_stars = Business_Cost_Stars.getText().toString().trim();
        final String name = Business_Name.getText().toString().trim();


        if(email.isEmpty()){
            Business_Email.setError("שדה חובה");
            Business_Email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            Business_Password.setError("שדה חובה");
            Business_Password.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            Business_Phone.setError("שדה חובה");
            Business_Phone.requestFocus();
            return;
        }
        if (description.isEmpty()){
            Business_Phone.setError("שדה חובה");
            Business_Phone.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Business_Email.setError("אימייל לא חוקי");
            Business_Email.requestFocus();
            return;
        }

        if (password.length() < 6){
            Business_Password.setError("הסיסמה חייבת להכיל 6 תווים לפחות !");
            Business_Password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Business business = new Business(name, email, password, phone, description, description_short, cost_stars, stars);

                            FirebaseDatabase.getInstance().getReference("Businesses")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(business).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(register_business.this,"נרשמת בהצלחה !",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else {
                                        Toast.makeText(register_business.this,"ההרשמה נכשלה",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(register_business.this,"ההרשמה נכשלה",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}