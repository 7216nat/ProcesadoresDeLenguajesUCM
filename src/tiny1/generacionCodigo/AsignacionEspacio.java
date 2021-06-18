package tiny1.generacionCodigo;
import java.util.HashMap;

import tiny1.asint.Procesamiento;
import tiny1.asint.TinyASint.*;
public class AsignacionEspacio implements Procesamiento {
	
	HashMap<String, Integer> direcciones;
	
	
	private int counter = 0;
	
	
	public HashMap<String, Integer> getDirecciones(){
		return direcciones;
	}

    public AsignacionEspacio() {
		direcciones = new HashMap<String, Integer>();
    }
    public void procesa(Prog prog) {
        prog.decs().procesa(this);
        prog.insts().procesa(this);
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
        dec.tipo().procesa(this);
        dec.setTam(dec.tipo().tam());
        direcciones.put(dec.id().toString(), counter);
        counter += dec.tam();
    }
    public void procesa(DTipo dec) {
        dec.tipo().procesa(this);
    }
    public void procesa(DProc dec) {
        dec.pars().procesa(this);
        dec.bloque().procesa(this);
    }
    public void procesa(NoPars decs){
        // naa
    }
    public void procesa(ParsComp pars) {
        pars.pars().procesa(this);
        pars.par().procesa(this);
    }
    public void procesa(ParsSimp pars) {
        pars.par().procesa(this);
    }
    public void procesa(ParRef par) {
        par.tipo().procesa(this);
    }
    public void procesa(ParSinRef par) {
        par.tipo().procesa(this);
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
    public void procesa(Null exp) {

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

            