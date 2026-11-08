package cz.zcu.ast.nodes;

/********************************************
 * Represents a string node in the abstract syntax tree (AST).
 * This node contains a string value.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class StringNode implements IAstNode {

    /** the string value of this node **/
    public String value;

    /**
     * Creates a new string node with the given value.
     *
     * @param value the string value for this node
     **/
    public StringNode(String value) {
        this.value = value;
    }

    /**
     * Analyzes the string node. Since a string node does not have any specific analysis requirements, this method is
     * empty.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(Object scope) throws Exception {

    }

    /**
     * Generates code for the string node. Since a string node does not have any specific code generation requirements,
     * this method is empty.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(Object scope, Object generator) {

    }
}