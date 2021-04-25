/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.report;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.f.schMon.R;
import com.example.f.schMon.controller.Global;
import com.example.f.schMon.controller.Helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * Created by Mir Ferdous on 30/11/2017.
 */

public class RepAdapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = RepAdapterRecycler.class.getName();
    private Context context;
    List<RepMdlin> list;
    RepMdlin data;
    private String schID, gradeID, openMode, date, time_lm, categoryID, categoryName;
    int index = 1;
    VH VH;
    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");


    public RepAdapterRecycler(Context context,
                              List<RepMdlin> list, String schID, String gradeID, String openMode, String date,
                              String categoryID, String categoryName) {
        this.context = context;
        this.list = list;
        this.schID = schID;
        this.gradeID = gradeID;
        this.openMode = openMode;
        this.date = date;
        time_lm = df1.format(new Date(System.currentTimeMillis()));
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater LInflater = LayoutInflater.from(context);
        View row = LInflater.inflate(R.layout.row_report, parent, false);
        VH = new VH(row);

        return VH;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, final int i) {
        /** if yes answer =1
         * if no answer =0*/

        //set answer views visibility
        if (list.get(i).getAnswer() != null) {
            ((VH) h).repAnsContainer.setVisibility(View.VISIBLE);
            ((VH) h).repDetailsContainer.setVisibility(View.VISIBLE);
            if (list.get(i).getAnswer().equals("0")) {// ans no
                ((VH) h).repDetailsContainer.setVisibility(View.VISIBLE);
                ((VH) h).yesCommentTV.setVisibility(View.GONE);
            } else if (list.get(i).getAnswer().equals("1")) {// ans yes
                ((VH) h).repDetailsContainer.setVisibility(View.GONE);
                ((VH) h).yesCommentTV.setVisibility(View.VISIBLE);
            } else {// not answered
                ((VH) h).yesCommentTV.setVisibility(View.GONE);
                ((VH) h).repDetailsContainer.setVisibility(View.GONE);
            }
        } else {
            ((VH) h).repAnsContainer.setVisibility(View.GONE);
            ((VH) h).yesCommentTV.setVisibility(View.GONE);
            //  changeLayoutParams(((VH) h).cardVW);
        }


        // hide question categoryWise
        if (/*!list.get(i).getCategoryID().equalsIgnoreCase(categoryID)
                || */!list.get(i).getCategoryName().equalsIgnoreCase(categoryName)) {
            ((VH) h).setVisibility(false);
        } else {
            // ((VH) h).questionTV.setText((index++ + ". " + list.get(i).getQuestion()));
            ((VH) h).questionTV.setText(((i + 1) + ". " + list.get(i).getQuestion()));


            ((VH) h).img.setImageResource(list.get(i).getImgID());
            ((VH) h).probStatusTV.setText(list.get(i).getStatusExtra());
            ((VH) h).priorityTV.setText(list.get(i).getPriorityExtra());
            ((VH) h).priorityTV.setTextColor(list.get(i).getPriorityColorExtra());
            ((VH) h).detailsTV.setText(list.get(i).getDetailsExtra());
            ((VH) h).dateTargetTV.setText(list.get(i).getDateTargetExtra());
            ((VH) h).yesCommentTV.setText("Comment: " + list.get(i).getYesComment());

        }
        //font
        Helper.setFont(context, new View[]{((VH) h).questionTV, ((VH) h).probStatusTV, ((VH) h).priorityTV,
                ((VH) h).detailsTV, ((VH) h).dateTargetTV});


        ((VH) h).cardVW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (openMode.equalsIgnoreCase(Global.DisplayMode)) {
                    Toasty.error(context, "This Report is Synced, can't be Edited.", Toast.LENGTH_SHORT, true).show();
                } else {
                    list.get(i).setListPosition(i);
                    Bundle bundle = new Bundle();
                    bundle.putString("schID", schID);
                    bundle.putString("gradeID", gradeID);
                    bundle.putString(Global.openMode, openMode);
                    bundle.putInt("index", i);
                    bundle.putParcelable("data", list.get(i));
                    DialogFragment dialog = new RepDialog();
                    dialog.setArguments(bundle);
                    dialog.setCancelable(false);

                    // dialog .setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);//this line for full screen dialog

                    dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "RepDialog2");
                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    void changeLayoutParams(CardView cardView) {
        //cardView.setLayoutParams(new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT));
        cardView.setMinimumHeight(60);
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.repCard)
        CardView cardVW;
        @BindView(R.id.repAnsContainer)
        View repAnsContainer;
        @BindView(R.id.repDetailsContainer)
        View repDetailsContainer;
        @BindView(R.id.repQuestion)
        TextView questionTV;
        @BindView(R.id.repDetails)
        TextView detailsTV;
        @BindView(R.id.repPriority)
        TextView priorityTV;
        @BindView(R.id.repProblemStatus)
        TextView probStatusTV;
        @BindView(R.id.targetDateR)
        TextView dateTargetTV;
        @BindView(R.id.repStatusImg)
        CircleImageView img;
        @BindView(R.id.repYesComment)
        TextView yesCommentTV;

        public void setVisibility(boolean isVisible) {
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            if (isVisible) {
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                itemView.setVisibility(View.VISIBLE);
            } else {
                itemView.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
            }
            itemView.setLayoutParams(param);
        }


        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
