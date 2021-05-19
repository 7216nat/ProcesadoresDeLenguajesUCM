package tiny1.analizadorsintactico.desc;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny1.asint.Impresion;
import tiny1.asint.TinyASint.Prog;

public class Main {
    public static void main(String[] args) throws Exception {
    	
        Reader input = new InputStreamReader(new FileInputStream("input.txt"));
        
        AnalizadorSintacticoDesc as = new AnalizadorSintacticoDesc(input);
        Prog prog = as.Init();
        prog.procesa(new Impresion());
        System.out.println("Parseo finalizado sin errores");
    }  
}
