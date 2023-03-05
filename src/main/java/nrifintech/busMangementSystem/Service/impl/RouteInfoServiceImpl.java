package nrifintech.busMangementSystem.Service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;

@Service
public class RouteInfoServiceImpl implements RouteInfoService {
	
	@Autowired
	RouteInfoRepo routeInfoRepo;
	
	@Autowired
	BusMapRepo busMapRepo;
	
	@Autowired
	BusRepo busRepo;

	@Override
	public void createRouteInfo(int routeId, int action) {
		//check if this routeId data is already in routeInfo table
		//if present, then update info, else initializwe it
		
		LocalDate today = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
	    String currentDate = today.format(formatter);
	    RouteInfo routeInfo = this.routeInfoRepo.getRouteByPresentDate(routeId, currentDate);
	    System.out.println("Testing:      "+routeId);
	    System.out.println(routeInfo);
		if(routeInfo !=null)
		{

			//when ticket is created, update total_bookings
			if(action==0)
			{
				routeInfo.setTotal_bookings(routeInfo.getTotal_bookings()+1);
			}
			else //when its cancelled, update total_cancellations
			{
				routeInfo.setTotal_cancellations(routeInfo.getTotal_cancellations()+1);
			}
			
		}
		else
		{
			//save entire data for current_date and routeId
			//get toal seats from bus and route relation.
			//get all the bus running on this route_id using bus_map table.
			//then get total_seats of all the bus from bus table.
			int total_seats = 0;
			int busId = busMapRepo.findByRouteId(routeId).getBus_id();
			Bus bus = busRepo.findById(busId).orElseThrow(()-> new ResouceNotFound("Bus", "BusId", routeId));
			total_seats = bus.getTotalNumberOfseats();
			
			RouteInfo currentDate_RouteInfo = new RouteInfo();
			currentDate_RouteInfo.setDate(currentDate);
			currentDate_RouteInfo.setRoute_id(routeId);
			currentDate_RouteInfo.setTotal_bookings(1);
			currentDate_RouteInfo.setTotal_cancellations(0);
			currentDate_RouteInfo.setTotal_seats(total_seats);
			this.routeInfoRepo.save(currentDate_RouteInfo);
			
		}
		
	}

}
