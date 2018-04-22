package com.pilgubjateng.battistrada.pilgubjateng;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


import com.pilgubjateng.battistrada.pilgubjateng.slider.FragmentSlider;
import com.pilgubjateng.battistrada.pilgubjateng.slider.SliderIndicator;
import com.pilgubjateng.battistrada.pilgubjateng.slider.SliderPagerAdapter;
import com.pilgubjateng.battistrada.pilgubjateng.slider.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mhr on 25/03/2018.
 */

public class News extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        WebView web = (WebView) findViewById(R.id.web_view);
        web.loadUrl("http://jateng.kpu.go.id/berita-2/");
        web.setWebViewClient(new WebViewClient());
    }
}
