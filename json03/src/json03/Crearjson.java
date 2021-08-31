package json03;

import com.google.gson.Gson;

public class Crearjson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   Persona persona = new Persona();
		    persona.setNombre("Unai");
		    persona.setEdad(28);

		    Gson gson = new Gson();        
		    System.out.println(gson.toJson(persona));
	}

}
