package CC;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;

public class exe {
	public static int carrotAmount,totCarrots;
	
	private static Toolkit t;
	private static int x=0,y=0,width=800,height=400;
	
	public static JFrame window = new JFrame();
	public static JLabel lblAmount,lblMessage,lblMehrFelder,lblHelfer;
	public static JButton btnCarrot,btnMehrFelder,btnHelfer;
	private static JButton btnNewButton;
	public static int[] num;
	public static ResultSet rs;
	
	public static String User = "Admin";
	public static int UserID;
	public static int Helfer,Felder;
	public static Timer timer;
	
	public static preise p = new preise();
	public static Database d = new Database();
	private static JLabel lblInfos;
	
	public static void main(String[] args) throws SQLException {
		
		d.URL = "users";
		d.create();
		
		setupContent();
		setupShop();
		carrotAmount = value("carrots");
		Felder = value("felder");
		totCarrots = value("totCarrots");
		
		lblAmount.setText(value("carrots") + " Möhren");
		lblMehrFelder.setText(preise.felder() + " Möhren");
		lblInfos.setText("<html><body>Möhren pro Klick: " + cpc() + "<br>Möhren pro sec: " + cps() + "</body></html>");
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message("hello");
			}
		});
		
		btnNewButton_1.setBounds(10, 337, 89, 23);
		window.getContentPane().add(btnNewButton_1);
		
		
		
		
		
		
		window.getContentPane().repaint();
		
		
		
		
		
		
		
		
		btnCarrot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					carrotAmount = carrotAmount + cpc();
					totCarrots = totCarrots + cpc();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				lblAmount.setText(carrotAmount + " Möhren");
			}
		});
		
		window.addWindowListener(new java.awt.event.WindowAdapter() {
		    
			@Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		        close(window);
		    }
		});
		

	}
	public static void setupContent(){
		t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		int x = (int) (d.getWidth()-width)/2;
		int y = (int) (d.getHeight()-height)/2;
		window.getContentPane().setLayout(null);
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(exe.class.getResource("/CC/assets/carrot_m.png")));
		window.setBounds(x, y, width, height);
		window.setTitle("CarrotClicker");
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		btnCarrot = new JButton(new ImageIcon(exe.class.getResource("/CC/assets/carrot_m.png")));
		btnCarrot.setBackground(SystemColor.menu);
		btnCarrot.setForeground(SystemColor.text);
		btnCarrot.setBounds(123, 151, 150, 150);
		btnCarrot.setBorder(BorderFactory.createEmptyBorder());
		btnCarrot.setContentAreaFilled(false);
		window.getContentPane().add(btnCarrot);
		
		lblAmount = new JLabel(carrotAmount + "Möhren");
		lblAmount.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setBackground(new Color(255, 255, 255));
		lblAmount.setBounds(123, 83, 150, 46);
		window.getContentPane().add(lblAmount);
		
		lblInfos = new JLabel("<html><body>Möhren pro Klick: 1<br>Möhren pro sec: 1</body></html>");
		lblInfos.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfos.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblInfos.setBounds(105, 18, 184, 75);
		window.getContentPane().add(lblInfos);
		
		
		
		
		
	}
	
	public static void setupShop(){
		btnHelfer = new JButton(new ImageIcon(exe.class.getResource("/CC/assets/Erntehelfer.png")));
		btnHelfer.setBounds(481, 29, 100, 100);
		btnHelfer.setBorder(BorderFactory.createEmptyBorder());
		btnHelfer.setContentAreaFilled(false);
		window.getContentPane().add(btnHelfer);
		
		lblHelfer = new JLabel("PREIS");
		lblHelfer.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelfer.setBounds(481, 133, 100, 25);
		window.getContentPane().add(lblHelfer);
		
		btnMehrFelder = new JButton(new ImageIcon(exe.class.getResource("/CC/assets/mehrFelder.png")));
		btnMehrFelder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(buy(preise.felder())){
						carrotAmount = carrotAmount - preise.felder();
						
						Felder++;
						
						d.executeUpdate("UPDATE " + d.URL + " SET felder = " + Felder + " WHERE name = '" + User + "';");
						lblAmount.setText(carrotAmount + " Möhren");
						lblMehrFelder.setText(preise.felder() + " Möhren");
						lblInfos.setText("<html><body>Möhren pro Klick: " + cpc() + "<br>Möhren pro sec: " + cps() + "</body></html>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnMehrFelder.setContentAreaFilled(false);
		btnMehrFelder.setBorder(BorderFactory.createEmptyBorder());
		btnMehrFelder.setBounds(591, 29, 100, 100);
		window.getContentPane().add(btnMehrFelder);
		
		lblMehrFelder = new JLabel("PREIS");
		lblMehrFelder.setHorizontalAlignment(SwingConstants.CENTER);
		lblMehrFelder.setBounds(591, 133, 100, 25);
		window.getContentPane().add(lblMehrFelder);
		
		window.getContentPane().repaint();
	}
	
	public static void close(JFrame window){
		int i = JOptionPane.showConfirmDialog(window,"Wirklich beenden?","CarrotClicker",JOptionPane.YES_NO_OPTION,1, new ImageIcon(exe.class.getResource("/CC/assets/carrot_s.png")));
		if (i == 0){
			
			try {
				d.executeUpdate("UPDATE " + d.URL + " SET carrots = " + carrotAmount + " WHERE name = '" + User + "';");   
				d.executeUpdate("UPDATE " + d.URL + " SET totCarrots = " + totCarrots + " WHERE name = '" + User + "';");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			window.setVisible(false);
			window.dispose();
		}else{
			JOptionPane.showMessageDialog(window, "Richtige Entscheidung.");
		}
	}
	
	public static void message(String message) {
		lblMessage = new JLabel(message);
		lblMessage.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(0, 0, 50, 30);
		window.getContentPane().add(lblMessage);
		
		//for(int i = 0; i<10;i++){ //int i = btnCarrot.getY(); i<btnCarrot.getY()+3; i++
			 // ((int)(50-btnCarrot.getWidth())/2)+btnCarrot.getX()
			//System.out.println(lblMessage.getText() + " " + i );	
			//clblMessage.setText("" + i);
			window.getContentPane().repaint();
			
			
			
			timer = new Timer(1000,new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0;i<10;i++){
						System.out.println(i);
					}
					
				}
				
			});
			timer.setRepeats(false);
			timer.start();
		//}
		lblMessage.setText("leer");
		
		
	}
	
	public static void timer(int delay){
		System.out.println("Timer gestartet");
		timer = new Timer(delay,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					carrotAmount = carrotAmount + cps();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblAmount.setText(carrotAmount + " Möhren");
				window.getContentPane().repaint();
				
			}
			
		});
		timer.start();
		System.out.println("Timer beendet");
	}
	
	public static int cps() throws SQLException{	//carrots per second
		int c = value("Helfer")*1;
		
		return c;
	}
	
	public static int cpc() throws SQLException{    //Carrots per Click
		int c = 1*(value("felder")*value("felder"));
		if(c == 0){
			return 1;
		}else{
			return c;
		}
	}
	
	public static int value(String field) throws SQLException{
		int ret = 0;
		Database d = new Database();
		d.connect();
		d.URL= "users";
		rs = d.executeQuarry("select name," + field + " from users");
		while(rs.next()){
			if(rs.getString("name").equals(User)){
				ret = rs.getInt(field);
			}
		}
		d.con.close();
		return ret;
	}
	public static boolean buy(int price){
		boolean b = false;
		if(carrotAmount >= price){
			b = true;
		}
		
		return b;
	}
	
}
