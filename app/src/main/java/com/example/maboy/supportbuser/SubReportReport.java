package com.example.maboy.supportbuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class SubReportReport extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ImageView ivBack;
    private Button btSend;
    private EditText edtContent;

    private RadioGroup rgRg;
    private boolean rg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_report);

        initViews();
    }

    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back_report);
        btSend = (Button) findViewById(R.id.bt_gop_y_khac);
        rgRg = (RadioGroup) findViewById(R.id.rg_rg);
        edtContent = (EditText) findViewById(R.id.edt_content_report);

        ivBack.setOnClickListener(this);
        btSend.setOnClickListener(this);
        rgRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_report:
                onBackPressed();
                break;
            case R.id.bt_gop_y_khac:
                check();
                break;
            default:
                break;
        }
    }

    private void check() {
        if (isRg() && !edtContent.getText().toString().equals("")) {
            Toast.makeText(this, "Cảm ơn quý khách đã góp ý" +"\nChúng tôi sẽ xem xét và cải thiện dịch vụ", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        setRg(true);
    }

    public boolean isRg() {
        return rg;
    }

    public void setRg(boolean rg) {
        this.rg = rg;
    }
}
