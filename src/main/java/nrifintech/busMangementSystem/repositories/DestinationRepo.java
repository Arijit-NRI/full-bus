package nrifintech.busMangementSystem.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nrifintech.busMangementSystem.entities.Destination;

public interface DestinationRepo extends JpaRepository<Destination, Integer>{



	@Query("SELECT d FROM Destination d WHERE d.name LIKE %?1%")
	public List<Destination> findByName(String name);
	
	@Query("SELECT d FROM Destination d WHERE d.name = ?1")
	public Destination checkIfExistsByName(String name);



}
