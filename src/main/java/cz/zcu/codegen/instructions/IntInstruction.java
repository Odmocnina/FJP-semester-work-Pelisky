package cz.zcu.codegen.instructions;

public final class IntInstruction extends Instruction {
	public IntInstruction(long par) {
		super("INT", 0, new NumeralPar(par));
	}
}
