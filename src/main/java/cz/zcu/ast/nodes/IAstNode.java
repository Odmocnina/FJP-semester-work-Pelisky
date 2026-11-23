package cz.zcu.ast.nodes;

import cz.zcu.codegen.instructions.Instruction;
import cz.zcu.symboltable.SymbolTable;

import java.util.List;

/*********************************************
 * Interface representing a node in the abstract syntax tree (AST). Every node in the AST should implement this
 * interface to provide methods for semantic analysis and code generation.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public interface IAstNode {

    /**
     * Analyzes the AST node within the given scope.
     *
     * @param tableOfCurrentScope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    void analyze(SymbolTable.Scope tableOfCurrentScope) throws Exception;

    /**
     * Generates code for the AST node within the given scope using the provided code generator.
     *
     * @param tableOfCurrentScope the current scope for code generation
     * @return List of {@link Instruction}s that represent the Node
     */
    List<Instruction> generate(SymbolTable.Scope tableOfCurrentScope);

}
