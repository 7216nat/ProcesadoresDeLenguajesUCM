package tiny1.analizadorsintactico;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny1.analizadorlexico.AnalizadorLexicoTiny;
import tiny1.analizadorsintactico.asc.AnalizadorSintacticoAsc;
import tiny1.analizadorsintactico.desc.AnalizadorSintacticoDesc;

public class Main {
    public static void main(String[] args) throws Exception {
        	
        Reader input = new InputStreamReader(new FileInputStream(args[0]));
        
        if(args[1].equals("asc")) {
        	System.out.println("Parseando el archivo " + args[0] + " con un analizador ascendente");
        	
        	AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
            AnalizadorSintacticoAsc as = new AnalizadorSintacticoAsc(al);
            as.parse();

            System.out.println("OK");
        }
        
        else if(args[1].equals("desc")) {
        	System.out.println("Parseando el archivo " + args[0] + " con un analizador descendente predictivo");
        
            AnalizadorSintacticoDesc as = new AnalizadorSintacticoDesc(input);
            as.Start();

            System.out.println("OK");
        }
        
        else {
        	System.out.println("Error en el comando, ejemplo: analizador.jar 'file1.txt' asc");
        }
        

    }  
}
