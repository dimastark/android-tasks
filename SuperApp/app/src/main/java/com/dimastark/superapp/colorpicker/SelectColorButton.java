package com.dimastark.superapp.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.dimastark.superapp.models.Color;
import com.dimastark.superapp.models.ColorBlock;
import com.dimastark.superapp.utils.Observable;
import com.dimastark.superapp.utils.Observer;

public class SelectColorButton extends AppCompatButton implements Observer<Color> {
    private ColorBlock cell;
    private ScrollColorEditor editor;
    private GestureDetector gestureDetector;
    private boolean isConnected = false;
    private Observable<Color> selectedColor;

    public SelectColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        gestureDetector = new GestureDetector(context, new OnGestureListener());
    }

    public Color getColor() {
        return cell.getColor().getValue();
    }

    private void setColor(Color color) {
        ((GradientDrawable) getBackground()).setColor(color.asInt());
    }

    void connectToBlock(ColorBlock cell, Observable<Color> selectedColor, ScrollColorEditor editor) {
        if (isConnected)
            throw new IllegalStateException("Trying to connect a button that is already connected.");

        this.cell = cell;
        this.selectedColor = selectedColor;
        this.editor = editor;

        cell.getColor().register(this);
        update(cell.getColor().getValue());

        isConnected = true;
    }

    @Override @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            this.setPressed(false);
            this.setSelected(false);
        }

        if (isConnected)
            gestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public void update(Color newValue) {
        setColor(newValue);
    }

    private class OnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            SelectColorButton.this.setPressed(true);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            cell.reset();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            editor.start(cell);
            SelectColorButton.this.setSelected(true);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            selectedColor.setValue(cell.getColor().getValue());
            return true;
        }
    }
}
