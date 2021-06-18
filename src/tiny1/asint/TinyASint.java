package tiny1.asint;

import java.util.Map;

public class TinyASint {
    
	public static abstract class ASTNode {
		int tam;
		public int tam() {return tam;}
		public void setTam(int tam) {this.tam = tam;}
	}
	
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

    public enum Type{
        INT, BOOL, REAL, STRING, NULL, ERROR, OK, ARRAY, RECORD, POINTER
    }
    public enum DecType{
        VAR, TYPE, PROC 
    }
    public static abstract class Exp extends ASTNode{
        private Tipo tipo;
        public Exp() {}
        public boolean esDesignador(){
            return false;
        }
        public Dec vinculo(){
            throw new UnsupportedOperationException("Vinculo unsupported.");
        }
        public void setVinculo(Dec dec){
            throw new UnsupportedOperationException("Set vinculo unsupported.");
        }  
        public void setTipo(Tipo tipo){this.tipo = tipo;}
        public Tipo getTipo(){return tipo;}
        public Type getType(){return tipo.type();}
        public abstract int prioridad();
        public abstract void procesa(Procesamiento procesamiento);
    }
    
    public static abstract class ExpBin extends Exp {
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

    public static abstract class ExpUni extends Exp {
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
        @Override
        public boolean esDesignador(){
            return true;
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
        @Override
        public boolean esDesignador(){
            return true;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
    public static class Ptr extends ExpAcc {
        public Ptr(Exp arg0, StringLocalizado arg1) {
            super(arg0,arg1);
        }
        @Override
        public boolean esDesignador(){
            return true;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }

    public static class Indir extends ExpUni {
        public Indir(Exp arg) {
            super(arg);
        }
        @Override
        public boolean esDesignador(){
            return true;
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
        private Dec vinculo;
        public IdenExp(StringLocalizado id) {
            super();
            this.id = id;
        }
        @Override
        public boolean esDesignador(){
            return true;
        }
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
        @Override
        public Dec vinculo(){
            return vinculo;
        }
        @Override
        public void setVinculo(Dec dec){
            this.vinculo = dec;
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
        public True() {
            super();
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    public static class False extends Exp {
        public False() {
            super();
        }
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
        public Null() {
            super();
        }
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
    public static class NoExps extends Exps{
        public NoExps(){
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
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
    	private int etqi;
    	private int etqs;
        public Insts(){
        }
        public abstract void procesa(Procesamiento p);
        public int etqi() {return etqi;}
        public int etqs() {return etqs;}
        public void setEtqi(int etqi) {this.etqi = etqi;}
        public void setEtqs(int etqs) {this.etqs = etqs;}
    }
    public static class NoInsts extends Insts{
        public NoInsts(){
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
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


    public static abstract class Inst extends ASTNode {
    	private int etqi;
    	private int etqs;
    	
        public Inst(){
        }
        public abstract void procesa(Procesamiento p);
        public int etqi() {return etqi;}
        public int etqs() {return etqs;}
        public void setEtqi(int etqi) {this.etqi = etqi;}
        public void setEtqs(int etqs) {this.etqs = etqs;}
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
    public static class IIfThen extends Inst {
        private Exp exp;
        private Insts insts;
        public IIfThen(Exp exp, Insts insts) {
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
    public static class IIfThenElse extends Inst {
        private Exp exp;
        private Insts insts0;
        private Insts insts1;
        public IIfThenElse(Exp exp, Insts insts0, Insts insts1) {
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
    public static class IWhile extends Inst {
        private Exp exp;
        private Insts insts;
        public IWhile(Exp exp, Insts insts) {
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
    public static class ICall extends Inst {
        private StringLocalizado id;
        private Exps exps;
        private Dec vinculo;
        public ICall(StringLocalizado id, Exps exps) {
            super();
            this.id = id;
            this.exps = exps;
        }
        public StringLocalizado id() {return id;}
        public Exps exps() { return exps;}
        public void procesa(Procesamiento p) { p.procesa(this); }
        public Dec vinculo(){return this.vinculo;}
        public void setVinculo(Dec dec){this.vinculo = dec;}      
    }
    public static class Bloque extends Inst {
        private Prog prog;
        public Bloque(Prog prog) {
            super();
            this.prog = prog;
        }
        public Prog prog() {return prog;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }      
    }
    

    public static abstract class Tipo extends ASTNode{
        public Tipo(){}
        public abstract Type type();
        public Tipo tipoSimpl(){return this;}
        public abstract void procesa(Procesamiento p);
        public abstract String toString();
    }
    public static class INT extends Tipo {
        public INT(){
            super();
        }
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        public Type type() {
            return Type.INT;
        }
        @Override
        public String toString(){return "INT";}
    }
    public static class REAL extends Tipo {
        public REAL(){
            super();
        }
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        public Type type() {
            return Type.REAL;
        }
        @Override
        public String toString(){return "REAL";}
    }
    public static class BOOL extends Tipo {
        public BOOL(){
            super();
        }
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        public Type type() {
            return Type.BOOL;
        }
        @Override
        public String toString(){return "BOOL";}
    }
    public static class STRING extends Tipo {
        public STRING(){
            super();
        }
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        public Type type() {
            return Type.STRING;
        }
        @Override
        public String toString(){return "STRING";}
    }
    public static class IdenTipo extends Tipo {
        private StringLocalizado str;
        private Dec vinculo = null;

        public IdenTipo(StringLocalizado str){
            super();
            this.str = str;
        }
        public Dec vinculo(){return this.vinculo;}
        public void setVinculo(Dec dec){this.vinculo = dec;}
        @Override
        public Tipo tipoSimpl(){return vinculo.tipo();}
        public Type type(){
            return vinculo.tipo().type();
        }
        public StringLocalizado str(){return str;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        @Override
        public String toString(){return str.toString();}
    }
    public static class ARRAY extends Tipo {
        private StringLocalizado num;
        private Tipo tipo;
        private int dim;
        public ARRAY(StringLocalizado num, Tipo tipo){
            super();
            this.tipo = tipo;
            this.num = num;
        }
        public StringLocalizado num(){return num;}
        public Tipo tipo(){return tipo;}
        public void procesa(Procesamiento p){p.procesa(this);}
        @Override
        public Type type() {return Type.ARRAY;}
        public void setDim(int dim) {this.dim = dim;}
        public int getDim() {return dim;}
        @Override
        public String toString(){return "ARRAY";}
    }
    public static class REGISTRO extends Tipo {
        private Campos campos;
        private Map<String, Campo> listCampos;
        public REGISTRO(Campos campos){
            super();
            this.campos = campos;
        }
        public void setList(Map<String, Campo> listCampos){this.listCampos = listCampos;}
        public Map<String, Campo> getList(){return this.listCampos;}
        public Campos campos(){return campos;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        @Override
        public Type type() {
            return Type.RECORD;
        }
        @Override
        public String toString(){return "REGISTRO";}
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
        @Override
        public Type type() {
            return Type.POINTER;
        }
        @Override
        public String toString(){return "POINTER";}
    }
    public static class ERROR extends Tipo {
        public ERROR(){
            super();
        }
        @Override
        public Type type() {return Type.ERROR;}

        @Override
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
        public String toString(){return "ERROR";}
    }
    public static class OK extends Tipo {
        public OK(){
            super();
        }
        @Override
        public Type type() {return Type.OK;}

        @Override
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
        public String toString(){return "OK";}
    }

    public static abstract class Campos{
        public Campos(){
        }
        public Campos campos(){return null;}
        public Campo campo(){return null;}
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
        @Override
        public Campos campos(){return campos;}
        @Override
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
        @Override
        public Campo campo(){return campo;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
    }
    public static class Campo{
        int dir;
        private StringLocalizado id;
        private Tipo tipo;
        private Type type;
        public Campo(Tipo tipo, StringLocalizado id){
            this.tipo = tipo;
            this.id = id;
        }
        public Tipo tipo() {return tipo;}
        public void setTipo(Tipo tipo){this.tipo = tipo;}
        public Type type(){return type;}
        public void setType(Type type){this.type = type;}
        public StringLocalizado id() {return id;}
        public void setDir(int dir){
            this.dir = dir;
        }
        public int getDir(){ return dir;}
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        @Override
        public String toString(){
            return "Campo " + this.id().toString() + "; Dir: " + this.dir;
        } 
    }


    public static abstract class Decs extends ASTNode{
        public Decs() {
        }
        public abstract void procesa(Procesamiento p);
    }
    public static class NoDecs extends Decs{
        public NoDecs(){
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
    }
    public static class AuxDecs extends Decs{
        private Decs decs;
         public AuxDecs(Decs decs) {
             super();
             this.decs = decs;
         }
         public Decs decs() {return decs;}
         public void procesa(Procesamiento p) {
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
 
    
    public static abstract class Dec extends ASTNode{
        int ambito;
        int dir;
        private StringLocalizado id;
        private Tipo tipo;
        private Type type;
        public Dec(Tipo tipo, StringLocalizado id) {
            this.tipo = tipo;
            this.id = id;
        }
        public Tipo tipo() {return tipo;}
        public void setTipo(Tipo tipo){this.tipo = tipo;}
        public Type type(){return type;}
        public void setType(Type type){this.type = type;}
        public StringLocalizado id() {return id;}
        public void setAmbito(int ambito){
            this.ambito = ambito;
        }
        public void setDir(int dir){
            this.dir = dir;
        }
        public int getAmbito(){ return ambito;}
        public int getDir(){ return dir;}
        public String toString(){
            return "; Ambito: " + this.ambito;
        }
        public abstract DecType decType();
        public abstract void procesa(Procesamiento p);
    }
    public static class DVar extends Dec  {
        public DVar(Tipo tipo, StringLocalizado id) {
            super(tipo, id);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }
        @Override
        public String toString(){
            return "Var " + this.id().toString() + super.toString();
        }
        @Override
        public DecType decType() {
            return DecType.VAR;
        }     
    }
    public static class DTipo extends Dec  {
        public DTipo(Tipo tipo, StringLocalizado id) {
            super(tipo, id);
        }
        @Override
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }
        @Override
        public String toString(){
            return "Tipo " + this.id().toString() + super.toString();
        }
        @Override
        public DecType decType() {
            return DecType.TYPE;
        }       
    }
    public static class DProc extends Dec{
        private Pars pars;
        private Inst bloque;
        public DProc(StringLocalizado id, Pars pars, Inst bloque) {
            super(TypeOk, id);
            setType(Type.OK);
            this.pars = pars;
            this.bloque = bloque;
        }
        public Pars pars() {return pars;}
        public Inst bloque(){return bloque;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
        @Override
        public String toString(){
            return "Proc " + this.id().toString() + super.toString();
        }
        @Override
        public DecType decType() {
            return DecType.PROC;
        }   
    }

    public static abstract class Pars {
        public Pars() {
        }
        public abstract void procesa(Procesamiento p);
    }
    public static class NoPars extends Pars{
        public NoPars(){
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
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


    public static abstract class Par extends Dec{
        public Par(Tipo tipo, StringLocalizado id) {
            super(tipo, id);
        }
        public abstract void procesa(Procesamiento p);
    }
    public static class ParRef extends Par{      
        public ParRef(Tipo tipo, StringLocalizado id) {
            super(tipo, id);  
        }
        public void procesa(Procesamiento p){
            p.procesa(this); 
        }
        @Override
        public String toString(){
            return "ParRef " + this.id().toString() + super.toString();
        }
        @Override
        public DecType decType() {
            return DecType.VAR;
        }  
    }
    public static class ParSinRef extends Par{
        public ParSinRef(Tipo tipo, StringLocalizado id) {
            super(tipo, id);  
        }
        public void procesa(Procesamiento p){
            p.procesa(this); 
        }
        @Override
        public String toString(){
            return "ParSinRef " + this.id().toString() + super.toString();
        }
        @Override
        public DecType decType() {
            return DecType.VAR;
        }    
    }

    public static class Prog  {
        private Insts insts;
        private Decs decs;
        public Prog(Decs decs, Insts insts) {
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
    public Prog prog(Decs decs, Insts insts) {return new Prog(decs, insts);}
    public static final Decs noDecs = new NoDecs();
    public Decs auxDecs(Decs decs) {return new AuxDecs(decs);}
    public Decs decSimp(Dec dec) {return new LDecSimp(dec);}
    public Decs decComp(Decs decs, Dec dec) {return new LDecComp(decs, dec);}
    public Dec dVar(Tipo tipo, StringLocalizado id) {return new DVar(tipo,id);}
    public Dec dTipo(Tipo tipo, StringLocalizado id) {return new DTipo(tipo,id);}
    public Dec dProc(StringLocalizado id, Pars pars, Inst bloque) {return new DProc(id, pars, bloque);}
    public static final Pars noPars = new NoPars();
    public Pars parsComp(Pars pars, Par par) {return new ParsComp(pars, par);}
    public Pars parsSimp(Par par) {return new ParsSimp(par);}
    public Par parRef(Tipo tipo, StringLocalizado id) {return new ParRef(tipo,id);}
    public Par parSinRef(Tipo tipo, StringLocalizado id) {return new ParSinRef(tipo,id);}
    public static final Tipo TypeInt = new INT();
    public static final Tipo TypeReal = new REAL();
    public static final Tipo TypeBool = new BOOL();
    public static final Tipo TypeString = new STRING();
    public Tipo idenTipo(StringLocalizado str) {return new IdenTipo(str);}
    public Tipo array(StringLocalizado num, Tipo tipo) {return new ARRAY(num, tipo);}
    public Tipo registro(Campos campos) {return new REGISTRO(campos);}
    public static final Tipo TypeError = new ERROR();
    public static final Tipo TypeOk = new OK();
    public Campos camposComp(Campos campos, Campo campo) {return new CamposComp(campos, campo);}
    public Campos camposSimp(Campo campo) {return new CamposSimp(campo);}
    public Campo campo(Tipo tipo, StringLocalizado id) {return new Campo(tipo, id);}
    public Tipo pointer(Tipo tipo) {return new POINTER(tipo);}
    public static final Insts noInsts = new NoInsts();
    public Insts instsComp(Insts insts, Inst inst) {return new InstsComp(insts, inst);}
    public Insts instsSimp(Inst inst) {return new InstsSimp(inst);}
    public Inst iAsig(Exp exp0, Exp exp1) {return new IAsig(exp0, exp1);}
    public Inst iIfThen(Exp exp, Insts insts) {return new IIfThen(exp, insts);}
    public Inst iIfThenElse(Exp exp, Insts insts0, Insts insts1) {return new IIfThenElse(exp, insts0, insts1);}
    public Inst iWhile(Exp exp, Insts insts) {return new IWhile(exp, insts);}
    public Inst iRead(Exp exp) {return new IRead(exp);}
    public Inst iWrite(Exp exp) {return new IWrite(exp);}
    public Inst iNew(Exp exp) {return new INew(exp);}
    public Inst iDelete(Exp exp) {return new IDelete(exp);}
    public Inst iNl(){ return new INl();}
    public Inst iCall(StringLocalizado id, Exps exps) {return new ICall(id, exps);}
    public Inst bloque(Prog prog) {return new Bloque(prog);}
    public static final Exps noExps = new NoExps();
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
    public static final Exp TRUE = new True();
    public static final Exp FALSE = new False();
    public Exp cadena(StringLocalizado id) {return new Cadena(id);}
    public static final Exp NULL = new Null();

    public StringLocalizado str(String s, int fila, int col) {
        return new StringLocalizado(s,fila,col);
    }
}
