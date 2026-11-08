package cz.zcu.ast.nodes;

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
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    void analyze(Object scope) throws Exception;

    /**
     * Generates code for the AST node within the given scope using the provided code generator.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    void generate(Object scope, Object generator);

}
