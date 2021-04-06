package com.chocolatedevelopers.stuganizer.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chocolatedevelopers.stuganizer.lists.CourseList;
import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.lists.TimelineList;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalGpa2 extends Fragment {
    CircularProgressBar progressBar;
    TextView textView;
    View view;
    MySqLiteConnector connector;
    ArrayList<String> allCredits;
    ArrayList<String> allGrades;
    Float mark, totalMark = 0f, cLoad, totalCLoad=0f, GP;
    FloatingActionButton fab;
    public TotalGpa2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_total_gpa2, container, false);

        progressBar = view.findViewById(R.id.progress3);
        textView = view.findViewById(R.id.result_text3);

        connector = new MySqLiteConnector(getContext());
        fab = view.findViewById(R.id.show_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allCredits = new ArrayList<>();
                allGrades = new ArrayList<>();

                ArrayList<CourseList> credit = connector.getCredit2_1();
                ArrayList<CourseList> credit2 = connector.getCredit2_2();
                ArrayList<TimelineList> grade = connector.getGrade2_1();
                ArrayList<TimelineList> grade2 = connector.getGrade2_2();

                if(credit.size() > 0) {
                    for (int i = 0; i < credit.size(); i++) {
                        String cred = credit.get(i).getCreditLoad();
                        allCredits.add(cred);
                    }
                    for (int i = 0; i < grade.size(); i++) {
                        String grad = grade.get(i).getGrade();
                        allGrades.add(grad);
                    }
                }

                if(credit2.size() > 0) {
                    for (int i = 0; i < credit2.size(); i++) {
                        String cred = credit2.get(i).getCreditLoad();
                        allCredits.add(cred);
                    }
                    for (int i = 0; i < grade2.size(); i++) {
                        String grad = grade2.get(i).getGrade();
                        allGrades.add(grad);
                    }
                }

                if(allCredits.size() > 0) {
                    for (int i = 0; i < allCredits.size(); i++) {
                        String cred = allCredits.get(i);
                        String grad = allGrades.get(i);
                        if(cred.equals("1") || cred.equals("2") || cred.equals("3") || cred.equals("4") || cred.equals("5") || cred.equals("6")) {
                            if(grad.equals("A")) {
                                mark = 5f;
                                cLoad = Float.parseFloat(cred);
                                totalCLoad =totalCLoad + cLoad;
                                totalMark = totalMark + (mark * cLoad);
                            } else if(grad.equals("B")) {
                                mark = 4f;
                                cLoad = Float.parseFloat(cred);
                                totalCLoad =totalCLoad + cLoad;
                                totalMark = totalMark + (mark * cLoad);
                            } else if(grad.equals("C")) {
                                mark = 3f;
                                cLoad = Float.parseFloat(cred);
                                totalCLoad =totalCLoad + cLoad;
                                totalMark = totalMark + (mark * cLoad);
                            } else if(grad.equals("D")) {
                                mark = 2f;
                                cLoad = Float.parseFloat(cred);
                                totalCLoad =totalCLoad + cLoad;
                                totalMark = totalMark + (mark * cLoad);
                            } else if(grad.equals("E")) {
                                mark = 1f;
                                cLoad = Float.parseFloat(cred);
                                totalCLoad =totalCLoad + cLoad;
                                totalMark = totalMark + (mark * cLoad);
                            } else if(grad.equals("F")) {
                                cLoad = Float.parseFloat(cred);
                                totalCLoad =totalCLoad + cLoad;
                            }
                        }
                    }
                }

                GP = totalMark / totalCLoad;

                if(GP > 0) {
                    Animate(progressBar, textView, round(GP));
                } else {
                    Animate(progressBar, textView, 0.0f);
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(), R.style.AlertDialogTheme);
                    builder.setTitle("No GPA yet");
                    builder.setMessage("Sorry, but you don't seem to have set your credit loads or grades for any of the semesters. Only then can we compute your gpa");
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


        return view;
    }
    private void Animate(CircularProgressBar progressBar, TextView textResult, Float result) {
        progressBar.setProgressWithAnimation(result, 3000L);
        textResult.setText(String.valueOf(result));
    }

    public Float round(Float number) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(number));
    }
}
