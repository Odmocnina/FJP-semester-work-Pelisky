package cz.zcu.codegen.instructions;

import cz.zcu.exceptions.NotImplemented;

/******************************************
 * Record for holding address for if or cycles
 *
 * @param label label of the address, used for JMP
 ******************************************/
public record AddressPar(Label label) implements Instruction.Par {

	/**
	 * Method for translating label to string
	 *
	 * @return label in string
	 **/
	@Override
	public String getString() {
		return String.format("@%s", label.getName());
	}
}
