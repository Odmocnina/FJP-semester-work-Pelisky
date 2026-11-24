package cz.zcu.codegen.instructions;

/*******************************************
 * Abstract class for representing PL/0 instructions
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 *******************************************/
public abstract sealed class Instruction permits CondJumpInstruction, IntInstruction, JumpInstruction, LabelInstruction,
		LitInstruction, LodInstruction, OperationInstruction, RetInstruction, StoreInstruction, WriteInstruction {

	/** operation code of the instruction **/
	private final String opcode;

	/** level difference on where is the operation being done and where was the variable being used declared **/
	private final int level;

	/** parameter of the instruction **/
	private final Par par;

	/***********************************************
	 * Constructor for the instruction
	 *
	 * @param opcode Instruction being used in string (STO, LIT, ...)
	 * @param level level difference of the instruction and the var declaration being used
	 * @param par parameter of the instruction
	 ***********************************************/
	public Instruction(String opcode, int level, Par par) {
		this.opcode = opcode;
		this.level = level;
		this.par = par;
	}

	/**
	 * Getter of the level difference of the instruction
	 *
	 * @return int value of the level difference
	 **/
	public int getLevel() {
		return level;
	}

	/**
	 * Getter of the parameter of the instruction
	 *
	 * @return parameter of the instruction
	 **/
	public Par getPar() {
		return par;
	}

	/**
	 * Getter of the PL/0 instruction
	 *
	 * @return PL/0 instruction in string
	 **/
	public String getOpcode() {
		return opcode;
	}

	/**
	 * Return the parameter's value as a String. Not an equivalent of "toString", which is for debug printing!
	 * Parameters can be: literal value, an enum op ops, an address (label) or zero
	 *
	 * @return Value of the parameter as {@link String}
	 */
	public sealed interface Par permits NumeralPar, OperationPar, AddressPar {
		String getString();
	}

}
