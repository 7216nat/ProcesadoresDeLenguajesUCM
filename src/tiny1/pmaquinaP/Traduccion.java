package tiny1.pmaquinaP;

import java.util.HashMap;

import tiny1.asint.Procesamiento;
import tiny1.asint.TinyASint.*;

public class Traduccion implements Procesamiento{

	private MaquinaP m;
	private HashMap<String, Integer> direcciones;
	
	public MaquinaP getMaquinaP() {
		return m;
	}
	
	public Traduccion(MaquinaP m, HashMap<String, Integer> direcciones) {
		this.m = m;
		this.direcciones = direcciones;
	}
	
	@Override
	public void procesa(Prog prog) {
        prog.decs().procesa(this);
        prog.insts().procesa(this);
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
	public void procesa(DProc exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(NoPars exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParsComp exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParsSimp exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParRef exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParSinRef exp) {
		// TODO Auto-generated method stub
		
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
			// TODO Econtrar tamaño
			m.ponInstruccion(m.mueve(exp.exp0().tam()));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(IWrite exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(INew exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(IDelete exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(INl exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ICall proc) {
		//m.ponInstruccion(m.activa(proc.nivel(), proc.tam(), proc.etqi()));
		//m.ponInstruccion(m.dup());
		//m.ponInstruccion(m.apilaInt(proc.par));
		
		
		
		
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
		
		// TODO ATENCIÓN ESTO SE TIENE QUE HACER CON EL TAMAÑO DEL TIPO BASE DEL ARRAY
		m.ponInstruccion(m.apilaInt(1));
		m.ponInstruccion(m.mul());
		m.ponInstruccion(m.suma());
	}

	@Override
	public void procesa(Ptr exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Atr exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Indir exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Parentesis exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Ent exp) {
		m.ponInstruccion(m.apilaInt(Integer.parseInt(exp.ent().toString())));	
	}

	@Override
	public void procesa(IdenExp exp) {
		
		// TODO ATENCIÓN AQUÍ HAY QUE DIFERENCIAR ENTRE VARIABLES LOCALES Y GLOBALES Y TAL
		
		m.ponInstruccion(m.apilaInt(direcciones.get(exp.id().toString())));	
		
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
		m.ponInstruccion(m.apilaCadena(exp.cadena().toString()));	
	}

	@Override
	public void procesa(Null exp) {

		
	}

}
