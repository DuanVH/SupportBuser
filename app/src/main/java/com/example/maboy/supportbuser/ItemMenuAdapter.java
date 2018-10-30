package com.example.maboy.supportbuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maboy on 30/01/2018.
 */

public class ItemMenuAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ItemMenu> menus = new ArrayList<>();

    public ItemMenuAdapter(ArrayList<ItemMenu> menus, Context context) {
        this.menus = menus;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int i) {
        return menus.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewItemMenu viewItemMenu;
        if (view == null) {
            view = inflater.inflate(R.layout.item_menu, viewGroup, false);
            viewItemMenu = new ViewItemMenu();
            viewItemMenu.photo = (ImageView) view.findViewById(R.id.imv_photo);
            viewItemMenu.name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(viewItemMenu);
        } else {
            viewItemMenu = (ViewItemMenu) view.getTag();
        }
        ItemMenu menu = menus.get(i);
        viewItemMenu.photo.setImageResource(menu.getPhoto());
        viewItemMenu.name.setText(menu.getName());

        return view;
    }

    private class ViewItemMenu {
        ImageView photo;
        TextView name;
    }
}
