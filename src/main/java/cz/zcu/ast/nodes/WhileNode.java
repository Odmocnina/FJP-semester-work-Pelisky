package cz.zcu.ast.nodes;

public class WhileNode implements IAstNode {

    /** condition for while **/
    public IAstNode condition;

    /** commands in the while **/
    public IAstNode body;

    /**
     * Creates a new while node with the given condition and body.
     *
     * @param condition condition for while cycle
     * @param body commands in the while cycle
     **/
    public WhileNode(IAstNode condition, IAstNode body) {
        this.condition = condition;
        this.body = body;
    }

    /**
     * Analyzes the while node by analyzing its condition and body.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(Object scope) throws Exception {
        condition.analyze(scope); // check if condition is ok
        body.analyze(scope);     // check if the code inside is ok
    }

    /**
     * Generates code for the while node by generating code for its condition and body.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(Object scope, Object generator) {

    }
}