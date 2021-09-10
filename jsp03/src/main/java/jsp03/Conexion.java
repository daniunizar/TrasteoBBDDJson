package jsp03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    private static Connection conexion;
    private static Configuracion configuracion = new Configuracion();
    private static String datos_config = configuracion.getData("configRemoto.conf");
    
    //CONEXI�N DESDE UN S�LO FICHERO
    private static String datos_configuracion[] = datos_config.split(" ");
    //private static final String url = datos_configuracion[0]; //url que conecta a la base de datos de la m�quina virtual
    //private static final String usuario = datos_configuracion[1];
    //private static final String clave = (datos_configuracion.length==3)?datos_configuracion[2]:"";
    private static final String url = "jdbc:oracle:thin:@13.36.189.88:1521/xe";
    private static final String usuario = "dmunoz";
    private static final String clave = "cCSYp9ktoDtm";

    /**
     * M�todo que establece conexi�n con la base de datos.
     */
    public static void Conexion() {
        conexion = null;
        try {
            conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexi�n con la base de datos establecida");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al conectar con la base de datos.");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la conexi�n");
        }
    }

    /**
     * M�todo que recupera la informaci�n de la conexi�n para vincularla a un objeto Connect de la clase que la solicita.
     * @return Devuelve la conexi�n actual.
     */
    public static Connection getConnection() {
        return conexion;
    }
    
    
    /**
     * M�todo que pone fin a la conexi�n actual con la Base de Datos.
     */
    public static void desconexionBaseDatos() {
        try {
            conexion.close();
            System.out.println("Se ha cerrado la conexi�n con la base de datos DESDE SU PROPIA CLASE");

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al desconectar de la base de datos");
        }
    }
    

}
