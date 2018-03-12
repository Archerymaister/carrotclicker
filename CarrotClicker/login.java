package CarrotClicker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Scanner;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class login extends run{
  
  public static JFrame logInWindow;
  public static JPanel JPLogin;
  public static JTextField textField;
  public static JPasswordField passwordField;
  public static JLabel lblBenutzername,lblLogIn,lblPasswort;
  public static JButton btnLogIn,btnRegistrieren;
  
  public static JPanel JPReg;
  public static JLabel lblRegistrieren, lblRegName, lblRegPass, lblRegPassRepeat;
  public static JTextField tfRegName, tfRegPass, tfRegPassRepeat;
  public static JButton btnRegOk, btnRegBack;
  
  public static JLabel lblLoginBG, lblRegBG;
  
  private static Toolkit t;
  
  private static int width = 500, height = 400;
  
  public static void setupContent(){
    t = Toolkit.getDefaultToolkit();
    Dimension dm = t.getScreenSize();
    
    //Zentriertes Ausrichten des Fensters auf dem Monitor
    int x = (int) (dm.getWidth() - width) / 2;
    int y = (int) (dm.getHeight() - height) / 2;
    
    
    logInWindow = new JFrame();
    JPLogin = new JPanel();
    logInWindow.getLayeredPane().setLayer(JPLogin, 2 );
    logInWindow.setContentPane(JPLogin);
    logInWindow.getContentPane().setLayout(null);
    logInWindow.setIconImage(t.getDefaultToolkit().getImage(Window.class.getResource("pics/carrot_m.png")));
    logInWindow.setTitle("CarrotClicker - Anmeldung");
    logInWindow.setResizable(false);
    logInWindow.setBounds(x, y, width, height);
    logInWindow.setVisible(true);
    logInWindow.setDefaultCloseOperation(logInWindow.EXIT_ON_CLOSE);
    
    lblLoginBG = new JLabel(new ImageIcon(login.class.getResource("pics/BGLogin.png")));
    lblLoginBG.setBounds(0,0,500,400);
    JPLogin.add(lblLoginBG);
       
    lblBenutzername = new JLabel("Benutzername");
    lblBenutzername.setHorizontalAlignment(SwingConstants.RIGHT);
    lblBenutzername.setFont(new Font("Tahoma", Font.PLAIN, 18));
    lblBenutzername.setBounds(center(120)-75, 86, 120, 24);
    JPLogin.add(lblBenutzername);
    
    lblLogIn = new JLabel("LogIn");
    lblLogIn.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
    lblLogIn.setBounds(center(150), 24, 150, 29);
    JPLogin.add(lblLogIn);
    
    textField = new JTextField();
    textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
    textField.setBounds(center(154)+75, 89, 154, 24); 
    textField.setColumns(10);
    JPLogin.add(textField);
    
    lblPasswort = new JLabel("Passwort");
    lblPasswort.setHorizontalAlignment(SwingConstants.RIGHT);
    lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 18));
    lblPasswort.setBounds(center(120)-75, 144, 120, 24);
    JPLogin.add(lblPasswort);
    
    ActionListener ALLogIn = new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        String pass = "";
        for(int i = 0; i<passwordField.getPassword().length;i++){
          pass = pass+ passwordField.getPassword()[i];
        }
        LogIn.LogIn(textField.getText(),pass);
      }
    };
    
    passwordField = new JPasswordField();
    passwordField.setBounds(center(154)+75, 144, 154, 25);
    passwordField.addActionListener(ALLogIn);
    JPLogin.add(passwordField);
    
    btnLogIn = new JButton(new ImageIcon(login.class.getResource("pics/Anmelden.png")));
    btnLogIn.addActionListener(ALLogIn);
    btnLogIn.setBounds(center(120)+75, 204, 120, 37);
    JPLogin.add(btnLogIn);
    
    btnRegistrieren = new JButton(new ImageIcon(login.class.getResource("pics/Registrieren.png")));
    btnRegistrieren.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        
        logInWindow.setContentPane(JPReg);
        JPReg.revalidate();
        JPReg.repaint();
      }
    });
    btnRegistrieren.setBounds(center(120)-75, 204, 120, 37);
    JPLogin.add(btnRegistrieren);
    
    setUpReg();
    JPLogin.setComponentZOrder(lblLoginBG, 2);
    JPLogin.setComponentZOrder(lblBenutzername, 1);
    JPLogin.setComponentZOrder(lblLogIn, 1);
    JPLogin.setComponentZOrder(lblPasswort, 1);
    JPLogin.setComponentZOrder(btnLogIn, 1);
    JPLogin.setComponentZOrder(btnRegistrieren, 1);
    JPLogin.setComponentZOrder(textField, 1);
    JPLogin.setComponentZOrder(passwordField, 1);
    
    JPReg.setComponentZOrder(lblRegBG, 2);
    JPReg.setComponentZOrder(lblRegName, 1);
    JPReg.setComponentZOrder(lblRegPass, 1);
    JPReg.setComponentZOrder(lblRegPassRepeat, 1);
    JPReg.setComponentZOrder(lblRegistrieren, 1);
    JPReg.setComponentZOrder(btnRegOk, 1);
    JPReg.setComponentZOrder(btnRegBack, 1);
    JPReg.setComponentZOrder(tfRegName, 1);
    JPReg.setComponentZOrder(tfRegPass, 1);
    JPReg.setComponentZOrder(tfRegPassRepeat, 1);
    
    
    logInWindow.repaint();
  }
  
  
  public void LogIn(String user, String password){
    try{
      Boolean b = false;
      
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + d.URL,"root", "");
      Statement s = con.createStatement();
      
      ResultSet rs = s.executeQuery("select name, password from users");
      while(rs.next()){
        if(rs.getString("name").equals(user)){
          debug.log("User gefunden: " + rs.getString("name"));
          if(rs.getString("password").equals(password)){
            User = new user();
            User.setName(user);
            User.init();
           
            window.setupContent();
            Leaderboard.setUp();
            
            logInWindow.setVisible(false);
            logInWindow.dispose();
            
            JOptionPane.showMessageDialog(null, "Viel Spaß bei CarrotClicker!");
          }else{
            JOptionPane.showMessageDialog(logInWindow, "Falsches Password!");
          }
          b = true;
        }
      }
      
      if(!b){
        JOptionPane.showMessageDialog(logInWindow, "Benutzername nicht gefunden!");
      }      
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public static void reg(){
    try{
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost/"+d.URL,"root", "");
      Statement s = con.createStatement();
      boolean UserExists = false;
      
      if(tfRegName.getText().isEmpty() || tfRegPass.getText().isEmpty() || tfRegPassRepeat.getText().isEmpty()){
        JOptionPane.showMessageDialog(logInWindow, "Felder dürfen nicht leer sein!");
      }else{
        ResultSet rs = d.executeQuarry("select name from users");
        while (rs.next()) {
          if (rs.getString("name").equals(tfRegName.getText())) {
            debug.log("Benutzer " + tfRegName.getText() + " ist bereits vorhanden!");
            JOptionPane.showMessageDialog(logInWindow, "Benutzer " + tfRegName.getText() + " ist bereits vorhanden!");
            UserExists = true;
          }
        }
        
        if(!UserExists) {
          if(tfRegPass.getText().equals(tfRegPassRepeat.getText())){
            debug.log("Passwörter stimmen überein!");
            s.executeUpdate("insert into " + d.URL + " (name,password) values ('" + tfRegName.getText() +"','" + tfRegPass.getText() +"')");
            logInWindow.setContentPane(JPLogin);
            textField.setText(tfRegName.getText());
            passwordField.setText(tfRegPass.getText());
            JPLogin.revalidate();
            JPLogin.repaint();
            JOptionPane.showMessageDialog(logInWindow, "Willkommen " + tfRegName.getText() + ", du kannst dich jetzt jederzeit anmelden und Spielen!");
          }
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public static void setUpReg(){
    JPReg = new JPanel();
    JPReg.setLayout(null);
    
    lblRegBG = new JLabel(new ImageIcon(login.class.getResource("pics/BGLogin.png")));
    lblRegBG.setBounds(0,0,500,400);
    JPReg.add(lblRegBG);
    
    
    lblRegistrieren = new JLabel("Registrieren");
    lblRegistrieren.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblRegistrieren.setHorizontalAlignment(SwingConstants.CENTER);
    lblRegistrieren.setBounds(center(150), 25, 150, 29);
    JPReg.add(lblRegistrieren);
    
    lblRegName = new JLabel("Benutzername");
    lblRegName.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblRegName.setHorizontalAlignment(SwingConstants.RIGHT);
    lblRegName.setBounds(center(150)-100, 100, 150, 29);
    JPReg.add(lblRegName);
    
    lblRegPass = new JLabel("Passwort");
    lblRegPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblRegPass.setHorizontalAlignment(SwingConstants.RIGHT);
    lblRegPass.setBounds(center(150)-100, 150, 150, 29);
    JPReg.add(lblRegPass);
    
    lblRegPassRepeat = new JLabel("Passwort wiederholen");
    lblRegPassRepeat.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblRegPassRepeat.setHorizontalAlignment(SwingConstants.RIGHT);
    lblRegPassRepeat.setBounds(center(200)-120, 200, 200, 29);
    JPReg.add(lblRegPassRepeat);
    
    tfRegName = new JTextField();
    tfRegName.setBounds(center(154)+75, 100, 154, 25);
    JPReg.add(tfRegName);
    
    tfRegPass = new JTextField();
    tfRegPass.setBounds(center(154)+75, 150, 154, 25);
    JPReg.add(tfRegPass);
    
    ActionListener ALReg = new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        debug.log("Versuche Registrierung!");
        reg();
      }
    };
    
    tfRegPassRepeat = new JTextField();
    tfRegPassRepeat.setBounds(center(154)+75, 200, 154, 25);
    tfRegPassRepeat.addActionListener(ALReg);
    JPReg.add(tfRegPassRepeat);
    
    btnRegOk = new JButton(new ImageIcon(login.class.getResource("pics/Registrieren.png")));
    btnRegOk.setBounds(center(120)+75, 270, 120, 37);
    JPReg.add(btnRegOk);
    btnRegOk.addActionListener(ALReg);
    
    btnRegBack = new JButton(new ImageIcon(login.class.getResource("pics/zurück.png")));
    btnRegBack.setBounds(center(120)-75, 270, 120, 37);
    btnRegBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        logInWindow.setContentPane(JPLogin);
        JPReg.revalidate();
        JPReg.repaint();
      }
    });
    JPReg.add(btnRegBack);
  }
  
  public static int center(int width){
    return (int) (logInWindow.getWidth() - width) / 2;
  }
}
