package com.chocolatedevelopers.stuganizer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.skyfishjy.library.RippleBackground;

import de.hdodenhof.circleimageview.CircleImageView;

public class RippleActivity extends AppCompatActivity {

    RippleBackground rippleBackground;
    ImageView imageView;
    CircleImageView circleImageView;
    TextView click, welcome;
    MySqLiteConnector connector;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        connector = new MySqLiteConnector(this);
        Cursor cursor = connector.getUserDetails();
        if(cursor.getCount() == 0) {

        } else {
            while(cursor.moveToNext()) {
                user = cursor.getString(1);
            }
        }

        rippleBackground = (RippleBackground) findViewById(R.id.rippleBackground);

        rippleBackground.startRippleAnimation();
        imageView = findViewById(R.id.rippleImage);
        circleImageView = findViewById(R.id.welcome_image);
        click = findViewById(R.id.click_icon_textView);
        welcome = findViewById(R.id.welcome_image_text);
        welcome.setText("Big welcome, " + user);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RippleActivity.this, CourseSelection.class));
                finish();
            }
        });
    }
}
