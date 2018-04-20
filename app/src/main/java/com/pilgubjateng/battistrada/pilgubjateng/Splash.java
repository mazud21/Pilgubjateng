package com.pilgubjateng.battistrada.pilgubjateng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splash extends AppCompatActivity {

    public LinearLayout linearSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        linearSplash = (LinearLayout) findViewById(R.id.linearSplash);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.welcome_transisi);

        linearSplash.startAnimation(myanim);
        final Intent i = new Intent(this,SignIn.class);
        Thread timer = new Thread()
        {
            public void run(){
                try {
                    sleep(3000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
