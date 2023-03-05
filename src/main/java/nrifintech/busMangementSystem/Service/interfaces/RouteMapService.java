package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteMapService {

	void addRouteMap(int route_id,int destination_id,int index,String time);

}
