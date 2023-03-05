package nrifintech.busMangementSystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.RouteInfo;
public interface RouteInfoRepo extends JpaRepository<RouteInfo, Integer> {
	@Query(value = "SELECT * FROM route_info WHERE route_id = :routeId AND date = :date",nativeQuery = true)
	RouteInfo getRouteByPresentDate(@Param("routeId") int routeId, @Param("date") String date);
	
	@Query(value = "DELETE FROM route_info WHERE route_id = :routeId",nativeQuery = true)
	void deleteRouteInfo(@Param("routeId") int routeId);
}
