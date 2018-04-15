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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pilgubjateng.battistrada.pilgubjateng.model.UserModel;
import com.pilgubjateng.battistrada.pilgubjateng.util.Constant;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //deklrasi view
    EditText inputEmail, inputPass;
    TextView btnLogin, btnForgot;
    Button btnSignUp;

    LinearLayout activity_sign_up;

    //deklarasi firebase library
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //deklarasi penyimpanan uid
    private String email_uid;
    private String userEmail;

    //deklarasi model mengatur(set) pada variable
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //View
        btnSignUp = (Button) findViewById(R.id.btnSignUp_signup);
        btnLogin = (TextView) findViewById(R.id.btnLogin_signup);
        btnForgot = (TextView) findViewById(R.id.btnForgot_login);
        inputEmail = (EditText) findViewById(R.id.signUpEmail);
        inputPass = (EditText) findViewById(R.id.password_signUp);
        activity_sign_up = (LinearLayout) findViewById(R.id.activity_signup);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //init
        auth = FirebaseAuth.getInstance();
        userModel = new UserModel();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(Constant.KEY_USER);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUp.this, SignIn.class));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin_signup) {
            startActivity(new Intent(SignUp.this, SignIn.class));
            finish();
        } else if (view.getId() == R.id.btnForgot_login) {
            startActivity(new Intent(SignUp.this, ForgotPass.class));
            finish();
        } else if (view.getId() == R.id.btnSignUp_signup) {
            SignUpUser(inputEmail.getText().toString(), inputPass.getText().toString());
        }
    }

    private void SignUpUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast toast = Toast.makeText(getApplication(), "Gagal : " + task.getException(), Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(getApplication(), "Pendaftaran Berhasil ", Toast.LENGTH_SHORT);
                            toast.show();
                            startActivity(new Intent(SignUp.this, SignIn.class));
                            //init
                            email_uid = auth.getCurrentUser().getUid();
                            userEmail = auth.getCurrentUser().getEmail();
                            //set variable != null
                            userModel.setEmail(userEmail);
                            userModel.setNama("");
                            userModel.setAlamat("");
                            userModel.setNik("");
                            //proses setting nilai pada userModel
                            databaseReference.child(email_uid).setValue(userModel);
                            finish();
                        }
                    }
                });
    }
}
