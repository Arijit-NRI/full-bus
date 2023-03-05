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

import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.payloads.ApiResponse;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
	@Autowired
	RouteService routeService;
	 
	//get Not required currently
	@GetMapping("/get")
	public ResponseEntity<List<Route>> getAllroute(){
		return ResponseEntity.ok(this.routeService.getRoute());
	}
	
	//Not required currently
	@GetMapping("/get/{id}")
	public ResponseEntity<Route> getRouteById(@PathVariable("id") int uid){
		return ResponseEntity.ok(this.routeService.getRoute(uid));
	}
	//post
	@PostMapping("/create/{bus_id}")
	ResponseEntity<Route> createRoute(@RequestBody List<String> destinations,@PathVariable int bus_id){
		Route createdRoute = routeService.createRoute(destinations,bus_id);
		return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
	}
	@GetMapping("/getBySrcDest/{source}/{destination}")
	public ResponseEntity<List<Route>> getRoutesBySourceAndDestination(@PathVariable int source, @PathVariable int destination) {
	    List<Route> queryRoutes = routeService.getRoutesBySourceAndDestination(source, destination);
	    return ResponseEntity.ok(queryRoutes);
	}
	//update
	@PostMapping("/update/{routeId}")
	ResponseEntity<Route> updateRoute(@RequestBody List<String> destinations, @RequestBody int busId, @PathVariable("routeId") int routeId){
		Route updatedRoute = routeService.updateRoute(destinations, routeId,busId);
		return ResponseEntity.ok(updatedRoute);
	}
	//delete
	@DeleteMapping("/delete/{routeId}")
	public ResponseEntity<?> deleteRoute(@PathVariable("routeId") int routeId){
		routeService.deleteRoute(routeId);
		return new ResponseEntity(new ApiResponse("route deleted", true), HttpStatus.OK);
	}
	
	//getDestinationsbyId
	@GetMapping("/getDestinations/{routeId}")
	public ResponseEntity<?> getRouteDestinations(@PathVariable("routeId") int routeId){
		List<Destination>destinations = routeService.getRouteDestinations(routeId);
		return ResponseEntity.ok(destinations);
	}

}
