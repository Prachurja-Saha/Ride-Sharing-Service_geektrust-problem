package com.example.geektrust;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Path filePath = Path.of("src/main/java/com/example/geektrust/text.txt");
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {

            Map<String, Driver> driversMappedWithKey = new HashMap<>();
            Map<String, Rider> ridersMappedWithKey = new HashMap<>();

            // Key -> RiderID and List of Driver
            Map<String, List<String>> availableDriverRiderMapping= new HashMap<>();

            Map<String, String[]> rideIdMappedWithRiderId = new HashMap<>();

            // Ride ID and BILL
            Map<String, ValueHolder> billMappedWithKey = new HashMap<>();

            String line;

            while ((line = reader.readLine()) != null) {

                Main main = new Main();
                String[] command = line.split(" ");

                switch(command[0]) {

                    case "ADD_DRIVER" -> driversMappedWithKey.put(command[1], new Driver(Double.parseDouble(command[2]),
                            Double.parseDouble(command[3]), true));

                    case "ADD_RIDER" -> ridersMappedWithKey.put(command[1], new Rider(Double.parseDouble(command[2]),
                            Double.parseDouble(command[3]), true));

                    case "MATCH" -> {
                        if (!ridersMappedWithKey.get(command[1]).isAvailable()) {
                            System.out.println("RIDERS_NOT_AVAILABLE");

                        } else {
                            availableDriverRiderMapping.put(
                                    command[1], main.getMatchedDrivers(driversMappedWithKey, ridersMappedWithKey.get(command[1])));
                            String availableDrivers = String.join(" ", availableDriverRiderMapping.get(command[1]));
                            System.out.println("DRIVERS_MATCHED " + availableDrivers);
                        }
                    }

                    case "START_RIDE" -> {

                        // TODO: need to check index out of bound condition
                        String driverId = availableDriverRiderMapping.get(command[3]).get(Integer.parseInt(command[2])-1);
                        Driver driver = driversMappedWithKey.get(driverId);
                        driver.setAvailable(false);

                        driversMappedWithKey.put(driverId, driver);

                        availableDriverRiderMapping.remove(command[3]);
                        rideIdMappedWithRiderId.put(command[1], new String[]{command[3], driverId});
                        System.out.println("RIDE_STARTED " + command[1]);
                    }

                    case "STOP_RIDE" -> {
                        Rider rider = ridersMappedWithKey.get(rideIdMappedWithRiderId.get(command[1])[0]);
                        Double destinationX = Double.parseDouble(command[2]);
                        Double destinationY = Double.parseDouble(command[3]);
                        int timeInMinute = Integer.parseInt(command[4]);

                        double bill = 50 + 6.5 * main.getDifferenceBetweenTwoPoints(rider.getxCordinate(),
                                rider.getyCordinate(), destinationX, destinationY) + 2 * timeInMinute;

                        double serviceTax = 20 * bill/100;

                        Double finalBill = bill + serviceTax;


                        billMappedWithKey.put(command[1], new ValueHolder(rideIdMappedWithRiderId.get(command[1])[1], finalBill));
                        System.out.println("RIDE_STOPPED " + command[1]);


                    }

                    default -> System.out.println("BILL " + command[1] +" "+billMappedWithKey.get(command[1]).getDriverId()
                            +" "+billMappedWithKey.get(command[1]).getBill());

                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns max 5 Drivers in ascending order near to radius 5 km
     */
    public List<String> getMatchedDrivers(Map<String, Driver> driversMappedWithKey, Rider rider) {

        return driversMappedWithKey.entrySet().stream()
                .filter(oldSet -> oldSet.getValue().isAvailable())
//                .sorted(Map.Entry.comparingByKey())
                .map(oldSet-> Map.entry(oldSet.getKey(), getDifferenceBetweenTwoPoints(oldSet.getValue().getxCordinate(),
                        oldSet.getValue().getyCordinate(), rider.getxCordinate(), rider.getyCordinate())))
                .filter(newSet -> newSet.getValue()<=5)
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .limit(5)
                .toList();
    }

    public Double getDifferenceBetweenTwoPoints(Double x1, Double y1, Double x2, Double y2){

        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }
}

        /*
        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
        */