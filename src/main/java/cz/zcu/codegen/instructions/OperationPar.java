package cz.zcu.codegen.instructions;

import java.util.HashMap;
import java.util.Map;

/**
 * It is recommended to use the {@link fromcharacter} method instead of constructing unnecessary objects
 * @param op
 */
public record OperationPar(OPS op) implements Instruction.Par {
	@Override
	public String getString() {
		return String.format("%d", op.ordinal() + 1);
	}

	public enum OPS {
		UMIN, ADD, SUB, MUL, DIV, MOD, ODD, EQU, NEQ, LES, MEQ, MOR, LEQ
	}

	private static final Map<String, OperationPar> optopar = Map.ofEntries(
			Map.entry("+", new OperationPar(OPS.ADD)),
			Map.entry("-", new OperationPar(OPS.SUB)),
			Map.entry("*", new OperationPar(OPS.MUL)),
			Map.entry("/", new OperationPar(OPS.DIV)),
			Map.entry("%", new OperationPar(OPS.MOD)),
			Map.entry("=", new OperationPar(OPS.EQU)),
			Map.entry("!=", new OperationPar(OPS.NEQ)),
			Map.entry("<", new OperationPar(OPS.LES)),
			Map.entry("<=", new OperationPar(OPS.LEQ)),
			Map.entry(">", new OperationPar(OPS.MOR)),
			Map.entry(">=", new OperationPar(OPS.MEQ))
			);

	public static OperationPar fromcharacter(String operator) {
		return optopar.get(operator);
	}
}
