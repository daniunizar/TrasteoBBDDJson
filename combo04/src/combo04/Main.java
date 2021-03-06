package combo04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
			String sentenciaSql = "SELECT "
					+ "J.NOMBRE AS 'NOMBRE_JEFE', "
					+ "J.APELLIDO AS 'APELLIDO_JEFE', "
					+ "J.CARGO AS 'CARGO_JEFE', "
					+ "S.NOMBRE AS 'NOMBRE_SUBORDINADO', "
					+ "S.APELLIDO AS 'APELLIDO_SUBORDINADO', "
					+ "S.CARGO AS 'CARGO_SUBORDINADO' "
					+ "FROM JAVA_CARGOS J LEFT JOIN JAVA_RELACIONES ON J.ID = JAVA_RELACIONES.ID_JEFE JOIN JAVA_CARGOS S ON S.ID = JAVA_RELACIONES.ID_SUBORDINADO";
					;
			ResultSet miResultset = miStatement.executeQuery(sentenciaSql); //Devuelve un Objeto de tipo ResultSet
			
			//PASO 4. Leer el resulset recorri?ndolo
			while(miResultset.next()) {//mientras haya un registro hacia adelante del cursor nos desplazamos a ?l y hacemos lo siguiente...
				System.out.print(miResultset.getString("NOMBRE_JEFE")+" ");//RECUPERAMOS A PARTIR DE LOS ALIAS
				System.out.print(miResultset.getString("APELLIDO_JEFE")+" ");
				System.out.print(miResultset.getString("CARGO_JEFE")+" ");
				System.out.print(" >  ");
				System.out.print(miResultset.getString("NOMBRE_SUBORDINADO"));
				System.out.print(miResultset.getString("APELLIDO_SUBORDINADO")+" ");
				System.out.println(miResultset.getString("CARGO_SUBORDINADO")+" ");
			}
		}catch(Exception e) {
			System.out.println("La conexi?n no se pudo realizar");
			e.printStackTrace();
		}

	}

}
