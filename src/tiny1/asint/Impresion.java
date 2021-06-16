package tiny1.asint;
import tiny1.asint.TinyASint.*;
public class Impresion implements Procesamiento {
    private String tabs;
    public Impresion() {
        tabs = "";
    }
    public void procesa(Prog prog) {
        prog.decs().procesa(this);
        prog.insts().procesa(this);
        System.out.println();
    }
    public void procesa(NoDecs decs){
        //naa
    }
    public void procesa(AuxDecs decs){
        decs.decs().procesa(this);
        System.out.print("\n"+ tabs + "&&\n");
    }
    public void procesa(LDecSimp decs) {
        decs.dec().procesa(this);
    }
    public void procesa(LDecComp decs) {
        decs.decs().procesa(this);
        System.out.println(";");
        decs.dec().procesa(this);
    }
    public void procesa(DVar dec) { 
        System.out.print(tabs + "var "); 
        dec.tipo().procesa(this);
        System.out.print(" " + dec.id());
    }
    public void procesa(DTipo dec) {
        System.out.print(tabs + "type ");
        dec.tipo().procesa(this);
        System.out.print(dec.id());
    }
    public void procesa(DProc dec) {
        System.out.print(tabs + "proc " + dec.id()+ " (" );
        dec.pars().procesa(this);
        System.out.println(")");
        dec.bloque().procesa(this);
    }
    public void procesa(NoPars decs){
        // naa
    }
    public void procesa(ParsComp pars) {
        pars.pars().procesa(this);
        System.out.print(", ");
        pars.par().procesa(this);
    }
    public void procesa(ParsSimp pars) {
        pars.par().procesa(this);
    }
    public void procesa(ParRef par) {
        par.tipo().procesa(this);
        System.out.print(" & " + par.id());
    }
    public void procesa(ParSinRef par) {
        par.tipo().procesa(this);
        System.out.print( " " + par.id());
    }
    public void procesa(INT t) {
        System.out.print("int");
    }
    public void procesa(REAL t) {
        System.out.print("real");
    }
    public void procesa(BOOL t) {
        System.out.print("bool");
    }
    public void procesa(STRING t) {
        System.out.print("string");
    }
    public void procesa(IdenTipo t) {
        System.out.print(t.str());
    }
    public void procesa(ARRAY t) {
        System.out.print("array [" + t.num() + "] of ");
        t.tipo().procesa(this);
        
    }
    public void procesa(OK exp){
        System.out.print("ok");
    }
    public void procesa(ERROR exp){
        System.out.print("error");
    }
    public void procesa(REGISTRO t) {
        System.out.println("record {");
        t.campos().procesa(this);
        System.out.print("\n}");
    }
    public void procesa(CamposSimp cs) {
        cs.campo().procesa(this);
    }
    public void procesa(CamposComp cs) {
        cs.campos().procesa(this);
        System.out.println(";");
        cs.campo().procesa(this);
    }
    public void procesa(Campo c) {
        System.out.print("\t");
        c.tipo().procesa(this);
        System.out.print(" " + c.id());
    }
    public void procesa(POINTER t) {
        System.out.print("pointer ");
        t.tipo().procesa(this);
    }
    public void procesa(NoInsts decs){
        // naa
    }
    public void procesa(InstsComp is) {
        is.insts().procesa(this);
        System.out.println(";");
        is.inst().procesa(this);
    }
    public void procesa(InstsSimp is) {
        is.inst().procesa(this);
    }
    public void procesa(IAsig i) {
        System.out.print(tabs);
        i.exp0().procesa(this);
        System.out.print(" = ");
        i.exp1().procesa(this);
    }
    public void procesa(IIfThen i) {
        System.out.print(tabs + "if ");
        String tmp = tabs;
        tabs += "\t";
        i.exp().procesa(this);
        System.out.println(" then");
        i.insts().procesa(this);
        tabs = tmp;
        System.out.print("\n" + tabs + "endif");
    }
    public void procesa(IIfThenElse i) {
        System.out.print(tabs + "if ");
        String tmp = tabs;
        tabs += "\t";
        i.exp().procesa(this);
        System.out.println(" then");
        i.insts0().procesa(this);
        tabs = tmp;
        System.out.print("\n" + tabs + "else\n");
        tabs += "\t";
        i.insts1().procesa(this);
        tabs = tmp;
        System.out.print("\n" + tabs + "endif");
    }
    public void procesa(IWhile i) {
        System.out.print(tabs + "while ");
        i.exp().procesa(this);
        System.out.println(" do");
        String tmp = tabs;
        tabs += "\t";
        i.insts().procesa(this);
        tabs = tmp;
        System.out.print("\n" + tabs +"endwhile");
    }
    public void procesa(IRead i) {
        System.out.print(tabs + "read ");
        i.exp().procesa(this);
    }
    public void procesa(IWrite i) {
        System.out.print(tabs+ "write ");
        i.exp().procesa(this);
    }
    public void procesa(INew i) {
        System.out.print(tabs+"new ");
        i.exp().procesa(this);
    }
    public void procesa(IDelete i) {
        System.out.print(tabs + "delete ");
        i.exp().procesa(this);
    }
    public void procesa(INl i) {
        System.out.print(tabs + "nl");
    }
    public void procesa(ICall i) {
        System.out.print(tabs + "call "+ i.id() + "(");
        i.exps().procesa(this);
        System.out.print(")");
    }
    public void procesa(Bloque i) {
        System.out.println(tabs + "{");
        String tmp = tabs;
        tabs += "\t";
        i.prog().procesa(this);
        tabs = tmp;
        System.out.print(tabs + "}");
    }
    public void procesa(NoExps decs){
        // naa
    }
    public void procesa(Exps1 exp) {
        exp.exps().procesa(this);
        System.out.println(";");
        exp.exp().procesa(this);
    }
    public void procesa(Exps0 exp) {
        exp.exp().procesa(this);
    }
    public void procesa(And exp) {
        exp.arg0().procesa(this);
        System.out.print(" and ");
        exp.arg1().procesa(this);
    }
    public void procesa(Or exp) {
        exp.arg0().procesa(this);
        System.out.print(" or ");
        exp.arg1().procesa(this);
    }
    public void procesa(LT exp) {
        exp.arg0().procesa(this);
        System.out.print(" < ");
        exp.arg1().procesa(this);
    }
    public void procesa(GT exp) {
        exp.arg0().procesa(this);
        System.out.print(" > ");
        exp.arg1().procesa(this);
    }
    public void procesa(LE exp) {
        exp.arg0().procesa(this);
        System.out.print(" <= ");
        exp.arg1().procesa(this);
    }
    public void procesa(GE exp) {
        exp.arg0().procesa(this);
        System.out.print(" >= ");
        exp.arg1().procesa(this);
    }
    public void procesa(NE exp) {
        exp.arg0().procesa(this);
        System.out.print(" != ");
        exp.arg1().procesa(this);
    }
    public void procesa(EQ exp) {
        exp.arg0().procesa(this);
        System.out.print(" == ");
        exp.arg1().procesa(this);
    }
    public void procesa(Mod exp) {
        exp.arg0().procesa(this);
        System.out.print(" % ");
        exp.arg1().procesa(this);
    }
    public void procesa(Not exp) {
        System.out.print("not ");
        exp.arg().procesa(this);
    }
    public void procesa(Neg exp) {
        System.out.print("- ");
        exp.arg().procesa(this);
    }
    public void procesa(Index exp) {
        exp.arg0().procesa(this);
        System.out.print("[");
        exp.arg1().procesa(this);
        System.out.print("]");
    }
    public void procesa(Ptr exp) {
        exp.exp().procesa(this);
        System.out.print("->"+ exp.id());
    }
    public void procesa(Atr exp) {
        exp.exp().procesa(this);
        System.out.print("."+ exp.id());
    }
    public void procesa(Indir exp) {
        System.out.print("* ");
        exp.arg().procesa(this);
    }
    public void procesa(Parentesis exp) {
        System.out.print("(");
        exp.arg().procesa(this);
        System.out.print(")");
    }
    public void procesa(Ent exp) {
        System.out.print(exp.ent());
    }
    public void procesa(IdenExp exp) {
        System.out.print(exp.id());
    }
    public void procesa(Lreal exp) {
        System.out.print(exp.lreal());
    }
    public void procesa(True exp) {
        System.out.print("true");
    }
    public void procesa(False exp) {
        System.out.print("false");
    }
    public void procesa(Cadena exp) {
        System.out.print(exp.cadena());
    }
    public void procesa(Null exp) {
        System.out.print("null");
    }
    public void procesa(Suma exp) {
        exp.arg0().procesa(this);
        System.out.print(" + ");
        exp.arg1().procesa(this);
    }
    public void procesa(Resta exp) {
        exp.arg0().procesa(this);
        System.out.print(" - ");
        exp.arg1().procesa(this);
    }
    public void procesa(Mul exp) {
        exp.arg0().procesa(this);
        System.out.print(" * ");
        exp.arg1().procesa(this);
    }
    public void procesa(Div exp) {
        exp.arg0().procesa(this);
        System.out.print(" / ");
        exp.arg1().procesa(this);
    }   
}   

            