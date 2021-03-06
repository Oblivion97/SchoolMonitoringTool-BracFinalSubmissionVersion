/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.analyseAndTest.repFragUnused;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.f.schMon.controller.Helper;
import com.example.f.schMon.model.appInner.LastPerfMdl;
import com.example.f.schMon.controller.App;
import com.example.f.schMon.R;
import com.example.f.schMon.controller.Global;
import com.example.f.schMon.model.DAO;
import com.example.f.schMon.model.DAO2;
import com.example.f.schMon.view.common.DatePickerFragment;
import com.example.f.schMon.view.common.NavDrawer;
import com.example.f.schMon.view.report.RepMdlin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Created by ASUS on 10/10/2017.
 */

public class RepActivity2 extends AppCompatActivity implements RepDialog2.RepSubmitInterface, DatePickerFragment.DateDialogListner {
    private final Context context = RepActivity2.this;
    private final String TAG = RepActivity2.class.getName();
    private SQLiteDatabase db;

    private RepAdapter2 dataAdapter = null;
    private List<RepMdlin> list = new ArrayList<>();
    private List<Integer> unanswerQues = new ArrayList<>();

    @BindView(R.id.repSubmitBtn)
    Button submitBtn;
    @BindView(R.id.repDateBtn)
    Button dateBtn;
    Toolbar toolbar;
    @BindView(R.id.smoothPrg)
    SmoothProgressBar pb;
    @BindView(R.id.simpleListView)
    ListView listView;

    private Intent intent;
    private String openMode, schID, msg, date;
    private boolean dateInputted, dbInsertSuccess1, dbInsertSuccess2, dbInsertSuccess3;
    private RepAdapter2 adapter;
    private SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");

    /*___________________Start option menu______________________________________*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);//Menu Resource, Menu
        return true;
    }

    /**
     * hiding irrelevant option menu for this Activity
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        try {
            menu.findItem(R.id.home_menu).setVisible(false);
            menu.findItem(R.id.date_menu).setVisible(false);
            menu.findItem(R.id.settings_menu).setVisible(false);
            menu.findItem(R.id.sync_menu_details).setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.date_menu:
                DialogFragment dialog = new DatePickerFragment();
                dialog.show(getSupportFragmentManager(), "CalenderDialog");
                return true;
            case R.id.sync_menu:
                return true;
            case R.id.sync_menu_details:
                return true;
            case R.id.settings_menu:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

/*___________________End option menu______________________________________*/


