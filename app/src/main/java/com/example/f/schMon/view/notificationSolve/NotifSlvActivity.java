/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.notificationSolve;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.f.schMon.R;
import com.example.f.schMon.view.common.MessageEvent;
import com.example.f.schMon.controller.Global;
import com.example.f.schMon.controller.Helper;
import com.example.f.schMon.view.common.NavDrawer;
import com.example.f.schMon.view.sync_mir.SyncSynchronous;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class NotifSlvActivity extends AppCompatActivity {
    /**
     * if msgID = 1 success data available
     * msgID=0 failed data not available
     */
    private final Context context = NotifSlvActivity.this;
    private final String TAG = NotifSlvActivity.class.getName();
    MessageEvent msgEvnt = new MessageEvent();
    @BindView(R.id.repSlvRecyclerView)
    RecyclerView repSlvRecyclerView;
    @BindView(R.id.smoothPrg)
    SmoothProgressBar pb;
    @BindView(R.id.notif_slv_msgTV)
    TextView notif_slv_msgTV;

    @BindView(R.id.notf_btn_container)
    View notf_btn_container;
    @BindView(R.id.notf_prev)
    Button notf_prev;
    @BindView(R.id.notif_nxt)
    Button notif_nxt;
    // @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SimpleDateFormat df4 = new SimpleDateFormat("dd MMM yyyy, hh:mm");
    SyncSynchronous syncSynchronous = new SyncSynchronous(context);
    private List<RepSlvMdl> list = new ArrayList<>();
    // List<RepSlvMdl> listTemp = new ArrayList<>();
    private Thread syncSlvPrbRepThread;
    private NotifSlvAdapter notifSlvAdapter = null;
    private String lastSyncTime;
    private int pageNoCurrent = 0;

    boolean isDataAvailable = true;


    //private boolean dataAvailable;// if solve list returned form api is blank then it will be false

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
            menu.findItem(R.id.update_solved_menu).setVisible(true);
            menu.findItem(R.id.home_menu).setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update_solved_menu:
                onClickRefreshSolvedListBtn();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*___________________End option menu______________________________________*/

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        prevBtnEnableOrDisable();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_solved);
        ButterKnife.bind(this);
        //appbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Solved Problems Page " + String.valueOf(pageNoCurrent + 1));

        //  nav drawer
        NavDrawer.navDrawerBtnsClick(context, toolbar);


        // RecyclerView
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        repSlvRecyclerView.setLayoutManager(mLayoutManager);
        notifSlvAdapter = new NotifSlvAdapter(context, list);
        repSlvRecyclerView.setAdapter(notifSlvAdapter);

        /*================*/
        loadDataOfFirstPage();
    }


    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void loadDataOfFirstPage() {
        pb.setVisibility(View.VISIBLE);
        // notif_slv_msgTV.setText("Loading");
        if (Helper.isInternetConnected(context)) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        List<RepSlvMdl> listTemp = syncSynchronous.dwnldSlvedProblems_Report(String.valueOf(pageNoCurrent));
                        isDataAvailable(listTemp);
                        list.clear();
                        list.addAll(listTemp);
                        //Log.d(TAG, list.toString());
                        lastSyncTime = df4.format(new Date(System.currentTimeMillis()));
                        msgEvnt.setMessageID(1);
                        msgEvnt.setLastSyncTime(lastSyncTime);
                        msgEvnt.setSuccess(true);
                        msgEvnt.setMessage("Solved Problem List Updated");
                        EventBus.getDefault().post(msgEvnt);

                    } catch (Exception e) {
                        Log.d(TAG, e.toString(), e);
                        msgEvnt.setMessageID(0);
                        msgEvnt.setLastSyncTime(lastSyncTime);
                        msgEvnt.setSuccess(false);
                        msgEvnt.setMessage("Try Again");
                        EventBus.getDefault().post(msgEvnt);
                        Toasty.error(context, "Try Again").show();
                        notif_slv_msgTV.setText("Try Again");
                    }
                }
            };

            syncSlvPrbRepThread = new Thread(runnable);
            syncSlvPrbRepThread.start();
        } else {
            Toasty.error(context, Global.noNetErrorMsg).show();
            notif_slv_msgTV.setText(Global.noNetErrorMsg);
            pb.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.notif_nxt)
    void onClickNxt() {
        pb.setVisibility(View.VISIBLE);
        notif_slv_msgTV.setText("Loading");
        if (Helper.isInternetConnected(context)) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        List<RepSlvMdl> listTemp = syncSynchronous.dwnldSlvedProblems_Report(String.valueOf(pageNoCurrent + 1));
                        if (isDataAvailable(listTemp)) {
                            pageNoCurrent = pageNoCurrent + 1;
                            list.clear();
                            list.addAll(listTemp);
                            //Log.d(TAG, list.toString());
                            lastSyncTime = df4.format(new Date(System.currentTimeMillis()));
                            msgEvnt.setMessageID(1);
                            msgEvnt.setLastSyncTime(lastSyncTime);
                            msgEvnt.setSuccess(true);
                            msgEvnt.setMessage("Solved Problem List Updated");
                            EventBus.getDefault().post(msgEvnt);
                        } else {
                            lastSyncTime = df4.format(new Date(System.currentTimeMillis()));
                            msgEvnt.setMessageID(0);
                            msgEvnt.setLastSyncTime(lastSyncTime);
                            msgEvnt.setSuccess(true);
                            msgEvnt.setMessage("No More Data Available");
                            EventBus.getDefault().post(msgEvnt);
                        }

                    } catch (Exception e) {
                        Log.d(TAG, e.toString(), e);
                        msgEvnt.setLastSyncTime(lastSyncTime);
                        msgEvnt.setSuccess(false);
                        msgEvnt.setMessage("Try Again");
                        EventBus.getDefault().post(msgEvnt);
                        Toasty.error(context, "Try Again").show();
                        notif_slv_msgTV.setText("Try Again");
                    }
                }
            };

            syncSlvPrbRepThread = new Thread(runnable);
            syncSlvPrbRepThread.start();
        } else {
            Toasty.error(context, Global.noNetErrorMsg).show();
            notif_slv_msgTV.setText(Global.noNetErrorMsg);
            pb.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.notf_prev)
    void onClickPrevious() {
        if (pageNoCurrent > 0) {
            pb.setVisibility(View.VISIBLE);
            notif_slv_msgTV.setText("Loading");
            if (Helper.isInternetConnected(context)) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            List<RepSlvMdl> listTemp = syncSynchronous.dwnldSlvedProblems_Report(String.valueOf(pageNoCurrent - 1));
                            pageNoCurrent = pageNoCurrent - 1;
                            list.clear();
                            list.addAll(listTemp);
                            //Log.d(TAG, list.toString());
                            lastSyncTime = df4.format(new Date(System.currentTimeMillis()));
                            msgEvnt.setMessageID(1);
                            msgEvnt.setLastSyncTime(lastSyncTime);
                            msgEvnt.setSuccess(true);
                            msgEvnt.setMessage("Solved Problem List Updated");
                            EventBus.getDefault().post(msgEvnt);
                        } catch (Exception e) {
                            Log.d(TAG, e.toString(), e);
                            msgEvnt.setLastSyncTime(lastSyncTime);
                            msgEvnt.setSuccess(false);
                            msgEvnt.setMessage("Try Again");
                            EventBus.getDefault().post(msgEvnt);
                            Toasty.error(context, "Try Again").show();
                            notif_slv_msgTV.setText("Try Again");
                        }
                    }
                };

                syncSlvPrbRepThread = new Thread(runnable);
                syncSlvPrbRepThread.start();
            } else {
                Toasty.error(context, Global.noNetErrorMsg).show();
                notif_slv_msgTV.setText(Global.noNetErrorMsg);
                pb.setVisibility(View.GONE);
            }
        }
    }

    private void onClickRefreshSolvedListBtn() {
        pageNoCurrent = 0;
        loadDataOfFirstPage();
     /*   pb.setVisibility(View.VISIBLE);
        list.clear();
        notifSlvAdapter.notifyDataSetChanged();*/
    }


    boolean isDataAvailable(List<RepSlvMdl> listTemp) {
        boolean f = false;
        if (listTemp.size() <= 0) {
            isDataAvailable = false;
            f = false;
        } else {
            isDataAvailable = true;
            f = true;
        }
        return f;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent e) {
        prevBtnEnableOrDisable();
        pb.setVisibility(View.GONE);
        if (e.messageID == 1) {
            notifSlvAdapter.notifyDataSetChanged();
            notif_slv_msgTV.setVisibility(View.GONE);
            toolbar.setTitle("Solved Problems Page " + String.valueOf(pageNoCurrent + 1));
            Toasty.success(context, "Page "+String.valueOf(pageNoCurrent + 1)).show();

        } else if (e.messageID == 0) {
            Toasty.warning(context, "No More Data Available").show();
        }
    }


    void prevBtnEnableOrDisable() {
        if (pageNoCurrent == 0) {
            notf_prev.setEnabled(false);
            notf_prev.setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            notf_prev.setEnabled(true);
            notf_prev.setTextColor(getResources().getColor(R.color.white));
        }
    }


}
