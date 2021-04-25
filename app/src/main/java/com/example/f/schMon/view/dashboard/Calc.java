/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.dashboard;

import android.util.Log;

import com.example.f.schMon.model.DAO;
import com.example.f.schMon.model.appInner.LastPerfMdl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mir Ferdous on 06/11/2017.
 */

public class Calc {
    private static final String TAG = Calc.class.getName();

    //========================== dash ==================================================
    public static List<Integer> avgPerformCalc(List<LastPerfMdl> lpm) {
        List<Integer> avgPerformanceList = new ArrayList<>();
        float attendanc = 0, evlalution = 0, report = 0, sumAttndce = 0, sumReport = 0, sumEvaluation = 0,
                attendancCount = 0, evaluationCount = 0, reportCount = 0;
       /* int lpmPerf;
        int totalSch = DAO.getTotalSchoolCount();
        Log.d(TAG, "Total Std: " + String.valueOf(totalSch));*/

        try {
            for (int i = 0; i < lpm.size(); i++) {
                if (lpm.get(i).getLastAttenPerform() != null) {
                    if (lpm.get(i).getLastAttenPerform().equalsIgnoreCase("")) {
                       /* lpmPerf = 0;
                        sumAttndce += 0;
                        attendancCount++;*/
                    } else {
                        // lpmPerf = (int) Float.parseFloat(lpm.get(i).getLastAttenPerform());
                        sumAttndce += Float.parseFloat(lpm.get(i).getLastAttenPerform());
                        attendancCount++;
                    }
                }

                if (lpm.get(i).getLastRepPerform() != null) {
                    if (lpm.get(i).getLastRepPerform().equalsIgnoreCase("")) {
                       /* lpmPerf = 0;
                        sumReport += 0;
                        reportCount++;*/
                    } else {
                        //lpmPerf = (int) Float.parseFloat(lpm.get(i).getLastRepPerform());
                        sumReport += Float.parseFloat(lpm.get(i).getLastRepPerform());
                        reportCount++;
                    }
                }
                if (lpm.get(i).getLastEvalPerform() != null) {
                    if (lpm.get(i).getLastEvalPerform().equalsIgnoreCase("")) {
                       /* lpmPerf = 0;
                        sumEvaluation += 0;
                        evaluationCount++;*/
                    } else {
                        //lpmPerf = (int) Float.parseFloat(lpm.get(i).getLastEvalPerform());
                        sumEvaluation += Float.parseFloat(lpm.get(i).getLastEvalPerform());
                        evaluationCount++;
                    }
                }
            }

            if (attendancCount > 0) {
                attendanc = sumAttndce / attendancCount;
                Log.d(TAG, "Sum Attndece" + sumAttndce + "  Count: " + attendancCount);
            }
            if (evaluationCount > 0) {
                evlalution = sumEvaluation / evaluationCount;
                Log.d(TAG, "Sum Evalution" + sumEvaluation + "  Count: " + evaluationCount);
            }
            if (reportCount > 0) {
                report = sumReport / reportCount;
                Log.d(TAG, "Sum Report: " + sumReport + "  Count: " + reportCount);
            }
            avgPerformanceList.add(Math.round(attendanc));
            avgPerformanceList.add(Math.round(evlalution));
            avgPerformanceList.add(Math.round(report));
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }

        return avgPerformanceList;
        /**index 0 = Attendance
         * index 1 = evaluation
         * index 2 = report environment
         * */
    }
}