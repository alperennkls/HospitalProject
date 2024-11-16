package AdminEdit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Screens.AdminScreen;
import net.codejava.sql.javaconnect;
import user.Admin;
import user.Appointments;
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

public class AppointmentOperations extends JFrame {
	 @SuppressWarnings("unused")
	private ListSelectionListener selectionListener;
	 @SuppressWarnings("unused")
	private TableModelListener tableModelListener;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_ActiveAppoinments;
	@SuppressWarnings("serial")
	private DefaultTableModel appointmentmodel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return isEditable; // Düzenlenebilirliği kontrol eder
		}
	};  
	private Object[] appointmentdata = null;
	private Object[] deletedappointmentdata = null;
	@SuppressWarnings("serial")
	private DefaultTableModel deletedappointmentmodel = new DefaultTableModel() {
		
	};
	
	
	DeletedAppointments DeletedAppointments = new DeletedAppointments();
	Appointments Appointments = new Appointments();
	private JTable table_deletedAppointments;
	private static boolean isEditable = false; // Düzenlenebilirliği kontrol eden değişken

	javaconnect conn = new javaconnect();
	private JTextField delete_RandevuID;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentOperations frame = new AppointmentOperations();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

	public AppointmentOperations() throws SQLException {
		setTitle("Randevu İşlemleri");
		setBackground(new Color(255, 255, 255));
		selectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Seçim değişikliklerini ele almak için kod buraya gelecek.
            }
        };

        tableModelListener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Tablo değişikliklerini ele almak için kod buraya gelecek.
            }
        };
		Object[] colAppointmentList = new Object[7];
		colAppointmentList[0] = "ID";
		colAppointmentList[1] = "PatientID";
		colAppointmentList[2] = "BransID";
		colAppointmentList[3] = "DoctorID";
		colAppointmentList[4] = "HospitalID";
		colAppointmentList[5] = "Appointment Date";
		colAppointmentList[6] = "Appointment Time";
		appointmentmodel.setColumnIdentifiers(colAppointmentList);
		appointmentdata = new Object[7];
		for (int i = 0; i < Appointments.getAppointmentList2().size(); i++) {
			appointmentdata[0] = Appointments.getAppointmentList2().get(i).getAppointmentID();
			appointmentdata[1] = Appointments.getAppointmentList2().get(i).getPatientID();
			appointmentdata[2] = Appointments.getAppointmentList2().get(i).getBransID();
			appointmentdata[3] = Appointments.getAppointmentList2().get(i).getDoctorID();
			appointmentdata[4] = Appointments.getAppointmentList2().get(i).getHospitalID();
			appointmentdata[5] = Appointments.getAppointmentList2().get(i).getAppointmentDate();
			appointmentdata[6] = Appointments.getAppointmentList2().get(i).getAppointmentTime();
			appointmentmodel.addRow(appointmentdata);
		}
		Object[] colDeletedAppointmentList = new Object[7];
		colDeletedAppointmentList[0] = "ID";
		colDeletedAppointmentList[1] = "PatientID";
		colDeletedAppointmentList[2] = "BransID";
		colDeletedAppointmentList[3] = "DoctorID";
		colDeletedAppointmentList[4] = "HospitalID";
		colDeletedAppointmentList[5] = "Appointment Date";
		colDeletedAppointmentList[6] = "Appointment Time";
		deletedappointmentmodel.setColumnIdentifiers(colDeletedAppointmentList);
		deletedappointmentdata = new Object[7];
		for (int i = 0; i < DeletedAppointments.getDeletedAppointmentList2().size(); i++) {
			deletedappointmentdata[0] = DeletedAppointments.getDeletedAppointmentList2().get(i).getDeletedAppointmentID();
			deletedappointmentdata[1] = DeletedAppointments.getDeletedAppointmentList2().get(i).getPatientID();
			deletedappointmentdata[2] = DeletedAppointments.getDeletedAppointmentList2().get(i).getBransID();
			deletedappointmentdata[3] = DeletedAppointments.getDeletedAppointmentList2().get(i).getDoctorID();
			deletedappointmentdata[4] = DeletedAppointments.getDeletedAppointmentList2().get(i).getHospitalID();
			deletedappointmentdata[5] = DeletedAppointments.getDeletedAppointmentList2().get(i).getAppointmentDate();
			deletedappointmentdata[6] = DeletedAppointments.getDeletedAppointmentList2().get(i).getAppointmentTime();
			deletedappointmentmodel.addRow(deletedappointmentdata);

		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 523);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton logged_doctorexitbtn = new JButton("Çıkış Yap");
		logged_doctorexitbtn.setBackground(new Color(255, 255, 255));
		logged_doctorexitbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		logged_doctorexitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		logged_doctorexitbtn.setBounds(612, 8, 121, 29);
		contentPane.add(logged_doctorexitbtn);

		JTabbedPane tabbedPane_ActiveAppoinments = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_ActiveAppoinments.setBackground(new Color(255, 255, 255));
		tabbedPane_ActiveAppoinments.setBounds(10, 88, 723, 388);
		contentPane.add(tabbedPane_ActiveAppoinments);

		JPanel panel_ActiveAppoinments = new JPanel();
		panel_ActiveAppoinments.setBackground(new Color(255, 255, 255));
		tabbedPane_ActiveAppoinments.addTab("Aktif Randevular", null, panel_ActiveAppoinments, null);
		panel_ActiveAppoinments.setLayout(null);

		JScrollPane scrollPane_ActiveAppoinments = new JScrollPane();
		scrollPane_ActiveAppoinments.setBounds(10, 10, 567, 341);
		panel_ActiveAppoinments.add(scrollPane_ActiveAppoinments);

		table_ActiveAppoinments = new JTable(appointmentmodel);
		scrollPane_ActiveAppoinments.setViewportView(table_ActiveAppoinments);

		JButton dscreen_appointmentdelete = new JButton("Randevu Sil");
		dscreen_appointmentdelete.setBackground(new Color(255, 255, 255));
		dscreen_appointmentdelete.setBounds(587, 88, 121, 29);
		panel_ActiveAppoinments.add(dscreen_appointmentdelete);
		dscreen_appointmentdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(delete_RandevuID.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null, "Bir Randevu Seçmediniz", "Hata", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Connection con = conn.connDB();
				try {
					String query = "{call DeleteAppointment(?)}";
					PreparedStatement pst = con.prepareStatement(query);

					pst.setString(1, delete_RandevuID.getText());

					int result = pst.executeUpdate();
					if (result > 0) {
						refreshTable();
						table_ActiveAppoinments.clearSelection(); 
						JOptionPane.showMessageDialog(null, "Randevu Başarıyla Silindi", "Başarılı",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Randevu Silinemedi", "Hata", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e1.getMessage(), "Hata",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		});
		table_ActiveAppoinments.getModel().addTableModelListener(new TableModelListener() {
		    @Override
		    public void tableChanged(TableModelEvent e) {
		        int selectedRow = table_ActiveAppoinments.getSelectedRow();

		        if (selectedRow == -1 || selectedRow >= table_ActiveAppoinments.getRowCount()) {
		            return; // Geçersiz seçim varsa işlemi sonlandır
		        }

		        try {
		            int selectid = Integer.parseInt(table_ActiveAppoinments.getValueAt(selectedRow, 0).toString());
		            String selectpatientid = table_ActiveAppoinments.getValueAt(selectedRow, 1).toString();
		            String selectbransid = table_ActiveAppoinments.getValueAt(selectedRow, 2).toString();
		            String selectdoctorid = table_ActiveAppoinments.getValueAt(selectedRow, 3).toString();
		            String selecthospitalid = table_ActiveAppoinments.getValueAt(selectedRow,4).toString();
		            String selectactiveappointmentdate = table_ActiveAppoinments.getValueAt(selectedRow, 5).toString();
		            String selectactiveappointmenttime = table_ActiveAppoinments.getValueAt(selectedRow, 6).toString();

		            Admin admin = new Admin();
		            admin.updateActiveAppointments(selectid, selectpatientid, selectbransid,selectdoctorid, selecthospitalid,
		                    selectactiveappointmentdate,selectactiveappointmenttime);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + ex.getMessage(), "Hata",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		


		dscreen_appointmentdelete.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel = new JLabel("Randevu ID");
		lblNewLabel.setBounds(587, 10, 121, 29);
		panel_ActiveAppoinments.add(lblNewLabel);

		delete_RandevuID = new JTextField();
		delete_RandevuID.setBounds(587, 49, 121, 29);
		panel_ActiveAppoinments.add(delete_RandevuID);
		delete_RandevuID.setColumns(10);

		JPanel panel_DeletedAppointments = new JPanel();
		panel_DeletedAppointments.setBackground(new Color(255, 255, 255));
		tabbedPane_ActiveAppoinments.addTab("İptal Edilen Randevular", null, panel_DeletedAppointments, null);
		panel_DeletedAppointments.setLayout(null);

		JScrollPane scrollPane_deleteAppointments = new JScrollPane();
		scrollPane_deleteAppointments.setBounds(10, 10, 567, 341);
		panel_DeletedAppointments.add(scrollPane_deleteAppointments);

		table_deletedAppointments = new JTable(deletedappointmentmodel);
		scrollPane_deleteAppointments.setViewportView(table_deletedAppointments);

		JButton editButton = new JButton("Düzenle");
		editButton.setBackground(new Color(255, 255, 255));
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isEditable = !isEditable; // Düzenlenebilirliği değiştir
				((DefaultTableModel) table_ActiveAppoinments.getModel()).fireTableDataChanged(); // Tabloyu güncelle
				// Tabloyu güncelle
				editButton.setText(isEditable ? "Düzenlemeyi Bitir" : "Düzenle"); // Buton metnini güncelle
			}
		});
		editButton.setBounds(446, 10, 156, 29);
		contentPane.add(editButton);
		
		JButton btn_back = new JButton("<---");
		btn_back.setBackground(new Color(255, 255, 255));
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminScreen asc = new AdminScreen();
				asc.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_back.setBounds(10, 14, 121, 29);
		contentPane.add(btn_back);
		table_ActiveAppoinments.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table_ActiveAppoinments.getSelectedRow();
				if (selectedRow >= 0) {
					delete_RandevuID.setText(table_ActiveAppoinments.getValueAt(selectedRow, 0).toString());
				}
			}
		});

	}

	private void populateTableModel() throws SQLException {
		ArrayList<Appointments> appointments = Appointments.getAppointmentList2();
		for (Appointments appointment : appointments) {
			Object[] rowData = new Object[7];
			rowData[0] = appointment.getAppointmentID();
			rowData[1] = appointment.getPatientID();
			rowData[2] = appointment.getBransID();
			rowData[3] = appointment.getDoctorID();
			rowData[4] = appointment.getHospitalID();
			rowData[5] = appointment.getAppointmentDate();
			rowData[6] = appointment.getAppointmentTime();

			appointmentmodel.addRow(rowData);
		}
	}
	private void populateTableModel2() throws SQLException {
		ArrayList<DeletedAppointments> deletedAppointments = DeletedAppointments.getDeletedAppointmentList2();
		for (DeletedAppointments appointment : deletedAppointments) {
			Object[] rowData = new Object[7];
			rowData[0] = appointment.getDeletedAppointmentID();
			rowData[1] = appointment.getPatientID();
			rowData[2] = appointment.getBransID();
			rowData[3] = appointment.getDoctorID();
			rowData[4] = appointment.getHospitalID();
			rowData[5] = appointment.getAppointmentDate();
			rowData[6] = appointment.getAppointmentTime();

			deletedappointmentmodel.addRow(rowData);
		}
	}

	public void refreshTable() throws SQLException {
	    appointmentmodel.setRowCount(0); // Tabloyu temizle
	    deletedappointmentmodel.setRowCount(0);
	    table_ActiveAppoinments.clearSelection(); // Seçimi sıfırla
	    table_deletedAppointments.clearSelection(); // Seçimi sıfırla
	    populateTableModel(); // Tabloyu yeniden doldur
	    populateTableModel2();
	}
}
