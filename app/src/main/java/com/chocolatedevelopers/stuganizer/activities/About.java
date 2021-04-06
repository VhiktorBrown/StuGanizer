package com.chocolatedevelopers.stuganizer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.R;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element adsElement = new Element();
        adsElement.setTitle("Advertise here");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.stuganizer_free_file)
                .setDescription("StuGanizer - GPA Calculator, Note Taking app, Timetable Tracker all in one.")
                .addItem(new Element().setTitle("Version 1.0.2"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("chokolatedevelopers@gmail.com")
                .addWebsite("http://www.reevatech.africa")
                .addFacebook("Ebuka Victor Chukwujekwu")
                .addInstagram("Vhiktor Brown")
                .addItem(createCopyRight())
                .create();

        setContentView(aboutPage);

    }

    private Element createCopyRight() {
        Element element = new Element();
        String copyRight = String.format("Copyright %d by Chocolate Developers", Calendar.getInstance().get(Calendar.YEAR));
        element.setTitle(copyRight);
        element.setIconDrawable(R.drawable.ic_person);
        element.setGravity(Gravity.CENTER);
        element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(About.this, "Thank you for downloading StuGanizer", Toast.LENGTH_SHORT).show();
            }
        });
        return element;
    }
}
