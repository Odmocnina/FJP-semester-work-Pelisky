package cz.zcu.ast.nodes;

public class VarDeclNode implements IAstNode {

    /** type of the variable **/
    public String type;

    /** name of the variable **/
    public String name;

    /** assigment of the variable, can be null **/
    public IAstNode expression;

    /**
     * Create new node for declaration of new variable
     *
     * @param type type of the variable
     * @param name name of the variable
     * @param expression assigment of the variable, can be null
     */
    public VarDeclNode(String type, String name, IAstNode expression) {
        this.type = type;
        this.name = name;
        this.expression = expression;
    }

    /**
     * Semantic analyzation
     **/
    @Override
    public void analyze(Object scope) throws Exception {
        throw new Exception("Not yet implemented.");
    }

    /**
     * Function for generation of the PL/0 assembler instructions
     *
     * @param scope
     * @param generator
     **/
    @Override
    public void generate(Object scope, Object generator) {

    }
}
