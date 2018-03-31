package com.dimastark.superapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.dimastark.superapp.utils.Observable;

import java.util.ArrayList;
import java.util.List;

public class Palette implements Parcelable {
    private final static int DEFAULT_CAPACITY = 16;

    private List<ColorBlock> colors;
    private Observable<Color> currentColor;

    public Palette() {
        colors = new ArrayList<>();

        int[] borders = getColorsRange();

        for (int i = 1; i < borders.length; ++i)
            colors.add(new ColorBlock(borders[i - 1], borders[i]));

        currentColor = new Observable<>(new Color(255, 255, 255));
    }

    private Palette(Parcel in) {
        colors = new ArrayList<>();

        int size = in.readInt();
        for (int i = 0; i < size; i++)
            colors.add(new ColorBlock(in));

        currentColor = new Observable<>(new Color(in.readInt()));
    }

    public Observable<Color> getCurrentColor() {
        return currentColor;
    }

    public int size() {
        return colors.size();
    }

    public Iterable<ColorBlock> cells() {
        return colors;
    }

    private static int[] getColorsRange() {
        int capacity = DEFAULT_CAPACITY;
        float start = Color.MIN_HUE;
        float end = Color.MAX_HUE - 1;

        int[] interpolation = new int[capacity + 1];

        float step = (end - start) / (capacity + 1f);
        float current = start;

        for (int i = 0; i < interpolation.length - 1; i++, current += step)
            interpolation[i] = (int) current;

        interpolation[interpolation.length - 1] = (int) end;

        return interpolation;
    }

    public static final Creator<Palette> CREATOR = new Creator<Palette>() {
        @Override
        public Palette createFromParcel(Parcel in) {
            return new Palette(in);
        }

        @Override
        public Palette[] newArray(int size) {
            return new Palette[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(colors.size());

        for (int i = 0; i < colors.size(); i++)
            parcel.writeParcelable(colors.get(i), flags);

        parcel.writeInt(currentColor.getValue().asInt());
    }
}
