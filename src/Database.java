package CC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database extends start {
	public String URL;
	public Connection con;
	public Statement s;
	
	public void connect() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost/users","root", "");
		s = con.createStatement();
		
	}
	
	
	public void execute(String command)throws SQLException{
		connect();
		s.execute(command);
		con.close();
	}
	
	public void executeUpdate(String command) throws SQLException{
		connect();
		s.executeUpdate(command);
		con.close();
	}
	
	public ResultSet executeQuarry(String command) throws SQLException{
		
		con.prepareStatement(command);
		ResultSet rs = s.executeQuery(command);
		return rs;
	}
	
	public void create() throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://localhost/","root", "");
		s = con.createStatement();
		s.execute("CREATE database if not exists " + URL);
		s.execute("use " + URL);
		s.execute("create table if not exists " + URL +"(UNr int not null auto_increment, name varchar(15) not null, password varchar(25) not null, carrots int default 0, helfer int default 0, felder int default 0, PRIMARY KEY(UNr))");
		con.close();
	}
}
