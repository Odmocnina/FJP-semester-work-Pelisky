package cz.zcu.codegen.instructions;

public final class JumpInstruction extends Instruction {
	public JumpInstruction(Instruction.Par par) {
		super("JMP", 0, par);
	}
}
