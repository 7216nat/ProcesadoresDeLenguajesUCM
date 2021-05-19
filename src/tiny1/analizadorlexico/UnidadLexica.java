package tiny1.analizadorlexico;

import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
import tiny1.asint.TinyASint.StringLocalizado;

public class UnidadLexica extends Symbol{
    public UnidadLexica(int fila, int col, int clase, String lexema) {
        super(clase,null);
        value = new StringLocalizado(lexema,fila,col);
    }
    public int clase () {return sym;}
    public StringLocalizado lexema() {return (StringLocalizado)value;}
    public String toString() {
        StringLocalizado lexema = (StringLocalizado)value;
        return "[clase="+clase2string(sym)+",lexema="+lexema+",fila="+lexema.fila()+",col="+lexema.col()+"]";
    }
    private String clase2string(int clase) {
        switch(clase) {
            case ClaseLexica.INT: return "INT";
            case ClaseLexica.REAL: return "REAL";
            case ClaseLexica.BOOL: return "BOOL";  
            case ClaseLexica.TRUE: return "TRUE";
            case ClaseLexica.FALSE: return "FALSE";
            case ClaseLexica.AND: return "AND";
            case ClaseLexica.OR: return "OR";
            case ClaseLexica.NOT: return "NOT";
            case ClaseLexica.STRING: return "STRING";
            case ClaseLexica.NULL: return "NULL";
            case ClaseLexica.PROC: return "PROC";
            case ClaseLexica.IF: return "IF";
            case ClaseLexica.THEN: return "THEN";
            case ClaseLexica.ELSE: return "ELSE";
            case ClaseLexica.ENDIF: return "ENDIF";
            case ClaseLexica.WHILE: return "WHILE";
            case ClaseLexica.DO: return "DO";
            case ClaseLexica.ENDWHILE: return "ENDWHILE";
            case ClaseLexica.CALL: return "CALL";
            case ClaseLexica.TYPE: return "TYPE";
            case ClaseLexica.RECORD: return "RECORD";
            case ClaseLexica.ARR: return "ARRAY";
            case ClaseLexica.OF: return "OF";
            case ClaseLexica.PTR: return "PTR";
            case ClaseLexica.NEW: return "NEW";
            case ClaseLexica.DEL: return "DEL";
            case ClaseLexica.READ: return "READ";
            case ClaseLexica.WRITE: return "WRITE";
            case ClaseLexica.NL: return "NL";
            case ClaseLexica.VAR: return "VAR";
            case ClaseLexica.IDEN: return "IDEN";
            case ClaseLexica.ENT: return "ENT";
            case ClaseLexica.LREAL: return "LREAL";
            case ClaseLexica.CADENA: return "CADENA";
            case ClaseLexica.IGUAL: return "IGUAL";
            case ClaseLexica.MAS: return "MAS";
            case ClaseLexica.MENOS: return "MENOS";
            case ClaseLexica.POR: return "POR";
            case ClaseLexica.DIV: return "DIV";
            case ClaseLexica.MOD: return "MOD";
            case ClaseLexica.GT: return "GT";
            case ClaseLexica.GE: return "GE";
            case ClaseLexica.LT: return "LT";
            case ClaseLexica.LE: return "LE";
            case ClaseLexica.EQ: return "EQ";
            case ClaseLexica.NE: return "NE";
            case ClaseLexica.PAP: return "PAP";
            case ClaseLexica.PCIE: return "PCIE";
            case ClaseLexica.LLAP: return "LLAP";
            case ClaseLexica.LLCIE: return "LLCIE";
            case ClaseLexica.CAP: return "CAP";
            case ClaseLexica.CCIE: return "CCIE";
            case ClaseLexica.COMA: return "COMA";
            case ClaseLexica.PUNTO: return "PUNTO";
            case ClaseLexica.PUNTOCOMA: return "PUNTOCOMA";
            case ClaseLexica.FLECHA: return "FLECHA";
            case ClaseLexica.AMP: return "AMP";
            case ClaseLexica.SEPSECCION: return "SEPSECCION";
            default: return "?";               
        }
    }
}
