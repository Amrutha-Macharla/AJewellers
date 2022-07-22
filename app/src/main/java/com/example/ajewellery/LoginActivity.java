package com.example.ajewellery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ajewellery-585ff-default-rtdb.firebaseio.com/");
    EditText etMail,etPassword;
    TextView tvForgotPass, tvNewReg;
    ImageView imgGoogle;
    MaterialButton btnLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etMail = findViewById(R.id.et_maillogin);
        etPassword = findViewById(R.id.et_password);
        tvForgotPass = findViewById(R.id.tv_forgotpass);
        tvNewReg = findViewById(R.id.tv_newreg);
        imgGoogle = findViewById(R.id.img_googleimg);
        btnLogin = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail = etMail.getText().toString();
                final String pass = etPassword.getText().toString();

                if(mail.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent activity = new Intent(getApplicationContext(),IndexActivity.class);
                                startActivity(activity);
                            }else{
                                Toast.makeText(LoginActivity.this, "Login Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        tvNewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(activity);

            }
        });
//        imgGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Intent activity = new Intent(getApplicationContext(),IndexActivity.class);
            startActivity(activity);
        }
        else {
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu1,menu);
//        return true;
//    }
}