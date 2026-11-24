package cz.zcu.codegen.instructions;

/******************************************
 * Class for representing WRI (write to the output) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class WriteInstruction extends Instruction {

	/**
	 * Constructor for the write instruction takes nothing because we output the top of the stack
	 **/
	public WriteInstruction() {
		super("WRI", 0, new NumeralPar(0));
	}
}
