package tiny0;

import java.util.ArrayList;
import java.util.List;

// Clase nodo para la m�quina de estados
// Cada nodo tiene un estado asociado y una transici�n, estas transiciones se definen con
// un nodo y bajo que caracteres acceden a este

public class Nodo {

	Estado estado;	
	
	List<Nodo> vecinos;
	List<String> caracteres;		
	
	public Nodo(Estado estado) {
		this.estado = estado;
		this.vecinos = new ArrayList<Nodo>();
		this.caracteres = new ArrayList<String>();
	}
	
	public void addVecino(Nodo vecino, String caracteres) {
		this.vecinos.add(vecino);
		this.caracteres.add(caracteres);
	}
	
	public Nodo siguienteNodo(char c) throws Exception {
		
		int i = 0;
		
		for(String str : caracteres) {
			
			if(str.contains(c + ""))
				return vecinos.get(i);
						
			i++;
		}
		// En caso de que no se pueda transitar se lanzar� una excepci�n
		throw new Exception();			
	}
}
