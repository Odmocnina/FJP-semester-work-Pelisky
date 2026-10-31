package cz.zcu.symboltable;

/**
 * Primitivni ukazka, jak by mohly vypadat zaznamy v tabulce symbolu. {@link SymbolTable}
 *
 * @param level      Uroven zanoreni symbolu.
 * @param identifier Identifikator symbolu (tzn. jmeno)
 * @param type       Druh symbolu, dostupne typy zde - {@link SymbolType}
 */
public record Symbol(int level, String identifier, SymbolType type) {

	public enum SymbolType {
		VARIABLE, FUNCTION
	}
}
