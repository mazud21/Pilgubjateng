package com.pilgubjateng.battistrada.pilgubjateng;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupMenu;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pilgubjateng.battistrada.pilgubjateng.adapter.AdapterImage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6;
    //Button buttonVote;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

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

        cardView1 = (CardView)findViewById(R.id.cv_calon);
        cardView2 = (CardView)findViewById(R.id.cv_news);
        cardView3 = (CardView)findViewById(R.id.cv_memilih);
        cardView4 = (CardView)findViewById(R.id.cv_aspirasi);
        cardView5 = (CardView)findViewById(R.id.cv_isu);
        cardView6 = (CardView)findViewById(R.id.cv_about);
        //buttonVote = (Button)findViewById(R.id.btnVote);

        //init
        auth = FirebaseAuth.getInstance();

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Calon.class);
                startActivity(intent);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, News.class);
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Edukasi.class);
                startActivity(intent);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Aspirasi.class);
                startActivity(intent);
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Statistik.class);
                startActivity(intent);
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, About.class);
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
        init();
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

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new AdapterImage(MainActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 10000, 5000);
    }
}
