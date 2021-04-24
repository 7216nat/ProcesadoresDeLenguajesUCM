package tiny1.errors;

import java.util.List;

import tiny0.analizadorlexico.CL;
import tiny1.analizadorlexico.UnidadLexica;

public class GestionErroresTiny {
    public void errorLexico(int fila, int col, String lexema) {
            System.out.println("ERROR fila "+fila+","+col+": Caracter inexperado: " +lexema); 
            System.exit(1);
    }  
    public void errorSintactico(UnidadLexica UL) {
            System.err.println("ERROR de sintaxis fila "+UL.fila()+", columna "+UL.columna()+": Encontrada clase " + UL.sym);
            System.exit(1);
    }
    public void errorFatal(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(1);
    }
}   

