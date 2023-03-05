package nrifintech.busMangementSystem.entities;

import java.util.Date;

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
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String status;
	@ManyToOne
	Bus bus;
//	@ManyToOne
//	Route route;
	@ManyToOne
	User user;
	Date createdAt;

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", status=" + status + ", bus=" + bus + ", user=" + user + ", createdAt="
				+ createdAt + "]";
	}
	

}
