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
import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.payloads.ApiResponse;
import nrifintech.busMangementSystem.payloads.TicketDto;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.TicketRepo;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@Autowired
	BusService busService;

	@Autowired
	RouteService routeService;

	@Autowired
	UserService userService;
	
	@Autowired
	RouteInfoService routeInfoService;

	@Autowired
	TicketRepo ticketRepo;
	
	@Autowired
	BusMapRepo busMapRepo;


	// get
	@GetMapping("/ticket/get")
	public ResponseEntity<List<Ticket>> getAllTicket() {
		System.out.println("tkt get");
		return ResponseEntity.ok(this.ticketService.getTicket());
	}

	@GetMapping("/ticket/get/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("id") int ticketId) {
		return ResponseEntity.ok(this.ticketService.getTicket(ticketId));
	}

	
	@GetMapping("/ticket/getByUserId/{id}")
	public ResponseEntity<List<Ticket>> getTicketByUserId(@PathVariable("id") int userId) {
		System.out.println(userId);
		return ResponseEntity.ok(this.ticketService.getTicketByUserId(userId));
	}

	// post
	@PostMapping("/ticket/create")
	public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketDto ticketDto) {

		
		Ticket createdTicket = ticketService.createTicket(ticketDto);
//=======
//
//	// post
//	@PostMapping("/ticket/create")
//	public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketDto ticketDto) throws Exception {
//
//		// Get the route ID from the ticket and fetch the route, if not found give error
//		int routeId = ticketDto.getRouteId();
//		Route route = routeService.getRoute(routeId);
//		// Add the route to the ticket
//
//		// Get the user ID from the ticket and fetch the user, if not found give error
//		int userId = ticketDto.getUserId();
//		User user = userService.getUser(userId);
//		
//	
//	  LocalDateTime dateTime = LocalDateTime.now().with(LocalTime.MIDNIGHT);
//	    Date current_Date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
//	    for(Ticket t:this.ticketRepo.findByCreatedAtBefore(current_Date))
//	    {
//	    	if(t.getStatus().equals("waiting"))
//	    		t.setStatus("expired");
//	    	else if(t.getStatus().equals("confirmed"))
//	    		t.setStatus("availed");
//	    	this.ticketRepo.save(t);
//	    }
//	
//	    if(ticketRepo.findConfirmedTicketByUser(user).size()>=1)
//	    {
//	    	
//	    	//need to create custom exception for user creating multiple tickets
//	    	throw new UnauthorizedAction("Creating multiple ticket", user.getName());
//	    }
//	    
//        
//		// Add the user to the ticket
//
//		// Create the ticket
//		Ticket ticket = new Ticket();
//		ticket.setUser(user);
//
//		// Get the bus ID from the ticket and fetch the bus, if not found give error
//		int busId = ticketDto.getBusId();
//		Bus bus = busService.getBus(busId);
//		if (bus == null) {
//			throw new ResouceNotFound("Bus", "bus id", (long) (busId));
//		}
//		bus.setNumberOfSeats(bus.getNumberOfSeats() - 1);
//		ticket.setBus(bus);
//		// if bus is full logic
//		if (bus.getNumberOfSeats() < 0) {
//			ticket.setStatus("waiting");
//		} else {
//			ticket.setStatus("confirmed");
//		}
//		ticket.setCreatedAt(new Date());
//		Ticket createdTicket = ticketService.createTicket(ticket);
//>>>>>>> 4674ab9e20101f282f987640f6f2f04f021b0c90
		// Return the created ticket
		return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
	}

	// update
	@PostMapping("/ticket/update/{ticketId}")
	public ResponseEntity<Ticket> updateTicket(@Valid @RequestBody Ticket ticket,
			@PathVariable("ticketId") int ticketId) {
		Ticket updatedTicket = ticketService.updateTicket(ticket, ticketId);
		return ResponseEntity.ok(updatedTicket);
	}

	// delete
	@DeleteMapping("/ticket/delete/{ticketId}")
	public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") int ticketId) {
		ticketService.deleteTicket(ticketId);
		return new ResponseEntity(new ApiResponse("ticket deleted", true), HttpStatus.OK);
	}

	@PostMapping("/ticket/cancel/{ticketId}")
	public ResponseEntity<Ticket> cancelTicket(@PathVariable("ticketId") int ticketId) {
		Ticket ticket = ticketService.getTicket(ticketId);
		ticket.setStatus("cancelled");

		//Update route_info when ticket is cancelled.
		int bus_id = ticketRepo.findById(ticketId).orElseThrow(()-> new ResouceNotFound("Ticket","Id",ticketId)).getBus().getId();	
		int route_id = this.busMapRepo.findByBusId(bus_id).getRoute_id();
		this.routeInfoService.createRouteInfo(route_id, 1); //0 means create ticket and 1 means cancel ticket.
		
//		Bus bus = ticket.getBus();
//		if (bus.getNumberOfSeats() == 0) {
//			Ticket waitingTicket = ticketService.getMostRecentWaitingTicket(bus.getId());
//			if (waitingTicket != null) {
//				waitingTicket.setStatus("confirmed");
//			}
//		} else
//			bus.setNumberOfSeats(bus.getNumberOfSeats() + 1);

//		ticketService.updateTicket(ticket, ticketId);
		
		Bus bus = ticket.getBus();
		bus.setNumberOfSeats(bus.getNumberOfSeats() + 1);
		if (bus.getNumberOfSeats() == 0) {
			Ticket waitingTicket = ticketService.getMostRecentWaitingTicket(bus.getId());
			System.out.println(waitingTicket);
			if (waitingTicket != null) {
				waitingTicket.setStatus("confirmed");
				bus.setNumberOfSeats(bus.getNumberOfSeats() - 1);
				busService.updateBus(bus, bus.getId());
				ticketService.updateTicket(waitingTicket, waitingTicket.getId());
			}else {
				
				busService.updateBus(bus, bus.getId());
			}
		} else {
			bus.setNumberOfSeats(bus.getNumberOfSeats() + 1);
			busService.updateBus(bus, bus.getId());
		}
			

		return ResponseEntity.ok(ticket);
	}
}
