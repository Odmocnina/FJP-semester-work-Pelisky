package cz.zcu.exceptions;

public class VariableRedeclaration extends RuntimeException {
	public VariableRedeclaration(String message) {
		super(String.format("The variable has already been declared in this scope: %s\n", message));
	}
}
