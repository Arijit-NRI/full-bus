package nrifintech.busMangementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nrifintech.busMangementSystem.entities.Route;


public interface RouteRepo extends JpaRepository<Route, Integer> {	
	@Query("SELECT r FROM Route r WHERE r.start_destination_id = :startId AND r.end_destination_id = :endId")
	List<Route> searchBySourceAndDestination(@Param("startId") int startId, @Param("endId") int endId);

}

