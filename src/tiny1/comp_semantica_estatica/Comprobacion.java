package tiny1.comp_semantica_estatica;

import tiny1.asint.Procesamiento;
import tiny1.asint.TinyASint;
import tiny1.asint.TinyASint.*;
import tiny1.errors.GestionErrores;

public class Comprobacion implements Procesamiento{

    boolean ok;
    boolean verbose;
    public Comprobacion(boolean verbose){
        ok = true;
        this.verbose = verbose;
    }

    public boolean isOk(){return ok;}

    private void log(String msg) {
        if(verbose) {
            System.out.println("Comprobacion: " + msg);
        }
    }
    @Override
    public void procesa(Prog exp) {
        exp.decs().procesa(this);
        if (isOk()){
            exp.insts().procesa(this);
        }else
            GestionErrores.errorDeclaracionesInadecuadas();
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
        if (isOk())
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
        exp.tipo().procesa(this);
        int dim = Integer.parseInt(exp.num().toString()); 
        if (dim <= 0){
            ok &= false;
            GestionErrores.errorDimensionInadecuada(exp.num());
        }
        exp.setDim(dim);
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
    }

    @Override
    public void procesa(POINTER exp) {
        // TODO porque se entra en bucle infinito?
        //exp.tipo().procesa(this);
    }

    @Override
    public void procesa(NoInsts exp) {
        // naa
    }

    @Override
    public void procesa(InstsComp exp) {
        exp.insts().procesa(this);
        exp.inst().procesa(this);
        if (!(exp.insts().getOk() && exp.inst().getOk())){
            ok &= false;
            exp.setOk(false);
        }
    }

    @Override
    public void procesa(InstsSimp exp) {
        exp.inst().procesa(this);
        if (!exp.inst().getOk()){
            ok &= false;
            exp.setOk(false);
        }
    }

    @Override
    public void procesa(IAsig exp) {
        exp.exp0().procesa(this);
        exp.exp1().procesa(this);
        if (!exp.exp0().esDesignador()){
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorExpresionNoDesignador(exp.exp0().str());
        }
        else {
            log("Asignacion:" + exp.exp0().getTipo() + " =? " + exp.exp1().getTipo());
            if (!TypeCompatibilidad.comprobar(exp.exp0().getTipo(), exp.exp1().getTipo())){
                ok &= false;
                exp.setOk(false);
                GestionErrores.errorAsignacionIncompatible(exp.exp0().str());
            }
        }
    }

    @Override
    public void procesa(IIfThen exp) {
        exp.exp().procesa(this);
        exp.insts().procesa(this);
        if(exp.exp().getType() != Type.BOOL){
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorExpresionTipoInadecuado(exp.exp().str());            
        }else{
            if (!exp.insts().getOk()){
                ok &= false;
                exp.setOk(false);
            }
        }
    }

    @Override
    public void procesa(IIfThenElse exp) {
        exp.exp().procesa(this);
        exp.insts0().procesa(this);
        exp.insts1().procesa(this);
        if(exp.exp().getType() != Type.BOOL){
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorExpresionTipoInadecuado(exp.exp().str());            
        }else{
            if (!exp.insts0().getOk() || !exp.insts1().getOk()){
                ok &= false;
                exp.setOk(false);
            }
        }
    }

    @Override
    public void procesa(IWhile exp) {
        exp.exp().procesa(this);
        exp.insts().procesa(this);
        if (exp.exp().getType() != Type.BOOL){
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorIntruccionConElementosInadecuados(exp.exp().str());
        }
        if (!exp.insts().getOk()){
            ok &= false;
            exp.setOk(false);
        }
    }

    @Override
    public void procesa(IRead exp) {
        exp.exp().procesa(this);
        if (!exp.exp().esDesignador()){      
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorExpresionNoDesignador(exp.exp().str());
        }
        else{ 
            switch (exp.exp().getType()){
                case INT:
                case REAL:
                case STRING:
                    break;
                default:
                    ok &= false;
                    exp.setOk(false);
                    GestionErrores.errorIntruccionConElementosInadecuados(exp.exp().str());
                    break;
            }
        }
    }

    @Override
    public void procesa(IWrite exp) {
        exp.exp().procesa(this);
        switch (exp.exp().getType()){
            case INT:
            case REAL:
            case STRING:
            case BOOL:
                break;
            default:
                ok &= false;
                exp.setOk(false);
                GestionErrores.errorIntruccionConElementosInadecuados(exp.exp().str());
                break;
        }
    }

