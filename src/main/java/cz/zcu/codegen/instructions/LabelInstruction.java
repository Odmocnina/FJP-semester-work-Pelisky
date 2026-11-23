package cz.zcu.codegen.instructions;

/**
 * Pseudo-instruction that creates a label at a given point in the code.
 * Do not count on the labelname being preserved, see {@code addLabel} in {@link Label}
 */
public final class LabelInstruction extends Instruction {
	public LabelInstruction(String labelname) {
		super("", 0, new AddressPar(Label.addLabel(labelname)));
	}
}
