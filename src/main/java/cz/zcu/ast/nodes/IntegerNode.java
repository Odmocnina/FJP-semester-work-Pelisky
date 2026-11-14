package cz.zcu.ast.nodes;

import cz.zcu.symboltable.SymbolTable;

/********************************************
 * Represents an integer node in the abstract syntax tree (AST).
 * This node contains an integer value.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class IntegerNode implements IAstNode {

    /** the number value of the Integer **/
    public int value;

    /**
     * Creates a new IntegerNode with the given integer value.
     *
     * @param value the integer value for this node
     **/
    public IntegerNode(int value) {
        this.value = value;
    }

    /**
     * Analyzes the integer node.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {

    }

    /**
     * Generates code for the integer node.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(SymbolTable.Scope tableOfCurrentScope, Object generator) {

    }
}