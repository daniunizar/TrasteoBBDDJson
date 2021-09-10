<%@ page import="jsp03.Empleado" %>
<%@ page import="jsp03.Configuracion" %>
<%@ page import="jsp03.Conexion" %>
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
<link rel="stylesheet" href="../CSS/styles.css" type="text/css">
<style>
table {
   width: 100%;
   border: 1px solid #000;
}
th, td {
   width: 25%;
   text-align: left;
   vertical-align: top;
   border: 1px solid #000;
   border-collapse: collapse;
   padding: 0.3em;
   caption-side: bottom;
}
caption {
   padding: 0.3em;
}
</style>
</head>
<body>
<%
Class.forName("com.mysql.jdbc.Driver");//En references libraries
Class.forName("com.google.gson.Gson");
Class.forName("oracle.jdbc.OracleDriver");
Class.forName("com.google.gson.GsonBuilder");

try {
	//PASO 1. Crear la conexión a la BBDD
	//Url, usuario y contraseña
	//driver:protocolodriver:/url:puerto/nombreBBDD  usuario  password
	//Creamos objeto de tipo Connection
	 	Configuracion lectura = new Configuracion();
		lectura.getRutaActual();
		lectura.listaDirActual();
		Conexion.Conexion();
		Connection conexion = Conexion.getConnection();
		
	//Connection miConexion = DriverManager.getConnection(detalles, "root", ""); //Creamos objeto de tipo Connection

	//PASO 2. Creamos el objeto de tipo Statement
	Statement miStatement = conexion.createStatement(); //Creamos el objeto de tipo Statement
	
	//PASO 3. Ejecutar sentencia SQL
	String sentenciaSql = "SELECT * FROM JAVA_EMPLEADOS";
			
	ResultSet miResultset = miStatement.executeQuery(sentenciaSql); //Devuelve un Objeto de tipo ResultSet
	//PASO 4. Leer el resulset recorriéndolo
	List<Empleado> dataList = new LinkedList<>();//Lista que contendrá todos los Empleados para el JSON
	out.print("<table>");
	out.print("<tr>");
	out.print("<th>ID</th>");
	out.print("<th>NOMBRE</th>");
	out.print("<th>APELLIDO</th>");
	out.print("<th>CARGO</th>");
	out.print("<th>SUPERIOR</th>");
	out.print("</tr>");
	while(miResultset.next()) {//mientras haya un registro hacia adelante del cursor nos desplazamos a él y hacemos lo siguiente...
		//Imprimimos por pantalla
		//out.print(miResultset.getString("NOMBRE_EMPLEADO")+" ");//RECUPERAMOS A PARTIR DE LOS ALIAS
		//out.print(miResultset.getString("APELLIDO_EMPLEADO")+" ");
		//out.print(miResultset.getString("CARGO_EMPLEADO")+" ");
		//out.print(miResultset.getString("ID_EMPLEADO")+" ");
		//out.print(" >  ");
		//out.print(miResultset.getString("NOMBRE_JEFE")+" ");
		//out.print(miResultset.getString("APELLIDO_JEFE")+" ");
		//out.print(miResultset.getString("ID_JEFE")+" "); //SE EXTRAE DEL SUBORDINADO, NO DEL SUPERIOR
		//out.println(miResultset.getString("CARGO_JEFE")+" ");
		out.print("<tr>");
		out.print("<td>"+miResultset.getString("ID")+"</td>");
		out.print("<td>"+miResultset.getString("NOMBRE")+"</td>");
		out.print("<td>"+miResultset.getString("APELLIDO")+"</td>");
		out.print("<td>"+miResultset.getString("CARGO")+"</td>");
		out.print("<td>"+miResultset.getString("SUPERIOR")+"</td>");
		out.print("</tr>");
		//Convertimos cada empleado en objeto
		Empleado emp = new Empleado(miResultset.getInt("ID_EMPLEADO"), miResultset.getString("NOMBRE_EMPLEADO"), miResultset.getString("APELLIDO_EMPLEADO"), miResultset.getString("CARGO_EMPLEADO"), miResultset.getInt("ID_JEFE") );
		//out.println(emp);
		dataList.add(emp);
		//Formateamos cada objeto empleado a Json con Gson
		//Gson gson = new Gson();
	    //System.out.println(gson.toJson(emp));
		Gson gson = new Gson();
		String representacionJSON = gson.toJson(emp);
		//System.out.println(representacionJSON);
		//bonito
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionBonita = prettyGson.toJson(emp);
		//out.println(representacionBonita);
	}
	out.print("</table>");
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