grammar CustomLang;

// ==========================================
// PARSER (Syntactic rules)
// ==========================================


// this says where is the code file
@header {
    package cz.zcu;
}

// ============================= program definition ======================
// this is the name of the code at the top of the file, const procedure and functions block are not mandatory, code is
program
    : PROGRAM ID SEMI constBlock? procBlock? funcBlock? codeBlock EOF
    ;

// ==================================================================
// ========================== Blocks =================================
// block for constants multiple constants or not any, LBRACE and RBRACE are {}
constBlock
    : CONST LBRACE constDecl* RBRACE
    ;

constDecl
    : type ID ASSIGN expr SEMI
    ;

procBlock
    : PROCEDURES LBRACE procDecl* RBRACE
    ;

// LPAREN and RPAREN are the ()
procDecl
    : ID LPAREN paramList? RPAREN block
    ;

funcBlock
    : FUNCTIONS LBRACE funcDecl* RBRACE
    ;

funcDecl
    : ID LPAREN paramList? RPAREN COLON type block
    ;

codeBlock
    : CODE block
    ;

block
    : LBRACE statement* RBRACE
    ;

// ========================================================================
// ================================ Parameters ============================

paramList
    : param (COMMA param)*
    ;

param
    : ID COLON type
    ;

// ========================================================================
// ============================== Commands ================================

statement
    : varDecl		# declStatement		// assigment and declaration of new
    | idStat		# idStatement		// assigment of existing and calling of function
    | forStat		#forStatement		// for cycle
    | whileStat		#whileStatement		// while cycle
    | doWhileStat	#doWhileStatement	// do while cycle
    | repeatStat	#repeatStatement	// repeat cycle
    | ifStat		#ifStatement		// if condition
    | returnStat	#returnStatement	// return from fnc
    ;

varDecl
    : VAR type ID ( (ASSIGN) expr )? SEMI
    ;

// ============ this is so that there isn't a collision in callStat =====================
idStat
    : ID ( assignTail | callTail )
    ;

assignTail
    : ASSIGN expr SEMI
    ;

callTail
    : LPAREN argList? RPAREN SEMI
    ;

// ==============================================================================

assignStat
    : ID (ASSIGN | EQUAL) expr SEMI
    ;

// cycles

forInit
    : ID (ASSIGN | EQUAL) expr
    ;

forStat
    : FOR LPAREN forInit TO expr RPAREN DO block
    ;

whileStat
    : WHILE LPAREN expr RPAREN DO block
    ;

doWhileStat
    : DO block WHILE LPAREN expr RPAREN SEMI
    ;

repeatStat
    : REPEAT block UNTIL LPAREN expr RPAREN SEMI
    ;

// ---------------

ifStat
    : IF LPAREN expr RPAREN THEN block (ELSE block)?
    ;

returnStat
    : RETURN expr SEMI
    ;

argList
    : expr (COMMA expr)*
    ;

type
    : INT_TYPE | BOOL_TYPE | REAL_TYPE
    ;

// expressions
expr
    : prefixOp LPAREN expr COMMA expr RPAREN	#prefixExpr			// Binary op +(2, 2) or <(a, b)
    | NEGATION LPAREN expr RPAREN				#negationExpr		// negation
    | ID ( LPAREN argList? RPAREN )?   			#callExpr			// call of function or a variable: myFunc(1, 2), its united so that it doesnt do collisions
    | MINUS ( LPAREN expr COMMA expr RPAREN | expr )  #minusExpr	// minus: -5 or -+(2, 2)
    | INT_LITERAL								#intLitExpr			// numbers, text, booleans
    | REAL_LITERAL								#realLitExpr
    | STRING_LITERAL 							#strLitExpr
    | BOOL_LITERAL 								#boolLitExpr
    ;

prefixOp
    : PLUS | MUL | DIV | MOD | compareOp
    ;

compareOp
    : LESS | GREATER | LESS_EQ | GREATER_EQ | NEQ_OP | EQUAL
    ;

// ==========================================
// LEXER (lexical rules), keywords
// ==========================================

PROGRAM    : 'Program' ;
CONST      : 'Const' ;
PROCEDURES : 'Procedures' ;
FUNCTIONS  : 'Functions' ;
CODE       : 'Code' ;
VAR        : 'var' ;
RETURN     : 'return' ;
FOR        : 'for' ;
TO         : 'to' ;
DO         : 'do' ;
WHILE      : 'while' ;
REPEAT     : 'repeat' ;
UNTIL      : 'until' ;
IF         : 'if' ;
THEN       : 'then' ;
ELSE       : 'else' ;

// Data types
INT_TYPE   : 'int';
BOOL_TYPE  : 'bool';
REAL_TYPE  : 'real';

// values of the types
BOOL_LITERAL   : 'true' | 'false' ;
INT_LITERAL    : [0-9]+ ;
REAL_LITERAL   : [0-9]+ '.' [0-9]+ ;
STRING_LITERAL : '"' ~["]* '"' ; 


// arithmetic operators
PLUS       : '+' ;
MINUS      : '-' ;
MUL        : '*' ;
DIV        : '/' ;
MOD        : '%' ;

// comparison operators
EQUAL      : '=' ;
LESS       : '<' ;
GREATER    : '>' ;
LESS_EQ    : '<=' ;
GREATER_EQ : '>=' ;
NEQ_OP     : '!=' ;

// logical operators
AND        : '&' ;
OR         : '|' ;
NEGATION   : '!' ;

// rest
ASSIGN     : ':=' ;

// wrapping and ending of the commands
LPAREN : '(' ;
RPAREN : ')' ;
LBRACE : '{' ;
RBRACE : '}' ;
SEMI   : ';' ;
COLON  : ':' ;
COMMA  : ',' ;

// Identifiers, names of variables/functions...
ID : [a-zA-Z_][a-zA-Z0-9_]* ;

// ignoring of the whitespace and comments
WS            : [ \t\r\n]+ -> skip ;
LINE_COMMENT  : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;