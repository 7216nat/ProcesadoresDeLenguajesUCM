package tiny1.errors;


import tiny1.analizadorlexico.UnidadLexica;

public class GestionErrores {
    public void errorSintactico(UnidadLexica UL) {
        System.err.println("ERROR fila "+UL.lexema().fila()+","+UL.lexema().col()+
            ": Elemento inexperado "+UL.value);
        System.exit(1);
    }
    public void errorLexico(int fila, String lexema) {
        System.err.println("ERROR fila "+fila+": Caracter inexperado: "+lexema); 
        System.exit(1);
    }  
}   

