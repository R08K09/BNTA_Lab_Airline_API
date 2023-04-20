package com.example.airline_API.components;

import com.example.airline_API.models.Flight;
import com.example.airline_API.models.Passenger;
import com.example.airline_API.repositories.FlightRepository;
import com.example.airline_API.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Flight 1
        Flight flight1 = new Flight("Barcelona", 200, LocalDate.of(2023, 5, 18), LocalTime.of(12, 30));
        flightRepository.save(flight1);

        // Flight 2
        Flight flight2 = new Flight("New York", 260, LocalDate.of(2023, 7, 24), LocalTime.of(16, 22));
        flightRepository.save(flight2);

        // Passenger 1
        Passenger passenger1 = new Passenger("Anna", "02075786268");
        passengerRepository.save(passenger1);

        // Passenger 2
        Passenger passenger2 = new Passenger("Zsolt", "02078411579");
        passengerRepository.save(passenger2);

        // Passenger 3
        Passenger passenger3 = new Passenger("Colin", "02073659961");
        passengerRepository.save(passenger3);

        // Passenger 2
        Passenger passenger4 = new Passenger("Ed", "02074226793");
        passengerRepository.save(passenger4);


        // Many-to-Many relationships - adding passengers to flights
        flight1.addPassenger(passenger1);
        flight1.addPassenger(passenger2);
        flight1.addPassenger(passenger4);

        flight2.addPassenger(passenger1);
        flight2.addPassenger(passenger3);
        flight2.addPassenger(passenger4);

        flightRepository.save(flight1);
        flightRepository.save(flight2);

    }
}
