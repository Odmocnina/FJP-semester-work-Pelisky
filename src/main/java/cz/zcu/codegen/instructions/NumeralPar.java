package cz.zcu.codegen.instructions;

public record NumeralPar(long par) implements Instruction.Par {
	@Override
	public String getString() {
		return String.format("%d", par);
	}
}
