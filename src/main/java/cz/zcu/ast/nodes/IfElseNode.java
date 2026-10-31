package cz.zcu.ast.nodes;

public class IfElseNode implements IAstNode {

    /** condition for if **/
    public IAstNode condition;

    /** commands in if **/
    public IAstNode thenBlock;

    /** code in else. can be null **/
    public IAstNode elseBlock;

    public IfElseNode(IAstNode condition, IAstNode thenBlock, IAstNode elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public void analyze(Object scope) throws Exception {
        condition.analyze(scope);
        thenBlock.analyze(scope);
        if (elseBlock != null) {
            elseBlock.analyze(scope);
        }
    }

    @Override
    public void generate(Object scope, Object generator) {

    }
}