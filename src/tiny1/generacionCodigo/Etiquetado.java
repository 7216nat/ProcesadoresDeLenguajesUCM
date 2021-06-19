package tiny1.generacionCodigo;
import tiny1.asint.Procesamiento;
import tiny1.asint.TinyASint.*;
public class Etiquetado implements Procesamiento {
    
	private int etq;
	
	
    public Etiquetado() {

    }
    public void procesa(Prog prog) {
    	etq = 0;
        prog.insts().procesa(this);
    }
    public void procesa(NoDecs decs){
        //naa
    }
    public void procesa(AuxDecs decs){
       
    }
    public void procesa(LDecSimp decs) {
        
    }
    public void procesa(LDecComp decs) {

    }
    public void procesa(DVar dec) { 
      
    }
    public void procesa(DTipo dec) {
      
    }
    public void procesa(DProc dec) {
       
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
    }
    public void procesa(IWhile i) {
    	i.setEtqi(etq);
    	i.exp().procesa(this);
		etq++;
		i.insts().procesa(this);
		etq++;
		i.setEtqs(etq);	
    }
    public void procesa(IRead i) {
       
    }
    public void procesa(IWrite i) {
    	i.setEtqi(etq);
		i.exp().procesa(this);
		etq++;
		if(i.exp().esDesignador())
			etq++;
		i.setEtqs(etq);	
    }
    public void procesa(INew i) {

    }
    public void procesa(IDelete i) {

    }
    public void procesa(INl i) {
      
    }
    public void procesa(ICall i) {
      
    }
    public void procesa(Bloque i) {
    	i.setEtqi(etq);
        i.prog().procesa(this);
        i.setEtqs(etq);
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
		exp.arg0().procesa(this);
		if(exp.arg0().esDesignador())
			etq++;		
		exp.arg1().procesa(this);
		if(exp.arg1().esDesignador())
			etq++;
		
		etq++;
	}
    
    public void aux_expUni(ExpUni exp) {
		exp.arg().procesa(this);
		if(exp.arg().esDesignador())
			etq++;		
		
		etq++;
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
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		
		if(exp.arg1().esDesignador())
			etq++;
		
		// TODO ATENCI�N ESTO SE TIENE QUE HACER CON EL TAMA�O DEL TIPO BASE DEL ARRAY
		etq += 3;
    }
    public void procesa(Ptr exp) {
      
    }
    public void procesa(Atr exp) {
      
    }
    public void procesa(Indir exp) {
      
    }
    public void procesa(Parentesis exp) {
        
    }
    public void procesa(Ent exp) {
    	etq++;
    }
    public void procesa(IdenExp exp) {

		// TODO ATENCI�N AQU� HAY QUE DIFERENCIAR ENTRE VARIABLES LOCALES Y GLOBALES Y TAL
		
    	etq++;
    }
    public void procesa(Lreal exp) {
      
    }
    public void procesa(True exp) {
    	etq++;
    }
    public void procesa(False exp) {
    	etq++;
    }
    public void procesa(Cadena exp) {
    	etq++;
    }
    public void procesa(Nnull exp) {
        
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

            