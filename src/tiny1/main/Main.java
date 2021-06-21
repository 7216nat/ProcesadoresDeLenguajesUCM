package tiny1.main;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny1.analizadorlexico.AnalizadorLexico;
import tiny1.analizadorsintactico.asc.AnalizadorSintacticoAsc;
import tiny1.analizadorsintactico.desc.AnalizadorSintacticoDesc;
import tiny1.asint.Impresion;
import tiny1.asint.TinyASint.Prog;
import tiny1.comp_semantica_estatica.Comprobacion;
import tiny1.comp_semantica_estatica.SimplificacionTipo;
import tiny1.comp_semantica_estatica.Vinculacion;
import tiny1.generacionCodigo.AsignacionEspacio;
import tiny1.generacionCodigo.Etiquetado;
import tiny1.generacionCodigo.Traduccion;
import tiny1.pmaquinaP.MaquinaP;

public class Main {
    public static void main(String[] args) throws Exception {
        	
    	if(args.length != 2){
    		
    		System.err.println("Error en el comando, ejemplo: analizador.jar 'file1.txt' asc");
    		
    	} else {
    		
            Reader input = new InputStreamReader(new FileInputStream(args[0]));
            
            boolean verbose = true;
            
            Vinculacion vinc = new Vinculacion(verbose);
            SimplificacionTipo simp = new SimplificacionTipo(verbose);
            Comprobacion comp = new Comprobacion(verbose);
            AsignacionEspacio asig = new AsignacionEspacio(verbose);
            Etiquetado etiq = new Etiquetado(verbose);
       
            
            Prog prog = null;
            
            if(args[1].equals("asc")) {
            	System.out.println("Parseando el archivo " + args[0] + " con un analizador ascendente");
            	
            	AnalizadorLexico al = new AnalizadorLexico(input);
                AnalizadorSintacticoAsc as = new AnalizadorSintacticoAsc(al);
                prog = (Prog)as.parse().value;
               
            }
            
            else if(args[1].equals("desc")) {
            	System.out.println("Parseando el archivo " + args[0] + " con un analizador descendente predictivo");
            
                AnalizadorSintacticoDesc as = new AnalizadorSintacticoDesc(input);
                prog = as.Init();
            }
            
            else {
            	System.out.println("Error en el comando, ejemplo: analizador.jar 'file1.txt' asc");
            	System.exit(-1);
            }
            
            prog.procesa(new Impresion());
            
            prog.procesa(vinc);
            if (!vinc.isOk()){
                System.out.println("Error Vinculacion.");
                System.exit(-1);
            }
            
            prog.procesa(simp);
            
            prog.procesa(comp);
            if (!comp.isOk()){
                System.out.println("Error Comprobacion.");
                System.exit(-1);
            }
            
            prog.procesa(asig);
            prog.procesa(etiq);
            
            
            Traduccion trad = new Traduccion(new MaquinaP(asig.getStaticMem(),10,10,vinc.getMaxAnid() + 1, verbose), verbose);
            
            prog.procesa(trad);
            
            trad.getMaquinaP().ponInstruccion(trad.getMaquinaP().stop());
            trad.getMaquinaP().muestraCodigo();
            trad.getMaquinaP().ejecuta();
            trad.getMaquinaP().muestraEstado();
            
            System.out.println("Parseo finalizado sin errores");
    	}
    }  
}
