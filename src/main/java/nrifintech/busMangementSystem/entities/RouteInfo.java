package nrifintech.busMangementSystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class RouteInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	int route_id;
	String date;
	int total_seats;
    int total_bookings;
    int total_cancellations;
}
