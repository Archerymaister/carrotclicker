package CC;

import java.sql.SQLException;

public class preise {
	public static Database d = new Database();
	
	public static int helfer() throws SQLException{
		int p = 0;
		p = exe.value("helfer");
		return p;
	}
	
	public static int felder() throws SQLException{
		int p = exe.value("felder")*exe.value("felder")*100+(exe.value("felder")+1)*10;
		return p;
	}
}
