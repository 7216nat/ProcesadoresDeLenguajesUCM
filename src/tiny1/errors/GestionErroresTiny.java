package tiny1.errors;

import java.util.List;

import tiny0.analizadorlexico.CL;
import tiny1.analizadorlexico.UnidadLexica;

public class GestionErroresTiny {
    public void errorSintactico(UnidadLexica UL) {
            System.err.println("ERROR de sintaxis fila "+UL.fila()+", columna "+UL.columna()+": Encontrada clase " + UL.sym);
            System.exit(1);
    }
}   

