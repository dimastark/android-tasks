package com.dimastark.testapp.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

public class SomeView extends AppCompatTextView {
    private static final String TAG = "Dima/SomeView";

    public SomeView(Context context) {
        super(context);

        Log.d(TAG, "constructor()");
    }

    public SomeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.d(TAG, "constructor(Context, AttributeSet)");
    }

    public SomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.d(TAG, "constructor(Context, AttributeSet, int)");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Log.d(TAG, String.format(
                "onLayout(changed=%s, left=%d, top=%d, right=%d, bottom=%d)",
                Boolean.toString(changed), left, top, right, bottom
        ));
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.d(TAG, String.format("onConfigurationChanged(orientation=%s)", newConfig.orientation));
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Log.d(TAG, "onSaveInstanceState()");

        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);

        Log.d(TAG, "onRestoreInstanceState()");
    }

    @Override
    public boolean onPreDraw() {
        Log.d(TAG, "onPreDraw()");

        return super.onPreDraw();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        Log.d(TAG, "onAttachedToWindow()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "onDraw()");
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        Log.d(TAG, String.format("onFocusChanged(focused=%s)", Boolean.toString(focused)));
    }
}
