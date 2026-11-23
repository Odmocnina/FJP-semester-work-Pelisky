package cz.zcu.codegen.instructions;

public final class WriteInstruction extends Instruction {
	public WriteInstruction() {
		super("WRI", 0, new NumeralPar(0));
	}
}
