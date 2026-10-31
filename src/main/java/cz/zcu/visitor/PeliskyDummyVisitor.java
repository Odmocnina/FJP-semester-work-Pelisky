package cz.zcu.visitor;

import cz.zcu.CustomLangBaseVisitor;
import cz.zcu.CustomLangParser;

/**
 * Dummy visitor that does just prints some debug statements.
 * For experimenting only.
 */
public class PeliskyDummyVisitor extends CustomLangBaseVisitor<Void> {
	@Override
	public Void visitPrefixExpr(CustomLangParser.PrefixExprContext ctx) {
		System.out.printf("Visiting a prefix expression!: '%s', with  arguments: '%s' and '%s'\n", ctx.prefixOp().getText(), ctx.expr(0), ctx.expr(1));
		return super.visitPrefixExpr(ctx);
	}

	@Override
	public Void visitIntLitExpr(CustomLangParser.IntLitExprContext ctx) {
		System.out.printf("Visited an integer literal of value: %d\n", Integer.parseInt(ctx.INT_LITERAL().toString()));
		return super.visitIntLitExpr(ctx);
	}
}
