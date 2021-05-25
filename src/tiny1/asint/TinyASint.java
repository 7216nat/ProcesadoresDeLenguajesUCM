package tiny1.asint;

public class TinyASint {
    
    public static class StringLocalizado {
        private String s;
        private int fila;
        private int col;
        public StringLocalizado(String s, int fila, int col) {
            this.s = s;
            this.fila = fila;
            this.col = col;
        }
        public int fila() {return fila;}
        public int col() {return col;}
        public String toString() {
        return s;
        }
        public boolean equals(Object o) {
            return (o == this) || (
                    (o instanceof StringLocalizado) &&
                    (((StringLocalizado)o).s.equals(s)));                
        }
        public int hashCode() {
            return s.hashCode();
        }
    }

    public static abstract class Exp  {
        public Exp() {}   
        public abstract int prioridad();
        public abstract void procesa(Procesamiento procesamiento);
    }
    
    private static abstract class ExpBin extends Exp {
        private Exp arg0;
        private Exp arg1;
        public Exp arg0() {return arg0;}
        public Exp arg1() {return arg1;}
        public ExpBin(Exp arg0, Exp arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
    }

    private static abstract class ExpUni extends Exp {
        private Exp arg;
        public Exp arg() {return arg;}
        public ExpUni(Exp arg) {
            super();
            this.arg = arg;
        }
    }

    private static abstract class ExpAditiva extends ExpBin {
        public ExpAditiva(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 0;
        }
    }
    
