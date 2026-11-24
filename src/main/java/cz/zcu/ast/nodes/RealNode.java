package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.LitInstruction;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;
import cz.zcu.utils.Constants;

import java.util.List;

public class RealNode implements IAstNode {

    /** the number value of the Real number **/
    public double value;

    /**
     * Creates a new RealNode with the given double value.
     *
     * @param value the double value for this node
     **/
    public RealNode(double value) {
        this.value = value;
    }

    /**
     * Analyzes the real node. Checks its validity in the current scope.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
    }

    /**
	 * Generates code for the real node. Scales
	 *
	 * @param tableOfCurrentScope the current scope for code generation
	 * @return
	 **/
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
		int scaledValue = (int) Math.round(value * Constants.REAL_NUMBERS_SCALE);
        return List.of(new LitInstruction(scaledValue));
    }
}