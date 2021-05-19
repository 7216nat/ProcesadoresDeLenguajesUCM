package tiny1.analizadorlexico;

public class AlexOperations {
    private AnalizadorLexico alex;
    public AlexOperations(AnalizadorLexico alex) {
        this.alex = alex;   
    }
    public UnidadLexica token(int clase) {
        UnidadLexica t = new UnidadLexica(alex.fila(), alex.col(), clase, alex.lexema());
        alex.incCol();
        return t;     
  }
}
