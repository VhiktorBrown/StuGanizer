package com.chocolatedevelopers.stuganizer.lists.classes;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.chocolatedevelopers.stuganizer.interfaces.ReScaleListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

public class MyGallery {

    public static String getMemoryCardPath() {
        return "/sd/card";
    }

    public static String getInternalMemory() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static String PROFILE_IMAGES_PATH = getInternalMemory() + "/stuGanizer/profile_photo/";

    private static String getNameExt(String abPath) {
        try {
            return (abPath.substring(abPath.lastIndexOf(".")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getRealPath(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        try {
            if(cursor == null) {
                return uri.getPath();
            } else {
                cursor.moveToFirst();
                int d = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                return cursor.getString(d);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cursor.close();
        }
        return null;
    }

    public static Bitmap getImageFromPath(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(new File(path).getAbsolutePath(), options);
    }

    private static boolean exceeds900kb(String path) {
        return (new File(path).length() > 900 * 1024);
    }

    private static String generatePhotoName() {
        Random rand = new Random(System.nanoTime());
        long x1 = Math.abs(rand.nextLong());
        String i = String.valueOf(x1).substring(0, 12);
        return i;
    }

    private static void passToReScalingFragment(Context context, ReScaleListener reScaleListener, final File file, final String message) {
        if(reScaleListener != null) {
            reScaleListener.onFinish(file, true, message);
        }
    }

    public static void reScaleImage(final Context context, final String path, final ReScaleListener reScaleListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream outputStream = null;
                FileInputStream inputStream = null;
                Bitmap selectedBitmap = null;
                try{
                    File file = new File(path);
                    if(file.isFile()) {
                        BitmapFactory.Options o = new BitmapFactory.Options();
                        o.inJustDecodeBounds = true;
                        o.inSampleSize = 6;
                        inputStream = new FileInputStream(file);
                        BitmapFactory.decodeStream(inputStream, null, o);
                        final int REQUIRED_SIZE = 75;
                        int scale = 1;
                        while(o.outWidth /scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                            scale *=2;
                        }
                        BitmapFactory.Options o2 = new BitmapFactory.Options();
                        o2.inSampleSize = scale;
                        inputStream = new FileInputStream(file);
                        selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
                        inputStream.close();

                        String directory = MyGallery.PROFILE_IMAGES_PATH;
                        // delete old images from the folder
                        File[] manyFiles = new File(directory).listFiles();
                        for(File oneFile : manyFiles) {
                            if(oneFile.isFile()) oneFile.delete();
                        }

                        String photoName = generatePhotoName()+ MyGallery.getNameExt(path);

                        System.out.println(directory);
                        System.out.println(photoName);

                        final File file1 = new File(directory, photoName);
                        file1.getParentFile().mkdirs();
                        file1.createNewFile();
                        outputStream = new FileOutputStream(file1);
                        selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        if(!exceeds900kb(file1.getAbsolutePath())) {
                            passToReScalingFragment(context, reScaleListener, file1, "Photo rescaled");
                        } else {
                            passToReScalingFragment(context, reScaleListener, file1, "PhotoSize too large");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    passToReScalingFragment(context, reScaleListener, null, "An error occurred");
                    try{
                        if(null!= outputStream) {
                            outputStream.close();
                        }
                        if(null!=inputStream) {
                            inputStream.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
