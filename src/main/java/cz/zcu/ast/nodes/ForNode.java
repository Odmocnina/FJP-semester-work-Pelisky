package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.*;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;

import java.lang.classfile.instruction.LoadInstruction;
import java.util.ArrayList;
import java.util.List;

/********************************************
 * Represents a for loop in the abstract syntax tree (AST).
 * This node contains the iterator variable name, initial expression, end expression, and the body of the loop.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class ForNode implements IAstNode {

    /** global counter for labes created by fors **/
    private static int labelCounter = 0;

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
        var instr = new ArrayList<Instruction>();

        int id = labelCounter++;

        LabelInstruction startLabel = new LabelInstruction("FOR_START_" + id);
        LabelInstruction endLabel = new LabelInstruction("FOR_END_" + id);

        int baseOffset = 3 + tableOfCurrentScope.getLocalVariableCount();
        int counterAddress = baseOffset;   // address of the value for for-cycle counter
        int limitAddress = baseOffset + 1;   // address for the value of the for-cycle roof

        // save 2 spaces at the stack for end and beginning of for
        instr.add(new IntInstruction(2));

        // get the starting value and store in
        instr.addAll(initExpression.generate(tableOfCurrentScope));
        instr.add(new StoreInstruction(0, counterAddress));

        // get the ending value and store it
        instr.addAll(toExpression.generate(tableOfCurrentScope));
        instr.add(new StoreInstruction(0, limitAddress));

        // getting the start of the cycle and check of the ending condition
        instr.add(startLabel);
        instr.add(new LodInstruction(counterAddress));
        instr.add(new LodInstruction(limitAddress));
        instr.add(new OperationInstruction(OperationPar.OPS.LEQ));
        // counter <= limit
        instr.add(new CondJumpInstruction(endLabel.getPar())); // if true continue with the cycle if false, jump

        // generate the body of the for cycle
        instr.addAll(body.generate(tableOfCurrentScope));

        // increase the for-cycle counter by 1
        instr.add(new LodInstruction(counterAddress));
        instr.add(new LitInstruction(1));
        instr.add(new OperationInstruction(OperationPar.OPS.ADD));
        instr.add(new StoreInstruction(0, counterAddress));

        // jump to the start of for and clearing of the memory
        instr.add(new JumpInstruction(startLabel.getPar()));
        instr.add(endLabel);
        instr.add(new IntInstruction(-2));

        return instr;
    }
}
