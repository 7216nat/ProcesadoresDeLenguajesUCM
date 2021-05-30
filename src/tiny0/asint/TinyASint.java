package tiny0.asint;

import tiny0.procesamientos.Impresion;

public class TinyASint {

	public enum TNodo {
		ID, ENTERO, SUMA, DEC, ASIG, DECS_UNA, PROG_SIN_DECS, PROG_SIN_ASIGS, PROG_ENTERO, DECS_MUCHAS, ASIGS_UNA, ASIGS_MUCHAS, REAL,
		CINT, CREAL, CBOOL, CTRUE, CFALSE, MUL, DIV, RESTA, AND, OR, LT, LEQ, GT, EQ, NEQ, GEQ, NEG, NOT
	};

	public static abstract class Exp {
		public Exp() {
		};

		public abstract TNodo tipo();

		public Exp arg0() {
			throw new UnsupportedOperationException("arg0");
		}

		public Exp arg1() {
			throw new UnsupportedOperationException("arg1");
		}

		public StringLocalizado id() {
			throw new UnsupportedOperationException("id");
		}

		public StringLocalizado num() {
			throw new UnsupportedOperationException("num");
		}

		public abstract void procesa(Procesamiento procesamiento);
		public abstract int prioridad();
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

		public int fila() {
			return fila;
		}

		public int col() {
			return col;
		}

		public String toString() {
			return s;
		}

		public boolean equals(Object o) {
			return (o == this) || ((o instanceof StringLocalizado) && (((StringLocalizado) o).s.equals(s)));
		}

		public int hashCode() {
			return s.hashCode();
		}
	}

	public Exp suma(Exp arg0, Exp arg1) {return new Suma(arg0, arg1);}
	
	public Exp mul(Exp arg0, Exp arg1) { return new Mul(arg0, arg1); }
	public Exp div(Exp arg0, Exp arg1) { return new Div(arg0, arg1); }

	public Exp resta(Exp arg0, Exp arg1) { return new Resta(arg0, arg1); }
	public Exp neg(Exp arg0) { return new Neg(arg0); }

	public Exp and(Exp arg0, Exp arg1) { return new And(arg0, arg1); }
	public Exp or(Exp arg0, Exp arg1) { return new Or(arg0, arg1); }
	public Exp not(Exp arg0) { return new Not(arg0); }

	public Exp lt(Exp arg0, Exp arg1) { return new Lt(arg0, arg1); }
	public Exp gt(Exp arg0, Exp arg1) { return new Gt(arg0, arg1); }
	public Exp leq(Exp arg0, Exp arg1) { return new Leq(arg0, arg1); }
	public Exp geq(Exp arg0, Exp arg1) { return new Geq(arg0, arg1); }
	public Exp eq(Exp arg0, Exp arg1) { return new Eq(arg0, arg1); }
	public Exp neq(Exp arg0, Exp arg1) { return new Neq(arg0, arg1); }
	

	public static abstract class Prog  {
	       public Prog() {
	       }   
	       public abstract TNodo tipo();  
	       public Asigs asigs() {throw new UnsupportedOperationException("asigs");};
	       public Decs decs() {throw new UnsupportedOperationException("decs");}
	       public abstract void procesa(Procesamiento p);
	}
	

	public static class Prog_sin_decs extends Prog {
	  private Asigs asigs;
	   public Prog_sin_decs(Asigs asigs) {
	      super();
	      this.asigs = asigs;
	   }   
	   public TNodo tipo() {return TNodo.PROG_SIN_DECS;}; 
	   public Asigs asigs() {return asigs;}
	   public void procesa(Procesamiento p) {
           p.procesa(this); 
       } 
	}
	
	public static class Prog_sin_asigs extends Prog {
		  private Decs decs;
		   public Prog_sin_asigs(Decs decs) {
		      super();
		      this.decs = decs;
		   }   
		   public TNodo tipo() {return TNodo.PROG_SIN_ASIGS;}; 
		   public Decs decs() {return decs;}
		   public void procesa(Procesamiento p) {
	            p.procesa(this); 
	        } 
		}
	
	public static class Prog_entero extends Prog {
	  private Decs decs;
	  private Asigs asigs;
	   public Prog_entero(Decs decs, Asigs asigs) {
	      super();
	      this.asigs = asigs;
	      this.decs = decs;
	   }   
	   public TNodo tipo() {return TNodo.PROG_ENTERO;}; 
	   public Asigs asigs() {return asigs;}
	   public Decs decs() {return decs;}
	   public void procesa(Procesamiento p) {
           p.procesa(this); 
       } 
	}
	
	public Prog prog_sin_decs(Asigs asigs) {
		return new Prog_sin_decs(asigs);
	}
	public Prog prog_sin_asigs(Decs decs) {
		return new Prog_sin_asigs(decs);
	}
	public Prog prog_entero(Decs decs, Asigs asigs) {
		return new Prog_entero(decs,asigs);
	}
		
