package com.example.airline_API.controllers;

import com.example.airline_API.models.Passenger;
import com.example.airline_API.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    // Display details of all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        return new ResponseEntity(passengerService.findAllPassengers(), HttpStatus.OK);
    }

    // Display details of a specific passenger
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Passenger>> getPassenger(@PathVariable Long id){
        return new ResponseEntity(passengerService.findPassengerById(id), HttpStatus.OK);
    }

    // Add a new passenger
    @PostMapping
    public ResponseEntity<List<Passenger>> postPassenger(@RequestBody Passenger passenger){
        passengerService.addNewPassenger(passenger);
        return new ResponseEntity(passengerService.findAllPassengers(), HttpStatus.OK);
    }

}
