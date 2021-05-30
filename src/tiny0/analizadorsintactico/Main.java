package tiny0.analizadorsintactico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny0.asint.TinyASint.Prog;
import tiny0.procesamientos.Impresion;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Reader input = new InputStreamReader(new FileInputStream(args[0]));
        //AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(input);
        //asint.S();
        //System.out.println("OK");

        ConstructorAST constructorast = new ConstructorAST(input);
        Prog prog = constructorast.S();

        prog.procesa(new Impresion());
        
    }
}   
