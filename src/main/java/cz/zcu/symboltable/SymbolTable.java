package cz.zcu.symboltable;

import java.util.*;


// TODO: Chceme povolit, aby se mohly jmenovat napr. promenne a funkce stejne? Tedy ze by mohla existovat funkce "hruska" a zaroven promenna s nazvem "hruska"?
// ja jsem spis proti, akorat to vytvari nejasnosti - V

/**
 * Tabulka symbolu. Udrzuje v sobe momentalni stav zanoreni pomoci podtridy {@link Scope}.
 */
public class SymbolTable {
	// Potrebujeme vedet, v jake funkci, pripadne v jakem bloku, se nachazime - to zaobaluje Scope
	// a pak teprve muzeme jednoznacne identifikovat, ktery symbol chceme
	// TODO: mozna bychom se mohli vykaslat na tuhle toplevel mapu a rovnou presunout mapy dovnitr tridy Scope?
	private Map<Scope, Map<String, Symbol>> symbolTable = new HashMap<>();

	// pocatecni scope je logicky globalni
	private Scope currentScope = GLOBAL_SCOPE;

	private SymbolTable() {
		this.symbolTable.put(GLOBAL_SCOPE, new HashMap<>());
	}

	/**
	 * Zanoreni do vnitrniho scope/bloku. Tohle je verze pro pojmenovane scope, tzn. funkce a procedury.
	 *
	 * @param ID Libovolne unikatni ID, idealne tedy nazev funkce
	 * @return Nove vytvoreny {@link Scope} pro pozdejsi navigaci v tabulce.
	 */
	public Scope pushScope(String ID) {
		var newscope = new Scope(currentScope, ID);
		currentScope.children.add(newscope);
		this.currentScope = newscope;
		return newscope;
	}

	/**
	 * Verze {@code pushScope} pro anonymni scope jako jsou bloky
	 *
	 * @return Nove vytvoreny {@link Scope} pro pozdejsi navigaci v tabulce.
	 */
	public Scope pushScope() {
		return this.pushScope("AnonBlock");
	}

	/**
	 * Vyskoci o uroven vys. Zavolat vzdy kdyz vyskocime z bloku!
	 * Je OK volat i pokud uz jsme v nejvyssim scope.
	 *
	 * @return Vrati scope, ve kterem se tabulka nove nachazi.
	 */
	public Scope popScope() {
		if (this.currentScope == GLOBAL_SCOPE) {
			return GLOBAL_SCOPE;
		}
		this.currentScope = this.currentScope.parent;
		return currentScope;
	}

	/**
	 * Vrati {@link Symbol} z daneho scope. pripadne z rodicovskeho scope.
	 *
	 * @param scope Scope ve kterem zacit hledat a jehoz rodicovske scope budou take prohledavany
	 * @param ID    Identifikator symbolu, ktery hledame
	 * @return Bud nalezeny symbol, anebo {@code null}
	 */
	public Symbol getSymbol(Scope scope, String ID) {
		var tmpscope = scope;
		var scopesymbols = symbolTable.get(tmpscope);

		Symbol result = scopesymbols.get(ID);
		while (result == null) {
			// nic jsme nenasli
			if (tmpscope == GLOBAL_SCOPE) {
				break;
			} else {
				tmpscope = tmpscope.parent;
				scopesymbols = symbolTable.get(tmpscope);
				result = scopesymbols.get(ID);
			}
		}
		return result;
	}

	/**
	 * Ekvivalent funkce {@link getSymbol}, akorat nehleda v rodicovskych {@code Scope}. Bude neco najde rovnou, nebo nic.
	 *
	 * @param scope Scope ve kterem hledat
	 * @param ID    ID symbolu, ktery hledame
	 * @return Bud nalezeny symbol, nebo {@code null}
	 */
	public Symbol getSymbolLocal(Scope scope, String ID) {
		return this.symbolTable.get(scope).get(ID);
	}


	/**
	 * Globalni scope, sem jdou veskere constany, funkce, procedury, atd.
	 */
	public static final Scope GLOBAL_SCOPE = new Scope(null, "Global");

	/**
	 * Jednoducha trida pro vymezeni nejakeho scope. Slouzi jako prvni klic pro tabulku symbolu {@link SymbolTable}.
	 * Nemelo by byt treba se ji zabyvat mimo potreby tridy {@link SymbolTable}.
	 * Zamerne je nelze vytvaret externe, abych mohl pocitat s tim, ze vzdy budou zaznamenany v tabulce symbolu.
	 */
	public static class Scope {
		private final String funcname;
		private final Scope parent;
		private final List<Scope> children = new LinkedList<>();

		protected Scope(Scope parent, String funcname) {
			this.parent = parent;
			this.funcname = funcname;
		}

		public String getFuncname() {
			return funcname;
		}

		public Scope getParent() {
			return parent;
		}
	}
}
