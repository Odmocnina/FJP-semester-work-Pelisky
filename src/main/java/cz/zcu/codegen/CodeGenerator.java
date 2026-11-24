package cz.zcu.codegen;

import cz.zcu.ast.nodes.BlockNode;
import cz.zcu.ast.nodes.IAstNode;
import cz.zcu.ast.nodes.ProgramNode;
import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.IntInstruction;
import cz.zcu.codegen.instructions.LabelInstruction;
import cz.zcu.codegen.instructions.RetInstruction;
import cz.zcu.symboltable.SymbolTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**************************************************************
 * Class for representing the code generator that creates code from a given AST tree, by going over it, so far only
 * string output
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 **************************************************************/
public class CodeGenerator {

	private static final Logger logger = LogManager.getLogger();

	/**
	 * Method that goes over the AST and generates PL/0 instructions form Pelisky language
	 *
	 * @param root root node of the AST
	 * @return PL/0 instructions in string
	 **/
	public String compile(IAstNode root) {
		logger.info("Starting semantic analysis");

		// start and process of recursive decent when doing semantic analysis
		try {
			root.analyze(SymbolTable.GLOBAL_SCOPE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		logger.info("Semantic analysis finished");

		logger.debug("The top level element is: {}\n", root.getClass().getCanonicalName());
		if (!(root instanceof ProgramNode)) {
			logger.fatal("Top level statement is not declaration of program!");
			return "ERROR";
		}

        logger.info("The symbol table looks like this: {}", SymbolTable.SYMBOL_TABLE);

		BlockNode mainCode = (BlockNode) ((ProgramNode) root).codeBlock;
		if (mainCode.statements.isEmpty()) {
			logger.warn("Main block is empty");
			return "";
		} else {
			for (var statement : mainCode.statements) {
                logger.debug("Statement: {}", statement.getClass().getCanonicalName());
			}

			var allinst = new ArrayList<Instruction>();
			// preamble, TODO: can be merged with existing
			allinst.add(new IntInstruction(3));
			allinst.addAll(mainCode.generate(SymbolTable.GLOBAL_SCOPE));
			// main return
			allinst.add(new RetInstruction());

			var result = new StringBuilder();
			for (var instr: allinst) {
				result.append(emitInstruction(instr));
			}
			return result.toString();
		}
	}


	public String emitInstruction(Instruction ins) {
		return switch (ins) {
			case LabelInstruction l -> String.format("%s\n", l.getPar().getString());
			default -> String.format("%s %d %s\n", ins.getOpcode(), ins.getLevel(), ins.getPar().getString());
		};
	}
}