package cz.zcu.ast;

import cz.zcu.CustomLangBaseVisitor;
import cz.zcu.CustomLangParser;
import cz.zcu.ast.nodes.*;

public class ASTBuilder extends CustomLangBaseVisitor<IAstNode> {

    @Override
    public IAstNode visitBlock(CustomLangParser.BlockContext ctx) {

        BlockNode blockNode = new BlockNode();

        for (CustomLangParser.StatementContext statCtx : ctx.statement()) {

            IAstNode statement = visit(statCtx);

            if (statement != null) {
                blockNode.statements.add(statement);
            }
        }

        return blockNode;
    }

    @Override
    public IAstNode visitVarDecl(CustomLangParser.VarDeclContext ctx) {
        String type = ctx.type().getText();
        String name = ctx.ID().getText();

        IAstNode expression = null;

        if (ctx.expr() != null) {
            expression = visit(ctx.expr());
        }

        return new VarDeclNode(type, name, expression);
    }
}