package cz.zcu.ast.nodes;

import cz.zcu.symboltable.SymbolTable;

/********************************************
 * Represents a variable declaration node in the abstract syntax tree (AST).
 * This node contains the type, name, and optional assignment expression for a variable.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class VarDeclNode implements IAstNode {

    /** type of the variable **/
    public String type;

    /** name of the variable **/
    public String name;

    /** assigment of the variable, can be null **/
    public IAstNode expression;

    /**
     * Create new node for declaration of new variable
     *
     * @param type type of the variable
     * @param name name of the variable
     * @param expression assigment of the variable, can be null
     **/
    public VarDeclNode(String type, String name, IAstNode expression) {
        this.type = type;
        this.name = name;
        this.expression = expression;
    }

    /**
     * Analyzes the variable declaration node by checking its type, name, and expression.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        if (this.name == null) {
            throw new Exception("Variable name is null");
        }
    }

    /**
     * Function for generation of the PL/0 assembler instructions
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @param generator the code generator to use
     * @throws Exception if an error occurs during code generation
     **/
    @Override
    public void generate(SymbolTable.Scope tableOfCurrentScope, Object generator) {

    }
}
