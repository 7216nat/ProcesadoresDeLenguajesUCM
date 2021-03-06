package tiny0.analizadorlexico;

public enum Estado {

	INICIO(null), ENTERO(CL.ENTERO), MAS(CL.MAS), REAL(CL.REAL), EOF(CL.EOF), IDEN(CL.IDEN),
	POR(CL.POR), DIV(CL.DIV), PAP(CL.PAP), SEP(CL.SEP),
	PCIE(CL.PCIE), GT(CL.GT), GE(CL.GE), LT(CL.LT), LE(CL.LE),
	ASIG(CL.ASIG), EQ(CL.EQ), NE(CL.NE), PC(CL.PC),
	MENOS(CL.MENOS), CERO(CL.ENTERO), REAL1(CL.REAL), REAL2(CL.REAL), REAL3(CL.REAL);
	
	CL clase;
	
	Estado(CL clase) {
		this.clase = clase;
	}
	
}
