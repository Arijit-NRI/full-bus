package nrifintech.busMangementSystem.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@NotEmpty
	String name;
	@NotEmpty
	String bus_number;
	int numberOfSeats;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;
	int totalNumberOfseats;
	public void resetNumberOfSeats() {
		numberOfSeats = totalNumberOfseats;
		
	}
	@Override
	public String toString() {
		return "Bus [id=" + id + ", name=" + name + ", bus_number=" + bus_number + ", numberOfSeats=" + numberOfSeats
				+ ", route=" + route + ", totalNumberOfseats=" + totalNumberOfseats + "]";
	}

}
