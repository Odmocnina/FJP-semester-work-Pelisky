package cz.zcu.ast.nodes;

/********************************************
 * Represents a boolean node in the abstract syntax tree (AST).
 * This node contains a boolean value.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class BooleanNode implements IAstNode {

    /** the boolean value of the BooleanNode **/
    public boolean value;

    /**
     * Creates a new BooleanNode with the given boolean value.
     *
     * @param value the boolean value for this node
     */
    public BooleanNode(boolean value) {
        this.value = value;
    }

    /**
     * Analyzes the boolean node.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    public void analyze(Object scope) throws Exception {

    }

    /**
     * Generates code for the boolean node.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    public void generate(Object scope, Object generator) {

    }
}
