package com.chocolatedevelopers.stuganizer.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.interfaces.ClickInterface;
import com.chocolatedevelopers.stuganizer.lists.CourseList;
import com.chocolatedevelopers.stuganizer.adapters.CourseListAdapter;
import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.interfaces.RecyclerInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondSemester3 extends Fragment implements RecyclerInterface, ClickInterface {

    View view;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    LinearLayoutManager layoutManager;
    CourseListAdapter adapter;
    ArrayList<CourseList> courseListArrayList;
    Context context;
    MySqLiteConnector connector;
    String user;
    String creditLoad, grade;
    public SecondSemester3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_second_semester3, container, false);

        context = getContext();
        courseListArrayList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_second);
        fab = view.findViewById(R.id.fab_second);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CourseListAdapter(context, SecondSemester3.this, courseListArrayList, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        connector = new MySqLiteConnector(getContext());
        Cursor cursor = connector.getUserDetails();
        if(cursor.getCount() == 0) {

        } else {
            while(cursor.moveToNext()) {
                user = cursor.getString(1);
            }
        }


        creditLoad = "--CL--";
        grade = "-Grade-";
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(), R.style.AlertDialogTheme2);
                LinearLayout layout = new LinearLayout(getContext());
                builder.setIcon(R.drawable.ic_import_contacts_black_24dp);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setLayoutParams(layoutParams);
                layout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                layout.setGravity(Gravity.CLIP_VERTICAL);
                layout.setPadding(2,2,2,2);

                TextView textMessage = new TextView(getContext());
                textMessage.setText(user + ", please type in the course you wish to add.");
                textMessage.setTypeface(Typeface.SERIF);
                textMessage.setTextSize(14);
                textMessage.setTextColor(getResources().getColor(R.color.appColor));
                textMessage.setPadding(10, 10, 10, 10);
                textMessage.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textParams.bottomMargin = 5;

                final TextInputEditText textInputEditText = new TextInputEditText(getContext());
                textInputEditText.setPadding(10, 10, 10, 10);
                textInputEditText.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layout.addView(textMessage, textParams);
                layout.addView(textInputEditText, editTextParams);
                builder.setTitle("New Course");
                builder.setView(layout);
                builder.setCancelable(true);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(textInputEditText.length() > 0) {
                            CourseList courseList = new CourseList(textInputEditText.getText().toString(), creditLoad);
                            courseListArrayList.add(courseList);
                            saveNewCourse(textInputEditText.getText().toString(), creditLoad);
                            saveTimelineCourse(textInputEditText.getText().toString(), grade);
                            adapter.notifyDataSetChanged();

                        }
                    }
                });

                builder.setNegativeButton("Cancel", null);
                builder.show();

            }
        });

        ArrayList<CourseList> oldData = new MySqLiteConnector(getContext()).getCourse3_2();
        Toast.makeText(getContext(), "All data: " + oldData.size(), Toast.LENGTH_SHORT).show();
        if(oldData.size() > 0) {
            for(int i = 0; i < oldData.size(); i++) {
                courseListArrayList.add(oldData.get(i));
            }
            adapter.notifyDataSetChanged();
        }

       return view;
    }

    private void saveNewCourse(String course, String credit) {
        boolean insert = connector.saveCourse3_2(course, credit);
        if(insert) {
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), " Couldn't Save", Toast.LENGTH_LONG).show();
        }
    }

    private void saveTimelineCourse(String course, String grade) {
        boolean insert = connector.saveTimelineCourse3_2(course, grade);
        if(insert) {
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), " Couldn't Save", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void getPosition(int position, String data) {
        ArrayList<CourseList> list = connector.getCourse3_2();
        int id = list.get(position).getId();
        connector.updateCredit3_2(id, data);
    }

    @Override
    public void OnLongClick(final int position) {
        ArrayList<CourseList> list2 = connector.getCourse3_2();

        final String course = list2.get(position).getCourseName();
        final int id = list2.get(position).getId();
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(), R.style.AlertDialogTheme);
        builder.setTitle("Delete course?");
        builder.setMessage("Are you sure you want to delete this course from list?");
        builder.setIcon(R.drawable.ic_info);
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                connector.deleteFromCourse3_2(id, course);
                connector.deleteFromTimelineCourse3_2(id, course);
                courseListArrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