    @Override
    public void procesa(INew exp) {
        exp.exp().procesa(this);
        if (exp.exp().getType() != Type.POINTER){
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorIntruccionConElementosInadecuados(exp.exp().str());
        }
    }

    @Override
    public void procesa(IDelete exp) {
        exp.exp().procesa(this);
        if (exp.exp().getType() != Type.POINTER){
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorIntruccionConElementosInadecuados(exp.exp().str());
        }
    }

    @Override
    public void procesa(INl exp) {
        // naa
    }

    @Override
    public void procesa(ICall exp) {
        exp.exps().procesa(this);
        Exps exps = exp.exps();
        DProc proc = (DProc)exp.vinculo();
        Pars pars = proc.pars();

        if (exps.exp() == null && pars.par() == null){
            return;
        }
        if ((exps.exp() != null && pars.par() == null) || (exps.exp() == null && pars.par() != null)){
            exp.setOk(false);
            ok &= false;
            GestionErrores.errorParametrosNoCoinciden(exp.id());
            return;
        }
        do {
            if (!TypeCompatibilidad.comprobar(exps.exp().getTipo(), pars.par().tipo())){
                ok &= false;
                exp.setOk(false);
                GestionErrores.errorParametrosNoCoinciden(exp.id());
            }
            if (pars.par().esReferencia() && !exps.exp().esDesignador()){
                ok &= false;
                exp.setOk(false);
                GestionErrores.errorExpresionNoDesignador(exp.id());
            }
            exps = exps.exps();
            pars = pars.pars();
        } while (exps != null && pars != null);
        if (!(exps == null && pars == null)){
            exp.setOk(false);
            ok &= false;
            GestionErrores.errorParametrosNoCoinciden(exp.id());
        }
    }

