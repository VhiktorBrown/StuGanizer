package com.chocolatedevelopers.stuganizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.lists.Note;
import com.chocolatedevelopers.stuganizer.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;

public class EditNoteActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteDetails;
    Calendar calendar;
    String currentDate, currentTime;
    MySqLiteConnector connector;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID", 0);
        toolbar = findViewById(R.id.note_editor_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setTitle("Edit note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteTitle = findViewById(R.id.note_title);
        noteDetails = findViewById(R.id.note_details);
        connector = new MySqLiteConnector(this);
         note = connector.getNote(id);

        noteTitle.setText(note.getTitle());
        noteDetails.setText(note.getDetails());

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        calendar = Calendar.getInstance();
        currentDate = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get((Calendar.MINUTE)));
    }

    public String pad(int minute) {
        if(minute < 10)
            return "0"+minute;
        return String.valueOf(minute);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save) {
            if(noteTitle.length() > 0) {
               note.setTitle(noteTitle.getText().toString());
               note.setDetails(noteDetails.getText().toString());
               connector.editNote(note);
                Intent i = new Intent(this, NotesActivity.class);
                i.putExtra("ID", note.getId());
                startActivity(i);
                finish();
            } else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(EditNoteActivity.this, R.style.AlertDialogTheme);
                builder.setTitle("Empty field");
                builder.setMessage("Sorry, you can't leave the title empty.");
                builder.setIcon(R.drawable.ic_info);
                builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing.
                    }
                });
                builder.show();
            }

        } else if(item.getItemId() == R.id.delete) {
            startActivity(new Intent(EditNoteActivity.this, NotesActivity.class));
            finish();
        } else if(item.getItemId() == android.R.id.home) {
            startActivity(new Intent(EditNoteActivity.this, NotesActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditNoteActivity.this, NotesActivity.class));
        finish();
    }
}
