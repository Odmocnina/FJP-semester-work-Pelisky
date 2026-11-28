package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.*;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/********************************************
 * Represents a while loop node in the abstract syntax tree (AST).
 * This node contains the condition for the while loop and the body of commands to execute while the condition is true.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class WhileNode implements IAstNode {

    /** condition for while **/
    public IAstNode condition;

    /** commands in the while **/
    public IAstNode body;

    /** The scope for the loop's condition */
    public SymbolTable.Scope myscope = null;
    /**
     * Creates a new while node with the given condition and body.
     *
     * @param condition condition for while cycle
     * @param body commands in the while cycle
     **/
    public WhileNode(IAstNode condition, IAstNode body) {
        this.condition = condition;
        this.body = body;
    }

    /**
     * Analyzes the while node by analyzing its condition and body.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        myscope = SymbolTable.SYMBOL_TABLE.pushScope();
        condition.analyze(myscope); // check if condition is ok
        body.analyze(myscope);     // check if the code inside is ok
        SymbolTable.SYMBOL_TABLE.popScope();
    }

    /**
     * Generates code for the while node by generating code for its condition and body.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return
     **/
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        List<Instruction> instr = new ArrayList<>();
        var endlab = new LabelInstruction("whileloopend");
        var startlab = new LabelInstruction("whileloopstart");
        instr.add(startlab);
        // needs to be rechecked every time
        instr.addAll(condition.generate(myscope));
        instr.add(new CondJumpInstruction(endlab.getPar()));
        // else run the body and retry checking
        instr.addAll(body.generate(myscope));
        instr.add(new JumpInstruction(startlab.getPar()));
        instr.add(endlab);
        return instr;
    }
}