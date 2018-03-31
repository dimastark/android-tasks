package com.dimastark.superapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.dimastark.superapp.App;
import com.dimastark.superapp.R;

public class Color implements Parcelable {
    public static final float MAX_HUE = 360;
    public static final float MIN_HUE = 0;

    public static final float MAX_SATURATION = 1;
    public static final float MIN_SATURATION = 0;

    public static final float MAX_VOLUME = 1;
    public static final float MIN_VOLUME = 0;

    public static final Color[] FAVOURITES;

    static {
        int[] allColors = App
                .getContext()
                .getResources()
                .getIntArray(R.array.favourite_colors);

        FAVOURITES = new Color[allColors.length];

        for (int i = 0; i < allColors.length; i++)
            FAVOURITES[i] = new Color(allColors[i]);
    }

    private int color;

    private float[] hsv;

    private int red;
    private int green;
    private int blue;

    public Color(int color) {
        this.color = color;

        red = android.graphics.Color.red(color);
        green = android.graphics.Color.green(color);
        blue = android.graphics.Color.blue(color);

        float[] hsv = new float[3];
        android.graphics.Color.colorToHSV(color, hsv);

        this.hsv = hsv;
    }

    public Color(int r, int g, int b) {
        this(android.graphics.Color.rgb(r, g, b));
    }

    public Color(float h, float s, float v) {
        this(android.graphics.Color.HSVToColor(new float[] { h, s, v }));
    }

    public Color(float h) {
        this(h, MAX_SATURATION, MAX_VOLUME);
    }

    protected Color(Parcel in) {
        this(in.readInt());
    }

    public Color addHue(float delta) {
        return new Color(hsv[0] + delta, hsv[1], hsv[2]);
    }

    public Color addVolume(float delta) {
        return new Color(hsv[0], hsv[1], hsv[2] + delta);
    }

    public float hue() {
        return hsv[0];
    }

    public float volume() {
        return hsv[1];
    }

    public float saturation() {
        return hsv[2];
    }

    public int red() {
        return red;
    }

    public int green() {
        return green;
    }

    public int blue() {
        return blue;
    }

    public int asInt() {
        return color;
    }

    public static final Creator<Color> CREATOR = new Creator<Color>() {
        @Override
        public Color createFromParcel(Parcel in) {
            return new Color(in);
        }

        @Override
        public Color[] newArray(int size) {
            return new Color[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(color);
    }
}
