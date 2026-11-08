package cz.zcu.ast.nodes;

/********************************************
 * Represents an identification node in the abstract syntax tree (AST).
 * This node contains the name of a variable or function.
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ********************************************/
public class IdentificationNode implements IAstNode {

    /** name of variable/function **/
    public String name;

    /**
     * Creates a new identification node with the given name.
     *
     * @param name the name of the variable or function
     **/
    public IdentificationNode(String name) {
        this.name = name;
    }

    /**
     * Analyzes the identification node by checking if the variable or function with the given name exists in the
     * current scope.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(Object scope) throws Exception {

    }

    /**
     * Generates code for the identification node. Since an identification node does not have any specific code
     * generation requirements, this method is empty.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(Object scope, Object generator) {
    }

}
