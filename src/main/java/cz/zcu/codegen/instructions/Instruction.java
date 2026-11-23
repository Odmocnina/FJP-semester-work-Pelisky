package cz.zcu.codegen.instructions;

public abstract sealed class Instruction permits CondJumpInstruction, IntInstruction, JumpInstruction, LabelInstruction, LitInstruction, OperationInstruction, RetInstruction, StoreInstruction, WriteInstruction {
	private final String opcode;
	private final int level;
	private final Par par;

	public Instruction(String opcode, int level, Par par) {
		this.opcode = opcode;
		this.level = level;
		this.par = par;
	}

	public int getLevel() {
		return level;
	}

	public Par getPar() {
		return par;
	}

	public String getOpcode() {
		return opcode;
	}

	// Parameters can be: literal value, an enum op ops, an address (label) or zero
	public sealed interface Par permits NumeralPar, OperationPar, AddressPar {
		/**
		 * Return the parameter's value as a String.
		 * Not an equivalent of "toString", which is for debug printing!
		 * @return Value of the parameter as {@link String}
		 */
		String getString();
	}

}
