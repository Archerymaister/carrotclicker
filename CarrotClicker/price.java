package CarrotClicker;

import java.sql.SQLException;

public class price extends run{
  //CPS
  public static int priceHelper() throws SQLException{
    return (int) Math.pow(3.5,User.getHelper()+1)+User.getHelper()*2+25;
  }
  
  public static int priceMachine() throws SQLException{
    return (int) (Math.pow(3.0,User.getMachine())+Math.pow(2.0,User.getMachine())+User.getMachine()*3+100);
  }
  
  //CPC
  public static int priceFields() throws SQLException{
    return (int) Math.pow(2.0,User.getFields())*10+User.getFields()*3+20;
  }
  
  public static int priceDung() throws SQLException{
    return (int) Math.pow(2.0,User.getDung())*10+User.getDung()*7+120;
  }
  
  
}
