package com.example.maboy.supportbuser;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.lang.UCharacterEnums;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private static final String TAG = "DUAN";
    private static final int GALLERY_REQUEST_ANH_THE = 1317;
    private static final int GALLERY_REQUEST_PIC_01 = 13;
    private static final int GALLERY_REQUEST_PIC_02 = 17;
    private static final int GALLERY_REQUEST_PIC_03 = 24;

    private ImageView ivBack;

    private RadioGroup rgDoiTuong, rgLoaiThe;
    private RadioButton rbBt, rbUuTien, rb1, rb2;

    private TableRow trChonDoiTuong;
    private TextView tvSelect;

    private TableRow trChonTuyen;
    private TextView tvTuyenDaChon;


    private EditText edtHoTen, edtNgaySinh, edtDiaChi, edtPhone, edtNgayNhan;
    private CheckBox checkBox;

    private TableRow trNoiNhan;
    private TextView tvContentNoiNhan;

    private TableRow trAnhThe;
    private ImageView ivAnhThe;

    private TableRow trPic1, trPic2, trPic3;
    private TextView tvPic1, tvPic2, tvPic3;
    private ImageView ivPic1, ivPic2, ivPic3;

    private Button btDangKi;

    // Event
    private boolean dt = false;  // Đối tượng
    private boolean t = false;  // Tuyến
    private boolean nn = false;  // Nơi nhận thẻ (Điểm bán vé)
    private boolean select = true;
    private boolean edt = false;
    private boolean noiNhan = false;
    private boolean anhThe = false;
    private boolean pic1 = true;
    private boolean pic2 = true;
    private boolean pic3 = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket);
        initViews();
    }

    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back_ticket);

        rgDoiTuong = (RadioGroup) findViewById(R.id.rg_doi_tuong);
        rbBt = (RadioButton) findViewById(R.id.rb_bt);
        rbUuTien = (RadioButton) findViewById(R.id.rb_uu_tien);

        rgLoaiThe = (RadioGroup) findViewById(R.id.rg_loai_the);
        rb1 = (RadioButton) findViewById(R.id.rb_mot_tuyen);
        rb2 = (RadioButton) findViewById(R.id.rb_lien_tuyen);

        trChonDoiTuong = (TableRow) findViewById(R.id.tr_chon_doi_tuong);
        trChonDoiTuong.setVisibility(View.GONE);  // Ẩn đi hoàn toàn và bị View khác chiếm chỗ
        tvSelect = (TextView) findViewById(R.id.tv_chon_doi_tuong);

        trChonTuyen = (TableRow) findViewById(R.id.tr_chon_tuyen);
        trChonTuyen.setVisibility(View.GONE);
        tvTuyenDaChon = (TextView) findViewById(R.id.tv_tuyen_da_chon);

        edtHoTen = (EditText) findViewById(R.id.edt_ho_ten);
        edtNgaySinh = (EditText) findViewById(R.id.edt_birth_day);
        edtDiaChi = (EditText) findViewById(R.id.edt_address);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtNgayNhan = (EditText) findViewById(R.id.edt_ngay_nhan_the);

        checkBox = (CheckBox) findViewById(R.id.cb_dang_ki_nhan_the_tai_nha);

        trNoiNhan = (TableRow) findViewById(R.id.tr_noi_nhan);
        tvContentNoiNhan = (TextView) findViewById(R.id.tv_content_noi_nhan);

        trAnhThe = (TableRow) findViewById(R.id.tr_anh_the);
        ivAnhThe = (ImageView) findViewById(R.id.iv_anh_the);

        trPic1 = (TableRow) findViewById(R.id.tr_pic1);
        trPic1.setVisibility(View.GONE);
        trPic2 = (TableRow) findViewById(R.id.tr_pic2);
        trPic2.setVisibility(View.GONE);
        trPic3 = (TableRow) findViewById(R.id.tr_pic3);
        trPic3.setVisibility(View.GONE);

        tvPic1 = (TextView) findViewById(R.id.tv_pic1);
        tvPic2 = (TextView) findViewById(R.id.tv_pic2);
        tvPic3 = (TextView) findViewById(R.id.tv_pic3);

        ivPic1 = (ImageView) findViewById(R.id.iv_pic1);
        ivPic2 = (ImageView) findViewById(R.id.iv_pic2);
        ivPic3 = (ImageView) findViewById(R.id.iv_pic3);

        btDangKi = (Button) findViewById(R.id.bt_dang_ki);

        // Event
        rgDoiTuong.setOnCheckedChangeListener(this);
        rgLoaiThe.setOnCheckedChangeListener(this);

        rbUuTien.setOnCheckedChangeListener(this);
        rb1.setOnCheckedChangeListener(this);

        ivBack.setOnClickListener(this);
        trChonDoiTuong.setOnClickListener(this);
        trChonTuyen.setOnClickListener(this);
        trNoiNhan.setOnClickListener(this);
        trAnhThe.setOnClickListener(this);
        trPic1.setOnClickListener(this);
        trPic2.setOnClickListener(this);
        trPic3.setOnClickListener(this);
        btDangKi.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(this);
    }

    // Kiểm tra sự thay đổi ở 2 RadioGroup
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
    }

    // Kiểm tra RadioButton Ưu tiên & RadioButton Một tuyến
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.rb_uu_tien:
                if (b == true) {  // Đối tượng ưu tiên
                    trChonDoiTuong.setVisibility(View.VISIBLE);
                    dt = true;
                } else {  //Đối tượng bình thường
                    trChonDoiTuong.setVisibility(View.GONE);
                    tvSelect.setText("");
                    dt = false;
                }
                break;
            case R.id.rb_mot_tuyen:
                if (b == true) {
                    trChonTuyen.setVisibility(View.VISIBLE);
                    t = true;
                } else {
                    t = false;
                    trChonTuyen.setVisibility(View.GONE);
                    tvTuyenDaChon.setText("");
                }
                break;

            case R.id.cb_dang_ki_nhan_the_tai_nha:
                if (b == true) {  // Nhận thẻ tại nhà -> ko kiểm tra textview
                    tvContentNoiNhan.setText("");
                    trNoiNhan.setVisibility(View.GONE);
                    setNoiNhan(true);
                    nn = true;
                } else {  // Nhận thẻ tại điểm  bán vé
                    trNoiNhan.setVisibility(View.VISIBLE);
                    nn = false;
                    setNoiNhan(false);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_ticket:
                onBackPressed();
                break;

            case R.id.tr_chon_doi_tuong:
                showListDoiTuong();
                break;

            case R.id.tr_chon_tuyen:
                showListChonTuyen();
                break;

            case R.id.tr_noi_nhan:
                showListNoiNhan();
                break;

            case R.id.tr_anh_the:
                setPhoto(R.id.tr_anh_the);
                break;

            case R.id.tr_pic1:
                setPhoto(R.id.tr_pic1);
                break;

            case R.id.tr_pic2:
                setPhoto(R.id.tr_pic2);
                break;

            case R.id.tr_pic3:
                setPhoto(R.id.tr_pic3);
                break;

            case R.id.bt_dang_ki:
                check();
                break;
        }
    }

    // danh sách các điểm bán vé
    private void showListNoiNhan() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TicketActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Bách Khoa");
        arrayAdapter.add("BX Gia Lâm");
        arrayAdapter.add("BX Giáp Bát");
        arrayAdapter.add("BX Mỹ Đình");
        arrayAdapter.add("BX Nam Thăng Long");
        arrayAdapter.add("BX Yên Nghĩa");
        arrayAdapter.add("Công viên Nghĩa Đô ");
        arrayAdapter.add("Công viên Thống Nhất ");
        arrayAdapter.add("Công viên Thủ Lệ ");
        arrayAdapter.add("ĐH Lao động và Xã hội");
        arrayAdapter.add("Hoàng Đạo Thuý");
        arrayAdapter.add("Học viện Bưu chính viễn thông");
        arrayAdapter.add("Học viện Nông Nghiệp");
        arrayAdapter.add("Học viện Quân y");
        arrayAdapter.add("Kim Chung");
        arrayAdapter.add("Kim Ngưu");
        arrayAdapter.add("Linh Đàm");
        arrayAdapter.add("Long Biên");
        arrayAdapter.add("Nguyễn Công Trứ");
        arrayAdapter.add("Nguyễn Cơ Thạch");
        arrayAdapter.add("Nhà chờ BRT Núi Trúc");
        arrayAdapter.add("Siêu thị HC 549 Nguyễn Văn Cừ (Đức Giang)");
        arrayAdapter.add("Trần Khánh Dư");

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String strName = arrayAdapter.getItem(which);
                tvContentNoiNhan.setText(strName);
            }
        });
        builderSingle.show();
    }

    // Danh sách các tuýen buýt
    private void showListChonTuyen() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TicketActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("01 Gia Lâm - Yên Nghĩa");
        arrayAdapter.add("02 Bác Cổ - Yên Nghĩa");
        arrayAdapter.add("03 Giáp Bát - Gia Lâm");
        arrayAdapter.add("03B Bến xe Nước Ngầm - Vimcom - Phúc Lợi");
        arrayAdapter.add("04 LOng Biên - Bến xe Nước Ngầm");
        arrayAdapter.add("05 KĐT Linh Đàm - Phú Diễn");
        arrayAdapter.add("07 Cầu Giấy - Nội Bài");
        arrayAdapter.add("08 Long Biên - Đông Mỹ");
        arrayAdapter.add("09 Bờ Hồ - Bờ Hồ");
        arrayAdapter.add("10A Long Biên - Từ Sơn");


        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                tvTuyenDaChon.setText(strName);
            }
        });
        builderSingle.show();
    }

    // Danh sách đối tượng được ưu tiên
    private void showListDoiTuong() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TicketActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Học sinh - Sinh viên");
        arrayAdapter.add("Công nhân khu CN");
        arrayAdapter.add("Người cao tuổi");

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String strName = arrayAdapter.getItem(which);
                tvSelect.setText(strName);

                if (strName.equals("Học sinh - Sinh viên")) {
                    trPic1.setVisibility(View.VISIBLE);
                    trPic2.setVisibility(View.VISIBLE);
                    trPic3.setVisibility(View.VISIBLE);
                    tvPic1.setText("Thẻ HSSV (Mặt trước)");
                    tvPic2.setText("Thẻ HSSV (Mặt sau)");
                    tvPic3.setText("Đơn đăng kí");

                    setPic1(false);
                    setPic2(false);
                    setPic3(false);

                } else if (strName.equals("Công nhân khu CN")) {
                    trPic1.setVisibility(View.VISIBLE);
                    trPic2.setVisibility(View.VISIBLE);
                    trPic3.setVisibility(View.VISIBLE);
                    tvPic1.setText("Thẻ nhân viên (Mặt trước)");
                    tvPic2.setText("Thẻ nhân viên (Mặt sau)");
                    tvPic3.setText("Xác nhận của công ty");

                    setPic1(false);
                    setPic2(false);
                    setPic3(false);

                } else if (strName.equals("Người cao tuổi")) {
                    trPic1.setVisibility(View.VISIBLE);
                    trPic2.setVisibility(View.VISIBLE);
                    trPic3.setVisibility(View.GONE);
                    tvPic1.setText("Chứng minh nhân dân (Mặt trước)");
                    tvPic2.setText("Chứng minh nhân dân (Mặt sau)");

                    setPic1(false);
                    setPic2(false);
                }
            }
        });
        builderSingle.show();
    }

    private void setPhoto(int id) {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        switch (id) {
            case R.id.tr_anh_the:
                startActivityForResult(galleryIntent, GALLERY_REQUEST_ANH_THE);
                break;
            case R.id.tr_pic1:
                startActivityForResult(galleryIntent, GALLERY_REQUEST_PIC_01);
                break;
            case R.id.tr_pic2:
                startActivityForResult(galleryIntent, GALLERY_REQUEST_PIC_02);
                break;
            case R.id.tr_pic3:
                startActivityForResult(galleryIntent, GALLERY_REQUEST_PIC_03);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_ANH_THE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData(); //Lấy ra uri của image
            ivAnhThe.setImageURI(imageUri);
            setAnhThe(true);
        }
        if (requestCode == GALLERY_REQUEST_PIC_01 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData(); //Lấy ra uri của image
            ivPic1.setImageURI(imageUri);
            setPic1(true);
        }
        if (requestCode == GALLERY_REQUEST_PIC_02 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData(); //Lấy ra uri của image
            ivPic2.setImageURI(imageUri);
            setPic2(true);
        }
        if (requestCode == GALLERY_REQUEST_PIC_03 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData(); //Lấy ra uri của image
            ivPic3.setImageURI(imageUri);
            setPic3(true);
        }
    }

    // Kiểm tra các thông tin đã được điền đầy đủ chưa?
    private void check() {
        checkDoiTuong();
        checkTuyen();
        checkEdt();
        checkNoiNhan();
        if (isSelect() && isEdt() && isNoiNhan() && isAnhThe() && isPic1() && isPic2() && isPic3()) {
            Toast.makeText(this, "Đơn sẽ được chấp nhận trong trường hợp thông tin hợp lệ", Toast.LENGTH_SHORT).show();
            onBackPressed();

        } else {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    // Kiem tra da chon doi tuong chua
    // Kiem tra da chon tuyen chua
    private void checkDoiTuong() {
        if (dt == true) {
            if (!tvSelect.getText().toString().equals("")) {
                setSelect(true);
            } else {
                setSelect(false);
            }
        }
    }

    private void checkTuyen() {
        if (t == true) {
            if (!tvTuyenDaChon.getText().toString().equals("")) {
                setSelect(true);
            } else {
                setSelect(false);
            }
        }
    }

    // Kiểm tra Ho ten, Ngay sinh, Dia chi, Dia thoai, Ngay nhan the
    private void checkEdt() {
        if (!edtHoTen.getText().toString().equals("")) {
            if (!edtNgaySinh.getText().toString().equals("")) {
                if (!edtDiaChi.getText().toString().equals("")) {
                    if (!edtPhone.getText().toString().equals("")) {
                        if (!edtNgayNhan.getText().toString().equals("")) {
                            setEdt(true);
                        }
                    }
                }
            }
        } else {
            setEdt(false);
        }
    }

    // Kiểm tra nơi nhận thẻ (Các điểm bán vé)
    private void checkNoiNhan() {
        if (nn == false) {
            if (!tvContentNoiNhan.getText().toString().equals("")) {
                setNoiNhan(true);
            } else {
                setNoiNhan(false);
            }
        }
    }

    public void setAnhThe(boolean anhThe) {
        this.anhThe = anhThe;
    }

    public void setPic1(boolean pic1) {
        this.pic1 = pic1;
    }

    public void setPic2(boolean pic2) {
        this.pic2 = pic2;
    }

    public void setPic3(boolean pic3) {
        this.pic3 = pic3;
    }

    public boolean isAnhThe() {
        return anhThe;
    }

    public boolean isPic1() {
        return pic1;
    }

    public boolean isPic2() {
        return pic2;
    }

    public boolean isPic3() {
        return pic3;
    }

    public void setNoiNhan(boolean noiNhan) {
        this.noiNhan = noiNhan;
    }

    public boolean isNoiNhan() {
        return noiNhan;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isSelect() {
        return select;
    }

    public void setEdt(boolean edt) {
        this.edt = edt;
    }

    public boolean isEdt() {
        return edt;
    }
}
