package cz.zcu.codegen.instructions;

public final class LitInstruction extends Instruction {
	public LitInstruction(int value) {
		super("LIT", 0, new NumeralPar(value));
	}
}
