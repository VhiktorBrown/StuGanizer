package com.chocolatedevelopers.stuganizer.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chocolatedevelopers.stuganizer.databases.MySqLiteConnector;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.lists.SelectedCourses;
import com.chocolatedevelopers.stuganizer.activities.MainActivity;
import com.chocolatedevelopers.stuganizer.interfaces.RecyclerInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class CourseSelectionAdapter extends RecyclerView.Adapter<CourseSelectionAdapter.ViewHolder> {

    ArrayList<SelectedCourses> selectedCourses;
    Activity activity;
    RecyclerInterface recyclerInterface;
    MySqLiteConnector connector;

    public CourseSelectionAdapter(Activity activity, ArrayList<SelectedCourses> selectedCourses, MySqLiteConnector connector, RecyclerInterface recyclerInterface) {
        this.activity = activity;
        this.selectedCourses = selectedCourses;
        this.connector = connector;
        this.recyclerInterface = recyclerInterface;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.course_selection_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.course.setText(selectedCourses.get(position).getCourseTitle());

        switch(position) {
            case 0:
                holder.cardView.setBackgroundResource(R.color.blueHosta);
                break;
            case 1:
                holder.cardView.setBackgroundResource(R.color.cherryRed);
                break;
            case 2:
                holder.cardView.setBackgroundResource(R.color.cookieBrown);
                break;
            case 3:
                holder.cardView.setBackgroundResource(R.color.scarlet);
                break;
            case 4:
                holder.cardView.setBackgroundResource(R.color.steelBlue);
                break;
            case 5:
                holder.cardView.setBackgroundResource(R.color.venomGreen);
                break;
            case 6:
                holder.cardView.setBackgroundResource(R.color.tronBlue);
                break;
            case 7:
                holder.cardView.setBackgroundResource(R.color.fireBrick);
                break;
            case 8:
                holder.cardView.setBackgroundResource(R.color.sunriseOrange);
                break;
            case 9:
                holder.cardView.setBackgroundResource(R.color.blueLotus);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return selectedCourses.size();
    }

    public void filterList(ArrayList<SelectedCourses> courses) {
        selectedCourses = courses;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView course;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view_4);
            course = itemView.findViewById(R.id.course);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(v.getContext(), R.style.AlertDialogTheme);
                    builder.setTitle("Department Selected");
                    builder.setMessage("You have selected " + selectedCourses.get(getAdapterPosition()).getCourseTitle() + " as your department in school. Continue?");
                    builder.setBackground(v.getContext().getResources().getDrawable(R.drawable.alert_dialog_box, null));
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           connector.saveCourse(selectedCourses.get(getAdapterPosition()).getCourseTitle());
                           v.getContext().startActivity(new Intent(v.getContext(), MainActivity.class));
                            ((Activity)v.getContext()).finish();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                }
            });
            itemView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

    }
}
