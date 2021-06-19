package tiny1.asint;
import tiny1.asint.TinyASint.*;
public class ProcesamientoPorDefecto implements Procesamiento {
    public ProcesamientoPorDefecto() {
    }
    public void procesa(Prog prog) {
        prog.decs().procesa(this);
        prog.insts().procesa(this);
    }
    public void procesa(NoDecs decs){
        // naa
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
        // naa
    }
    public void procesa(REAL t) {
        // naa
    }
    public void procesa(BOOL t) {
        // naa
    }
    public void procesa(STRING t) {
        // naa
    }
    public void procesa(IdenTipo t) {
        // naa
    }
    public void procesa(OK exp){
        // naa
    }
    public void procesa(ERROR exp){
        // naa
    }
    public void procesa(NULL exp){
        // naa
    }
    public void procesa(ARRAY t) {
        t.tipo().procesa(this);
    }
    public void procesa(REGISTRO t) {
        t.campos().procesa(this);
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
    }
    public void procesa(POINTER t) {
        t.tipo().procesa(this);
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
    }
    public void procesa(IAsig i) {
        i.exp0().procesa(this);
        i.exp1().procesa(this);
    }
    public void procesa(IIfThen i) {
        i.exp().procesa(this);
        i.insts().procesa(this);
    }
    public void procesa(IIfThenElse i) {
        i.exp().procesa(this);
        i.insts0().procesa(this);
        i.insts1().procesa(this);
    }
    public void procesa(IWhile i) {
        i.exp().procesa(this);
        i.insts().procesa(this);
    }
    public void procesa(IRead i) {
        i.exp().procesa(this);
    }
    public void procesa(IWrite i) {
        i.exp().procesa(this);
    }
    public void procesa(INew i) {
        i.exp().procesa(this);
    }
    public void procesa(IDelete i) {
        i.exp().procesa(this);
    }
    public void procesa(INl i) {
        // naa
    }
    public void procesa(ICall i) {
        i.exps().procesa(this);
    }
    public void procesa(Bloque i) {
        i.prog().procesa(this);
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
    public void procesa(And exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(Or exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(LT exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(GT exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(LE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(GE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(NE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(EQ exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(Mod exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(Not exp) {
        exp.arg().procesa(this);
    }
    public void procesa(Neg exp) {
        exp.arg().procesa(this);
    }
    public void procesa(Index exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(Ptr exp) {
        exp.exp().procesa(this);
    }
    public void procesa(Atr exp) {
        exp.exp().procesa(this);
    }
    public void procesa(Indir exp) {
        exp.arg().procesa(this);
    }
    public void procesa(Parentesis exp) {
        exp.arg().procesa(this);
    }
    public void procesa(Ent exp) {
        // naa
    }
    public void procesa(IdenExp exp) {
        // naa
    }
    public void procesa(Lreal exp) {
        // naa
    }
    public void procesa(True exp) {
        // naa
    }
    public void procesa(False exp) {
        // naa
    }
    public void procesa(Cadena exp) {
        // naa
    }
    public void procesa(Null exp) {
        // naa
    }
    public void procesa(Suma exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(Resta exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(Mul exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }
    public void procesa(Div exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }   
}   

            