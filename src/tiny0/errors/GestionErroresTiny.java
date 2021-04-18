package tiny0.errors;

import java.util.List;

import tiny0.analizadorlexico.CL;

public class GestionErroresTiny {
    public void errorLexico(int fila, int col, String lexema) {
            System.out.println("ERROR fila "+fila+","+col+": Caracter inexperado: "+lexema); 
            System.exit(1);
    }  
    public void errorSintactico(int fila, int col, CL encontrada, CL ... esperadas) {
            System.out.print("ERROR fila "+fila+","+col+": Encontrado "+encontrada+" Se esperada: ");
            for(CL esperada: esperadas)
                    System.out.print(esperada+" ");
            System.out.println();
            System.exit(1);
    }
    public void errorFatal(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(1);
    }
}   

