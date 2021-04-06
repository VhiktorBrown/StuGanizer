package com.chocolatedevelopers.stuganizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.fragments.Friday;
import com.chocolatedevelopers.stuganizer.fragments.Monday;
import com.chocolatedevelopers.stuganizer.fragments.Saturday;
import com.chocolatedevelopers.stuganizer.fragments.Thursday;
import com.chocolatedevelopers.stuganizer.fragments.Tuesday;
import com.chocolatedevelopers.stuganizer.fragments.Wednesday;
import com.google.android.material.navigation.NavigationView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.io.File;


public class TimeTable extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    SmartTabLayout smartTabLayout;
    ViewPager viewPager;
    MySqLiteConnector connector;
    ImageView imageView;
    String user, school;
    private long backPressedTime;
    private Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.timetable_toolbar);
        connector = new MySqLiteConnector(this);
        Cursor cursor = connector.getUserDetails();
        if(cursor.getCount() == 0) {

        } else {
            while(cursor.moveToNext()) {
                user = cursor.getString(1);
                school = cursor.getString(2);
            }
        }


        smartTabLayout = findViewById(R.id.smart_tab_layout);
        viewPager = findViewById(R.id.timeTable_view_pager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("School Timetable");
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Monday", Monday.class)
        .add("Tuesday", Tuesday.class)
        .add("Wednesday", Wednesday.class)
        .add("Thursday", Thursday.class)
        .add("Friday", Friday.class)
        .add("Saturday", Saturday.class)
        .create());

        viewPager.setAdapter(adapter);

        smartTabLayout.setViewPager(viewPager);

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
                       startActivity(new Intent(TimeTable.this, MainActivity.class));
                        break;
                    case R.id.help_a_friend:
                        startActivity(new Intent(TimeTable.this, HelpAFriend.class));
                        finish();
                        break;
                    case R.id.my_notes:
                        startActivity(new Intent(TimeTable.this, NotesActivity.class));
                        finish();
                        break;
                    case R.id.timeline:
                        startActivity(new Intent(TimeTable.this, Timeline.class));
                        finish();
                        break;
                    case R.id.school_timetable:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.settings:
                        startActivity(new Intent(TimeTable.this, Settings.class));
                        break;
                    case R.id.help_and_feedback:
                        startActivity(new Intent(TimeTable.this, About.class));
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });
        navigationView.setCheckedItem(R.id.school_timetable);
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
