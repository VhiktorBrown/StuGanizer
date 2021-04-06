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
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.lists.Note;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;

public class NoteEditorActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle, noteDetails;
    Calendar calendar;
    String currentDate, currentTime;
    MySqLiteConnector connector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        toolbar = findViewById(R.id.note_editor_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setTitle("New note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteTitle = findViewById(R.id.note_title);
        noteDetails = findViewById(R.id.note_details);
        connector = new MySqLiteConnector(this);

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
                Note note = new Note(noteTitle.getText().toString(), noteDetails.getText().toString(), currentDate, currentTime);
                connector.saveNote(note);
                backToMain();
            } else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(NoteEditorActivity.this, R.style.AlertDialogTheme);
                builder.setTitle("Empty field");
                builder.setMessage("Please, do not leave the title empty.");
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
            startActivity(new Intent(NoteEditorActivity.this, NotesActivity.class));
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
        startActivity(new Intent(NoteEditorActivity.this, NotesActivity.class));
        finish();
    }
}
