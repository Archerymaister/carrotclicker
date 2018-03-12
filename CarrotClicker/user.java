package CarrotClicker;

import java.sql.SQLException;

public class user extends run{
  public String userName;
  public int itemFields, carrots, totCarrots, itemHelper, itemMachine, itemDung;
  
  private Database d = new Database();
  
  public user(){
    userName = null;
    carrots = 0;
    totCarrots = 0;
    itemFields = 0;
    itemHelper = 0;
    itemMachine = 0;
    itemDung = 0;    
  }
  
  public String getName(){
    return userName;
  }
  
  public void setName(String newName){
    userName = newName;
  }
  
  public int getCarrots(){
    return carrots;
  }
  
  public void setCarrots(int newCarrots){
    carrots = newCarrots;
  }
  
  public int getTotCarrots(){
    return totCarrots;
  }
  
  public void setTotCarrots(int newTotCarrots){
    totCarrots = newTotCarrots;
  }
  
  public int getFields(){
    return itemFields;
  }
  
  public void setFields(int newFields){
    itemFields = newFields;
  }
  
  public int getHelper(){
    return itemHelper;
  }
  
  public void setHelper(int newHelper){
    itemHelper = newHelper;
  }
  
  public int getMachine(){
    return itemMachine;
  }
  
  public void setMachine(int newMachine){
    itemMachine = newMachine;
  } 
  
  public int getDung(){
    return itemDung;
  }
  
  public void setDung(int newDung){
    itemDung = newDung;
  } 
  
  public void init() throws SQLException{
    carrots = d.value("carrots");
    totCarrots = d.value("totCarrots");
    itemFields = d.value("felder");
    itemHelper = d.value("helfer");
    itemDung = d.value("dung");
    itemMachine = d.value("maschine");
  }    
  
  public void reset(){
    carrots = 0;
    totCarrots = 0;
    itemFields = 0; 
    itemHelper = 0;
    itemDung = 0;
    itemMachine = 0;
    
    try{
      d.executeUpdate("UPDATE " + d.URL + " SET carrots='" + User.getCarrots() + "' WHERE name='" + User.getName() + "';");
      d.executeUpdate("UPDATE " + d.URL + " SET totCarrots='" + User.getTotCarrots() + "' WHERE name='" + User.getName() + "';");
      d.executeUpdate("UPDATE " + d.URL + " SET felder='" + User.getFields() + "' WHERE name='" + User.getName() + "';");
      d.executeUpdate("UPDATE " + d.URL + " SET helfer='" + User.getHelper() + "' WHERE name='" + User.getName() + "';");
      d.executeUpdate("UPDATE " + d.URL + " SET dung='" + User.getDung() + "' WHERE name='" + User.getName() + "';");
      d.executeUpdate("UPDATE " + d.URL + " SET maschine='" + User.getMachine() + "' WHERE name='" + User.getName() + "';");
    }catch(SQLException SQLE){
      SQLE.printStackTrace();
    }
  }
}
