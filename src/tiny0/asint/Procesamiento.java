package tiny0.asint;

import tiny0.asint.TinyASint.*;

public interface Procesamiento {
    void procesa(Asig asig);
    void procesa(Resta exp);
    void procesa(Mul exp);
    void procesa(Div exp);
    void procesa(Id exp);
    void procesa(Decs_muchas decs);
    void procesa(Decs_una decs);
    void procesa(Prog_sin_decs prog);    
    void procesa(Prog_entero prog);
	void procesa(Asigs_muchas asigs);
	void procesa(Asigs_una asigs);
	void procesa(Decs decs);
	void procesa(Entero entero);
	void procesa(Real real);
	void procesa(CTrue cTrue);
	void procesa(CFalse cFalse);
	void procesa(Suma suma);
	void procesa(And and);
	void procesa(Or or);
	void procesa(Lt lt);
	void procesa(Gt gt);
	void procesa(Leq leq);
	void procesa(Geq geq);
	void procesa(Eq eq);
	void procesa(Neq neq);
	void procesa(Not not);
	void procesa(Neg neg);
	void procesa(Prog_sin_asigs prog_sin_asigs);
	void procesa(CBool cBool);
	void procesa(CReal cReal);
	void procesa(CInt cInt);
}