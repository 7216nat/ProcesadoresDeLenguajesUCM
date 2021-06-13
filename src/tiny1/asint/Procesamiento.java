package tiny1.asint;
import tiny1.asint.TinyASint.*;
public interface Procesamiento {
    void procesa(Prog exp);
    void procesa(NoDecs exp);
    void procesa(AuxDecs exp);
    void procesa(LDecSimp exp);
    void procesa(LDecComp exp);
    void procesa(DVar exp);
    void procesa(DTipo exp);
    void procesa(DProc exp);
    void procesa(NoPars exp);
    void procesa(ParsComp exp);
    void procesa(ParsSimp exp);
    void procesa(ParRef exp);
    void procesa(ParSinRef exp);
    void procesa(INT exp);
    void procesa(REAL exp);
    void procesa(BOOL exp);
    void procesa(STRING exp);
    void procesa(IdenTipo exp);
    void procesa(ARRAY exp);
    void procesa(REGISTRO exp);
    void procesa(CamposSimp exp);
    void procesa(CamposComp exp);
    void procesa(Campo exp);
    void procesa(POINTER exp);
    void procesa(NoInsts exp);
    void procesa(InstsComp exp);
    void procesa(InstsSimp exp);
    void procesa(IAsig exp);
    void procesa(IIfThen exp);
    void procesa(IIfThenElse exp);
    void procesa(IWhile exp);
    void procesa(IRead exp);
    void procesa(IWrite exp);
    void procesa(INew exp);
    void procesa(IDelete exp);
    void procesa(INl exp);
    void procesa(ICall exp);
    void procesa(Bloque exp);
    void procesa(NoExps exp);
    void procesa(Exps1 exp);
    void procesa(Exps0 exp);
    void procesa(Suma exp);
    void procesa(Resta exp);
    void procesa(And exp);
    void procesa(Or exp);
    void procesa(LT exp);
    void procesa(GT exp);
    void procesa(LE exp);
    void procesa(GE exp);
    void procesa(NE exp);
    void procesa(EQ exp);
    void procesa(Mul exp);
    void procesa(Mod exp);
    void procesa(Div exp);
    void procesa(Not exp);
    void procesa(Neg exp);
    void procesa(Index exp);
    void procesa(Ptr exp);
    void procesa(Atr exp);
    void procesa(Indir exp);
    void procesa(Parentesis exp);
    void procesa(Ent exp);
    void procesa(IdenExp exp);
    void procesa(Lreal exp);
    void procesa(True exp);
    void procesa(False exp);
    void procesa(Cadena exp);
    void procesa(Null exp);
}