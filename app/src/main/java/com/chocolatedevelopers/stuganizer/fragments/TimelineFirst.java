package com.chocolatedevelopers.stuganizer.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chocolatedevelopers.stuganizer.lists.CourseList;
import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.interfaces.RecyclerInterface;
import com.chocolatedevelopers.stuganizer.lists.TimelineList;
import com.chocolatedevelopers.stuganizer.adapters.TimelineListAdapter;
import com.chocolatedevelopers.stuganizer.activities.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFirst extends Fragment implements RecyclerInterface {

    CircularProgressBar progressBar;
    TextView textResult;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TimelineListAdapter adapter;
    Context context;
    ArrayList<TimelineList> timelineListArrayList;
    MySqLiteConnector connector;
    Float mark, totalMark = 0f, cLoad, totalCLoad=0f, GP;
    Float altCred=0f;
    View view;
    public TimelineFirst() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timeline_first, container, false);
        context = getContext();
        recyclerView = view.findViewById(R.id.timeline_recycler);
        timelineListArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TimelineListAdapter(context, TimelineFirst.this, timelineListArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        connector = new MySqLiteConnector(getContext());

        progressBar = view.findViewById(R.id.progress);
        textResult = view.findViewById(R.id.result_text);

        ArrayList<CourseList> allCredits = connector.getCredit1_1();
        ArrayList<TimelineList> allGrades = connector.getGrade1_1();
        if(allCredits.size() > 0) {
            for (int i = 0; i < allCredits.size(); i++) {
                String c = allCredits.get(i).getCreditLoad();
                if (c.equals("1") || c.equals("2") || c.equals("3") || c.equals("4") || c.equals("5") || c.equals("6")) {
                    altCred = altCred + Float.parseFloat(c);
                }
            }
            if (altCred == 0f) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(), R.style.AlertDialogTheme);
                builder.setTitle("Hehehe! No hurry in life");
                builder.setMessage("You need to set your credit loads in your homepage before before your GPA can be computed.");
                builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    }
                });
                builder.show();
            }
        }

        if(allCredits.size() > 0) {
            for (int i = 0; i < allCredits.size(); i++) {
                String credit = allCredits.get(i).getCreditLoad();
                String grade = allGrades.get(i).getGrade();

                if (credit.equals("1") || credit.equals("2") || credit.equals("3") || credit.equals("4") || credit.equals("5") || credit.equals("6")) {
                    if (grade.equals("A")) {
                        mark = 5f;
                        cLoad = Float.parseFloat(credit);
                        totalCLoad = totalCLoad + cLoad;
                        totalMark = totalMark + (mark * cLoad);
                    } else if (grade.equals("B")) {
                        mark = 4f;
                        cLoad = Float.parseFloat(credit);
                        totalCLoad = totalCLoad + cLoad;
                        totalMark = totalMark + (mark * cLoad);
                    } else if (grade.equals("C")) {
                        mark = 3f;
                        cLoad = Float.parseFloat(credit);
                        totalCLoad = totalCLoad + cLoad;
                        totalMark = totalMark + (mark * cLoad);
                    } else if (grade.equals("D")) {
                        mark = 2f;
                        cLoad = Float.parseFloat(credit);
                        totalCLoad = totalCLoad + cLoad;
                        totalMark = totalMark + (mark * cLoad);
                    } else if (grade.equals("E")) {
                        mark = 1f;
                        cLoad = Float.parseFloat(credit);
                        totalCLoad = totalCLoad + cLoad;
                        totalMark = totalMark + (mark * cLoad);
                    } else if (grade.equals("F")) {
                        cLoad = Float.parseFloat(credit);
                        totalCLoad = totalCLoad + cLoad;
                    }
                }
            }

        }

            GP = totalMark / totalCLoad;
            if (GP > 0) {
                Animate(progressBar, textResult, round(GP));
            } else {
                Animate(progressBar, textResult, 0.0f);
            }


        ArrayList<TimelineList> allData = connector.getTimelineCourse1_1();
        if(allData.size() > 0) {
            for(int i = 0; i < allData.size(); i++) {
                timelineListArrayList.add(allData.get(i));
            }
            adapter.notifyDataSetChanged();
        }
        return view;
    }

    private void Animate(CircularProgressBar progressBar, TextView textResult, Float result) {
        progressBar.setProgressWithAnimation(result, 3000L);
        textResult.setText(String.valueOf(result));
    }

    @Override
    public void getPosition(int position, String data) {
        ArrayList<TimelineList> list = connector.getTimelineCourse1_1();
        if(list.size() > 0) {
            int id = list.get(position).getId();
            connector.updateGrade1_1(id, data);
        }

            Float credLoad, mark, totalMark=0f, totalCredLoad=0f, GPA;

            ArrayList<CourseList> allCredits = connector.getCredit1_1();
            ArrayList<TimelineList> allGrades = connector.getGrade1_1();

            if(allCredits.size() > 0) {
                for (int i = 0; i < allCredits.size(); i++) {
                    String credit = allCredits.get(i).getCreditLoad();
                    String grade = allGrades.get(i).getGrade();

                    if (credit.equals("1") || credit.equals("2") || credit.equals("3") || credit.equals("4") || credit.equals("5") || credit.equals("6")) {
                        if (grade.equals("A")) {
                            mark = 5f;
                            credLoad = Float.parseFloat(credit);
                            totalCredLoad = totalCredLoad + credLoad;
                            totalMark = totalMark + (mark * credLoad);
                        } else if (grade.equals("B")) {
                            mark = 4f;
                            credLoad = Float.parseFloat(credit);
                            totalCredLoad = totalCredLoad + credLoad;
                            totalMark = totalMark + (mark * credLoad);
                        } else if (grade.equals("C")) {
                            mark = 3f;
                            credLoad = Float.parseFloat(credit);
                            totalCredLoad = totalCredLoad + credLoad;
                            totalMark = totalMark + (mark * credLoad);
                        } else if (grade.equals("D")) {
                            mark = 2f;
                            credLoad = Float.parseFloat(credit);
                            totalCredLoad = totalCredLoad + credLoad;
                            totalMark = totalMark + (mark * credLoad);
                        } else if (grade.equals("E")) {
                            mark = 1f;
                            credLoad = Float.parseFloat(credit);
                            totalCredLoad = totalCredLoad + credLoad;
                            totalMark = totalMark + (mark * credLoad);
                        } else if (grade.equals("F")) {
                            credLoad = Float.parseFloat(credit);
                            totalCredLoad = totalCredLoad + credLoad;
                        }
                    }
                }
            }

            GPA = totalMark / totalCredLoad;
            if (GPA > 0) {
                Animate(progressBar, textResult, round(GPA));
            } else {
                Animate(progressBar, textResult, 0.0f);
            }

        }

        public Float round(Float number) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            return Float.valueOf(twoDForm.format(number));
        }
}
