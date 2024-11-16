package Screens;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.codejava.sql.javaconnect;
import user.Appointments;
import user.Doctor;
import user.DeletedAppointments;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DoctorScreen extends JFrame {
	static Doctor doctor= new Doctor();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_ActiveAppoinments;
	private DefaultTableModel appointmentmodel=new DefaultTableModel();
	private Object[] appointmentdata=null; 
	private Object[]deletedappointmentdata=null; 
	private DefaultTableModel deletedappointmentmodel=new DefaultTableModel();
	DeletedAppointments DeletedAppointments=new DeletedAppointments();
	Appointments Appointments = new Appointments();
	private JTable table;
	javaconnect conn = new javaconnect();
	private JTextField delete_RandevuID;
	/**
	 * Launch the application.
	 */
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorScreen frame = new DoctorScreen(doctor);
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
	 */
	
	public DoctorScreen(Doctor doctor) throws SQLException {
		Object[] colAppointmentList=new Object[4];
		colAppointmentList[0]="ID";
		colAppointmentList[1]="PatientID";
		colAppointmentList[2]="HospitalID";
		colAppointmentList[3]="Appointment Date";
		appointmentmodel.setColumnIdentifiers(colAppointmentList);
		appointmentdata=new Object[4];
		for(int i=0;i<Appointments.getAppointmentList().size();i++) {
			appointmentdata[0]=Appointments.getAppointmentList().get(i).getAppointmentID();
			appointmentdata[1]=Appointments.getAppointmentList().get(i).getPatientID();
			appointmentdata[2]=Appointments.getAppointmentList().get(i).getHospitalID();
			appointmentdata[3]=Appointments.getAppointmentList().get(i).getAppointmentDate();

			appointmentmodel.addRow(appointmentdata);

		}
		Object[] colDeletedAppointmentList=new Object[4];
		colDeletedAppointmentList[0]="ID";
		colDeletedAppointmentList[1]="PatientID";
		colDeletedAppointmentList[2]="HospitalID";
		colDeletedAppointmentList[3]="Appointment Date";
		deletedappointmentmodel.setColumnIdentifiers(colDeletedAppointmentList);
		deletedappointmentdata=new Object[4];
		for(int i=0;i<DeletedAppointments.getDeletedAppointmentList().size();i++) {
			deletedappointmentdata[0]=DeletedAppointments.getDeletedAppointmentList().get(i).getDeletedAppointmentID();
			deletedappointmentdata[1]=DeletedAppointments.getDeletedAppointmentList().get(i).getPatientID();
			deletedappointmentdata[2]=DeletedAppointments.getDeletedAppointmentList().get(i).getHospitalID();
			deletedappointmentdata[3]=DeletedAppointments.getDeletedAppointmentList().get(i).getAppointmentDate();
			deletedappointmentmodel.addRow(deletedappointmentdata);

		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 438);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);


		JLabel logged_doctor = new JLabel("Hoşgeldiniz Sayın "+doctor.getDoctorName());
		logged_doctor.setBounds(0, 10, 235, 44);
		contentPane.add(logged_doctor);
		
		JButton logged_doctorexitbtn = new JButton("Çıkış Yap");
		logged_doctorexitbtn.setBackground(new Color(255, 255, 255));
		logged_doctorexitbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		logged_doctorexitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		logged_doctorexitbtn.setBounds(485, 10, 121, 29);
		contentPane.add(logged_doctorexitbtn);
		
		JTabbedPane tabbedPane_ActiveAppoinments = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_ActiveAppoinments.setBackground(new Color(255, 255, 255));
		tabbedPane_ActiveAppoinments.setBounds(10, 88, 596, 282);
		contentPane.add(tabbedPane_ActiveAppoinments);
		
		JPanel panel_ActiveAppoinments = new JPanel();
		panel_ActiveAppoinments.setBackground(new Color(255, 255, 255));
		tabbedPane_ActiveAppoinments.addTab("Aktif Randevular", null, panel_ActiveAppoinments, null);
		panel_ActiveAppoinments.setLayout(null);
		
		JScrollPane scrollPane_ActiveAppoinments = new JScrollPane();
		scrollPane_ActiveAppoinments.setBounds(10, 10, 422, 235);
		panel_ActiveAppoinments.add(scrollPane_ActiveAppoinments);
		
	
		table_ActiveAppoinments = new JTable(appointmentmodel);
		scrollPane_ActiveAppoinments.setViewportView(table_ActiveAppoinments);
		
		JButton dscreen_appointmentdelete = new JButton("Randevu Sil");
		dscreen_appointmentdelete.setBackground(new Color(255, 255, 255));
		dscreen_appointmentdelete.setBounds(460, 88, 121, 29);
		panel_ActiveAppoinments.add(dscreen_appointmentdelete);
		dscreen_appointmentdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 Connection con = conn.connDB();
	                try {
	                	String query = "{call DeleteAppointment(?)}";
	                    PreparedStatement pst = con.prepareStatement(query);
	                 
	                    pst.setString(1, delete_RandevuID.getText());
	                
	                    int result = pst.executeUpdate();
	                    if(result>0) {
	                    	refreshTable();
	                        JOptionPane.showMessageDialog(null, "Randevu Başarıyla Silindi", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                    
	                        JOptionPane.showMessageDialog(null, "Randevu Silinemedi", "Hata", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e1.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });
		dscreen_appointmentdelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel = new JLabel("Randevu ID");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(460, 10, 121, 29);
		panel_ActiveAppoinments.add(lblNewLabel);
		
		delete_RandevuID = new JTextField();
		delete_RandevuID.setBounds(460, 49, 121, 29);
		panel_ActiveAppoinments.add(delete_RandevuID);
		delete_RandevuID.setColumns(10);
		
		JPanel panel_DeletedAppointments = new JPanel();
		panel_DeletedAppointments.setBackground(new Color(255, 255, 255));
		tabbedPane_ActiveAppoinments.addTab("İptal Edilen Randevular", null, panel_DeletedAppointments, null);
		panel_DeletedAppointments.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 422, 235);
		panel_DeletedAppointments.add(scrollPane_1);
		
		table = new JTable(deletedappointmentmodel);
		scrollPane_1.setViewportView(table);
		table_ActiveAppoinments.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				  int selectedRow = table_ActiveAppoinments.getSelectedRow();
			        if (selectedRow >= 0) 
			        { 
			            delete_RandevuID.setText(table_ActiveAppoinments.getValueAt(selectedRow, 0).toString());
			        }		
			 }
		});
		
	}
	 private void populateTableModel() throws SQLException {
	        ArrayList<Appointments> appointments = Appointments.getAppointmentList();
	        for (Appointments appointment : appointments) {
	            Object[] rowData = new Object[4];
	            rowData[0] = appointment.getAppointmentID();
	            rowData[1] = appointment.getPatientID();
	            rowData[2] = appointment.getHospitalID();
	            rowData[3] = appointment.getAppointmentDate();
	            appointmentmodel.addRow(rowData);	            
	            }
	        } 
	public	void refreshTable() throws SQLException {        
        appointmentmodel.setRowCount(0);
        populateTableModel();
    }
}
