
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
		
		return "Clase: " + this.clase + ", lexema: '" + this.lexema + "' en linea " + linea + " y columna " + columna;
		
	}
	
}
