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

<%
//En references libraries
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
	while(miResultset.next()) {//mientras haya un registro hacia adelante del cursor nos desplazamos a él y hacemos lo siguiente...
		//Convertimos cada empleado en objeto
		Empleado emp = new Empleado(miResultset.getInt("ID"), miResultset.getString("NOMBRE"), miResultset.getString("APELLIDO"), miResultset.getString("CARGO"), miResultset.getInt("SUPERIOR") );
		dataList.add(emp);
	}
	//Json que contiene todos los empleados en uno sólo json. El List tenía todos los objetos empleados, y parseamos a json ese List
	Gson gson2 = new Gson();
	String json = gson2.toJson(dataList);
	
	//Intento de response
	PrintWriter salida = response.getWriter();
	response.setContentType("application/json");//encabezado del response
	response.setCharacterEncoding("UTF-8");
	salida.print(json);
	salida.flush();
	//Fin intento de response
	
}catch(Exception e) {
	out.println("La conexión no se pudo realizar");
	e.printStackTrace();
}
%>
