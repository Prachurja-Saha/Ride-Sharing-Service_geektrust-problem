package com.example.geektrust;

public class ValueHolder {

    private String DriverId;
    private Double bill;

    public ValueHolder(String driverId, Double bill) {
        DriverId = driverId;
        this.bill = bill;
    }

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }
}
