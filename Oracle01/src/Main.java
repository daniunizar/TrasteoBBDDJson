 import java.sql.*;
 import oracle.jdbc.pool.OracleDataSource;
public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//intento con tnsnames extraídos de documento .ora
		System.setProperty("Oracle.net.tns_admin", "src/tnsnames.ora");
		String dburl = "jdbc:oracle:thin:@DESAPP1";
		//fin intento con tnsnames
		OracleDataSource ods = new OracleDataSource();
		ods.setUser("JFERNANDEZC");
	    ods.setPassword("vS91Zfg$");
	    //String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=biv-pdlegacy-01.aragon.local)(PORT=1523))(CONNECT_DATA=(SERVER=dedicated)(SERVICE_NAME=desapp1.dga.es)))";
	    ods.setURL(dburl);
	    //<host>:<port>/<service_name>
	    //ods.setURL("biv-pdlegacy-01.aragon.local:1523/desapp1.dga.es");
	    
	    Connection conn = ods.getConnection();
	}

}
