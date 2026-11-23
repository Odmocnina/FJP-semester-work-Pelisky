package cz.zcu.codegen.instructions;

public final class RetInstruction extends Instruction {
	public RetInstruction() {
		super("RET", 0, new NumeralPar(0));
	}
}
