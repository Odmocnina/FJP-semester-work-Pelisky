package cz.zcu.ast.nodes;

/********************************************
 * Represents a for loop in the abstract syntax tree (AST).
 * This node contains the iterator variable name, initial expression, end expression, and the body of the loop.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class ForNode implements IAstNode {

    /** name of the variable **/
    public String iteratorName;

    /** start value **/
    public IAstNode initExpression;

    /** end value **/
    public IAstNode toExpression;

    /** commands under the for **/
    public IAstNode body;

    /**
     * Creates a new for node with the given iterator name, initial expression, to expression, and body.
     *
     * @param iteratorName the name of the iterator variable
     * @param initExpression the initial value expression for the iterator
     * @param toExpression the end value expression for the iterator
     * @param body the commands to execute within the for loop
     **/
    public ForNode(String iteratorName, IAstNode initExpression, IAstNode toExpression, IAstNode body) {
        this.iteratorName = iteratorName;
        this.initExpression = initExpression;
        this.toExpression = toExpression;
        this.body = body;
    }

    /**
     * Analyzes the for node by analyzing its initial expression, to expression, and body.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(Object scope) throws Exception {
        initExpression.analyze(scope);
        toExpression.analyze(scope);
        body.analyze(scope);
    }

    /**
     * Generates code for the for node by generating code for its initial expression, to expression, and body.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(Object scope, Object generator) {
    }
}
