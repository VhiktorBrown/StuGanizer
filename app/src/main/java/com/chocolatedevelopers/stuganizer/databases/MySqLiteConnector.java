package com.chocolatedevelopers.stuganizer.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.chocolatedevelopers.stuganizer.lists.TimeTableDetails;
import com.chocolatedevelopers.stuganizer.lists.CourseList;
import com.chocolatedevelopers.stuganizer.lists.Note;
import com.chocolatedevelopers.stuganizer.lists.TimelineList;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;


public class MySqLiteConnector extends SQLiteOpenHelper {
    public MySqLiteConnector(Context activity) {
        super(activity, "list", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String table = "CREATE TABLE IF NOT EXISTS userDetails" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name CHAR," +
                "school CHAR," +
                "password CHAR," +
                "schoolYear CHAR," +
                "hint CHAR)";
        database.execSQL(table);

        String imageBase = "CREATE TABLE IF NOT EXISTS imageData" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name CHAR," +
                "image BLOB)";
        database.execSQL(imageBase);

        String note = "CREATE TABLE IF NOT EXISTS notesTable" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title CHAR," +
                "details CHAR," +
                "date CHAR," +
                "time CHAR)";
        database.execSQL(note);

        String timeTable1 = "CREATE TABLE IF NOT EXISTS timeTable1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lecturer CHAR," +
                "course CHAR," +
                "startTime CHAR," +
                "endTIme CHAR," +
                "venue CHAR)";
        database.execSQL(timeTable1);

        String timeTable2 = "CREATE TABLE IF NOT EXISTS timeTable2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lecturer CHAR," +
                "course CHAR," +
                "startTime CHAR," +
                "endTIme CHAR," +
                "venue CHAR)";
        database.execSQL(timeTable2);

        String timeTable3 = "CREATE TABLE IF NOT EXISTS timeTable3" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lecturer CHAR," +
                "course CHAR," +
                "startTime CHAR," +
                "endTIme CHAR," +
                "venue CHAR)";
        database.execSQL(timeTable3);

        String timeTable4 = "CREATE TABLE IF NOT EXISTS timeTable4" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lecturer CHAR," +
                "course CHAR," +
                "startTime CHAR," +
                "endTIme CHAR," +
                "venue CHAR)";
        database.execSQL(timeTable4);

        String timeTable5 = "CREATE TABLE IF NOT EXISTS timeTable5" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lecturer CHAR," +
                "course CHAR," +
                "startTime CHAR," +
                "endTIme CHAR," +
                "venue CHAR)";
        database.execSQL(timeTable5);

        String timeTable6 = "CREATE TABLE IF NOT EXISTS timeTable6" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lecturer CHAR," +
                "course CHAR," +
                "startTime CHAR," +
                "endTIme CHAR," +
                "venue CHAR)";
        database.execSQL(timeTable6);

        String check = "CREATE TABLE IF NOT EXISTS once" +
                "(save CHAR)";
        database.execSQL(check);

        String check2 = "CREATE TABLE IF NOT EXISTS once2" +
                "(save CHAR)";
        database.execSQL(check2);

        String check3 = "CREATE TABLE IF NOT EXISTS once3" +
                "(save CHAR)";
        database.execSQL(check3);

        String check4 = "CREATE TABLE IF NOT EXISTS once4" +
                "(save CHAR)";
        database.execSQL(check4);

        String course = "CREATE TABLE IF NOT EXISTS course" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nameOfCourse CHAR)";
        database.execSQL(course);

        String course1_1 = "CREATE TABLE IF NOT EXISTS course1_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course1_1);

        String course1_2 = "CREATE TABLE IF NOT EXISTS course1_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course1_2);

        String course2_1 = "CREATE TABLE IF NOT EXISTS course2_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course2_1);

        String course2_2 = "CREATE TABLE IF NOT EXISTS course2_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course2_2);

        String course3_1 = "CREATE TABLE IF NOT EXISTS course3_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course3_1);

        String course3_2 = "CREATE TABLE IF NOT EXISTS course3_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course3_2);

        String course4_1 = "CREATE TABLE IF NOT EXISTS course4_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course4_1);

        String course4_2 = "CREATE TABLE IF NOT EXISTS course4_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course4_2);

        String course5_1 = "CREATE TABLE IF NOT EXISTS course5_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course5_1);

        String course5_2 = "CREATE TABLE IF NOT EXISTS course5_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "creditLoad CHAR)";
        database.execSQL(course5_2);

        String timelineCourse1_1 = "CREATE TABLE IF NOT EXISTS timelineCourse1_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse1_1);

