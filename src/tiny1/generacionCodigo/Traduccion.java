package tiny1.generacionCodigo;

import java.util.HashMap;

import tiny1.asint.Procesamiento;
import tiny1.asint.TinyASint.*;
import tiny1.errors.GestionErrores;
import tiny1.pmaquinaP.MaquinaP;

public class Traduccion implements Procesamiento{

	private MaquinaP m;
	
	public MaquinaP getMaquinaP() {
		return m;
	}
	
	public Traduccion(MaquinaP m) {
		this.m = m;
	}
	
	@Override
	public void procesa(Prog prog) {
        prog.insts().procesa(this);
        prog.decs().procesa(this);
        m.ponInstruccion(m.stop());
	}

	@Override
	public void procesa(NoDecs exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(AuxDecs decs) {
		decs.decs().procesa(this);
		
	}

	@Override
	public void procesa(LDecSimp decs) {
		decs.dec().procesa(this);
		
	}

	@Override
	public void procesa(LDecComp decs) {
        decs.decs().procesa(this);
        decs.dec().procesa(this);
		
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
	public void procesa(DProc dec) {
		dec.bloque().procesa(this);
		
	}

	@Override
	public void procesa(NoPars exp) {
		// TODO Auto-generated method stub
		
	}

	public void procesa(ParsComp pars) {
		pars.pars().procesa(this);
		pars.par().procesa(this);
	}

	public void procesa(ParsSimp pars) {
		pars.par().procesa(this);
	}

	public void procesa(ParRef par) {

	}

	public void procesa(ParSinRef par) {
		

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
	public void procesa(OK exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ERROR exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(NULL exp) {
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
	public void procesa(NoInsts exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstsComp is) {
		is.insts().procesa(this);
        is.inst().procesa(this);
	}

	@Override
	public void procesa(InstsSimp is) {
		is.inst().procesa(this);
	}

	@Override
	public void procesa(IAsig exp) {

		exp.exp0().procesa(this);
		exp.exp1().procesa(this);
		
		if(exp.exp1().esDesignador()) {
			m.ponInstruccion(m.mueve(exp.exp0().getTipo().tam()));
		} else {
			m.ponInstruccion(m.desapilaInd());
		}

	}

	@Override
	public void procesa(IIfThen exp) {
		exp.exp().procesa(this);
		m.ponInstruccion(m.irF(exp.etqs()));
		exp.insts().procesa(this);
	}

	@Override
	public void procesa(IIfThenElse exp) {
		exp.exp().procesa(this);
		m.ponInstruccion(m.irF(exp.insts1().etqi()));
		exp.insts0().procesa(this);
		m.ponInstruccion(m.irA(exp.insts1().etqs()));
		exp.insts1().procesa(this);
	}

	@Override
	public void procesa(IWhile exp) {
		exp.exp().procesa(this);
		m.ponInstruccion(m.irF(exp.etqs()));
		exp.insts().procesa(this);
		m.ponInstruccion(m.irA(exp.etqi()));	
	}

	@Override
	public void procesa(IRead exp) {
		exp.exp().procesa(this);	
		m.ponInstruccion(m.read());			
		m.ponInstruccion(m.desapilaInd());	
	}

	@Override
	public void procesa(IWrite exp) {
		exp.exp().procesa(this);
		
		if(exp.exp().esDesignador())
			m.ponInstruccion(m.apilaInd());
		
		m.ponInstruccion(m.write());
		
	}

	@Override
	public void procesa(INew exp) {	
		exp.exp().procesa(this);
		m.ponInstruccion(m.alloc(exp.exp().getTipo().tam()));		
		m.ponInstruccion(m.desapilaInd());	

	}

	@Override
	public void procesa(IDelete exp) {
		exp.exp().procesa(this);
		m.ponInstruccion(m.dealloc(exp.exp().getTipo().tam()));		
		//GestionErrores.errorBorradoMemoriaDinamicaEjecucion(exp.exp().str());
	}

	@Override
	public void procesa(INl exp) {
		m.ponInstruccion(m.nl());
	}

	@Override
	public void procesa(ICall proc) {
	
		int nivel = proc.vinculo().getAmbito() + 1;
		int tam = proc.vinculo().tam();
		int sigdir = proc.etqs();
		int pdir = proc.vinculo().etqi();
		
		Pars pars = ((DProc)proc.vinculo()).pars();
		Exps exps = proc.exps();	
		
		m.ponInstruccion(m.activa(nivel, tam, sigdir));
		
		do {
			
			Par par = pars.par();
			Exp exp = exps.exp();
	
			m.ponInstruccion(m.dup());
			m.ponInstruccion(m.apilaInt(par.getDir()));
			m.ponInstruccion(m.suma());
			
			exp.procesa(this);
			
			if(proc.exps().exp().esDesignador()) {
				if(par instanceof ParRef) {
					m.ponInstruccion(m.mueve(par.tam()));
				} else {
					m.ponInstruccion(m.desapilaInd());
				}			
			} else {
				if(par instanceof ParRef) {

				} else {
					m.ponInstruccion(m.desapilaInd());
				}	
			}
			
			pars = pars.pars();
			exps = exps.exps();
			
		} while(pars != null && exps != null);
		
		m.ponInstruccion(m.desapilad(nivel));
		m.ponInstruccion(m.irA(pdir));		
		
	}

	@Override
	public void procesa(Bloque i) {
		i.prog().procesa(this);		
	}

	@Override
	public void procesa(NoExps exp) {
		// TODO Auto-generated method stub
		
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

	public void aux_expBin(ExpBin exp) {
		exp.arg0().procesa(this);
		if(exp.arg0().esDesignador())
			m.ponInstruccion(m.apilaInd());		
		exp.arg1().procesa(this);
		if(exp.arg1().esDesignador())
			m.ponInstruccion(m.apilaInd());	
	}
	
	public void aux_expUni(ExpUni exp) {
		exp.arg().procesa(this);
		if(exp.arg().esDesignador())
			m.ponInstruccion(m.apilaInd());		
	}
	
	@Override
	public void procesa(Suma exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.suma());		
	}

	@Override
	public void procesa(Resta exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.resta());				
	}

	@Override
	public void procesa(And exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.and());	
	}

	@Override
	public void procesa(Or exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.or());	
	}

	@Override
	public void procesa(LT exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.lt());	
	}

	@Override
	public void procesa(GT exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.gt());	
	}

