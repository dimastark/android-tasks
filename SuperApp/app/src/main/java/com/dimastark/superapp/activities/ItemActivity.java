package com.dimastark.superapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dimastark.superapp.R;
import com.dimastark.superapp.models.Note;

public class ItemActivity extends AppCompatActivity {
    class CreateNoteListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Bundle bundle = getIntent().getExtras();
            Intent intent = new Intent(ItemActivity.this, MainActivity.class);

            if (bundle != null) {
                intent.putExtra(Note.ID, bundle.getInt(Note.ID));
            }

            TextView title = findViewById(R.id.titleInput);
            intent.putExtra(Note.TITLE, title.getText().toString());

            TextView description = findViewById(R.id.descriptionInput);
            intent.putExtra(Note.DESCRIPTION, description.getText().toString());

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            TextView title = findViewById(R.id.titleInput);
            title.setText(bundle.getString(Note.TITLE));

            TextView description = findViewById(R.id.descriptionInput);
            description.setText(bundle.getString(Note.DESCRIPTION));
        }

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new CreateNoteListener());
    }
}
