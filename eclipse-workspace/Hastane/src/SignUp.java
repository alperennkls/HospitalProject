import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.codejava.sql.javaconnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField sign_tc;
	private JTextField sign_name;
	private JTextField sign_surname;
	private JLabel lblifrenizDorulama;
	private JButton btnNewButton; 
	private JPasswordField sign_pw;
	private JPasswordField sign_pwconfirm;
javaconnect conn = new javaconnect();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 431);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TC Kimlik Numaranız : ");
		lblNewLabel.setBounds(10, 10, 115, 32);
		contentPane.add(lblNewLabel);
		
		sign_tc = new JTextField();
		sign_tc.setBounds(120, 15, 115, 25);
		contentPane.add(sign_tc);
		sign_tc.setColumns(10);
		
		JLabel lblAdnz = new JLabel("Adınız :");
		lblAdnz.setBounds(10, 50, 115, 32);
		contentPane.add(lblAdnz);
		
		sign_name = new JTextField();
		sign_name.setColumns(10);
		sign_name.setBounds(120, 55, 115, 25);
		contentPane.add(sign_name);
		
		JLabel lblSoyadnz = new JLabel("Soyadınız : ");
		lblSoyadnz.setBounds(10, 90, 115, 32);
		contentPane.add(lblSoyadnz);
		
		sign_surname = new JTextField();
		sign_surname.setColumns(10);
		sign_surname.setBounds(120, 95, 115, 25);
		contentPane.add(sign_surname);
		
		JLabel lblifreniz = new JLabel("Şifreniz :");
		lblifreniz.setBounds(10, 130, 115, 32);
		contentPane.add(lblifreniz);
		
		lblifrenizDorulama = new JLabel("Şifre Doğrulama :");
		lblifrenizDorulama.setBounds(10, 170, 115, 32);
		contentPane.add(lblifrenizDorulama);
		
		btnNewButton = new JButton("Kayıt Ol");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
                Connection con = conn.connDB();
                
                if(sign_name.getText().isEmpty()||sign_pw.getText().isEmpty()||sign_pwconfirm.getText().isEmpty()||sign_surname.getText().isEmpty()||sign_tc.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen Tüm Alanları Eksiksiz Doldurunuz.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
                else if(!sign_tc.getText().matches("\\d+")) {
               
                    JOptionPane.showMessageDialog(null, "TC Kimlik Numaranız Sayılardan Oluşmalıdır.", "Hata", JOptionPane.ERROR_MESSAGE);

                }
                else if (sign_tc.getText().length()!=11) {
                    JOptionPane.showMessageDialog(null, "Tc Kimlik Numaranız 11 haneli olmalı", "Hata", JOptionPane.ERROR_MESSAGE);

                	
                }
                else if (!sign_pw.getText().equals(sign_pwconfirm.getText())) {
                    JOptionPane.showMessageDialog(null, "Girdiğiniz Şifreler Eşleşmiyor.", "Hata", JOptionPane.ERROR_MESSAGE);

                }
                else {
                try {
                	String query = "{call AddPatient(?, ?, ?, ?)}";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, sign_tc.getText());
                    pst.setString(2, sign_name.getText());
                    pst.setString(3, sign_surname.getText());
					pst.setString(4, new String(sign_pw.getPassword()));
                    int result = pst.executeUpdate();
                    if(result > 0) {
                        JOptionPane.showMessageDialog(null, "Başarıyla Kayıt Oldunuz", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                        Login lgn=new Login();
                        lgn.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hasta eklenemedi.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e1.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
		 }
        });
		btnNewButton.setBounds(120, 216, 115, 32);
		contentPane.add(btnNewButton);
		
		sign_pw = new JPasswordField();
		sign_pw.setBounds(120, 135, 115, 25);
		contentPane.add(sign_pw);
		
		sign_pwconfirm = new JPasswordField();
		sign_pwconfirm.setBounds(120, 175, 115, 25);
		contentPane.add(sign_pwconfirm);
	}
}
