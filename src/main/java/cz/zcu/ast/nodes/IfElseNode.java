package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.CondJumpInstruction;
import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.JumpInstruction;
import cz.zcu.codegen.instructions.LabelInstruction;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
     * @return
     */
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        // names of labels here end blocks, the meanings are therefore flipped (false is jumped to when cond is true)
		var cond = new ArrayList<>(condition.generate(tableOfCurrentScope));
        var trueblock = new ArrayList<>(thenBlock.generate(tableOfCurrentScope));

        if (elseBlock != null) {
            var falseblock = new ArrayList<>(elseBlock.generate(tableOfCurrentScope));
            var falselab = new LabelInstruction("false");
            falseblock.add(falselab);

            trueblock.add(new JumpInstruction(falselab.getPar()));
            var truelab = new LabelInstruction("true");
            trueblock.add(truelab);
            
            cond.add(new CondJumpInstruction(truelab.getPar()));

            cond.addAll(trueblock);
            cond.addAll(falseblock);
        } else {
            var truelab = new LabelInstruction("true");
            trueblock.add(truelab);
            cond.add(new CondJumpInstruction(truelab.getPar()));
            cond.addAll(trueblock);
        }
        return cond;
    }
}