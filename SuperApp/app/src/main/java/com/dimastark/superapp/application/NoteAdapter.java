package com.dimastark.superapp.application;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dimastark.superapp.R;
import com.dimastark.superapp.models.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private final List<Note> notes;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public NoteAdapter(List<Note> notes, OnItemClickListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.note_list_item, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.fill(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View leftBorder;
        TextView title;
        TextView createdAt;
        TextView description;

        ViewHolder(View forView) {
            super(forView);

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
}
