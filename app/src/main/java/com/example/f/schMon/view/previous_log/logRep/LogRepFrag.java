/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.previous_log.logRep;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.f.schMon.R;
import com.example.f.schMon.controller.Global;
import com.example.f.schMon.controller.Helper;
import com.example.f.schMon.model.DAO;
import com.example.f.schMon.model.DAO2;
import com.example.f.schMon.model.appInner.School;
import com.example.f.schMon.view.report.RepMdlin;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;


public class LogRepFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private final String TAG = LogRepFrag.class.getName();
    List<List<RepMdlin>> listList = new ArrayList<>();//list of repModelinList of all schools;
    List<School> schoolList = new ArrayList<>();

    private String schID;
    private String schName;

    LogRepAdapter adapter;
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;


    @BindView(R.id.logListView)
    ListView listview;

    private SQLiteDatabase db;
    private int n, m;
    private String dateLastRep;
    School school;
    private List<School> schList;
    private List<Integer> syncImgList = new ArrayList<>();
    private List<RepMdlin> repMdlins = new ArrayList<>();
    private List<RepMdlin> repModelinsSches = new ArrayList<>();
    private List<List<RepMdlin>> list = new ArrayList<>();
    private List<String> dateList = new ArrayList<>();
    Unbinder unbinder;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.smoothPrg)
    SmoothProgressBar pb;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = Global.getDB_with_nativeWay_writable(Global.dbName);

        //================= set data ==========================
        new LoadData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pb.setVisibility(View.VISIBLE);
                new LoadData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

    private void setSyncImg(List<List<RepMdlin>> listList) {
        for (List<RepMdlin> temp : listList) {
            if (Helper.isReportOfThisSchoolSynced(temp)) {
                syncImgList.add(R.drawable.ic_sync_complete);
            } else syncImgList.add(R.drawable.ic_sync_incomplete);
        }
    }

    public LogRepFrag() {
        // Required empty public constructor
    }


    public static LogRepFrag newInstance(String param1, String param2) {
        LogRepFrag fragment = new LogRepFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        //args.putInt(ARG_ SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_log_common, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /*=================================== Load data =========================================*/
    class LoadData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            schoolList = DAO.getAllInfoOfAllSchool();
            listList = DAO2.getLastRepModelinOfAllSchool();
            setSyncImg(listList);
            adapter = new LogRepAdapter(getActivity(), listList, schoolList, syncImgList);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pb.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            listview.setAdapter(adapter);
        }
    }


}
