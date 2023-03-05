package nrifintech.busMangementSystem.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import nrifintech.busMangementSystem.Service.interfaces.RouteMapService;
import nrifintech.busMangementSystem.repositories.RouteMapRepo;

public class RouteMapServiceImpl implements RouteMapService {
	@Autowired
	private RouteMapRepo routeMapRepo;

	@Override
	public void addRouteMap(int route_id, int destination_id, int index, String time) {
	}

	

	
}
