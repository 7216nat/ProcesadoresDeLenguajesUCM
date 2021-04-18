package tiny0.analizadorlexico;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import tiny0.errors.GestionErroresTiny;
class Main {
	public static void main(String[] args) throws Exception {
		
		if(args.length < 1)
			throw new Exception("Elige el nombre de archivo como argumento de entrada");
	      
	    // Se crea una maquina de estados con el nodo inicial y el codigo que hay que analizar lexicamente
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
	    Analizador analizador = new Analizador(input);
		analizador.fijaGestionErrores(new GestionErroresTiny());	    	
		
		while(true) {
						
			try {
				
				// Tratamos de obtener una unidad lexica hasta que demos con un EOF
			    UL ul = analizador.getToken();			    
				
			    System.out.println(ul.toString());
			    
			    if(ul.clase == CL.EOF) break;
			    
			} catch(Exception e) {
				
				System.out.println("Error parseando");
				break;
			}
			
		}	
		
	}
	
}
