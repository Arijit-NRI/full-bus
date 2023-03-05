package nrifintech.busMangementSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.interfaces.DestinationService;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.payloads.ApiResponse;

@RestController
@RequestMapping("/api/v1/")
public class DestinationController {
    @Autowired
    DestinationService destinationService;
    
    //get
    @GetMapping("/destination/get")
    public ResponseEntity<List<Destination>> getAllDestination(){
        return ResponseEntity.ok(this.destinationService.getDestination());
    }
    
    @GetMapping("/destination/get/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable("id") int destinationId){
        return ResponseEntity.ok(this.destinationService.getDestination(destinationId));
    }
    
    //post
    @PostMapping("/destination/create")
    public ResponseEntity<Destination> createDestination(@Valid @RequestBody Destination destination){
        Destination createdDestination = destinationService.createDestination(destination);
        return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
    }
    
    //update
    @PostMapping("/destination/update/{destinationId}")
    public ResponseEntity<Destination> updateDestination(@Valid @RequestBody Destination destination, @PathVariable("destinationId") int destinationId){
        Destination updatedDestination = destinationService.updateDestination(destination, destinationId);
        return ResponseEntity.ok(updatedDestination);
    }
    
    //delete
    @DeleteMapping("/destination/delete/{destinationId}")
    public ResponseEntity<?> deleteDestination(@PathVariable("destinationId") int destinationId){
        destinationService.deleteDestination(destinationId);
        return new ResponseEntity(new ApiResponse("destination deleted", true), HttpStatus.OK);
    }

    
    //custom
    @GetMapping("/destination/getbyname/{name}")
    public ResponseEntity<?> getDestinationByName(@PathVariable("name") String name){
    	System.out.println(name);
    	return ResponseEntity.ok( destinationService.getDestinationByName(name));
    }

}
