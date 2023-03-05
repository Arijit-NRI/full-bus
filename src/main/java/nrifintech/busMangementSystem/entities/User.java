package nrifintech.busMangementSystem.entities;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	//0 for user and 1 for admin
	int type;
	@NotEmpty
	String name;
	@NotEmpty
	@Email(message = "enter a valid mail")
	
	String email;
	@Size(min=4,message="password should be of atleast 4 characaters")
	@NotEmpty(message="password cant be empty")
	String password;
	@OneToMany
	Set<Ticket> tickets = new HashSet<>();
	
}
