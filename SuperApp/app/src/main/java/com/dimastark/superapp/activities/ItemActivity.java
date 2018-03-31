package com.dimastark.superapp.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dimastark.superapp.R;
import com.dimastark.superapp.colorpicker.PaletteLayout;
import com.dimastark.superapp.models.Color;
import com.dimastark.superapp.models.Note;
import com.dimastark.superapp.models.Palette;
import com.dimastark.superapp.utils.Observer;

public class ItemActivity extends AppCompatActivity {
    private TextView title;
    private TextView description;
    private Palette palette;

    class CreateNoteListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ItemActivity.this, MainActivity.class);
            Bundle bundle = getIntent().getExtras();

            if (bundle != null)
                intent.putExtra(Note.ID, bundle.getInt(Note.ID));

            intent.putExtra(Note.TITLE, title.getText().toString());
            intent.putExtra(Note.DESCRIPTION, description.getText().toString());
            intent.putExtra(Note.COLOR, palette.getCurrentColor().getValue().asInt());

            startActivity(intent);
        }
    }

    class ChangeColorObserver implements Observer<Color> {
        @Override
        public void onChange(Color newValue) {
            setColor(newValue);
        }
    }

    void setColor(Color color) {
        ActionBar bar = getSupportActionBar();

        if (bar != null && color != null) {
            bar.setBackgroundDrawable(new ColorDrawable(color.asInt()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item);

        title = findViewById(R.id.title_input);
        description = findViewById(R.id.description_input);
        palette = ((PaletteLayout) findViewById(R.id.palette_layout)).getPalette();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            title.setText(bundle.getString(Note.TITLE));
            description.setText(bundle.getString(Note.DESCRIPTION));
            Color color = new Color(bundle.getInt(Note.COLOR));
            palette.getCurrentColor().setValue(color);
            setColor(color);
        }

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new CreateNoteListener());
        palette.getCurrentColor().addObserver(new ChangeColorObserver());
    }
}
