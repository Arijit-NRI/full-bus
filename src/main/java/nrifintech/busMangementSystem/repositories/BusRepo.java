package nrifintech.busMangementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nrifintech.busMangementSystem.entities.Bus;


public interface BusRepo extends  JpaRepository<Bus, Integer>{


	Bus findByName(String name);

}
