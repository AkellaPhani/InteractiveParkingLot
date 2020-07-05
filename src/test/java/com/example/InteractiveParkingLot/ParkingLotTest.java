package com.example.InteractiveParkingLot;

import com.example.InteractiveParkingLot.ParkingLot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ParkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    @Test
    public void createParkingLot() throws Exception {
        parkingLot.createParkingLot("6");
        assertEquals(6, parkingLot.ParkingSlotsCount);
        assertEquals(6, parkingLot.availableSlotList.size());
        Assert.assertTrue("createdaparkinglotwith6slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
    }

    @Test
    public void park() throws Exception {
        parkingLot.park("KA-01-HH-1234", "White");
        assertEquals("Sorry,parkinglotisnotcreated" , outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.park("KA-01-BB-0001", "Black");
        assertEquals(3, parkingLot.availableSlotList.size());
    }

    @Test
    public void leave() throws Exception {
        parkingLot.leave("2");
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.park("KA-01-BB-0001", "Black");
        parkingLot.park("KA-01-HH-7777", "Red");
        parkingLot.park("KA-01-HH-2701", "Blue");
        parkingLot.park("KA-01-HH-3141", "Black");
        parkingLot.leave("4");
        assertEquals("Sorry,parkinglotisnotcreated" +
                "\r\n" +
                "Createdaparkinglotwith6slots" +
                "\r\n" +
                "Allocatedslotnumber:1" +
                "\r\n" +
                "Allocatedslotnumber:2" +
                "\r\n" +
                "Allocatedslotnumber:3" +
                "\r\n" +
                "Allocatedslotnumber:4" +
                "\r\n" +
                "Allocatedslotnumber:5" +
                "\r\n" +
                "Allocatedslotnumber:6" +
                "\r\n" +
                "Slotnumber4isfree" , outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void status() throws Exception {
        parkingLot.status();
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.park("KA-01-BB-0001", "Black");
        parkingLot.park("KA-01-HH-2701", "Blue");
        parkingLot.park("KA-01-HH-3141", "Black");

        parkingLot.status();
        assertEquals("Sorry,parkinglotisnotcreated" +
                "\r\n" +
                "Createdaparkinglotwith6slots" +
                "\r\n" +
                "Allocatedslotnumber:1" +
                "\r\n" +
                "Allocatedslotnumber:2" +
                "\r\n" +
                "Allocatedslotnumber:3" +
                "\r\n" +
                "Allocatedslotnumber:4" +
                "\r\n" +
                "Allocatedslotnumber:5" +
                "\r\n" +
                "SlotNo.\tRegistrationNo.\tColor\r\n" +
                "1\tKA-01-HH-1234\tWhite\r\n" +
                "2\tKA-01-HH-9999\tWhite\r\n" +
                "3\tKA-01-BB-0001\tBlack\r\n" +
                "4\tKA-01-HH-2701\tBlue\r\n" +
                "5\tKA-01-HH-3141\tBlack", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void getRegistrationNumbersFromColor() throws Exception {
        parkingLot.getRegistrationNumbersFromColor("White");
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.park("KA-01-BB-0001", "Black");
        parkingLot.park("KA-01-P-333", "White");
        parkingLot.getRegistrationNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated" +
                "\r\n" +
                "Createdaparkinglotwith6slots" +
                "\r\n" +
                "Allocatedslotnumber:1" +
                "\r\n" +
                "Allocatedslotnumber:2" +
                "\r\n" +
                "Allocatedslotnumber:3" +
                "\r\n" +
                "Allocatedslotnumber:4" +
                "\r\n" +
                "KA-01-HH-1234"+"\r\n"+"KA-01-HH-9999"+"\r\n"+"KA-01-P-333", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void getSlotNumbersFromColor() throws Exception {
        parkingLot.getSlotNumbersFromColor("White");
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.park("KA-01-BB-0001", "Black");
        parkingLot.park("KA-01-P-333", "White");
        parkingLot.getSlotNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated" +
                "\r\n" +
                "Createdaparkinglotwith6slots" +
                "\r\n" +
                "Allocatedslotnumber:1" +
                "\r\n" +
                "Allocatedslotnumber:2" +
                "\r\n"+
                "Allocatedslotnumber:3" +
                "\r\n"+
                "Allocatedslotnumber:4" +
                "\r\n"+
                "1,2,4", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void getSlotNumberFromRegNo() throws Exception {
        parkingLot.getSlotNumberFromRegNo("KA-01-HH-1234");
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.park("KA-01-P-333", "White");
        parkingLot.getSlotNumberFromRegNo("KA-01-HH-1234");
        assertEquals("Sorry,parkinglotisnotcreated" +
                "\r\n" +
                "Createdaparkinglotwith6slots" +
                "\r\n" +
                "Allocatedslotnumber:1" +
                "\r\n" +
                "Allocatedslotnumber:2" + "\r\n"+

                "Allocatedslotnumber:3" +
                "\r\n" +
                "1", outContent.toString().trim().replace(" ", ""));
        parkingLot.getSlotNumberFromRegNo("MH-04-AY-1111");
        assertEquals("Sorry,parkinglotisnotcreated" +
                "\r\n" +
                "Createdaparkinglotwith6slots" +
                "\r\n" +
                "Allocatedslotnumber:1" +
                "\r\n" +
                "Allocatedslotnumber:2" + "\r\n"+

                "Allocatedslotnumber:3" +
                "\r\n" +
                "1"+"\r\n"+"Notfound", outContent.toString().trim().replace(" ", ""));
    }

}