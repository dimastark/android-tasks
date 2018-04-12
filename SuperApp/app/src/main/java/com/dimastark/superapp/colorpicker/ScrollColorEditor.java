package com.dimastark.superapp.colorpicker;

import android.content.Context;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.dimastark.superapp.application.App;
import com.dimastark.superapp.models.Color;
import com.dimastark.superapp.models.ColorBlock;

class ScrollColorEditor extends GestureDetector.SimpleOnGestureListener {
    private static final int VIBRATION_DURATION = 100;
    private static final float HUE_EDIT_SCALE = 1f / 15;
    private static final float VOLUME_EDIT_SCALE = 1f / 300;

    private ColorBlock editCell;
    private Color editStartColor;
    private Vibrator vibrator;
    private boolean isVerticalOverflow;
    private boolean isHorizontalOverflow;

    ScrollColorEditor() {
        this.vibrator = (Vibrator) App.getContext().getSystemService(Context.VIBRATOR_SERVICE);
    }

    public boolean enabled() {
        return editCell != null;
    }

    public void start(ColorBlock cell) {
        editCell = cell;
        editStartColor = editCell.getColor().getValue();
        vibrator.vibrate(VIBRATION_DURATION);

        isVerticalOverflow = false;
        isHorizontalOverflow = false;
    }

    public void stop() {
        editCell = null;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (!enabled() || e1 == null || e2 == null) {
            return true;
        }

        float x1 = e1.getX();
        float y1 = e1.getY();
        float x2 = e2.getX();
        float y2 = e2.getY();

        Color nextColor = editStartColor.addHue((x2 - x1) * HUE_EDIT_SCALE);
        isHorizontalOverflow = tryApplyColor(nextColor, !isHorizontalOverflow);

        nextColor = nextColor.addVolume((y1 - y2) * VOLUME_EDIT_SCALE);
        isVerticalOverflow = tryApplyColor(nextColor, !isVerticalOverflow);

        editCell.setColor(nextColor);

        return true;
    }

    private boolean tryApplyColor(Color color, boolean vibrate) {
        if (editCell.isCorrectColor(color)) {
            return false;
        }

        if (vibrate)
            vibrator.vibrate(VIBRATION_DURATION);

        return true;
    }
}
