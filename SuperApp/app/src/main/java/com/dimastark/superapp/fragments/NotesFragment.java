package com.dimastark.superapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dimastark.superapp.R;
import com.dimastark.superapp.application.NoteAdapter;
import com.dimastark.superapp.models.Note;

import java.util.ArrayList;

public class NotesFragment extends Fragment implements
        View.OnClickListener,
        NoteAdapter.OnItemClickListener {

    private Context context;
    private NoteAdapter adapter;
    private ArrayList<Note> notes = new ArrayList<>();

    public interface OnNoteCreateListener {
        void onCreateClick();
    }

    public interface OnNoteClickListener {
        void onNoteClick(int id, Note note);
    }

    public static NotesFragment newInstance(ArrayList<Note> notes) {
        NotesFragment fragment = new NotesFragment();

        fragment.notes = notes;

        return fragment;
    }

    @Override
    public void onClick(View view) {
        ((OnNoteCreateListener)context).onCreateClick();
    }

    @Override
    public void onItemClick(View v, int position) {
        ((OnNoteClickListener)context).onNoteClick(position, notes.get(position));
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes_fragment, container, false);

        adapter = new NoteAdapter(notes, this);

        RecyclerView recyclerView = view.findViewById(R.id.notesList);
        recyclerView.setAdapter(adapter);

        Button createButton = view.findViewById(R.id.createButton);
        createButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }
}
