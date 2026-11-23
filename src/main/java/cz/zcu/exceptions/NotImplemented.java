package cz.zcu.exceptions;

public class NotImplemented extends RuntimeException {
	public NotImplemented(String name) {
		super(String.format("Not implemented: %s", name));
	}
}
