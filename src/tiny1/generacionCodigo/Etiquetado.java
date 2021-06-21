package tiny1.generacionCodigo;
import tiny1.asint.Procesamiento;
import tiny1.asint.TinyASint.*;
public class Etiquetado implements Procesamiento {
    
	private int etq = 0;
	private boolean verbose = true;
	
	
    public Etiquetado(boolean verbose) {
    	this.verbose = verbose;
    }
    public void procesa(Prog prog) {
        prog.insts().procesa(this);
        prog.decs().procesa(this);
    }
    public void procesa(NoDecs decs){
        //naa
    }
    public void procesa(AuxDecs decs){
       decs.decs().procesa(this);
    }
    public void procesa(LDecSimp decs) {
    	decs.dec().procesa(this);
    }
    public void procesa(LDecComp decs) {
    	decs.decs().procesa(this);
        decs.dec().procesa(this);
    }
    public void procesa(DVar dec) { 
      
    }
    public void procesa(DTipo dec) {
      
    }
    public void procesa(DProc dec) {
        dec.bloque().procesa(this);
                
        dec.setEtqi(dec.bloque().etqi());
        dec.setEtqs(dec.bloque().etqs());
    }
    public void procesa(NoPars decs){
        // naa
    }
    public void procesa(ParsComp pars) {
      
    }
    public void procesa(ParsSimp pars) {
        
    }
    public void procesa(ParRef par) {

    }
    public void procesa(ParSinRef par) {
       
    }
    public void procesa(INT t) {

    }
    public void procesa(REAL t) {

    }
    public void procesa(BOOL t) {

    }
    public void procesa(STRING t) {

    }
    public void procesa(IdenTipo t) {

    }
    public void procesa(ARRAY t) {
        
    }
    public void procesa(OK exp){

    }
    public void procesa(ERROR exp){

    }
    public void procesa(NULL exp){

    }
    public void procesa(REGISTRO t) {

    }
    public void procesa(CamposSimp cs) {

    }
    public void procesa(CamposComp cs) {
      
    }
    public void procesa(Campo c) {
      
    }
    public void procesa(POINTER t) {
      
    }
    public void procesa(NoInsts decs){
        // naa
    }
    public void procesa(InstsComp is) {
		is.insts().procesa(this);
        is.inst().procesa(this);
    }
    public void procesa(InstsSimp is) {
        is.inst().procesa(this);
        is.setEtqi(is.inst().etqi());
        is.setEtqs(is.inst().etqs());
    }
    public void procesa(IAsig i) {
    	i.setEtqi(etq);    	
    	i.exp0().procesa(this);
		i.exp1().procesa(this);
		
		etq++;
		i.setEtqs(etq);
    }
    public void procesa(IIfThen i) {
    	i.setEtqi(etq);
    	i.exp().procesa(this);
    	etq++;
    	i.insts().procesa(this);
    	i.setEtqs(etq);
    }
    public void procesa(IIfThenElse i) {
    	i.setEtqi(etq);
    	i.exp().procesa(this);
    	etq++;
    	i.insts0().procesa(this);
    	etq++;
    	i.insts1().procesa(this);
    	i.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(IWhile i) {
    	i.setEtqi(etq);
    	i.exp().procesa(this);
		etq++;
		i.insts().procesa(this);
		etq++;
		i.setEtqs(etq);	
		if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(IRead i) {
    	i.setEtqi(etq);
		i.exp().procesa(this);	
		etq++;		
		etq++;
    	i.setEtqs(etq);	
    	if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(IWrite i) {
    	i.setEtqi(etq);
		i.exp().procesa(this);
		etq++;
		if(i.exp().esDesignador())
			etq++;
		i.setEtqs(etq);	
		if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(INew i) {
    	i.setEtqi(etq);
		i.exp().procesa(this);
		etq++;
		etq++;
		i.setEtqs(etq);	
		if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(IDelete i) {
    	i.setEtqi(etq);
		i.exp().procesa(this);
		etq++;
		i.setEtqs(etq);	
		if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(INl i) {
    	i.setEtqi(etq);
		etq++;
		i.setEtqs(etq);	
		if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(ICall proc) {

    	proc.setEtqi(etq);
		
		Pars pars = ((DProc)proc.vinculo()).pars();
		Exps exps = proc.exps();	
		
		Par par = pars.par();
		Exp exp = exps.exp();
		
		etq++;
		
		while(par != null && exp != null) {
	
			etq += 3;
			
			exp.procesa(this);
			
			if(proc.exps().exp().esDesignador()) {
				etq++;		
			} else {
				if(par instanceof ParRef) {

				} else {
					etq++;
				}	
			}
			
			pars = pars.pars();
			exps = exps.exps();
			
			par = pars != null ? pars.par() : null;
			exp = exps != null ? exps.exp() : null;
		} 
		
		etq += 2;	
		
		proc.setEtqs(etq);	
		
		if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
    }
    public void procesa(Bloque i) {
    	i.setEtqi(etq);
        i.prog().procesa(this);
        i.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(NoExps decs){
        // naa
    }
    public void procesa(Exps1 exp) {
        exp.exps().procesa(this);
        exp.exp().procesa(this);
    }
    public void procesa(Exps0 exp) {
    	exp.exp().procesa(this);
    }
    
    public void aux_expBin(ExpBin exp) {
    	    	
    	exp.setEtqi(etq);
    	
		exp.arg0().procesa(this);
		if(exp.arg0().esDesignador())
			etq++;		
		exp.arg1().procesa(this);
		if(exp.arg1().esDesignador())
			etq++;
		
		etq++;
		
		exp.setEtqs(etq);

    	if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
   
	}
    
    public void aux_expUni(ExpUni exp) {
    	
    	exp.setEtqi(etq);
    	
		exp.arg().procesa(this);
		if(exp.arg().esDesignador())
			etq++;		
		
		etq++;
		
		exp.setEtqs(etq);
		if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
	}
    
    public void procesa(And exp) {
    	aux_expBin(exp);
    }
    public void procesa(Or exp) {
    	aux_expBin(exp);
    }
    public void procesa(LT exp) {
    	aux_expBin(exp);
    }
    public void procesa(GT exp) {
    	aux_expBin(exp);
    }
    public void procesa(LE exp) {
    	aux_expBin(exp);
    }
    public void procesa(GE exp) {
    	aux_expBin(exp);
    }
    public void procesa(NE exp) {
    	aux_expBin(exp);
    }
    public void procesa(EQ exp) {
    	aux_expBin(exp);
    }
    public void procesa(Mod exp) {
    	aux_expBin(exp);
    }
    public void procesa(Not exp) {
    	aux_expUni(exp);
    }
    public void procesa(Neg exp) {
    	aux_expUni(exp);
    }
    public void procesa(Index exp) {
    	exp.setEtqi(etq);
    	
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		
		if(exp.arg1().esDesignador())
			etq++;
		
		// TODO ATENCI�N ESTO SE TIENE QUE HACER CON EL TAMA�O DEL TIPO BASE DEL ARRAY
		etq += 3;
		exp.setEtqs(etq);
		if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
    }
    public void procesa(Ptr exp) {
    	exp.setEtqi(etq);
    	exp.exp().procesa(this);
    	etq+=3;
    	exp.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " + exp.etqs());
    }
    public void procesa(Atr i) {
    	i.setEtqi(etq);
    	i.exp().procesa(this);
    	etq++;
    	etq++;
    	i.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + i.toString() + " etqi: " + i.etqi() + " etqs: " +  i.etqs());
    }
    public void procesa(Indir exp) {
    	exp.setEtqi(etq);
		exp.arg().procesa(this);
		etq++;
		exp.setEtqs(etq);
		if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
    }
    public void procesa(Parentesis exp) {
        exp.arg().procesa(this);
    }
    public void procesa(Ent exp) {
    	etq++;
    }
    public void procesa(IdenExp exp) {
    	exp.setEtqi(etq);
    	
    	if(exp.vinculo().getAmbito() == 0) {
			// Variable global
			etq++;
		} else {
			etq += 3;
			if(exp.vinculo() instanceof ParRef)
				etq++;
		}
    	exp.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
    }
    public void procesa(Lreal exp) {
      
    }
    public void procesa(True exp) {
    	exp.setEtqi(etq);
    	etq++;
    	exp.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
    }
    public void procesa(False exp) {
    	exp.setEtqi(etq);
    	etq++;
    	exp.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
    }
    public void procesa(Cadena exp) {
    	exp.setEtqi(etq);
    	etq++;
    	exp.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
    }
    public void procesa(Nnull exp) {
    	exp.setEtqi(etq);
    	etq++;
    	exp.setEtqs(etq);
    	if(verbose)
    		System.out.println("Etiquetado: " + exp.toString() + " etqi: " + exp.etqi() + " etqs: " +  exp.etqs());
    }
    public void procesa(Suma exp) {
    	aux_expBin(exp);
    }
    public void procesa(Resta exp) {
    	aux_expBin(exp);
    }
    public void procesa(Mul exp) {
    	aux_expBin(exp);
    }
    public void procesa(Div exp) {
    	aux_expBin(exp);
    }   
}   

            