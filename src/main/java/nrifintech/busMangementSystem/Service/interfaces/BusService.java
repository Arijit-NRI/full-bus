package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Bus;

@Service
public interface BusService {
	Bus createBus(Bus bus);
	Bus updateBus(Bus bus, int id);
	Bus getBus(int id);
	List<Bus> getBus();
	void deleteBus(int id);

}