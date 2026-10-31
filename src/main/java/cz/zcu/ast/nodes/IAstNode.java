package cz.zcu.ast.nodes;

public interface IAstNode {

    public abstract void analyze(Object scope) throws Exception;

    public abstract void generate(Object scope, Object generator);

}
