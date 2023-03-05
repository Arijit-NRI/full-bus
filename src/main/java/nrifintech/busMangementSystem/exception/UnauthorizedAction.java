package nrifintech.busMangementSystem.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedAction extends RuntimeException{
	String ActionName;
	String UserName;

	public UnauthorizedAction(String ActionName, String UserName) {
		super(String.format("%s done by %s", ActionName, UserName));
		this.ActionName = ActionName;
		UserName = UserName;
	}
}
