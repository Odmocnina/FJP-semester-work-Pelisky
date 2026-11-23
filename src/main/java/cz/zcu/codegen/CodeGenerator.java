package cz.zcu.codegen;

import cz.zcu.CustomLangParser;
import cz.zcu.ast.nodes.BlockNode;
import cz.zcu.ast.nodes.IAstNode;
import cz.zcu.ast.nodes.ProgramNode;
import cz.zcu.symboltable.Symbol;
import cz.zcu.symboltable.SymbolTable;
import org.antlr.v4.codegen.model.decl.CodeBlock;

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
		}

		for (var statement : mainCode.statements) {
			System.out.println("Statement: " + statement.getClass().getCanonicalName());
		}
		return "";
	}
}
