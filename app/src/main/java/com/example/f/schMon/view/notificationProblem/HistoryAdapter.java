/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.notificationProblem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.f.schMon.R;
import com.example.f.schMon.controller.Global;
import com.example.f.schMon.controller.Helper;
import com.example.f.schMon.model.DAO;
import com.example.f.schMon.model.appInner.DB;
import com.example.f.schMon.model.appInner.School;
import com.example.f.schMon.view.report.RepMdlin;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by Mir Ferdous on 10/10/2017.
 */

public class HistoryAdapter extends ArrayAdapter<RepMdlin> {
    private static final String TAG = HistoryAdapter.class.getName();
    private Context context;
    private List<RepMdlin> list;
    School s;
    ViewHolder h;
    RepMdlin data;

    public HistoryAdapter(Context context, List<RepMdlin> list) {
        super(context, -1, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        @BindView(R.id.historyDate)
        TextView dateTV;
        @BindView(R.id.notification_title)
        TextView title;
        @BindView(R.id.notification_school_name)
        TextView schoolName;
        @BindView(R.id.notification_description)
        TextView description;
        @BindView(R.id.notification_school_grade)
        TextView eduType;
        @BindView(R.id.historyDelete)
        ImageButton delete;
        @BindView(R.id.notification_target)
        TextView targetDate;
        @BindView(R.id.notification_solve)
        TextView solveDate;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //================= view to view h assignment =====================
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.row_notif_history, null);
            h = new ViewHolder(convertView);
            convertView.setTag(h);
            //h.delete.setTag(position);
        } else {
            h = (ViewHolder) convertView.getTag();
        }

        Helper.setFont(context, new View[]{h.dateTV, h.title, h.schoolName, h.eduType, h.description, h.targetDate, h.solveDate});
        //================= set data =====================
        data = list.get(position);
        s = DAO.getInfoOfASchool(data.getSchID());

        h.dateTV.setText("Report Date: " + Helper.dateFormat2(data.getDateReport()));
        h.title.setText("Problem: " + Helper.toCamelCase(data.getQuestion()));
        h.schoolName.setText(Helper.toCamelCase(s.getSchName()) + "(Code: " + s.getSchCode() + ")");
        h.eduType.setText("Grade: " + s.getGrade() + "\n" + "Edu. Type: " + s.getEdu_type());
        h.description.setText("Problem Details: " + data.getDetails());

        String targetDate = data.getDateTarget();
        String solveDate = data.getDateSolve();


        if (targetDate == null)
            targetDate = "";
        if (solveDate == null)
            solveDate = "";

        h.targetDate.setText("Target Date: " + Helper.dateFormat2(targetDate));
        h.solveDate.setText("Solved Date: " + Helper.dateFormat2(solveDate));

        //================ on click =========================
        h.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                data = list.get(position);
                // Helper.getAlertDialog(context, String.valueOf(position) + " is index\n" + list.get(position).toString());


                deleteNotifHistory(list.get(position).getSchID(),
                        list.get(position).getDateReport(),
                        list.get(position).getServerIDQues(),
                        list.get(position).getQuestion(),
                        list.get(position).getGradeID());

                /* updateDB(id);*/

                Toasty.success(context, "This Problem Still Exists", Toast.LENGTH_SHORT).show();
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


    public boolean deleteNotifHistory(String schID, String date,
                                      String quesID, String ques, String gradeID) {
        boolean f = true;
        //get not solved reps from backup table
        RepMdlin r = getRepFromNotifyHistoryBckupTable(schID, date, quesID, ques);

        if (r == null) {
            //Log.d(TAG, "null");
        } else {
            r.setNotify("1");
            List<RepMdlin> repMdlins = new ArrayList<>();
            repMdlins.add(r);
            //  insert not solved item in main table(report_admin)
            f = DAO.insertReport(repMdlins, schID, date, gradeID);

            // now delete this from history backup table
            deleteFromBckupTable(r);
        }
        return f;
    }

