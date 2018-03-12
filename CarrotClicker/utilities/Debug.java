package CarrotClicker.utilities;

import CarrotClicker.run;

public class Debug extends run{
  public void log(String message) { // Debug-Meldungen in der Konsole
    if(debugging){
      Time time = new Time();
      System.out.println("[" + time.strTime() + "] " + message);
    }
  }
}
