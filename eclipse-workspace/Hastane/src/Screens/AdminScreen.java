package Screens;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AdminEdit.AppointmentOperations;
import AdminEdit.DoctorOperations;
import user.Admin;
import AdminEdit.PatientOperations;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AdminScreen extends JFrame {
	static Admin admin= new Admin();
 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
   
	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen frame = new AdminScreen();
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
	@SuppressWarnings("static-access")
	public AdminScreen() {
		setForeground(new Color(255, 255, 255));
		setTitle("Admin Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 449);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın "+admin.getAdminName());
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 187, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Doktor İşlemleri");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoctorOperations dop;
				try {
					dop = new DoctorOperations();
					dop.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(10, 74, 222, 55);
		contentPane.add(btnNewButton);
		
		JButton btnHastaListes = new JButton("Hasta İşlemleri");
		btnHastaListes.setBackground(new Color(255, 255, 255));
		btnHastaListes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PatientOperations po = new PatientOperations();
					po.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnHastaListes.setHorizontalAlignment(SwingConstants.LEFT);
		btnHastaListes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHastaListes.setBounds(396, 74, 222, 55);
		contentPane.add(btnHastaListes);
		
		JButton btnRandevuListesi = new JButton("Randevu İşlemleri");
		btnRandevuListesi.setBackground(new Color(255, 255, 255));
		btnRandevuListesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppointmentOperations aop;
				try {
					aop = new AppointmentOperations();
					aop.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRandevuListesi.setHorizontalAlignment(SwingConstants.LEFT);
		btnRandevuListesi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRandevuListesi.setBounds(10, 166, 222, 55);
		contentPane.add(btnRandevuListesi);
		
		JButton btnNewButton_1 = new JButton("Çıkış");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(472, 10, 146, 38);
		contentPane.add(btnNewButton_1);
	}

	
}