    public static class Suma extends ExpAditiva {
        public Suma(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class Resta extends ExpAditiva {
        public Resta(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }

    private static abstract class ExpLogico extends ExpBin {
        public ExpLogico(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 1;
        }
    }

    public static class And extends ExpLogico {
        public And(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class Or extends ExpLogico {
        public Or(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }

    private static abstract class ExpComparativa extends ExpBin {
        public ExpComparativa(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 2;
        }
    }

    public static class LT extends ExpComparativa {
        public LT(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class GT extends ExpComparativa {
        public GT(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class LE extends ExpComparativa {
        public LE(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class GE extends ExpComparativa {
        public GE(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class EQ extends ExpComparativa {
        public EQ(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class NE extends ExpComparativa {
        public NE(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }

    private static abstract class ExpMultiplicativa extends ExpBin {
        public ExpMultiplicativa(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 3;
        }
    }
    
    public static class Mul extends ExpMultiplicativa {
        public Mul(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class Div extends ExpMultiplicativa {
        public Div(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class Mod extends ExpMultiplicativa {
        public Mod(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }

    private static abstract class ExpPre extends ExpUni {
        public ExpPre(Exp arg) {
            super(arg);
        }
        public final int prioridad() {
            return 4;
        }
    }

    public static class Not extends ExpPre {
        public Not(Exp arg) {
            super(arg);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class Neg extends ExpPre {
        public Neg(Exp arg) {
            super(arg);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }

    private static abstract class ExpAcc extends Exp{
        private StringLocalizado id;
        private Exp exp;
        public ExpAcc(Exp exp, StringLocalizado id) {
            super();
            this.id = id;
            this.exp = exp;
        }
        public StringLocalizado id(){return id;}
        public Exp exp(){return exp;}
        public final int prioridad() {
            return 5;
        }
        public abstract void procesa(Procesamiento procesamiento);
    }

    public static class Index extends ExpBin {
        public Index(Exp exp0, Exp exp1) {
            super(exp0, exp1);
        }
        public final int prioridad() {
            return 5;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }    
    }
    public static class Atr extends ExpAcc {
        public Atr(Exp arg0, StringLocalizado arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
    public static class Ptr extends ExpAcc {
        public Ptr(Exp arg0, StringLocalizado arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }

    public static class Indir extends ExpUni {
        public Indir(Exp arg) {
            super(arg);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }    
        public final int prioridad() {
            return 6;
        }
    }

    public static class Parentesis extends ExpUni {
        public Parentesis(Exp arg) {
            super(arg);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }    
        public final int prioridad() {
            return 7;
        }
    }
    public static class Ent extends Exp {
        private StringLocalizado ent;
        public Ent(StringLocalizado ent) {
            super();
            this.ent = ent;
        }
        public StringLocalizado ent() {return ent;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    public static class IdenExp extends Exp{
        private StringLocalizado id;
        public IdenExp(StringLocalizado id) {
            super();
            this.id = id;
        }
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    public static class Lreal extends Exp {
        private StringLocalizado lreal;
        public Lreal(StringLocalizado lreal) {
            super();
            this.lreal = lreal;
        }
        public StringLocalizado lreal() {return lreal;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    public static class True extends Exp {
        private StringLocalizado bool;
        public True(StringLocalizado bool) {
            super();
            this.bool = bool;
        }
        public StringLocalizado bool() {return bool;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    public static class False extends Exp {
        private StringLocalizado bool;
        public False(StringLocalizado bool) {
            super();
            this.bool = bool;
        }
        public StringLocalizado bool() {return bool;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    public static class Cadena extends Exp {
        private StringLocalizado cadena;
        public Cadena(StringLocalizado cadena) {
            super();
            this.cadena = cadena;
        }
        public StringLocalizado cadena() {return cadena;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    public static class Null extends Exp {
        private StringLocalizado none;
        public Null(StringLocalizado none) {
            super();
            this.none = none;
        }
        public StringLocalizado none() {return none;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }

    public static abstract class Exps {
        public Exps(){
        }
        public abstract void procesa(Procesamiento p);
    }
    public static class Exps1 extends Exps {
        private Exp exp;
        private Exps exps;
        public Exps1(Exps exps, Exp exp) {
            super();
            this.exp = exp;
            this.exps = exps;
        }
        public Exp exp() {return exp;}
        public Exps exps() {return exps;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }     
    }
    public static class Exps0 extends Exps {
        private Exp exp;
        public Exps0(Exp exp) {
            super();
            this.exp = exp;
        }
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }     
    }    

    public static abstract class Insts {
        public Insts(){
        }
        public abstract void procesa(Procesamiento p);
    }

    public static class InstsComp extends Insts {
        private Inst inst;
        private Insts insts;
        public InstsComp(Insts insts, Inst inst) {
            super();
            this.inst = inst;
            this.insts = insts;
        }
        public Inst inst() {return inst;}
        public Insts insts() {return insts;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class InstsSimp extends Insts {
        private Inst inst;
        public InstsSimp(Inst inst) {
            super();
            this.inst = inst;
        }
        public Inst inst() {return inst;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    } 

    public static abstract class Inst {
        public Inst(){
        }
        public abstract void procesa(Procesamiento p);
    }

    public static class IAsig extends Inst {
        private Exp exp0;
        private Exp exp1;
        public IAsig(Exp exp0, Exp exp1) {
            super();
            this.exp0 = exp0;
            this.exp1 = exp1;
        }
        public Exp exp0() {return exp0;}
        public Exp exp1() {return exp1;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IIfThen1 extends Inst {
        private Exp exp;
        private Insts insts;
        public IIfThen1(Exp exp, Insts insts) {
            super();
            this.exp = exp;
            this.insts = insts;
        }
        public Exp exp() {return exp;}
        public Insts insts() {return insts;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IIfThen0 extends Inst {
        private Exp exp;
        public IIfThen0(Exp exp) {
            super();
            this.exp = exp;
        }
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IIfThenElse11 extends Inst {
        private Exp exp;
        private Insts insts0;
        private Insts insts1;
        public IIfThenElse11(Exp exp, Insts insts0, Insts insts1) {
            super();
            this.exp = exp;
            this.insts0 = insts0;
            this.insts1 = insts1;
        }
        public Exp exp() {return exp;}
        public Insts insts0() {return insts0;}
        public Insts insts1() {return insts1;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IIfThenElse10 extends Inst {
        private Exp exp;
        private Insts insts;
        public IIfThenElse10(Exp exp, Insts insts) {
            super();
            this.exp = exp;
            this.insts = insts;
        }
        public Exp exp() {return exp;}
        public Insts insts() {return insts;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IIfThenElse01 extends Inst {
        private Exp exp;
        private Insts insts;
        public IIfThenElse01(Exp exp, Insts insts) {
            super();
            this.exp = exp;
            this.insts = insts;
        }
        public Exp exp() {return exp;}
        public Insts insts() {return insts;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IIfThenElse00 extends Inst {
        private Exp exp;
        public IIfThenElse00(Exp exp) {
            super();
            this.exp = exp;
        }
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IWhile1 extends Inst {
        private Exp exp;
        private Insts insts;
        public IWhile1(Exp exp, Insts insts) {
            super();
            this.exp = exp;
            this.insts = insts;
        }
        public Exp exp() {return exp;}
        public Insts insts() {return insts;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IWhile0 extends Inst {
        private Exp exp;
        public IWhile0(Exp exp) {
            super();
            this.exp = exp;
        }
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IRead extends Inst {
        private Exp exp;
        public IRead(Exp exp) {
            super();
            this.exp = exp;
        }
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IWrite extends Inst {
        private Exp exp;
        public IWrite(Exp exp) {
            super();
            this.exp = exp;
        }
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class INew extends Inst {
        private Exp exp;
        public INew(Exp exp) {
            super();
            this.exp = exp;
        }
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class IDelete extends Inst {
        private Exp exp;
        public IDelete(Exp exp) {
            super();
            this.exp = exp;
        }
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class INl extends Inst {
        public INl(){
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }  
    }
    public static class ICall1 extends Inst {
        private StringLocalizado id;
        private Exps exps;
        public ICall1(StringLocalizado id, Exps exps) {
            super();
            this.id = id;
            this.exps = exps;
        }
        public StringLocalizado id() {return id;}
        public Exps exps() { return exps;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class ICall0 extends Inst {
        private StringLocalizado id;
        public ICall0(StringLocalizado id) {
            super();
            this.id = id;
        }
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class Bloque1 extends Inst {
        private Prog prog;
        public Bloque1(Prog prog) {
            super();
            this.prog = prog;
        }
        public Prog prog() {return prog;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    public static class Bloque0 extends Inst {
        public Bloque0() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    
    public static abstract class Tipo{
        public Tipo(){
        }
        public abstract void procesa(Procesamiento p);
    }

    public static class INT extends Tipo {
        private StringLocalizado str;
        public INT(StringLocalizado str){
            super();
            this.str = str;
        }
        public StringLocalizado str(){return str;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class REAL extends Tipo {
        private StringLocalizado str;
        public REAL(StringLocalizado str){
            super();
            this.str = str;
        }
        public StringLocalizado str(){return str;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class BOOL extends Tipo {
        private StringLocalizado str;
        public BOOL(StringLocalizado str){
            super();
            this.str = str;
        }
        public StringLocalizado str(){return str;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class STRING extends Tipo {
        private StringLocalizado str;
        public STRING(StringLocalizado str){
            super();
            this.str = str;
        }
        public StringLocalizado str(){return str;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class IdenTipo extends Tipo {
        private StringLocalizado str;
        public IdenTipo(StringLocalizado str){
            super();
            this.str = str;
        }
        public StringLocalizado str(){return str;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class ARRAY extends Tipo {
        private StringLocalizado num;
        private Tipo tipo;
        public ARRAY(StringLocalizado num, Tipo tipo){
            super();
            this.tipo = tipo;
            this.num = num;
        }
        public StringLocalizado num(){return num;}
        public Tipo tipo(){return tipo;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class REGISTRO extends Tipo {
        private Campos campos;
        public REGISTRO(Campos campos){
            super();
            this.campos = campos;
        }
        public Campos campos(){return campos;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }

    public static abstract class Campos{
        public Campos(){
        }
        public abstract void procesa(Procesamiento p);
    }

    public static class CamposComp extends Campos{
        private Campos campos;
        private Campo campo;
        public CamposComp(Campos campos, Campo campo){
            super();
            this.campos = campos;
            this.campo = campo;
        }
        public Campos campos(){return campos;}
        public Campo campo(){return campo;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class CamposSimp extends Campos{
        private Campo campo;
        public CamposSimp(Campo campo){
            super();
            this.campo = campo;
        }
        public Campo campo(){return campo;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class Campo{
        private Tipo tipo;
        private StringLocalizado id;
        public Campo(Tipo tipo, StringLocalizado id){
            this.tipo = tipo;
            this.id = id;
        }
        public Tipo tipo(){return tipo;}
        public StringLocalizado id(){return id;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }

    public static class POINTER extends Tipo {
        private Tipo tipo;
        public POINTER(Tipo tipo){
            super();
            this.tipo = tipo;
        }
        public Tipo tipo(){return tipo;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }

    public static abstract class Decs {
        public Decs() {
        }
        public abstract void procesa(Procesamiento p);
    }
    public static abstract class Dec {
        public Dec() {
        }
        public abstract void procesa(Procesamiento p);
    }
    public static class DVar extends Dec  {
        private Tipo tipo;
        private StringLocalizado id;
        public DVar(Tipo tipo, StringLocalizado id) {
            this.id = id;
            this.tipo = tipo;
        }
        public StringLocalizado id() {return id;}
        public Tipo tipo() {return tipo;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class DTipo extends Dec  {
        private Tipo tipo;
        private StringLocalizado id;
        public DTipo(Tipo tipo, StringLocalizado id) {
            this.id = id;
            this.tipo = tipo;
        }
        public StringLocalizado id() {return id;}
        public Tipo tipo() {return tipo;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    private static abstract class DProc extends Dec{
        public DProc() {
            super();
        }
        public abstract void procesa(Procesamiento p);
    }
    public static class DProcConPars extends DProc{
        private StringLocalizado id;
        private Pars pars;
        private Inst bloque;
        public DProcConPars(StringLocalizado id, Pars pars, Inst bloque){
            super();
            this.id = id;
            this.pars = pars;
            this.bloque = bloque;
        }
        public StringLocalizado id() {return id;}
        public Pars pars() {return pars;}
        public Inst bloque(){return bloque;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
    }
    public static class DProcSinPars extends DProc{
        private StringLocalizado id;
        private Inst bloque;
        public DProcSinPars(StringLocalizado id, Inst bloque){
            super();
            this.id = id;
            this.bloque = bloque;
        }
        public StringLocalizado id() {return id;}
        public Inst bloque(){return bloque;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
    }

    public static abstract class Pars {
        public Pars() {
        }
        public abstract void procesa(Procesamiento p);
    }
    public static class ParsComp extends Pars{
        private Pars pars;
        private Par par;
        public ParsComp(Pars pars, Par par) {
            super();
            this.par = par;
            this.pars= pars;
        }
        public Pars pars(){return pars;}
        public Par par(){return par;}
        public void procesa(Procesamiento p){
            p.procesa(this); 
        }
    }
    public static class ParsSimp extends Pars{
        private Par par;
        public ParsSimp(Par par) {
            super();
            this.par = par;
        }
        public Par par(){return par;}
        public void procesa(Procesamiento p){
            p.procesa(this); 
        }
    }

    public static abstract class Par {
        public Par() {
        }
        public abstract void procesa(Procesamiento p);
    }
    public static class ParRef extends Par{
        private StringLocalizado id;
        private Tipo tipo;
        public ParRef(Tipo tipo, StringLocalizado id) {
            super();
            this.id = id;
            this.tipo= tipo;
        }
        public StringLocalizado id(){return id;}
        public Tipo tipo(){return tipo;}
        public void procesa(Procesamiento p){
            p.procesa(this); 
        }
    }
    public static class ParSinRef extends Par{
        private StringLocalizado id;
        private Tipo tipo;
        public ParSinRef(Tipo tipo, StringLocalizado id) {
            super();
            this.id = id;
            this.tipo= tipo;
        }
        public StringLocalizado id(){return id;}
        public Tipo tipo(){return tipo;}
        public void procesa(Procesamiento p){
            p.procesa(this); 
        }
    }

    public static class LDecSimp extends Decs {
       private Dec dec; 
       public LDecSimp(Dec dec) {
          super();
          this.dec = dec;
       }   
       public Dec dec() {return dec;}
       public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class LDecComp extends Decs {
        private Dec dec;
        private Decs decs;
        public LDecComp(Decs decs, Dec dec) {
            super();
            this.dec = dec;
            this.decs = decs;
        }
        public Dec dec() {return dec;}
        public Decs decs() {return decs;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }     
    }

    public static abstract class Prog  {
       public Prog() {
       }   
       public abstract void procesa(Procesamiento p); 
    }
    public static class ProgSinDecs extends Prog {
        private Insts insts;
        public ProgSinDecs(Insts insts) {
            super();
            this.insts = insts;
        }   
        public Insts insts() {return insts;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }     
    }
    public static class ProgConDecs extends Prog {
        private Insts insts;
        private Decs decs;
        public ProgConDecs(Decs decs, Insts insts) {
            super();
            this.insts = insts;
            this.decs = decs;
        }   
        public Insts insts() {return insts;}
        public Decs decs() {return decs;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }     
    }

    // Constructoras    
    public Prog progConDecs(Decs decs, Insts insts) {return new ProgConDecs(decs, insts);}
    public Prog progSinDecs(Insts insts) {return new ProgSinDecs(insts);}
    public Decs decSimp(Dec dec) {return new LDecSimp(dec);}
    public Decs decComp(Decs decs, Dec dec) {return new LDecComp(decs, dec);}
    public Dec dVar(Tipo tipo, StringLocalizado id) {return new DVar(tipo,id);}
    public Dec dTipo(Tipo tipo, StringLocalizado id) {return new DTipo(tipo,id);}
    public Dec dProcConPars(StringLocalizado id, Pars pars, Inst bloque) {return new DProcConPars(id, pars, bloque);}
    public Dec dProcSinPars(StringLocalizado id, Inst bloque) {return new DProcSinPars(id, bloque);}
    public Pars parsComp(Pars pars, Par par) {return new ParsComp(pars, par);}
    public Pars parsSimp(Par par) {return new ParsSimp(par);}
    public Par parRef(Tipo tipo, StringLocalizado id) {return new ParRef(tipo,id);}
    public Par parSinRef(Tipo tipo, StringLocalizado id) {return new ParSinRef(tipo,id);}
    public Tipo intint(StringLocalizado str) {return new INT(str);}
    public Tipo real(StringLocalizado str) {return new REAL(str);}
    public Tipo boolbool(StringLocalizado str) {return new BOOL(str);}
    public Tipo stringstring(StringLocalizado str) {return new STRING(str);}
    public Tipo idenTipo(StringLocalizado str) {return new IdenTipo(str);}
    public Tipo array(StringLocalizado num, Tipo tipo) {return new ARRAY(num, tipo);}
    public Tipo registro(Campos campos) {return new REGISTRO(campos);}
    public Campos camposComp(Campos campos, Campo campo) {return new CamposComp(campos, campo);}
    public Campos camposSimp(Campo campo) {return new CamposSimp(campo);}
    public Campo campo(Tipo tipo, StringLocalizado id) {return new Campo(tipo, id);}
    public Tipo pointer(Tipo tipo) {return new POINTER(tipo);}
    public Insts instsComp(Insts insts, Inst inst) {return new InstsComp(insts, inst);}
    public Insts instsSimp(Inst inst) {return new InstsSimp(inst);}
    public Inst iAsig(Exp exp0, Exp exp1) {return new IAsig(exp0, exp1);}
    public Inst iIfThen1(Exp exp, Insts insts) {return new IIfThen1(exp, insts);}
    public Inst iIfThen0(Exp exp) {return new IIfThen0(exp);}
    public Inst iIfThenElse11(Exp exp, Insts insts0, Insts insts1) {return new IIfThenElse11(exp, insts0, insts1);}
    public Inst iIfThenElse10(Exp exp, Insts insts) {return new IIfThenElse10(exp, insts);}
    public Inst iIfThenElse01(Exp exp, Insts insts) {return new IIfThenElse01(exp, insts);}
    public Inst iIfThenElse00(Exp exp) {return new IIfThenElse00(exp);}
    public Inst iWhile1(Exp exp, Insts insts) {return new IWhile1(exp, insts);}
    public Inst iWhile0(Exp exp) {return new IWhile0(exp);}
    public Inst iRead(Exp exp) {return new IRead(exp);}
    public Inst iWrite(Exp exp) {return new IWrite(exp);}
    public Inst iNew(Exp exp) {return new INew(exp);}
    public Inst iDelete(Exp exp) {return new IDelete(exp);}
    public Inst iNl(){ return new INl();}
    public Inst iCall1(StringLocalizado id, Exps exps) {return new ICall1(id, exps);}
    public Inst iCall0(StringLocalizado id) {return new ICall0(id);}
    public Inst bloque1(Prog prog) {return new Bloque1(prog);}
    public Inst bloque0() {return new Bloque0();}
    public Exps exps1(Exps exps, Exp exp) {return new Exps1(exps, exp);}
    public Exps exps0(Exp exp) {return new Exps0(exp);}

    public Exp suma(Exp arg0, Exp arg1) {return new Suma(arg0,arg1);}
    public Exp resta(Exp arg0, Exp arg1) {return new Resta(arg0,arg1);}
    public Exp and(Exp arg0, Exp arg1) {return new And(arg0,arg1);}
    public Exp or(Exp arg0, Exp arg1) {return new Or(arg0,arg1);}
    public Exp lt(Exp arg0, Exp arg1) {return new LT(arg0,arg1);}
    public Exp gt(Exp arg0, Exp arg1) {return new GT(arg0,arg1);}
    public Exp ge(Exp arg0, Exp arg1) {return new GE(arg0,arg1);}
    public Exp le(Exp arg0, Exp arg1) {return new LE(arg0,arg1);}
    public Exp ne(Exp arg0, Exp arg1) {return new NE(arg0,arg1);}
    public Exp eq(Exp arg0, Exp arg1) {return new EQ(arg0,arg1);}
    public Exp cmp(String op, Exp arg0, Exp arg1){
        switch(op){
            case "<": return lt(arg0, arg1);
            case ">": return gt(arg0, arg1);
            case "<=": return le(arg0, arg1);
            case ">=": return ge(arg0, arg1);
            case "!=": return ne(arg0, arg1);
            case "==": return eq(arg0, arg1);
            default: throw new UnsupportedOperationException("cmp "+op);
        }
    }
    public Exp mul(Exp arg0, Exp arg1) {return new Mul(arg0,arg1);}
    public Exp div(Exp arg0, Exp arg1) {return new Div(arg0,arg1);}
    public Exp mod(Exp arg0, Exp arg1) {return new Mod(arg0,arg1);}
    public Exp op3na(String op, Exp arg0, Exp arg1){
        switch(op){
            case "*": return mul(arg0, arg1);
            case "/": return div(arg0, arg1);
            case "%": return mod(arg0, arg1);
            default: throw new UnsupportedOperationException("cmp "+op);
        }
    }
    public Exp not(Exp arg) {return new Not(arg);}
    public Exp neg(Exp arg) {return new Neg(arg);}
    public Exp index(Exp arg0, Exp arg1) {return new Index(arg0, arg1);}
    public Exp ptr(Exp arg0, StringLocalizado arg1) {return new Ptr(arg0, arg1);}
    public Exp atr(Exp arg0, StringLocalizado arg1) {return new Atr(arg0, arg1);}
    public Exp indir(Exp arg) {return new Indir(arg);}
    public Exp parentesis(Exp arg) {return new Parentesis(arg);}
    public Exp ent(StringLocalizado num) {return new Ent(num);}
    public Exp idenExp(StringLocalizado id) {return new IdenExp(id);}
    public Exp lreal(StringLocalizado num) {return new Lreal(num);}
    public Exp truetrue(StringLocalizado id) {return new True(id);}
    public Exp falsefalse(StringLocalizado id) {return new False(id);}
    public Exp cadena(StringLocalizado id) {return new Cadena(id);}
    public Exp nullnull(StringLocalizado id) {return new Null(id);}

    public StringLocalizado str(String s, int fila, int col) {
        return new StringLocalizado(s,fila,col);
    }
}