    /*  nav drawer close on back button click */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }


    /*==========================================================================================================================*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Global.openMode, openMode);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (openMode.equals(Global.RunMode)) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        //appbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Administrative Report");

        //  nav drawer
        NavDrawer.navDrawerBtnsClick(context, toolbar);

        db = Global.getDB_with_nativeWay_writable(Global.dbName);


        intent = getIntent();
        schID = intent.getStringExtra("schID");
        openMode = intent.getStringExtra(Global.openMode);

        loadData();

    }

    private void loadData() {
        new AsyncTask<String, Void, List<RepMdlin>>() {
            @Override
            protected List<RepMdlin> doInBackground(String... strings) {
                openMode = strings[0];
                //edit mode
                if (openMode.equals(Global.EditMode)) {
                    //repModelins = DAO.getLastRepModelinListBySchool(schID);
                    String lastDate ;

                    /*lastDate = DAO2.lastDateForASchool(schID, DB.report_admin.table,
                            DB.report_admin.sch_id, DB.report_admin.date_report);*/

                    lastDate=intent.getStringExtra("date");
                    list = DAO2.getReport1School1Date(schID, lastDate);


                    date = intent.getStringExtra("date");
                    if (date != null || !date.equals(""))
                        dateInputted = true;
                }
                // new report mode
                else if (openMode.equals(Global.NewMode)) {
                    //get questionTV
                    list = DAO.getActiveQuesRep();
                }
                //run mode
                else if (openMode.equals(Global.RunMode)) {
                    list.addAll(App.getRepList());
                }


                return list;
            }

            @Override
            protected void onPostExecute(List<RepMdlin> list) {
                Helper.setQuesNoForDialogFrag(list);

                adapter = new RepAdapter2(context, R.layout.reporting_row, list, schID, openMode, date);

                listView.setAdapter(adapter);
                pb.setVisibility(View.GONE);

                Helper.getAlertDialog(context, openMode + "\n" + list.toString());
                Log.d(TAG, openMode + "\n" + list.toString());

            }
        }.execute(new String[]{openMode});
    }

    private String dbUpdateOnSubmit() {

        //=========== checking all ques answered or not ===========
        if (unansweredQues() != null) {           //unanswered ques list
            new AlertDialog.Builder(context)
                    .setMessage("Answer Question No - \n" + unansweredQues() + "\nThen Submit Again")
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    }).create().show();
        } else {

            setDateToallRep();

            /**  1. db insert insert checklist answers to db */
           // dbInsertSuccess1 = DAO.insertReport(list);


            /** 2  db insert last performance for dash */
            String dateT = list.get(0).getDateReport();
            String performace = Helper.calcRepPerformForASch(list);//calcReportPerformForASch(list);
            String schName = DAO.getSchoolNameFromSchoolID(schID);
            LastPerfMdl lpm = new LastPerfMdl(schID, schName, performace, dateT, null, null, null, null);
            dbInsertSuccess2 = DAO.insertLastPerformAll(schID, lpm);


            if (dbInsertSuccess1 && dbInsertSuccess2) {
                msg = Helper.print(list);
            } else msg = "Inserting Report In DB Failed";


           /* new AlertDialog.Builder(context).setMessage(msg)
                    .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((RepActivity2) context).finish();
                        }
                    }).create().show();*/

            ((RepActivity2) context).finish();
        }
        return msg;
    }

    private void setDateToallRep() {
        /**=========== setting date to all RepModel after filling up all answers =========== */
        int n = list.size();
        for (int i = 0; i < n; i++) {
            if (list.get(i).getAnswer().equals("1") || list.get(i).getAnswer().equals("0")) {
                list.get(i).setDateReport(date);
                list.get(i).setTimeLm(date);
            }
        }
    }

    private String unansweredQues() {
        String x = null;
        RepMdlin r;
        int n = list.size();
        for (int i = 0; i < n; i++) {
            r = list.get(i);
            if (r.getAnswer() == null || (!r.getAnswer().equals("0") && !r.getAnswer().equals("1"))) {
                if (!unanswerQues.contains(i + 1))//multiple entry block
                    unanswerQues.add(i + 1);
            }
        }

        StringBuffer t = new StringBuffer();
        for (int i = 0; i < unanswerQues.size(); i++) {
            t.append(String.valueOf(unanswerQues.get(i)));
            if (i < unanswerQues.size() - 1)
                t.append(", ");
        }

        if (unanswerQues.size() > 0)
            x = t.toString();
        else x = null;
        unanswerQues.clear();
        return x;
    }

    @OnClick(R.id.repSubmitBtn)
    public void onClickSubmitAll(View v) {
        if (dateInputted) {
            dbUpdateOnSubmit();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Input Date...Then Submit")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            builder.create().show();
        }
    }

    @OnClick(R.id.repDateBtn)
    public void onClickDate(View view) {
        DialogFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), "CalenderDialog");
    }


    @Override
    public void onDateClick(DialogFragment dialog, boolean dateInputted, Calendar calendar) {
        this.dateInputted = dateInputted;//date is inputted from date picker fragment now submit btn can work
        date = df1.format(calendar.getTime());
    }

    //=================== frag interface implementation ==============================
    @Override
    public void onSubmitClickSingleRep(String schID, int index, RepMdlin data) {

     /*   for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getQuestionID().equals(data.getQuestionID()) &&
                    list.get(i).getQuestion().equals(data.getQuestion())) {
                list.remove(i);
                list.add(i, data);
            }
        }*/

      /*  listView.invalidate();
        adapter.notifyDataSetChanged();*/

       /* adapter = new RepAdapter2(context, R.layout.reporting_row, list, schID, openMode, date);
        listView.setAdapter(adapter);*/

       /* Helper.getAlertDialog(context, "index" + index + " Data: \n" +
                list.get(index).toString()
                + "size of list: " + list.size() +
                "list is : " + list.toString());*/


        listView.setAdapter(new RepAdapter2(context, R.layout.reporting_row, new ArrayList<RepMdlin>(), schID, openMode, date));


        //=========== global =================
        openMode = Global.RunMode;

        dataAddinList(list, data);

        Intent intentRep = new Intent(this, RepActivity2.class);
        intentRep.putExtra("schID", schID);
        intentRep.putExtra(Global.openMode, Global.RunMode);
        //intentRep.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intentRep);
        finish();


    }


    void dataAddinList(List<RepMdlin> list, RepMdlin data) {
        App.getRepList().clear();
        List<RepMdlin> temp = new ArrayList<>();
        temp.addAll(list);

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getServerIDQues().equals(data.getServerIDQues()) &&
                    temp.get(i).getQuestion().equals(data.getQuestion())) {
                temp.remove(i);
                temp.add(i, data);
            }
        }

        App.setRepList(temp);
    }

}
