
public class MaquinaEstados {

	Nodo nodo;
	Nodo nodoInicial;
	
	int linea = 0;
	int columna = 0;
	
	String texto;
	
	public MaquinaEstados(Nodo nodoInicial, String texto) {
		this.texto = texto;
		this.nodoInicial = nodoInicial;
		this.nodo = nodoInicial;
	}
	
	public UL getToken() throws Exception {
		
		String lexema = "";

		while(true) {

			// Se quita el primer carácter			
			char c = texto.charAt(0);
			
			texto = texto.substring(1);
			
			// En caso de salto de línea se incrementa el contador
			if(c == '\n') {
				linea++;
				columna = 0;
			}
									
			// Caso especial para el manejo de EOF (cuando no quedan caracteres)
			if(texto.length() == 0) return new UL(CL.EOF, "", linea, columna);
			
			// Se comprueba si se puede acceder a otro estado
			try {
			
				nodo = nodo.siguienteNodo(c);
							
				// Solo añadimos el carácter al lexema si no es un carácter omitible
				if(!"\n\r\t\b ".contains(c + ""))
					lexema += c;
				
			} catch (Exception e){	
				
				// En caso de que estemos en un nodo sin salida pueden pasar dos cosas:
				// 1: que sea un estado válido (con token asociado)
				// 2: que sea un error léxico
				Estado estado = nodo.estado;
				
				// Si no es un estado final, es decir, no tiene clase léxica asociada, es un error.
				if(estado.clase == null) throw new Exception();
				
				// Se reestablece el nodo
				nodo = nodoInicial;
				
				// Se devuelve el caracter a su posición
				texto = c + texto;
										
				// Se devuelve una unidad léxica con los datos propios
				return new UL(estado.clase, lexema, linea, columna);
				
			} finally {
				columna++;
			}
		}	
	}
	
}
	
