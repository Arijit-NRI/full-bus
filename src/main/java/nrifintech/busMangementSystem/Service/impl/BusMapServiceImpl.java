package nrifintech.busMangementSystem.Service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.BusMapService;
import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.RouteMap;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.RouteMapRepo;
@Service
public class BusMapServiceImpl implements BusMapService{
	@Autowired
	private BusMapRepo busMapRepo;
	
	@Override
	public void addBusMap(int route_id,int bus_id) {
		BusMap b = new BusMap();
		b.setBus_id(bus_id);
		b.setRoute_id(route_id);
		busMapRepo.save(b);
	}
	@Override
	public void updateBusByRoute(int route_id,int bus_id) {
		BusMap b = busMapRepo.findByRouteId(route_id);
		b.setBus_id(bus_id);
		busMapRepo.save(b);
	}
	@Override
	public void deleteByRouteId(int id) {
		busMapRepo.deleteByRouteId(id);
	}
	@Override
	public void deleteByBusId(int id) {
		busMapRepo.deleteByBusId(id);
	}
	@Override
	public int  getBusIdByRouteId(int id) {
		BusMap bm = busMapRepo.findByBusId(id);
		return bm.getBus_id();
	}
	@Override
	public int getRouteIdByBusId(int id) {
		BusMap bm = busMapRepo.findByRouteId(id);
		return bm.getRoute_id();
	}
}
