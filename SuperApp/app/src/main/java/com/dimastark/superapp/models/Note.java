package com.dimastark.superapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Note implements Parcelable {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String COLOR = "color";

    private static final Random random = new Random();

    private String title;
    private String description;
    private Date createdAt;
    private Color color;

    public Note(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.createdAt = Calendar.getInstance().getTime();
        this.color = color;

        if (color == null)
            this.color = Color.FAVOURITES[random.nextInt(Color.FAVOURITES.length)];
    }

    private Note(Parcel in) {
        title = in.readString();
        description = in.readString();
        createdAt = new Date(in.readLong());
        color = new Color(in.readInt());
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

    public void setColor(Color color) {
        this.color = color;
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

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeLong(createdAt.getTime());
        parcel.writeInt(color.asInt());
    }
}
