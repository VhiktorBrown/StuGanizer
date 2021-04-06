package com.chocolatedevelopers.stuganizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.adapters.NoteAdapter;
import com.chocolatedevelopers.stuganizer.lists.Note;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    NoteAdapter adapter;
    ArrayList<Note> noteArrayList;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    MySqLiteConnector connector;
    ImageView imageView;
    String user, school;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        toolbar = findViewById(R.id.notes_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Notes");
        connector = new MySqLiteConnector(this);

        Cursor cursor = connector.getUserDetails();
        if(cursor.getCount() == 0) {

        } else {
            while(cursor.moveToNext()) {
                user = cursor.getString(1);
                school = cursor.getString(2);
            }
        }

        Cursor cursor2 = connector.getSave();
        if(cursor2.getCount() == 0) {
            showTargetView();
        }


        recyclerView = findViewById(R.id.note_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteArrayList = connector.getNotes();
        adapter = new NoteAdapter(this, noteArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        adapter.notifyDataSetChanged();

        fab = findViewById(R.id.fab_note);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotesActivity.this, NoteEditorActivity.class));
                finish();
            }
        });

        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.nav_name);
        TextView schoolName = header.findViewById(R.id.nav_school);
        imageView = header.findViewById(R.id.nav_image);
        name.setText(user);
        schoolName.setText(school);

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Stuganizer/profile/");
        if(file.exists()) {
            File[] files = file.listFiles();
            if (files!=null) {
                for (int i = 0; i < files.length; i++) {
                    Bitmap bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.my_courses:
                        startActivity(new Intent(NotesActivity.this, MainActivity.class));
                        finish();
                        break;
                    case R.id.help_a_friend:
                        startActivity(new Intent(NotesActivity.this, HelpAFriend.class));
                        finish();
                        break;
                    case R.id.my_notes:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.timeline:
                        startActivity(new Intent(NotesActivity.this, Timeline.class));
                        finish();
                        break;
                    case R.id.school_timetable:
                        startActivity(new Intent(NotesActivity.this, TimeTable.class));
                        finish();
                        break;
                    case R.id.settings:
                        startActivity(new Intent(NotesActivity.this, Settings.class));
                        break;
                    case R.id.help_and_feedback:
                        startActivity(new Intent(NotesActivity.this, About.class));
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });
        navigationView.setCheckedItem(R.id.my_notes);

    }
    public void showTargetView() {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.fab_note), "Add New Note", "Click on this button to add new notes")
                        .outerCircleColor(R.color.appColor)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.colorWhite)
                        .titleTextSize(20)
                        .titleTextColor(R.color.colorWhite)
                        .descriptionTextSize(14)
                        .descriptionTextColor(R.color.colorWhite)
                        .textColor(R.color.colorWhite)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.colorWhite)
                        .drawShadow(true)
                        .cancelable(true)
                        .tintTarget(false)
                        .targetRadius(45)
                        .transparentTarget(true),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                    }
                });
        connector.save("yes");
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
