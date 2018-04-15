package com.pilgubjateng.battistrada.pilgubjateng;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pilgubjateng.battistrada.pilgubjateng.model.UserModel;
import com.pilgubjateng.battistrada.pilgubjateng.util.Constant;

public class EditProfile extends AppCompatActivity {

    private EditText txtEditEmailProfile;
    private EditText etEditNIKProfile, etEditNamaProfile, etEditAlamatProfile, etEditEmailProfile;
    private ProgressBar progressBar;
    private FloatingActionButton fabSendEditProfile;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //init
        auth = FirebaseAuth.getInstance();

        txtEditEmailProfile = (EditText) findViewById(R.id.tvEditEmailProfile);
        etEditNamaProfile = (EditText) findViewById(R.id.etEditNamaProfile);
        etEditAlamatProfile = (EditText) findViewById(R.id.etEditAlamatProfile);
        etEditNIKProfile = (EditText) findViewById(R.id.etEditNIKProfile);
        fabSendEditProfile = (FloatingActionButton) findViewById(R.id.fab_send_edit_profile);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        etEditNIKProfile.setText(getIntent().getStringExtra(Constant.KEY_NIK));
        etEditNamaProfile.setText(getIntent().getStringExtra(Constant.KEY_NAMA));
        etEditAlamatProfile.setText(getIntent().getStringExtra(Constant.KEY_ALAMAT));

        //mengambil uid
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("user").child(auth.getCurrentUser().getUid());

        if (auth.getCurrentUser() != null) {
            txtEditEmailProfile.setText(auth.getCurrentUser().getEmail());
        }

        fabSendEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (auth.getCurrentUser() != null) {
                    Log.d("Editprofile", auth.getCurrentUser().toString());
                    auth.getCurrentUser().updateEmail(txtEditEmailProfile.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intentChData = new Intent(EditProfile.this, Profile.class);
                                        UserModel data = new UserModel();
                                        data.setNik(etEditNIKProfile.getText().toString());
                                        data.setNama(etEditNamaProfile.getText().toString());
                                        data.setAlamat(etEditAlamatProfile.getText().toString());
                                        data.setEmail(txtEditEmailProfile.getText().toString());
                                        databaseReference.setValue(data);
                                        Toast.makeText(EditProfile.this, "Data berhasil diperbaharui", Toast.LENGTH_LONG).show();
                                        startActivity(intentChData);
                                    } else {
                                        Toast.makeText(EditProfile.this, "Gagal memperbaharui data", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }

            }
        });
    }

}
