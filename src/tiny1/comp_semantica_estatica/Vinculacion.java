package tiny1.comp_semantica_estatica;
import java.util.List;
import java.util.Stack;
import java.util.HashMap;
import java.util.ArrayList;
import tiny1.asint.TinyASint.*;
import tiny1.asint.Procesamiento;
import tiny1.asint.ProcesamientoPorDefecto;
import tiny1.errors.GestionErrores;

public class Vinculacion implements Procesamiento{

    private int currBloque;
    private List<HashMap<String, Dec>> pilaAnidada;
    private Stack<HashMap<String, Campo>> camposList;
    private VinculacionRef crefs;
    private boolean ok;
    private boolean verbose;
    public Vinculacion(boolean verbose){

        currBloque = -1;
        ok = true;
        inic();
        crefs = new VinculacionRef();
        this.verbose = verbose;
    }
    public boolean isOk(){
        return ok;
    }

    private void inic(){
        pilaAnidada = new ArrayList<>();
        camposList = new Stack<>();
    }

    private void abreBloque(){
        pilaAnidada.add(new HashMap<>());
        currBloque++;
    }

    private void log(String msg) {
        if(verbose) {
            System.out.println("Vinculacion: " + msg);
        }
    }
    private void cierraBloque(){
        log(currBloque + "  " + pilaAnidada.get(currBloque).toString());
        pilaAnidada.remove(currBloque);
        currBloque--;
    }

    private boolean insertaId(String id, Dec dec){
        if (pilaAnidada.get(currBloque).containsKey(id)){
            return false;
        }
        pilaAnidada.get(currBloque).put(id, dec);
        return true;
    }

    private void checkId(StringLocalizado str, String id, Dec dec){
        if (!insertaId(id, dec)){
            ok &= false;
            GestionErrores.errorVinculacionDuplicado(str);
        }
        dec.setAmbito(currBloque);
    }
    private Dec buscaId(String id){
        for(int i = pilaAnidada.size()-1; i>=0; i--){
            if (pilaAnidada.get(i).containsKey(id)){
                return pilaAnidada.get(i).get(id);
            } 
        }
        return null;
    }

    private class VinculacionRef extends ProcesamientoPorDefecto{
        @Override
        public void procesa(IdenTipo exp) {
            Dec dec = buscaId(exp.str().toString());
            if (dec != null){
                if (dec.decType() != DecType.TYPE){
                    ok &= false;
                    GestionErrores.errorVinculacionDeclaracionTipoInadecuado(exp.str());
                }
                else {
                    exp.setVinculo(dec);
                    log("->VinculacionRef: "+exp.str().fila() + " " + exp.str().col()+ " " +exp.str().toString() + " --> "+dec.toString());
                }
            } else {
                ok &= false;
                GestionErrores.errorVinculacionTipoInexistennte(exp.str());
            }
        }
    }
    
    @Override
    public void procesa(Prog exp) {
        abreBloque();
        exp.decs().procesa(this);
        exp.decs().procesa(crefs);
        exp.insts().procesa(this);
        cierraBloque();
    }

    @Override
    public void procesa(NoDecs exp) {
        // naa
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
        checkId(exp.id(), exp.id().toString(), exp);
    }

    @Override
    public void procesa(DTipo exp) {
        exp.tipo().procesa(this);
        checkId(exp.id(), exp.id().toString(), exp);    
    }

    @Override
    public void procesa(DProc exp) {
        checkId(exp.id(), exp.id().toString(), exp);
        exp.setAmbito(currBloque);
        abreBloque();
        exp.pars().procesa(this);
        Bloque b = (Bloque)exp.bloque();
        b.prog().decs().procesa(this);
        b.prog().insts().procesa(this);
        cierraBloque();
    }

    @Override
    public void procesa(NoPars exp) {
        // naa
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
        checkId(exp.id(), exp.id().toString(), exp);
    }

    @Override
    public void procesa(ParSinRef exp) {
        exp.tipo().procesa(this);
        checkId(exp.id(), exp.id().toString(), exp);
    }

    @Override
    public void procesa(INT exp) {
        // nothing to do        
    }

    @Override
    public void procesa(REAL exp) {
        // nothing to do        
    }

    @Override
    public void procesa(BOOL exp) {
        // nothing to do        
    }

    @Override
    public void procesa(STRING exp) {
        // nothing to do  
    }

    @Override
    public void procesa(IdenTipo exp) {
        // nothing to do at first round
    }

    @Override
    public void procesa(ARRAY exp) {
        exp.tipo().procesa(this);
    }

    @Override
    public void procesa(REGISTRO exp) {
        camposList.add(new HashMap<>());
        exp.campos().procesa(this);
        exp.setList(camposList.pop());
    }
    public void procesa(OK exp){
        // nothing to do  
    }
    public void procesa(ERROR exp){
        // nothing to do  
    }
    public void procesa(NULL exp){
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
        if (camposList.peek().containsKey(exp.id().toString())){
            ok &= false;
            GestionErrores.errorCampoRegistroDuplicado(exp.id());
        }
        else camposList.peek().put(exp.id().toString(), exp);
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
        // nothing to do
    }

    @Override
    public void procesa(ICall exp) {
        Dec dec = buscaId(exp.id().toString());
        if (dec == null){
            ok &= false;
            GestionErrores.errorVinculacionVariableInexistennte(exp.id());
        }
        else{
            if (dec.decType() != DecType.PROC){
                ok &= false;
                GestionErrores.errorVinculacionDeclaracionTipoInadecuado(exp.id());
            }
            else {
                log("->VinculacionNormal: "+exp.id().fila() + " " + exp.id().col()+ " " +exp.id().toString() + " --> "+dec.toString());
                exp.setVinculo(dec);
            }
        }
        exp.exps().procesa(this);
    }

    @Override
    public void procesa(Bloque exp) {
        abreBloque();
        exp.prog().decs().procesa(this);
        exp.prog().insts().procesa(this);
        cierraBloque();
    }

    @Override
    public void procesa(NoExps exp) {
        // naa
    }

    @Override
    public void procesa(Exps1 exp) {
        exp.exp().procesa(this);
        exp.exps().procesa(this);
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
        // nothing to do
    }

    @Override
    public void procesa(IdenExp exp) {
        Dec dec = buscaId(exp.str().toString());
        if (dec != null){
            if (dec.decType() != DecType.VAR){
                ok &= false;
                GestionErrores.errorVinculacionDeclaracionTipoInadecuado(exp.str());
            }
            else {
                log("->VinculacionNormal: "+exp.str().fila() + " " + exp.str().col()+ " " +exp.str().toString() + " --> "+dec.toString());
                exp.setVinculo(dec);
            }
        }
        else {
            ok &= false;
            GestionErrores.errorVinculacionVariableInexistennte(exp.str());
        }
    }

    @Override
    public void procesa(Lreal exp) {
        // nothing to do
    }

    @Override
    public void procesa(True exp) {
        // nothing to do
    }

    @Override
    public void procesa(False exp) {
        // nothing to do
    }

    @Override
    public void procesa(Cadena exp) {
        // nothing to do
    }

    @Override
    public void procesa(Nnull exp) {
        // nothing to do
    }
}