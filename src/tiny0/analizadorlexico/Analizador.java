package tiny0.analizadorlexico;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.Reader;
import tiny0.errors.GestionErroresTiny;

public class Analizador {

	Nodo nodo;
	Nodo nodoInicial;
	
	int linea = 1;
	int columna = 1;
	
	Map<String, CL> palabrasReservadas = new HashMap<String, CL>();
	
	Reader input;
	String texto;
	
	GestionErroresTiny errores;

	public Analizador(Reader input) {
		this.input = input;
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void fijaGestionErrores(GestionErroresTiny errores){
		this.errores = errores;
	}

	private void start() throws IOException{
		// Cadenas de caracteres que seran utiles para abreviar codigo
		String enteros = "123456789";
		String minusculas = "abcdefghijklmnopqrstuvwxyz";
		String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String todos = "";
		
		for(int i = 0; i < 255; i++) todos += (char) i;

		// Definimos los nodos de la maquina de estados y su estado asociado si se trata de un nodo final
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
		
		//Se anaden las palabras reservadas y sus clases lexicas asociadas
		addPalabraReservada("int", CL.CINT);
		addPalabraReservada("real", CL.CREAL);
		addPalabraReservada("bool", CL.CBOOL);
		addPalabraReservada("true", CL.CTRUE);
		addPalabraReservada("false", CL.CFALSE);
		addPalabraReservada("and", CL.CAND);
		addPalabraReservada("or", CL.COR);
		addPalabraReservada("not", CL.CNOT);

		// Se carga el archivo y se guarda en un string
	    int c = 0;  
	    StringBuilder bld = new StringBuilder();
	    
	    while((c = input.read()) != -1){
	    	char character = (char) c;
	    	bld.append(character);  	
	    }
	    
		this.texto = bld.toString();
		
		this.nodoInicial = nodoInicio;
		this.nodo = nodoInicial;
	}
	private void addPalabraReservada(String palabra, CL clase) {
		palabrasReservadas.put(palabra, clase);
	}
	
	public UL getToken() throws IOException{
		
		String lexema = "";

		while(true) {

			if(nodo.estado == Estado.INICIO) lexema = "";
			
			// Caso especial para el manejo de EOF (cuando no quedan caracteres)
			if(texto.length() <= 0) return new UL(CL.EOF, "", linea, columna-1);

			// Se quita el primer caracter			
			char c = texto.charAt(0);
					
			texto = texto.substring(1);
			
			// En caso de salto de linea se incrementa el contador
			if(c == '\n') {
				linea++;
				columna = 1;
			}
									
			
			// Se comprueba si se puede acceder a otro estado
			try {
			
				nodo = nodo.siguienteNodo(c);
							
				// Solo anadimos el caracter al lexema si no es un caracter omitible y si no estamos en el inicio
				if(!"\n\r\t\b ".contains(c + ""))
					lexema += c;
				
			} catch (Exception e){	
				
				// En caso de que estemos en un nodo sin salida pueden pasar dos cosas:
				// 1: que sea un estado valido (con token asociado)
				// 2: que sea un error lexico
				Estado estado = nodo.estado;
				
				// Si no es un estado final, es decir, no tiene clase lexica asociada, es un error.
				if(estado == null) {
					errores.errorLexico(linea, columna, lexema);
					System.exit(1);
				}
				
				// Se reestablece el nodo
				nodo = nodoInicial;
				
				// Se devuelve el caracter a su posicion
				texto = c + texto;
				columna--;			
				
				CL clase = estado.clase;

				if(palabrasReservadas.containsKey(lexema))
					clase = palabrasReservadas.get(lexema);				
				
				// Se devuelve una unidad lexica con los datos propios
				return new UL(clase, lexema, linea, columna);
				
			} finally {
				columna++;
			}
		}	
	}
	
}
	
