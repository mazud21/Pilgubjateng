package com.pilgubjateng.battistrada.pilgubjateng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Vote extends AppCompatActivity {

    Button btnPaslon1,btnPaslon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        btnPaslon1 = (Button)findViewById(R.id.vote_paslon1);
        btnPaslon2 = (Button)findViewById(R.id.vote_paslon2);

        btnPaslon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Terimakasih telah mengikuti Polling ini", Toast.LENGTH_SHORT).show();
            }
        });

        btnPaslon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Terimakasih telah mengikuti Polling ini", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
