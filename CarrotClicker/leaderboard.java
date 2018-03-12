package CarrotClicker;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;

import javax.swing.*;      

import CarrotClicker.utilities.Debug;

public class leaderboard extends Window{
  public static JPanel JPLeaderboard = new JPanel();
  private static JLabel lblJPBackground,lblLeaderboard,lblTable;
  public static JButton btnClose;
  
  
  public void setUp()throws SQLException{
    JPLeaderboard.setLayout(null); 
    
    lblJPBackground = new JLabel(new ImageIcon(Window.class.getResource("pics/LeaderboardBG.png")));
    lblJPBackground.setBounds(0,0,600,400);
    JPLeaderboard.add(lblJPBackground);
    
    lblLeaderboard = new JLabel("Bestenliste");
    lblLeaderboard.setBounds((int) (width - 300) / 2,30,300,50);
    lblLeaderboard.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
    lblLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
    JPLeaderboard.add(lblLeaderboard);
    
    
    
    lblTable = new JLabel("");
    lblTable.setBounds(lblJPBackground.getX(),lblJPBackground.getY(),lblJPBackground.getWidth(),lblJPBackground.getHeight());
    lblTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
    lblTable.setHorizontalAlignment(SwingConstants.CENTER);
    lblTable.setVerticalAlignment(SwingConstants.CENTER);
    JPLeaderboard.add(lblTable);
    
    btnClose = new JButton(new ImageIcon(Window.class.getResource("pics/Close.png")));
    btnClose.setBounds(window.getWidth()-90,35,40,40);
    btnClose.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        hide();
      }
    });
    JPLeaderboard.add(btnClose);
    
    JPLeaderboard.setComponentZOrder(lblJPBackground, 2);
    JPLeaderboard.setComponentZOrder(btnClose, 1);
  }
  
  public void show() throws SQLException{
    debug.log("Leaderboard aktiviert");
    d.executeUpdate("UPDATE " + d.URL + " SET totCarrots='" + User.getTotCarrots() + "' WHERE name='" + User.getName() + "';");
    if(d.Highscore().equals("<html><body><center></center></body></html>")){
      lblTable.setText("Nicht genügend Werte!");
    }else{
      lblTable.setText(d.Highscore());
    }
    window.setContentPane(JPLeaderboard);
    JPLeaderboard.revalidate();    
    JPLeaderboard.repaint();
  }
  public void hide(){
    debug.log("Leaderboard deaktiviert");
    window.setContentPane(JPWindow);
    JPLeaderboard.revalidate();
    JPLeaderboard.repaint();
  }
}
