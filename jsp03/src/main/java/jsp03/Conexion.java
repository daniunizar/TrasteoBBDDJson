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
    
    //CONEXIÓN DESDE UN SÓLO FICHERO
    private static String datos_configuracion[] = datos_config.split(" ");
    //private static final String url = datos_configuracion[0]; //url que conecta a la base de datos de la máquina virtual
    //private static final String usuario = datos_configuracion[1];
    //private static final String clave = (datos_configuracion.length==3)?datos_configuracion[2]:"";
    private static final String url = "jdbc:oracle:thin:@13.36.189.88:1521/xe";
    private static final String usuario = "dmunoz";
    private static final String clave = "cCSYp9ktoDtm";

    /**
     * Método que establece conexión con la base de datos.
     */
    public static void Conexion() {
        conexion = null;
        try {
            conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión con la base de datos establecida");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al conectar con la base de datos.");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la conexión");
        }
    }

    /**
     * Método que recupera la información de la conexión para vincularla a un objeto Connect de la clase que la solicita.
     * @return Devuelve la conexión actual.
     */
    public static Connection getConnection() {
        return conexion;
    }
    
    
    /**
     * Método que pone fin a la conexión actual con la Base de Datos.
     */
    public static void desconexionBaseDatos() {
        try {
            conexion.close();
            System.out.println("Se ha cerrado la conexión con la base de datos DESDE SU PROPIA CLASE");

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al desconectar de la base de datos");
        }
    }
    

}
