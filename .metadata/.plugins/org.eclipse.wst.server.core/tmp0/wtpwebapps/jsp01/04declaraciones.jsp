<html>
<body>
<h1>Declaraciones JSP</h1>
<%!
	private String nombre = "Dani";
	private int edad = 15;
	int resultado;
	public int metodoSuma(int num1, int num2){
		resultado = num1 + num2;
		return resultado;
	}

	public int metodoResta(int num1, int num2){
		resultado = num1 - num2;
		return resultado;
	}
	
	public int metodoMultiplica(int num1, int num2){
		resultado = num1 * num2;
		return resultado;
	}
%>

SUMA: <%= metodoSuma(5, 3)%>
RESTA: <%= metodoResta(5, 3)%>
MULTIPLICACIÓN: <%= metodoMultiplica(5, 3)%>
</body>
</html>