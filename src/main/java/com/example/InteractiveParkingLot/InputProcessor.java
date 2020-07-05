package com.example.InteractiveParkingLot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InputProcessor {
ParkingLot parkingLot = new ParkingLot();
    public void validateInput(String inputString) {
        Map<String, Method> commandsMap = new HashMap<String, Method>();
        try {
            commandsMap.put("create_parking_lot", ParkingLot.class.getMethod("createParkingLot", String.class));
            commandsMap.put("park", ParkingLot.class.getMethod("park", String.class, String.class));
            commandsMap.put("leave", ParkingLot.class.getMethod("leave", String.class));
            commandsMap.put("status", ParkingLot.class.getMethod("status"));
            commandsMap.put("registration_numbers_for_cars_with_colour", ParkingLot.class.getMethod("getRegistrationNumbersFromColor", String.class));
            commandsMap.put("slot_numbers_for_cars_with_colour", ParkingLot.class.getMethod("getSlotNumbersFromColor", String.class));
            commandsMap.put("slot_number_for_registration_number", ParkingLot.class.getMethod("getSlotNumberFromRegNo", String.class));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        String[] inputs = inputString.split(" ");
        switch (inputs.length) {
            case 1:
                try {
                    Method method = commandsMap.get(inputString);
                    if (method != null) {
                        method.invoke(parkingLot);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    Method method =commandsMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingLot, inputs[1]);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    Method method = commandsMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingLot, inputs[1], inputs[2]);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}
