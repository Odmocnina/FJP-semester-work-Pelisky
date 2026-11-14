package cz.zcu.ast.nodes;

import cz.zcu.symboltable.SymbolTable;

/********************************************
 * Represents an if-else statement in the abstract syntax tree (AST).
 * This node contains the condition, the block of commands to execute if the condition is true,
 * and an optional block of commands to execute if the condition is false.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class IfElseNode implements IAstNode {

    /** condition for if **/
    public IAstNode condition;

    /** commands in if **/
    public IAstNode thenBlock;

    /** code in else. can be null **/
    public IAstNode elseBlock;

    /**
     * Creates a new if-else node with the given condition, then block, and optional else block.
     *
     * @param condition the condition for the if statement
     * @param thenBlock the commands to execute if the condition is true
     * @param elseBlock the commands to execute if the condition is false (can be null)
     **/
    public IfElseNode(IAstNode condition, IAstNode thenBlock, IAstNode elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    /**
     * Analyzes the if-else node by analyzing its condition, then block, and optional else block.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        condition.analyze(tableOfCurrentScope);
        thenBlock.analyze(tableOfCurrentScope);
        if (elseBlock != null) {
            elseBlock.analyze(tableOfCurrentScope);
        }
    }

    /**
     * Generates code for the if-else node by generating code for its condition, then block, and optional else block.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(SymbolTable.Scope tableOfCurrentScope, Object generator) {

    }
}