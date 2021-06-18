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
    public static void errorDimensionInadecuada(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Dimension array inadecuada: "+str.toString()); 
    }
    public static void errorVinculacionDeclaracionTipoInadecuado(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Vinculacion a una declaracion inadecuada: "+str.toString()); 
    }
    public static void errorCampoRegistroDuplicado(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Campo de registro duplicado: "+str.toString()); 
    }
    public static void errorParametrosNoCoinciden(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Parametros error: "+str.toString()); 
    }         
}   

