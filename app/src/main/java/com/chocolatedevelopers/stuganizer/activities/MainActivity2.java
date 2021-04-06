package com.chocolatedevelopers.stuganizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.adapters.PagerAdapter2;
import com.chocolatedevelopers.stuganizer.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class MainActivity2 extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TabLayout tabLayout;
    Toolbar toolbar;
    TabItem first, second;
    ViewPager pager;
    PagerAdapter2 adapter;
    MySqLiteConnector connector;
    String user, school, schoolYear, department;
    ImageView imageView;
    TextView showCourse;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = findViewById(R.id.toolbar);

        pager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        first = findViewById(R.id.first_semester);
        second = findViewById(R.id.second_semester);
        showCourse = findViewById(R.id.blank_textView);
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
        Cursor cursor3 = connector.getCourse();
        if(cursor3.getCount() == 0) {

        } else {
            while (cursor3.moveToNext()) {
                department = cursor3.getString(1);
            }
        }
        showCourse.setText("Department: " + department);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Year 2");
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        adapter = new PagerAdapter2(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        pager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.help_a_friend:
                        startActivity(new Intent(MainActivity2.this, HelpAFriend.class));
                        finish();
                        break;
                    case R.id.my_notes:
                        startActivity(new Intent(MainActivity2.this, NotesActivity.class));
                        finish();
                        break;
                    case R.id.timeline:
                        startActivity(new Intent(MainActivity2.this, Timeline.class));
                        finish();
                        break;
                    case R.id.school_timetable:
                        startActivity(new Intent(MainActivity2.this, TimeTable.class));
                        finish();
                        break;
                    case R.id.settings:
                        startActivity(new Intent(MainActivity2.this, Settings.class));
                        break;
                    case R.id.help_and_feedback:
                        startActivity(new Intent(MainActivity2.this, About.class));
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });
        navigationView.setCheckedItem(R.id.my_courses);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(schoolYear.equals("4")) {
            inflater.inflate(R.menu.school_year, menu);
        } else if(schoolYear.equals("5")) {
            inflater.inflate(R.menu.school_year2, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.year_1) {
            startActivity(new Intent(MainActivity2.this, MainActivity.class));
            finish();
        } else if(item.getItemId() == R.id.year_2) {

        } else if(item.getItemId() == R.id.year_3) {
            startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            finish();
        } else if(item.getItemId() == R.id.year_4) {
            startActivity(new Intent(MainActivity2.this, MainActivity4.class));
            finish();
        } else if(item.getItemId() == R.id.year_5) {
            startActivity(new Intent(MainActivity2.this, MainActivity5.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
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
