package Screens;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import user.Appointments;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import net.codejava.sql.javaconnect;
import user.Admin;
import user.Branslar;
import user.Doctor;
import user.Hospitals;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PatientScreen extends JFrame {
	javaconnect conn=new javaconnect();
	

	Appointments Appointments= new Appointments();
	private static final long serialVersionUID = 1L;
	private Object[] appointmentdata = null;
	Admin admin = new Admin();
	private static String PatientID2;
	public static String getPatientID2() {
		return PatientID2;
	}

	public static void setPatientID2(String patientID2) {
		PatientID2 = patientID2;
	}

	private int hospitalID;
	private int bransID;
	private int doctorID;
	private JPanel contentPane;
	private JTable full_appointmentstable;
	@SuppressWarnings("serial")
	private DefaultTableModel appointmentmodel = new DefaultTableModel() {};  
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientScreen frame = new PatientScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws HeadlessException 
	 */
	@SuppressWarnings("rawtypes")
	public PatientScreen() throws HeadlessException, SQLException {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(10, 22, 682, 442);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Aktif Randevularınız", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 474, 395);
		panel.add(scrollPane);
		Object[] colAppointmentList=new Object[6];
        colAppointmentList[0]="DoctorName";
        colAppointmentList[1]="DoctorSurname";
        colAppointmentList[2]="Brans";
        colAppointmentList[3]="Hastane";
        colAppointmentList[4]="Randevu Tarihi";
        colAppointmentList[5]="Randevu Saati";
		appointmentmodel.setColumnIdentifiers(colAppointmentList);
		appointmentdata=new Object[6];
		for(int i=0;i< Appointments.getAppointmentList3().size();i++) {
			appointmentdata[0]=Appointments.getAppointmentList3().get(i).getDoctorName();
			appointmentdata[1]=Appointments.getAppointmentList3().get(i).getDoctorSurname();
			appointmentdata[2]=Appointments.getAppointmentList3().get(i).getBrans();
			appointmentdata[3]=Appointments.getAppointmentList3().get(i).getHospital();
			appointmentdata[4]=Appointments.getAppointmentList3().get(i).getAppointmentDate();
			appointmentdata[5]=Appointments.getAppointmentList3().get(i).getAppointmentTime();
			appointmentmodel.addRow(appointmentdata);

		}
		JLabel lblHastaneSecim = new JLabel("Hastane Seçimi");
		lblHastaneSecim.setBackground(new Color(255, 255, 255));
		lblHastaneSecim.setBounds(491, 5, 142, 26);
		panel.add(lblHastaneSecim);
		
		JComboBox<Hospitals> add_selecthospital = new JComboBox<>();
	    add_selecthospital.setBounds(491, 35, 139, 26);
	    panel.add(add_selecthospital);
	    
		
		
		full_appointmentstable = new JTable(appointmentmodel);
		scrollPane.setViewportView(full_appointmentstable);
		

	    for (Hospitals hospital : admin.getHospitalList()) {
	        add_selecthospital.addItem(hospital);
	    }
	    JLabel lblNewLabel = new JLabel("Bölüm Seçimi");
	    lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(491, 68, 142, 26);
	    add_selecthospital.addActionListener(e -> {
	        @SuppressWarnings("unchecked")
			JComboBox<Hospitals> c = (JComboBox<Hospitals>) e.getSource();
	        Hospitals item2 = (Hospitals) c.getSelectedItem();	        
			panel.add(lblNewLabel);
	        if (item2 != null) {
	            Admin.setHospitalID2(String.valueOf(item2.getHospitalID()));
	        } else {
	            System.out.println("Seçilen hastane null");
	        }
	    });
	    
	    JComboBox<Branslar> add_selectbrans = new JComboBox<Branslar>();
		add_selectbrans.setBounds(491, 104, 139, 26);	
	    add_selecthospital.addActionListener(e1 ->{	    	
			panel.add(add_selectbrans);
			JLabel lblNewLabel_1 = new JLabel("Doktor Seçimi");
			lblNewLabel_1.setBackground(new Color(255, 255, 255));
			lblNewLabel_1.setBounds(491, 135, 142, 26);
			panel.add(lblNewLabel_1);				
			});	    		
			
		
		JComboBox<Doctor> add_selectdoctor = new JComboBox<Doctor>();
		add_selectdoctor.setBounds(491, 171, 139, 26);	
		
		for(int i=0;i<admin.getBransList().size();i++)
		{
		add_selectbrans.addItem(new Branslar(admin.getBransList().get(i).getBransID(),admin.getBransList().get(i).getBransName()));	
		}
		
		add_selectbrans.addActionListener(e1 ->{
		add_selectdoctor.removeAllItems();
		@SuppressWarnings("unchecked")
		JComboBox<String> c1 =(JComboBox)e1.getSource();			
			Branslar item = (Branslar) c1.getSelectedItem();
			Admin.setBransID2(String.valueOf(item.getBransID()));
			panel.add(add_selectdoctor);
			try {
				for(int i=0;i<admin.getDoctorList2().size();i++)
				{
				add_selectdoctor.addItem(new Doctor(admin.getDoctorList2().get(i).getDoctorID2(),admin.getDoctorList2().get(i).getDoctorName2()));				
				}
			} catch (SQLException e11) {
				e11.printStackTrace();
			}
		});
		
		
		JDateChooser add_selectdate = new JDateChooser();
		add_selectdate.setBackground(new Color(255, 255, 255));
		add_selectdate.setBounds(491, 235, 139, 26);			
		add_selectdate.setDateFormatString("y MMM d");
			
		add_selectdoctor.addActionListener(e ->{			
			JLabel lblNewLabel_1_1 = new JLabel("Tarih Seçimi");
			lblNewLabel_1_1.setBounds(491, 199, 142, 26);
			panel.add(lblNewLabel_1_1);
			panel.add(add_selectdate);			
			});


		@SuppressWarnings("unused")
		SimpleDateFormat dform = new SimpleDateFormat("dd/MM/yy HH:mm:ss");		
		JButton btnUygunRandevularGrntle = new JButton("Uygun Saatleri Gör");
		btnUygunRandevularGrntle.setBackground(new Color(255, 255, 255));
		btnUygunRandevularGrntle.setBounds(491, 271, 139, 28);
		JComboBox add_selecttime = new JComboBox();
		add_selecttime.setBounds(491, 334, 139, 26);
		add_selectdate.addHierarchyListener(e1 ->{
	    	
			panel.add(btnUygunRandevularGrntle);
			JLabel lblNewLabel_1_2 = new JLabel("Saat Seçiniz");
			lblNewLabel_1_2.setBackground(new Color(255, 255, 255));
			lblNewLabel_1_2.setBounds(491, 298, 142, 26);
			panel.add(lblNewLabel_1_2);
			});
		
		
		
		
				
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		btnUygunRandevularGrntle.addActionListener(new ActionListener() {
		    @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
		        add_selecttime.removeAllItems(); 
				

		        String[] allTimes = {
		            "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00",
		            "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00",
		            "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00"
		        };

		        String selectedDate = sdf.format(add_selectdate.getDate());
		        Branslar selectedBrans = (Branslar) add_selectbrans.getSelectedItem();
		        Doctor selectedDoctor = (Doctor) add_selectdoctor.getSelectedItem();


		        if (selectedDate != null && selectedBrans != null && selectedDoctor != null) {
		            Connection con = conn.connDB();
		            String query = "SELECT AppointmentTime FROM Appointments WHERE DoctorID=? AND AppointmentDate = ?";
		            panel.add(add_selecttime);
		            try {	
		                PreparedStatement pst = con.prepareStatement(query);
		                pst.setInt(1, selectedDoctor.getDoctorID2()); // Doktor ID
		                pst.setString(2, selectedDate); // Seçilen Tarih

		                ResultSet rs = pst.executeQuery();
		                java.util.List<String> bookedTimes = new ArrayList<>();
		                while (rs.next()) {
		                    String appointmentTime = rs.getString("AppointmentTime");
		                    bookedTimes.add(appointmentTime);
		                }

		                for (String time : allTimes) {
		                    if (!bookedTimes.contains(time)) {
		                        add_selecttime.addItem(time);
		                    }
		                }
		            }
		            catch (SQLException ex) {
		                ex.printStackTrace();
		                System.out.println("SQL Hatası: " + ex.getMessage());
		            } 
		        } else {
		            JOptionPane.showMessageDialog(null, "Lütfen tüm seçimleri yapınız!", "Uyarı", JOptionPane.WARNING_MESSAGE);
		        }
	
				
		    }
		    
		});
		 
   		
   		JButton btn_randevual = new JButton("Randevu Al");
   		btn_randevual.setBackground(new Color(255, 255, 255));
		btn_randevual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Connection con = conn.connDB();
                if (add_selectbrans.getSelectedItem() == null || add_selectdoctor.getSelectedItem() == null || add_selectdate.getDate() == null || add_selecttime.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Lütfen Tüm Alanları Eksiksiz Doldurunuz.", "Hata", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Hospitals hospital= (Hospitals) add_selecthospital.getSelectedItem();
                        if (hospital != null) {
                            hospitalID = hospital.getHospitalID();
                        }
                        
                        Branslar brans= (Branslar) add_selectbrans.getSelectedItem();
                        if (brans != null) {
                            bransID = brans.getBransID();
                        }
                        
                        Doctor selectedDoctor= (Doctor) add_selectdoctor.getSelectedItem();
                        if (selectedDoctor != null) {
                            doctorID = selectedDoctor.getDoctorID2(); // Ensure this is correct
                        }
                        // ComboBox'tan seçilen zamanı al
                        String selectedTime = add_selecttime.getSelectedItem().toString();
                        // Zamanı Time nesnesine dönüştür
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        // ComboBox'tan seçilen zamanı alStringselectedTime= add_selecttime.getSelectedItem().toString();
                        // Zamanı Time nesnesine dönüştürSimpleDateFormattimeFormat=newSimpleDateFormat("HH:mm:ss");
                        java.util.Date parsedTime= timeFormat.parse(selectedTime);
                        Time sqlTime=new Time(parsedTime.getTime());
                        
                        String query="exec AddAppointment ?, ?, ?, ?, ?, ?";
                        PreparedStatement pst= con.prepareStatement(query);
                        pst.setString(1, PatientID2);
                        pst.setString(2, String.valueOf(bransID));
                        pst.setString(3, String.valueOf(doctorID)); // Ensure doctorID is correctly set
                        pst.setString(4, String.valueOf(hospitalID));
                        pst.setDate(5, new java.sql.Date(add_selectdate.getDate().getTime()));
                        pst.setTime(6, sqlTime);
                        
                        
                        int result= pst.executeUpdate();
                        if (result > 0) {
                            refreshTable();
                            JOptionPane.showMessageDialog(null, "Randevu başarıyla alındı.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Randevu alınamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e1.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Tarih formatı hatası: " + e1.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
		
					
		btn_randevual.setBounds(491, 370, 139, 35);
		panel.add(btn_randevual);	
									
		}
	
	private void populateTableModel() throws SQLException {
		ArrayList<Appointments> appointments = Appointments.getAppointmentList3();
		for (Appointments appointment : appointments) {
			Object[] rowData = new Object[6];
			rowData[0] = appointment.getDoctorName();
			rowData[1] = appointment.getDoctorSurname();
			rowData[2] = appointment.getBrans();
			rowData[3] = appointment.getHospital();
			rowData[4] = appointment.getAppointmentDate();
			rowData[5] = appointment.getAppointmentTime();
			appointmentmodel.addRow(rowData);
		}
	}

	public void refreshTable() throws SQLException {
	    appointmentmodel.setRowCount(0); // Tabloyu temizle
	    full_appointmentstable.clearSelection(); // Seçimi sıfırla
	    populateTableModel(); // Tabloyu yeniden doldur
	}
}
