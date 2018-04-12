package com.pilgubjateng.battistrada.pilgubjateng;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4;
    //Button buttonVote;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView1 = (ImageView)findViewById(R.id.calon);
        imageView2 = (ImageView)findViewById(R.id.cara);
        imageView3 = (ImageView)findViewById(R.id.issue);
        imageView4 = (ImageView)findViewById(R.id.ask);
        //buttonVote = (Button)findViewById(R.id.btnVote);

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
                Intent intent = new Intent(MainActivity.this, Isu.class);
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
    }

}