        String timelineCourse1_2 = "CREATE TABLE IF NOT EXISTS timelineCourse1_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse1_2);

        String timelineCourse2_1 = "CREATE TABLE IF NOT EXISTS timelineCourse2_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse2_1);

        String timelineCourse2_2 = "CREATE TABLE IF NOT EXISTS timelineCourse2_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse2_2);

        String timelineCourse3_1 = "CREATE TABLE IF NOT EXISTS timelineCourse3_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse3_1);

        String timelineCourse3_2 = "CREATE TABLE IF NOT EXISTS timelineCourse3_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse3_2);

        String timelineCourse4_1 = "CREATE TABLE IF NOT EXISTS timelineCourse4_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse4_1);

        String timelineCourse4_2 = "CREATE TABLE IF NOT EXISTS timelineCourse4_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse4_2);

        String timelineCourse5_1 = "CREATE TABLE IF NOT EXISTS timelineCourse5_1" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse5_1);

        String timelineCourse5_2 = "CREATE TABLE IF NOT EXISTS timelineCourse5_2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "course CHAR, " +
                "grade CHAR)";
        database.execSQL(timelineCourse5_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String table = "DROP TABLE IF EXISTS userDetails";
        database.execSQL(table);

        String imageBase = "DROP TABLE IF EXISTS imageData";
        database.execSQL(imageBase);

        String noteTable = "DROP TABLE IF EXISTS notesTable";
        database.execSQL(noteTable);

        String course1_1 = "DROP TABLE IF EXISTS course1_1";
        database.execSQL(course1_1);

        String course1_2 = "DROP TABLE IF EXISTS course1_2";
        database.execSQL(course1_2);

        String course2_1 = "DROP TABLE IF EXISTS course2_1";
        database.execSQL(course2_1);

        String course2_2 = "DROP TABLE IF EXISTS course2_2";
        database.execSQL(course2_2);

        String course3_1 = "DROP TABLE IF EXISTS course3_1";
        database.execSQL(course3_1);

        String course3_2 = "DROP TABLE IF EXISTS course3_2";
        database.execSQL(course3_2);

        String course4_1 = "DROP TABLE IF EXISTS course4_1";
        database.execSQL(course4_1);

        String course4_2 = "DROP TABLE IF EXISTS course4_2";
        database.execSQL(course4_2);

        String course5_1 = "DROP TABLE IF EXISTS course5_1";
        database.execSQL(course5_1);

        String course5_2 = "DROP TABLE IF EXISTS course5_2";
        database.execSQL(course5_2);

        String check = "DROP TABLE IF EXISTS once";
        database.execSQL(check);

        String check2 = "DROP TABLE IF EXISTS once2";
        database.execSQL(check2);

        String check3 = "DROP TABLE IF EXISTS once3";
        database.execSQL(check3);

        String check4 = "DROP TABLE IF EXISTS once4";
        database.execSQL(check4);

        String timelineCourse1_1 = "DROP TABLE IF EXISTS timelineCourse1_1";
        database.execSQL(timelineCourse1_1);

        String timelineCourse1_2 = "DROP TABLE IF EXISTS timelineCourse1_2";
        database.execSQL(timelineCourse1_2);

        String timelineCourse2_1 = "DROP TABLE IF EXISTS timelineCourse2_1";
        database.execSQL(timelineCourse2_1);

        String timelineCourse2_2 = "DROP TABLE IF EXISTS timelineCourse2_2";
        database.execSQL(timelineCourse2_2);

        String timelineCourse3_1 = "DROP TABLE IF EXISTS timelineCourse3_1";
        database.execSQL(timelineCourse3_1);

        String timelineCourse3_2 = "DROP TABLE IF EXISTS timelineCourse3_2";
        database.execSQL(timelineCourse3_2);

        String timelineCourse4_1 = "DROP TABLE IF EXISTS timelineCourse4_1";
        database.execSQL(timelineCourse4_1);

        String timelineCourse4_2 = "DROP TABLE IF EXISTS timelineCourse4_2";
        database.execSQL(timelineCourse4_2);

        String timelineCourse5_1 = "DROP TABLE IF EXISTS timelineCourse5_1";
        database.execSQL(timelineCourse5_1);

        String timelineCourse5_2 = "DROP TABLE IF EXISTS timelineCourse5_2";
        database.execSQL(timelineCourse5_2);

        String timeTable1 = "DROP TABLE IF EXISTS timeTable1";
        database.execSQL(timeTable1);

        String timeTable2 = "DROP TABLE IF EXISTS timeTable2";
        database.execSQL(timeTable2);

        String timeTable3 = "DROP TABLE IF EXISTS timeTable3";
        database.execSQL(timeTable3);

        String timeTable4 = "DROP TABLE IF EXISTS timeTable4";
        database.execSQL(timeTable4);

        String timeTable5 = "DROP TABLE IF EXISTS timeTable5";
        database.execSQL(timeTable5);

        String timeTable6 = "DROP TABLE IF EXISTS timeTable6";
        database.execSQL(timeTable6);
    }

    synchronized boolean saveDetails(String name, String school, String password, String schoolYear, String hint) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("school", school);
            contentValues.put("password", password);
            contentValues.put("schoolYear", schoolYear);
            contentValues.put("hint", hint);

            status = database.insert("userDetails", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveCourse(String nameOfCourse) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("nameOfCourse", nameOfCourse);

            status = database.insert("course", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }



    public synchronized boolean save(String check) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("save", check);

            status = database.insert("once", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean save2(String check) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("save", check);

            status = database.insert("once2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean save3(String check) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("save", check);

            status = database.insert("once3", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }
    public synchronized boolean save4(String check) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("save", check);

            status = database.insert("once4", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    public Cursor getSave() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM once", null);
        return cursor;
    }
    public Cursor getSave2() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM once2", null);
        return cursor;
    }
    public Cursor getSave3() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM once3", null);
        return cursor;
    }
    public Cursor getSave4() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM once4", null);
        return cursor;
    }

    public int updateUserName(String name, String pass) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);

        return database.update("userDetails", contentValues, "password=?", new String[]{String.valueOf(pass)});
    }

    public int updatePassword(String name, String pass) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", pass);

        return database.update("userDetails", contentValues, "name=?", new String[]{String.valueOf(name)});
    }

    public int updateHint(String name, String hint) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hint", hint);

        return database.update("userDetails", contentValues, "name=?", new String[]{String.valueOf(name)});
    }

    long insertImage(String name, byte b[]) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("image", b);
        return database.insert("imageData", null, cv);
    }

    long updateImage(String name, byte b[]) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image", b);
        return database.update("imageData", cv, "name=?", new String[]{String.valueOf(name)});
    }

    public int updateImageName(String name, int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);

        return database.update("imageData", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    public Bitmap getImage() {
        Bitmap bp = null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query("imageData", null, null, null, null, null, null);
        if(cursor.moveToNext()) {
             byte b[] = cursor.getBlob(2);
             ByteArrayInputStream bi = new ByteArrayInputStream(b);
             bp = BitmapFactory.decodeStream(bi);
             cursor.close();
        }
        return bp;
    }

    public long saveNote(Note note) {
        SQLiteDatabase database = this.getWritableDatabase();
        long status;
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("details", note.getDetails());
        contentValues.put("date", note.getDate());
        contentValues.put("time", note.getTime());

        status = database.insert("notesTable", null, contentValues);
        return status;
    }

    public Note getNote(long id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query("notesTable", new String[]{"id", "title", "details", "date", "time"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        return new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
    }

    public ArrayList<Note> getNotes() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Note> everyNote = new ArrayList<>();

        String command = "SELECT * FROM notesTable ORDER BY id DESC";
        Cursor cursor = database.rawQuery(command, null);
        if(cursor.moveToFirst())
            do{
                Note note = new Note();
                note.setId(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setDetails(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));

                everyNote.add(note);

            }while(cursor.moveToNext());
            return everyNote;
        }

        int editNote(Note note) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("details", note.getDetails());
        contentValues.put("date", note.getDate());
        contentValues.put("time", note.getTime());
        return database.update("notesTable", contentValues, "id=?", new String[]{String.valueOf(note.getId())});
        }

        public void delete(long id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("notesTable", "id=?", new String[]{String.valueOf(id)});
        database.close();
        }

    void deleteImage() {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM " + "imageData";
            database.execSQL(query);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    void deleteEverythingInTable(String tableName) {
        SQLiteDatabase database = null;
        try {
            database = this.getReadableDatabase();
            String query = "DELETE FROM " + tableName;
            database.execSQL(query);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }




    synchronized boolean saveCourse1_1(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course1_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveCourse1_2(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course1_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveCourse2_1(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course2_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    public synchronized boolean saveCourse2_2(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course2_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveCourse3_1(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course3_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    public synchronized boolean saveCourse3_2(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course3_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveCourse4_1(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course4_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveCourse4_2(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course4_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveCourse5_1(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course5_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveCourse5_2(String course, String creditLoad) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("creditLoad", creditLoad);

            status = database.insert("course5_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimelineCourse1_1(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse1_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimelineCourse1_2(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse1_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimelineCourse2_1(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse2_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    public synchronized boolean saveTimelineCourse2_2(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse2_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimelineCourse3_1(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse3_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    public synchronized boolean saveTimelineCourse3_2(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse3_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimelineCourse4_1(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse4_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimelineCourse4_2(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse4_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimelineCourse5_1(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse5_1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimelineCourse5_2(String course, String grade) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("course", course);
            contentValues.put("grade", grade);

            status = database.insert("timelineCourse5_2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }




    synchronized boolean saveTimeTable1(String lecturer, String course, String startTime, String endTime, String venue) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lecturer", lecturer);
            contentValues.put("course", course);
            contentValues.put("startTime", startTime);
            contentValues.put("endTime", endTime);
            contentValues.put("venue", venue);

            status = database.insert("timeTable1", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimeTable2(String lecturer, String course, String startTime, String endTime, String venue) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lecturer", lecturer);
            contentValues.put("course", course);
            contentValues.put("startTime", startTime);
            contentValues.put("endTime", endTime);
            contentValues.put("venue", venue);

            status = database.insert("timeTable2", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimeTable3(String lecturer, String course, String startTime, String endTime, String venue) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lecturer", lecturer);
            contentValues.put("course", course);
            contentValues.put("startTime", startTime);
            contentValues.put("endTime", endTime);
            contentValues.put("venue", venue);

            status = database.insert("timeTable3", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimeTable4(String lecturer, String course, String startTime, String endTime, String venue) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lecturer", lecturer);
            contentValues.put("course", course);
            contentValues.put("startTime", startTime);
            contentValues.put("endTime", endTime);
            contentValues.put("venue", venue);

            status = database.insert("timeTable4", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimeTable5(String lecturer, String course, String startTime, String endTime, String venue) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lecturer", lecturer);
            contentValues.put("course", course);
            contentValues.put("startTime", startTime);
            contentValues.put("endTime", endTime);
            contentValues.put("venue", venue);

            status = database.insert("timeTable5", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    synchronized boolean saveTimeTable6(String lecturer, String course, String startTime, String endTime, String venue) {
        long status = -1;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lecturer", lecturer);
            contentValues.put("course", course);
            contentValues.put("startTime", startTime);
            contentValues.put("endTime", endTime);
            contentValues.put("venue", venue);

            status = database.insert("timeTable6", null, contentValues);
            System.out.println("Done");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong");
        }
        return status > -1;
    }

    ArrayList<TimeTableDetails> getTimeTable1() {
        ArrayList<TimeTableDetails> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timeTable1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String lecturer = cursor.getString(1);
                    String course = cursor.getString(2);
                    String startTime = cursor.getString(3);
                    String endTime = cursor.getString(4);
                    String venue = cursor.getString(5);
                    rows.add(new TimeTableDetails(id, lecturer, course, startTime, endTime, venue));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimeTableDetails> getTimeTable2() {
        ArrayList<TimeTableDetails> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timeTable2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String lecturer = cursor.getString(1);
                    String course = cursor.getString(2);
                    String startTime = cursor.getString(3);
                    String endTime = cursor.getString(4);
                    String venue = cursor.getString(5);
                    rows.add(new TimeTableDetails(id, lecturer, course, startTime, endTime, venue));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimeTableDetails> getTimeTable3() {
        ArrayList<TimeTableDetails> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timeTable3 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String lecturer = cursor.getString(1);
                    String course = cursor.getString(2);
                    String startTime = cursor.getString(3);
                    String endTime = cursor.getString(4);
                    String venue = cursor.getString(5);
                    rows.add(new TimeTableDetails(id, lecturer, course, startTime, endTime, venue));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimeTableDetails> getTimeTable4() {
        ArrayList<TimeTableDetails> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timeTable4 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String lecturer = cursor.getString(1);
                    String course = cursor.getString(2);
                    String startTime = cursor.getString(3);
                    String endTime = cursor.getString(4);
                    String venue = cursor.getString(5);
                    rows.add(new TimeTableDetails(id, lecturer, course, startTime, endTime, venue));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimeTableDetails> getTimeTable5() {
        ArrayList<TimeTableDetails> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timeTable5 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String lecturer = cursor.getString(1);
                    String course = cursor.getString(2);
                    String startTime = cursor.getString(3);
                    String endTime = cursor.getString(4);
                    String venue = cursor.getString(5);
                    rows.add(new TimeTableDetails(id, lecturer, course, startTime, endTime, venue));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimeTableDetails> getTimeTable6() {
        ArrayList<TimeTableDetails> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timeTable6 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String lecturer = cursor.getString(1);
                    String course = cursor.getString(2);
                    String startTime = cursor.getString(3);
                    String endTime = cursor.getString(4);
                    String venue = cursor.getString(5);
                    rows.add(new TimeTableDetails(id, lecturer, course, startTime, endTime, venue));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }


    ArrayList<CourseList> getCourse1_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course1_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCourse1_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course1_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCourse2_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course2_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    public ArrayList<CourseList> getCourse2_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course2_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCourse3_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course3_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    public ArrayList<CourseList> getCourse3_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course3_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCourse4_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course4_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCourse4_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course4_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCourse5_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course5_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCourse5_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course5_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(id, courseName, creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCredit1_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course1_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }
    ArrayList<CourseList> getCredit1_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course1_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCredit2_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course2_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }
    ArrayList<CourseList> getCredit2_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course2_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCredit3_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course3_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }
    ArrayList<CourseList> getCredit3_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course3_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCredit4_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course4_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }
    ArrayList<CourseList> getCredit4_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course4_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<CourseList> getCredit5_1() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course5_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }
    ArrayList<CourseList> getCredit5_2() {
        ArrayList<CourseList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM course5_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String creditLoad = cursor.getString(2);
                    rows.add(new CourseList(creditLoad));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse1_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse1_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse1_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse1_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse2_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse2_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse2_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse2_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse3_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse3_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse3_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse3_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse4_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse4_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse4_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse4_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse5_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse5_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getTimelineCourse5_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse5_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(id, courseName, grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade1_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse1_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade1_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse1_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade2_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse2_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade2_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse2_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade3_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse3_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade3_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse3_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade4_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse4_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade4_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse4_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade5_1() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse5_1 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    ArrayList<TimelineList> getGrade5_2() {
        ArrayList<TimelineList> rows = new ArrayList<>();
        Cursor cursor = null;
        try(SQLiteDatabase database = this.getWritableDatabase()) {
            String command = "SELECT * FROM timelineCourse5_2 ORDER BY id ASC";
            cursor = database.rawQuery(command, null);
            if(cursor.moveToFirst()) {
                do {
                    //int id = cursor.getInt(0);
                    //String courseName = cursor.getString(1);
                    String grade = cursor.getString(2);
                    rows.add(new TimelineList(grade));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  rows;
    }

    void deleteFromTimetable1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timeTable1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    void deleteFromTimetable2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timeTable2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    void deleteFromTimetable3(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timeTable3 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    void deleteFromTimetable4(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timeTable4 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    void deleteFromTimetable5(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timeTable5 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    void deleteFromTimetable6(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timeTable6 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse1_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course1_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse1_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course1_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse2_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course2_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse2_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course2_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse3_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course3_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse3_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course3_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse4_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course4_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse4_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course4_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse5_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course5_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromCourse5_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM course5_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse1_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse1_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse1_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse1_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse2_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse2_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse2_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse2_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse3_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse3_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse3_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse3_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse4_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse4_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse4_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse4_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse5_1(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse5_1 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    public void deleteFromTimelineCourse5_2(int id, String course) {
        SQLiteDatabase database = null;
        try {
            database = this.getWritableDatabase();
            String query = "DELETE FROM timelineCourse5_2 WHERE id ='" + id +"' AND course = '" + course + "'";
            database.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(database != null)
                database.close();
        }
    }

    int updateCredit1_1(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course1_1", contentValues, "id=?", new String[]{String.valueOf(id)});

    }

    int updateCredit1_2(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course1_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateCredit2_1(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course2_1", contentValues, "id=?", new String[]{String.valueOf(id)});

    }

    public int updateCredit2_2(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course2_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateCredit3_1(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course3_1", contentValues, "id=?", new String[]{String.valueOf(id)});

    }

    public int updateCredit3_2(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course3_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateCredit4_1(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course4_1", contentValues, "id=?", new String[]{String.valueOf(id)});

    }

    int updateCredit4_2(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course4_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateCredit5_1(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course5_1", contentValues, "id=?", new String[]{String.valueOf(id)});

    }

    int updateCredit5_2(int id, String credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("creditLoad", credit);

        return database.update("course5_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade1_1(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse1_1", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade1_2(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse1_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade2_1(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse2_1", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade2_2(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse2_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade3_1(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse3_1", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade3_2(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse3_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade4_1(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse4_1", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade4_2(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse4_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade5_1(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse5_1", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    int updateGrade5_2(int id, String grade) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("grade", grade);

        return database.update("timelineCourse5_2", contentValues, "id=?", new String[]{String.valueOf(id)});
    }

    public Cursor getUserDetails() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM userDetails", null);
        return cursor;
    }

    public Cursor getCourse() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM course", null);
        return cursor;
    }
}
