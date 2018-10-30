package com.example.maboy.supportbuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by maboy on 01/03/2018.
 */

public class ActionSearch extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    private ListView lvSearch;
    private ListViewAdapter adapter;
    private SearchView searchView;
    private ArrayList<Bus> arrayList = new ArrayList<>();

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        initContent();
    }

    private void initContent() {

        arrayList.add(new Bus(R.drawable.bus, "HV Cảnh sát - CV nước Hồ Tây", 13));
        arrayList.add(new Bus(R.drawable.bus, "Mai Động - SVĐ Quốc gia", 26));
        arrayList.add(new Bus(R.drawable.bus, "BX Nam Thăng Long - BX Yên Nghĩa", 27));
        arrayList.add(new Bus(R.drawable.bus, "Nhổn - Giáp bát", 32));
//        arrayList.add(new Bus(R.drawable.bus, "BX Mỹ Đình - Đông Anh", 46));

        adapter = new ListViewAdapter(this, arrayList);
        lvSearch.setAdapter(adapter);
    }

    private void initViews() {
        lvSearch = (ListView) findViewById(R.id.lv_search);

        ivBack = (ImageView) findViewById(R.id.iv_back);

        searchView = (SearchView) findViewById(R.id.input_search_view);

        ivBack.setOnClickListener(this);

        searchView.setOnQueryTextListener(this);

        lvSearch.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(this, "..." + arrayList.get(i).getId(), Toast.LENGTH_SHORT).show();
        putIdBus(arrayList.get(i).getId());  // put id của bus sang MainActivity
    }

    public void putIdBus(int id){
        Intent intent = new Intent(ActionSearch.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("BUS_ID", id);
        intent.putExtra("ID", bundle);
        startActivity(intent);
    }
}
