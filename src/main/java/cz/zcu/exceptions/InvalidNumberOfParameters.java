package cz.zcu.exceptions;

public class InvalidNumberOfParameters extends RuntimeException {
	public InvalidNumberOfParameters(String message) {
		super(String.format("%s: Invalid number of parameters", message));
	}
}
