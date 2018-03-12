package CarrotClicker;

import javax.swing.JOptionPane;

import java.sql.SQLException;

public class Command extends Window{  
  
  public static void process(String[] command){
    String strCommand = "";
    
    try{
      switch (command[0].toLowerCase()) {
        //Command "Carrot"
        case "carrot" :
        case "carrots" : 
          switch (command[1].toLowerCase()) {
            //Sub-Command "give"
            case "give" :
            case "add" :
            case "+" :
              User.setCarrots(User.getCarrots()+Integer.parseInt(command[2]));
              Window.lblAmount.setText(User.getCarrots() + " Möhren");
              break;
              //Sub-Command "take"
            case "take" :
            case "del" :
            case "-" : 
              User.setCarrots(User.getCarrots()-Integer.parseInt(command[2]));
              Window.lblAmount.setText(User.getCarrots() + " Möhren");
              break;
              //Sub-Command "set"
            case "set" :
            case "=" :
              User.setCarrots(Integer.parseInt(command[2]));
              Window.lblAmount.setText(User.getCarrots() + " Möhren");
              break;
            default: 
              JOptionPane.showMessageDialog(Window.window, "Befehl: carrot [give/take/set] [Menge]");
          } // end of switch
          break;
          //Command "item"
        case "item" :
        case "items" :
          switch (command[1].toLowerCase()) {
            //Sub-Command "Helfer"
            case "helfer" :
            case "helper" :
              switch (command[2].toLowerCase()) {
                //Sub-Command "give"
                case "give" :
                case "add" :
                case "+" :
                  User.setHelper(User.getHelper()+Integer.parseInt(command[3]));
                  d.executeUpdate("UPDATE " + d.URL + " SET helfer='" + User.getHelper() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblHelfer.setText(p.priceHelper()+" Karotten");
                  break;
                  //Sub-Command "take"
                case "take" :
                case "del" : 
                case "-" :
                  if(User.getHelper()-Integer.parseInt(command[3]) <= 0){
                    User.setHelper(0);
                  }else{  
                    User.setHelper(User.getHelper()-Integer.parseInt(command[3]));
                  }  
                  d.executeUpdate("UPDATE " + d.URL + " SET helfer='" + User.getHelper() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblHelfer.setText(p.priceHelper()+" Karotten");
                  break;
                  //Sub-Command "set"
                case "set" :
                case "=" :
                  if(Integer.parseInt(command[3]) < 0){
                    User.setHelper(0);
                  }else{  
                    User.setHelper(Integer.parseInt(command[3]));
                  }  
                  d.executeUpdate("UPDATE " + d.URL + " SET helfer='" + User.getHelper() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblHelfer.setText(p.priceHelper()+" Karotten");
                  break;
                default: 
                  JOptionPane.showMessageDialog(Window.window, "Befehl: item Helfer [give/take/set] [Menge]");
              } // end of switch
              break;
              //Sub-Command Felder
            case "felder" :
            case "fields" :
              switch (command[2].toLowerCase()) {
                //Sub-Command "give"
                case "give" :
                case "add" :
                case "+" :
                  User.setFields(User.getFields()+Integer.parseInt(command[3]));
                  d.executeUpdate("UPDATE " + d.URL + " SET felder='" + User.getFields() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblFelder.setText(p.priceFields()+" Karotten");
                  break;
                  //Sub-Command "take"
                case "take" :
                case "del" : 
                case "-" :
                  if(User.getFields()-Integer.parseInt(command[3]) <= 0){
                    User.setFields(0);
                  }else{  
                    User.setFields(User.getFields()-Integer.parseInt(command[3]));
                  }  
                  d.executeUpdate("UPDATE " + d.URL + " SET felder='" + User.getFields() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblFelder.setText(p.priceFields()+" Karotten");
                  break;
                  //Sub-Command "set"
                case "set" :
                case "=" :
                  if(Integer.parseInt(command[3]) < 0){
                    User.setFields(0);
                  }else{  
                    User.setFields(Integer.parseInt(command[3]));
                  }  
                  d.executeUpdate("UPDATE " + d.URL + " SET felder='" + User.getFields() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblFelder.setText(p.priceFields()+" Karotten");
                  break;
                default: 
                  JOptionPane.showMessageDialog(Window.window, "Befehl: item Felder [give/take/set] [Menge]");
              }  
            case "werkzeuge" :
            case "tools":
              switch (command[2].toLowerCase()) {
                //Sub-Command "give"
                case "give" :
                case "add" :
                case "+" :
                  User.setMachine(User.getMachine()+Integer.parseInt(command[3]));
                  d.executeUpdate("UPDATE " + d.URL + " SET maschine='" + User.getMachine() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblMaschine.setText(p.priceMachine()+" Karotten");
                  break;
                  //Sub-Command "take"
                case "take" :
                case "del" : 
                case "-" :
                  if(User.getMachine()-Integer.parseInt(command[3]) <= 0){
                    User.setMachine(0);
                  }else{  
                    User.setMachine(User.getMachine()-Integer.parseInt(command[3]));
                  }  
                  d.executeUpdate("UPDATE " + d.URL + " SET maschine='" + User.getMachine() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblMaschine.setText(p.priceMachine()+" Karotten");
                  break;
                  //Sub-Command "set"
                case "set" :
                case "=" :
                  if(Integer.parseInt(command[3]) < 0){
                    User.setMachine(0);
                  }else{  
                    User.setMachine(Integer.parseInt(command[3]));
                  }  
                  d.executeUpdate("UPDATE " + d.URL + " SET maschine='" + User.getMachine() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblMaschine.setText(p.priceMachine()+" Karotten");
                  break;
                default: 
                  JOptionPane.showMessageDialog(Window.window, "Befehl: item Maschine [give/take/set] [Menge]");
              } // end of switch
            case "dung" :
            case "dünger" :
            case "dunger" :
              switch (command[2].toLowerCase()) {
                //Sub-Command "give"
                case "give" :
                case "add" :
                case "+" :
                  User.setDung(User.getDung()+Integer.parseInt(command[3]));
                  d.executeUpdate("UPDATE " + d.URL + " SET dung='" + User.getDung() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblDung.setText(p.priceDung()+" Karotten");
                  break;
                  //Sub-Command "take"
                case "take" :
                case "del" : 
                case "-" :
                  if(User.getDung()-Integer.parseInt(command[3]) <= 0){
                    User.setDung(0);
                  }else{  
                    User.setDung(User.getDung()-Integer.parseInt(command[3]));
                  }  
                  d.executeUpdate("UPDATE " + d.URL + " SET dung='" + User.getDung() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblDung.setText(p.priceDung()+" Karotten");
                  break;
                  //Sub-Command "set"
                case "set" :
                case "=" :
                  if(Integer.parseInt(command[3]) < 0){
                    User.setDung(0);
                  }else{  
                    User.setDung(Integer.parseInt(command[3]));
                  }  
                  d.executeUpdate("UPDATE " + d.URL + " SET dung='" + User.getDung() + "' WHERE name='" + User.getName() + "';");
                  lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                  lblDung.setText(p.priceDung()+" Karotten");
                  break;
                default: 
                  JOptionPane.showMessageDialog(Window.window, "Befehl: item Dünger [give/take/set] [Menge]");
              } // end of switch
              break; 
              //Sub-Command "List"
            case "list" :
            case "show" :
              JOptionPane.showMessageDialog(Window.window, "Verfügbare Items: \r\n - Felder\r\n - Helfer\r\n - Maschine\r\n - Dünger\r\n");
              break;
            default: 
              JOptionPane.showMessageDialog(Window.window, "Befehl: item [Itemname] [give/take/set] [Menge]");
          } // end of switch
          break;
          //Command "Reset"
        case "reset" :
          switch (command[1].toLowerCase()) {
            case "player" :
            case "user" :
              switch (command[2].toLowerCase()) {
                case "current" :
                case "active" :
                case "aktiv" :
                  int dialogResult = JOptionPane.showConfirmDialog(window, "Wirklich löschen?", "CarrotClicker",JOptionPane.YES_NO_OPTION);
                  
                  if(dialogResult == 0){
                    try{
                      User.reset();
                      
                      lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
                      lblFelder.setText(p.priceFields()+" Karotten");
                      lblHelfer.setText(p.priceHelper()+" Karotten");
                      lblMaschine.setText(p.priceMachine()+" Karotten");
                      lblDung.setText(p.priceDung()+" Karotten");
                      
                      
                      debug.log("Alle Werte des Spielers " + User.getName() + " wurden gelöscht");
                    }catch(Exception exc){
                      exc.printStackTrace();
                    }
                  }            
                  break;
                case "name" : 
                  int resetUser = JOptionPane.showConfirmDialog(window, "Wirklich alle Werte von " + command[3] + " löschen?", "CarrotClicker",JOptionPane.YES_NO_OPTION);
                  
                  if(resetUser == 0){
                    try{
                      d.executeUpdate("UPDATE " + d.URL + " SET carrots='" + User.getCarrots() + "' WHERE name='" + command[3] + "';");
                      d.executeUpdate("UPDATE " + d.URL + " SET felder='" + User.getFields() + "' WHERE name='" + command[3] + "';");
                      d.executeUpdate("UPDATE " + d.URL + " SET helfer='" + User.getHelper() + "' WHERE name='" + command[3] + "';");
                      d.executeUpdate("UPDATE " + d.URL + " SET dung='" + User.getDung() + "' WHERE name='" + command[3] + "';");
                      d.executeUpdate("UPDATE " + d.URL + " SET maschine='" + User.getMachine() + "' WHERE name='" + command[3] + "';");
                      
                      debug.log("Alle Werte des Spielers " + command[3] + " wurden gelöscht!");
                    }catch(SQLException SQLE){
                      SQLE.printStackTrace();
                    }
                  }
                  break;
                default:  
                  JOptionPane.showMessageDialog(Window.window, "Befehl: reset player [current/name] <Spielername>");
              } // end of switch
              
              break;
            case "database" : 
              int resetDB = JOptionPane.showConfirmDialog(window, "Wirklich alle Werte aus der Datenbank löschen?", "CarrotClicker",JOptionPane.YES_NO_OPTION);
              
              if(resetDB == 0){
                try{
                  d.executeUpdate("UPDATE " + d.URL + " SET carrots='0';");
                  d.executeUpdate("UPDATE " + d.URL + " SET totCarrots='0';");
                  d.executeUpdate("UPDATE " + d.URL + " SET felder='0';");
                  d.executeUpdate("UPDATE " + d.URL + " SET dung='0';");
                  d.executeUpdate("UPDATE " + d.URL + " SET helfer='0';");
                  
                  debug.log("Alle Werte aus der Datenbank wurden gelöscht!");
                }catch(Exception SQLE){
                  SQLE.printStackTrace();
                }
              }
              break;
            default: 
              
          } // end of switch
          break;
          //Command "Remove"    
        case "remove" :
        case "delete" :
          if(command[1].equals(User.getName())){
            JOptionPane.showMessageDialog(Window.window, "Der aktive Nutzer kann nicht gelöscht werden!");
          }else{
            int removeUser = JOptionPane.showConfirmDialog(window, "Wirklich den Account von " + command[1] + " löschen?", "CarrotClicker",JOptionPane.YES_NO_OPTION);
            
            if(removeUser == 0){
              try{
                d.executeUpdate("DELETE FROM " + d.URL + " WHERE name = '" + command[1] + "';");
                
                debug.log("Spieler " + command[1] + " wurde ausgelöscht!");
              }catch(SQLException SQLE){
                SQLE.printStackTrace();
              }
            }else{
              debug.log("Spieler  " + command[1] + " wurde nicht ausgelöscht!");
            }
          }
          break;
          //Command "Save"
        case "save" :
          d.executeUpdate("UPDATE " + d.URL + " SET carrots='" + User.getCarrots() + "' WHERE name='" + User.getName() + "';");
          d.executeUpdate("UPDATE " + d.URL + " SET totCarrots='" + User.getTotCarrots() + "' WHERE name='" + User.getName() + "';");
          d.executeUpdate("UPDATE " + d.URL + " SET helfer='" + User.getHelper() + "' WHERE name='" + User.getName() + "';");
          d.executeUpdate("UPDATE " + d.URL + " SET felder='" + User.getFields() + "' WHERE name='" + User.getName() + "';");
          d.executeUpdate("UPDATE " + d.URL + " SET dung='" + User.getDung() + "' WHERE name='" + User.getName() + "';");
          d.executeUpdate("UPDATE " + d.URL + " SET maschine='" + User.getMachine() + "' WHERE name='" + User.getName() + "';");
          break;
          //Command "Reload"
        case "reload" :
          User.init();
          break;
          //Command "Help"
        case "help":
        case "?":
          JOptionPane.showMessageDialog(Window.window, "Verfügbare Befehle:\r\n"
          +"carrot [give/take/set] [Menge]\r\n"
          +"item [List/Itemname] [give/take/set] [Menge]\r\n"
          +"reload\r\n"
          +"save\r\n"
          +"reset [Player/Database] [Current/Name] <Spielername>\r\n"
          +"remove [Spielername]\r\n");
          break;
        default: 
          JOptionPane.showMessageDialog(Window.window, "Unbekannter Befehl!");
      } // end of switch
      for (String s : command ) {
        strCommand = strCommand + " " + s;
      } // end of for
      TFcommand.setText("");
      debug.log(User.getName() + " hat einen Befehl ausgeführt:" + strCommand);
    }catch(SQLException SQLE){
      SQLE.printStackTrace();
    }catch(ArrayIndexOutOfBoundsException AIOOBE){
      JOptionPane.showMessageDialog(window, "Der Befehl " + command[0] + " benötigt mehr Argumente!");
    }catch(NumberFormatException NFE){
      JOptionPane.showMessageDialog(window, "Eingabe hat falsches Format!");
    }
  }
}
