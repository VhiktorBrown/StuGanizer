package com.chocolatedevelopers.stuganizer.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.chocolatedevelopers.stuganizer.interfaces.ClickInterface;
import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.adapters.TimeTableAdapter;
import com.chocolatedevelopers.stuganizer.lists.TimeTableDetails;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Monday extends Fragment implements ClickInterface {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<TimeTableDetails> timeTableDetailsArrayList;
    Context context;
    FloatingActionButton fab;
    TimeTableAdapter adapter;
    MySqLiteConnector connector;

    View view;
    public Monday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_monday, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_monday);
        context = getContext();
        timeTableDetailsArrayList = new ArrayList<>();
        fab = view.findViewById(R.id.fab_monday);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TimeTableAdapter(Monday.this, timeTableDetailsArrayList, context, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        connector = new MySqLiteConnector(getContext());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(), R.style.MyDialogTheme);
                dialog.setContentView(R.layout.add_timetable_dialog);
                dialog.setCancelable(true);

                final EditText lecturerName = dialog.findViewById(R.id.lecturer_name);
                final EditText courseName = dialog.findViewById(R.id.course_name);
                final TextView startTime = dialog.findViewById(R.id.start_time);
                final TextView endTime = dialog.findViewById(R.id.end_time);
                final EditText venueName = dialog.findViewById(R.id.venue_name);
                View done = dialog.findViewById(R.id.done_button);
                View cancel = dialog.findViewById(R.id.cancel_button);
                dialog.show();

                startTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog timePickerDialog;
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);
                        timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                TextView startTime = dialog.findViewById(R.id.start_time);
                                startTime.setText(pad(hourOfDay) + ":" + pad(minute));
                            }
                        }, hour, minute, false);
                        timePickerDialog.show();
                    }
                });

                endTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog timePickerDialog;
                        Calendar calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);
                        timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                TextView endTime = dialog.findViewById(R.id.end_time);
                                endTime.setText(pad(hourOfDay) + ":" + pad(minute));
                            }
                        }, hour, minute, false);
                        timePickerDialog.show();
                    }
                });

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String lecturer = lecturerName.getText().toString();
                        String course = courseName.getText().toString();
                        String start = startTime.getText().toString();
                        String end = endTime.getText().toString();
                        String venue = venueName.getText().toString();

                        TimeTableDetails details = new TimeTableDetails(lecturer, course, start, end, venue);
                        if(course.length() > 0 ) {
                            timeTableDetailsArrayList.add(details);
                            saveTimetable(lecturer, course, start, end, venue);
                            adapter.notifyDataSetChanged();
                        } else {
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(), R.style.AlertDialogTheme);
                            builder.setTitle("Empty field");
                            builder.setMessage("You have to at least fill in the name of the course.");
                            builder.setIcon(R.drawable.ic_info);
                            builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            builder.show();
                        }
                        dialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        ArrayList<TimeTableDetails> oldData = new MySqLiteConnector(getContext()).getTimeTable1();
        if(oldData.size() > 0) {
            for(int i = 0; i < oldData.size(); i++) {
                timeTableDetailsArrayList.add(oldData.get(i));
            }
            adapter.notifyDataSetChanged();
        }
        return view;
    }

    public void saveTimetable(String lecturer, String course, String startTime, String endTime, String venue) {
        boolean insert = connector.saveTimeTable1(lecturer, course, startTime, endTime, venue);
        if(insert) {
            Toast.makeText(getContext(), "Saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Couldn't save", Toast.LENGTH_SHORT).show();
        }
    }
    public String pad(int minute) {
        if(minute < 10)
            return "0"+minute;
        return String.valueOf(minute);
    }

    @Override
    public void OnLongClick(final int position) {
        ArrayList<TimeTableDetails> list = connector.getTimeTable1();
        final int id = list.get(position).getId();
        final String course = list.get(position).getCourseName();
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(), R.style.AlertDialogTheme);
        builder.setTitle("Delete timetable tab?");
        builder.setMessage("Are you sure you want to delete this tab?");
        builder.setIcon(R.drawable.ic_info);
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                connector.deleteFromTimetable1(id, course);
                timeTableDetailsArrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
