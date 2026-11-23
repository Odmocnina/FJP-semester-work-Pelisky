package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.StoreInstruction;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.Symbol;
import cz.zcu.symboltable.SymbolTable;

import java.util.List;

/********************************************
 * Represents a variable declaration node in the abstract syntax tree (AST).
 * This node contains the type, name, and optional assignment expression for a variable.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class VarDeclNode implements IAstNode {

    /** type of the variable **/
    public String type;

    /** name of the variable **/
    public String name;

    /** assigment of the variable, can be null **/
    public IAstNode expression;

    /** address within the local block */
    public Integer address = null;

    /**
     * Create new node for declaration of new variable
     *
     * @param type type of the variable
     * @param name name of the variable
     * @param expression assigment of the variable, can be null
     **/
    public VarDeclNode(String type, String name, IAstNode expression) {
        this.type = type;
        this.name = name;
        this.expression = expression;
    }

    /**
     * Analyzes the variable declaration node by checking its type, name, and expression.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        if (this.name == null) {
            throw new Exception("Variable name is null");
        }
        SymbolTable.SYMBOL_TABLE.putSymbol(tableOfCurrentScope, new Symbol(0, this.name, Symbol.SymbolType.VARIABLE));
    }

    /**
     * Function for generation of the PL/0 assembler instructions
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return
     * @throws Exception if an error occurs during code generation
     */
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        var instructs = this.expression.generate(tableOfCurrentScope);
        // variables are always local
        instructs.add(new StoreInstruction(0, this.address));
        return instructs;
    }
}
