package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.OperationInstruction;
import cz.zcu.codegen.instructions.OperationPar;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;

import java.util.ArrayList;
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
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        for (IAstNode operand : operands) {
            operand.analyze(tableOfCurrentScope);
        }
    }

    /**
     * Generates code for the operation node by generating code for its operands.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return
     */
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        var instructions = new ArrayList<Instruction>();
        for (IAstNode operand : operands) {
            instructions.addAll(operand.generate(tableOfCurrentScope));
        }
        if (operands.size() == 1 && operator.equals("-")) {
            instructions.add(new OperationInstruction(OperationPar.OPS.UMIN));
        } else {
        	instructions.add(new OperationInstruction(OperationPar.fromcharacter(operator)));
        }
        return instructions;
    }
}