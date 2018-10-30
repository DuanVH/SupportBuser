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

public class SubReportFeedback extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ImageView ivBack;
    private EditText edtContent;
    private Button btDannhGia;

    private RadioGroup rgSpeed, rgFuntion, rgExactly;
    private int i, j, k;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_feedback);

        initViews();
    }

    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back_feedback);
        rgSpeed = (RadioGroup) findViewById(R.id.rg_feedback_speed);
        rgFuntion = (RadioGroup) findViewById(R.id.rg_feedback_function);
        rgExactly = (RadioGroup) findViewById(R.id.rg_feedback_exactly);
        edtContent = (EditText) findViewById(R.id.edt_content);
        btDannhGia = (Button) findViewById(R.id.bt_danh_gia);

        ivBack.setOnClickListener(this);
        rgSpeed.setOnCheckedChangeListener(this);
        rgFuntion.setOnCheckedChangeListener(this);
        rgExactly.setOnCheckedChangeListener(this);
        btDannhGia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back_feedback:
                onBackPressed();
                break;

            case R.id.bt_danh_gia:
                check();
                break;
        }

    }

    private void check() {
        int c = getI() + getJ() + getK();
        String str = edtContent.getText().toString();
        if (c == 3 && !str.equals("")) {
            Toast.makeText(this, "Cảm ơn những đóng góp của bạn" + "\nChúng tôi sẽ xem xét và nâng cấp trog phiên bản tiếp theo", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()) {
            case R.id.rg_feedback_speed:
                setI(1);
                break;
            case R.id.rg_feedback_function:
                setJ(1);
                break;
            case R.id.rg_feedback_exactly:
                setK(1);
                break;
        }

    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getK() {
        return k;
    }

    public void setI(int i) {

        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setK(int k) {
        this.k = k;
    }
}
