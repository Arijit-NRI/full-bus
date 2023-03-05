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

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.payloads.ApiResponse;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
	@Autowired
	BusService busService;
	 
	//get
	@GetMapping("/get")
	public ResponseEntity<List<Bus>> getAllbus(){
		return ResponseEntity.ok(this.busService.getBus());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Bus> getBusById(@PathVariable("id") int uid){
		return ResponseEntity.ok(this.busService.getBus(uid));
	}
	//post
	
	@PostMapping("/create")
	ResponseEntity<Bus> createBus(@Valid @RequestBody Bus bus){
		Bus createdBus = busService.createBus(bus);
		return new ResponseEntity<>(createdBus, HttpStatus.CREATED);
	}
	//update
	@PostMapping("/update/{busId}")
	ResponseEntity<Bus> createBus(@Valid @RequestBody Bus bus, @PathVariable("busId") int busId){
		Bus updatedBus = busService.updateBus(bus, busId);
		return ResponseEntity.ok(updatedBus);
	}
	//delete
	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<?> deleteBus(@PathVariable("busId") int busId){
		busService.deleteBus(busId);
		return new ResponseEntity(new ApiResponse("bus deleted", true), HttpStatus.OK);
	}
}
