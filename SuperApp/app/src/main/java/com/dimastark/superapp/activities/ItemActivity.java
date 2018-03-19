package com.dimastark.superapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dimastark.superapp.R;

public class ItemActivity extends AppCompatActivity {
    class CreateNoteListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView title = findViewById(R.id.titleInput);
            TextView description = findViewById(R.id.descriptionInput);

            Intent intent = new Intent(ItemActivity.this, MainActivity.class);
            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                intent.putExtra(MainActivity.ID, bundle.getInt(MainActivity.ID));
            }

            intent.putExtra(MainActivity.TITLE, title.getText().toString());
            intent.putExtra(MainActivity.DESCRIPTION, description.getText().toString());

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item);

        TextView title = findViewById(R.id.titleInput);
        TextView description = findViewById(R.id.descriptionInput);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            title.setText(bundle.getString(MainActivity.TITLE));
            description.setText(bundle.getString(MainActivity.DESCRIPTION));
        }

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new CreateNoteListener());
    }
}
