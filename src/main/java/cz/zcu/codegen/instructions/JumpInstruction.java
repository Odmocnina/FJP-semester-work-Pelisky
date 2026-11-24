package cz.zcu.codegen.instructions;

/******************************************
 * Class for representing JMP (jump/goto) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class JumpInstruction extends Instruction {

	/**
	 * Constructor for the JMP instruction
	 *
	 * @param par label to jump to
	 **/
	public JumpInstruction(Instruction.Par par) {
		super("JMP", 0, par);
	}
}
