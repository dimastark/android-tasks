package com.dimastark.superapp.colorpicker;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.dimastark.superapp.R;
import com.dimastark.superapp.models.Color;
import com.dimastark.superapp.models.ColorBlock;
import com.dimastark.superapp.models.Palette;
import com.dimastark.superapp.utils.Observable;

import icepick.Icepick;
import icepick.State;

public class PaletteLayout extends HorizontalScrollView {
    private final ScrollColorEditor colorEditor;

    private LinearLayout buttonsLayout;
    private GestureDetector gestureDetector;
    @State Palette palette;

    public PaletteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        colorEditor = new ScrollColorEditor();
        gestureDetector = new GestureDetector(context, colorEditor);
        gestureDetector.setIsLongpressEnabled(false);
    }

    private void updateContent() {
        buttonsLayout.removeAllViews();

        Observable<Color> selectedColor = palette.getCurrentColor();
        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (ColorBlock block : palette.cells()) {
            SelectColorButton button = (SelectColorButton) inflater.inflate(
                    R.layout.palette_button, buttonsLayout, false
            );
            button.connectToBlock(block, selectedColor, colorEditor);

            buttonsLayout.addView(button);
        }
    }

    public Palette getPalette() {
        return palette;
    }

    public void setPalette(Palette palette) {
        this.palette = palette;

        updateContent();
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();

        buttonsLayout = findViewById(R.id.gradient);
        setPalette(new Palette());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        if (colorEditor.enabled()) {
            if (event.getAction() == MotionEvent.ACTION_UP)
                colorEditor.stop();

            getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }

        return super.onInterceptTouchEvent(event);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return Icepick.saveInstanceState(this, super.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
    }
}
