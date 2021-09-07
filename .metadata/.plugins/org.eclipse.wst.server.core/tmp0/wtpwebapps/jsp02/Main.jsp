<%@ page import="jsp02.Empleado" %>
<%@ page import="jsp02.Lectura" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi JSP</title>
</head>
<body>
<%
Class.forName("com.mysql.jdbc.Driver");//En references libraries
Class.forName("com.google.gson.Gson");
Class.forName("com.google.gson.GsonBuilder");


//Lectura de detalles de conexión
Lectura lectura = new Lectura();

String url_detalles = "detalles.txt";
String url_usuario = "user.txt";
String url_pass = "pass.txt";
String detalles = lectura.lee(url_detalles);
String usuario = lectura.lee(url_usuario);
String pass = lectura.lee(url_pass);
System.out.println("Detalles de conexión: "+detalles);
System.out.println("Usuario: "+usuario);
String user = usuario;
//String pass = lectura.lee(url_pass);
//lectura.listaDirActual();
try {
	//PASO 1. Crear la conexión a la BBDD
	//Url, usuario y contraseña
	//driver:protocolodriver:/url:puerto/nombreBBDD  usuario  password
	//SHOW VARIABLES WHERE VARIABLE_NAME IN ('hostname', 'port'); Se pone en SQL en MYSQL para saber el nombre y el puerto, de ahí sacamos el 3306 que además es el por defecto para MYSQL
	Connection miConexion = DriverManager.getConnection(detalles, user, pass); //Creamos objeto de tipo Connection
	//Connection miConexion = DriverManager.getConnection(detalles, "root", ""); //Creamos objeto de tipo Connection

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
	//PASO 4. Leer el resulset recorriéndolo
	List<Empleado> dataList = new LinkedList<>();//Lista que contendrá todos los Empleados para el JSON
	while(miResultset.next()) {//mientras haya un registro hacia adelante del cursor nos desplazamos a él y hacemos lo siguiente...
		//Imprimimos por pantalla
		out.print(miResultset.getString("NOMBRE_EMPLEADO")+" ");//RECUPERAMOS A PARTIR DE LOS ALIAS
		out.print(miResultset.getString("APELLIDO_EMPLEADO")+" ");
		out.print(miResultset.getString("CARGO_EMPLEADO")+" ");
		out.print(miResultset.getString("ID_EMPLEADO")+" ");

		out.print(" >  ");
		out.print(miResultset.getString("NOMBRE_JEFE")+" ");
		out.print(miResultset.getString("APELLIDO_JEFE")+" ");
		out.print(miResultset.getString("ID_JEFE")+" "); //SE EXTRAE DEL SUBORDINADO, NO DEL SUPERIOR
		out.println(miResultset.getString("CARGO_JEFE")+" ");
				
		//Convertimos cada empleado en objeto
		Empleado emp = new Empleado(miResultset.getInt("ID_EMPLEADO"), miResultset.getString("NOMBRE_EMPLEADO"), miResultset.getString("APELLIDO_EMPLEADO"), miResultset.getString("CARGO_EMPLEADO"), miResultset.getInt("ID_JEFE") );
		out.println(emp);
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
		out.println(representacionBonita);
	}
	//Json que contiene todos los empleados en uno sólo json. Los recopila en el List
	Gson gson2 = new Gson();
	String json = gson2.toJson(dataList);
	out.println(json);
}catch(Exception e) {
	out.println("La conexión no se pudo realizar");
	e.printStackTrace();
}
%>
</body>
</html>