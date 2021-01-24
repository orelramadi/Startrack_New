package com.upstore.myapplication.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.upstore.myapplication.MainActivity;
import com.upstore.myapplication.R;

public class Activity_Login extends AppCompatActivity implements View.OnClickListener{

    private EditText User_Email, User_Password;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));

        TextView register_personal = (TextView) findViewById(R.id.register_button_personal);
        register_personal.setOnClickListener(this);

        TextView register_business = (TextView) findViewById(R.id.register_button_business);
        register_business.setOnClickListener(this);

        Button sign_In = (Button) findViewById(R.id.login_button);
        sign_In.setOnClickListener(this);

        User_Email = (EditText) findViewById(R.id.login_email);
        User_Password = (EditText) findViewById(R.id.login_password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        mUser = FirebaseAuth.getInstance().getCurrentUser();


        if (mUser != null) {
            Intent intent = new Intent(Activity_Login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                userLogin();
                break;

            case R.id.register_button_personal:
                startActivity(new Intent(this, UserRegister.class));
                break;

            case R.id.register_button_business:
                startActivity(new Intent(this, BusinessRegister.class));
                break;

        }
    }

    private void userLogin() {
        String email = User_Email.getText().toString().trim();
        String password = User_Password.getText().toString().trim();

        if (email.isEmpty()){
            User_Email.setError("שדה חובה");
            User_Email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            User_Email.setError("אימייל לא חוקי");
            User_Email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            User_Password.setError("שדה חובה");
            User_Password.requestFocus();
            return;
        }
        if (password.length() < 6){
            User_Password.setError("הסיסמה חייבת להכיל 6 תווים לפחות !");
            User_Password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Activity_Login.this, MainActivity.class));
                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(Activity_Login.this,"המשתמש או הסיסמה לא נכונים",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}