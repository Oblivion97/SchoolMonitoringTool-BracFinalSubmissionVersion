/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.notificationSolve;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.f.schMon.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotifSlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = NotifSlvAdapter.class.getName();
    private Context context;
    List<RepSlvMdl> list;
    VH VH;

    public NotifSlvAdapter(Context context, List<RepSlvMdl> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater LInflater = LayoutInflater.from(context);
        View row = LInflater.inflate(R.layout.row_notif_solve, parent, false);
        VH = new VH(row);

        return VH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VH) holder).notification_school_name.setText("School Name: " + list.get(position).getmSchoolName());
        ((VH) holder).notification_school_grade.setText("Grade: " + list.get(position).getmGrade());
        ((VH) holder).notification_title.setText("Problem Name: " + list.get(position).getQuestionName());
        ((VH) holder).notification_description.setText("Problem Details: " + list.get(position).getDetails());
        ((VH) holder).status.setText("Status: " + "Problem Solved");
        ((VH) holder).reportdate.setText("Reported Date: " + list.get(position).getSubmittedDate());
        ((VH) holder).solvedate.setText("Solve Date: " + list.get(position).getSolveDate());
        ((VH) holder).notification_target.setText("Target Date: " + list.get(position).getTargetDate());

        //priority name set

        if (list.get(position).getPriority() == 1)
            ((VH) holder).notification_priority.setText("Problem Priority: Major");
        else if (list.get(position).getPriority() == 2)
            ((VH) holder).notification_priority.setText("Problem Priority: Moderate" );
        else if (list.get(position).getPriority() == 3)
            ((VH) holder).notification_priority.setText("Problem Priority: Minor" );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.notification_school_name)
        TextView notification_school_name;
        @BindView(R.id.notification_school_grade)
        TextView notification_school_grade;
        @BindView(R.id.notification_title)
        TextView notification_title;
        @BindView(R.id.notification_description)
        TextView notification_description;
        @BindView(R.id.reportdate)
        TextView reportdate;
        @BindView(R.id.notification_target)
        TextView notification_target;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.solvedate)
        TextView solvedate;
        @BindView(R.id.notification_priority)
        TextView notification_priority;


        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
