package com.pilgubjateng.battistrada.pilgubjateng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Isu extends AppCompatActivity {

    Button btnkirim,btnreset;
    EditText nama,email,pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isu);

        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Issue");

        nama = (EditText)findViewById(R.id.et_nama_pesan);
        email = (EditText)findViewById(R.id.et_email_pesan);
        pesan = (EditText)findViewById(R.id.textArea_pesan);
        btnkirim = (Button)findViewById(R.id.btn_kirim_pesan);
        btnreset = (Button)findViewById(R.id.btn_reset_pesan);

        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = nama.getText().toString().trim();
                String e = email.getText().toString().trim();
                String p = pesan.getText().toString().trim();

                if (TextUtils.isEmpty(n) && TextUtils.isEmpty(e) && TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplication(), "Nama, Email dan Pesan harus diisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(n) && TextUtils.isEmpty(e)) {
                    Toast.makeText(getApplication(), "Nama dan Email harus diisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(n)  && TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplication(), "Nama dan Pesan harus diisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(e)  && TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplication(), "Email dan Pesan harus diisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(e)) {
                    Toast.makeText(getApplication(), "Email harus diisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplication(), "Pesan harus diisi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(n)) {
                    Toast.makeText(getApplication(), "Nama harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Pesan anda terkirim", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama.setText("");
                email.setText("");
                pesan.setText("");
            }
        });
    }
}
