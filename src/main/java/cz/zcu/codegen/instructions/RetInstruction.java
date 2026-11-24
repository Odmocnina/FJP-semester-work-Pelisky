package cz.zcu.codegen.instructions;

/******************************************
 * Class for representing RET (return from program/subprogram) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class RetInstruction extends Instruction {

	/**
	 * Constructor of the RET instruction, takes no parameters because RET instruction is always RET 0 0
	 **/
	public RetInstruction() {
		super("RET", 0, new NumeralPar(0));
	}
}
