package com.example.maboy.supportbuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Tab2DiemBanVe extends Fragment implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {

    private static final String TAG = "GIANG";
    private SearchView sv;
    private ListView lv;
    private ListDiemBanVeAdapter adapter;
    private ArrayList<DiemBanVe> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_diem_ban_ve, container, false);

        sv = (SearchView) rootView.findViewById(R.id.sv_input_diem_ban_ve);
        lv = (ListView) rootView.findViewById(R.id.lv_diem_ban_ve);

        lv.setOnItemClickListener(this);
        sv.setOnQueryTextListener(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e(TAG, "onActivityCreated: " );

        addArrayDiemBanVe();
    }

    private void addArrayDiemBanVe() {

        arrayList = new ArrayList<>();

        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A8) 144 Xuân Thuỷ", 21.036824, 105.782759));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A17) Bách Khoa", 21.007271, 105.845167));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A05) BX Gia Lâm", 21.048407, 105.878420));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A01) BX Giáp Bát", 20.980452, 105.841465));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A09) BX Mỹ Đình", 21.028740, 105.778312));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A07) BX Nam Thăng Long", 21.069453, 105.785894));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A18) BX Yên Nghĩa", 20.950448, 105.748121));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A20) Công viên Nghĩa Đô", 21.040087, 105.797440));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A03) Công viên Thống Nhất", 21.017244, 105.844010));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A04) Công viên Thủ Lệ", 21.029108, 105.803978));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A22) Hoàng Đạo Thuý", 21.006083, 105.805768));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A11) Học viện Bưu chính viễn thông", 20.980523, 105.787902));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A02) Học viện Nông nghiệp", 21.005906525283553 , 105.93310475349438));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A12) Học viện Quân y", 20.966948, 105.789412));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A19) Kiều Mai", 21.04522350934982 , 105.75137972831737));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A46) Kim Chung", 21.131562230727194 , 105.7794141769413));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A13) Kim Ngưu", 20.997219967165588 , 105.86194093254085));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A34) Linh Đàm", 20.969687586761935 , 105.83104827976229));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A15) Long Biên", 21.041230072336504 , 105.8496218470774));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A14) Nguyễn Công Trứ", 21.01377999234691 , 105.85303008102028));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A25) Nguyễn Cơ Thạch", 21.032576309050793 , 105.7654626667495));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A21) Nhà chờ BRT Núi Trúc", 21.028969957935562 , 105.8265177905555));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A23) Siêu thị HC 549 Nguyễn Văn Cừ (Đức Giang)", 21.049599210601162 , 105.8833631873132));
        arrayList.add(new DiemBanVe(R.drawable.cabin, "(A06) Trần Khánh Dư", 21.023497218317665 , 105.86083849816487));

        adapter = new ListDiemBanVeAdapter(getActivity(), arrayList);
        lv.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        LatLng latLng = new LatLng(arrayList.get(i).getLat(), arrayList.get(i).getLng());
        String title = arrayList.get(i).getName();
        String snippet = arrayList.get(i).getName();
        sendDiemBanVe(latLng, title, snippet);
    }

    private void sendDiemBanVe(LatLng latLng, String title, String snippet) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("LAT", latLng.latitude);
        bundle.putDouble("LNG", latLng.longitude);
        bundle.putString("TITLE", title);
        bundle.putString("SNIPPET", snippet);
        intent.putExtra("DBV", bundle);
        startActivity(intent);
        Log.e(TAG, "sendDiemBanVe: " );
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }


    @Override
    public boolean onQueryTextChange(String s) {
        String text = s;
        adapter.filter(text);
        return false;
    }
}
