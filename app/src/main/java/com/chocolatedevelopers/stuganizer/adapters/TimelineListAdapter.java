package com.chocolatedevelopers.stuganizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.lists.TimelineList;
import com.chocolatedevelopers.stuganizer.interfaces.RecyclerInterface;

import java.util.List;


public class TimelineListAdapter extends RecyclerView.Adapter<TimelineListAdapter.ViewHolder> {

    private List<TimelineList> timelineList;
    Fragment fragment;
    Context context;
    RecyclerInterface recyclerInterface;
    public TimelineListAdapter(Context context, Fragment fragment, List<TimelineList> timelineList, RecyclerInterface recyclerInterface) {
        this.timelineList = timelineList;
        this.context = context;
        this.fragment = fragment;
        this.recyclerInterface = recyclerInterface;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.timeline_courses_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final int cardPosition = position;
        holder.courseName.setText(timelineList.get(position).getCourseName());

        String[] grades = {"-Grade-", "A", "B", "C", "D", "E", "F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, grades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.grade.setAdapter(adapter);

        String grade = timelineList.get(position).getGrade();
        int pos = adapter.getPosition(grade);
        holder.grade.setSelection(pos);

        holder.grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String grade = holder.grade.getSelectedItem().toString();
                recyclerInterface.getPosition(cardPosition, grade);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        switch(position) {
            case 0:
                holder.view.setBackgroundResource(R.color.blueHosta);
                break;
            case 1:
                holder.view.setBackgroundResource(R.color.cherryRed);
                break;
            case 2:
                holder.view.setBackgroundResource(R.color.cookieBrown);
                break;
            case 3:
                holder.view.setBackgroundResource(R.color.scarlet);
                break;
            case 4:
                holder.view.setBackgroundResource(R.color.grayCloud);
                break;
            case 5:
                holder.view.setBackgroundResource(R.color.venomGreen);
                break;
            case 6:
                holder.view.setBackgroundResource(R.color.tronBlue);
                break;
            case 7:
                holder.view.setBackgroundResource(R.color.fireBrick);
                break;
            case 8:
                holder.view.setBackgroundResource(R.color.lemonChiffon);
                break;
            case 9:
                holder.view.setBackgroundResource(R.color.blueLotus);
                break;
            case 10:
                holder.view.setBackgroundResource(R.color.vampireGray);
                break;
            case 11:
                holder.view.setBackgroundResource(R.color.maroon);
                break;
            case 12:
                holder.view.setBackgroundResource(R.color.sunYellow);
                break;
            case 13:
                holder.view.setBackgroundResource(R.color.rust);
                break;
            case 14:
                holder.view.setBackgroundResource(R.color.sunriseOrange);
                break;
            case 15:
                holder.view.setBackgroundResource(R.color.orangeGold);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return timelineList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        Spinner grade;
        CardView cardView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view3);
            courseName = itemView.findViewById(R.id.timeline_courseName);
            grade = itemView.findViewById(R.id.grade_spinner);
            cardView = itemView.findViewById(R.id.card_view3);
        }
    }
}
