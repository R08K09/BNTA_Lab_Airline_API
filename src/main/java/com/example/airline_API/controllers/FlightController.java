package com.example.airline_API.controllers;

import com.example.airline_API.models.Flight;
import com.example.airline_API.models.Passenger;
import com.example.airline_API.repositories.FlightRepository;
import com.example.airline_API.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    // Display all available flights and Filter flights by destination
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlightsAndFilters(@RequestParam(required = false, name = "destination") String destination){
        // GET /flights?destination=Barcelona
        if(destination != null){
            return new ResponseEntity<>(flightService.findFlightsByDestination(destination), HttpStatus.OK);
        }
        // GET /flights
        return new ResponseEntity<>(flightService.findAllFlights(), HttpStatus.OK);
    }

    // Display details of a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Flight>> getFlight(@PathVariable Long id){
        return new ResponseEntity(flightService.findFlightById(id), HttpStatus.OK);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<List<Flight>> postFlight(@RequestBody Flight flight){
        flightService.addNewFlight(flight);
        return new ResponseEntity(flightService.findAllFlights(), HttpStatus.OK);
    }

    // Book a passenger on to a flight
    @PutMapping(value = "/{id}")
    public ResponseEntity<Flight> bookPassengerToFlight(@RequestBody Passenger passenger, @PathVariable Long id){
        Flight flight = flightService.findFlightById(id);
        Flight updatedflight = flightService.addPassengerToFlight(flight, passenger);
        return new ResponseEntity<>(updatedflight, HttpStatus.OK);
    }

    // Cancel a flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteFlight(@PathVariable Long id){
        flightService.cancelFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
