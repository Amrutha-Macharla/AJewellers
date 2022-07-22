package com.example.ajewellery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText etUserName,etEmail,etPhno,etPass,etReenter;
    TextView tvLogin;
    MaterialButton btnSignup;
    ProgressBar bar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUserName = findViewById(R.id.et_usernamereg);
        etEmail = findViewById(R.id.et_mail);
        etPhno = findViewById(R.id.et_phno);
        etPass = findViewById(R.id.et_pass);
        etReenter = findViewById(R.id.et_reenterpass);
        tvLogin = findViewById(R.id.tv_login);
        btnSignup = findViewById(R.id.btn_signup);
        bar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();



        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(activity);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUserName.getText().toString();
                String pass = etPass.getText().toString();
                String mail = etEmail.getText().toString();
                String phno = etPhno.getText().toString();
                String reenter = etReenter.getText().toString();
                mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                            HashMap<String,Object> userdata = new HashMap<>();
                            userdata.put("Name",username);
                            userdata.put("Email",mail);
                            userdata.put("phno",phno);
                            DatabaseReference myRef = database.getReference("users/"+task.getResult().getUser().getUid());
                            myRef.setValue(userdata);
//                            System.out.println(task.getResult().getUser().getUid());
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Error !!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}