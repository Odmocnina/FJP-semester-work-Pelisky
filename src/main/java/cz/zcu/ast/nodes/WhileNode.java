package cz.zcu.ast.nodes;

public class WhileNode implements IAstNode {

    /** condition for while **/
    public IAstNode condition;

    /** commands in the while **/
    public IAstNode body;


    public WhileNode(IAstNode condition, IAstNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void analyze(Object scope) throws Exception {
        condition.analyze(scope); // check if condition is ok
        body.analyze(scope);     // check if the code inside is ok
    }

    @Override
    public void generate(Object scope, Object generator) {

    }
}