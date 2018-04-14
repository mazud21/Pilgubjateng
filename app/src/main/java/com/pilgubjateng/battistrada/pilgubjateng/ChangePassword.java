package com.pilgubjateng.battistrada.pilgubjateng;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity{

    private TextView textView;
    private EditText editPass;
    private Button btnChange;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        setTitle("Ubah Password");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editPass = (EditText) findViewById(R.id.edit_password_profile);
        btnChange = (Button) findViewById(R.id.btnChange);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.tved);

        //init
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            textView.setText("Welcome " + auth.getCurrentUser().getEmail());
        }

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String password = editPass.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplication(), "Enter your new Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                } else {
                    user = auth.getCurrentUser();
                    if (user != null) {
                        user.updatePassword(password)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(ChangePassword.this, "Failed to Change Password", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ChangePassword.this, "Password Changed !", Toast.LENGTH_LONG).show();
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });
                    }
                }
            }
        });
    }
}