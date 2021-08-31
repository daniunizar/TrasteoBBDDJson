package conectaBD;

import java.sql.Connection;
import java.sql.*;

public class Consulta_Preparada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PASO 1. Crear la conexión a la BBDD
		try {
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", ""); //Creamos objeto de tipo Connection

			//PASO 2. Preparar la consulta
			PreparedStatement sentenciaSQL = miConexion.prepareStatement("SELECT ID, NOMBRE, APELLIDO, TLF FROM CONTACTOS"
			+ " WHERE NOMBRE=? AND APELLIDO=?");
			
			//PASO 3. Establecer los parámetros de consulta. Orden de interrogante y valor
			sentenciaSQL.setString(1, "Harry");//nombre y apellido que son los 2 interrogantes son de tipo string, por eso usamos setString();
			sentenciaSQL.setString(2, "Potter");
			
			//PASO 4. Ejecutar la consulta y recuperar de ella un objeeto ResultSet
			ResultSet rs = sentenciaSQL.executeQuery();
			
			//PASO 5. Leer la consulta
			while(rs.next()) {
				System.out.print(rs.getString("ID")+" ");
				System.out.print(rs.getString("NOMBRE")+" ");
				System.out.print(rs.getString("APELLIDO")+" ");
				System.out.println(rs.getString("TLF")+" ");
			}
			rs.close();//cerrar el resultset y liberar la memoria
			
			//Reutilización de la consulta preparada con otros parámetros. Por eso nos saltamos el paso 2. Y como la conexión no la hemos cerrado, también el 1.
			
			//PASO 3. Establecer los parámetros de consulta. Orden de interrogante y valor
			sentenciaSQL.setString(1, "Hermione");//nombre y apellido que son los 2 interrogantes son de tipo string, por eso usamos setString();
			sentenciaSQL.setString(2, "Granger");
			
			//PASO 4. Ejecutar la consulta y recuperar de ella un objeeto ResultSet
			rs = sentenciaSQL.executeQuery(); //Como ya se declaró antes no hace falta poner tipo de dato
			
			//PASO 5. Leer la consulta
			while(rs.next()) {
				System.out.print(rs.getString("ID")+" ");
				System.out.print(rs.getString("NOMBRE")+" ");
				System.out.print(rs.getString("APELLIDO")+" ");
				System.out.println(rs.getString("TLF")+" ");
			}
			rs.close();//cerrar el resultset y liberar la memoria
			
		}catch(SQLException e) {
			System.out.println("Error al conectar con la BBDD");
			e.printStackTrace();

		}
		
	}

}
