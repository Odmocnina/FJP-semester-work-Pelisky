package cz.zcu;

import cz.zcu.ast.ASTBuilder;
import cz.zcu.ast.nodes.IAstNode;
import cz.zcu.codegen.CodeGenerator;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.TreeViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.Arrays;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    /** logger used for logging **/
    private static final Logger logger = LogManager.getLogger();

    public static class ThrowingErrorListener extends BaseErrorListener {
        public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            // Vyhození výjimky s přesným řádkem, pozicí a popisem chyby
            throw new ParseCancellationException("Error on line: " + line + " (character " + charPositionInLine + "): " + msg);
        }
    }

    public static void main(String[] args) throws Exception {
        // create a stream of the code from our code file
        CharStream input = CharStreams.fromString("""
				Program HelloWorld;
				
				Code {
					var int a := 1;
					// A -> Z+1
				    for (x : 65 -> 91) do {
				    	var int b := +(a,1);
				    	WriteLn(x);
				    	WriteLn(10);
				    	WriteLn(+(a,x));
				    	WriteLn(10);
				    	WriteLn(+(x,b));
				    	WriteLn(10);
				    }
				}""");

        // make a tokes from our input code
        CustomLangLexer lexer = new CustomLangLexer(input);

        // remove error for our error handling
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CustomLangParser parser = new CustomLangParser(tokens);

        // remove error for our error handling
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        try {
            logger.info("Starting to parse...");
            // create the tree from our code
            ParseTree tree = parser.program();

            // any visitor can be attached like so
            var visitor = new ASTBuilder();
            IAstNode mytree = visitor.visit(tree);

            // print the tree
            logger.info("Parsing finished!");
            logger.debug(tree.toStringTree());

            // === grafic showing of the tree ===
            JFrame frame = new JFrame("Syntactic tree - CustomLang");
            JPanel panel = new JPanel();
            TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
            viewer.setScale(1.2);
            panel.add(viewer);
            frame.add(new JScrollPane(panel));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);


			CodeGenerator codegen = new CodeGenerator();
			var code = codegen.compile(mytree);
			System.out.println(code);


		} catch (ParseCancellationException e) {
            logger.error("Compilation failed: {}", e.getMessage());
        }
    }
}