package tiny0.semops;

import tiny0.asint.TinyASint;

public class SemOps extends TinyASint{

	public Exp exp(String op, Exp arg0, Exp arg1) {
		
		switch(op) {
			case "+": return suma(arg0, arg1);
			case "*": return mul(arg0, arg1);
			case "/": return div(arg0, arg1);
			case "-": return resta(arg0, arg1);
			case ">": return gt(arg0, arg1);
			case "<": return lt(arg0, arg1);
			case "<=": return leq(arg0, arg1);
			case ">=": return geq(arg0, arg1);
			case "==": return eq(arg0, arg1);
			case "!=": return neq(arg0, arg1);
			case "or": return or(arg0, arg1);
			case "and": return and(arg0, arg1);
		}
		throw new UnsupportedOperationException("exp" + op);
	}
	
	public Prog prog(Decs d, Asigs a) {
		if(d == null) return prog_sin_decs(a);
		if(a == null) return prog_sin_asigs(d);
		return prog_entero(d, a);
		
	}


}
