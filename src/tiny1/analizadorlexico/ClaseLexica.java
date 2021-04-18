package tiny1.analizadorlexico;

public enum ClaseLexica {
    // 
    IDEN, ENT, LREAL, CADENA,
    // constantes literales
    INT, STRING, BOOL, TRUE, FALSE, REAL, AND, OR,
    NOT, NULL, PROC, IF, THEN, ELSE, ENDIF, 
    WHILE, DO, ENDWHILE, CALL, RECORD, ARR, OF,
    PTR, NEW, DEL, READ, WRITE, NL, VAR, EOF,
    // Operadores
    PAP, PCIE, LLAP, LLCIE, CAP, CCIE,
    
    COMA, PUNTO, PUNTOCOMA, FLECHA, AMP,
    
    IGUAL, MAS, MENOS, POR, DIV, MOD,
    GT, GE, LT, LE, EQ, NE,
    // otros
    SEPSECCION
}
