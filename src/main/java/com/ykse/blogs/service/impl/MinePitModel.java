package com.ykse.blogs.service.impl;

/**
 * Created by etomhua on 9/11/2017.
 */
public class MinePitModel {

    private int lat;
    private int lng;
    private boolean isBomb = false;
    private int bombsAround;

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }

    @Override
    public String toString() {
        return isBomb()?"炸":"口";
    }
}
