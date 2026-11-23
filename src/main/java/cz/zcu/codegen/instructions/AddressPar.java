package cz.zcu.codegen.instructions;

import cz.zcu.exceptions.NotImplemented;

public record AddressPar(Label label) implements Instruction.Par {
	@Override
	public String getString() {
		return String.format("@%s", label.getName());
	}
}
