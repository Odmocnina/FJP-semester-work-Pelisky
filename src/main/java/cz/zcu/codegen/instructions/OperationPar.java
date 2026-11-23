package cz.zcu.codegen.instructions;

public record OperationPar(OPS op) implements Instruction.Par {
	public enum OPS {
		UMIN, ADD, SUB, MUL, DIV, MOD, ODD, EQU, NEQ, LES, MET, MOT, LET
	}
}