	@Override
	public void procesa(LE exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.le());	
	}

	@Override
	public void procesa(GE exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.ge());	
	}

	@Override
	public void procesa(NE exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.neq());	
	}

	@Override
	public void procesa(EQ exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.eq());	
	}

	@Override
	public void procesa(Mul exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.mul());
	}

	@Override
	public void procesa(Mod exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.mod());
	}

	@Override
	public void procesa(Div exp) {
		aux_expBin(exp);
		m.ponInstruccion(m.div());
	}

	@Override
	public void procesa(Not exp) {
		aux_expUni(exp);
		m.ponInstruccion(m.not());
	}

	@Override
	public void procesa(Neg exp) {
		aux_expUni(exp);
		m.ponInstruccion(m.neg());	
	}

	@Override
	public void procesa(Index exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		
		if(exp.arg1().esDesignador())
			m.ponInstruccion(m.apilaInd());
		
		m.ponInstruccion(m.apilaInt(exp.getTipo().tam()));
		m.ponInstruccion(m.mul());
		m.ponInstruccion(m.suma());
	}

	@Override
	public void procesa(Ptr exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Atr exp) {
		exp.exp().procesa(this);
		
		Campo campo = ((REGISTRO)exp.exp().vinculo().tipo()).getList().get(exp.id().toString());
		
		m.ponInstruccion(m.apilaInt(campo.desp()));
		m.ponInstruccion(m.suma());
	}

	@Override
	public void procesa(Indir exp) {
		exp.arg().procesa(this);
		m.ponInstruccion(m.apilaInd());	
	}

	@Override
	public void procesa(Parentesis exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Ent exp) {
		m.ponInstruccion(m.apilaInt(Integer.parseInt(exp.str().toString())));	
	}

	@Override
	public void procesa(IdenExp exp) {
		
		if(exp.vinculo().getAmbito() == 0) {
			// Variable global
			m.ponInstruccion(m.apilaInt(exp.vinculo().getDir()));	
		} else {
			m.ponInstruccion(m.apilad(exp.vinculo().getAmbito()));	
			m.ponInstruccion(m.apilaInt(exp.vinculo().getDir()));	
			m.ponInstruccion(m.suma());	
		}
		
		// TODO ATENCI�N AQU� HAY QUE DIFERENCIAR ENTRE VARIABLES LOCALES Y GLOBALES Y TAL

		
		
	}

	@Override
	public void procesa(Lreal exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(True exp) {
		m.ponInstruccion(m.apilaBool(true));	
	}

	@Override
	public void procesa(False exp) {
		m.ponInstruccion(m.apilaBool(false));	
	}

	@Override
	public void procesa(Cadena exp) {
		m.ponInstruccion(m.apilaCadena(exp.str().toString()));	
	}

	@Override
	public void procesa(Nnull exp) {

		
	}

}
