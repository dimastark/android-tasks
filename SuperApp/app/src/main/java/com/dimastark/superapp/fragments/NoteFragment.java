package com.dimastark.superapp.fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dimastark.superapp.R;
import com.dimastark.superapp.colorpicker.PaletteLayout;
import com.dimastark.superapp.models.Color;
import com.dimastark.superapp.models.Note;
import com.dimastark.superapp.models.Palette;
import com.dimastark.superapp.utils.Observer;

public class NoteFragment extends Fragment implements View.OnClickListener, Observer<Color> {
    private Context context;
    private ViewHolder viewHolder;

    public interface OnSaveNoteListener {
        void onNoteUpdate(int id, Note note);
        void onNoteCreate(Note note);
        void onSaveClick();
    }

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    public static NoteFragment newInstance(int id, Note note) {
        NoteFragment fragment = newInstance();

        Bundle arguments = new Bundle();
        arguments.putInt(Note.ID, id);
        arguments.putParcelable(Note.NOTE, note);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = getArguments();
        OnSaveNoteListener listener = (OnSaveNoteListener) context;

        Note note = new Note(
                viewHolder.title.getText().toString(),
                viewHolder.description.getText().toString(),
                viewHolder.palette.getCurrentColor().getValue()
        );

        if (bundle != null)
            listener.onNoteUpdate(bundle.getInt(Note.ID), note);
        else
            listener.onNoteCreate(note);

        listener.onSaveClick();
    }

    @Override
    public void update(Color newValue) {
        viewHolder.setColor(newValue);
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        viewHolder = new ViewHolder(view);

        Bundle bundle = getArguments();

        if (bundle != null) {
            Note note = bundle.getParcelable(Note.NOTE);
            viewHolder.fill(note);
        }

        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        viewHolder.palette.getCurrentColor().register(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ViewHolder.bar = ((AppCompatActivity) context).getSupportActionBar();
        this.context = context;
    }

    @Override
    public void onDestroyView() {
        Color color = new Color(getResources().getColor(R.color.colorPrimary));
        viewHolder.setColor(color);

        super.onDestroyView();
    }

    static class ViewHolder {
        static ActionBar bar;
        TextView title;
        TextView description;
        Palette palette;

        ViewHolder(View forView) {
            title = forView.findViewById(R.id.title_input);
            description = forView.findViewById(R.id.description_input);
            palette = ((PaletteLayout) forView.findViewById(R.id.palette_layout)).getPalette();
        }

        void fill(Note note) {
            title.setText(note.getTitle());
            description.setText(note.getDescription());
            palette.getCurrentColor().setValue(note.getColor());
            setColor(note.getColor());
        }

        void setColor(Color color) {
            if (bar != null && color != null)
                bar.setBackgroundDrawable(new ColorDrawable(color.asInt()));
        }
    }
}
