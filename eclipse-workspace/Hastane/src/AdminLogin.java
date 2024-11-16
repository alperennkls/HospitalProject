import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Screens.AdminScreen;
import net.codejava.sql.javaconnect;
import user.Admin;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AdminLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField login_admintc;
	private JPasswordField login_adminpw;
	private javaconnect conn = new javaconnect();
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminLogin() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 434);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Admin Login Screen");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 22));
		lblNewLabel.setBounds(149, 10, 221, 72);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 123, 491, 138);
		contentPane.add(panel);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. Number :");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 46, 167, 35);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Password            : ");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(10, 91, 167, 35);
		panel.add(lblNewLabel_1_1_1);

		login_admintc = new JTextField();
		login_admintc.setColumns(10);
		login_admintc.setBounds(187, 52, 158, 29);
		panel.add(login_admintc);

		login_adminpw = new JPasswordField();
		login_adminpw.setBounds(187, 99, 158, 29);
		panel.add(login_adminpw);

		JLabel lblNewLabel_2_1 = new JLabel("Admin Login");
		lblNewLabel_2_1.setFont(new Font("Serif", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(10, 7, 118, 29);
		panel.add(lblNewLabel_2_1);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (login_admintc.getText().length() != 11) {
					JOptionPane.showMessageDialog(null, "TC Number must be 11", "Hata", JOptionPane.ERROR_MESSAGE);
				} else {
					try (Connection con = conn.connDB();
							PreparedStatement pst = con.prepareStatement("SELECT * FROM Admins WHERE AdminTc = ? AND AdminPw = ?")) {

						pst.setString(1, login_admintc.getText());
						pst.setString(2, new String(login_adminpw.getPassword()));

						try (ResultSet rs = pst.executeQuery()) {
							if (rs.next()) {
								Admin.setAdminName(rs.getString("AdminName"));
								AdminScreen asc = new AdminScreen();
								asc.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Wrong Tc number or Password", "Hata", JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
			}
		});
		btnLogin.setFont(new Font("Sitka Text", Font.BOLD, 19));
		btnLogin.setBounds(20, 271, 491, 71);
		contentPane.add(btnLogin);
		
		JButton btnNewButton = new JButton("<---");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Login lgn=new Login();
			lgn.setVisible(true);
			dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(0, 10, 103, 31);
		contentPane.add(btnNewButton);
	}
}
