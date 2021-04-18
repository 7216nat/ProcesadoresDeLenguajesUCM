package tiny1.analizadorlexico;

public class AlexOperations {
    private AnalizadorLexicoTiny alex;
    public AlexOperations(AnalizadorLexicoTiny alex) {
        this.alex = alex;   
    }
    public UnidadLexica unidadId() {
        return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.IDEN,alex.lexema()); 
    } 
    public UnidadLexica unidadEnt() {
        return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.ENT,alex.lexema()); 
    } 
    public UnidadLexica unidadLReal() {
        return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LREAL,alex.lexema()); 
    } 
    public UnidadLexica unidadCadena() {
        return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.CADENA,alex.lexema()); 
    } 

    public UnidadLexica unidadInt() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.INT); 
    }
    public UnidadLexica unidadString() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.STRING); 
    }
    public UnidadLexica unidadBool() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.BOOL); 
    }
    public UnidadLexica unidadTrue() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TRUE); 
    }
    public UnidadLexica unidadFalse() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FALSE); 
    }
    public UnidadLexica unidadReal() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.REAL); 
    }
    public UnidadLexica unidadAnd() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AND); 
    }
    public UnidadLexica unidadOr() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OR); 
    }
    public UnidadLexica unidadNot() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NOT); 
    }
    public UnidadLexica unidadNull() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NULL); 
    }
    public UnidadLexica unidadProc() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PROC); 
    }
    public UnidadLexica unidadIf() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IF); 
    }
    public UnidadLexica unidadThen() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.THEN); 
    }
    public UnidadLexica unidadElse() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ELSE); 
    }
    public UnidadLexica unidadEndif() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ENDIF); 
    }
    public UnidadLexica unidadWhile() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WHILE); 
    }
    public UnidadLexica unidadDo() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DO); 
    }
    public UnidadLexica unidadEndwhile() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ENDWHILE); 
    }
    public UnidadLexica unidadCall() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CALL); 
    }
    public UnidadLexica unidadRecord() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.RECORD); 
    }
    public UnidadLexica unidadArr() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ARR); 
    }
    public UnidadLexica unidadOf() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OF); 
    }
    public UnidadLexica unidadPtr() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PTR); 
    }
    public UnidadLexica unidadNew() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NEW); 
    }
    public UnidadLexica unidadDel() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DEL); 
    }
    public UnidadLexica unidadRead() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.READ); 
    }
    public UnidadLexica unidadWrite() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WRITE); 
    }
    public UnidadLexica unidadNl() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL); 
    }
    public UnidadLexica unidadVar() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.VAR); 
    }

    public UnidadLexica unidadIgual() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IGUAL); 
    } 
    public UnidadLexica unidadSuma() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAS); 
    } 
    public UnidadLexica unidadResta() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENOS); 
    } 
    public UnidadLexica unidadMul() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.POR); 
    } 
    public UnidadLexica unidadDiv() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DIV); 
    }
    public UnidadLexica unidadMod() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MOD); 
    }
    public UnidadLexica unidadGT() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.GT); 
    }
    public UnidadLexica unidadGE() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.GE); 
    }   
    public UnidadLexica unidadLT() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LT); 
    }   
    public UnidadLexica unidadLE() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LE); 
    }   
    public UnidadLexica unidadEQ() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EQ); 
    }   
    public UnidadLexica unidadNE() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NE); 
    }      

    public UnidadLexica unidadPAp() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PAP); 
    } 
    public UnidadLexica unidadPCie() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCIE); 
    }
    public UnidadLexica unidadLlAp() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LLAP); 
    } 
    public UnidadLexica unidadLlCie() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LLCIE); 
    }
    public UnidadLexica unidadCAp() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CAP); 
    } 
    public UnidadLexica unidadCCie() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CCIE); 
    }

    public UnidadLexica unidadComa() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.COMA); 
    }
    public UnidadLexica unidadPunto() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTO); 
    } 
    public UnidadLexica unidadPuntocoma() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTOCOMA); 
    } 
    public UnidadLexica unidadAmp() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AMP); 
    } 
    public UnidadLexica unidadFlecha() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FLECHA); 
    }  

    public UnidadLexica unidadSepseccion() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SEPSECCION); 
    }
    public UnidadLexica unidadEof() {
        return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF); 
    }
    public void error() {
        System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
    }
}
