package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.SymbolTable;

import java.util.List;

/********************************************
 * Represents a program node in the abstract syntax tree (AST).
 * This node contains the name of the program and its code block. This is the declaration of the program name at the
 * beginning of the program, and the code block contains all the commands that are part of the program.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class ProgramNode implements IAstNode {

    /** name of the program (string after program key word) **/
    public String programName;

    /** code block of the program **/
    public IAstNode codeBlock;

    /**
     * Creates a new ProgramNode with the given program name and code block, with programs code inside.
     *
     * @param programName the name of the program
     * @param codeBlock the code block of the program
     **/
    public ProgramNode(String programName, IAstNode codeBlock) {
        this.programName = programName;
        this.codeBlock = codeBlock;
    }

    /**
     * Analyzes the program node by analyzing its code block.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        if (codeBlock != null) {
            codeBlock.analyze(tableOfCurrentScope);
        }
    }

    /**
     * Generates code for the program node by generating code for its code block.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return
     */
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        if (codeBlock != null) {
            codeBlock.generate(tableOfCurrentScope);
        }
        throw new NotImplemented(this.getClass().getCanonicalName());
    }
}