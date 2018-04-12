package com.dimastark.superapp.colorpicker;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.dimastark.superapp.R;
import com.dimastark.superapp.models.Palette;

public class ColorPicker extends RelativeLayout implements AdapterView.OnItemClickListener {
    private PaletteLayout paletteLayout;

    public ColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Palette getPalette() {
        return paletteLayout.getPalette();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FavoriteColorsAdapter.ViewHolder vh = (FavoriteColorsAdapter.ViewHolder) view.getTag();
        getPalette().getCurrentColor().setValue(vh.getColor());
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();

        paletteLayout = findViewById(R.id.palette_layout);

        GridView favorites = findViewById(R.id.favorite_colors);
        favorites.setOnItemClickListener(this);
        favorites.setAdapter(new FavoriteColorsAdapter((Activity) getContext()));
    }
}
