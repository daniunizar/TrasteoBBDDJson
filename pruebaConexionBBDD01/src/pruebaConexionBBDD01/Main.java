package pruebaConexionBBDD01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Lectura lectura = new Lectura();
			lectura.listaDirActual();
			String detalles = lectura.lee("src/detalles.prod");
			String user = lectura.lee("src/user.prod");
			String pass=lectura.lee("src/pass.prod");
			
			
			Connection miConexion = DriverManager.getConnection(detalles, user, pass);
			Statement miStatement = miConexion.createStatement(); 
			String sentenciaSql="SELECT * FROM JAVA_EMPLEADOS";
			ResultSet miResultset = miStatement.executeQuery(sentenciaSql); //Devuelve un Objeto de tipo ResultSet
			while(miResultset.next()) {
				System.out.println(miResultset.getString("NOMBRE"));//RECUPERAMOS A PARTIR DE LOS ALIAS
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
