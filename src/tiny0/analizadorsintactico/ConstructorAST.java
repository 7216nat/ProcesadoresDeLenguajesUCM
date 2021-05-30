/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package tiny0.analizadorsintactico;

import tiny0.analizadorlexico.*;
import tiny0.asint.TinyASint.Asig;
import tiny0.asint.TinyASint.Asigs;
import tiny0.asint.TinyASint.Dec;
import tiny0.asint.TinyASint.Decs;
import tiny0.asint.TinyASint.Exp;
import tiny0.asint.TinyASint.Prog;
import tiny0.errors.*;
import tiny0.semops.SemOps;

import java.io.IOException;
import java.io.Reader;

public class ConstructorAST {
	private UL anticipo;
	private Analizador alex;
	private GestionErroresTiny errores;
	private SemOps sem;
	
	public ConstructorAST(Reader input) {
		errores = new GestionErroresTiny();
		alex = new Analizador(input);
		alex.fijaGestionErrores(errores);
		sigToken();
		sem = new SemOps();
	}

	public Prog S() {
		Decs d = Ds();
		empareja(CL.SEP);
		Asigs a = ASIGs();
		empareja(CL.EOF);
		return sem.prog(d, a);
	}

	private Decs Ds() {
		switch (anticipo.clase()) {
		case CINT:
		case CREAL:
		case CBOOL:
			return LDs();
		case SEP:
			return null;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.CINT, CL.CREAL, CL.CBOOL,
					CL.SEP);
			return null;
		}
	}

	private Decs LDs() {
		return RLDs(sem.decs_una(D()));
	}

	private Dec D() {
		switch (anticipo.clase()) {
		case CINT:
			empareja(CL.CINT);
			UL iden1 = anticipo;
			empareja(CL.IDEN);
			return sem.cint(sem.str(iden1.lexema(), iden1.fila(), iden1.columna()));
		case CREAL:
			empareja(CL.CREAL);
			UL iden2 = anticipo;
			empareja(CL.IDEN);
			return sem.creal(sem.str(iden2.lexema(), iden2.fila(), iden2.columna()));
		case CBOOL:
			empareja(CL.CBOOL);
			UL iden3 = anticipo;
			empareja(CL.IDEN);
			return sem.cbool(sem.str(iden3.lexema(), iden3.fila(), iden3.columna()));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.CINT, CL.CREAL, CL.CBOOL);
			return null;
		}
	}

	private Decs RLDs(Decs ah) {
		switch (anticipo.clase()) {
		case PC:
			empareja(CL.PC);
			return RLDs(sem.decs_muchas(ah, D()));
		case SEP:
			return ah;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.PC, CL.SEP);
			return null;
		}
	}

	private Asigs ASIGs() {
		switch (anticipo.clase()) {
		case IDEN:
			return LASIGs();
		case EOF:
			return null;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.IDEN, CL.EOF);
			return null;
		}
	}

	private Asigs LASIGs() {
		return RLASIGs(sem.asigs_una(ASIG()));
	}

	private Asigs RLASIGs(Asigs ah) {
		switch (anticipo.clase()) {
		case PC:
			empareja(CL.PC);
			return RLASIGs(sem.asigs_muchas(ah, ASIG()));
		case EOF:
			return ah;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.IDEN, CL.EOF);
			return null;
		}
	}

	private Asig ASIG() {
		UL idenToken = anticipo;
		empareja(CL.IDEN);
		empareja(CL.ASIG);
		return sem.asig(sem.str(idenToken.lexema(), idenToken.fila(), idenToken.columna()), E0());
	}

	private Exp E0() {
		switch (anticipo.clase()) {
		case IDEN:
		case CNOT:
		case MENOS:
		case CTRUE:
		case CFALSE:
		case REAL:
		case ENTERO:
		case PAP:
			return RE0(E1());
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.IDEN, CL.ENTERO, CL.CNOT,
					CL.MENOS, CL.CTRUE, CL.CFALSE, CL.REAL, CL.PAP);
			return null;
		}
	}

	private Exp RE0(Exp ah) {
		switch (anticipo.clase()) {
		case MAS:
			return RE0(sem.exp(Op0(), ah, E0()));
		case MENOS:
			return RE0(sem.exp(Op0(), ah, E1()));
		case PCIE:
		case EOF:
		case PC:
			return ah;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.MAS, CL.MENOS);
			return null;
		}
	}

	private Exp E1() {
		switch (anticipo.clase()) {
		case IDEN:
		case CNOT:
		case MENOS:
		case CTRUE:
		case CFALSE:
		case REAL:
		case ENTERO:
		case PAP:
			return RE1(E2());
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.IDEN, CL.ENTERO, CL.CNOT,
					CL.MENOS, CL.CTRUE, CL.CFALSE, CL.REAL, CL.PAP);
			return null;
		}
	}

	private Exp RE1(Exp ah) {
		switch (anticipo.clase()) {
		case COR:
		case CAND:
			return RE1(sem.exp(Op1(), ah, E2()));
		case PCIE:
		case EOF:
		case MAS:
		case MENOS:
		case PC:
			return ah;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.COR, CL.CAND);
			return null;
		}
	}

	private Exp E2() {
		switch (anticipo.clase()) {
		case IDEN:
		case CNOT:
		case MENOS:
		case CTRUE:
		case CFALSE:
		case REAL:
		case ENTERO:
		case PAP:
			return RE2(E3());
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.IDEN, CL.ENTERO, CL.CNOT,
					CL.MENOS, CL.CTRUE, CL.CFALSE, CL.REAL, CL.PAP);
			return null;
		}
	}

	private Exp RE2(Exp ah) {
		switch (anticipo.clase()) {
		case GT:
		case GE:
		case LT:
		case LE:
		case EQ:
		case NE:
			return RE2(sem.exp(Op2(), ah, E3()));
		case COR:
		case CAND:
		case PCIE:
		case EOF:
		case MAS:
		case MENOS:
		case PC:
			return ah;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.GT, CL.GE, CL.LE, CL.LT,
					CL.EQ, CL.NE);
			return null;
		}
	}

	private Exp E3() {
		switch (anticipo.clase()) {
		case IDEN:
		case CNOT:
		case MENOS:
		case CTRUE:
		case CFALSE:
		case REAL:
		case ENTERO:
		case PAP:
			return RE3(E4());
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.IDEN, CL.ENTERO, CL.CNOT,
					CL.MENOS, CL.CTRUE, CL.CFALSE, CL.REAL, CL.PAP);
			return null;
		}
	}

	private Exp RE3(Exp ah) {
		switch (anticipo.clase()) {
		case POR:
		case DIV:
			return RE2(sem.exp(Op3(), ah, E4()));
		case PCIE:
		case EOF:
		case MAS:
		case MENOS:
		case PC:
		case COR:
		case CAND:
		case NE:
		case EQ:
		case GT:
		case GE:
		case LT:
		case LE:
			return ah;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.POR, CL.DIV);
			return null;
		}
	}

	private Exp E4() {
		switch (anticipo.clase()) {
		case MENOS:
			empareja(CL.MENOS);
			return E5();
		case CNOT:
			empareja(CL.CNOT);
			return E4();
		case IDEN:
		case CTRUE:
		case CFALSE:
		case REAL:
		case ENTERO:
		case PAP:
			return E5();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.IDEN, CL.ENTERO, CL.CNOT,
					CL.MENOS, CL.CTRUE, CL.CFALSE, CL.REAL, CL.PAP);
			return null;
		}
	}

	private Exp E5() {
		switch (anticipo.clase()) {
		case IDEN:
			UL tkIden = anticipo;
			empareja(CL.IDEN);
			return sem.id(sem.str(tkIden.lexema(), tkIden.fila(), tkIden.columna()));
		case ENTERO:
			UL tkEntero = anticipo;
			empareja(CL.ENTERO);
			return sem.entero(sem.str(tkEntero.lexema(), tkEntero.fila(), tkEntero.columna()));
		case REAL:
			UL tkReal = anticipo;
			empareja(CL.REAL);
			return sem.real(sem.str(tkReal.lexema(), tkReal.fila(), tkReal.columna()));
		case CTRUE:
			UL tkCtrue = anticipo;
			empareja(CL.CTRUE);
			return sem.ctrue(sem.str(tkCtrue.lexema(), tkCtrue.fila(), tkCtrue.columna()));
		case CFALSE:
			UL tkCfalse = anticipo;
			empareja(CL.CFALSE);
			return sem.cfalse(sem.str(tkCfalse.lexema(), tkCfalse.fila(), tkCfalse.columna()));
		case PAP:
			empareja(CL.PAP);
			Exp e = E0();
			empareja(CL.PCIE);
			return e;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.IDEN, CL.ENTERO, CL.CTRUE,
					CL.CFALSE, CL.REAL, CL.PAP);
			return null;
		}
	}
	
	private String Op0() {
		switch (anticipo.clase()) {
		case MAS:
			empareja(CL.MAS);
			return "+";
		case COR:
			empareja(CL.MENOS);
			return "-";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.MAS, CL.MENOS);
			return null;
		}
	}

	private String Op1() {
		switch (anticipo.clase()) {
		case CAND:
			empareja(CL.CAND);
			return "and";
		case COR:
			empareja(CL.COR);
			return "or";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.CAND, CL.COR);
			return null;
		}
	}

	private String Op2() {
		switch (anticipo.clase()) {
		case GT:
			empareja(CL.GT);
			return ">";
		case GE:
			empareja(CL.GE);
			return ">=";
		case LE:
			empareja(CL.LE);
			return "<=";
		case LT:
			empareja(CL.LT);
			return "<";
		case EQ:
			empareja(CL.EQ);
			return "==";
		case NE:
			empareja(CL.NE);
			return "!=";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.GT, CL.GE, CL.LE, CL.LT,
					CL.EQ, CL.NE);
			return null;
		}
	}

	private String Op3() {
		switch (anticipo.clase()) {
		case POR:
			empareja(CL.POR);
			return "*";
		case DIV:
			empareja(CL.DIV);
			return "/";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), CL.POR, CL.DIV);
			return null;
		}
	}

	private void empareja(CL claseEsperada) {
		if (anticipo.clase() == claseEsperada)
			sigToken();
		else
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), claseEsperada);
	}

	private void sigToken() {
		try {
			anticipo = alex.getToken();
		} catch (IOException e) {
			errores.errorFatal(e);
		}
	}

}
