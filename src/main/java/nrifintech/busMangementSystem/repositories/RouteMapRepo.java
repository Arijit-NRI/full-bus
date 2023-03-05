package nrifintech.busMangementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import nrifintech.busMangementSystem.entities.RouteMap;

public interface RouteMapRepo extends JpaRepository<RouteMap, Integer>{
	@Modifying
	@Query(value = "DELETE FROM route_map WHERE route_id = :id", nativeQuery = true)
	void deleteFromRepoByRouteId(@Param("id") int id);
	
	@Query(value = "SELECT * FROM route_map WHERE route_id = :id ORDER BY destination_index", nativeQuery = true)
	List<RouteMap> getByRouteIdSortedByDestinationIndex(@Param("id") int id);
}
