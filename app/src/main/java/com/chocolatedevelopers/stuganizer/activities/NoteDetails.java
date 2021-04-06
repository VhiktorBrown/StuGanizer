package com.chocolatedevelopers.stuganizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.lists.Note;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteDetails extends AppCompatActivity {
Toolbar toolbar;
TextView textDetails;
FloatingActionButton deleteFab;
MySqLiteConnector connector;
    Note note;
    Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        Intent i = getIntent();
        id = i.getLongExtra("ID", 0);
        connector = new MySqLiteConnector(this);

        toolbar = findViewById(R.id.note_details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textDetails = findViewById(R.id.note_details_content);
        deleteFab = findViewById(R.id.note_details_delete);

        note = connector.getNote(id);

        Toast.makeText(this, "ID: " + note.getTitle(), Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle(note.getTitle());
        textDetails.setText(note.getDetails());
        textDetails.setMovementMethod(new ScrollingMovementMethod());

        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(NoteDetails.this, R.style.AlertDialogTheme);
                builder.setTitle("Delete?");
                builder.setMessage("Are you sure you want to delete this note?");
                builder.setIcon(R.drawable.ic_info);
                builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        connector.delete(id);
                        backToMain();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.edit_note) {
            Intent intent = new Intent(this, EditNoteActivity.class);
            intent.putExtra("ID", note.getId());
            startActivity(intent);
            finish();
        } else if(item.getItemId() == android.R.id.home) {
            startActivity(new Intent(NoteDetails.this, NotesActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void backToMain() {
        startActivity(new Intent(this, NotesActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NoteDetails.this, NotesActivity.class));
        finish();
    }
}
