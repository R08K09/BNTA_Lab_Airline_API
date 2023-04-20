package com.example.airline_API.services;

import com.example.airline_API.models.Flight;
import com.example.airline_API.models.Passenger;
import com.example.airline_API.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    // Add details of a new flight
    public Flight addNewFlight(Flight flight){
        flightRepository.save(flight);
        return flight;
    }

    // Display all available flights
    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    // Display details of a specific flight
    public Flight findFlightById(Long id){
        return flightRepository.findById(id).get();
    }

    // Book a passenger on to a flight
    public Flight addPassengerToFlight(Flight flight, Passenger passenger){
        List<Passenger> passengers = flight.getPassengers();
        passengers.add(passenger);
        flight.setPassengers(passengers);
        return flightRepository.save(flight);
    }

    // Cancel a flight
    public void cancelFlight(Long id){
        flightRepository.deleteById(id);
    }

    // Filter flights by destination
    public List<Flight> findFlightsByDestination(String destination){
        List<Flight> allFlights = flightRepository.findAll();
        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : allFlights) {
            if (Objects.equals(flight.getDestination(), destination)){
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }
}