    public void deleteFromBckupTable(RepMdlin r) {
        SQLiteDatabase db = Global.getDB_with_nativeWay_writable(Global.dbName);
        String table = DB.notif_bckup.table;
        String whereClause = DB.notif_bckup.sch_id + " = ? AND " + DB.notif_bckup.date_report + " = ? AND " +
                DB.notif_bckup.server_id_ques + " = ? ";
        String[] whereArgs = new String[]{r.getSchID(), r.getDateReport(), r.getServerIDQues()};
        db.delete(table, whereClause, whereArgs);
    }

    @Nullable
    public static RepMdlin getRepFromNotifyHistoryBckupTable(String schID, String date,
                                                             String quesID, String ques) {
        SQLiteDatabase db = Global.getDB_with_nativeWay_writable(Global.dbName);
        String table = DB.notif_bckup.table;
        RepMdlin r = null;
        Cursor c = null;

        String sel = DB.notif_bckup.sch_id + " = ? AND " + DB.notif_bckup.date_report + " = ? AND " +
                DB.notif_bckup.server_id_ques + " = ? " /*+ DB.notif_bckup.question + " = ? "*/;

        String[] selArg = new String[]{schID, date, quesID};
        c = db.query(table, null, sel, selArg, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                //27 column
                r = new RepMdlin();
                r.set_id(c.getString(c.getColumnIndex(DB.notif_bckup._ID)));
                r.setSchID(c.getString(c.getColumnIndex(DB.notif_bckup.sch_id)));
                r.setServerIDQues(c.getString(c.getColumnIndex(DB.notif_bckup.server_id_ques)));
                r.setQuesTitle(c.getString(c.getColumnIndex(DB.notif_bckup.questionTitle)));
                r.setQues(c.getString(c.getColumnIndex(DB.notif_bckup.question)));
                r.setAnswer(c.getString(c.getColumnIndex(DB.notif_bckup.answer)));
                r.setAnsWeight(c.getString(c.getColumnIndex(DB.notif_bckup.answerWeight)));
                r.setDetails(c.getString(c.getColumnIndex(DB.notif_bckup.details)));
                r.setPriority(c.getString(c.getColumnIndex(DB.notif_bckup.priority)));
                r.setServerQuestion(c.getString(c.getColumnIndex(DB.notif_bckup.serverQuestion)));
                r.setDateReport(c.getString(c.getColumnIndex(DB.notif_bckup.date_report)));
                r.setDateTarget(c.getString(c.getColumnIndex(DB.notif_bckup.date_target)));
                r.setDateSolve(c.getString(c.getColumnIndex(DB.notif_bckup.date_solve)));
                r.setSynced(c.getString(c.getColumnIndex(DB.notif_bckup.synced)));
                r.setNotify_his(c.getString(c.getColumnIndex(DB.notif_bckup.notify)));
                r.setNotify_his(c.getString(c.getColumnIndex(DB.notif_bckup.notify_his)));
                r.setN_update_date(c.getString(c.getColumnIndex(DB.notif_bckup.n_update_date)));
                r.setServerIDAns(c.getString(c.getColumnIndex(DB.notif_bckup.server_id_answer)));
                r.setLocalIDAns(c.getString(c.getColumnIndex(DB.notif_bckup.local_id_answer)));
                r.setLocalIDQues(c.getString(c.getColumnIndex(DB.notif_bckup.local_id_ques)));
                r.setLocalIDAns(c.getString(c.getColumnIndex(DB.notif_bckup.local_id_answer)));
                r.setLocalIDQues(c.getString(c.getColumnIndex(DB.notif_bckup.local_id_ques)));
                r.setCategoryID(c.getString(c.getColumnIndex(DB.notif_bckup.ques_category_id)));
                r.setCategoryName(c.getString(c.getColumnIndex(DB.notif_bckup.category)));
                r.setYesComment(c.getString(c.getColumnIndex(DB.notif_bckup.yesComment)));
                r.setIsProblem(c.getString(c.getColumnIndex(DB.notif_bckup.isProblem)));
                r.setServerIDAns(c.getString(c.getColumnIndex(DB.notif_bckup.server_id_answer)));
                r.setTimeLm(c.getString(c.getColumnIndex(DB.notif_bckup.time_lm)));
            }
        }
        if (c != null)
            c.close();

        return r;
    }
}