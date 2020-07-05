package com.example.InteractiveParkingLot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        InputProcessor inputProcessor = new InputProcessor();
            switch (args.length) {
                case 0:
                    System.out.println("Please enter 'exit' to quit");
                    System.out.println("Enter Input here");
                    while(true) {
                        try {
                            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
                            String inputString = bufferReader.readLine();
                            if (inputString.equalsIgnoreCase("exit")) {
                                break;
                            } else if (!inputString.isEmpty()) {
                                inputProcessor.validateInput(inputString.trim());
                            }
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }