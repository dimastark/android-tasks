package com.dimastark.superapp.models;

import android.graphics.Color;
import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Note implements Serializable {
    private static final Random random = new Random();
    private static int nextColorIndex;

    private static final int colorsCount;
    private static final int[] allPossibleColors;

    static {
        String[] allPossibleColorsInHex = new String[] {
                "#e53935", "#ec407a", "#7b1fa2", "#651fff", "#304ffe", "#2962ff",
                "#0091ea", "#00e5ff", "#4db6ac", "#00c853", "#64dd17", "#aeea00",
                "#ffff00", "#ffb300", "#f57c00", "#ff5722", "#9e9e9e", "#607d8b",
        };

        colorsCount = allPossibleColorsInHex.length;
        allPossibleColors = new int[colorsCount];
        nextColorIndex = random.nextInt(colorsCount);

        for (int i = 0; i < colorsCount; i++) {
            allPossibleColors[i] = Color.parseColor(allPossibleColorsInHex[i]);
        }
    }

    private String title;
    private String description;
    private Date createdAt;
    private int color;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = Calendar.getInstance().getTime();
        this.color = allPossibleColors[nextColorIndex];

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

    public int getColor() {
        return color;
    }

    private static void updateColorIndex() {
        int nextInt = random.nextInt(colorsCount);

        while (nextInt == nextColorIndex) {
            nextInt = random.nextInt(colorsCount);
        }

        nextColorIndex = nextInt;
    }
}
