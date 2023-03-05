package nrifintech.busMangementSystem.payloads;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.User;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TicketDto {
	int busId;
	int routeId;
	int userId;
	@Override
	public String toString() {
		return "TicketDto [busId=" + busId + ", routeId=" + routeId + ", userId=" + userId + "]";
	}
	
	
}
