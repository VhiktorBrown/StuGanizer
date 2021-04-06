package com.chocolatedevelopers.stuganizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settings extends AppCompatActivity {
    CircleImageView imageView;
    TextView userName, schoolName;
    Button changeUserName, changePicture, removePicture, changePassword, changeHint;
    MySqLiteConnector connector;
    String user, school, password, schoolYear, hint;
    Uri imageUri;
    Bitmap image;
    Toolbar toolbar;
    private static final int PICK_IMAGE = 1;
    private int STORAGE_PERMISSION_CODE = 1;
    private String TAG = "check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        imageView = findViewById(R.id.settings_image_view);
        userName = findViewById(R.id.settings_user_name);
        schoolName = findViewById(R.id.settings_school_name);
        changeUserName = findViewById(R.id.change_username_btn);
        changePicture = findViewById(R.id.change_picture);
        removePicture = findViewById(R.id.remove_picture);
        changePassword = findViewById(R.id.change_password);
        changeHint = findViewById(R.id.change_hint);

        toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        connector = new MySqLiteConnector(this);
        Cursor cursor = connector.getUserDetails();
        if(cursor.getCount() == 0) {

        } else{
            while(cursor.moveToNext()) {
                user = cursor.getString(1);
                school = cursor.getString(2);
                password = cursor.getString(3);
                schoolYear = cursor.getString(4);
                hint = cursor.getString(5);
            }
        }
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


        userName.setText(user);
        schoolName.setText(school);

        Toast.makeText(this, "Username: " + user + "\n" + "Password: " + password, Toast.LENGTH_SHORT).show();


        changeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme2);
                LinearLayout layout = new LinearLayout(Settings.this);
                builder.setIcon(R.drawable.ic_person);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setLayoutParams(layoutParams);
                layout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                layout.setGravity(Gravity.CLIP_VERTICAL);
                layout.setPadding(2,2,2,2);

                TextView textMessage = new TextView(Settings.this);
                textMessage.setText("Please, type in your new username.");
                textMessage.setTypeface(Typeface.SERIF);
                textMessage.setTextSize(14);
                textMessage.setTextColor(getResources().getColor(R.color.appColor));
                textMessage.setPadding(10, 10, 10, 10);
                textMessage.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textParams.bottomMargin = 5;

                final TextInputEditText textInputEditText = new TextInputEditText(Settings.this);
                textInputEditText.setPadding(10, 10, 10, 10);
                textInputEditText.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layout.addView(textMessage, textParams);
                layout.addView(textInputEditText, editTextParams);
                builder.setTitle("Change username");
                builder.setView(layout);
                builder.setCancelable(true);

                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(textInputEditText.length() > 0) {
                            int insert = connector.updateUserName(textInputEditText.getText().toString(), password);
                            connector.updateImageName(textInputEditText.getText().toString(), 1);
                            if (insert > -1) {
                                userName.setText(textInputEditText.getText().toString());
                                Toast.makeText(Settings.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Settings.this, "Couldn't update", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme);
                            builder.setTitle("Empty field");
                            builder.setMessage(user + ", please provide something");
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
                });
                builder.setNegativeButton("Don't", null);
                builder.show();

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme2);
                LinearLayout layout = new LinearLayout(Settings.this);
                builder.setIcon(R.drawable.ic_password);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setLayoutParams(layoutParams);
                layout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                layout.setGravity(Gravity.CLIP_VERTICAL);
                layout.setPadding(2,2,2,2);

                TextView textMessage = new TextView(Settings.this);
                textMessage.setText("Howdy " + user + ", please type in the new password. Ensure it's a strong password");
                textMessage.setTypeface(Typeface.SERIF);
                textMessage.setTextSize(14);
                textMessage.setTextColor(getResources().getColor(R.color.appColor));
                textMessage.setPadding(10, 10, 10, 10);
                textMessage.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textParams.bottomMargin = 5;

                final TextInputEditText textInputEditText = new TextInputEditText(Settings.this);
                textInputEditText.setPadding(10, 10, 10, 10);
                textInputEditText.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layout.addView(textMessage, textParams);
                layout.addView(textInputEditText, editTextParams);
                builder.setTitle("Change password");
                builder.setView(layout);
                builder.setCancelable(true);

                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(textInputEditText.length() > 0) {
                            int insert = connector.updatePassword(user, textInputEditText.getText().toString());
                            if (insert > -1) {
                                Toast.makeText(Settings.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Settings.this, "Couldn't update", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme);
                            builder.setTitle("Empty field");
                            builder.setMessage(user + ", please provide a password");
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
                });
                builder.setNegativeButton("Don't", null);
                builder.show();

            }
        });

        changeHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme2);
                LinearLayout layout = new LinearLayout(Settings.this);
                builder.setIcon(R.drawable.ic_hint);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setLayoutParams(layoutParams);
                layout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                layout.setGravity(Gravity.CLIP_VERTICAL);
                layout.setPadding(2,2,2,2);

                TextView textMessage = new TextView(Settings.this);
                textMessage.setText("Please, type in the new password hint. Ensure it's something that's easy to remember.");
                textMessage.setTypeface(Typeface.SERIF);
                textMessage.setTextSize(14);
                textMessage.setTextColor(getResources().getColor(R.color.appColor));
                textMessage.setPadding(10, 10, 10, 10);
                textMessage.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textParams.bottomMargin = 5;

                final TextInputEditText textInputEditText = new TextInputEditText(Settings.this);
                textInputEditText.setPadding(10, 10, 10, 10);
                textInputEditText.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layout.addView(textMessage, textParams);
                layout.addView(textInputEditText, editTextParams);
                builder.setTitle("Change password hint");
                builder.setView(layout);
                builder.setCancelable(true);

                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(textInputEditText.length() > 0) {
                            int insert = connector.updateHint(user, textInputEditText.getText().toString());
                            if (insert > -1) {
                                Toast.makeText(Settings.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Settings.this, "Couldn't update", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme);
                            builder.setTitle("Empty field");
                            builder.setMessage(user + ", please provide something");
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
                });
                builder.setNegativeButton("Don't", null);
                builder.show();

            }
        });

        removePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme);
                builder.setTitle("Remove?");
                builder.setMessage("Are you sure you want to remove profile picture?");
                builder.setIcon(R.drawable.ic_info);
                builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        File filePath = Environment.getExternalStorageDirectory();
                        File dir = new File(filePath.getAbsolutePath() + "/StuGanizer/profile/");
                        if(!dir.exists()) {
                            dir.mkdirs();
                        }
                        File[] manyFiles = new File(String.valueOf(dir)).listFiles();
                        if(manyFiles.length > 0) {
                            for (File oneFile : manyFiles) {
                                if (oneFile.isFile()) oneFile.delete();
                            }
                        }
                        imageView.setImageResource(R.drawable.profile);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();

            }
        });

        changePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(connector.getImage() != null) {
                    connector.updateImageName(user, 1);
                }
                onChooseImage();

                */

               if(ContextCompat.checkSelfPermission(Settings.this,
                       Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                   Intent gallery = new Intent();
                   gallery.setType("image/*");
                   gallery.setAction(Intent.ACTION_GET_CONTENT);

                   startActivityForResult(Intent.createChooser(gallery, "Select picture"), PICK_IMAGE);
               } else {
                   requestStoragePermissions();
               }


            }
        });

    }

    public void requestStoragePermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme);
            builder.setTitle("Your permission?");
            builder.setMessage("Your permission is needed to change your picture, if that's okay with you. Without it, this feature cannot function");
            builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
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
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select picture"), PICK_IMAGE);
            } else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Settings.this, R.style.AlertDialogTheme);
                builder.setTitle("Access not granted");
                builder.setMessage("Sorry, but you didn't grant access. You can't update profile picture.");
                builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        }
    }

    public void onChooseImage() {
        CropImage.activity().start(Settings.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(image);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            FileOutputStream output;
            File filePath = Environment.getExternalStorageDirectory();
            File dir = new File(filePath.getAbsolutePath() + "/StuGanizer/profile/");
                if(!dir.exists()) {
                    dir.mkdirs();
                }
            File[] manyFiles = new File(String.valueOf(dir)).listFiles();
                if(manyFiles.length > 0) {
                    for (File oneFile : manyFiles) {
                        if (oneFile.isFile()) oneFile.delete();
                    }
                }
            File file = new File(dir, "picture" +".png");
            Toast.makeText(Settings.this, "Successfully saved!", Toast.LENGTH_SHORT).show();
            try{
                output = new FileOutputStream(file);
                image.compress(Bitmap.CompressFormat.PNG, 100, output);
                output.flush();
                output.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }

          /*  String state = Environment.getExternalStorageState();
            if(Environment.MEDIA_MOUNTED.equals(state)) {
                String path = Environment.getExternalStorageDirectory().toString();
                File file = new File(path, "UniqueFileName"+".png");
                if(!file.exists()) {
                    Log.d("path", file.toString());
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        FileOutputStream outputStream = new FileOutputStream(file);
                        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } else {
                String path = Environment.getExternalStorageDirectory().toString();
                File file = new File(path, "UniqueFileName"+".png");
                if(!file.exists()) {
                    Log.d("path", file.toString());
                    FileOutputStream outputStream = null;
                    try{
                        outputStream = new FileOutputStream(file);
                        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            */



          /*  String filePath = Environment.getExternalStorageDirectory().toString();

            File file = new File(filePath, System.currentTimeMillis() + ".jpg");
            if(!file.exists()) {
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    Toast.makeText(Settings.this, "File entered", Toast.LENGTH_SHORT).show();
                    image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    imageView.setImageBitmap(image);
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            */



            //saveSharedPreference(Settings.this, "path", saveToInternalStorage(image));

      /*  String realPath = MyGallery.getRealPath(Settings.this, imageUri);
            System.out.println(realPath);
            MyGallery.reScaleImage(Settings.this, realPath, new ReScaleListener() {
                @Override
                public void onFinish(final File file, final boolean success, final String message) {
                    OnUxThread.run(new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Settings.this, message, Toast.LENGTH_SHORT).show();
                            if(success) {
                                String imageName = file.getName();
                                String newPath = file.getAbsolutePath();
                                Bitmap bitmap = MyGallery.getImageFromPath(newPath);
                                imageView.setImageBitmap(bitmap);
                            } else {
                                Toast.makeText(Settings.this, "Couldn't get image", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }), Settings.this);
                }
            });

            */




        }

        /*
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(resultCode == RESULT_OK) {
                imageUri = result.getUri();
                try {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    image.compress(Bitmap.CompressFormat.JPEG, 20, stream);
                    imageView.setImageBitmap(image);
                    byte[] imageInByte = stream.toByteArray();
                    if(connector.getImage() == null) {
                        connector.insertImage(user, imageInByte);
                        Toast.makeText(this, "User: " + user, Toast.LENGTH_SHORT).show();
                    } else {
                        connector.updateImage(user, imageInByte);
                        Toast.makeText(this, "User: " + user, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Image to be picked crashed");
                }

            } else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception ex = result.getError();
                Toast.makeText(this, "Error: " + ex, Toast.LENGTH_SHORT).show();
            }
        }
        */
    }

   /* private void saveImage(Bitmap imageBitmap) {
        String root = Environment.getExternalStorageDirectory().getPath();
        File myDir = new File(root, "Organizer");
        if(!myDir.exists()) {
            myDir.mkdirs();
        } else {
            Log.w(TAG, "Didn't work");
        }

        String fileName = "Image 1";
        String absolutePath = myDir.getAbsolutePath() + "/" + fileName + "images.png";
        try{
            FileOutputStream out = new FileOutputStream(absolutePath);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            imageView.setImageBitmap(imageBitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    */

    private String saveToInternalStorage(Bitmap imageBitmap) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File myPath = new File(directory, "profile.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path, ImageView image) {
        try {
            File f = new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            image.setImageBitmap(b);
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void saveSharedPreference(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getSharedPreference(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "defaultValue");
    }

   public class  Util {

   }

}
