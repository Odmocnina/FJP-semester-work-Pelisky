package cz.zcu.codegen.instructions;

/******************************************
 * Class for representing OPR (operation) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class OperationInstruction extends Instruction{

	/**
	 * Constructor for the OPR instruction, takes the enum OPS from class OperationPar and creates new instance of
	 * OperationPar class
	 *
	 * @param operation operation to be executed, represented in the enum OperationPar.OPS
	 **/
	public OperationInstruction(OperationPar.OPS operation) {
		super("OPR", 0, new OperationPar(operation));
	}

	/**
	 * Constructor for the OPR instruction, takes the instance of OperationPar class
	 *
	 * @param operation operation to be executed, represented in the instance of OperationPar
	 **/
	public OperationInstruction(OperationPar operation) {
		super("OPR", 0, operation);
	}
}
