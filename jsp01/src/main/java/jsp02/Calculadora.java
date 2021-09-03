package jsp02;

public class Calculadora {
	private int resultado;
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
}
