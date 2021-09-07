import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		File ruta = new File("src/acceso.txt");//partimos desde carpeta proyecto
		FileInputStream fis = new FileInputStream(ruta);
		int b;
		while((b=fis.read())!=-1) {
			System.out.print((char)b);
		}
		fis.close();
	}

}