    public static abstract class Asigs {
        public Asigs() {
        	
        }   
        public abstract TNodo tipo(); 
        public Asigs asigs() {throw new UnsupportedOperationException("asigs");}
        public Asig asig() {throw new UnsupportedOperationException("asig");}
       
        public abstract void procesa(Procesamiento p);
    }
    
    public static class Asig{
        private StringLocalizado id;
        private Exp exp;
        public Asig(StringLocalizado id, Exp exp) {
            this.id = id;
            this.exp = exp;
        }
        public TNodo tipo() {return TNodo.ASIG;}
        public StringLocalizado id() {return id;}
        public Exp exp() {return exp;}
        
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
    }

	
    public static abstract class Decs {
        public Decs() {
        }   
        public abstract TNodo tipo(); 
        public Decs decs() {throw new UnsupportedOperationException("decs");}
        public Dec dec() {throw new UnsupportedOperationException("dec");}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
    }
	
    
    public static class Decs_una extends Decs {
        private Dec dec; 
        public Decs_una(Dec dec) {
           super();
           this.dec = dec;
        }   
        public TNodo tipo() {return TNodo.DECS_UNA;}; 
        public Dec dec() {
            return dec;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
    }
    
    public static class Decs_muchas extends Decs {
        private Dec dec;
        private Decs decs;
        public Decs_muchas(Decs decs, Dec dec) {
           super();
           this.dec = dec;
           this.decs = decs;
        }
        public TNodo tipo() {return TNodo.DECS_MUCHAS;}; 
        public Dec dec() {
            return dec;
        }
        public Decs decs() {
            return decs;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
    }
    
    public Decs decs_muchas(Decs decs, Dec dec) {
        return new Decs_muchas(decs,dec);
    }
    
    public abstract static class Dec  {
        private StringLocalizado id;
        public Dec(StringLocalizado id) {
            this.id = id;
        }
        public TNodo tipo() {return TNodo.DEC;}
        public StringLocalizado id() {return id;}
		public abstract void procesa(Procesamiento p);
      
    }

    
    public static class Asigs_una extends Asigs {
    	private Asig asig; 
          public Asigs_una(Asig asig) {
             super();
             this.asig = asig;
          }   
          public TNodo tipo() {return TNodo.ASIGS_UNA;}; 
          public Asig asig() {
              return asig;
          }
          public void procesa(Procesamiento p) {
              p.procesa(this); 
          } 
    }
    
    public static class Asigs_muchas extends Asigs {
    	private Asig asig;
    	private Asigs asigs;
          public Asigs_muchas(Asigs asigs, Asig asig) {
             super();
             this.asigs = asigs;
             this.asig = asig;
          }   
          public TNodo tipo() {return TNodo.ASIGS_MUCHAS;}; 
          public Asig asig() {
              return asig;
          }
          public Asigs asigs() {
              return asigs;
          }
          public void procesa(Procesamiento p) {
              p.procesa(this); 
          } 
    }
    
	public Asigs asigs_una(Asig asig) {
		return new Asigs_una(asig);
	}
	
	public Asigs asigs_muchas(Asigs asigs, Asig asig) {
		return new Asigs_muchas(asigs, asig);
	}
    
    public static class Id extends Exp {
		private StringLocalizado id;

		public Id(StringLocalizado id) {
			super();
			this.id = id;
		}

		public TNodo tipo() {
			return TNodo.ID;
		}

		public StringLocalizado id() {
			return id;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
		public final int prioridad() {
            return 6;
        }
	}

	public static class Entero extends Exp {
		private StringLocalizado num;

		public Entero(StringLocalizado num) {
			super();
			this.num = num;
		}

		public TNodo tipo() {
			return TNodo.ENTERO;
		}

		public StringLocalizado num() {
			return num;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
		public final int prioridad() {
            return 6;
        }
	}
	
	public static class Real extends Exp {
		private StringLocalizado num;

		public Real(StringLocalizado num) {
			super();
			this.num = num;
		}

		public TNodo tipo() {
			return TNodo.REAL;
		}

		public StringLocalizado num() {
			return num;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 6;
        }
	}
	
	public static class CTrue extends Exp {
		private StringLocalizado num;

		public CTrue(StringLocalizado num) {
			super();
			this.num = num;
		}

		public TNodo tipo() {
			return TNodo.CTRUE;
		}

		public StringLocalizado num() {
			return num;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 6;
        }
	}
	
	public static class CFalse extends Exp {
		private StringLocalizado num;

		public CFalse(StringLocalizado num) {
			super();
			this.num = num;
		}

		public TNodo tipo() {
			return TNodo.CFALSE;
		}

		public StringLocalizado num() {
			return num;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 6;
        }
	}
	
	public static class CInt extends Dec {

		public CInt(StringLocalizado id) {
			super(id);
		}

		public TNodo tipo() {
			return TNodo.CINT;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
	}
	
