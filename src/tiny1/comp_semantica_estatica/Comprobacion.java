package tiny1.comp_semantica_estatica;

import tiny1.asint.Procesamiento;
import tiny1.asint.TinyASint.*;
import tiny1.errors.GestionErrores;

public class Comprobacion implements Procesamiento{

    boolean ok;
    public Comprobacion(){
        ok = true;
    }

    @Override
    public void procesa(Prog exp) {
        exp.decs().procesa(this);
        exp.insts().procesa(this);
    }

    @Override
    public void procesa(NoDecs exp) {
        // skip
    }

    @Override
    public void procesa(AuxDecs exp) {
        exp.decs().procesa(this);
    }

    @Override
    public void procesa(LDecSimp exp) {
        exp.dec().procesa(this);
    }

    @Override
    public void procesa(LDecComp exp) {
        exp.decs().procesa(this);
        exp.dec().procesa(this);
    }

    @Override
    public void procesa(DVar exp) {
        exp.tipo().procesa(this);
    }

    @Override
    public void procesa(DTipo exp) {
        exp.tipo().procesa(this);
    }

    @Override
    public void procesa(DProc exp) {
        exp.pars().procesa(this);
        exp.bloque().procesa(this);
    }

    @Override
    public void procesa(NoPars exp) {
        // skip
    }

    @Override
    public void procesa(ParsComp exp) {
        exp.pars().procesa(this);
        exp.par().procesa(this);
    }

    @Override
    public void procesa(ParsSimp exp) {
        exp.par().procesa(this);
    }

    @Override
    public void procesa(ParRef exp) {
        exp.tipo().procesa(this);
    }

    @Override
    public void procesa(ParSinRef exp) {
        exp.tipo().procesa(this);
    }

    @Override
    public void procesa(INT exp) {
        // naa
    }

    @Override
    public void procesa(REAL exp) {
        // naa
    }

    @Override
    public void procesa(BOOL exp) {
        // naa
    }

    @Override
    public void procesa(STRING exp) {
        // naa
    }

    @Override
    public void procesa(IdenTipo exp) {
        // naa
    }

    @Override
    public void procesa(ARRAY exp) {
        int dim = Integer.parseInt(exp.num().toString()); 
        if (dim <= 0){
            ok &= false;
            GestionErrores.errorDimensionInadecuada(exp.num());
        }
        exp.setDim(dim);
        exp.tipo().procesa(this);
    }

    @Override
    public void procesa(REGISTRO exp) {
        exp.campos().procesa(this);
    }

    public void procesa(OK exp){
        // nothing to do  
    }
    
    public void procesa(ERROR exp){
        // nothing to do  
    }

    @Override
    public void procesa(CamposSimp exp) {
        exp.campo().procesa(this);
    }

    @Override
    public void procesa(CamposComp exp) {
        exp.campos().procesa(this);
        exp.campo().procesa(this);
    }

    @Override
    public void procesa(Campo exp) {
        exp.tipo().procesa(this);
    }

    @Override
    public void procesa(POINTER exp) {
        exp.tipo().procesa(this);
    }

    @Override
    public void procesa(NoInsts exp) {
        // naa
    }

    @Override
    public void procesa(InstsComp exp) {
        exp.insts().procesa(this);
        exp.inst().procesa(this);
    }

    @Override
    public void procesa(InstsSimp exp) {
        exp.inst().procesa(this);
    }

    @Override
    public void procesa(IAsig exp) {
        exp.exp0().procesa(this);
        exp.exp1().procesa(this);
    }

    @Override
    public void procesa(IIfThen exp) {
        exp.exp().procesa(this);
        exp.insts().procesa(this);
    }

    @Override
    public void procesa(IIfThenElse exp) {
        exp.exp().procesa(this);
        exp.insts0().procesa(this);
        exp.insts1().procesa(this);
    }

    @Override
    public void procesa(IWhile exp) {
        exp.exp().procesa(this);
        exp.insts().procesa(this);
    }

    @Override
    public void procesa(IRead exp) {
        exp.exp().procesa(this);
    }

    @Override
    public void procesa(IWrite exp) {
        exp.exp().procesa(this);
    }

    @Override
    public void procesa(INew exp) {
        exp.exp().procesa(this);
    }

    @Override
    public void procesa(IDelete exp) {
        exp.exp().procesa(this);
    }

    @Override
    public void procesa(INl exp) {
        //
    }

    @Override
    public void procesa(ICall exp) {
        exp.exps().procesa(this);
    }

    @Override
    public void procesa(Bloque exp) {
        exp.prog().procesa(this);
    }

    @Override
    public void procesa(NoExps exp) {
        // naa
    }

    @Override
    public void procesa(Exps1 exp) {
        exp.exps().procesa(this);
        exp.exp().procesa(this);
    }

    @Override
    public void procesa(Exps0 exp) {
        exp.exp().procesa(this);
    }

    @Override
    public void procesa(Suma exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(Resta exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(And exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(Or exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(LT exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(GT exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(LE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(GE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(NE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(EQ exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(Mul exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(Mod exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(Div exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(Not exp) {
        exp.arg().procesa(this);
    }

    @Override
    public void procesa(Neg exp) {
        exp.arg().procesa(this);
    }

    @Override
    public void procesa(Index exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
    }

    @Override
    public void procesa(Ptr exp) {
        exp.exp().procesa(this);
    }

    @Override
    public void procesa(Atr exp) {
        exp.exp().procesa(this);
    }

    @Override
    public void procesa(Indir exp) {
        exp.arg().procesa(this);
    }

    @Override
    public void procesa(Parentesis exp) {
        exp.arg().procesa(this);
    }

    @Override
    public void procesa(Ent exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IdenExp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Lreal exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(True exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(False exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Cadena exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Null exp) {
        // TODO Auto-generated method stub
        
    }
    
}
