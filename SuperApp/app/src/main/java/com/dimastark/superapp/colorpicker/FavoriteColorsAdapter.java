package com.dimastark.superapp.colorpicker;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.dimastark.superapp.R;
import com.dimastark.superapp.models.Color;

public class FavoriteColorsAdapter extends ArrayAdapter<Color> {
    private final Activity context;

    FavoriteColorsAdapter(Activity context) {
        super(context, R.layout.color_item, Color.FAVOURITES);

        this.context = context;
    }

    static class ViewHolder {
        ImageView imageView;
        Color color;

        ViewHolder(View forView) {
            imageView = forView.findViewById(R.id.image_view);
        }

        void fill(Color color) {
            this.color = color;
            GradientDrawable drawable = (GradientDrawable) imageView.getDrawable();
            drawable.setColor(color.asInt());
        }

        Color getColor() {
            return color;
        }
    }

    @NonNull @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.color_item, parent, false);

            view.setTag(new ViewHolder(view));
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.fill(getItem(position));

        return view;
    }
}
