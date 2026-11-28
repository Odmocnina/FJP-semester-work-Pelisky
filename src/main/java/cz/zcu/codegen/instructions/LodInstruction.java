package cz.zcu.codegen.instructions;

import cz.zcu.symboltable.Symbol;

/******************************************
 * Class for representing LOD (load from memory) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class LodInstruction extends Instruction {
	/**
	 * Shorthand for creating from a {@link cz.zcu.symboltable.Symbol}
	 *
	 * @param symbol The symbol to get the address from
	 **/
	public LodInstruction(Symbol symbol) {
		this(symbol.address(), symbol.level());
	}

	/**
	 * Constructor of the LOD instruction
	 * Uses 0 as the level
	 *
	 * @param address Address that we are loading from
	 **/
	public LodInstruction(int address) {
		this(address, 0);
	}


	/**
	 * Constructor of the LOD instruction
	 *
	 * @param address Address that we are loading from
	 * @param level   Level to load from (typically 0 or -1 for function params)
	 **/
	public LodInstruction(int address, int level) {
		super("LOD", level, new NumeralPar(address));
	}
}
