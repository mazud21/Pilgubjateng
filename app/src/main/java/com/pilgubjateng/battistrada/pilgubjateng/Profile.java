package com.pilgubjateng.battistrada.pilgubjateng;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pilgubjateng.battistrada.pilgubjateng.model.UserModel;
import com.pilgubjateng.battistrada.pilgubjateng.model.UserModelData;
import com.pilgubjateng.battistrada.pilgubjateng.util.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class Profile extends AppCompatActivity{

    private static final String TAG = "";
    private TextView txtProfile, txtNik, txtNama, txtAlamat;
    private EditText editPass;
    private Button btnChange;
    private ProgressBar progressBar;
    private FloatingActionButton fabEdit;
    private LinearLayout linearEdit;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //View
        txtProfile = (TextView) findViewById(R.id.tvEmailProfile);
        editPass = (EditText) findViewById(R.id.edit_password_profile);
        btnChange = (Button) findViewById(R.id.btnChange);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //linearEdit = (LinearLayout) findViewById(R.id.linearEdit);

        fabEdit = (FloatingActionButton) findViewById(R.id.fab_edit);
        txtNik = findViewById(R.id.tvNIKProfile);
        txtNama = findViewById(R.id.tvNamaProfile);
        txtAlamat = findViewById(R.id.tvAlamatProfile);

        //init
        auth = FirebaseAuth.getInstance();

        //Check if already session
        if (auth.getCurrentUser() != null) {
            txtProfile.setText("" + auth.getCurrentUser().getEmail());
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user").child(auth.getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectUserData((Map<String, Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(Profile.this, fabEdit);
                // Inflating menu using xml file
                popup.getMenuInflater().inflate(R.menu.menu_edit_profile, popup.getMenu());
                // registering OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_data:
                                Intent intentChData = new Intent(Profile.this, EditProfile.class);
                                intentChData.putExtra(Constant.KEY_NIK, txtNik.getText().toString());
                                intentChData.putExtra(Constant.KEY_NAMA, txtNama.getText().toString());
                                intentChData.putExtra(Constant.KEY_ALAMAT, txtAlamat.getText().toString());
                                startActivity(intentChData);
                                break;
                            case R.id.edit_password:
                                Intent intentChPass = new Intent(Profile.this, ValidateChangePassword.class);
                                startActivity(intentChPass);
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    //load/show data user
    private void collectUserData(Map<String, Object> users) {

        ArrayList<UserModel> data = new ArrayList<>();

        //iterate through each user, ignoring their UID
        UserModelData da = new UserModelData();
        try {
            for (int i = 0; i < users.size(); i++) {
                da.setNik(users.get(Constant.KEY_NIK).toString());
                da.setNama(users.get(Constant.KEY_NAMA).toString());
                //da.setEmail(users.get(Constant.KEY_EMAIL).toString());
                da.setAlamat(users.get(Constant.KEY_ALAMAT).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtNik.setText(da.getNik());
        txtNama.setText(da.getNama());
        txtAlamat.setText(da.getAlamat());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Profile.this, MainActivity.class));
        finish();
    }
}

/*        if (v.getId() == R.id.btnDelete) {
            user = auth.getCurrentUser();
            progressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "ingreso a deleteAccount");
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG,"OK! Works fine!");
                            startActivity(new Intent(Profile.this, SignIn.class));
                            finish();
                        } else {
                            Log.w(TAG,"Something is wrong!");
                        }
                    }
                });
            }
*/