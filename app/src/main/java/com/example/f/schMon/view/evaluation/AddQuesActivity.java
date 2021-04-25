/*
 * Copyright (c) MiR FeRdOuS
 */

package com.example.f.schMon.view.evaluation;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.f.schMon.R;
import com.example.f.schMon.controller.Global;
import com.example.f.schMon.controller.Helper;
import com.example.f.schMon.model.DAO;
import com.example.f.schMon.model.DAO2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class AddQuesActivity extends AppCompatActivity {
    private final Context context = AddQuesActivity.this;
    private final String TAG = AddQuesActivity.class.getName();
    private SQLiteDatabase db;
    private String timeLm, grade, openMode, schID, gradeID, date, attendance, subject, chapter;
    private boolean subjectInputted = false, chapterInputted = false;
    private SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
    private EvalQuesModel evalQuesModel;
    private EvlModelin data;
    private Intent intent;
    private String subjectDefault, chapterDefault;
    private List<String> chapterList = new ArrayList<String>();
    private List<String> subjectList = new ArrayList<String>();
    private ArrayAdapter<String> dataAdapterChapter;
    private ArrayAdapter<String> dataAdapterSubject;
    @BindView(R.id.QuestionText)
    EditText quesET;
    @BindView(R.id.AnswerText)
    EditText ansET;
    @BindView(R.id.saveAns)
    Button savaBtn;
    @BindView(R.id.spinnerSubject)
    Spinner spinnerSubject;
    @BindView(R.id.spinnerChapter)
    Spinner spinnerChapter;

//___________________Start option menu______________________________________

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //___________________End option menu______________________________________
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ques);
        ButterKnife.bind(this);
        //appbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = Global.getDB_with_nativeWay_writable(Global.dbName);

        intent = getIntent();
        openMode = intent.getStringExtra(Global.openMode);
        schID = intent.getStringExtra("schID");//schoolSync id
        gradeID = intent.getStringExtra("gradeID");
        date = intent.getStringExtra("date");
        attendance = intent.getStringExtra("attendance");


/*===================================== spinner =======================================================================*/


        subjectList = DAO2.getSubjectList(gradeID); // subAndChapt.get("subject");
        subjectDefault = subjectList.get(0);
        chapterList = DAO2.getChapterList(gradeID, subjectDefault);

 /*subject*/

       /* SharedPrefHelper.saveSubjectList(subjectList);
        Log.d(TAG, "total : " + SharedPrefHelper.getSubjectList().size()
                + SharedPrefHelper.getSubjectList().toString());*/

        dataAdapterSubject = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, subjectList);
        dataAdapterSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(dataAdapterSubject);

        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectDefault = subjectList.get(position);
                subject = subjectDefault;
                chapterList = DAO2.getChapterList(gradeID, subjectDefault);

                dataAdapterChapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, chapterList);
                dataAdapterChapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerChapter.setAdapter(dataAdapterChapter);
                dataAdapterChapter.notifyDataSetChanged();

                subjectInputted = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


/* chapter */


        dataAdapterChapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, chapterList);
        dataAdapterChapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChapter.setAdapter(dataAdapterChapter);

        spinnerChapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chapterDefault = chapterList.get(position);
                chapter = chapterDefault;

                chapterInputted = true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void onClickSaveAns(View view) {
        String ques, ans;
        ques = quesET.getText().toString();
        ans = ansET.getText().toString();
        timeLm = df1.format(new Date(System.currentTimeMillis()));
        grade = DAO.getInfoOfASchool(schID).grade;

        if (!ques.equals("") && !ans.equals("") && subjectInputted && chapterInputted) {
            evalQuesModel = new EvalQuesModel(
                    null,
                    Helper.generateUniqueID("userques"),//localid
                    gradeID,
                    ques,
                    ans,
                    "true",
                    "1",
                    timeLm,
                    "1",
                    grade,
                    subject,
                    chapter
            );

            DAO2.insertNewQuestion(evalQuesModel);

            Toasty.success(context, "New Question Added", Toast.LENGTH_SHORT, true).show();

            returnResult_to_StartingActivity();

            //   startEvalActivity_QuestionList();
        } else {
            new AlertDialog.Builder(context).setIcon(R.drawable.ic_warning)
                    .setTitle("Please Input Question & Answer And Select Subject & Chapter ...")
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).create().show();
        }
    }

    public EvlModelin evalQuesModelToEvlModelin(EvalQuesModel evalQuesModel) {
        EvlModelin eval = new EvlModelin();
        eval.setServerIDQues(evalQuesModel.getServer_id());
        eval.setLocalIDQues(evalQuesModel.getLocal_id());//user generated local id
        eval.setQues(evalQuesModel.getQues());
        eval.setAnswer(evalQuesModel.getAnswer());
        eval.setActive(evalQuesModel.getActive());
        eval.setSynced(evalQuesModel.getSynced());
        eval.setTime_lm(evalQuesModel.getTimeLm());
        eval.setServer_ques(evalQuesModel.getServerQuestion());
        eval.setGrade(evalQuesModel.getGrade());
        eval.setGradeID(evalQuesModel.getGradeID());
        eval.setSubject(evalQuesModel.getSubject());
        eval.setChapter(evalQuesModel.getChapter());
        return eval;
    }

    private void returnResult_to_StartingActivity() {
        this.data = evalQuesModelToEvlModelin(evalQuesModel);

        Intent returnIntent = new Intent();
        returnIntent.putExtra(Global.openMode, Global.RunMode);
        returnIntent.putExtra("schID", this.schID);
        returnIntent.putExtra("gradeID", this.gradeID);
        returnIntent.putExtra("date", this.date);
        returnIntent.putExtra("attendance", this.attendance);
        returnIntent.putExtra("data", this.data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    //============================== unused ===========================================================================================

    void startEvalActivity_QuestionList() {
        Intent intent = new Intent(context, EvlActivity.class);
        intent.putExtra(Global.openMode, Global.RunMode);
        intent.putExtra("schID", schID);
        intent.putExtra("date", this.date);
        intent.putExtra("attendance", attendance);
        startActivity(intent);
        overridePendingTransition(R.anim.lefttoright, 0);
        finish();
    }

}
