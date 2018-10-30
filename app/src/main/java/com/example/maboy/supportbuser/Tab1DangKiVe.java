package com.example.maboy.supportbuser;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class Tab1DangKiVe extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "DUAN";
    private static final int GALLERY_REQUEST_ANH_THE = 1317;
    private static final int GALLERY_REQUEST_PIC_01 = 13;
    private static final int GALLERY_REQUEST_PIC_02 = 17;
    private static final int GALLERY_REQUEST_PIC_03 = 24;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_dang_ki_ve, container, false);

        // initViews()
        rgDoiTuong = (RadioGroup) rootView.findViewById(R.id.rg_doi_tuong_vt);
        rbBt = (RadioButton) rootView.findViewById(R.id.rb_bt_vt);
        rbUuTien = (RadioButton) rootView.findViewById(R.id.rb_uu_tien_vt);

        rgLoaiThe = (RadioGroup) rootView.findViewById(R.id.rg_loai_the_vt);
        rb1 = (RadioButton) rootView.findViewById(R.id.rb_mot_tuyen_vt);
        rb2 = (RadioButton) rootView.findViewById(R.id.rb_lien_tuyen_vt);

        trChonDoiTuong = (TableRow) rootView.findViewById(R.id.tr_chon_doi_tuong_vt);
        trChonDoiTuong.setVisibility(View.GONE);  // Ẩn đi hoàn toàn và bị View khác chiếm chỗ
        tvSelect = (TextView) rootView.findViewById(R.id.tv_chon_doi_tuong_vt);

        trChonTuyen = (TableRow) rootView.findViewById(R.id.tr_chon_tuyen_vt);
        trChonTuyen.setVisibility(View.GONE);
        tvTuyenDaChon = (TextView) rootView.findViewById(R.id.tv_tuyen_da_chon_vt);

        edtHoTen = (EditText) rootView.findViewById(R.id.edt_ho_ten_vt);
        edtNgaySinh = (EditText) rootView.findViewById(R.id.edt_birth_day_vt);
        edtDiaChi = (EditText) rootView.findViewById(R.id.edt_address_vt);
        edtPhone = (EditText) rootView.findViewById(R.id.edt_phone_vt);
        edtNgayNhan = (EditText) rootView.findViewById(R.id.edt_ngay_nhan_the_vt);

        checkBox = (CheckBox) rootView.findViewById(R.id.cb_dang_ki_nhan_the_tai_nha_vt);

        trNoiNhan = (TableRow) rootView.findViewById(R.id.tr_noi_nhan_vt);
        tvContentNoiNhan = (TextView) rootView.findViewById(R.id.tv_content_noi_nhan_vt);

        trAnhThe = (TableRow) rootView.findViewById(R.id.tr_anh_the_vt);
        ivAnhThe = (ImageView) rootView.findViewById(R.id.iv_anh_the_vt);

        trPic1 = (TableRow) rootView.findViewById(R.id.tr_pic1_vt);
        trPic1.setVisibility(View.GONE);
        trPic2 = (TableRow) rootView.findViewById(R.id.tr_pic2_vt);
        trPic2.setVisibility(View.GONE);
        trPic3 = (TableRow) rootView.findViewById(R.id.tr_pic3_vt);
        trPic3.setVisibility(View.GONE);

        tvPic1 = (TextView) rootView.findViewById(R.id.tv_pic1_vt);
        tvPic2 = (TextView) rootView.findViewById(R.id.tv_pic2_vt);
        tvPic3 = (TextView) rootView.findViewById(R.id.tv_pic3_vt);

        ivPic1 = (ImageView) rootView.findViewById(R.id.iv_pic1_vt);
        ivPic2 = (ImageView) rootView.findViewById(R.id.iv_pic2_vt);
        ivPic3 = (ImageView) rootView.findViewById(R.id.iv_pic3_vt);

        btDangKi = (Button) rootView.findViewById(R.id.bt_dang_ki_vt);

        // Event
        rgDoiTuong.setOnCheckedChangeListener(this);
        rgLoaiThe.setOnCheckedChangeListener(this);

        rbUuTien.setOnCheckedChangeListener(this);
        rb1.setOnCheckedChangeListener(this);

        trChonDoiTuong.setOnClickListener(this);
        trChonTuyen.setOnClickListener(this);
        trNoiNhan.setOnClickListener(this);
        trAnhThe.setOnClickListener(this);
        trPic1.setOnClickListener(this);
        trPic2.setOnClickListener(this);
        trPic3.setOnClickListener(this);
        btDangKi.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(this);


        return rootView;
    }

    // Kiểm tra sự thay đổi ở 2 RadioGroup
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
    }

    // Kiểm tra RadioButton Ưu tiên & RadioButton Một tuyến
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.rb_uu_tien_vt:
                if (b == true) {  // Đối tượng ưu tiên
                    trChonDoiTuong.setVisibility(View.VISIBLE);
                    dt = true;
                } else {  //Đối tượng bình thường
                    trChonDoiTuong.setVisibility(View.GONE);
                    tvSelect.setText("");
                    dt = false;
                }
                break;
            case R.id.rb_mot_tuyen_vt:
                if (b == true) {
                    trChonTuyen.setVisibility(View.VISIBLE);
                    t = true;
                } else {
                    t = false;
                    trChonTuyen.setVisibility(View.GONE);
                    tvTuyenDaChon.setText("");
                }
                break;

            case R.id.cb_dang_ki_nhan_the_tai_nha_vt:
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

            case R.id.tr_chon_doi_tuong_vt:
                showListDoiTuong();
                break;

            case R.id.tr_chon_tuyen_vt:
                showListChonTuyen();
                break;

            case R.id.tr_noi_nhan_vt:
                showListNoiNhan();
                break;

            case R.id.tr_anh_the_vt:
                setPhoto(R.id.tr_anh_the_vt);
                break;

            case R.id.tr_pic1_vt:
                setPhoto(R.id.tr_pic1_vt);
                break;

            case R.id.tr_pic2_vt:
                setPhoto(R.id.tr_pic2_vt);
                break;

            case R.id.tr_pic3_vt:
                setPhoto(R.id.tr_pic3_vt);
                break;

            case R.id.bt_dang_ki_vt:
                check();
                break;
        }
    }

    // danh sách các điểm bán vé
    private void showListNoiNhan() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
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
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
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
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
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
            case R.id.tr_anh_the_vt:
                startActivityForResult(galleryIntent, GALLERY_REQUEST_ANH_THE);
                break;
            case R.id.tr_pic1_vt:
                startActivityForResult(galleryIntent, GALLERY_REQUEST_PIC_01);
                break;
            case R.id.tr_pic2_vt:
                startActivityForResult(galleryIntent, GALLERY_REQUEST_PIC_02);
                break;
            case R.id.tr_pic3_vt:
                startActivityForResult(galleryIntent, GALLERY_REQUEST_PIC_03);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            Toast.makeText(getActivity(), "Đơn sẽ được chấp nhận trong trường hợp thông tin hợp lệ", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();

        } else {
            Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
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
