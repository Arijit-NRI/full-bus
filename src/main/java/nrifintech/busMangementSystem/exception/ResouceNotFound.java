package nrifintech.busMangementSystem.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResouceNotFound extends RuntimeException{
	String resourceName;
	String FieldName;
	long fieldValue;
	public ResouceNotFound(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not fosund with %s %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		FieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
