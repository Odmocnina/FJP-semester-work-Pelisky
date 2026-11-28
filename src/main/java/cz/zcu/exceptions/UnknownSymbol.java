package cz.zcu.exceptions;

public class UnknownSymbol extends RuntimeException {
	public UnknownSymbol(String message) {
		super(String.format("Symbol not found for scope: %s", message));
	}
}
