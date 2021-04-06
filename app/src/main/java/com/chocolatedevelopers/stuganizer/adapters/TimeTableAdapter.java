package com.chocolatedevelopers.stuganizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.lists.TimeTableDetails;
import com.chocolatedevelopers.stuganizer.interfaces.ClickInterface;

import java.util.List;


public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHolder> {

    Context context;
    Fragment fragment;
    List<TimeTableDetails> timeTableDetails;
    ClickInterface clickInterface;

    public TimeTableAdapter(Fragment fragment, List<TimeTableDetails> timeTableDetails, Context context, ClickInterface clickInterface) {
        this.fragment = fragment;
        this.timeTableDetails = timeTableDetails;
        this.context = context;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.timetable_details_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int pos = position;
        holder.lecturerName.setText(timeTableDetails.get(position).getLecturerName());
        holder.courseName.setText(timeTableDetails.get(position).getCourseName());
        holder.startTime.setText(timeTableDetails.get(position).getStartTime());
        holder.endTime.setText(timeTableDetails.get(position).getEndTime());
        holder.venueName.setText(timeTableDetails.get(position).getVenueName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.OnLongClick(pos);
            }
        });

        switch ((position)) {
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
            case 5:
                holder.view.setBackgroundResource(R.color.venomGreen);
                break;
            case 4:
                holder.view.setBackgroundResource(R.color.grayCloud);
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
        return timeTableDetails.size();
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView startTime, endTime, courseName, venueName, lecturerName;
        CardView cardView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.start_time_1);
            endTime = itemView.findViewById(R.id.end_time_1);
            courseName = itemView.findViewById(R.id.course_name_1);
            venueName = itemView.findViewById(R.id.venue_name_1);
            lecturerName = itemView.findViewById(R.id.lecturer_name_1);
            cardView = itemView.findViewById(R.id.card_view2);
            view = itemView.findViewById(R.id.view2);

        }
    }
}
