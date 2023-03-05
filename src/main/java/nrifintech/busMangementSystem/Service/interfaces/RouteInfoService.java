package nrifintech.busMangementSystem.Service.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;

@Service
public interface RouteInfoService {
	void createRouteInfo(int routeId, int action);
}
