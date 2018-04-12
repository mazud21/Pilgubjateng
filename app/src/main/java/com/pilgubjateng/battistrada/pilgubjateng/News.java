package com.pilgubjateng.battistrada.pilgubjateng;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by mhr on 25/03/2018.
 */

public class News extends AppCompatActivity {

    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<ModelNews> mSportsData;
    private NewsAdapter mAdapter;

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
