package cz.zcu.ast.nodes;

public interface IAstNode {

    /**
     * Analyzes the AST node within the given scope.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    public abstract void analyze(Object scope) throws Exception;

    /**
     * Generates code for the AST node within the given scope using the provided code generator.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    public abstract void generate(Object scope, Object generator);

}
