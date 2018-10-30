package com.example.maboy.supportbuser;

import java.util.ArrayList;

public class BusStop {

    private String name;  // Tên điểm dừng
    private ArrayList<Bus> buses;  // Các bus đi qua
    private double lat;
    private double lng;
    private int state = 1;  // = 1 thì hiển thị bus stop; = 0 thì không hiển thị

    public BusStop(String name, ArrayList<Bus> buses, double lat, double lng, int state) {
        this.name = name;
        this.buses = buses;
        this.lat = lat;
        this.lng = lng;
        this.state = state;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public int getState() {
        return state;
    }
}
