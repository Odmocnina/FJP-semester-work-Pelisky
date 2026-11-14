package cz.zcu.ast.nodes;

import cz.zcu.symboltable.SymbolTable;

import java.util.List;

/********************************************
 * Represents a function or procedure call in the abstract syntax tree (AST).
 * This node contains the name of the function or procedure being called and a list of arguments passed to it.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class CallNode implements IAstNode {

    /** the name of the function or procedure being called **/
    public String identifierName;

    /** the list of arguments passed to the function or procedure **/
    public List<IAstNode> arguments;

    /**
     * Creates a new call node with the given identifier name and list of arguments.
     *
     * @param identifierName the name of the function or procedure being called
     * @param arguments the list of arguments passed to the function or procedure
     **/
    public CallNode(String identifierName, List<IAstNode> arguments) {
        this.identifierName = identifierName;
        this.arguments = arguments;
    }

    /**
     * Analyzes the call node by analyzing its arguments.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        // check arguments from left to right
        for (IAstNode arg : arguments) {
            arg.analyze(tableOfCurrentScope);
        }
    }

    /**
     * Generates code for the call node by generating code for its arguments.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(SymbolTable.Scope tableOfCurrentScope, Object generator) {
        for (IAstNode arg : arguments) {
            arg.generate(tableOfCurrentScope, generator);
        }
    }
}