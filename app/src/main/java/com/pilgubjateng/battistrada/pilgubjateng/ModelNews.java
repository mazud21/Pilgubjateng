package com.pilgubjateng.battistrada.pilgubjateng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

class ModelNews {


    //Member variables representing the title and information about the sport
    private String title;
    private String info;
    private	final int imageResource;

    /**
     * Constructor for the ModelNews data model
     * @param title The name if the sport.
     * @param info Information about the sport.
     */
    public ModelNews(String title, String info, int	imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    /**
     * Gets the title of the sport
     * @return The title of the sport.
     */
    String getTitle() {

        return title;
    }
    /**
     * Gets the info about the sport
     * @return The info about the sport.
     */
    String getInfo() {

        return info;
    }

    public int	getImageResource()	{
        return	imageResource;
    }
}
