package cz.zcu.ast;

import cz.zcu.CustomLangBaseVisitor;
import cz.zcu.CustomLangParser;
import cz.zcu.ast.nodes.*;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public IAstNode visitProgram(CustomLangParser.ProgramContext ctx) {
        // gets the name of the program
        String programName = ctx.ID().getText();

        // proceses the code block
        IAstNode codeBlock = visit(ctx.codeBlock());

        return new ProgramNode(programName, codeBlock);
    }

    @Override
    public IAstNode visitCodeBlock(CustomLangParser.CodeBlockContext ctx) {
        return visit(ctx.block()); // skip the code word and go straight to the code inside bracets
    }

    @Override
    public IAstNode visitIdStat(CustomLangParser.IdStatContext ctx) {
        String identifierName = ctx.ID().getText();

        // if followed bz (), its calling of subprogram
        if (ctx.callTail() != null) {
            List<IAstNode> arguments = new ArrayList<>();

            // if there are some paramterers in ()
            if (ctx.callTail().argList() != null) {
                // go over all parameters seprated by , and process them
                for (CustomLangParser.ExprContext exprCtx : ctx.callTail().argList().expr()) {
                    arguments.add(visit(exprCtx));
                }
            }
            return new CallNode(identifierName, arguments);
        }

        return null;
    }


    @Override
    public IAstNode visitStrLitExpr(CustomLangParser.StrLitExprContext ctx) {
        // get text from parser
        String textWithQuotes = ctx.STRING_LITERAL().getText();

        // remove the start end ending bracets just the string inside
        String cleanText = textWithQuotes;
        if (cleanText.startsWith("\"") && cleanText.endsWith("\"") && cleanText.length() >= 2) {
            cleanText = cleanText.substring(1, cleanText.length() - 1);
        }

        return new StringNode(cleanText);
    }
}