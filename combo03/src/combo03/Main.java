package combo03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Main {

	public static void main(String[] args) {
		
		try {
			//PASO 1. Crear la conexi?n a la BBDD
			//Url, usuario y contrase?a
			//driver:protocolodriver:/url:puerto/nombreBBDD  usuario  password
			//SHOW VARIABLES WHERE VARIABLE_NAME IN ('hostname', 'port'); Se pone en SQL en MYSQL para saber el nombre y el puerto, de ah? sacamos el 3306 que adem?s es el por defecto para MYSQL
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/novadevs", "root", ""); //Creamos objeto de tipo Connection
			
			//PASO 2. Creamos el objeto de tipo Statement
			Statement miStatement = miConexion.createStatement(); //Creamos el objeto de tipo Statement
			
			//PASO 3. Ejecutar sentencia SQL
			String miSentenciaSql = "SELECT * FROM JAVA_CARGOS";
			ResultSet miResultset = miStatement.executeQuery(miSentenciaSql); //Devuelve un Objeto de tipo ResultSet
			
			//PASO 4. Leer el resulset recorri?ndolo
			while(miResultset.next()) {//mientras haya un registro hacia adelante del cursor nos desplazamos a ?l y hacemos lo siguiente...
				System.out.print(miResultset.getString("ID")+" ");
				System.out.print(miResultset.getString("NOMBRE")+" ");
				System.out.print(miResultset.getString("APELLIDO")+" ");
				System.out.print(miResultset.getString("CARGO"));
				PreparedStatement sentenciaSQL2 = miConexion.prepareStatement("SELECT * FROM JAVA_RELACIONES"
						+ " WHERE ID_JEFE=?");
				sentenciaSQL2.setString(1, miResultset.getString(1));
				ResultSet rs2 = sentenciaSQL2.executeQuery();
				System.out.print(" Subordinados: ");
				while(rs2.next()) {
					System.out.print(rs2.getString(2)+ " ");
				}
				rs2.close();
				System.out.println("\n");
			}
			
			//INTENTO SERIO
			String sentenciaSql = "SELECT "
					+ "J.NOMBRE AS 'NOMBRE_JEFE', "
					+ "S.NOMBRE AS 'NOMBRE_SUBORDINADO',"
					+ "FROM JAVA_CARGOS J "
					+ "LEFT JOIN JAVA_RELACIONES "
					+ "ON J.ID = JAVA_RELACIONES.ID_JEFE "
					+ "JOIN JAVA_CARGOS S "
					+ "ON S.ID = JAVA_RELACIONES.ID_SUBORDINADO"
					;
		}catch(Exception e) {
			System.out.println("La conexi?n no se pudo realizar");
			e.printStackTrace();
		}
		
		
		
		
		

	}

}
