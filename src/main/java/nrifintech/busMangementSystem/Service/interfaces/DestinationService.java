package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Destination;
@Service
public interface DestinationService {
	Destination createDestination(Destination destination);
	Destination updateDestination(Destination destination, int id);
	Destination getDestination(int id);
	List<Destination> getDestination();
	void deleteDestination(int id);
	List<Destination> getDestinationByName(String name);
}
