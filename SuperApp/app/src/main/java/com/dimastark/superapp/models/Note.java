package com.dimastark.superapp.models;

import android.text.format.DateUtils;

import com.dimastark.superapp.App;
import com.dimastark.superapp.R;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Note implements Serializable {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    private static final Random random = new Random();
    private static Color[] possibleColors;
    private static int nextColorIndex;

    static {
        int[] allColors = App
                .getContext()
                .getResources()
                .getIntArray(R.array.favourite_colors);
        possibleColors = new Color[allColors.length];

        for (int i = 0; i < allColors.length; i++) {
            possibleColors[i] = new Color(allColors[i]);
        }
    }

    private String title;
    private String description;
    private Date createdAt;
    private Color color;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = Calendar.getInstance().getTime();
        this.color = possibleColors[nextColorIndex];

        updateColorIndex();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormattedCreatedAt() {
        return DateUtils.getRelativeTimeSpanString(
                getCreatedAt().getTime(),
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS).toString();
    }

    public Color getColor() {
        return color;
    }

    private static void updateColorIndex() {
        int nextInt = random.nextInt(possibleColors.length);

        while (nextInt == nextColorIndex) {
            nextInt = random.nextInt(possibleColors.length);
        }

        nextColorIndex = nextInt;
    }
}
