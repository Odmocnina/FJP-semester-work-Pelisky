package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.LodInstruction;
import cz.zcu.codegen.instructions.StoreInstruction;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.exceptions.UnknownSymbol;
import cz.zcu.symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/**************************************************
 * Class representing an assignment node in the abstract syntax tree (AST).
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 **************************************************/
public class AssignNode implements IAstNode {

    /** The name of the variable being assigned to **/
    public String identifierName;

    /** The expression being assigned to the variable **/
    public IAstNode expression;

    /**
     * Creates a new assignment node with the given identifier name and expression.
     *
     * @param identifierName the name of the variable being assigned to
     * @param expression the expression being assigned to the variable
     **/
    public AssignNode(String identifierName, IAstNode expression) {
        this.identifierName = identifierName;
        this.expression = expression;
    }

    /**
     * Analyzes the assignment node by analyzing the expression on the right side of the assignment.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        // check the validity of expression on the right side of the assignment
        expression.analyze(tableOfCurrentScope);
        if (SymbolTable.SYMBOL_TABLE.getSymbol(tableOfCurrentScope, this.identifierName) == null) {
            throw new UnknownSymbol(this.identifierName);
        }
    }

    /**
     * Generates code for the assignment node by generating code for the expression on the right side of the assignment.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return
     */
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        // generate code for the expression on the right side of the assignment
		var instr = new ArrayList<>(expression.generate(tableOfCurrentScope));
        instr.add(new StoreInstruction(0, SymbolTable.SYMBOL_TABLE.getSymbol(tableOfCurrentScope, this.identifierName).address()));
        return instr;
    }
}