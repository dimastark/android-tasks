package com.dimastark.superapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.dimastark.superapp.R;
import com.dimastark.superapp.models.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String STATE_NOTES = "notes";
    public static final int DEFAULT_ID = -1;

    private ArrayList<Note> notes = new ArrayList<>();

    class CreateNoteListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ItemActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }

    class UpdateNoteListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, ItemActivity.class);

            Note note  = (Note) adapterView.getItemAtPosition(i);

            intent.putExtra(Note.ID, i);
            intent.putExtra(Note.TITLE, note.getTitle());
            intent.putExtra(Note.DESCRIPTION, note.getDescription());

            MainActivity.this.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.notesList);
        listView.setAdapter(new NoteArrayAdapter(this, notes));
        listView.setOnItemClickListener(new UpdateNoteListener());

        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new CreateNoteListener());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        checkCreateOrUpdateNote(intent.getExtras());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(STATE_NOTES, notes);

        super.onSaveInstanceState(outState);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        notes = (ArrayList<Note>) savedInstanceState.getSerializable(STATE_NOTES);
    }

    void checkCreateOrUpdateNote(Bundle bundle) {
        if (bundle != null) {
            int id = bundle.getInt(Note.ID, DEFAULT_ID);

            String title = bundle.getString(Note.TITLE);
            String description = bundle.getString(Note.DESCRIPTION);

            if (id != DEFAULT_ID) {
                Note note = notes.get(id);

                note.setTitle(title);
                note.setDescription(description);
            } else {
                notes.add(new Note(title, description));
            }
        }
    }
}
