package com.example.maboy.supportbuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SubReportExamine extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ImageView ivBack;
    private Button btSend;

    private RadioGroup rg1, rg2, rg3, rg4, rg5;
    private EditText edtKhaoSat;
    private int i1, i2, i3, i4, i5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_examine);

        initViews();
    }

    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back_examine);
        rg1 = (RadioGroup) findViewById(R.id.rg_1);
        rg2 = (RadioGroup) findViewById(R.id.rg_2);
        rg3 = (RadioGroup) findViewById(R.id.rg_3);
        rg4 = (RadioGroup) findViewById(R.id.rg_4);
        rg5 = (RadioGroup) findViewById(R.id.rg_5);
        edtKhaoSat = (EditText) findViewById(R.id.edt_khao_sat_khac);
        btSend = (Button) findViewById(R.id.bt_khao_sat);

        ivBack.setOnClickListener(this);
        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
        rg3.setOnCheckedChangeListener(this);
        rg4.setOnCheckedChangeListener(this);
        rg5.setOnCheckedChangeListener(this);
        btSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_examine:
                onBackPressed();
                break;
            case R.id.bt_khao_sat:
                check();
            default:
                break;
        }
    }

    private void check() {
        int k = getI1() + getI2() + getI3() + getI4() + getI5();
        String str = edtKhaoSat.getText().toString();
        if (k == 5 && !str.equals("")) {
            Toast.makeText(this, "Phồi của bạn đã được gửi thành công", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()) {
            case R.id.rg_1:
                setI1(1);
                break;

            case R.id.rg_2:
                setI2(1);
                break;

            case R.id.rg_3:
                setI3(1);
                break;

            case R.id.rg_4:
                setI4(1);
                break;

            case R.id.rg_5:
                setI5(1);
                break;
        }
    }

    public int getI1() {
        return i1;
    }

    public int getI2() {
        return i2;
    }

    public int getI3() {
        return i3;
    }

    public int getI4() {
        return i4;
    }

    public int getI5() {
        return i5;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public void setI2(int i2) {
        this.i2 = i2;
    }

    public void setI3(int i3) {
        this.i3 = i3;
    }

    public void setI4(int i4) {
        this.i4 = i4;
    }

    public void setI5(int i5) {
        this.i5 = i5;
    }

}
