import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Screens.DoctorScreen;
import Screens.PatientScreen;
import net.codejava.sql.javaconnect;
import user.Appointments;
import user.DeletedAppointments;
import user.Doctor;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField login_doctortc;
    private JPasswordField login_doctorpw;
    private JTextField login_patienttc;  
    private JPasswordField login_patientpw;
    private javaconnect conn = new javaconnect();
    Doctor dc=new Doctor();	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 533, 434);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Hastane Randevu Sistemi");
        lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 25));
        lblNewLabel.setBounds(67, 21, 381, 71);
        contentPane.add(lblNewLabel);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 126, 496, 261);
        contentPane.add(tabbedPane);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Doktor Girişi", null, panel, null);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("T.C. Numaranız :");
        lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(10, 46, 167, 35);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Şifreniz             : ");
        lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(10, 91, 167, 35);
        panel.add(lblNewLabel_1_1);

        login_doctortc = new JTextField();
        login_doctortc.setBounds(187, 52, 158, 29);
        panel.add(login_doctortc);
        login_doctortc.setColumns(10);

        login_doctorpw = new JPasswordField();
        login_doctorpw.setBounds(187, 99, 158, 29);
        panel.add(login_doctorpw);

        JLabel lblNewLabel_2_1 = new JLabel("Doktor Girişi");
        lblNewLabel_2_1.setFont(new Font("Serif", Font.PLAIN, 13));
        lblNewLabel_2_1.setBounds(10, 10, 118, 29);
        panel.add(lblNewLabel_2_1);
        JButton btn_DoctorLogin = new JButton("Giriş Yap");
        btn_DoctorLogin.setBackground(new Color(255, 255, 255));
        btn_DoctorLogin.setBounds(273, 156, 191, 45);
        panel.add(btn_DoctorLogin);
        btn_DoctorLogin.setFont(new Font("Sitka Text", Font.BOLD, 19));
        
                JButton btnAdminLogin = new JButton("Yönetici Girişi");
                btnAdminLogin.setBackground(new Color(255, 255, 255));
                btnAdminLogin.setBounds(10, 156, 191, 45);
                panel.add(btnAdminLogin);
                btnAdminLogin.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        AdminLogin asc = new AdminLogin();
                        asc.setVisible(true);
                        dispose();
                    }
                });
                btnAdminLogin.setFont(new Font("Sitka Text", Font.BOLD, 19));
        btn_DoctorLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                if (login_doctortc.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "TC Kimlik Numaranız 11 Haneli olmalıdır", "Hata", JOptionPane.ERROR_MESSAGE);
                } else {
                    String sql = "SELECT * FROM Doctors WHERE DoctorTC = ? AND DoctorPw = ?";
                    try (
                    	Connection con = conn.connDB();
                        PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, login_doctortc.getText());
                        pst.setString(2, new String(login_doctorpw.getPassword()));
                        try (ResultSet rs = pst.executeQuery()) {
                            if (rs.next()) {
                                Doctor doctor = new Doctor();
                                doctor.setDoctorName(rs.getString("DoctorName"));
                                Appointments.setDoctorID2(rs.getInt("DoctorID"));
                                DeletedAppointments.setDoctorID2(rs.getInt("DoctorID"));                                
                                DoctorScreen dsc = new DoctorScreen(doctor);
                                dsc.setVisible(true);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Yanlış TC Kimlik Numarası veya Şifre", "Hata", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Hasta Girişi", null, panel_1, null);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1_2 = new JLabel("T.C. Numaranız :");
        lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(10, 46, 167, 35);
        panel_1.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("Şifreniz             : ");
        lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(10, 91, 167, 35);
        panel_1.add(lblNewLabel_1_1_1);

        login_patienttc = new JTextField();
        login_patienttc.setColumns(10);
        login_patienttc.setBounds(187, 52, 158, 29);
        panel_1.add(login_patienttc);

        login_patientpw = new JPasswordField();
        login_patientpw.setBounds(187, 99, 158, 29);
        panel_1.add(login_patientpw);

        JLabel lblNewLabel_2 = new JLabel("Hasta Girişi");
        lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(10, 10, 118, 29);
        panel_1.add(lblNewLabel_2);
        
        JButton btnAdminLogin_1 = new JButton("Kayıt Ol");
        btnAdminLogin_1.setBackground(new Color(255, 255, 255));
        btnAdminLogin_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SignUp sign=new SignUp();
        		sign.setVisible(true);
        		dispose();
        	}
        });
        btnAdminLogin_1.setFont(new Font("Sitka Text", Font.BOLD, 19));
        btnAdminLogin_1.setBounds(10, 156, 191, 45);
        panel_1.add(btnAdminLogin_1);
    	Appointments appointments=new Appointments();

        JButton btn_PatientLogin = new JButton("Giriş Yap");
        btn_PatientLogin.setBackground(new Color(255, 255, 255));
        btn_PatientLogin.addActionListener(new ActionListener() {
        	@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
        		if (login_patienttc.getText().length() != 11) {
                    JOptionPane.showMessageDialog(null, "TC Kimlik Numaranız 11 Haneli olmalıdır", "Hata", JOptionPane.ERROR_MESSAGE);
                } else {
                    String sql = "SELECT * FROM Patients WHERE PatientTC = ? AND PatientPw = ?";
                    try (
                    	Connection con = conn.connDB();
                        PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, login_patienttc.getText());
                        pst.setString(2, new String(login_patientpw.getPassword()));
                        try (ResultSet rs = pst.executeQuery()) {
                            if (rs.next()) {
                             	appointments.setPatientID2(rs.getString("PatientID"));
                             	PatientScreen.setPatientID2(rs.getString("PatientID"));
                             	PatientScreen psc= new PatientScreen(); 
                               psc.setVisible(true); 
                               dispose();                                 
                            } else { 
                                 JOptionPane.showMessageDialog(null, "Yanlış TC Kimlik Numarası veya Şifre", "Hata", JOptionPane.ERROR_MESSAGE);
                            } 
                        } 
                    } catch (Exception e2) { 
                        e2.printStackTrace(); 
                    }
                }
            }
        });
        btn_PatientLogin.setFont(new Font("Sitka Text", Font.BOLD, 19));
        btn_PatientLogin.setBounds(273, 156, 191, 45);
        panel_1.add(btn_PatientLogin);
    }
}
