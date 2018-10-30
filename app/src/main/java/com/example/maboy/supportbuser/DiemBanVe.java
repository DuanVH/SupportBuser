package com.example.maboy.supportbuser;

public class DiemBanVe {

    private int photo;
    private String name;
    private String busId;
    private double lat;
    private double lng;

    public DiemBanVe(int photo, String name, String busId, double lat, double lng) {
        this.photo = photo;
        this.name = name;
        this.busId = busId;
        this.lat = lat;
        this.lng = lng;
    }

    public DiemBanVe(int photo, String name, double lat, double lng) {
        this.photo = photo;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
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

    public int getPhoto() {
        return photo;
    }

    public String getBusId() {
        return busId;
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
}
