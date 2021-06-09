package tiny1.errors;


import tiny1.analizadorlexico.UnidadLexica;
import tiny1.asint.TinyASint.StringLocalizado;

public class GestionErrores {
    public static void errorSintactico(UnidadLexica UL) {
        System.err.println("ERROR fila "+UL.lexema().fila()+","+UL.lexema().col()+
            ": Elemento inexperado "+UL.value);
        System.exit(1);
    }
    public static void errorLexico(int fila, String lexema) {
        System.err.println("ERROR fila "+fila+": Caracter inexperado: "+lexema); 
        System.exit(1);
    }
    public static void errorVinculacionTipoInexistennte(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Declacion de tipo no declarada: "+str.toString()); 
    }
    public static void errorVinculacionVariableInexistennte(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Variable no declarada: "+str.toString()); 
    }
    public static void errorVinculacionDuplicado(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Declaracion duplicada: "+str.toString()); 
    }  
}   

