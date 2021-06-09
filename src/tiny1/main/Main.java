package tiny1.main;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny1.analizadorlexico.AnalizadorLexico;
import tiny1.analizadorsintactico.asc.AnalizadorSintacticoAsc;
import tiny1.analizadorsintactico.desc.AnalizadorSintacticoDesc;
import tiny1.asint.Impresion;
import tiny1.asint.TinyASint.Prog;
import tiny1.comp_semantica_estatica.SimplificacionTipo;
import tiny1.comp_semantica_estatica.Vinculacion;

public class Main {
    public static void main(String[] args) throws Exception {
        	
    	if(args.length != 2){
    		
    		System.err.println("Error en el comando, ejemplo: analizador.jar 'file1.txt' asc");
    		
    	} else {
    		
            Reader input = new InputStreamReader(new FileInputStream(args[0]));
            boolean verbose = true;
            if(args[1].equals("asc")) {
            	System.out.println("Parseando el archivo " + args[0] + " con un analizador ascendente");
            	
            	AnalizadorLexico al = new AnalizadorLexico(input);
                AnalizadorSintacticoAsc as = new AnalizadorSintacticoAsc(al);
                Prog prog = (Prog)as.parse().value;
                //prog.procesa(new Impresion());
                prog.procesa(new Vinculacion(verbose));
                System.out.println("Parseo finalizado sin errores");
            }
            
            else if(args[1].equals("desc")) {
            	System.out.println("Parseando el archivo " + args[0] + " con un analizador descendente predictivo");
            
                AnalizadorSintacticoDesc as = new AnalizadorSintacticoDesc(input);
                Prog prog = as.Init();
                //prog.procesa(new Impresion());
                prog.procesa(new Vinculacion(verbose));
                
                prog.procesa(new SimplificacionTipo(verbose));
                System.out.println("Parseo finalizado sin errores");
            }
            
            else {
            	System.out.println("Error en el comando, ejemplo: analizador.jar 'file1.txt' asc");
            }
    		
    	}
    }  
}