	public static class CReal extends Dec {

		public CReal(StringLocalizado id) {
			super(id);
		}

		public TNodo tipo() {
			return TNodo.CREAL;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
	}
	
	
	public static class CBool extends Dec {

		public CBool(StringLocalizado id) {
			super(id);
		}

		public TNodo tipo() {
			return TNodo.CBOOL;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
	}

	private static abstract class ExpBin extends Exp {
		private Exp arg0;
		private Exp arg1;

		public ExpBin(Exp arg0, Exp arg1) {
			super();
			this.arg0 = arg0;
			this.arg1 = arg1;
		}

		public Exp arg0() {
			return arg0;
		}

		public Exp arg1() {
			return arg1;
		}
		
	}
	
	private static abstract class ExpUni extends Exp {
		private Exp arg0;

		public ExpUni(Exp arg0) {
			super();
			this.arg0 = arg0;
		}

		public Exp arg0() {
			return arg0;
		}
	}

	public static class Suma extends ExpBin {
		public Suma(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public TNodo tipo() {
			return TNodo.SUMA;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
		public final int prioridad() {
            return 0;
        }
	}
	
	public static class Mul extends ExpBin {
		public Mul(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public TNodo tipo() {
			return TNodo.MUL;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 3;
        }
	}
	
	public static class Div extends ExpBin {
		public Div(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public TNodo tipo() {
			return TNodo.DIV;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 3;
        }
	}
	
	public static class Resta extends ExpBin {
		public Resta(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public TNodo tipo() {
			return TNodo.RESTA;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 0;
        }
	}
	
	public static class And extends ExpBin {
		public And(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public TNodo tipo() {
			return TNodo.AND;
		}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        } 
		public final int prioridad() {
            return 1;
        }
	}
	
	public static class Or extends ExpBin {
		public Or(Exp arg0, Exp arg1) {	super(arg0, arg1);}
		public TNodo tipo() { return TNodo.OR;}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 2;
        }
	}
	
	public static class Lt extends ExpBin {
		public Lt(Exp arg0, Exp arg1) {	super(arg0, arg1);}
		public TNodo tipo() { return TNodo.LT;}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 2;
        }
		}
	
	public static class Gt extends ExpBin {
		public Gt(Exp arg0, Exp arg1) {	super(arg0, arg1);}
		public TNodo tipo() { return TNodo.GT;}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }public final int prioridad() {
            return 2;
        }
		}
	
	public static class Leq extends ExpBin {
		public Leq(Exp arg0, Exp arg1) {	super(arg0, arg1);}
		public TNodo tipo() { return TNodo.LEQ;}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }public final int prioridad() {
            return 2;
        }
		}
	
	public static class Geq extends ExpBin {
		public Geq(Exp arg0, Exp arg1) {	super(arg0, arg1);}
		public TNodo tipo() { return TNodo.GEQ;}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }public final int prioridad() {
            return 2;
        }
		}
	
	public static class Eq extends ExpBin {
		public Eq(Exp arg0, Exp arg1) {	super(arg0, arg1);}
		public TNodo tipo() { return TNodo.EQ;}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }public final int prioridad() {
            return 2;
        }
	}
	
	public static class Neq extends ExpBin {
		public Neq(Exp arg0, Exp arg1) {	super(arg0, arg1);}
		public TNodo tipo() { return TNodo.NEQ;}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }public final int prioridad() {
            return 2;
        }
		}
	
	public static class Neg extends ExpUni {
		public Neg(Exp arg0) {	super(arg0);}
		public TNodo tipo() { return TNodo.NEG;}
		public void procesa(Procesamiento p) {
	        p.procesa(this); 
	    }public final int prioridad() {
            return 4;
        }
		}
	
	
	public static class Not extends ExpUni {
		public Not(Exp arg0) {	super(arg0);}
		public TNodo tipo() { return TNodo.NOT;}
		public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
		public final int prioridad() {
            return 4;
        }
		}
	
    public Decs decs_una(Dec dec) {
        return new Decs_una(dec);
    }
	
	public Exp id(StringLocalizado val) {
		return new Id(val);
	}

	public Exp entero(StringLocalizado val) {
		return new Entero(val);
	}

	public Exp real(StringLocalizado val) {
		return new Real(val);
	}
	
	public Exp ctrue(StringLocalizado val) {
		return new CTrue(val);
	}

	public Exp cfalse(StringLocalizado val) {
		return new CFalse(val);
	}

	public Dec cbool(StringLocalizado id) {
		return new CBool(id);
	}

	public Dec cint(StringLocalizado id) {
		return new CInt(id);
	}	

	public Dec creal(StringLocalizado id) {
		return new CReal(id);
	}
	
	public Asig asig(StringLocalizado id, Exp exp) {
		return new Asig(id, exp);
	}
	
	public StringLocalizado str(String s, int fila, int col) {
		return new StringLocalizado(s, fila, col);
	}
}
