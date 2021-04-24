package tiny1.analizadorsintactico.asc;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny1.analizadorlexico.AnalizadorLexicoTiny;

public class Main {
    public static void main(String[] args) throws Exception {
    	
        Reader input = new InputStreamReader(new FileInputStream("input.txt"));
        
        AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
        AnalizadorSintacticoAsc as = new AnalizadorSintacticoAsc(al);
        as.parse();
        System.out.println("Parseo finalizado sin errores");
    }  
}
