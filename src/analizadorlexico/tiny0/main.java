package tiny0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) throws Exception {
		if(args.length < 1)
			throw new Exception("Elige el nombre de archivo como argumento de entrada");
		// Cadenas de caracteres que ser�n �tiles para abreviar c�digo
		String enteros = "123456789";
		String minusculas = "abcdefghijklmnopqrstuvwxyz";
		String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String todos = "";
		
		for(int i = 0; i < 255; i++) todos += (char) i;

		// Definimos los nodos de la m�quina de estados y su estado asociado si se trata de un nodo final
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
		Nodo nodoSep_1 = new Nodo(null);
		Nodo nodoSep = new Nodo(Estado.SEP);
		Nodo nodoMenos 	= new Nodo(Estado.MENOS);
		Nodo nodoCero = new Nodo(Estado.CERO);
		Nodo nodoEntero = new Nodo(Estado.ENTERO);
		Nodo nodoMas = new Nodo(Estado.MAS);
		Nodo nodoVar = new Nodo(Estado.IDEN);
		Nodo nodoEOF = new Nodo(Estado.EOF);
		Nodo nodoCom = new Nodo(null);
		Nodo nodoReal_1 = new Nodo(null);
		Nodo nodoR0 = new Nodo(null);
		Nodo nodoReal = new Nodo(Estado.REAL);
		Nodo nodoExp_1 = new Nodo(null);
		Nodo nodoExp = new Nodo(Estado.REAL);
		Nodo nodoExpCero = new Nodo(Estado.REAL);
		Nodo nodoSign = new Nodo(null);		
		
		
		// Se modelan las conexiones de los nodos, y que caracteres se usan para ir de uno a otro
		nodoInicio.addVecino(nodoPor, "*");
		nodoInicio.addVecino(nodoDiv, "/");
		nodoInicio.addVecino(nodoPAp, "(");
		nodoInicio.addVecino(nodoPCie, ")");
		nodoInicio.addVecino(nodoGT, ">");
		nodoInicio.addVecino(nodoLT, "<");
		nodoInicio.addVecino(nodoAsig, "=");
		nodoInicio.addVecino(nodoNE_1, "!");
		nodoInicio.addVecino(nodoPC, ";");
		nodoInicio.addVecino(nodoSep_1, "&");
		nodoInicio.addVecino(nodoMenos, "-");
		nodoInicio.addVecino(nodoCero, "0");
		nodoInicio.addVecino(nodoEntero, enteros);
		nodoInicio.addVecino(nodoMas, "+");
		nodoInicio.addVecino(nodoVar, mayusculas + minusculas);
		nodoInicio.addVecino(nodoEOF, "");
		nodoInicio.addVecino(nodoCom, "#");
		
		nodoGT.addVecino(nodoGE, "=");
		nodoLT.addVecino(nodoLE, "=");
		nodoAsig.addVecino(nodoEq, "=");
		nodoNE_1.addVecino(nodoNE, "=");
		
		nodoSep_1.addVecino(nodoSep, "&");
		
		nodoMas.addVecino(nodoCero, "0");
		nodoMas.addVecino(nodoEntero, enteros);
		
		nodoMenos.addVecino(nodoCero, "0");
		nodoMenos.addVecino(nodoEntero, enteros);

		nodoEntero.addVecino(nodoEntero, enteros + "0");
		nodoEntero.addVecino(nodoReal_1, ".");
		nodoEntero.addVecino(nodoExp_1, "eE");
		
		nodoCero.addVecino(nodoReal_1, ".");
		nodoCero.addVecino(nodoExp_1, "eE");
		
		nodoReal_1.addVecino(nodoReal, "0" + enteros);
		
		nodoReal.addVecino(nodoReal, enteros);
		nodoReal.addVecino(nodoR0, "0");
		nodoReal.addVecino(nodoExp_1, "eE");
		
		nodoR0.addVecino(nodoReal, enteros);
		
		nodoExp_1.addVecino(nodoSign, "+-");
		nodoExp_1.addVecino(nodoExp, enteros);
		nodoExp_1.addVecino(nodoExpCero, "0");	
		
		nodoExp.addVecino(nodoExp, "0" + enteros);
		
		nodoVar.addVecino(nodoVar, minusculas + mayusculas + enteros + "0" + "_");
		
		nodoCom.addVecino(nodoCom, todos.replace('\n', ' '));
		nodoCom.addVecino(nodoInicio, "\n");
		
		nodoSign.addVecino(nodoExp, enteros);
		
		nodoInicio.addVecino(nodoInicio, " " + '\n' + '\r' + '\t' + '\b');
		

		
		
		// Se carga el archivo y se guarda en un string
		
		File f = new File(args[0]);
		
	    FileReader fr = new FileReader(f);
	    
	    BufferedReader br = new BufferedReader(fr);
	    
	    int c = 0;  
	    
	    String str = "";
	    
	    while((c = br.read()) != -1){
	    	
	    	char character = (char) c;
	    	
	    	str += character;    	

	    }
	      
	    // Se crea una m�quina de estados con el nodo inicial y el c�digo que hay que analizar l�xicamente
	    Analizador analizador = new Analizador(nodoInicio, str);
			    	
		
		//Se a�aden las palabras reservadas y sus clases l�xicas asociadas
	    analizador.addPalabraReservada("int", CL.CINT);
	    analizador.addPalabraReservada("real", CL.CREAL);
	    analizador.addPalabraReservada("bool", CL.CBOOL);
	    analizador.addPalabraReservada("true", CL.CTRUE);
	    analizador.addPalabraReservada("false", CL.CFALSE);
	    analizador.addPalabraReservada("and", CL.CAND);
	    analizador.addPalabraReservada("or", CL.COR);
	    analizador.addPalabraReservada("not", CL.CNOT);
		
		while(true) {
						
			try {
				
				// Tratamos de obtener una unidad l�xica hasta que demos con un EOF
			    UL ul = analizador.getToken();			    
			   
			    System.out.println(ul.toString());
			    
			    if(ul.clase == CL.EOF) break;
			    
			} catch(Exception e) {
				
				System.out.println("Error parseando");
				break;
			}
			
		}	
		
	}
	
}
