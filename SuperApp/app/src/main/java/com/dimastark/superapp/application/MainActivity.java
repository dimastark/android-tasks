package com.dimastark.superapp.application;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.dimastark.superapp.R;
import com.dimastark.superapp.fragments.NoteFragment;
import com.dimastark.superapp.fragments.NotesFragment;
import com.dimastark.superapp.models.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        NotesFragment.OnNoteCreateListener,
        NotesFragment.OnNoteClickListener,
        NoteFragment.OnSaveNoteListener {

    private FragmentHistoryManager history;

    private ArrayList<Note> notes = new ArrayList<>();
    private NotesFragment list;

    @Override
    public void onCreateClick() {
        history.push(NoteFragment.newInstance());
    }

    @Override
    public void onNoteClick(int id, Note note) {
        history.push(NoteFragment.newInstance(id, note));
    }

    @Override
    public void onNoteUpdate(int id, Note note) {
        notes.set(id, note);
    }

    @Override
    public void onNoteCreate(Note note) {
        notes.add(note);
    }

    @Override
    public void onSaveClick() {
        list.notifyDataSetChanged();
        history.pop(list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        history = new FragmentHistoryManager(getSupportFragmentManager());

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            list = NotesFragment.newInstance(notes);
            history.replace(list);
        }
    }

    @Override
    public void onBackPressed() {
        OptionsLayout options = findViewById(R.id.drawer_layout);

        if (options.isDrawerOpen(GravityCompat.START) && !options.getIsDrawerLocked())
            options.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
