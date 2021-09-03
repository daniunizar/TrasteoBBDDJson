package pruebasOrganigrama02;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.Data;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//PASO 1. Crear la conexi�n a la BBDD
			//Url, usuario y contrase�a
			//driver:protocolodriver:/url:puerto/nombreBBDD  usuario  password
			//SHOW VARIABLES WHERE VARIABLE_NAME IN ('hostname', 'port'); Se pone en SQL en MYSQL para saber el nombre y el puerto, de ah� sacamos el 3306 que adem�s es el por defecto para MYSQL
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/novadevs", "root", ""); //Creamos objeto de tipo Connection
			
			//PASO 2. Creamos el objeto de tipo Statement
			Statement miStatement = miConexion.createStatement(); //Creamos el objeto de tipo Statement
			
			//PASO 3. Ejecutar sentencia SQL
			String sentenciaSql = "SELECT "
					+ "S.NOMBRE AS 'NOMBRE_EMPLEADO', "
					+ "S.ID AS 'ID_EMPLEADO', "
					+ "S.APELLIDO AS 'APELLIDO_EMPLEADO', "
					+ "S.SUPERIOR AS 'ID_JEFE', "
					+ "S.CARGO AS 'CARGO_EMPLEADO', "
					+ "J.NOMBRE AS 'NOMBRE_JEFE', "
					+ "J.APELLIDO AS 'APELLIDO_JEFE', "
					+ "J.CARGO AS 'CARGO_JEFE' "
					+ "FROM JAVA_EMPLEADOS J RIGHT JOIN JAVA_EMPLEADOS S ON S.SUPERIOR = J.ID;"
					;
			ResultSet miResultset = miStatement.executeQuery(sentenciaSql); //Devuelve un Objeto de tipo ResultSet
			//PASO 4. Leer el resulset recorri�ndolo
			List<Empleado> dataList = new LinkedList<>();//Lista que contendr� todos los Empleados para el JSON
			while(miResultset.next()) {//mientras haya un registro hacia adelante del cursor nos desplazamos a �l y hacemos lo siguiente...
				//Imprimimos por pantalla
				System.out.print(miResultset.getString("NOMBRE_EMPLEADO")+" ");//RECUPERAMOS A PARTIR DE LOS ALIAS
				System.out.print(miResultset.getString("APELLIDO_EMPLEADO")+" ");
				System.out.print(miResultset.getString("CARGO_EMPLEADO")+" ");
				System.out.print(miResultset.getString("ID_EMPLEADO")+" ");

				System.out.print(" >  ");
				System.out.print(miResultset.getString("NOMBRE_JEFE")+" ");
				System.out.print(miResultset.getString("APELLIDO_JEFE")+" ");
				System.out.print(miResultset.getString("ID_JEFE")+" "); //SE EXTRAE DEL SUBORDINADO, NO DEL SUPERIOR
				System.out.println(miResultset.getString("CARGO_JEFE")+" ");
						
				//Convertimos cada empleado en objeto
				Empleado emp = new Empleado(miResultset.getInt("ID_EMPLEADO"), miResultset.getString("NOMBRE_EMPLEADO"), miResultset.getString("APELLIDO_EMPLEADO"), miResultset.getString("CARGO_EMPLEADO"), miResultset.getInt("ID_JEFE") );
				System.out.println(emp);
				dataList.add(emp);
				//Formateamos cada objeto empleado a Json con Gson
				//Gson gson = new Gson();
			    //System.out.println(gson.toJson(emp));
				Gson gson = new Gson();
				String representacionJSON = gson.toJson(emp);
				System.out.println(representacionJSON);
				//bonito
				Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
				String representacionBonita = prettyGson.toJson(emp);
				System.out.println(representacionBonita);
			}
			//Json que contiene todos los empleados en uno s�lo json. Los recopila en el List
			Gson gson2 = new Gson();
			String json = gson2.toJson(dataList);
			System.out.println(json);
		}catch(Exception e) {
			System.out.println("La conexi�n no se pudo realizar");
			e.printStackTrace();
		}
	}

}
