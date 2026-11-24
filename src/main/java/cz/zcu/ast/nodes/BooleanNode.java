package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.LitInstruction;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;

import java.util.List;

/********************************************
 * Represents a boolean node in the abstract syntax tree (AST).
 * This node contains a boolean value.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class BooleanNode implements IAstNode {

    /** the boolean value of the BooleanNode **/
    public boolean value;

    /**
     * Creates a new BooleanNode with the given boolean value.
     *
     * @param value the boolean value for this node
     **/
    public BooleanNode(boolean value) {
        this.value = value;
    }

    /**
     * Analyzes the boolean node.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {

    }

    /**
	 * Generates code for the boolean node.
	 *
	 * @param tableOfCurrentScope the current scope for code generation
	 * @return
	 **/
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        // insert to the stack depending on if the boolean value is 0 or 1
        return List.of(new LitInstruction(this.value ? 1 : 0));
    }
}
