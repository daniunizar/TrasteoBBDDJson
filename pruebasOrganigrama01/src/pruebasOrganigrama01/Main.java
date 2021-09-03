package pruebasOrganigrama01;

import java.sql.*;

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
			String miSentenciaSql = 
					"SELECT SUPERVISOR.NOMBRE AS SUPERVISOR_NOMBRE, "
					+ "SUPERVISOR.APELLIDO AS SUPERVISOR_APELLIDO, "
					+"GROUP_CONCAT(SUPERVISADO.NOMBRE ORDER BY SUPERVISADO.NOMBRE ) AS SUPERVISADO, "
			        +"COUNT(*) "  
					+"FROM JAVA_EMPLEADOS AS SUPERVISOR " 
					+"INNER JOIN JAVA_EMPLEADOS SUPERVISADO ON  SUPERVISOR.ID = SUPERVISADO.SUPERIOR " 
					+"GROUP BY SUPERVISOR_NOMBRE;"
					;
			ResultSet miResultset = miStatement.executeQuery(miSentenciaSql); //Devuelve un Objeto de tipo ResultSet
			
			//PASO 4. Leer el resulset recorri�ndolo
			while(miResultset.next()) {//mientras haya un registro hacia adelante del cursor nos desplazamos a �l y hacemos lo siguiente...
				System.out.print(miResultset.getString("SUPERVISOR_NOMBRE")+" ");
				System.out.print(miResultset.getString("SUPERVISOR_APELLIDO")+" ");
				System.out.print("supervisa a --> ");
				System.out.println(miResultset.getString("SUPERVISADO")+" ");

			}
		}catch(Exception e) {
			System.out.println("La conexi�n no se pudo realizar");
			e.printStackTrace();
		}
/* Consulta jer�rquica MYSQL
		SELECT  Supervisor.NOMBRE AS Supervisor, 
        GROUP_CONCAT(Supervisado.nombre  ORDER BY Supervisado.nombre ) AS Supervisado, 
        COUNT(*)  
FROM JAVA_EMPLEADOS AS Supervisor 
  INNER JOIN JAVA_EMPLEADOS Supervisado ON  Supervisor.ID = Supervisado.SUPERIOR 
GROUP BY Supervisor;
*/
	}

}
