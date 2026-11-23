package cz.zcu.codegen.instructions;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Acquired from creating a LabelInstruction
 */
public class Label {
	private final String name;

	Label(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	private static final Set<String> LABELS = new HashSet<>();

	/**
	 * Creates a label with the given name as base.
	 * !Mangles names if a label exists! Do not use the string given as argument to refer to the label!
	 * Use only the returned {@code Label} object.
	 *
	 * @param name Requested name of the new {@code Label}. May be mangled if duplicate.
	 * @return Actual {@code Label}
	 */
	public static Label addLabel(String name) {
		if (LABELS.contains(name)) {
			var r = new Random();
			String newname = name + r.nextInt();
			while (LABELS.contains(newname)) {
				newname = name + r.nextInt();
			}
			LABELS.add(newname);
			return new Label(newname);
		} else {
			LABELS.add(name);
			return new Label(name);
		}
	}

}
