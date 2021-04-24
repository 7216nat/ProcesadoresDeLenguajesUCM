package tiny1.analizadorlexico;

import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

public abstract class UnidadLexica extends Symbol{
    private int fila;
    private int columna;
    protected UnidadLexica(int fila, int columna, int clase, String lexema) {
    	super(clase, lexema);
        this.fila = fila;
        this.columna = columna;
    }
    public int clase () {return sym;}
    public abstract String lexema();
    public int fila() {return fila;}
    public int columna() {return columna;}
}
