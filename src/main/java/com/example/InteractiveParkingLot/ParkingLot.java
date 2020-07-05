package com.example.InteractiveParkingLot;


import java.util.*;


public class ParkingLot {
    int ParkingSlotsCount = 0;

    ArrayList<Integer> availableSlotList;
    Map<String, CarModel> parkingMap;
    Map<String, String> slotRegNoMap;
    Map<String, ArrayList<String>> colourRegNoMap;


    public void createParkingLot(String lotCount) {
        try {
            this.ParkingSlotsCount = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.ParkingSlotsCount; i++) {
            availableSlotList.add(i);
        }
        this.parkingMap = new HashMap<String, CarModel>();
        this.slotRegNoMap = new HashMap<String, String>();
        this.colourRegNoMap = new HashMap<String, ArrayList<String>>();
        System.out.println("Created a parking lot with " + lotCount + " slots");
    }
    public void park(String regNo, String color) {
        if (this.ParkingSlotsCount == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.parkingMap.size() == this.ParkingSlotsCount) {
            System.out.println("Sorry, parking lot is full");
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            CarModel car = new CarModel(regNo, color);
            this.parkingMap.put(slot, car);
            this.slotRegNoMap.put(regNo, slot);
            ArrayList<String> regNoList;
            if(this.colourRegNoMap.containsKey(color)) {
                regNoList = this.colourRegNoMap.get(color);
                this.colourRegNoMap.remove(color);
            } else {
                regNoList = new ArrayList<String>();
            }
            regNoList.add(regNo);
            this.colourRegNoMap.put(color, regNoList);
            System.out.println("Allocated slot number: " + slot);
            availableSlotList.remove(0);
        }
    }
    public void leave(String slotNo) {
        if (this.ParkingSlotsCount == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.parkingMap.size() > 0) {
            CarModel carToLeave = this.parkingMap.get(slotNo);
            if (carToLeave != null) {
                this.parkingMap.remove(slotNo);
                this.slotRegNoMap.remove(carToLeave.getRegNo());
                ArrayList<String> regNoList = this.colourRegNoMap.get(carToLeave.getColor());
                if (regNoList.contains(carToLeave.getRegNo())) {
                    regNoList.remove(carToLeave.getRegNo());
                }
                this.availableSlotList.add(Integer.parseInt(slotNo));
                System.out.println("Slot number " + slotNo + " is free");
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }
    public void status() {
        if (this.ParkingSlotsCount == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.parkingMap.size() > 0) {
            System.out.println("Slot No.\tRegistration No.\tColor");
            CarModel car;
            for (int i = 1; i <= this.ParkingSlotsCount; i++) {
                String key = Integer.toString(i);
                if (this.parkingMap.containsKey(key)) {
                    car = this.parkingMap.get(key);
                    System.out.println(i + "\t" + car.getRegNo() + "\t" + car.getColor());
                }
            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }
    public void getRegistrationNumbersFromColor(String color) {
        if (this.ParkingSlotsCount == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.colourRegNoMap.containsKey(color)) {
            ArrayList<String> regNoList = this.colourRegNoMap.get(color);
            regNoList.forEach(System.out::println);
        } else {
            System.out.println("Not found");
        }
    }
    public void getSlotNumbersFromColor(String color) {
        if (this.ParkingSlotsCount == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.colourRegNoMap.containsKey(color)) {
            ArrayList<String> regNoList = this.colourRegNoMap.get(color);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            for (int i=0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(this.slotRegNoMap.get(regNoList.get(i))));
            }
            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
        } else {
            System.out.println("Not found");
        }
    }
    public void getSlotNumberFromRegNo(String regNo) {
        if (this.ParkingSlotsCount == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.slotRegNoMap.containsKey(regNo)) {
            System.out.println(this.slotRegNoMap.get(regNo));
        } else {
            System.out.println("Not found");
        }
    }
}
