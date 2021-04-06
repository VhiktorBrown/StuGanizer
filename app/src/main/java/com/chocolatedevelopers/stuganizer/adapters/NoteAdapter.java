package com.chocolatedevelopers.stuganizer.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chocolatedevelopers.stuganizer.activities.NoteDetails;
import com.chocolatedevelopers.stuganizer.R;
import com.chocolatedevelopers.stuganizer.lists.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    LayoutInflater inflater;
    static ArrayList<Note> noteArrayList;
    Context context;

    public NoteAdapter(Context context, ArrayList<Note> note) {
        this.inflater = LayoutInflater.from(context);
        this.noteArrayList = note;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = noteArrayList.get(position).getTitle();
        String date = noteArrayList.get(position).getDate();
        String time = noteArrayList.get(position).getTime();

        holder.noteTitle.setText(title);
        holder.date.setText(date);
        holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        TextView noteTitle;
        TextView date;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.card_view_title);
            date = itemView.findViewById(R.id.card_view_date);
            time = itemView.findViewById(R.id.card_view_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), NoteDetails.class);
                    intent.putExtra("ID", noteArrayList.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                    ((Activity)v.getContext()).finish();

                }
            });
        }
    }
}
