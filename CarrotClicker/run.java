package CarrotClicker;

import CarrotClicker.utilities.Debug;
import CarrotClicker.utilities.Time;

import java.sql.ResultSet;
import java.sql.SQLException;



import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class run {
  // Anfang Attribute
  public static boolean debugging = true, boolLogin = true;
  //public static Timer timerCPS;
  
  public String Player;
  public static Database d = new Database();
  public static Debug debug = new Debug();
  public static login LogIn = new login();
  public static Window window = new Window();
  public static leaderboard Leaderboard = new leaderboard();
  public static user User;
  // Ende Attribute
  
  // Anfang Methoden
  // Beim Programmstart ausgeführt
  public void onStart() throws SQLException { 
    debug.log("Gestartet");
    
    try {
      d.create();
    } catch (SQLException SQLE) {
      debug.log(""+SQLE);
      SQLE.printStackTrace();
      
    }catch (UnsupportedClassVersionError UCVE){
      UCVE.printStackTrace();
    }
    
    if(boolLogin){     
      LogIn.setupContent();
    }else{
      User = new user();
      User.setName("Admin");
      User.init();
      
      window.setupContent();
      Leaderboard.setUp();
      
      debug.log("Login umgangen!");
    }
  }
  
  // Beim Programmende ausgeführt
  public void onClose (JFrame window) throws SQLException {     
    d.executeUpdate("UPDATE " + d.URL + " SET carrots='" + User.getCarrots() + "' WHERE name='" + User.getName() + "';");
    d.executeUpdate("UPDATE " + d.URL + " SET totCarrots='" + User.getTotCarrots() + "' WHERE name='" + User.getName() + "';");
    debug.log("Spiel beendet");
    
    
    System.exit(0);
    window.setVisible(false);
    window.dispose();
  }
  
  public static void main(String[] args) {
    run Run = new run();
    try {           
      Run.onStart();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void Click(){
    User.setCarrots(User.getCarrots() + window.CPC());
    User.setTotCarrots(User.getTotCarrots() + window.CPC());
    window.lblAmount.setText(User.getCarrots() + " Möhren");
  }
  // Ende Methoden
}
