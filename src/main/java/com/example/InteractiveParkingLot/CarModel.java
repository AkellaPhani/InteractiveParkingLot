package com.example.InteractiveParkingLot;

public class CarModel {

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    String regNo;
        String color;
        public CarModel(String regNo, String color) {
            this.regNo = regNo;
            this.color = color;
        }
    }

