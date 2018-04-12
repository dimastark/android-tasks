package com.dimastark.superapp.application;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.dimastark.superapp.R;

import static android.graphics.Color.TRANSPARENT;

public class OptionsLayout extends DrawerLayout {
    private boolean isDrawerLocked;
    private boolean disallowIntercept;

    public OptionsLayout(Context context) {
        super(context);
    }

    public OptionsLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        selectDrawer();
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent ev) {
        return !disallowIntercept && super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setDrawerLockMode(int lockMode) {
        super.setDrawerLockMode(lockMode);
        disallowIntercept = (lockMode == LOCK_MODE_LOCKED_OPEN);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        selectDrawer();
    }

    public boolean getIsDrawerLocked() {
        return isDrawerLocked;
    }

    void selectDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        FrameLayout container = findViewById(R.id.fragment_container);

        drawer.closeDrawers();

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            container.setPadding(560, 0, 0, 0);
            drawer.setScrimColor(TRANSPARENT);
            isDrawerLocked = true;
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            container.setPadding(0, 0, 0, 0);
            isDrawerLocked = false;
        }
    }
}
