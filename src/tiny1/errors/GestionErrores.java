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
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Declaracion no declarada: "+str.toString()); 
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
    public static void errorIntruccionConElementosInadecuados(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Instruccion error: "+str.toString()); 
    }
    public static void errorExpresionNoDesignador(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Instruccion error, expresion no designador: "+str.toString()); 
    }
    public static void errorBorradoMemoriaDinamicaEjecucion(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Instruccion error, no es posible borrar memoria din�mica: "+str.toString()); 
    }        
    public static void errorAsignacionIncompatible(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": asignacion incompatible: "+str.toString()); 
    }
    public static void errorExpresionTipoInadecuado(StringLocalizado str){
        System.err.println("ERROR fila "+str.fila()+ ", columna "+ str.col()+": Tipo expresion no esperada: "+str.toString()); 
    }
    public static void errorDeclaracionesInadecuadas(){
        System.err.println("Declaraciones erroneas !!!"); 
    }           
}   
