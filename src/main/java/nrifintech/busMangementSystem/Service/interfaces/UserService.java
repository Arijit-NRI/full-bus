package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.User;
@Service
public interface UserService {
	User createUser(User user);
	User updateUser(User user, int id);
	User getUser(int id);
	List<User> getUser();
	void deleteUser(int id);
	boolean checkUser(String email, String password);
	boolean checkAdmin(String email, String password);
}
