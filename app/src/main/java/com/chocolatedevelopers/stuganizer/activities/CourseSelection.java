package com.chocolatedevelopers.stuganizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.lists.SelectedCourses;
import com.chocolatedevelopers.stuganizer.adapters.CourseSelectionAdapter;
import com.chocolatedevelopers.stuganizer.interfaces.RecyclerInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class CourseSelection extends AppCompatActivity implements RecyclerInterface {
    TextView welcomeText;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Button doneButton;
    ArrayList<SelectedCourses> selectedCoursesArrayList;
    String user;
    MySqLiteConnector connector;
    CourseSelectionAdapter adapter;
    ArrayList<String> listOfSelectedCourses;
    EditText searchCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);

        connector = new MySqLiteConnector(this);
        Cursor cursor = connector.getUserDetails();
        if(cursor.getCount() == 0) {

        } else {
            while(cursor.moveToNext()) {
                user = cursor.getString(1);
            }
        }
        selectedCoursesArrayList = new ArrayList<>();
        listOfSelectedCourses = new ArrayList<>();
        adapter = new CourseSelectionAdapter(this, selectedCoursesArrayList, connector, this);

        welcomeText = findViewById(R.id.user_name);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);

        searchCourse = findViewById(R.id.select_course_editText);
        welcomeText.setText("Hi, " + user);

        searchCourse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        selectedCoursesArrayList.add(new SelectedCourses("Accountancy "));
        selectedCoursesArrayList.add(new SelectedCourses("Adult Education "));
        selectedCoursesArrayList.add(new SelectedCourses("Agriculture Economics "));
        selectedCoursesArrayList.add(new SelectedCourses("Agro-based Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("Animal Science "));
        selectedCoursesArrayList.add(new SelectedCourses("Applied Biochemistry "));
        selectedCoursesArrayList.add(new SelectedCourses("Architecture "));
        selectedCoursesArrayList.add(new SelectedCourses("Banking & Finance "));
        selectedCoursesArrayList.add(new SelectedCourses("Botany "));
        selectedCoursesArrayList.add(new SelectedCourses("Building "));
        selectedCoursesArrayList.add(new SelectedCourses("Business Admin "));
        selectedCoursesArrayList.add(new SelectedCourses("Chemical Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("Chinese "));
        selectedCoursesArrayList.add(new SelectedCourses("Civil Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("Commercial Law "));
        selectedCoursesArrayList.add(new SelectedCourses("Computer Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("Computer Science "));
        selectedCoursesArrayList.add(new SelectedCourses("Cooperative Economics "));
        selectedCoursesArrayList.add(new SelectedCourses("Crop Science "));
        selectedCoursesArrayList.add(new SelectedCourses("Education Management "));
        selectedCoursesArrayList.add(new SelectedCourses("Electrical Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("English and Literature"));
        selectedCoursesArrayList.add(new SelectedCourses("Entrepreneurial Studies "));
        selectedCoursesArrayList.add(new SelectedCourses("Environment Management "));
        selectedCoursesArrayList.add(new SelectedCourses("Estate Management "));
        selectedCoursesArrayList.add(new SelectedCourses("Fine & Applied Arts "));
        selectedCoursesArrayList.add(new SelectedCourses("Fisheries "));
        selectedCoursesArrayList.add(new SelectedCourses("Forestry & Wildlife "));
        selectedCoursesArrayList.add(new SelectedCourses("Geography & Meteorology "));
        selectedCoursesArrayList.add(new SelectedCourses("Geology "));
        selectedCoursesArrayList.add(new SelectedCourses("Geophysics "));
        selectedCoursesArrayList.add(new SelectedCourses("Guidance & Counselling "));
        selectedCoursesArrayList.add(new SelectedCourses("History and Int. Studies "));
        selectedCoursesArrayList.add(new SelectedCourses("Human Kinetics "));
        selectedCoursesArrayList.add(new SelectedCourses("Igbo, African studies "));
        selectedCoursesArrayList.add(new SelectedCourses("Industrial Chemistry "));
        selectedCoursesArrayList.add(new SelectedCourses("Industrial Physics "));
        selectedCoursesArrayList.add(new SelectedCourses("Industrial & Production Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("International Law "));
        selectedCoursesArrayList.add(new SelectedCourses("Library & Information "));
        selectedCoursesArrayList.add(new SelectedCourses("Linguistics"));
        selectedCoursesArrayList.add(new SelectedCourses("Marketing "));
        selectedCoursesArrayList.add(new SelectedCourses("Mathematics "));
        selectedCoursesArrayList.add(new SelectedCourses("Mechanical Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("Medicine "));
        selectedCoursesArrayList.add(new SelectedCourses("Medical Lab Science "));
        selectedCoursesArrayList.add(new SelectedCourses("Medical Rehab "));
        selectedCoursesArrayList.add(new SelectedCourses("Metallurgical Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("Microbiology and Brewing "));
        selectedCoursesArrayList.add(new SelectedCourses("Music "));
        selectedCoursesArrayList.add(new SelectedCourses("Nursing Science "));
        selectedCoursesArrayList.add(new SelectedCourses("Polymer Engineering "));
        selectedCoursesArrayList.add(new SelectedCourses("Parasitology and Entomology "));
        selectedCoursesArrayList.add(new SelectedCourses("Philosophy "));
        selectedCoursesArrayList.add(new SelectedCourses("Physics "));
        selectedCoursesArrayList.add(new SelectedCourses("Public Administration "));
        selectedCoursesArrayList.add(new SelectedCourses("Quantity Surveying "));
        selectedCoursesArrayList.add(new SelectedCourses("Radiography "));
        selectedCoursesArrayList.add(new SelectedCourses("Religion and Human Relations "));
        selectedCoursesArrayList.add(new SelectedCourses("Science Edu "));
        selectedCoursesArrayList.add(new SelectedCourses("Soil Science "));
        selectedCoursesArrayList.add(new SelectedCourses("Statistics"));
        selectedCoursesArrayList.add(new SelectedCourses("Theatre and film studies "));
        selectedCoursesArrayList.add(new SelectedCourses("Zoology "));


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(CourseSelection.this, R.style.AlertDialogTheme);
        builder.setTitle("Select your department/course");
        builder.setMessage("Type in the search box to search for your department and select to continue");
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing.
            }
        });
        builder.show();


    }

    @Override
    public void getPosition(final int position, String data) {

    }

    private void saveNewCourse(String course, String credit) {
        boolean insert = connector.saveCourse1_1(course, credit);
        if(insert) {
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, " Couldn't Save", Toast.LENGTH_LONG).show();
        }
    }

    private void saveTimelineCourse(String course, String grade) {
        boolean insert = connector.saveTimelineCourse1_1(course, grade);
        if(insert) {
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, " Couldn't Save", Toast.LENGTH_LONG).show();
        }
    }

    private void filter(String text) {
        ArrayList<SelectedCourses> list = new ArrayList<>();
        for(SelectedCourses courses : selectedCoursesArrayList) {
            if(courses.getCourseTitle().toLowerCase().contains(text.toLowerCase()) ) {
                list.add(courses);
            }
            adapter.filterList(list);
        }
    }
}
