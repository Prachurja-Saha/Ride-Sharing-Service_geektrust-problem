package com.example.geektrust;

public class Rider {

    private Double xCordinate;
    private Double yCordinate;
    private boolean available;

    public Rider(Double xCordinate, Double yCordinate, boolean available) {
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
        this.available = available;
    }


    public Double getxCordinate() {
        return xCordinate;
    }

    public void setxCordinate(Double xCordinate) {
        this.xCordinate = xCordinate;
    }

    public Double getyCordinate() {
        return yCordinate;
    }

    public void setyCordinate(Double yCordinate) {
        this.yCordinate = yCordinate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
