package nrifintech.busMangementSystem.Service.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface BusMapService {
	void addBusMap(int route_id,int bus_id);
	void deleteByRouteId(int id);
	void deleteByBusId(int id);
	int  getBusIdByRouteId(int id);
	int getRouteIdByBusId(int id);
	 void updateBusByRoute(int route_id,int bus_id);
}
