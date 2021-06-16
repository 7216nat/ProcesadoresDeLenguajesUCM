package tiny1.comp_semantica_estatica;

import java.util.HashSet;
import java.util.Set;
import tiny1.utils.Par;

import tiny1.asint.TinyASint.*;
public class TypeCompatibilidad {
    private TypeCompatibilidad(){
        throw new IllegalStateException("TypeCompatibilidad class");
    }

    public static boolean comprobar(Tipo t1, Tipo t2) {
        return comprobar(t1, t2, new HashSet<>());
    }
    
    private static boolean comprobar(Tipo t1, Tipo t2, Set<Par<Tipo, Tipo>> set) {
        
        if(set.add(new Par<>(t1, t2))) {

            if(t1.equals(t2)) return true;

            // int y real
            if(t1.type() == Type.INT && t2.type() == Type.REAL) return true;
            if(t1.type() == Type.REAL && t2.type() == Type.INT) return true;

            // array
            if(t1.type() == Type.ARRAY && t2.type() == Type.ARRAY) {
                ARRAY a1 = (ARRAY)t1;
                ARRAY a2 = (ARRAY)t2;
                if(a1.getDim() != a2.getDim()) return false;
                return comprobar(a1.tipo().tipoSimpl(), a2.tipo().tipoSimpl());
            }

            // registro
            if(t1.type() == Type.RECORD && t2.type() == Type.RECORD) {
                REGISTRO r1 = (REGISTRO)t1;
                REGISTRO r2 = (REGISTRO)t2;
                
                if (r1.getList().size() != r2.getList().size())
                    return false;
                Campos cs1 = r1.campos();
                Campos cs2 = r2.campos();
                if (cs1.equals(cs2)) return true;
                do {
                    if (!cs1.campo().id().equals(cs2.campo().id()))
                        return false;
                    if (!comprobar(cs1.campo().tipo().tipoSimpl(), cs2.campo().tipo().tipoSimpl()))
                        return false;
                    cs1 = cs1.campos();
                    cs2 = cs2.campos();
                } while (cs1.campos() != null && cs2.campos() != null);

                return (cs1.campos() == null && cs2.campos() == null);
            }
            
            // puntero y null
            if(t1.type() == Type.POINTER && t2.type() == Type.NULL) 
                return true;
            
            // puntero puntero
            if(t1.type() == Type.POINTER && t2.type() == Type.POINTER) {
                POINTER p1 = (POINTER)t1;
                POINTER p2 = (POINTER)t2;
                
                return comprobar(p1.tipo().tipoSimpl(), p2.tipo().tipoSimpl(), set);
            }

            return false;
            
        }

        else {
            return true;
        }
    }
}
