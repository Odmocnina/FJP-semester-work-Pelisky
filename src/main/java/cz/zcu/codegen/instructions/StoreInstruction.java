package cz.zcu.codegen.instructions;

/******************************************
 * Class for representing STO (store value in the stack) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class StoreInstruction extends Instruction {

	/**
	 * Constructor for the STO instruction, it can store on different level for scope (local, global) storing and on
	 * specific address
	 *
	 * @param level if the value is to be stored on global 0, or local scope 1
	 * @param address on which address should the value be stored
	 **/
	public StoreInstruction(int level, int address) {
		super("STO", level, new NumeralPar(address));
	}
}
