package com.example.maboy.supportbuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by maboy on 13/03/2018.
 */

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Bus> busList = null;
    private ArrayList<Bus> arrayList;

    private List<DiemBanVe> veList = null;
    private ArrayList<DiemBanVe> veArrayList;

    // List Bus
    public ListViewAdapter(Context mContext, List<Bus> busList) {
        this.mContext = mContext;
        this.busList = busList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Bus>();
        this.arrayList.addAll(busList);
    }

    public class ViewHolder {
        ImageView photo;
        TextView name;
    }

    @Override
    public int getCount() {
        return busList.size();
    }

    @Override
    public Object getItem(int i) {
        return busList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item , null);
            holder.photo = (ImageView) view.findViewById(R.id.iv_photo);
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Bus bus = busList.get(i);
        String text =  "(Bus " + bus.getId() + ") " + bus.getName();
        holder.photo.setImageResource(bus.getPhoto());
        holder.name.setText(text);

        return view;
    }

    // Filter class
    public void filter(String text) {
        text = text.toLowerCase(Locale.getDefault());
        busList.clear();
        if (text.length() == 0) {
            busList.addAll(arrayList);
        } else {
            for (Bus bt : arrayList) {
                String s =  "(Bus " + bt.getId() + ") " + bt.getName();  // Vừa thêm
//                if (bt.getName().toLowerCase(Locale.getDefault()).contains(text)) {
                if (s.toLowerCase(Locale.getDefault()).contains(text)) {
                    busList.add(bt);
                }
            }
        }
        notifyDataSetChanged();
    }
}
