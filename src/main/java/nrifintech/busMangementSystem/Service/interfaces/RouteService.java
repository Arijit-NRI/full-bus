package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Route;
@Service
public interface RouteService {
	Route getRoute(int id);
	Route createRoute(List<String> destinations,int bus_id);
	List<Route> getRoutesBySourceAndDestination(int source, int destination);
	List<Destination> getRouteDestinations(int routeId);
	Route updateRoute(List<String> destinations, int id, int bus_id);
	void deleteRoute(int id);
	List<Route> getRoute();
	
}

