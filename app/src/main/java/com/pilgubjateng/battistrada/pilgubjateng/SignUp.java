package com.pilgubjateng.battistrada.pilgubjateng;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Button btnSignUp;
    EditText inputEmail, inputPass;
    TextView btnLogin, btnForgot;

    LinearLayout activity_sign_up;

    private FirebaseAuth auth;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //View
        btnSignUp = (Button) findViewById(R.id.btnSignUp_signup);
        btnLogin = (TextView) findViewById(R.id.btnLogin_signup);
        btnForgot = (TextView) findViewById(R.id.btnForgot_login);
        inputEmail = (EditText)findViewById(R.id.signUpEmail);
        inputPass = (EditText)findViewById(R.id.password_signUp);
        activity_sign_up = (LinearLayout) findViewById(R.id.activity_signup);

        btnSignUp.setOnClickListener(this);
        //btnForgot.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //init
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUp.this, SignIn.class));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin_signup){
            startActivity(new Intent(SignUp.this,SignIn.class));
            finish();
        }else if (view.getId() == R.id.btnForgot_login){
            startActivity(new Intent(SignUp.this,ForgotPass.class));
            finish();
        }else if (view.getId() == R.id.btnSignUp_signup){
            SignUpUser(inputEmail.getText().toString(),inputPass.getText().toString());
        }
    }

    private void SignUpUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            snackbar = Snackbar.make(activity_sign_up,"Error "+task.getException(),Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }else {
                            snackbar = Snackbar.make(activity_sign_up,"Register Success !",Snackbar.LENGTH_LONG);
                            snackbar.show();
                            startActivity(new Intent(SignUp.this,SignIn.class));
                            finish();
                        }
                    }
                });
    }
}
