package cz.zcu.codegen;

import cz.zcu.ast.nodes.BlockNode;
import cz.zcu.ast.nodes.IAstNode;
import cz.zcu.ast.nodes.ProgramNode;
import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.codegen.instructions.IntInstruction;
import cz.zcu.codegen.instructions.LabelInstruction;
import cz.zcu.codegen.instructions.RetInstruction;
import cz.zcu.symboltable.SymbolTable;

import java.util.ArrayList;

/**
 * Creates code from a given AST tree, so far only string output
 */
public class CodeGenerator {
	public String compile(IAstNode root) {
		System.out.println("Starting analysis");
		try {
			root.analyze(SymbolTable.GLOBAL_SCOPE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("Analysis done");

		System.out.printf("The top level element is: %s\n", root.getClass().getCanonicalName());
		if (!(root instanceof ProgramNode)) {
			System.out.println("Toplevel statement neni deklarace programu!");
			return "ERROR";
		}
		System.out.println("The symbol table looks like this: " + SymbolTable.SYMBOL_TABLE);

		BlockNode mainCode = (BlockNode) ((ProgramNode) root).codeBlock;
		if (mainCode.statements.isEmpty()) {
			System.out.println("Block Main je prazdny");
			return "";
		} else {
			for (var statement : mainCode.statements) {
				System.out.println("Statement: " + statement.getClass().getCanonicalName());
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