package cz.zcu.codegen.instructions;

/******************************************
 * Class for representing LOD (load from memory) instruction
 *
 * @author Michael Hladky, Victor Verner
 * @version 1.0
 ******************************************/
public final class LodInstruction extends Instruction {

    /**
     * Constructor of the LOD instruction
     *
     * @param value on which value are we going to store
     **/
    public LodInstruction(int value) {
        super("LOD", 0, new NumeralPar(value));
    }
}
