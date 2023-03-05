package nrifintech.busMangementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.Destination;
import java.util.List;

public interface BusMapRepo extends JpaRepository<BusMap, Integer> {
	
	@Transactional
	@Modifying
 	@Query(value = "DELETE FROM bus_map WHERE bus_id = :busId",nativeQuery = true)
	void deleteByBusId(@Param("busId") int busId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM bus_map WHERE route_id = :routeId",nativeQuery = true)
	void deleteByRouteId(@Param("routeId") int routeId);
	
	@Query(value = "SELECT * FROM bus_map WHERE bus_id = :busId",nativeQuery = true)
	BusMap findByBusId(@Param("busId") int busId);
	
	@Query(value = "SELECT * FROM bus_map WHERE route_id = :routeId",nativeQuery = true)
	BusMap findByRouteId(@Param("routeId") int routeId);
	
}
