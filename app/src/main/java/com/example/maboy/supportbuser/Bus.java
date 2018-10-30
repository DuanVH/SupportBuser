package com.example.maboy.supportbuser;

/**
 * Created by maboy on 13/03/2018.
 */

public class Bus {

    private int photo;
    private String name;  // Tên xe
    private int id;  // số xe

    public Bus(String name) {
        this.name = name;
    }

    public Bus(int id) {
        this.id = id;
    }

    public Bus(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Bus(int photo, String name, int id) {
        this.photo = photo;
        this.name = name;
        this.id = id;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public int getPhoto() {
        return photo;
    }
}
