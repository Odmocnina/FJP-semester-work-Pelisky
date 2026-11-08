package cz.zcu.ast.nodes;

/**************************************************
 * Class representing an assignment node in the abstract syntax tree (AST).
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 */
public class AssignNode implements IAstNode {

    /** The name of the variable being assigned to **/
    public String identifierName;

    /** The expression being assigned to the variable **/
    public IAstNode expression;

    /**
     * Creates a new assignment node with the given identifier name and expression.
     *
     * @param identifierName the name of the variable being assigned to
     * @param expression the expression being assigned to the variable
     **/
    public AssignNode(String identifierName, IAstNode expression) {
        this.identifierName = identifierName;
        this.expression = expression;
    }

    /**
     * Analyzes the assignment node by analyzing the expression on the right side of the assignment.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(Object scope) throws Exception {
        // check the validity of expression on the right side of the assignment
        expression.analyze(scope);
    }

    /**
     * Generates code for the assignment node by generating code for the expression on the right side of the assignment.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(Object scope, Object generator) {
        // generate code for the expression on the right side of the assignment
        expression.generate(scope, generator);
    }
}