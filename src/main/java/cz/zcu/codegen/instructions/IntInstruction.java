package cz.zcu.codegen.instructions;

public final class IntInstruction extends Instruction {
	public IntInstruction(int par) {
		super("INT", 0, new NumeralPar(par));
	}
}
