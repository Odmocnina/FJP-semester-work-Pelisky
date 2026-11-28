package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.*;
import cz.zcu.exceptions.NotImplemented;
import cz.zcu.symboltable.Symbol;
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

    /** name of the variable where the current loop value is held **/
    public String iteratorName;

	/** the scope this for loop creates */
	private SymbolTable.Scope myscope = null;

	/** Symbol for locating the endpoint value of the for loop */
	private Symbol toexpr = null;

    /**
     * Creates a new for node with the given initial expression, to expression, and body.
     *
     * @param initExpression the initial value expression for the loop
     * @param toExpression the end value expression for the loop
     * @param body the commands to execute within the for loop
     * @param iteratorName the name of the variable that will track the current loop value
     **/
    public ForNode(IAstNode initExpression, IAstNode toExpression, IAstNode body, String iteratorName) {
        this.initExpression = initExpression;
        this.toExpression = toExpression;
        this.body = body;
        this.iteratorName = iteratorName;
    }

    /**
     * Analyzes the for node by analyzing its initial expression, to expression, and body.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception {
		myscope = SymbolTable.SYMBOL_TABLE.pushScope();
        initExpression.analyze(myscope);
        toExpression.analyze(myscope);
        SymbolTable.SYMBOL_TABLE.putLocalVariable(myscope, iteratorName);
		this.toexpr = SymbolTable.SYMBOL_TABLE.putAnonymousVariable(myscope);
		body.analyze(myscope);
		SymbolTable.SYMBOL_TABLE.popScope();
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
		instr.add(new IntInstruction(2)); // from and to values

		// store initial expression
		instr.addAll(initExpression.generate(myscope));
		var itersymbol = SymbolTable.SYMBOL_TABLE.getSymbolLocalOnly(myscope, this.iteratorName);
		instr.add(new StoreInstruction(0, itersymbol.address()));

		// store ending barrier expression
		instr.addAll(toExpression.generate(myscope));
		instr.add(new StoreInstruction(0,toexpr.address()));

		var endlab = new LabelInstruction("forend");

		var startlabl = new LabelInstruction("forstart");
		instr.add(startlabl);

		// check loop end
		instr.add(new LodInstruction(itersymbol));
		instr.add(new LodInstruction(toexpr));
		instr.add(new OperationInstruction(OperationPar.OPS.LES));
		// end if !(curloop < endexpr)
		instr.add(new CondJumpInstruction(endlab.getPar()));

		// body comes only after the first check
		instr.addAll(body.generate(myscope));

		// increment loop counter
		instr.add(new LodInstruction(itersymbol));
		instr.add(new LitInstruction(1));
		instr.add(new OperationInstruction(OperationPar.OPS.ADD));
		instr.add(new StoreInstruction(0, itersymbol.address()));


		// always jump to start after first init since that's where the check for ending the loop is
		instr.add(new JumpInstruction(startlabl.getPar()));

		instr.add(endlab);

        return instr;
    }
}
