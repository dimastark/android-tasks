package com.dimastark.testapp.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class SomeViewGroup extends LinearLayout {
    private static final String TAG = "Dima/SomeViewGroup";

    public SomeViewGroup(Context context) {
        super(context);

        Log.d(TAG, "constructor(Context)");
    }

    public SomeViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Log.d(TAG, "constructor(Context, AttributeSet)");
    }

    public SomeViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.d(TAG, "constructor(Context, AttributeSet, int)");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        Log.d(TAG, String.format(
                "onLayout(changed=%s, left=%d, top=%d, right=%d, bottom=%d)",
                Boolean.toString(changed), l, t, r, b
        ));
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);

        Log.d(TAG, "onViewAdded()");
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);

        Log.d(TAG, "onViewRemoved(View)");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        Log.d(TAG, "onAttachedToWindow()");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        Log.d(TAG, "onDetachedFromWindow()");
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.d(TAG, String.format("onConfigurationChanged(orientation=%d)", newConfig.orientation));
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);

        Log.d(TAG, "onScreenStateChanged()");
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);

        Log.d(TAG, "onDrawForeground()");
    }
}
