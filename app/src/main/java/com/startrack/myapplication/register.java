package com.startrack.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class register extends AppCompatActivity implements View.OnClickListener{

    private TextView User_Register_Button;
    private EditText User_Name, User_Email, User_Password, User_Phone, User_Stars;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        User_Register_Button = (Button) findViewById(R.id.register_button);
        User_Register_Button.setOnClickListener(this);

        User_Name = (EditText) findViewById(R.id.register_name);
        User_Email = (EditText) findViewById(R.id.register_email);
        User_Password = (EditText) findViewById(R.id.register_password);
        User_Phone = (EditText) findViewById(R.id.register_phone_number);
        User_Stars = (EditText) findViewById(R.id.register_new_user_stars);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_button) {
            RegisterUser();
        }

    }

    private void RegisterUser() {
        final String email = User_Email.getText().toString().trim();
        final String password = User_Password.getText().toString().trim();
        final String phone = User_Phone.getText().toString().trim();
        final String stars = User_Stars.getText().toString().trim();
        final String name = User_Name.getText().toString().trim();


        if(email.isEmpty()){
            User_Email.setError("שדה חובה");
            User_Email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            User_Password.setError("שדה חובה");
            User_Password.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            User_Phone.setError("שדה חובה");
            User_Phone.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            User_Email.setError("אימייל לא חוקי");
            User_Email.requestFocus();
            return;
        }

        if (password.length() < 6){
            User_Password.setError("הסיסמה חייבת להכיל 6 תווים לפחות !");
            User_Password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(name, email, password, phone, stars);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(register.this,"נרשמת בהצלחה !",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else {
                                        Toast.makeText(register.this,"ההרשמה נכשלה",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(register.this,"ההרשמה נכשלה",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        }
                    });
    }
}