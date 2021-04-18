/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package tiny0.analizadorsintactico;

import tiny0.analizadorlexico.*;
import tiny0.errors.*;

import java.io.IOException;
import java.io.Reader;

public class AnalizadorSintacticoTiny {
private UL anticipo;
private Analizador alex;
private GestionErroresTiny errores;
public AnalizadorSintacticoTiny(Reader input) {
    errores = new GestionErroresTiny();
    alex = new Analizador(input);
    alex.fijaGestionErrores(errores);
    sigToken();
}
public void S() {
    Ds();
    empareja(CL.SEP);
    ASIGs();
    empareja(CL.EOF);
}
private void Ds() {
    switch(anticipo.clase()) {
        case CINT: case CREAL: case CBOOL:          
            LDs();
            break;
        case SEP:
            break;
        default:
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.CINT, CL.CREAL, CL.CBOOL, CL.SEP);                                            
    }
}
private void LDs() {
    D();
    RLDs();
}
private void D() {
    switch(anticipo.clase()) {
        case CINT: 
            empareja(CL.CINT);
            break;
        case CREAL:
            empareja(CL.CREAL);
            break;
        case CBOOL:
            empareja(CL.CBOOL);
            break;
        default: 
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.CINT, CL.CREAL, CL.CBOOL); 
    }
    empareja(CL.IDEN);                                      
}    
private void RLDs() {
    switch(anticipo.clase()) {
        case PC:    
            empareja(CL.PC);
            D();
            RLDs();
            break;
        case SEP: break;    
        default:
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                            CL.PC, CL.SEP);                                       
    }          
}
private void ASIGs(){
    switch(anticipo.clase()) {
        case IDEN:    
            LASIGs();
            break;
        case EOF: break;    
        default:
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                            CL.IDEN, CL.EOF);                                       
    }        
}
private void LASIGs(){
    ASIG();
    RLASIGs();       
}
private void RLASIGs(){
    switch(anticipo.clase()) {
        case PC:
            empareja(CL.PC);
            ASIG();
            RLASIGs();
            break;
        case EOF: break;
        default:
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                        CL.IDEN, CL.EOF); 
    }     
}
private void ASIG(){
    empareja(CL.IDEN);
    empareja(CL.ASIG);
    E0();
}      
private void E0() {
    switch(anticipo.clase()) {
        case IDEN: case CNOT: case MENOS: case CTRUE: case CFALSE: case REAL: case ENTERO: case PAP:
            E1();
            RE0();
            break;
        default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                        CL.IDEN,CL.ENTERO,CL.CNOT,CL.MENOS,CL.CTRUE,
                                        CL.CFALSE,CL.REAL,CL.PAP);                                    
    }  
}
private void RE0() {
    switch(anticipo.clase()) {
        case MAS:
            empareja(CL.MAS);
            E0();
            break;
        case MENOS:
            empareja(CL.MENOS);
            E1();
            break; 
        case PCIE: case EOF: case PC: break;
        default:    
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.MAS,CL.MENOS);                                              
    } 
}
private void E1() {
    switch(anticipo.clase()) {
        case IDEN: case CNOT: case MENOS: case CTRUE: case CFALSE: case REAL: case ENTERO: case PAP:
            E2();
            RE1();
            break;
        default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                            CL.IDEN,CL.ENTERO,CL.CNOT,CL.MENOS,CL.CTRUE,
                                            CL.CFALSE,CL.REAL,CL.PAP);                             
    }  
}
private void RE1() {
    switch(anticipo.clase()) {
        case COR: case CAND: 
            Op1();
            E2();
            RE1();
            break;
        case PCIE: case EOF: case MAS: case MENOS: case PC: break;
        default:    
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.COR,CL.CAND);                                              
    } 
}
private void E2() {
    switch(anticipo.clase()) {
        case IDEN: case CNOT: case MENOS: case CTRUE: case CFALSE: case REAL: case ENTERO: case PAP:
            E3(); 
            RE2(); 
            break;
        default:     
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.IDEN,CL.ENTERO,CL.CNOT,CL.MENOS,CL.CTRUE,
                                    CL.CFALSE,CL.REAL,CL.PAP);
    }   
}
private void RE2() {
    switch(anticipo.clase()) {
        case GT: case GE: case LT: case LE: case EQ: case NE:
            Op2();
            E3(); 
            RE2(); 
            break;
        case COR: case CAND: case PCIE: case EOF: case MAS: case MENOS: case PC: break;
        default:     
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.GT,CL.GE,CL.LE,CL.LT,CL.EQ,CL.NE);
    }   
}
private void E3() {
    switch(anticipo.clase()) {
        case IDEN: case CNOT: case MENOS: case CTRUE: case CFALSE: case REAL: case ENTERO: case PAP:
            E4(); 
            RE3(); 
            break;
        default:     
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.IDEN,CL.ENTERO,CL.CNOT,CL.MENOS,CL.CTRUE,
                                    CL.CFALSE,CL.REAL,CL.PAP);
    }   
}
private void RE3() {
    switch(anticipo.clase()) {
        case POR: case DIV:
            Op3();
            E4(); 
            break;
        case PCIE: case EOF: case MAS: case MENOS: case PC: case COR: case CAND:
        case NE: case EQ: case GT: case GE: case LT: case LE: break;
        default:     
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.POR,CL.DIV);
    }   
}
private void E4() {
    switch(anticipo.clase()) {
        case MENOS:
            empareja(CL.MENOS);
            E5(); 
            break;
        case CNOT:
            empareja(CL.CNOT);
            E4();
            break;
        case IDEN: case CTRUE: case CFALSE: case REAL: case ENTERO: case PAP:
            E5();
            break;
        default:     
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.IDEN,CL.ENTERO,CL.CNOT,CL.MENOS,CL.CTRUE,
                                    CL.CFALSE,CL.REAL,CL.PAP);
    }   
}
private void E5() {
    switch(anticipo.clase()) {
        case IDEN:
            empareja(CL.IDEN);
            break;
        case ENTERO:
            empareja(CL.ENTERO);
            break;
        case REAL:
            empareja(CL.REAL);
            break;
        case CTRUE:
            empareja(CL.CTRUE);
            break;
        case CFALSE:
            empareja(CL.CFALSE);
            break;
        case PAP:
            empareja(CL.PAP);
            E0();
            empareja(CL.PCIE);
            break;
        default:     
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.IDEN,CL.ENTERO,CL.CTRUE,
                                    CL.CFALSE,CL.REAL,CL.PAP);
    }   
}  
private void Op1() {
    switch(anticipo.clase()) {
        case CAND: empareja(CL.CAND); break;  
        case COR: empareja(CL.COR); break;  
        default:    
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.CAND,CL.COR);
    }  
}
private void Op2() {
    switch(anticipo.clase()) {
        case GT: empareja(CL.GT); break;  
        case GE: empareja(CL.GE); break;
        case LE: empareja(CL.LE); break;  
        case LT: empareja(CL.LT); break;  
        case EQ: empareja(CL.EQ); break;  
        case NE: empareja(CL.NE); break;    
        default:    
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.GT, CL.GE, CL.LE, CL.LT
                                    ,CL.EQ, CL.NE);
    }  
}
private void Op3() {
    switch(anticipo.clase()) {
        case POR: empareja(CL.POR); break;  
        case DIV: empareja(CL.DIV); break;  
        default:    
            errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                                    CL.POR,CL.DIV);
    }  
}
private void empareja(CL claseEsperada) {
    if (anticipo.clase() == claseEsperada)
        sigToken();
    else errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),claseEsperada);
}
private void sigToken() {
    try {
        anticipo = alex.getToken();
    }
    catch(IOException e) {
        errores.errorFatal(e);
    }
}

}
