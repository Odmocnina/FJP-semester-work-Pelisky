package cz.zcu.codegen.instructions;

public final class OperationInstruction extends Instruction{
	public OperationInstruction(OperationPar.OPS operation) {
		super("OPR", 0, new OperationPar(operation));
	}

	public OperationInstruction(OperationPar operation) {
		super("OPR", 0, operation);
	}
}
