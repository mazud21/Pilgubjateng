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

    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<ModelNews> mSportsData;
    private NewsAdapter mAdapter;

    private SliderPagerAdapter SAdapter;
    private SliderIndicator mIndicator;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    ImageView newsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Berita PILKADA");

        //kondisi jika akan melakukan back act
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);
        setupSlider();

        /*

        newsImage = (ImageView)findViewById(R.id.newsImage);

        newsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(News.this, NewsDetails.class);
                startActivity(intent);
            }
        });

        */

        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize the ArrayLIst that will contain the data
        mSportsData = new ArrayList<>();

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new NewsAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData();

    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://www.kpu.go.id/./application/modules/post/images/c36f54c1a0c399851b90938249e4d890.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.kpu.go.id/./application/modules/post/images/2b75b28bbec51ce56d16d4f4791c7690.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.kpu.go.id/./application/modules/post/images/6a6d99a7806cbaf29407526b0a6f09c1.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.kpu.go.id/./application/modules/post/images/f00d320c4bebc88822e91472d9cfe1c8.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.kpu.go.id/./application/modules/post/images/fd92b9ce414f38365896b95c055a6ed9.JPG"));

        SAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(SAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.indicator_circle2);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }


    /**
     * Method for initializing the sports data from resources.
     */
    private void initializeData() {
        //Get the resources from the XML file
        String[] sportsList = getResources().getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources().getStringArray(R.array.sports_info);
        TypedArray sportsImageResources=getResources().obtainTypedArray(R.array.sports_images);


        //Clear the existing data (to avoid duplication)
        mSportsData.clear();

        //Create the ArrayList of Sports objects with the titles and information about each sport
        for (int i = 0; i < sportsList.length; i++) {
            mSportsData.add(new ModelNews(sportsList[i], sportsInfo[i],
                    sportsImageResources.getResourceId(i, 0)));

        }

        sportsImageResources.recycle();
        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged();
    }

}
