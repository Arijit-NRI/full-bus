package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.exception.UnauthorizedAction;
import nrifintech.busMangementSystem.repositories.BusRepo;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusRepo busRepo;

	@Override
	public Bus createBus(Bus bus) {
		bus.setNumberOfSeats(bus.getTotalNumberOfseats());
		return busRepo.save(bus);
	}

	@Override
	public Bus updateBus(Bus updatedBus, int id) {
//		throw new UnauthorizedAction("bus creation check, null", null);
		Bus bus = busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));
		System.out.println("prev bus is "+ bus);
		System.out.println("updated bus is "+ updatedBus);
		if(updatedBus.getName()!=null) bus.setName(updatedBus.getName());
		if(updatedBus.getTotalNumberOfseats()>0) bus.setTotalNumberOfseats(updatedBus.getTotalNumberOfseats());
		bus.setNumberOfSeats(updatedBus.getNumberOfSeats());
		System.out.println("updated bus is "+ bus);
		return busRepo.save(bus);
	}

	@Override
	public Bus getBus(int id) {
		return busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));
	}

	@Override
	public List<Bus> getBus() {
		return busRepo.findAll();
	}

	@Override
	public void deleteBus(int id) {
		Bus bus = busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));
		busRepo.delete(bus);
	}
	


	


}
