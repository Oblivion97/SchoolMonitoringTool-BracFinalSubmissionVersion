/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.attendance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.f.schMon.R;
import com.example.f.schMon.controller.Helper;
import com.example.f.schMon.model.appInner.School;

import java.util.List;

/**
 * Created by Mir Ferdous on 04/06/2017.
 */

public class SchoolListAdapterAttnd extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = this.getClass().getName();
    private Context context;
    private List<School> schList;
    private MyViewHolder myViewHolder;
    private String openWhichActivity;


    //============== constructor ================
    public SchoolListAdapterAttnd(Context context, List<School> schList, String openWhichActivity) {
        this.context = context;
        this.schList = schList;
        this.openWhichActivity = openWhichActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater LInflater = LayoutInflater.from(context);
        View row = LInflater.inflate(R.layout.row_school_list, parent, false);
        myViewHolder = new MyViewHolder(row);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder) holder).schName.setText(schList.get(position).schName.toUpperCase() + " (Code: " +
                schList.get(position).schCode + ")");
        ((MyViewHolder) holder).schGrade.setText("Grade: " + schList.get(position).grade);
        ((MyViewHolder) holder).schType.setText("Type: " + schList.get(position).edu_type);
        ((MyViewHolder) holder).totalStd.setText("Total Students: " + schList.get(position).totalStd);
        ((MyViewHolder) holder).schIDTV.setText(schList.get(position).schID);
        ((MyViewHolder) holder).gradeIDTV.setText(schList.get(position).gradeID);

    }

    @Override
    public int getItemCount() {
        return schList.size();
    }

    public List<School> getList() {
        return this.schList;
    }


    void clickSchool(TextView schIDTV, TextView gradeIDTV) {
        // passing schoolSync id for next activity to show schoolSync profile of selected schoolSync
        String schID = schIDTV.getText().toString();
        String gradeID = gradeIDTV.getText().toString();

        Intent intent = new Intent(context, AttndActivity.class);
        intent.putExtra("schID", schID);
        intent.putExtra("gradeID", gradeID);
        context.startActivity(intent);

    }

    //============ VH ================

    class MyViewHolder extends RecyclerView.ViewHolder {

        //  Button schNmeBtn; //clickable schoolSync name in list
        TextView schIDTV, gradeIDTV; // hidden schoolSync id to pass to schoolSync profile activity

        CardView cardView;
        TextView schName, schGrade, schType, totalStd;
        LinearLayout rootSchools;

        public MyViewHolder(View itemView) {
            super(itemView);
            schIDTV = itemView.findViewById(R.id.schID);
            gradeIDTV = itemView.findViewById(R.id.gradeID);
            cardView = itemView.findViewById(R.id.sch_name_card_view);
            schName = itemView.findViewById(R.id.schListName);
            schGrade = itemView.findViewById(R.id.schListGrade);
            schType = itemView.findViewById(R.id.schListType);
            totalStd = itemView.findViewById(R.id.schListTotalStudent);
            rootSchools = itemView.findViewById(R.id.root_school);
            Helper.setFont(context, new View[]{schName, schGrade, schType, totalStd});

            /**_____________Btn Listner____________________*/
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickSchool(schIDTV, gradeIDTV);
                }
            });


        }
    }


}
