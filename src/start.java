package CC;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Timer;
import java.util.TimerTask;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;

public class start {
	public static int carrotAmount, totCarrots;

	private static Toolkit t;
	private static int width = 800, height = 400;

	public static JFrame window = new JFrame();
	public static JLabel lblAmount, lblMessage, lblMsgBG, lblMehrFelder, lblHelfer;
	public static JButton btnCarrot, btnFelder, btnHelfer;
	public static int[] num;
	public static ResultSet rs;

	public static String User = "Admin";
	public static int UserID;
	public static int Helfer, Felder;
	public static Timer timerCPS;

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
		Helfer = value("helfer");

		lblAmount.setText(value("carrots") + " Möhren");
		lblMehrFelder.setText(preise.felder() + " Möhren");
		lblHelfer.setText(preise.helfer() + " Möhren");
		lblInfos.setText("<html><body>Möhren pro Klick: " + cpc() + "<br>Möhren pro sec: " + cps() + "</body></html>");
		window.getContentPane().repaint();
		
		timerCPS = new Timer();
		timerCPS.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				try {
					carrotAmount = carrotAmount + cps();
					lblAmount.setText(carrotAmount + " Möhren");
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
				
			}
		}, 0, 1000);

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

	public static void setupContent() {
		t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		int x = (int) (d.getWidth() - width) / 2;
		int y = (int) (d.getHeight() - height) / 2;
		window.getContentPane().setLayout(null);
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(start.class.getResource("/CC/assets/carrot_m.png")));
		window.setBounds(x, y, width, height);
		window.setTitle("CarrotClicker");
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		btnCarrot = new JButton(new ImageIcon(start.class.getResource("/CC/assets/carrot_m.png")));
		btnCarrot.setBackground(SystemColor.menu);
		btnCarrot.setForeground(SystemColor.text);
		btnCarrot.setBounds(83, 174, 150, 150);
		btnCarrot.setBorder(BorderFactory.createEmptyBorder());
		btnCarrot.setContentAreaFilled(false);
		window.getContentPane().add(btnCarrot);

		lblAmount = new JLabel(carrotAmount + "Möhren");
		lblAmount.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setBounds(92, 73, 150, 46);
		window.getContentPane().add(lblAmount);
		window.getContentPane().setComponentZOrder(lblAmount, window.getComponentCount());

		lblInfos = new JLabel("<html><body>Möhren pro Klick: 1<br>Möhren pro sec: 1</body></html>");
		lblInfos.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfos.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblInfos.setBounds(74, 11, 184, 75);
		window.getContentPane().add(lblInfos);

	}

	public static void setupShop() {
		btnHelfer = new JButton(new ImageIcon(start.class.getResource("/CC/assets/Erntehelfer.png")));
		btnHelfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (buy(preise.helfer())) {
						carrotAmount = carrotAmount - preise.helfer();

						Helfer++;

						d.executeUpdate(
								"UPDATE " + d.URL + " SET helfer = " + Helfer + " WHERE name = '" + User + "';");
						lblAmount.setText(carrotAmount + " Möhren");
						lblHelfer.setText(preise.helfer() + " Möhren");
						lblInfos.setText("<html><body>Möhren pro Klick: " + cpc() + "<br>Möhren pro sec: " + cps()
								+ "</body></html>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnHelfer.setBounds(433, 19, 100, 100);
		btnHelfer.setBorder(BorderFactory.createEmptyBorder());
		btnHelfer.setContentAreaFilled(false);
		window.getContentPane().add(btnHelfer);

		lblHelfer = new JLabel("PREIS");
		lblHelfer.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelfer.setBounds(433, 123, 100, 25);
		window.getContentPane().add(lblHelfer);

		btnFelder = new JButton(new ImageIcon(start.class.getResource("/CC/assets/mehrFelder.png")));
		btnFelder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (buy(preise.felder())) {
						carrotAmount = carrotAmount - preise.felder();

						Felder++;

						d.executeUpdate(
								"UPDATE " + d.URL + " SET felder = " + Felder + " WHERE name = '" + User + "';");
						lblAmount.setText(carrotAmount + " Möhren");
						lblMehrFelder.setText(preise.felder() + " Möhren");
						lblInfos.setText("<html><body>Möhren pro Klick: " + cpc() + "<br>Möhren pro sec: " + cps()
								+ "</body></html>");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		btnFelder.setContentAreaFilled(false);
		btnFelder.setBorder(BorderFactory.createEmptyBorder());
		btnFelder.setBounds(319, 18, 100, 100);
		window.getContentPane().add(btnFelder);

		lblMehrFelder = new JLabel("PREIS");
		lblMehrFelder.setHorizontalAlignment(SwingConstants.CENTER);
		lblMehrFelder.setBounds(319, 122, 100, 25);
		window.getContentPane().add(lblMehrFelder);

		window.getContentPane().repaint();
	}

	public static void close(JFrame window) {
		int i = JOptionPane.showConfirmDialog(window, "Wirklich beenden?", "CarrotClicker", JOptionPane.YES_NO_OPTION,
				1, new ImageIcon(start.class.getResource("/CC/assets/carrot_s.png")));
		if (i == 0) {
			timerCPS.cancel();
			try {
				d.executeUpdate("UPDATE " + d.URL + " SET carrots = " + carrotAmount + " WHERE name = '" + User + "';");
				d.executeUpdate(
						"UPDATE " + d.URL + " SET totCarrots = " + totCarrots + " WHERE name = '" + User + "';");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			window.setVisible(false);
			window.dispose();
		} else {
			JOptionPane.showMessageDialog(window, "Richtige Entscheidung.");
		}
	}

	public static int i;
	public static Timer timerMessage;
	public static boolean active = false;

	public static void message(String message) {
		if (active == false) {
			active = true;
			window.getContentPane().repaint();

			lblMessage = new JLabel(message);
			lblMessage.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
			lblMessage.setForeground(new Color(255, 140, 0));
			lblMessage.setBounds((btnCarrot.getWidth() - lblMessage.getWidth()) / 2 + btnCarrot.getX(),
					btnCarrot.getY(), 250, 30);

			lblMsgBG = new JLabel();
			lblMsgBG.setIcon(new ImageIcon(start.class.getResource("/CC/assets/Message.png")));
			lblMsgBG.setBounds(lblMessage.getX(), lblMessage.getY(), 250, 30);
			window.getContentPane().add(lblMsgBG);

			window.getContentPane().add(lblMessage);
			window.getContentPane().setComponentZOrder(lblMessage, 1);
			window.getContentPane().setComponentZOrder(lblMsgBG, 2);
			window.getContentPane().repaint();

			i = (int) btnCarrot.getY();

			System.out.println("start");
			timerMessage = new Timer();
			timerMessage.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {

					i = i - 2;
					lblMessage.setBounds((btnCarrot.getWidth() - lblMessage.getWidth()) / 2 + btnCarrot.getX(), i, 250,
							30);
					lblMsgBG.setBounds(lblMessage.getX(), lblMessage.getY(), 250, 30);
					if (i < (int) btnCarrot.getY() - 100) {
						window.getContentPane().remove(lblMessage);
						window.getContentPane().remove(lblMsgBG);
						window.getContentPane().repaint();
						active = false;
						timerMessage.cancel();
					}
				}
			}, 0, 30);
		}
	}

	public static int cps() throws SQLException { // carrots per second
		int c = value("Helfer") * 1;
		return c;
	}

	public static int cpc() throws SQLException { // Carrots per Click
		int c = (int) Math.pow(2.0, value("felder"));
		return c;
	}

	public static int value(String field) throws SQLException {
		int ret = 0;
		Database d = new Database();
		d.connect();
		d.URL = "users";
		rs = d.executeQuarry("select name," + field + " from users");
		while (rs.next()) {
			if (rs.getString("name").equals(User)) {
				ret = rs.getInt(field);
			}
		}
		d.con.close();
		return ret;
	}

	public static boolean buy(int price) {
		boolean b = false;
		if (carrotAmount >= price) {
			b = true;
		}else{
			message("Nicht genügend Möhren!");
		}

		return b;
	}
}
