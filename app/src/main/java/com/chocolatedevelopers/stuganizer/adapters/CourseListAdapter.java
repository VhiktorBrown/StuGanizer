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
import com.chocolatedevelopers.stuganizer.lists.CourseList;
import com.chocolatedevelopers.stuganizer.interfaces.ClickInterface;
import com.chocolatedevelopers.stuganizer.interfaces.RecyclerInterface;

import java.util.List;


public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {

    private List<CourseList> courseList;
    Fragment fragment;
    Context context;
    RecyclerInterface recyclerInterface;
    private ClickInterface clickInterface;
    public CourseListAdapter(Context context, Fragment fragment, List<CourseList> courseList, RecyclerInterface recyclerInterface, ClickInterface clickInterface) {
        this.courseList = courseList;
        this.context = context;
        this.fragment = fragment;
        this.recyclerInterface = recyclerInterface;
        this.clickInterface = clickInterface;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.courses_main_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final int cardPosition = position;
        holder.courseName.setText(courseList.get(position).getCourseName());

        String[] creditLoads = {"--CL--", "1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, creditLoads);
        holder.creditLoad.setAdapter(adapter);

        String creditLoad = courseList.get(position).getCreditLoad();
        final int pos = adapter.getPosition(creditLoad);
        holder.creditLoad.setSelection(pos);

        holder.creditLoad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String credit = holder.creditLoad.getSelectedItem().toString();
                recyclerInterface.getPosition(cardPosition, credit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clickInterface.OnLongClick(position);
                return true;
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
        return courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        Spinner creditLoad;
        CardView cardView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            courseName = itemView.findViewById(R.id.courseName);
            creditLoad = itemView.findViewById(R.id.credit_load_spinner);
            cardView = itemView.findViewById(R.id.card_view);


        }
    }
}
