package cz.zcu.codegen.instructions;

import cz.zcu.exceptions.NotImplemented;

public record AddressPar(Label label) implements Instruction.Par {
	@Override
	public String getString() {
		throw new NotImplemented("Adress Parameter");
	}
}
