package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;

import java.util.ArrayList;
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
        for (IAstNode statement : statements) {
            statement.analyze(blockscope);
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
        for (IAstNode statement : statements) {
            statement.generate(tableOfCurrentScope);
        }
        throw new NotImplemented(this.getClass().getCanonicalName());
    }
}
