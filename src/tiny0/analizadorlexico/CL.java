package tiny0.analizadorlexico;

// Clase lexica, algunas seran univaluada y otras multivaluadas
public enum CL {
	CINT, CREAL, CBOOL, CTRUE, CFALSE, CAND, COR, CNOT,
	IDEN(true), ENTERO(true), REAL(true), SEP, PC,
	ASIG, MAS, MENOS, POR, DIV, PAP, PCIE, GT, GE, LT, LE, EQ, NE, EOF;
	
	boolean multivaluada = false;
	
	// Por defecto es univaluada
	CL() {}
	
	
	CL(boolean multivaluada) {
		this.multivaluada = multivaluada;
	}
}
