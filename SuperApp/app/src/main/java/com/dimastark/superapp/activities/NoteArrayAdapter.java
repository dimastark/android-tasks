package com.dimastark.superapp.activities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dimastark.superapp.R;
import com.dimastark.superapp.models.Note;

import java.util.List;

public class NoteArrayAdapter extends ArrayAdapter<Note> {
    private final Activity context;

    NoteArrayAdapter(Activity context, List<Note> notes) {
        super(context, R.layout.note_list_item, notes);

        this.context = context;
    }

    static class ViewHolder {
        View leftBorder;
        TextView title;
        TextView createdAt;
        TextView description;

        ViewHolder(View forView) {
            leftBorder = forView.findViewById(R.id.leftBorder);
            title = forView.findViewById(R.id.title);
            createdAt = forView.findViewById(R.id.createdAt);
            description = forView.findViewById(R.id.description);
        }

        void fill(Note note) {
            title.setText(note.getTitle());
            createdAt.setText(note.getFormattedCreatedAt());
            description.setText(note.getDescription());
            leftBorder.setBackgroundColor(note.getColor().asInt());
        }
    }

    @NonNull @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.note_list_item, parent, false);

            view.setTag(new ViewHolder(view));
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.fill(getItem(position));

        return view;
    }
}
