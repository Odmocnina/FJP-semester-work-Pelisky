package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;

import java.util.List;

/********************************************
 * Represents a for loop in the abstract syntax tree (AST).
 * This node contains the iterator variable name, initial expression, end expression, and the body of the loop.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class ForNode implements IAstNode {

    /** start value **/
    public IAstNode initExpression;

    /** end value **/
    public IAstNode toExpression;

    /** commands under the for **/
    public IAstNode body;

    /**
     * Creates a new for node with the given initial expression, to expression, and body.
     *
     * @param initExpression the initial value expression for the loop
     * @param toExpression the end value expression for the loop
     * @param body the commands to execute within the for loop
     **/
    public ForNode(IAstNode initExpression, IAstNode toExpression, IAstNode body) {
        this.initExpression = initExpression;
        this.toExpression = toExpression;
        this.body = body;
    }

    /**
     * Analyzes the for node by analyzing its initial expression, to expression, and body.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        initExpression.analyze(tableOfCurrentScope);
        toExpression.analyze(tableOfCurrentScope);
        body.analyze(tableOfCurrentScope);
    }

    /**
     * Generates code for the for node by generating code for its initial expression, to expression, and body.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return list of PL/0 instructions
     **/
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        throw new NotImplemented(this.getClass().getCanonicalName());
    }
}
