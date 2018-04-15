package com.pilgubjateng.battistrada.pilgubjateng;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pilgubjateng.battistrada.pilgubjateng.model.UserModel;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText inputEmail, inputPass;
    TextView btnSignUp, btnForgot;
    CheckBox checkBox;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private UserModel userModel;

    LinearLayout activity_sign_in;
    ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //View
        btnLogin = (Button) findViewById(R.id.btnLogin);
        inputEmail = (EditText) findViewById(R.id.loginEmail);
        inputPass = (EditText) findViewById(R.id.password_login);
        btnSignUp = (TextView) findViewById(R.id.btnSignUp_login);
        btnForgot = (TextView) findViewById(R.id.btnForgot_login);
        checkBox = (CheckBox) findViewById(R.id.checkbox1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        activity_sign_in = (LinearLayout) findViewById(R.id.activity_signin);

        btnSignUp.setOnClickListener(this);
        btnForgot.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //init
        auth = FirebaseAuth.getInstance();

        userModel = new UserModel();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    inputPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    inputPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        //Check if already session, if->true=> Main Program
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SignIn.this, MainActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnForgot_login) {
            startActivity(new Intent(SignIn.this, ForgotPass.class));
            finish();
        } else if (view.getId() == R.id.btnSignUp_login) {
            startActivity(new Intent(SignIn.this, SignUp.class));
            finish();
        } else if (view.getId() == R.id.btnLogin) {
            String email = inputEmail.getText().toString().trim();
            String password = inputPass.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplication(), "Masukkan Email yang sudah terdaftar", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplication(), "Masukkan Password", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(getApplication(), "Email dan Password masih kosong", Toast.LENGTH_SHORT).show();

            } else if (password.length() <= 7) {
                Toast.makeText(getApplication(), "Password minimal 8 huruf", Toast.LENGTH_SHORT).show();

            } else {
                loginUser(inputEmail.getText().toString(), inputPass.getText().toString());
            }
        }
    }

    private void loginUser(final String email, final String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplication(), "Email atau Password yang anda masukkan salah", Toast.LENGTH_LONG).show();
                        } else {
                            progressBar.setVisibility(View.VISIBLE);
                            startActivity(new Intent(SignIn.this, MainActivity.class));
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}