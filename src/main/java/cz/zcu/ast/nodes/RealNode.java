package cz.zcu.ast.nodes;

public class RealNode implements IAstNode {

    /** the number value of the Real number **/
    public double value;

    /**
     * Creates a new RealNode with the given double value.
     *
     * @param value the double value for this node
     **/
    public RealNode(double value) {
        this.value = value;
    }

    /**
     * Analyzes the real node. Checks its validity in the current scope.
     *
     * @param scope the current scope for analysis
     * @throws Exception if an error occurs during analysis
     **/
    @Override
    public void analyze(Object scope) throws Exception {
    }

    /**
     * Generates code for the real node.
     *
     * @param scope the current scope for code generation
     * @param generator the code generator to use
     **/
    @Override
    public void generate(Object scope, Object generator) {
    }
}