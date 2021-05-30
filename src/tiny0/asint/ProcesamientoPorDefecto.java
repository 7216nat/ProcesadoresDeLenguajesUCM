
package tiny0.asint;

import tiny0.asint.TinyASint.*;

public class ProcesamientoPorDefecto implements Procesamiento {
   public void procesa(Suma exp) {}
   public void procesa(Resta exp) {}
   public void procesa(Mul exp) {}
   public void procesa(Div exp) {}
   public void procesa(Id exp) {}
   public void procesa(Dec dec) {}
   public void procesa(Decs_muchas decs) {}
   public void procesa(Decs_una decs) {}
   public void procesa(Prog_sin_decs prog) {}    
   public void procesa(Prog_entero prog) {}
   public void procesa(Asig asig) {}
   public void procesa(Asigs asigs) {}
   public void procesa(Decs decs) {}
   public void procesa(Entero entero){}
   public void procesa(Real real) {}
   public void procesa(CTrue cTrue) {}
   public void procesa(CFalse cFalse) {}
   public void procesa(And and) {}
   public void procesa(Or or) {}
   public void procesa(Lt lt) {}
   public void procesa(Gt gt) {}
   public void procesa(Leq leq) {}
   public void procesa(Geq geq) {}
   public void procesa(Eq eq) {}
   public void procesa(Neq neq) {}
   public void procesa(Not not) {}
   public void procesa(Neg neg) {}
   public void procesa(Prog_sin_asigs prog_sin_asigs) {}
   public void procesa(Asigs_muchas asigs) {}
   public void procesa(Asigs_una asigs) {}
@Override
public void procesa(CBool cBool) {
	// TODO Auto-generated method stub
	
}
@Override
public void procesa(CReal cReal) {
	// TODO Auto-generated method stub
	
}
@Override
public void procesa(CInt cInt) {
	// TODO Auto-generated method stub
	
} 
}
