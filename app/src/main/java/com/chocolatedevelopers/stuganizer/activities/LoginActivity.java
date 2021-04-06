package com.chocolatedevelopers.stuganizer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    LinearLayout logInLayout;
    CircleImageView userImage;
    TextInputEditText password, userName;
    TextView forgotPassword, welcomeText;
    Button logInButton;
    MySqLiteConnector connector;
    String user, pass, hint;
    ProgressBar progressBar;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logInLayout = findViewById(R.id.logIn_layout);
        userImage = findViewById(R.id.user_image);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgot_password);
        logInButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_bar);
        welcomeText = findViewById(R.id.welcome_text);

                final int[][] states = new int[][]{
                        new int[]{android.R.attr.state_enabled},
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_pressed}
                };

                final int[] colors = new int[] {
                        Color.GRAY,
                        Color.GRAY,
                        Color.GRAY,
                        Color.GRAY
                };

        final int[][] states2 = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_pressed}
        };

        final int[] colors2 = new int[] {
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE
        };

        connector = new MySqLiteConnector(this);
        Cursor cursor = connector.getUserDetails();
        if(cursor.getCount() == 0) {

        } else{
            while(cursor.moveToNext()) {
                user = cursor.getString(1);
                pass = cursor.getString(3);
                hint = cursor.getString(5);
            }
        }
        userName.setText(user);
        welcomeText.setText(user + ", welcome back.");

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().length() == 0) {
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(LoginActivity.this, R.style.AlertDialogTheme);
                    builder.setTitle("Empty field");
                    builder.setMessage("Please, provide a password");
                    builder.setIcon(R.drawable.ic_info);
                    builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                    builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing.
                        }
                    });
                    builder.show();
                } else if(userName.getText().toString().equals(user) && password.getText().toString().equals(pass)) {
                    progressBar.setVisibility(View.VISIBLE);
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }, 4000);

                } else {
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(LoginActivity.this, R.style.AlertDialogTheme);
                    builder.setTitle("Incorrect password");
                    builder.setMessage("Sorry, the password you entered is incorrect. Please try again.");
                    builder.setIcon(R.drawable.ic_info);
                    builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                    builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing.
                        }
                    });
                    builder.show();
                    password.setText("");
                }


            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorStateList myList = new ColorStateList(states, colors);
                ColorStateList myList2 = new ColorStateList(states2, colors2);
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(LoginActivity.this, R.style.AlertDialogTheme2);
                LinearLayout layout = new LinearLayout(LoginActivity.this);
                builder.setIcon(R.drawable.ic_hint);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setLayoutParams(layoutParams);
                layout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                layout.setGravity(Gravity.CLIP_VERTICAL);
                layout.setPadding(2,2,2,2);

                TextView textMessage = new TextView(LoginActivity.this);
                textMessage.setText("Please, type in your password hint to gain access to app again.");
                textMessage.setTypeface(Typeface.SERIF);
                textMessage.setTextSize(14);
                textMessage.setTextColor(getResources().getColor(R.color.appColor));
                textMessage.setPadding(10, 10, 10, 10);
                textMessage.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textParams.bottomMargin = 5;

                final TextInputEditText textInputEditText = new TextInputEditText(LoginActivity.this);
                textInputEditText.setHintTextColor(myList);
                textInputEditText.setPadding(10, 10, 10, 10);
                textInputEditText.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layout.addView(textMessage, textParams);
                layout.addView(textInputEditText, editTextParams);
                builder.setTitle("Password Hint");
                builder.setView(layout);
                builder.setCancelable(true);

                builder.setPositiveButton("LogIn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(textInputEditText.length() > 0 && textInputEditText.getText().toString().equals(hint)) {
                            progressBar.setVisibility(View.VISIBLE);
                            Snackbar.make(logInLayout, "You can now change your password in 'Settings'", Snackbar.LENGTH_LONG).show();
                            Handler handlerr = new Handler();
                            handlerr .postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }, 4000);

                        } else {
                            Snackbar.make(logInLayout, "Sorry, but the hint you entered is incorrect. Try again, please.", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });
    }
}
