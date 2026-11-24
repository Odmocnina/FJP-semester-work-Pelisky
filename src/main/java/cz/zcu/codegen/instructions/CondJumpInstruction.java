package cz.zcu.codegen.instructions;

/******************************************
 * Class for representing JMC (jump/goto after condition) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class CondJumpInstruction extends Instruction {

	/**
	 * Constructor for the JMC instruction
	 *
	 * @param par label to jump to
	 **/
	public CondJumpInstruction(Par par) {
		super("JMC", 0, par);
	}
}
