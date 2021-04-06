package com.chocolatedevelopers.stuganizer.fragments;

import android.content.Context;
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
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineSecond2 extends Fragment implements RecyclerInterface {
    CircularProgressBar progressBar;
    TextView textResult;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TimelineListAdapter adapter;
    Context context;
    ArrayList<TimelineList> timelineListArrayList;
    MySqLiteConnector connector;
    Float mark, totalMark = 0f, cLoad, totalCLoad=0f, GP;
    View view;
    public TimelineSecond2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timeline_second2, container, false);
        context = getContext();
        recyclerView = view.findViewById(R.id.timeline_recycler_2);
        timelineListArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TimelineListAdapter(context, TimelineSecond2.this, timelineListArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        connector = new MySqLiteConnector(getContext());

        progressBar = view.findViewById(R.id.progress2);
        textResult = view.findViewById(R.id.result_text2);

        ArrayList<CourseList> allCredits = connector.getCredit2_2();
        ArrayList<TimelineList> allGrades = connector.getGrade2_2();

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

        ArrayList<TimelineList> allData = connector.getTimelineCourse2_2();
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
        ArrayList<TimelineList> list = connector.getTimelineCourse2_2();
        if(list.size() > 0) {
            int id = list.get(position).getId();
            connector.updateGrade2_2(id, data);
        }

            Float credLoad, mark, totalMark=0f, totalCredLoad=0f, GPA;

            ArrayList<CourseList> allCredits = connector.getCredit2_2();
            ArrayList<TimelineList> allGrades = connector.getGrade2_2();

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
