package com.chocolatedevelopers.stuganizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class HelpAFriend extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Toolbar toolbar;
    Spinner gpaSpinner;
    EditText totalCourses;
    Button continueButton;
    String selectedGPA;
    MySqLiteConnector connector;
    String user, school, schoolYear;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_a_friend);
        toolbar = findViewById(R.id.help_toolbar);
        gpaSpinner = findViewById(R.id.gpa_spinner);
        totalCourses = findViewById(R.id.total_courses);
        continueButton = findViewById(R.id.continue_button);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Help That Friend");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        connector = new MySqLiteConnector(this);
        Cursor cursor = connector.getUserDetails();
        if(cursor.getCount() == 0) {

        } else {
            while(cursor.moveToNext()) {
                user = cursor.getString(1);
                school = cursor.getString(2);
                schoolYear = cursor.getString(4);
            }
        }

        String[] gp = {"4.0 GPA", "5.0 GPA"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gp);
        gpaSpinner.setAdapter(adapter);

        gpaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGPA = gpaSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedGPA.equals("4.0 GPA") || selectedGPA.equals("5.0 GPA")) {
                    if (totalCourses.length() > 0) {
                        int numOfCourses = Integer.parseInt(totalCourses.getText().toString());
                        if (numOfCourses > 0 && numOfCourses < 50) {
                            Bundle bundle = new Bundle();
                            bundle.putString("maxGpa", selectedGPA);
                            bundle.putInt("totalCourses", numOfCourses);
                            Intent intent = new Intent(HelpAFriend.this, ComputationActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            totalCourses.setText("");
                        } else {
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(HelpAFriend.this, R.style.AlertDialogTheme);
                            builder.setTitle("Incorrect entry");
                            builder.setMessage("Please, make sure your total number of courses is not more than 50");
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
                    } else {
                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(HelpAFriend.this, R.style.AlertDialogTheme);
                        builder.setTitle("Empty field");
                        builder.setMessage("You didn't type in anything. Please type in something");
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
                }
            }
        });


        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.nav_name);
        TextView schoolName = header.findViewById(R.id.nav_school);
        ImageView imageView = header.findViewById(R.id.nav_image);
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
                        startActivity(new Intent(HelpAFriend.this, MainActivity.class));
                        finish();
                        break;
                    case R.id.help_a_friend:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.my_notes:
                        startActivity(new Intent(HelpAFriend.this, NotesActivity.class));
                        finish();
                        break;
                    case R.id.timeline:
                        startActivity(new Intent(HelpAFriend.this, Timeline.class));
                        finish();
                        break;
                    case R.id.school_timetable:
                        startActivity(new Intent(HelpAFriend.this, TimeTable.class));
                        finish();
                        break;
                    case R.id.settings:
                        startActivity(new Intent(HelpAFriend.this, Settings.class));
                        break;
                    case R.id.help_and_feedback:
                        startActivity(new Intent(HelpAFriend.this, About.class));
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });
        navigationView.setCheckedItem(R.id.help_a_friend);

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
