package cz.zcu.ast.nodes;

public class ForNode implements IAstNode {

    /** name of the variable **/
    public String iteratorName;

    /** start value **/
    public IAstNode initExpression;

    /** end value **/
    public IAstNode toExpression;

    /** commands under the for **/
    public IAstNode body;

    public ForNode(String iteratorName, IAstNode initExpression, IAstNode toExpression, IAstNode body) {
        this.iteratorName = iteratorName;
        this.initExpression = initExpression;
        this.toExpression = toExpression;
        this.body = body;
    }

    @Override
    public void analyze(Object scope) throws Exception {
        initExpression.analyze(scope);
        toExpression.analyze(scope);
        body.analyze(scope);
    }

    @Override
    public void generate(Object scope, Object generator) {
    }
}
