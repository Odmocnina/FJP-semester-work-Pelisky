package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.IntInstruction;
import cz.zcu.symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/********************************************
 * Represents a block of code in the abstract syntax tree (AST).
 * This node contains a list of statements that are part of the block.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class BlockNode implements IAstNode {

    /** List of commands that are in the block **/
    public List<IAstNode> statements = new ArrayList<>();

    /**
     * Function for checking the semantic of the block of code, checks the semantic of all commands in it
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        // Block itself cannot be wrong that is chacked by the grammar, commands in it might be
        var blockscope = SymbolTable.SYMBOL_TABLE.pushScope();
        // analyze and connect in the right order
        for (IAstNode statement : statements) {
            statement.analyze(blockscope);
        }
        // but extract all declarations to the top for codegen for easier access
        // and assign addresses
        statements.sort(Comparator.comparing((node) -> !(node instanceof VarDeclNode)));
        for (int i = 0; i < statements.size(); i++) {
            var stat = statements.get(i);
            if (!(stat instanceof VarDeclNode)) {
                break;
            }
            ((VarDeclNode) stat).address = i+3; // first three are SB,DB and PC
        }
        SymbolTable.SYMBOL_TABLE.popScope();
    }

    /**
     * Function for generation of the PL/0 assembler instructions
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return
     */
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        // Block itself does nothing, commands in it may do something
        var instr = new ArrayList<Instruction>();
        var nodecls = statements.stream().filter(node -> (node instanceof VarDeclNode)).count();
        instr.add(new IntInstruction(nodecls));
        for (IAstNode statement : statements) {
            instr.addAll(statement.generate(tableOfCurrentScope));
        }
        instr.add(new IntInstruction(-nodecls));
        return instr;
    }
}
