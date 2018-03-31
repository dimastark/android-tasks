package com.dimastark.superapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.dimastark.superapp.utils.Observable;

public class ColorBlock implements Parcelable {
    private final float minHue;
    private final float maxHue;
    private final Color initialColor;

    private Observable<Color> currentColor;

    ColorBlock(float minHue, float maxHue) {
        this.minHue = minHue;
        this.maxHue = maxHue;

        initialColor = new Color((maxHue + minHue) / 2);
        currentColor = new Observable<>(initialColor);
    }

    ColorBlock(Parcel in) {
        this(in.readFloat(), in.readFloat());
    }

    public void reset() {
        currentColor.setValue(initialColor);
    }

    public boolean isCorrectColor(Color color) {
        return minHue <= color.hue() && color.hue() < maxHue
                && Color.MIN_SATURATION <= color.saturation()
                && color.saturation() <= Color.MAX_SATURATION
                && Color.MIN_VOLUME <= color.volume()
                && color.volume() <= Color.MAX_VOLUME;
    }

    public Observable<Color> getColor() {
        return currentColor;
    }

    public void setColor(Color newColor) {
        currentColor.setValue(newColor);
    }

    public static final Creator<ColorBlock> CREATOR = new Creator<ColorBlock>() {
        @Override
        public ColorBlock createFromParcel(Parcel in) {
            return new ColorBlock(in);
        }

        @Override
        public ColorBlock[] newArray(int size) {
            return new ColorBlock[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeFloat(minHue);
        parcel.writeFloat(maxHue);
    }
}
