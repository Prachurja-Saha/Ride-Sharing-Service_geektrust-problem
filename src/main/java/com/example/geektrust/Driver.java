package com.example.geektrust;


public class Driver {

    private Double xCordinate;
    private Double yCordinate;
    private boolean available;

    public Driver(Double xCordinate, Double yCordinate, boolean status) {
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
        this.available = status;
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
