package tiny1.analizadorsintactico.asc;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny1.analizadorlexico.AnalizadorLexico;
import tiny1.asint.Impresion;
import tiny1.asint.TinyASint.Prog;

public class Main {
    public static void main(String[] args) throws Exception {
    	
        Reader input = new InputStreamReader(new FileInputStream("input.txt"));
        
        AnalizadorLexico al = new AnalizadorLexico(input);
        AnalizadorSintacticoAsc as = new AnalizadorSintacticoAsc(al);
        Prog prog = (Prog)as.parse().value;
        prog.procesa(new Impresion());
        System.out.println("Parseo finalizado sin errores");
    }  
}
