package com.chocolatedevelopers.stuganizer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;

public class SplashScreen extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView image;
    Handler handler;
    MySqLiteConnector connector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.splashscreen_image);

        connector = new MySqLiteConnector(this);
        final Cursor cursor = connector.getUserDetails();

        image.setAnimation(topAnim);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (cursor.getCount() == 0) {
                    startActivity(new Intent(SplashScreen.this, WalkThrough.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }
            }
        }, 4000);

    }
}
