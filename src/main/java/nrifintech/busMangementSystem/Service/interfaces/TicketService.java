package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.payloads.TicketDto;

@Service
public interface TicketService {
	Ticket getTicket(int id);
	List<Ticket> getTicket();
	void deleteTicket(int id);
	Ticket getMostRecentWaitingTicket(int busId);
	Ticket createTicket(TicketDto ticketDto);
	Ticket updateTicket(Ticket ticket, int id);
	List<Ticket> getTicketByUserId(int userId);

		
}
