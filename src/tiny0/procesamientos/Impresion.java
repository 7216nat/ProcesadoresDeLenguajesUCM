package tiny0.procesamientos;

import tiny0.asint.ProcesamientoPorDefecto;
import tiny0.asint.TinyASint.*;

public class Impresion extends ProcesamientoPorDefecto {
   public Impresion() {
	   
   }
   
   public void procesa(Prog_sin_decs prog) {
       prog.asigs().procesa(this);
       System.out.println();
   }    
   
   public void procesa(Prog_sin_asigs prog) {
       prog.asigs().procesa(this);
       System.out.println();
   } 
   
   public void procesa(Prog_entero prog) {
       prog.decs().procesa(this);
       System.out.println("\n&&");
       prog.asigs().procesa(this);     
   }    
   public void procesa(Decs_muchas decs) {
       decs.decs().procesa(this);
       System.out.println(";");
       decs.dec().procesa(this);
   }
   public void procesa(Decs_una decs) {
       decs.dec().procesa(this);
   }
   
   public void procesa(CInt dec) {
	   System.out.print("int " + dec.id());
   }
   
   public void procesa(CBool dec) {
	   System.out.print("bool " + dec.id());
   }

   public void procesa(CReal dec) {
	   System.out.print("real " + dec.id());
   }
   
   public void procesa(Asigs_muchas asigs) {
	   asigs.asigs().procesa(this);
       System.out.println(";");
       asigs.asig().procesa(this);
   }
   
   public void procesa(Asigs_una asigs) {
	   asigs.asig().procesa(this);
   }
      
   public void procesa(Asig asig) {
	   System.out.print(asig.id() + " = ");
	   asig.exp().procesa(this);
   }
    
   public void procesa(Suma exp) {
      imprime_arg(exp.arg0(),0); 
      System.out.print(" + ");
      imprime_arg(exp.arg1(),1);       
   }
   public void procesa(Resta exp) {
      imprime_arg(exp.arg0(),0); 
      System.out.print(" - ");
      imprime_arg(exp.arg1(),1);       
   }
   public void procesa(Mul exp) {
      imprime_arg(exp.arg0(),1); 
      System.out.print(" * ");
      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(Div exp) {
      imprime_arg(exp.arg0(),1); 
      System.out.print(" / ");
      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(Gt exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" > ");
	      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(Lt exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" < ");
	      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(Geq exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" >= ");
	      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(Leq exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" <= ");
	      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(Eq exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" == ");
	      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(Neq exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" != ");
	      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(And exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" and ");
	      imprime_arg(exp.arg1(),2);       
   }
   public void procesa(Or exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" or ");
	      imprime_arg(exp.arg1(),2);       
   }
   private void imprime_arg(Exp arg, int p) {
       if (arg.prioridad() < p) {
           System.out.print("(");
           arg.procesa(this);
           System.out.print(")");
       }
       else {
           arg.procesa(this);
       }
   }
   public void procesa(Id exp) {
       System.out.print(exp.id());
   }
   
   public void procesa(Entero exp) {
       System.out.print(exp.num());
   }
   
   public void procesa(Real exp) {
       System.out.print(exp.num());
   }
   
   public void procesa(CTrue exp) {
       System.out.print(exp.num());
   }
   
   public void procesa(CFalse exp) {
       System.out.print(exp.num());
   }
   
}   

            