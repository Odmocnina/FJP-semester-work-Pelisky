package cz.zcu.ast.nodes;

import java.util.List;

/********************************************
 * Represents an operation node in the abstract syntax tree (AST). This node contains an operator and a list of
 * parameters for the operations.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class OperationNode implements IAstNode {

    /** what operation is going to be done **/
    public String operator;

    /** list of parameters for the operations **/
    public List<IAstNode> operands;

    /**
     * Creates a new operation node with the given operator and list of operands.
     *
     * @param operator the operator for this operation node
     * @param operands the list of operands for this operation node
     **/
    public OperationNode(String operator, List<IAstNode> operands) {
        this.operator = operator;
        this.operands = operands;
    }

    /**
     * Analyzes the operation node by analyzing its operands.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(Object scope) throws Exception {
        for (IAstNode operand : operands) {
            operand.analyze(scope);
        }
    }

    /**
     * Generates code for the operation node by generating code for its operands.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(Object scope, Object generator) {
        for (IAstNode operand : operands) {
            operand.generate(scope, generator);
        }
    }
}