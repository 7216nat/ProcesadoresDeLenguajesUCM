package tiny1.comp_semantica_estatica;

import tiny1.asint.ProcesamientoPorDefecto;
import tiny1.asint.TinyASint.*;

public class SimplificacionTipo extends ProcesamientoPorDefecto{
    private boolean verbose;
    public SimplificacionTipo(boolean verbose){
        this.verbose = verbose;
    }
    private void log(String msg) {
        if(verbose) {
            System.out.println("SimplificacionTipo: " + msg);
        }
    }
    @Override
    public void procesa(DVar exp) {
        exp.tipo().procesa(this);
        exp.setType(exp.tipo().type());
        log(exp.id().fila() + " " + exp.id().col()+ " " +exp.id().toString() + ": " + exp.tipo().toString() + " --> " + exp.tipo().tipoSimpl().toString() + ": " + exp.type().toString());
        exp.setTipo(exp.tipo().tipoSimpl());
    }

    @Override
    public void procesa(DTipo exp) {
        exp.tipo().procesa(this);
        exp.setType(exp.tipo().type());
        log(exp.id().fila() + " " + exp.id().col()+ " " +exp.id().toString() + ": " + exp.tipo().toString() + " --> " + exp.tipo().tipoSimpl().toString() + ": " + exp.type().toString());
        exp.setTipo(exp.tipo().tipoSimpl());
    }
    @Override
    public void procesa(ParRef par) {
        par.tipo().procesa(this);
        par.setType(par.tipo().type());
        log(par.id().fila() + " " + par.id().col()+ " " +par.id().toString() + ": " + par.tipo().toString() + " --> " + par.tipo().tipoSimpl().toString() + ": " + par.type().toString());
        par.setTipo(par.tipo().tipoSimpl());
    }
    @Override
    public void procesa(ParSinRef par) {
        par.tipo().procesa(this);
        par.setType(par.tipo().type());
        log(par.id().fila() + " " + par.id().col()+ " " +par.id().toString() + ": " + par.tipo().toString() + " --> " + par.tipo().tipoSimpl().toString() + ": " + par.type().toString());
        par.setTipo(par.tipo().tipoSimpl());
    }
    @Override
    public void procesa(Campo c) {
        c.tipo().procesa(this);
        c.setType(c.tipo().type());
        log(c.id().fila() + " " + c.id().col()+ " " +c.id().toString() + ": " + c.tipo().toString() + " --> " + c.tipo().tipoSimpl().toString() + ": " + c.type().toString());
        c.setTipo(c.tipo().tipoSimpl());
    }
    @Override
    public void procesa(ARRAY t) {
        t.tipo().procesa(this);
        log(t.tipo().toString());
        t.setTipo(t.tipo().tipoSimpl());
        log(t.tipo().toString());
    }
    @Override
    public void procesa(POINTER t) {
        t.tipo().procesa(this);
        log(t.tipo().toString());
        t.setTipo(t.tipo().tipoSimpl());
        log(t.tipo().toString());

    }
}