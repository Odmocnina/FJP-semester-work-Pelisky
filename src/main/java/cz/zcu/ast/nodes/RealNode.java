package cz.zcu.ast.nodes;

import cz.zcu.symboltable.SymbolTable;

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
     * Generates code for the real node.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(SymbolTable.Scope tableOfCurrentScope, Object generator) {
    }
}