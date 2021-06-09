package tiny1.comp_semantica_estatica;

import tiny1.asint.Procesamiento;
import tiny1.asint.ProcesamientoPorDefecto;
import tiny1.asint.TinyASint.*;

public class Comprobacion implements Procesamiento{

    private Simplificacion simp;
    public Comprobacion(){
        simp = new Simplificacion();
    }

    private class Simplificacion extends ProcesamientoPorDefecto{
        
    }
    @Override
    public void procesa(ProgConDecs exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(NoDecs exp) {
        // TODO
    }

    @Override
    public void procesa(ProgSinDecs exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(LDecSimp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(LDecComp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(DVar exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(DTipo exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(DProcConPars exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(DProcSinPars exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(ParsComp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(ParsSimp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(ParRef exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(ParSinRef exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(INT exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(REAL exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(BOOL exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(STRING exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IdenTipo exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(ARRAY exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(REGISTRO exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(CamposSimp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(CamposComp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Campo exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(POINTER exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(InstsComp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(InstsSimp exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IAsig exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IIfThen1 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IIfThen0 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IIfThenElse11 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IIfThenElse10 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IIfThenElse01 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IIfThenElse00 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IWhile1 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IWhile0 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IRead exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IWrite exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(INew exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(IDelete exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(INl exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(ICall1 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(ICall0 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Bloque1 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Bloque0 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Exps1 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Exps0 exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Suma exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Resta exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(And exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Or exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(LT exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(GT exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(LE exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(GE exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(NE exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(EQ exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Mul exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Mod exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Div exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Not exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Neg exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Index exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Ptr exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Atr exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Indir exp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procesa(Parentesis exp) {
        // TODO Auto-generated method stub
        
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
