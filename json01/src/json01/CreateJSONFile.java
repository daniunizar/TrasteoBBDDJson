package json01;
import java.io.FileWriter;
import java.io.IOException;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class CreateJSONFile {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONArray list = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("nombre", "HARRY");
		obj.put("ape", "POTTER");
		
		list.add("POCIONES");
		list.add("HISTORIA DE LA MAGIA");
		list.add("DEFENSA CONTRA LAS ARTES OSCURAS");
		obj.put("asignaturas", list);
		try(FileWriter file = new FileWriter("myJSON.json")){
			file.write(obj.toString());
			file.flush();
		}catch(IOException e) {
			System.out.println("Errorcillo");
			e.printStackTrace();
		}
		System.out.println(obj);
		

	}

}
