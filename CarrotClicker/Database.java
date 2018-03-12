package CarrotClicker;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Driver;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import javax.swing.JOptionPane;

public class Database extends run {
  public String URL = "users";
  public Connection con;
  public Statement s;
  
  public void connect() throws SQLException{
    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + URL , "root", "");
    s = con.createStatement();
  }
  
  public void execute(String command) throws SQLException { // Ausführen eines  SQL-Befehls
    connect();
    s.execute(command);
  }
  
  public void executeUpdate(String command) throws SQLException { // Ausführen einer DB-Änderung  
    connect();
    s.executeUpdate(command);
  }
  
  public ResultSet executeQuarry(String command) throws SQLException { // Anfordern von Daten aus der DB
    connect();
    con.prepareStatement(command);
    ResultSet rs = s.executeQuery(command);
    return rs;
  }
  
  public void create() throws SQLException { // Bei Programmstart ausgeführt, erstellt DB, falls nicht vorhanden  
    boolean found = true;
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + URL + "" , "root", "");
      s = con.createStatement();
    }catch(MySQLSyntaxErrorException SQLE){
      //SQLE.printStackTrace();
      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" , "root", "");
      s = con.createStatement();
      s.execute("CREATE database if not exists " + URL + ";");
      s.execute("use " + URL + ";");              
      s.execute("create table if not exists " + URL + "(" 
      + " UNr int not null auto_increment,"
      + " name varchar(15) not null," 
      + " password varchar(25) not null," 
      + " carrots int default 0,"
      + " totCarrots int default 0,"
      + " helfer int default 0," 
      + " felder int default 0," 
      + " dung int default 0,"
      + " maschine int default 0,"
      + " PRIMARY KEY(UNr));");
      
      s.execute("INSERT INTO " + URL + "(name,password) values('Admin','0000')");
      debug.log("Datenbank erstellt!");
      found = false;
    }catch(ClassNotFoundException CNFE){
      System.out.println(CNFE);
    }catch(CommunicationsException CE){
      debug.log("Datenbank-Server offline!");
      JOptionPane.showMessageDialog(null, "Bitte Datenbank-Server starten!");
      System.exit(0);
    }
    if(found)
      debug.log("Datenbank gefunden: '" + URL + "'");
  }
  
  public int value(String field) throws SQLException { // bestimmten Wert abfragen
    Database d = new Database();
    int ret = 0;
    
    ResultSet rs = d.executeQuarry("select name," + field + " from users");
    while (rs.next()) {
      if (rs.getString("name").equals(User.userName)) {
        return rs.getInt(field);
      }
    }
    return 0;
  }
  
  public String Highscore() throws SQLException{
    String str = "<html><body><center>";
    String[] name = new String[]{"","","","",""};
    int iTemp = 0, UNR = 0;
    int[] temp = new int[5], carrots = new int[5];
    ResultSet rs = d.executeQuarry("select UNR,totCarrots,name from users");
    for(int i = 0; i < 5;i++){
      while (rs.next()) {
        if (rs.getInt("totCarrots") > carrots[i] && !contains(name,rs.getString("name"))) {
          carrots[i] = rs.getInt("totCarrots");
          UNR = rs.getInt("UNR");
          name[i] = rs.getString("name");
        }
      }
      if(!name[i].equals("")){
        rs.first();
        temp[i] = UNR;
        str = str + (name[i] + "..................................." + carrots[i] + "<br>");
      }
    }
    return str + "</center></body></html>";
  }
  
  private boolean contains(String[] array, String part){
    int i = 0;
    
    for (String s : array){
      if(array[i].equals(part)){
        return true;
      }
      i++;
    } // end of for
    return false;
  }
}
    
