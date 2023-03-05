package nrifintech.busMangementSystem.Service.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.Service.interfaces.UserService;

import nrifintech.busMangementSystem.Service.interfaces.TicketService;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.ResouceNotFound;

import nrifintech.busMangementSystem.exception.UnauthorizedAction;

import nrifintech.busMangementSystem.payloads.TicketDto;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;
import nrifintech.busMangementSystem.repositories.RouteRepo;
import nrifintech.busMangementSystem.repositories.TicketRepo;
import nrifintech.busMangementSystem.repositories.UserRepo;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepo ticketRepo;
	@Autowired
	private BusRepo busRepo;
	@Autowired
	private RouteRepo routeRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RouteInfoRepo routeInfoRepo;
	@Autowired
	private BusMapRepo busMapRepo;

	@Autowired
	RouteService routeService;
	@Autowired
	UserService userService;
	@Autowired
	BusService busService;
	@Autowired
	RouteInfoService routeInfoService;

	@Override
	public Ticket createTicket(TicketDto ticketDto) {
//		int routeId = ticketDto.getRouteId();
//		Route route = routeService.getRoute(routeId);
//		// Add the route to the ticket
//
		
//		// Get the bus ID from the ticket and fetch the bus, if not found give error
		int busId = ticketDto.getBusId();
		Bus bus = busService.getBus(busId);
		
//		// Get the user ID from the ticket and fetch the user, if not found give error
		int userId = ticketDto.getUserId();
		User user = userService.getUser(userId);

		
		//change the status of ticket for past dates.
		LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.toLocalDate().atStartOfDay();
        Date current_date = Date.from(midnight.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("today morning date is "+current_date);
        List<Ticket> ticketsCreatedYesterDay = this.ticketRepo.findByCreatedAtBefore(current_date);
        
        List<Ticket> ticketsCreatedToday = this.ticketRepo.findByCreatedToday(current_date);
        System.out.println("size iss " + ticketsCreatedToday.size());
        // check if it is the first ticket of the day
        if(ticketsCreatedToday.size()==0)  	bus.resetNumberOfSeats();   
        
		for(Ticket t:ticketsCreatedYesterDay)
		{
			if(t.getStatus().equals("waiting"))
				t.setStatus("expired");
			else if(t.getStatus().equals("confirmed"))
				t.setStatus("availed");
			this.ticketRepo.save(t);
		}

//		
//		System.out.println(ticketRepo.findConfirmedTicketByUser(user.getId()).size());
//	
	    if(ticketRepo.findConfirmedTicketByUser(user.getId()).size()>=1)
	    {
	    	
	    	//need to create custom excepiton for user creating multiple tickets
	    	throw new UnauthorizedAction("Creating multiple ticket", user.getName());
	    }

		// change the status for all the tickets before current day

		// get all dates from ticket
		// fetched_date = format it to java date time format
		// curent_date =todays date in midnight time format.
		// if fetched_date < current_date :
		// update status of ticket:
		// waiting -> expired
		// confirmed -> availed.
		// cancelled -> cancelled.

//	    Date current_date = new Date();

		// Add the user to the ticket

		// Create the ticket
		Ticket ticket = new Ticket();
		ticket.setUser(user);
//
		int numberOfSeat = bus.getNumberOfSeats();
		bus.setNumberOfSeats(numberOfSeat-1);
		System.out.println(busId+ " now have seats " +numberOfSeat*2);
		bus = busService.updateBus(bus, busId);
		ticket.setBus(bus);
//		// if bus is full logic
		if (bus.getNumberOfSeats() < 0) {
			ticket.setStatus("waiting");
		} else {
			ticket.setStatus("confirmed");
			//Update route_info when ticket is created
			int bus_id = ticketDto.getBusId();		
			//get route from bus_map: relation bw bus_id and route_id
			int route_id = this.busMapRepo.findByBusId(bus_id).getRoute_id();
			this.routeInfoService.createRouteInfo(route_id, 0); //0 means create ticket and 1 means cancel ticket.
		}
		ticket.setCreatedAt(new Date());
		return ticketRepo.save(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket, int id) {
		Ticket updatedTicket = ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));

		updatedTicket.setBus(ticket.getBus());
//	    updatedTicket.setRoute(ticket.getRoute());
		updatedTicket.setUser(ticket.getUser());
		updatedTicket.setStatus(ticket.getStatus());
		updatedTicket.setCreatedAt(new Date());

		return ticketRepo.save(updatedTicket);
	}




	@Override
	public Ticket getTicket(int id) {
		return this.ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
	}

	@Override
	public List<Ticket> getTicket() {

	    return this.ticketRepo.findAll();
	}

	@Override
	public void deleteTicket(int id) {
		Ticket ticket = this.ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
		this.ticketRepo.delete(ticket);
	}

	public Ticket getMostRecentWaitingTicket(int busId) {
		// Create a list of all waiting tickets for the given bus ID, ordered by
		// creation time in descending order
		List<Ticket> waitingTickets = ticketRepo.findByBusIdAndStatusOrderByCreatedAtDesc(busId, "waiting");

		// Return the first waiting ticket in the list, or null if the list is empty
		return waitingTickets.isEmpty() ? null : waitingTickets.get(0);
	}

	@Override
	public List<Ticket> getTicketByUserId(int userId) {
		// TODO Auto-generated method stub
		return ticketRepo.findByUserId(userId);
	}

//	public Ticket getMostRecentWaitingTicket(int busId) {
//	    // Create a list of all waiting tickets for the given bus ID, ordered by creation time in descending order
//	    List<Ticket> waitingTickets = ticketRepo.findByBusIdAndStatusOrderByCreatedAtDesc(busId, "waiting");
//
//	    // Return the first waiting ticket in the list, or null if the list is empty
//	    return waitingTickets.isEmpty() ? null : waitingTickets.get(0);
//	}

}
