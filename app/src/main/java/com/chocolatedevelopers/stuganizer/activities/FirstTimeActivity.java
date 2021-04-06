package com.chocolatedevelopers.stuganizer.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FirstTimeActivity extends AppCompatActivity {

    TextInputLayout usernameText, schoolText, passwordText, schoolYearText, hintText;
    TextInputEditText usernameEdit, schoolEdit, passwordEdit, schoolYearEdit, hintEdit;
    Button signInButton;
    MySqLiteConnector connector;
    ProgressBar progressBar;
    private static final int PICK_IMAGE = 1;
    private int STORAGE_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        connector = new MySqLiteConnector(this);
        usernameText = findViewById(R.id.username_text);
        usernameEdit = findViewById(R.id.username_edit);
        progressBar = findViewById(R.id.first_time_progress_bar);

        schoolText = findViewById(R.id.school_text);
        schoolEdit = findViewById(R.id.school_edit);

        passwordText = findViewById(R.id.password_text);
        passwordEdit = findViewById(R.id.password_edit);

        schoolYearText = findViewById(R.id.schoolYear_text);
        schoolYearEdit = findViewById(R.id.schoolYear_edit);

        hintText = findViewById(R.id.hint_text);
        hintEdit = findViewById(R.id.hint_edit);

        signInButton = findViewById(R.id.signIn_button);

        if(ContextCompat.checkSelfPermission(FirstTimeActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestStoragePermissions();
        }

        signInButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(usernameEdit.length() > 0 && schoolEdit.length() > 0 && passwordEdit.length() > 0 && schoolYearEdit.length() > 0 && hintEdit.length() > 0) {
                    if (schoolYearEdit.getText().toString().equals("4") || schoolYearEdit.getText().toString().equals("5")) {
                        connector.saveDetails(usernameEdit.getText().toString(), schoolEdit.getText().toString(), passwordEdit.getText().toString(), schoolYearEdit.getText().toString(), hintEdit.getText().toString());
                        progressBar.setVisibility(View.VISIBLE);
                        Handler handler;
                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(FirstTimeActivity.this, RippleActivity.class));
                                finish();
                            }
                        }, 2000);
                    } else {
                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(FirstTimeActivity.this, R.style.AlertDialogTheme);
                        builder.setTitle("Incorrect entry");
                        builder.setMessage("Sorry, but school year has to be either 4 or 5.");
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
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(FirstTimeActivity.this, R.style.AlertDialogTheme);
                    builder.setTitle("Missing Entries");
                    builder.setMessage("Sorry, you seem to have left some entries empty. Please, fill all fields.");
                    builder.setIcon(R.drawable.ic_info);
                    builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }

                }
        });
    }

    public void requestStoragePermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(FirstTimeActivity.this, R.style.AlertDialogTheme);
            builder.setTitle("Your permission?");
            builder.setMessage("Your permission is needed to change your picture, if that's okay with you. Without it, this feature cannot function");
            builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(FirstTimeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }
}
