package com.example.maboy.supportbuser;

import android.content.Context;
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

import java.util.ArrayList;

public class Tab1TuyenBuyt extends Fragment implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {

    private static final String TAG = "MABOY";
    private SearchView sv;
    private ListView lv;
    private ListViewAdapter adapter;
    private ArrayList<Bus> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e(TAG, "onCreateView: " );

        View rootView = inflater.inflate(R.layout.tab1_tuyen_bus, container, false);

        sv = (SearchView) rootView.findViewById(R.id.sv_input_tuyen_bus);
        lv = (ListView) rootView.findViewById(R.id.lv_bus);

        lv.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e(TAG, "onActivityCreated: " );

        initContentListView();
    }

    private void initContentListView() {

        arrayList = new ArrayList<>();

        arrayList.add(new Bus(R.drawable.bus, "HV Cảnh sát - CV nước Hồ Tây", 13));
        arrayList.add(new Bus(R.drawable.bus, "Mai Động - SVĐ Quốc gia", 26));
        arrayList.add(new Bus(R.drawable.bus, "BX Nam Thăng Long - BX Yên Nghĩa", 27));
        arrayList.add(new Bus(R.drawable.bus, "Nhổn - Giáp bát", 32));

        adapter = new ListViewAdapter(getActivity(), arrayList);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getActivity(), "..." + arrayList.get(i).getId(), Toast.LENGTH_SHORT).show();
        sendIdBus(arrayList.get(i).getId());  // put id của bus sang MainActivity
    }



    private void sendIdBus(int id){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("BUS_ID", id);
        intent.putExtra("ID", bundle);
        startActivity(intent);
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
