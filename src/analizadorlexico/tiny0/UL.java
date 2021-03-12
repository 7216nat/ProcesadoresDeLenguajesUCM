package tiny0;
// Unidad L�xica, contiene informaci�n sobre la clase l�xica, el lexema, la l�nea y la columna.

public class UL {

	String lexema;
	CL clase;
	
	int linea;
	int columna;
	
	public UL(CL clase, String lexema, int linea, int columna) {
		this.clase = clase;
		this.lexema = lexema;
		this.linea = linea;
		this.columna = columna;
	}
	
	public String toString() {
		
		// Si es multivaluada mostrar� el lexema, si no, no es necesario
		return "Clase: " + this.clase + (clase.multivaluada?", lexema: '" + this.lexema + "'":"") + " en linea " + linea + " y columna " + columna;
		
	}
	
}
