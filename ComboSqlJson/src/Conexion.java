import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Conexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//PASO 1. Crear la conexi�n a la BBDD
			//Url, usuario y contrase�a
			//driver:protocolodriver:/url:puerto/nombreBBDD  usuario  password
			//SHOW VARIABLES WHERE VARIABLE_NAME IN ('hostname', 'port'); Se pone en SQL en MYSQL para saber el nombre y el puerto, de ah� sacamos el 3306 que adem�s es el por defecto para MYSQL
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", ""); //Creamos objeto de tipo Connection
			
			//PASO 2. Creamos el objeto de tipo Statement
			Statement miStatement = miConexion.createStatement(); //Creamos el objeto de tipo Statement
			
			//PASO 3. Ejecutar sentencia SQL
			String miSentenciaSql = "SELECT * FROM CONTACTOS";
			ResultSet miResultset = miStatement.executeQuery(miSentenciaSql); //Devuelve un Objeto de tipo ResultSet
			
			//PASO 4. Leer el resulset recorri�ndolo
			while(miResultset.next()) {//mientras haya un registro hacia adelante del cursor nos desplazamos a �l y hacemos lo siguiente...
				System.out.print(miResultset.getString("ID")+" ");
				System.out.print(miResultset.getString("NOMBRE")+" ");
				System.out.print(miResultset.getString("APELLIDO")+" ");
				System.out.println(miResultset.getString("TLF"));
				JSONObject obj = new JSONObject();
				JSONArray list = new JSONArray();
				obj.put("id", miResultset.getString("ID"));
				obj.put("nombre", miResultset.getString("NOMBRE"));
				obj.put("apellido", miResultset.getString("APELLIDO"));
				obj.put("tlf", miResultset.getString("TLF"));
				//NO S� QU� HACE
				try(FileWriter file = new FileWriter("myJSON.json")){
					file.write(obj.toString());
					file.flush();
				}catch(IOException e) {
					System.out.println("Error");
					e.printStackTrace();
				}
				//FIN NO S� QU� HACE
				System.out.println(obj);
				
			}
		}catch(Exception e) {
			System.out.println("La conexi�n no se pudo realizar");
			e.printStackTrace();
		}
		
	}

}
