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
        log(exp.id().fila() + " " + exp.id().col()+ " " +exp.id().toString() + ": " + exp.type().toString());
    }

    @Override
    public void procesa(DTipo exp) {
        exp.tipo().procesa(this);
        exp.setType(exp.tipo().type());
        log(exp.id().fila() + " " + exp.id().col()+ " " +exp.id().toString() + ": " + exp.type().toString());
    }
    @Override
    public void procesa(ParRef par) {
        par.setType(par.tipo().type());
        log(par.id().fila() + " " + par.id().col()+ " " +par.id().toString() + ": " + par.type().toString());
    }
    @Override
    public void procesa(ParSinRef par) {
        par.setType(par.tipo().type());
        log(par.id().fila() + " " + par.id().col()+ " " +par.id().toString() + ": " + par.type().toString());
    }
    @Override
    public void procesa(Campo c) {
        c.setType(c.tipo().type());
        log(c.id().fila() + " " + c.id().col()+ " " +c.id().toString() + ": " + c.type().toString());
    }
}