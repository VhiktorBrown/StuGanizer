package com.chocolatedevelopers.stuganizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chocolatedevelopers.stuganizer.adapters.ComputationAdapter;
import com.chocolatedevelopers.stuganizer.lists.ComputationModel;
import com.chocolatedevelopers.stuganizer.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ComputationActivity extends AppCompatActivity {
RecyclerView recyclerView;
LinearLayoutManager layoutManager;
ArrayList<ComputationModel> computationModelArrayList;
ComputationAdapter adapter;
Toolbar toolbar;
Button computeButton;
TextView gpaTextView;
String selectedGPA;
int numOfCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computation);

        toolbar = findViewById(R.id.computation_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Computation Arena");

        computeButton = findViewById(R.id.compute_button);
        gpaTextView = findViewById(R.id.selected_courses);

        recyclerView = findViewById(R.id.computation_recycler);
        layoutManager = new LinearLayoutManager(this);
        computationModelArrayList = new ArrayList<>();
        adapter = new ComputationAdapter(this, computationModelArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            selectedGPA = bundle.getString("maxGpa");
            numOfCourses = bundle.getInt("totalCourses");
        }
        gpaTextView.setText("You have chosen to compute GPA for " + numOfCourses + " courses");

        for(int i = 0; i < numOfCourses; i++) {
            ComputationModel model = new ComputationModel();
            computationModelArrayList.add(model);
        }

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedGPA.equals("4.0 GPA")) {
                    String grade;
                    double creditLoad, mark, totalMark=0.0, totalCreditLoad=0.0, GP;
                    boolean check=true;
                    int i =0;
                    while(i < numOfCourses) {
                        grade = computationModelArrayList.get(i).getGrade();
                        if(computationModelArrayList.get(i).getCreditLoad().length() > 0 && computationModelArrayList.get(i).getGrade().length() > 0 && (grade.equals("A") || grade.equals("a") || grade.equals("B") || grade.equals("b") || grade.equals("C") || grade.equals("c") || grade.equals("D") || grade.equals("d") || grade.equals("E") || grade.equals("e") || grade.equals("F") || grade.equals("f"))) {
                            creditLoad = Double.parseDouble(computationModelArrayList.get(i).getCreditLoad());
                                if (grade.equals("A") || grade.equals("a")) {
                                    mark = 4.0;
                                    totalMark = totalMark + (mark * creditLoad);
                                    totalCreditLoad = totalCreditLoad + creditLoad;
                                } else if (grade.equals("B") || grade.equals("b")) {
                                    mark = 3.0;
                                    totalMark = totalMark + (mark * creditLoad);
                                    totalCreditLoad = totalCreditLoad + creditLoad;
                                } else if (grade.equals("C") || grade.equals("c")) {
                                    mark = 2.0;
                                    totalMark = totalMark + (mark * creditLoad);
                                    totalCreditLoad = totalCreditLoad + creditLoad;
                                } else if (grade.equals("D") || grade.equals("d")) {
                                    mark = 1.0;
                                    totalMark = totalMark + (mark * creditLoad);
                                    totalCreditLoad = totalCreditLoad + creditLoad;
                                } else if (grade.equals("E") || grade.equals("e")) {

                                    totalCreditLoad = totalCreditLoad + creditLoad;
                                } else {
                                    totalCreditLoad = totalCreditLoad + creditLoad;
                                }
                        }else {
                            i = numOfCourses;
                            check = false;
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ComputationActivity.this, R.style.AlertDialogTheme);
                            builder.setTitle("Invalid inputs");
                            builder.setMessage("Please, make sure grades are within A to F before clicking the compute button. And make sure no field is left empty.");
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
                        i++;
                    }
                    if(check) {
                        GP = totalMark / totalCreditLoad;
                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ComputationActivity.this, R.style.ComputationAlertDialogTheme);
                        builder.setMessage("Your GP is: " + round(GP));
                        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();

                    }

                } else if(selectedGPA.equals("5.0 GPA")) {
                    String grade;
                    double creditLoad, mark, totalMark=0.0, totalCreditLoad=0.0, GP;
                    boolean check=true;
                    int i =0;
                    while(i < numOfCourses) {
                        grade = computationModelArrayList.get(i).getGrade();
                        if(computationModelArrayList.get(i).getCreditLoad().length() > 0 && computationModelArrayList.get(i).getGrade().length() > 0 && (grade.equals("A") || grade.equals("a") || grade.equals("B") || grade.equals("b") || grade.equals("C") || grade.equals("c") || grade.equals("D") || grade.equals("d") || grade.equals("E") || grade.equals("e") || grade.equals("F") || grade.equals("f"))) {
                            creditLoad = Double.parseDouble(computationModelArrayList.get(i).getCreditLoad());
                            if (grade.equals("A") || grade.equals("a")) {
                                mark = 5.0;
                                totalMark = totalMark + (mark * creditLoad);
                                totalCreditLoad = totalCreditLoad + creditLoad;
                            } else if (grade.equals("B") || grade.equals("b")) {
                                mark = 4.0;
                                totalMark = totalMark + (mark * creditLoad);
                                totalCreditLoad = totalCreditLoad + creditLoad;
                            } else if (grade.equals("C") || grade.equals("c")) {
                                mark = 3.0;
                                totalMark = totalMark + (mark * creditLoad);
                                totalCreditLoad = totalCreditLoad + creditLoad;
                            } else if (grade.equals("D") || grade.equals("d")) {
                                mark = 2.0;
                                totalMark = totalMark + (mark * creditLoad);
                                totalCreditLoad = totalCreditLoad + creditLoad;
                            } else if (grade.equals("E") || grade.equals("e")) {
                                mark = 1.0;
                                totalMark = totalMark + (mark * creditLoad);
                                totalCreditLoad = totalCreditLoad + creditLoad;
                            } else {
                                totalCreditLoad = totalCreditLoad + creditLoad;
                            }
                        }else {
                            i = numOfCourses;
                            check = false;
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ComputationActivity.this, R.style.AlertDialogTheme);
                            builder.setTitle("Invalid inputs");
                            builder.setMessage("Please, make sure grades are within A to F before clicking the compute button. And make sure no field is left empty.");
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
                        i++;
                    }
                    if(check) {
                        GP = totalMark / totalCreditLoad;
                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ComputationActivity.this, R.style.ComputationAlertDialogTheme);
                        builder.setMessage("Your GP is: " + round(GP));
                        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_box, null));
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();

                    }

                }
            }
        });


    }

    public Double round(Double number) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(number));
    }
}
