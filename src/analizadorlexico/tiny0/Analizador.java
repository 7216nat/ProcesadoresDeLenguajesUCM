package tiny0;

import java.util.HashMap;
import java.util.Map;

public class Analizador {

	Nodo nodo;
	Nodo nodoInicial;
	
	int linea = 0;
	int columna = 0;
	
	Map<String, CL> palabrasReservadas = new HashMap<String, CL>();
	
	String texto;
	
	public Analizador(Nodo nodoInicial, String texto) {
		this.texto = texto;
		this.nodoInicial = nodoInicial;
		this.nodo = nodoInicial;
	}
	
	public void addPalabraReservada(String palabra, CL clase) {
		palabrasReservadas.put(palabra, clase);
	}
	
	public UL getToken() throws Exception {
		
		String lexema = "";

		while(true) {

			if(nodo.estado == Estado.INICIO) lexema = "";
			
			// Se quita el primer car�cter			
			char c = texto.charAt(0);
			
			// System.out.println("Analizando " + c + " val " + (int) c + " en nodo " + nodo.estado);
			
			texto = texto.substring(1);
			
			// En caso de salto de l�nea se incrementa el contador
			if(c == '\n') {
				linea++;
				columna = 0;
			}
									
			// Caso especial para el manejo de EOF (cuando no quedan caracteres)
			if(texto.length() == 0) return new UL(CL.EOF, "", linea, columna);
			
			// Se comprueba si se puede acceder a otro estado
			try {
			
				nodo = nodo.siguienteNodo(c);
							
				// Solo a�adimos el car�cter al lexema si no es un car�cter omitible y si no estamos en el inicio
				if(!"\n\r\t\b ".contains(c + ""))
					lexema += c;
				
			} catch (Exception e){	
				
				// En caso de que estemos en un nodo sin salida pueden pasar dos cosas:
				// 1: que sea un estado v�lido (con token asociado)
				// 2: que sea un error l�xico
				Estado estado = nodo.estado;
				
				// Si no es un estado final, es decir, no tiene clase l�xica asociada, es un error.
				if(estado.clase == null) throw new Exception();
				
				// Se reestablece el nodo
				nodo = nodoInicial;
				
				// Se devuelve el caracter a su posici�n
				texto = c + texto;
				columna--;
				
				// En caso de que se trate de una palabra reservada, elegimos la clase correcta;
				CL clase = estado.clase;
				
				if(palabrasReservadas.containsKey(lexema))
					clase = palabrasReservadas.get(lexema);				
				
				// Se devuelve una unidad l�xica con los datos propios
				return new UL(clase, lexema, linea, columna);
				
			} finally {
				columna++;
			}
		}	
	}
	
}
	
