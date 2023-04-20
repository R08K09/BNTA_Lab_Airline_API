package com.example.airline_API.services;

import com.example.airline_API.models.Passenger;
import com.example.airline_API.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    // Display details of all passengers
    public List<Passenger> findAllPassengers(){
        return passengerRepository.findAll();
    }

    // Display details of a specific passenger
    public Passenger findPassengerById(Long id){
        return passengerRepository.findById(id).get();
    }

    // Add a new passenger
    public Passenger addNewPassenger(Passenger passenger){
        passengerRepository.save(passenger);
        return passenger;
    }



}
