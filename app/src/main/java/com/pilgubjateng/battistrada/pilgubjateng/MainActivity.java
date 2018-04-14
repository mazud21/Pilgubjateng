package com.pilgubjateng.battistrada.pilgubjateng;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4;
    //Button buttonVote;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView1 = (ImageView) findViewById(R.id.calon);
        imageView2 = (ImageView) findViewById(R.id.cara);
        imageView3 = (ImageView) findViewById(R.id.issue);
        imageView4 = (ImageView) findViewById(R.id.ask);
        //buttonVote = (Button)findViewById(R.id.btnVote);

        //init
        auth = FirebaseAuth.getInstance();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Calon.class);
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaraMemilih.class);
                startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Aspirasi.class);
                startActivity(intent);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, News.class);
                startActivity(intent);
            }
        });
/*        buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Vote.class);
                startActivity(intent);
            }
        });
*/
        //menampilkan bottom nav
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.act_home:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.date:
                        //datePicker
                        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                        showDateDialog();

                        break;
                    case R.id.more:
                        PopupMenu popup = new PopupMenu(MainActivity.this, bottomNavigationView.findViewById(R.id.more));
                        // Inflating menu using xml file
                        popup.getMenuInflater().inflate(R.menu.menu_more, popup.getMenu());
                        // registering OnMenuItemClickListener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.profile_more:
                                        Intent intentProfile = new Intent(MainActivity.this, Profile.class);
                                        startActivity(intentProfile);
                                        break;
                                    case R.id.about_more:
                                        Intent intentAbout = new Intent(MainActivity.this, About.class);
                                        startActivity(intentAbout);
                                        break;
                                    case R.id.logout_more:
                                        auth.signOut();
                                        if (auth.getCurrentUser() == null) {
                                            startActivity(new Intent(MainActivity.this, SignIn.class));
                                            finish();
                                        }
                                        break;
                                }
                                return true;
                            }
                        });
                        popup.show();
                        //Intent intentProfile = new Intent(MainActivity.this, Profile.class);
                        //startActivity(intentProfile);
                        break;
                }
                return true;
            }
        });
    }

    //datepicker class
    private void showDateDialog() {

        //to get current date
        Calendar newCalendar = Calendar.getInstance();

        //inisialisasi date daialog
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                Calendar newDate = Calendar.getInstance();
                //Set Calendar untuk menampung tanggal yang dipilih
                newDate.set(year, monthOfYear, dayOfMonth);

                //Update TextView dengan tanggal yang kita pilih
                //tvDateResult.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
