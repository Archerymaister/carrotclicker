package CarrotClicker;

import java.awt.*;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.*;

import java.sql.SQLException;

import javax.swing.*;    

import java.util.Timer;
import java.util.TimerTask; 

import CarrotClicker.utilities.Debug;

public class Window extends run {
  private static run Run = new run();
  private static Toolkit t;
  
  public static int width = 600, height = 400;
  
  public static JFrame window = new JFrame();
  public static JPanel JPWindow = new JPanel();
  public static JLabel lblAmount, lblMessage, lblMsgBG, lblFelder, lblHelfer, lblMaschine, lblDung, lblInfos, lblBackground, lblShop;
  public static JButton btnCarrot, btnFelder, btnHelfer, btnMaschine, btnDung, btnLeaderboard, btnCommand;
  
  public static JTextField TFcommand;
  
  public static Debug debug = new Debug();
  public static price p = new price();
  public static Command cmd = new Command();
  
  
  public void setupContent() throws SQLException {
    t = Toolkit.getDefaultToolkit();
    Dimension dim = t.getScreenSize();
    
    //Zentriertes Ausrichten des Fensters auf dem Monitor
    int x = (int) (dim.getWidth() - width) / 2;
    int y = (int) (dim.getHeight() - height) / 2;
    
    // Fenster einrichten
    window.setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("pics/carrot_m.png")));
    window.setBounds(x, y, width+6, height+29);
    window.setTitle("CarrotClicker - " + User.getName());
    window.setResizable(false);
    window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    window.getLayeredPane().setLayer(JPWindow, 2 );
    window.setContentPane(JPWindow);
    JPWindow.setLayout(null);
    window.setVisible(true);
    window.addWindowListener(new WindowAdapter() {
      
      @Override
      public void windowClosing(WindowEvent e) {
        int dialogResult = JOptionPane.showConfirmDialog(window, "Wirklich beenden?", "CarrotClicker",JOptionPane.YES_NO_OPTION);
        
        if(dialogResult == 0){
          try{
            Run.onClose(window);
          }catch(Exception exc){
            exc.printStackTrace();
          }
        }else{
          JOptionPane.showMessageDialog(window, "Richtige Entscheidung!");
        }
      }
    });
    
    //Hintergrund
    lblBackground = new JLabel(new ImageIcon(Window.class.getResource("pics/BG.png")));
    lblBackground.setBounds(0,0,JPWindow.getWidth(),JPWindow.getHeight());
    JPWindow.add(lblBackground);
    
    
    // Hauptknopf
    btnCarrot = new JButton(new ImageIcon(Window.class.getResource("pics/carrot_m.png")));
    btnCarrot.setBackground(SystemColor.menu);
    btnCarrot.setForeground(SystemColor.text);
    btnCarrot.setBounds(80, 140, 150, 150);
    btnCarrot.setBorder(BorderFactory.createEmptyBorder());
    btnCarrot.setContentAreaFilled(false);
    btnCarrot.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        Click();
      }
    });
    JPWindow.add(btnCarrot);
    
    // Anzeige Aktuelle Möhren
    lblAmount = new JLabel(User.getCarrots() + " Möhren");
    lblAmount.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
    lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
    lblAmount.setBounds(40, 73, 250, 46);
    JPWindow.add(lblAmount);
    
    // Anzeige CPC + CPS
    lblInfos = new JLabel("<html><body><center>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</center></body></html>");
    lblInfos.setHorizontalAlignment(SwingConstants.CENTER);
    lblInfos.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
    lblInfos.setBounds(40, 11, 250, 75);
    JPWindow.add(lblInfos);
    
    // Command Promt
    TFcommand = new JTextField();
    TFcommand.setBounds(10, (JPWindow.getHeight()-30), JPWindow.getWidth()-20, 25);
    TFcommand.setBackground(new Color(175,134,85));
    TFcommand.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    TFcommand.addActionListener(new ActionListener(){;
      public void actionPerformed(ActionEvent arg0){
        cmd.process(TFcommand.getText().split(" ")); 
      }
    });
    
    // loads leaderboard
    btnLeaderboard = new JButton(new ImageIcon(Window.class.getResource("pics/Leaderboard.png")));
    btnLeaderboard.setBounds(100,320,40,40);
    btnLeaderboard.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try{
          Leaderboard.show();
        }catch(SQLException SQLE){
          SQLE.printStackTrace();
        }
      }
    });
    JPWindow.add(btnLeaderboard);
    
    btnCommand = new JButton(new ImageIcon(Window.class.getResource("pics/Command.png")));
    btnCommand.setBounds(160,320,40,40);
    btnCommand.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if(TFcommand.getParent() == null){
          JPWindow.add(TFcommand);
          JPWindow.setComponentZOrder(TFcommand, 1);
          debug.log("Kommandozeile geladen");
          JPWindow.repaint();
        }else{
          JPWindow.remove(TFcommand);
          debug.log("Kommandozeile entladen"); 
          JPWindow.repaint();
        }
      }
    });
    JPWindow.add(btnCommand);
    
    //Cookies per Seconds
    Timer timerCPS = new Timer();
    timerCPS.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        User.setCarrots(User.getCarrots() + CPS());
        User.setTotCarrots(User.getTotCarrots() + CPS());
        lblAmount.setText(User.getCarrots() + " Möhren");
      }
    }, 0, 1000);
    
    //Message
    lblMessage = new JLabel();
    lblMessage.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
    lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
    lblMessage.setForeground(new Color(255, 140, 0));
    lblMessage.setBounds((btnCarrot.getWidth() - lblMessage.getWidth()) / 2 + btnCarrot.getX(),btnCarrot.getY(), 250, 30);
    
    //Message BG
    lblMsgBG = new JLabel();
    lblMsgBG.setIcon(new ImageIcon(Window.class.getResource("pics/Message.png")));
    lblMsgBG.setBounds(lblMessage.getX(), lblMessage.getY(), 250, 30);    
    
    JPWindow.repaint();
    
    setupShop();
  }

  public void setupShop() throws SQLException {
    lblShop = new JLabel("Shop");
    lblShop.setBounds(400,20,100,30);
    lblShop.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
    lblShop.setHorizontalAlignment(SwingConstants.CENTER);
    JPWindow.add(lblShop);
    
    //Kaufbutton "Helfer"
    btnHelfer = new JButton(new ImageIcon(Window.class.getResource("pics/Erntehelfer.png")));
    btnHelfer.setBounds(330, 215, 100, 100);
    btnHelfer.setBorder(BorderFactory.createEmptyBorder());
    btnHelfer.setContentAreaFilled(false);
    btnHelfer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try{
          
          if(buy(p.priceHelper())){
            User.setCarrots(User.getCarrots()-p.priceHelper());
            User.setHelper(User.getHelper()+1);
            d.executeUpdate("UPDATE " + d.URL + " SET helfer='" + User.getHelper() + "' WHERE name='" + User.getName() + "';");
            lblHelfer.setText(p.priceHelper()+" Karotten");
            lblAmount.setText(User.getCarrots() + " Möhren");
            lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
            debug.log("Helfer erhöht");
          }else{
            message("Nicht genug Möhren");
          }
        }catch(SQLException SQLE){
          SQLE.printStackTrace();
        }
      }
    });
    JPWindow.add(btnHelfer);
    
    //Preis Helfer
    lblHelfer = new JLabel(p.priceHelper()+" Karotten");
    lblHelfer.setHorizontalAlignment(SwingConstants.CENTER);
    lblHelfer.setBounds(btnHelfer.getX(), btnHelfer.getY()+100, 100, 25);
    JPWindow.add(lblHelfer);
    
    //Kaufbutton "Werkzeuge"
    btnMaschine = new JButton(new ImageIcon(Window.class.getResource("pics/Werkzeuge.png")));
    btnMaschine.setBounds(470, 215, 100, 100);
    btnMaschine.setBorder(BorderFactory.createEmptyBorder());
    btnMaschine.setContentAreaFilled(false);
    btnMaschine.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try{
          
          if(buy(p.priceMachine())){
            User.setCarrots(User.getCarrots()-p.priceMachine());
            User.setMachine(User.getMachine()+1);
            d.executeUpdate("UPDATE " + d.URL + " SET maschine='" + User.getMachine() + "' WHERE name='" + User.getName() + "';");
            lblMaschine.setText(p.priceMachine()+" Karotten");
            lblAmount.setText(User.getCarrots() + " Möhren");
            lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
            debug.log("Maschine erhöht");
          }else{
            message("Nicht genug Möhren");
          }
        }catch(SQLException SQLE){
          SQLE.printStackTrace();
        }
      }
    });
    JPWindow.add(btnMaschine);
    
    //Preis Maschine
    lblMaschine = new JLabel(p.priceMachine()+" Karotten");
    lblMaschine.setHorizontalAlignment(SwingConstants.CENTER);
    lblMaschine.setBounds(btnMaschine.getX(), btnMaschine.getY()+100, 100, 25);
    JPWindow.add(lblMaschine);
    
    //Kaufbutton "Felder"
    btnFelder = new JButton(new ImageIcon(Window.class.getResource("pics/mehrFelder.png")));
    btnFelder.setContentAreaFilled(false);
    btnFelder.setBorder(BorderFactory.createEmptyBorder());
    btnFelder.setBounds(330, 80, 100, 100);
    btnFelder.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        
        try{
          
          if(buy(p.priceFields())){
            User.setCarrots(User.getCarrots()-p.priceFields());
            User.setFields(User.getFields()+1);
            d.executeUpdate("UPDATE " + d.URL + " SET felder='" + User.getFields() + "' WHERE name='" + User.getName() + "';");
            lblFelder.setText(p.priceFields()+" Karotten");
            lblAmount.setText(User.getCarrots() + " Möhren");
            lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
            debug.log("Felder erhöht");
          }else{
            message("Nicht genug Möhren");
          }
        }catch(SQLException SQLE){
          SQLE.printStackTrace();
        }
      }
    });
    JPWindow.add(btnFelder);
    
    //Preis Felder
    lblFelder = new JLabel(p.priceFields()+" Karotten");
    lblFelder.setHorizontalAlignment(SwingConstants.CENTER);
    lblFelder.setBounds(btnFelder.getX(), btnFelder.getY()+100, 100, 25);
    JPWindow.add(lblFelder);
    
    //Kaufbutton "Dünger"
    btnDung = new JButton(new ImageIcon(Window.class.getResource("pics/Dünger.png")));
    btnDung.setContentAreaFilled(false);
    btnDung.setBorder(BorderFactory.createEmptyBorder());
    btnDung.setBounds(470, 80, 100, 100);
    btnDung.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        
        try{
          
          if(buy(p.priceDung())){
            User.setCarrots(User.getCarrots()-p.priceDung());
            User.setDung(User.getDung()+1);
            d.executeUpdate("UPDATE " + d.URL + " SET dung='" + User.getDung() + "' WHERE name='" + User.getName() + "';");
            lblDung.setText(p.priceDung()+" Karotten");
            lblAmount.setText(User.getCarrots() + " Möhren");
            lblInfos.setText("<html><body>Möhren pro Klick: " + CPC() + "<br>Möhren pro sec: " + CPS() + "</body></html>");
            debug.log("Dünger erhöht");
          }else{
            message("Nicht genug Möhren");
          }
        }catch(SQLException SQLE){
          SQLE.printStackTrace();
        }
      }
    });
    JPWindow.add(btnDung);
    
    //Preis Dunger
    lblDung = new JLabel(p.priceDung()+" Karotten");
    lblDung.setHorizontalAlignment(SwingConstants.CENTER);
    lblDung.setBounds(btnDung.getX(), btnDung.getY()+100, 100, 25);
    JPWindow.add(lblDung);
    
    //Hintergrundbild in den Hintergrund legen, den Rest darüber abbilden
    JPWindow.setComponentZOrder(lblBackground, 2);
    JPWindow.setComponentZOrder(lblShop, 1);
    JPWindow.setComponentZOrder(lblAmount, 1);
    JPWindow.setComponentZOrder(lblInfos, 1);
    JPWindow.setComponentZOrder(lblFelder, 1);
    JPWindow.setComponentZOrder(lblHelfer, 1);
    JPWindow.setComponentZOrder(btnCarrot, 1);
    JPWindow.setComponentZOrder(btnFelder, 1);
    JPWindow.setComponentZOrder(btnHelfer, 1);
    JPWindow.setComponentZOrder(btnMaschine, 1);
    JPWindow.setComponentZOrder(lblMaschine, 1);
    JPWindow.setComponentZOrder(btnDung, 1);
    JPWindow.setComponentZOrder(lblDung, 1);
    JPWindow.setComponentZOrder(btnLeaderboard, 1);
    JPWindow.setComponentZOrder(btnCommand, 1);
    
    JPWindow.repaint();    
  }
  private static boolean buy(int itemPrice){
    return User.getCarrots() >= itemPrice;
  }
    
  public static int CPC(){
    int i = 1 + User.getFields() + User.getDung()*2;
    return i;
  }
    
  public static int CPS(){
    return User.getHelper()+User.getMachine()*2;
  }
    
  public static int i;
  public static Timer timerMessage;
  public static boolean active = false;

  public static void message(String message) {
    if (!active) {
      active = true;
    }else{
      timerMessage.cancel();
    }
    
    JPWindow.add(lblMsgBG);
    JPWindow.add(lblMessage);
    JPWindow.setComponentZOrder(lblMessage, 1);
    JPWindow.setComponentZOrder(lblMsgBG, 2);
    JPWindow.repaint();
    
    lblMessage.setText(message);
    i = (int) btnCarrot.getY();
    
    timerMessage = new Timer();
    timerMessage.scheduleAtFixedRate(new TimerTask() {
      
      @Override
      public void run() {
        
        i = i - 2;
        lblMessage.setBounds((btnCarrot.getWidth() - lblMessage.getWidth()) / 2 + btnCarrot.getX(), i, 250, 30);
        lblMsgBG.setBounds(lblMessage.getX(), lblMessage.getY(), 250, 30);
        if (i < (int) btnCarrot.getY() - 100) {
          JPWindow.remove(lblMessage);
          JPWindow.remove(lblMsgBG);
          JPWindow.repaint();
          active = false;
          timerMessage.cancel();
        }
      }
    }, 0, 30);
  }  
}
  
