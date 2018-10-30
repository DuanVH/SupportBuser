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

public class ListDiemBanVeAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<DiemBanVe> veList = null;
    private ArrayList<DiemBanVe> veArrayList;


    public ListDiemBanVeAdapter(Context mContext, List<DiemBanVe> veList) {
        this.mContext = mContext;
        this.veList = veList;
        inflater = LayoutInflater.from(mContext);
        this.veArrayList = new ArrayList<DiemBanVe>();
        this.veArrayList.addAll(veList);
    }

    public class ViewHolder {
        ImageView photo;
        TextView name;
    }

    @Override
    public int getCount() {
        return veList.size();
    }

    @Override
    public Object getItem(int i) {
        return veList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ListDiemBanVeAdapter.ViewHolder holder;

        if (view == null) {
            holder = new ListDiemBanVeAdapter.ViewHolder();
            view = inflater.inflate(R.layout.listview_item , null);
            holder.photo = (ImageView) view.findViewById(R.id.iv_photo);
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ListDiemBanVeAdapter.ViewHolder) view.getTag();
        }

        DiemBanVe ve = veList.get(i);
        String text = ve.getName().toString();
        holder.photo.setImageResource(ve.getPhoto());
        holder.name.setText(text);

        return view;
    }

    public void filter(String text) {
        text = text.toLowerCase(Locale.getDefault());
        veList.clear();
        if (text.length() == 0) {
            veList.addAll(veArrayList);
        } else {
            for (DiemBanVe ve : veArrayList) {
                String s =  ve.getName().toLowerCase();  // Vừa thêm
//                if (bt.getName().toLowerCase(Locale.getDefault()).contains(text)) {
                if (s.toLowerCase(Locale.getDefault()).contains(text)) {
                    veList.add(ve);
                }
            }
        }
        notifyDataSetChanged();
    }
}
