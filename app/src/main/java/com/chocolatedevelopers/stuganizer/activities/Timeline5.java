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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.adapters.PagerAdapterT5;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class Timeline5 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    PagerAdapterT5 adapter;
    TabLayout tabLayout;
    Toolbar toolbar;
    TabItem first, second, total;
    ViewPager pager;
    MySqLiteConnector connector;
    ImageView imageView;
    String user, school, schoolYear;
    private long backPressedTime;
    private Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline5);

        toolbar = findViewById(R.id.timeline_toolbar);

        pager = findViewById(R.id.timeline_view_pager);
        tabLayout = findViewById(R.id.tab);
        first = findViewById(R.id.first_item);
        second = findViewById(R.id.second_item);
        total = findViewById(R.id.total_gp);
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


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timeline");
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        adapter = new PagerAdapterT5(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        pager.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        if(schoolYear.equals("4"))
            bottomNavigationView.inflateMenu(R.menu.school_year);
        else if(schoolYear.equals("5"))
            bottomNavigationView.inflateMenu(R.menu.school_year2);


        bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.orangeGold));
        bottomNavigationView.setSelectedItemId(R.id.year_5);

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
                        startActivity(new Intent(Timeline5.this, MainActivity.class));
                        finish();
                        break;
                    case R.id.help_a_friend:
                        startActivity(new Intent(Timeline5.this, HelpAFriend.class));
                        finish();
                        break;
                    case R.id.my_notes:
                        startActivity(new Intent(Timeline5.this, NotesActivity.class));
                        finish();
                        break;
                    case R.id.timeline:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.school_timetable:
                        startActivity(new Intent(Timeline5.this, TimeTable.class));
                        finish();
                        break;
                    case R.id.settings:
                        startActivity(new Intent(Timeline5.this, Settings.class));
                        break;
                    case R.id.help_and_feedback:
                        startActivity(new Intent(Timeline5.this, About.class));
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });
        navigationView.setCheckedItem(R.id.timeline);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.year_1:
                    startActivity(new Intent(Timeline5.this, Timeline.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.year_2:
                    startActivity(new Intent(Timeline5.this, Timeline2.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.year_3:
                    startActivity(new Intent(Timeline5.this, Timeline3.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.year_4:
                    startActivity(new Intent(Timeline5.this, Timeline4.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.year_5:

                    return true;
            }
            return false;
        }
    };

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
