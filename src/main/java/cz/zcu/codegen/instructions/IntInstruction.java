package cz.zcu.codegen.instructions;

/******************************************
 * Class for representing INT (allocation of memory) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class IntInstruction extends Instruction {

	/**
	 * Constructor of the INT instruction
	 *
	 * @param par how many spaces are to be allocated
	 **/
	public IntInstruction(long par) {
		super("INT", 0, new NumeralPar(par));
	}
}
