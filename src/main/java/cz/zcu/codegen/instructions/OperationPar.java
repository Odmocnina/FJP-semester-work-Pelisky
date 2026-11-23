package cz.zcu.codegen.instructions;

public record OperationPar(OPS op) implements Instruction.Par {
	@Override
	public String getString() {
		return String.format("%d", op.ordinal()+1);
	}

	public enum OPS {
		UMIN, ADD, SUB, MUL, DIV, MOD, ODD, EQU, NEQ, LES, MET, MOT, LET
	}
}
