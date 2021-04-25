/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.analyseAndTest;

import com.example.f.schMon.model.DAO;
import com.example.f.schMon.model.DAO2;
import com.example.f.schMon.view.report.RepMdlin;

import java.util.HashSet;
import java.util.List;

public class T1 {
    public static HashSet returnUniqueString() {
        List<RepMdlin> list = DAO2.getAllReportofAllSchool();
        HashSet<String> temp = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i).getServerIDAns());
        }
        return temp;
    }
}
