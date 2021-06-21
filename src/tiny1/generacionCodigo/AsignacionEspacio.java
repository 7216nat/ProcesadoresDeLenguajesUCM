package tiny1.generacionCodigo;


import tiny1.asint.Procesamiento;
import tiny1.asint.TinyASint.*;
public class AsignacionEspacio implements Procesamiento {
	

	private int despCounter = 0;
	private int parCounter = 0;
	private int globalCounter = 0;
	private boolean verbose = false;
	
	public int getStaticMem() {
		return globalCounter;
	}
	
	public AsignacionEspacio(boolean verbose) {
		this.verbose = verbose;
	}
	
    public void procesa(Prog prog) {
        prog.decs().procesa(this);
        prog.insts().procesa(this);
    }
    public void procesa(NoDecs decs){
       
    }
    public void procesa(AuxDecs decs){
        decs.decs().procesa(this);
        decs.setTam(decs.decs().tam());
    }
    public void procesa(LDecSimp decs) {
        decs.dec().procesa(this);
        decs.setTam(decs.dec().tam());
    }
    public void procesa(LDecComp decs) {
        decs.decs().procesa(this);
        decs.dec().procesa(this);
        decs.setTam(decs.decs().tam() + decs.dec().tam());
    }
    public void procesa(DVar dec) { 
        dec.tipo().procesa(this);
        dec.setTam(dec.tipo().tam());
        dec.setDir(globalCounter);
    	globalCounter+=dec.tam();
    }
    public void procesa(DTipo dec) {
    	dec.tipo().procesa(this);
    	dec.setTam(dec.tipo().tam());
    	dec.setDir(globalCounter);
    	globalCounter+=dec.tam();
    }
    public void procesa(DProc dec) {
    	
    	parCounter = 0;
    	
        dec.pars().procesa(this);
        dec.bloque().procesa(this);
        
        int bloquetam = ((Bloque)dec.bloque()).prog().decs().tam();
        int parstam =  dec.pars().tam();
        
        dec.setTam(parstam + bloquetam);
        dec.setDir(globalCounter);
        
        globalCounter+=dec.pars().tam();
        
    }
    public void procesa(NoPars decs){
        // naa
    }
    public void procesa(ParsComp pars) {
        pars.par().procesa(this);
        pars.pars().procesa(this);
    }
    public void procesa(ParsSimp pars) {
        pars.par().procesa(this);
    }
    public void procesa(ParRef par) {
        par.tipo().procesa(this);
        par.setTam(par.tipo().tam());
        par.setDir(parCounter);
        parCounter += par.tam();
        if(verbose)
        	System.out.println("Asignación: " + par.toString() + " tam: " + par.tam() + " dir: " + par.getDir());

    }
    public void procesa(ParSinRef par) {
        par.tipo().procesa(this);
        par.setTam(par.tipo().tam());
        par.setDir(parCounter);
        parCounter += par.tam();
        if(verbose)
        	System.out.println("Asignación: " + par.toString() + " tam: " + par.tam() + " dir: " + par.getDir());

    }
    public void procesa(INT t) {
    	t.setTam(1);
    	
    }
    public void procesa(REAL t) {
    	t.setTam(1);
    }
    public void procesa(BOOL t) {
    	t.setTam(1);
    }
    public void procesa(STRING t) {
    	t.setTam(1);
    }
    public void procesa(IdenTipo t) {

    }
    public void procesa(ARRAY t) {
    	t.tipo().procesa(this);
    	t.setTam(Integer.parseInt(t.num().toString()) * t.tipo().tam());
    }
    public void procesa(OK exp){

    }
    public void procesa(ERROR exp){

    }
    public void procesa(NULL t){
    	t.setTam(1);
    }
    public void procesa(REGISTRO t) {
    	despCounter = 0;
        t.campos().procesa(this);
        t.setTam(despCounter);
    }
    public void procesa(CamposSimp cs) {
        cs.campo().procesa(this);
    }
    public void procesa(CamposComp cs) {
        cs.campos().procesa(this);
        cs.campo().procesa(this);
    }
    public void procesa(Campo c) {
        c.tipo().procesa(this);
        c.setDesp(despCounter);
        despCounter += c.tipo().tam();
    }
    public void procesa(POINTER t) {
    	t.setTam(1);
    }
    public void procesa(NoInsts decs){
        // naa
    }
    public void procesa(InstsComp is) {

    }
    public void procesa(InstsSimp is) {

    }
    public void procesa(IAsig i) {

    }
    public void procesa(IIfThen i) {

    }
    public void procesa(IIfThenElse i) {

    }
    public void procesa(IWhile i) {

    }
    public void procesa(IRead i) {

    }
    public void procesa(IWrite i) {

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
    	i.prog().procesa(this);
    	i.setTam(i.prog().decs().tam());
    }
    public void procesa(NoExps decs){
        // naa
    }
    public void procesa(Exps1 exp) {
  
    }
    public void procesa(Exps0 exp) {

    }
    public void procesa(And exp) {

    }
    public void procesa(Or exp) {

    }
    public void procesa(LT exp) {

    }
    public void procesa(GT exp) {

    }
    public void procesa(LE exp) {

    }
    public void procesa(GE exp) {

    }
    public void procesa(NE exp) {

    }
    public void procesa(EQ exp) {

    }
    public void procesa(Mod exp) {

    }
    public void procesa(Not exp) {

    }
    public void procesa(Neg exp) {

    }
    public void procesa(Index exp) {

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
    	
    }
    public void procesa(IdenExp exp) {
    	
    }
    public void procesa(Lreal exp) {
  
    }
    public void procesa(True exp) {

    }
    public void procesa(False exp) {

    }
    public void procesa(Cadena exp) {

    }
    public void procesa(Nnull exp) {

    }
    public void procesa(Suma exp) {

    }
    public void procesa(Resta exp) {

    }
    public void procesa(Mul exp) {

    }
    public void procesa(Div exp) {

    }   
}   

            