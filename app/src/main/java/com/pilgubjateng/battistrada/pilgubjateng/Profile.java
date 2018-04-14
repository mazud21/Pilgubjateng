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

import java.text.SimpleDateFormat;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "";
    private TextView txtProfile;
    private EditText editPass;
    private Button btnChange, btnLogout, btnDelete;
    private ProgressBar progressBar;
    private FloatingActionButton fabEdit;
    private LinearLayout linearEdit;
    private Context context;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    LinearLayout activity_profile;

    Toast toast;
    Snackbar snackbar;

    private FirebaseAuth auth;
    private FirebaseUser user;

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
        //btnDelete = (Button) findViewById(R.id.btnDelete);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        linearEdit = (LinearLayout) findViewById(R.id.linearEdit);

        fabEdit = (FloatingActionButton) findViewById(R.id.fab_edit);

        //btnDelete.setOnClickListener(this);

        //init
        auth = FirebaseAuth.getInstance();

        //Check if already session
        if (auth.getCurrentUser() != null) {
            txtProfile.setText("" + auth.getCurrentUser().getEmail());
        }

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(Profile.this,fabEdit);
                // Inflating menu using xml file
                popup.getMenuInflater().inflate(R.menu.menu_edit_profile, popup.getMenu());
                // registering OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit_data:
                                /*
                                PopupMenu popup = new PopupMenu(Profile.this,fabEdit);

                                // Inflating menu using xml file
                                popup.getMenuInflater().inflate(R.menu.menu_edit_profile, popup.getMenu());
                                */
                                //Intent intentChData = new Intent(Profile.this, ChangeData.class);
                                //startActivity(intentChData);
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Profile.this, MainActivity.class));
        finish();
    }

    /*
    private void deleteUser() {

    }
    */

    @Override
    public void onClick(View v) {

        String password = editPass.getText().toString().trim();
        if (v.getId() == R.id.btnChange){
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplication(), "Enter your new Password", Toast.LENGTH_SHORT).show();
            } else {
                user = auth.getCurrentUser();
                if (user != null) {
                    user.updatePassword(password)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Profile.this, "Password Changed !", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Profile.this, "Failed to Change Password", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }
            }
        }
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

//assert user != null;
/*        user.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User password updated.");
                        }
                    }
                });
*/