    @Override
    public void procesa(Bloque exp) {
        exp.prog().procesa(this);
        if(!exp.prog().insts().getOk()){
            ok &= false;
            exp.setOk(false);
        }
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

    private void operadorAritmetico(Exp exp, Exp arg0, Exp arg1){
        if (arg0.getType() == Type.INT && arg1.getType() == Type.INT)
            exp.setTipo(TinyASint.TypeInt);
        else if ((arg0.getType() == Type.REAL || arg0.getType() == Type.INT) && (arg1.getType() == Type.REAL || arg1.getType() == Type.INT))
            exp.setTipo(TinyASint.TypeReal);
        else
            exp.setTipo(TinyASint.TypeError);
    }

    private void operadorLogico(Exp exp, Exp arg0, Exp arg1){
        if (arg0.getType() == Type.BOOL && arg1.getType() == Type.BOOL)
            exp.setTipo(TinyASint.TypeBool);
        else
            exp.setTipo(TinyASint.TypeError);
    }
    private void operadorRelacional(Exp exp, Exp arg0, Exp arg1){
        if (arg0.getType() == Type.BOOL && arg1.getType() == Type.BOOL)
            exp.setTipo(TinyASint.TypeBool);
        else if (arg0.getType() == Type.STRING && arg1.getType() == Type.STRING)
            exp.setTipo(TinyASint.TypeBool);
        else if ((arg0.getType() == Type.REAL || arg0.getType() == Type.INT) && (arg1.getType() == Type.REAL || arg1.getType() == Type.INT))
            exp.setTipo(TinyASint.TypeBool);
        else
            exp.setTipo(TinyASint.TypeError);
    }
    private void operadorRelacionalPlus(Exp exp, Exp arg0, Exp arg1){
        if (arg0.getType() == Type.BOOL && arg1.getType() == Type.BOOL)
            exp.setTipo(TinyASint.TypeBool);
        else if (arg0.getType() == Type.STRING && arg1.getType() == Type.STRING)
            exp.setTipo(TinyASint.TypeBool);
        else if ((arg0.getType() == Type.REAL || arg0.getType() == Type.INT) && (arg1.getType() == Type.REAL || arg1.getType() == Type.INT))
            exp.setTipo(TinyASint.TypeBool);
        else if ((arg0.getType() == Type.POINTER || arg0.getType() == Type.NULL) && (arg1.getType() == Type.POINTER || arg1.getType() == Type.NULL))
            exp.setTipo(TinyASint.TypeBool);
        else
            exp.setTipo(TinyASint.TypeError);
    }

    @Override
    public void procesa(Suma exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorAritmetico(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(Resta exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorAritmetico(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(And exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorLogico(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(Or exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorLogico(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(LT exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorRelacional(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(GT exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorRelacional(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(LE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorRelacional(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(GE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorRelacional(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(NE exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorRelacionalPlus(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(EQ exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorRelacionalPlus(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(Mul exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorAritmetico(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(Mod exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        if (exp.arg0().getType() == Type.INT && exp.arg1().getType() == Type.INT)
            exp.setTipo(TinyASint.TypeInt);
        else
            exp.setTipo(TinyASint.TypeError);
    }

    @Override
    public void procesa(Div exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        operadorAritmetico(exp, exp.arg0(), exp.arg1());
    }

    @Override
    public void procesa(Not exp) {
        exp.arg().procesa(this);
        if (exp.arg().getType() == Type.BOOL)
            exp.setTipo(TinyASint.TypeBool);
        else
            exp.setTipo(TinyASint.TypeError);
    }

    @Override
    public void procesa(Neg exp) {
        exp.arg().procesa(this);
        if (exp.arg().getType() == Type.INT)
            exp.setTipo(TinyASint.TypeInt);
        else if (exp.arg().getType() == Type.REAL)
            exp.setTipo(TinyASint.TypeReal);
        else
            exp.setTipo(TinyASint.TypeError);
    }

    @Override
    public void procesa(Index exp) {
        exp.arg0().procesa(this);
        exp.arg1().procesa(this);
        if (exp.arg0().getType() == Type.ARRAY && exp.arg1().getType() == Type.INT){
            ARRAY a = (ARRAY)exp.arg0().getTipo();
            exp.setTipo(a.tipo());
        }
        else
            exp.setTipo(TinyASint.TypeError);
        log(exp.str().col() + " " + exp.str().fila() + " " + exp.arg0().str() + "["+ exp.arg1().str() + "]: " + exp.getTipo());
    }

    @Override
    public void procesa(Ptr exp) {
        exp.exp().procesa(this);
        if(exp.exp().getType() == Type.POINTER){
            POINTER p = (POINTER)exp.exp().getTipo();
            if (p.tipo().type() == Type.RECORD){
                REGISTRO r = (REGISTRO)p.tipo();
                if (r.getList().containsKey(exp.id().toString())){
                    exp.setTipo(r.getList().get(exp.id().toString()).tipo());
                }
                else 
                    exp.setTipo(TinyASint.TypeError); 
            }
            else 
                exp.setTipo(TinyASint.TypeError); 
        }
        else 
            exp.setTipo(TinyASint.TypeError);
        log(exp.str().col() + " " + exp.str().fila() + " " + exp.exp().str() + "->"+ exp.id() + ": " + exp.getTipo());
    }

    @Override
    public void procesa(Atr exp) {
        exp.exp().procesa(this);
        if (exp.exp().getType() == Type.RECORD){
            REGISTRO r = (REGISTRO)exp.exp().getTipo();
            if (r.getList().containsKey(exp.id().toString())){
                exp.setTipo(r.getList().get(exp.id().toString()).tipo());
            }
            else 
                exp.setTipo(TinyASint.TypeError); 
        }
        else 
            exp.setTipo(TinyASint.TypeError); 
        log(exp.str().col() + " " + exp.str().fila() + " " + exp.exp().str() + "->"+ exp.id() + ": " + exp.getTipo());
    }

    @Override
    public void procesa(Indir exp) {
        exp.arg().procesa(this);
        if (exp.arg().getType() == Type.POINTER){
            POINTER p = (POINTER)exp.arg().getTipo();
            exp.setTipo(p.tipo());
        }
        else
            exp.setTipo(TinyASint.TypeError);
        log(exp.str().col() + " " + exp.str().fila() + " *" + exp.arg().str() + ": " + exp.getTipo());
    }

    @Override
    public void procesa(Parentesis exp) {
        exp.arg().procesa(this);
        exp.setTipo(exp.arg().getTipo());
    }

    @Override
    public void procesa(Ent exp) {
        exp.setTipo(TinyASint.TypeInt);
    }

    @Override
    public void procesa(IdenExp exp) {
        exp.setTipo(exp.vinculo().tipo());
        log(exp.str().fila() + " " + exp.str().col() + " "  + " " + exp.str().toString() + ": " + exp.getTipo());
    }

    @Override
    public void procesa(Lreal exp) {
        exp.setTipo(TinyASint.TypeReal);
    }

    @Override
    public void procesa(True exp) {
        exp.setTipo(TinyASint.TypeBool);
    }

    @Override
    public void procesa(False exp) {
        exp.setTipo(TinyASint.TypeBool);
    }

    @Override
    public void procesa(Cadena exp) {
        exp.setTipo(TinyASint.TypeString);
    }

    @Override
    public void procesa(Nnull exp) {
        exp.setTipo(TinyASint.TypeNull);
    }
    
}
