package cz.zcu.codegen.instructions;

public final class StoreInstruction extends Instruction {
	public StoreInstruction(int level, int address) {
		super("STO", level, new NumeralPar(address));
	}
}
