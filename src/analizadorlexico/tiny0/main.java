import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class main {

		
	public static void main(String args[]) throws Exception {
		
		String enteros = "123456789";
		String minusculas = "abcdefghijklmnopqrstuvwxyz";
		String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		Nodo nodoInicio = new Nodo(Estado.INICIO);		
		Nodo nodoPor = new Nodo(Estado.POR);
		Nodo nodoDiv = new Nodo(Estado.DIV);
		Nodo nodoPAp = new Nodo(Estado.PAP);
		Nodo nodoPCie = new Nodo(Estado.PCIE);
		Nodo nodoGT = new Nodo(Estado.GT);
		Nodo nodoGE = new Nodo(Estado.GE);
		Nodo nodoLT = new Nodo(Estado.LT);
		Nodo nodoLE = new Nodo(Estado.LE);
		Nodo nodoAsig = new Nodo(Estado.ASIG);
		Nodo nodoEq = new Nodo(Estado.EQ);
		Nodo nodoNE_1 = new Nodo(null);
		Nodo nodoNE = new Nodo(Estado.NE);
		Nodo nodoPC = new Nodo(Estado.PC);
		Nodo nodoSEP_1 = new Nodo(null);
		Nodo nodoSEP = new Nodo(Estado.SEP);
		Nodo nodoMenos 	= new Nodo(Estado.MENOS);
		Nodo nodoCero = new Nodo(Estado.CERO);
		Nodo nodoENTERO = new Nodo(Estado.ENTERO);
		Nodo nodoMAS = new Nodo(Estado.MAS);
		Nodo nodoVAR = new Nodo(Estado.VAR);
		Nodo nodoEOF = new Nodo(Estado.EOF);
		Nodo nodoCOM = new Nodo(null);
		Nodo nodoREAL_1 = new Nodo(null);
		Nodo nodoREAL1 = new Nodo(Estado.REAL1);
		Nodo nodoREAL_2 = new Nodo(null);
		Nodo nodoREAL2 = new Nodo(Estado.REAL2);
		Nodo nodoREAL_3 = new Nodo(null);
		Nodo nodoREAL3 = new Nodo(Estado.REAL3);				
		
		nodoInicio.addVecino(nodoPor, "*");
		nodoInicio.addVecino(nodoDiv, "/");
		nodoInicio.addVecino(nodoPAp, "(");
		nodoInicio.addVecino(nodoPCie, ")");
		nodoInicio.addVecino(nodoGT, ">");
		nodoInicio.addVecino(nodoLT, "<");
		nodoInicio.addVecino(nodoAsig, "=");
		nodoInicio.addVecino(nodoNE_1, "!");
		nodoInicio.addVecino(nodoPC, ";");
		nodoInicio.addVecino(nodoSEP_1, "&");
		nodoInicio.addVecino(nodoMenos, "-");
		nodoInicio.addVecino(nodoCero, "0");
		nodoInicio.addVecino(nodoENTERO, enteros);
		nodoInicio.addVecino(nodoMAS, "+");
		nodoInicio.addVecino(nodoVAR, mayusculas + minusculas);
		nodoInicio.addVecino(nodoEOF, "");
		nodoInicio.addVecino(nodoCOM, "#");
		
		nodoGT.addVecino(nodoGE, "=");
		nodoLT.addVecino(nodoLE, "=");
		nodoAsig.addVecino(nodoEq, "=");
		nodoNE_1.addVecino(nodoNE, "=");
		
		nodoSEP_1.addVecino(nodoSEP, "&");
		
		nodoMenos.addVecino(nodoCero, "0");
		nodoMenos.addVecino(nodoENTERO, enteros);

		nodoCero.addVecino(nodoREAL1, ".");

		nodoENTERO.addVecino(nodoENTERO, enteros + "0");
		nodoENTERO.addVecino(nodoREAL1, ".");
		
		nodoMAS.addVecino(nodoCero, "0");
		nodoMAS.addVecino(nodoENTERO, enteros);
		
		nodoVAR.addVecino(nodoVAR, minusculas + mayusculas + enteros + "0" + "_");
		
		nodoCOM.addVecino(nodoCOM, minusculas + mayusculas + enteros + "0" + "_"); // AQUÍ IRÍA CUALQUIER CARÁCTER
		//nodoCOM.addVecino(nodoInicio, NL);
		
		nodoInicio.addVecino(nodoInicio, " " + '\n' + '\r' + '\t' + '\b');
		
		nodoREAL_1.addVecino(nodoREAL_1, "0");
		nodoREAL_1.addVecino(nodoREAL1, enteros);
		
		nodoREAL1.addVecino(nodoREAL1, "0" + enteros);
		nodoREAL1.addVecino(nodoREAL_2, "eE");
		
		nodoREAL_2.addVecino(nodoREAL2, enteros);
		nodoREAL_2.addVecino(nodoREAL_3, "+-");
		nodoREAL_2.addVecino(nodoREAL3, "0");
		
		nodoREAL2.addVecino(nodoREAL2, enteros + "0");
		
		nodoREAL_3.addVecino(nodoREAL2, enteros);
		nodoREAL_3.addVecino(nodoREAL3, "0");
				
		File f = new File("file.txt");
		
	    FileReader fr = new FileReader(f);
	    
	    BufferedReader br = new BufferedReader(fr);
	    
	    int c = 0;  
	    
	    String str = "";
	    
	    while((c = br.read()) != -1){
	    	
	    	char character = (char) c;
	    	
	    	str += character;    	

	    }
	    
	    System.out.println(str);
  
		MaquinaEstados me = new MaquinaEstados(nodoInicio, str);
			    	
		while(true) {
						
			try {
				
				// Tratamos de obtener una unidad léxica hasta que demos con un EOF
			    UL ul = me.getToken();			    
			   
			    System.out.println(ul.toString());
			    
			    if(ul.clase == CL.EOF) break;
			    
			} catch(Exception e) {
				
				System.out.println("Error parseando");
				break;
			}
			
		}	
		
	}
	
}
