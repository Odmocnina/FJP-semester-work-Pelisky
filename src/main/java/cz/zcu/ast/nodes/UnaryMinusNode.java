package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.OperationInstruction;
import cz.zcu.codegen.instructions.OperationPar;
import cz.zcu.symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/********************************************
 * Represents a unary minus node in the abstract syntax tree (AST).
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class UnaryMinusNode implements IAstNode {

    /** The expression to be negated **/
    public IAstNode expression;

    /**
     * Creates a new UnaryMinusNode with the given expression.
     *
     * @param expression the inner expression node
     **/
    public UnaryMinusNode(IAstNode expression) {
        this.expression = expression;
    }

    /**
     * Analyzes the expression after the -
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
        expression.analyze(tableOfCurrentScope);
    }

    /**
     * Add the instruction for unary minus
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return
     **/
    @Override
    public List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope) {
        var instr = new ArrayList<>(expression.generate(tableOfCurrentScope));
        instr.add(new OperationInstruction(OperationPar.OPS.UMIN)); //
        return instr;
    }

}
