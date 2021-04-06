package com.chocolatedevelopers.stuganizer.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chocolatedevelopers.stuganizer.lists.ComputationModel;
import com.chocolatedevelopers.stuganizer.R;

import java.util.ArrayList;

public class ComputationAdapter extends RecyclerView.Adapter<ComputationAdapter.ViewHolder> {

    private LayoutInflater inflater;
    public static ArrayList<ComputationModel> computationModelArrayList;

    public ComputationAdapter(Context context, ArrayList<ComputationModel> computationModelArrayList) {
        this.computationModelArrayList = computationModelArrayList;
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.credit_grade_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.courseIndex.setText(computationModelArrayList.get(position).getCourseIndex());
        holder.creditLoad.setText(computationModelArrayList.get(position).getCreditLoad());
        holder.grade.setText(computationModelArrayList.get(position).getGrade());
    }

    @Override
    public int getItemCount() {
        return computationModelArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseIndex;
        EditText creditLoad;
        EditText grade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIndex = itemView.findViewById(R.id.course_index);
            creditLoad = itemView.findViewById(R.id.creditLoad_editText);
            grade = itemView.findViewById(R.id.grade_editText);

            creditLoad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    computationModelArrayList.get(getAdapterPosition()).setCreditLoad(creditLoad.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            grade.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    computationModelArrayList.get(getAdapterPosition()).setGrade(grade.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}
