package com.example.maboy.supportbuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TableRow trReport;
    private TableRow trExamine;
    private TableRow trFeedback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        initViews();
    }

    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back_report);
        trReport = (TableRow) findViewById(R.id.tr_report);
        trExamine = (TableRow) findViewById(R.id.tr_examine);
        trFeedback = (TableRow) findViewById(R.id.tr_feedback);

        ivBack.setOnClickListener(this);
        trReport.setOnClickListener(this);
        trExamine.setOnClickListener(this);
        trFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_report:
//                startActivity(new Intent(this, MainActivity.class));
                onBackPressed();
                break;

            case R.id.tr_report:
                startActivity(new Intent(this, SubReportReport.class));
                break;

            case R.id.tr_examine:
                startActivity(new Intent(this, SubReportExamine.class));
                break;

            case R.id.tr_feedback:
                startActivity(new Intent(this, SubReportFeedback.class));
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}
