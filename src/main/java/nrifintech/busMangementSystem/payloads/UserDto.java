package nrifintech.busMangementSystem.payloads;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
	int id;
	String name;
	String email;
	String password;
}
