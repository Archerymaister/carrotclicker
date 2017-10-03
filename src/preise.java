package CC;

import java.sql.SQLException;

public class preise {
	public static Database d = new Database();
	
	public static int helfer() throws SQLException{
		int p = (int) Math.pow(2.5,start.value("helfer"))+start.value("helfer")*2;
		return p;
	}
	
	public static int felder() throws SQLException{
		int p = start.value("felder")*start.value("felder")*100+(start.value("felder")+1)*10*(int) Math.pow(2.5, Database.value("felder"));
		return p;
	}
}
