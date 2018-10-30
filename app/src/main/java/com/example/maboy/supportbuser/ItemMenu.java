package com.example.maboy.supportbuser;

/**
 * Created by maboy on 30/01/2018.
 */

public class ItemMenu {

    private String name;
    private int photo;

    public ItemMenu(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
