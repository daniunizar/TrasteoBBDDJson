package pruebaConexionBBDD01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Lectura {
	private String url;
	
	public Lectura() {
		super();
		
	}

	public String lee(String url) {
		String resultado = "";
		try{
			File ruta = new File(url);
			FileInputStream fis = new FileInputStream(ruta);
			int b;
			while((b=fis.read())!=-1) {
				resultado += (char)b;
			}
			fis.close();
		}catch(IOException e){
			System.out.println("Error al leer fichero de detalles de conexión");
		}
		return resultado;
	}	
	
	public void listaDirActual() {
		String sCarpAct = System.getProperty("user.dir");
		File carpeta = new File(sCarpAct);
		String[] listado = carpeta.list();
		if (listado == null || listado.length == 0) {
		    System.out.println("No hay elementos dentro de la carpeta actual");
		    return;
		}
		else {
		    for (int i=0; i< listado.length; i++) {
		        System.out.println(listado[i]);
		    }
		}
	}
}
