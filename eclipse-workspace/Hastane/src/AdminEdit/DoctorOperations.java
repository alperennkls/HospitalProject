package AdminEdit;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
import user.Doctor;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DoctorOperations extends JFrame {
	 private ListSelectionListener selectionListener;
	 private TableModelListener tableModelListener;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_Doctor;
    private static boolean isEditable = false; // Düzenlenebilirliği kontrol eden değişken
 
	@SuppressWarnings("serial")
	private DefaultTableModel doctorModel=new DefaultTableModel() {
		  public boolean isCellEditable(int row, int column) {
              return isEditable; // Düzenlenebilirliği kontrol eder
          }
	}; 
	
	private Object[] doctordata=null; 
	Admin admin=new Admin();
	private JTextField add_DoctorTC;
	private JTextField add_DoctorName;
	private JTextField add_DoctorSurname;
	private JTextField add_BransID;
	private JTextField add_HospitalID;
	private JTextField add_Password;
	private JTextField delete_DoctorID;
	 javaconnect conn=new javaconnect();
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorOperations frame = new DoctorOperations();
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
	 
	public DoctorOperations() throws SQLException {
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
		Object[] colDoctorList=new Object[7];
		colDoctorList[0]="ID";
		colDoctorList[1]="TC";
		colDoctorList[2]="DoctorName";
		colDoctorList[3]="DoctorSurname";
		colDoctorList[4]="BransID";
		colDoctorList[5]="HastaneID";
		colDoctorList[6]="DoctorPw";

		doctorModel.setColumnIdentifiers(colDoctorList);
		doctordata=new Object[7];
		for(int i=0;i<admin.getDoctorList().size();i++) {
			doctordata[0]=admin.getDoctorList().get(i).getDoctorID();
			doctordata[1]=admin.getDoctorList().get(i).getDoctorTC();
			doctordata[2]=admin.getDoctorList().get(i).getDoctorName();
			doctordata[3]=admin.getDoctorList().get(i).getDoctorSurname();
			doctordata[4]=admin.getDoctorList().get(i).getBransID();
			doctordata[5]=admin.getDoctorList().get(i).getHospitalID();
			doctordata[6]=admin.getDoctorList().get(i).getDoctorPw();

			doctorModel.addRow(doctordata);

		}
		setTitle("Doktor İşlemleri");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 610);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(10, 70, 752, 493);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Admin Yönetimi", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 23, 529, 433);
		panel.add(scrollPane);
		
		
		

		scrollPane.setViewportView(table_Doctor);
		
		table_Doctor = new JTable(doctorModel);
		scrollPane.setViewportView(table_Doctor);
		
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(602, 320, 98, 32);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnDoktorkar = new JButton("Çıkar");
		btnDoktorkar.setBackground(new Color(255, 255, 255));
		btnDoktorkar.setBounds(602, 427, 98, 32);
		panel.add(btnDoktorkar);
		btnDoktorkar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Connection con = conn.connDB();
				 if(delete_DoctorID.getText().isEmpty())
             	{
                     JOptionPane.showMessageDialog(null, "DoctorID yi Doldurunuz.", "Hata", JOptionPane.ERROR_MESSAGE);
             	}
				 else {					
	                try {
	                		                	
	                	String query = "{call DeleteDoctor(?)}";
	                    PreparedStatement pst = con.prepareStatement(query);
	                 
	                    pst.setInt(1, Integer.parseInt(delete_DoctorID.getText()));
	                
	                    int result = pst.executeUpdate();
	                    if(result>0) {
	                    	 // Dinleyicileri devre dışı bırak
	                        table_Doctor.getSelectionModel().removeListSelectionListener(selectionListener);
	                        doctorModel.removeTableModelListener(tableModelListener);

	                        refreshTable(); // Tabloyu güncelle
	                        table_Doctor.clearSelection(); // Seçimi sıfırla
	                        
	                        // Dinleyicileri tekrar etkinleştir
	                        table_Doctor.getSelectionModel().addListSelectionListener(selectionListener);
	                        doctorModel.addTableModelListener(tableModelListener);

	                        JOptionPane.showMessageDialog(null, "Doktor başarıyla Çıkartıldı.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Doktor Çıkartılamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e1.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	                }
	            }}
	        });
		btnDoktorkar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLabel = new JLabel("DoctorTC");
		lblNewLabel.setBounds(570, 10, 167, 25);
		panel.add(lblNewLabel);
		
		add_DoctorTC = new JTextField();
		add_DoctorTC.setColumns(10);
		add_DoctorTC.setBounds(570, 35, 167, 25);
		panel.add(add_DoctorTC);
		
		JLabel lblNewLabel_1 = new JLabel("Doctor Name");
		lblNewLabel_1.setBounds(570, 60, 167, 25);
		panel.add(lblNewLabel_1);
		
		add_DoctorName = new JTextField();
		add_DoctorName.setColumns(10);
		add_DoctorName.setBounds(570, 85, 167, 25);
		panel.add(add_DoctorName);
		
		JLabel lblNewLabel_2 = new JLabel("Doctor Surname");
		lblNewLabel_2.setBounds(570, 110, 167, 25);
		panel.add(lblNewLabel_2);
		
		add_DoctorSurname = new JTextField();
		add_DoctorSurname.setColumns(10);
		add_DoctorSurname.setBounds(570, 135, 167, 25);
		panel.add(add_DoctorSurname);
		
		JLabel lblNewLabel_3 = new JLabel("BransID");
		lblNewLabel_3.setBounds(570, 160, 167, 25);
		panel.add(lblNewLabel_3);
		
		add_BransID = new JTextField();
		add_BransID.setColumns(10);
		add_BransID.setBounds(570, 185, 167, 25);
		panel.add(add_BransID);
		
		JLabel lblNewLabel_4 = new JLabel("HospitalID");
		lblNewLabel_4.setBounds(570, 210, 167, 25);
		panel.add(lblNewLabel_4);
		
		add_HospitalID = new JTextField();
		add_HospitalID.setColumns(10);
		add_HospitalID.setBounds(570, 235, 167, 25);
		panel.add(add_HospitalID);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setBounds(570, 260, 167, 25);
		panel.add(lblNewLabel_5);
		
		add_Password = new JTextField();
		add_Password.setColumns(10);
		add_Password.setBounds(570, 285, 167, 25);
		panel.add(add_Password);
		
		delete_DoctorID = new JTextField();
		delete_DoctorID.setColumns(10);
		delete_DoctorID.setBounds(570, 392, 167, 25);
		panel.add(delete_DoctorID);
		
		JLabel lblNewLabel_4_1 = new JLabel("DoctorID");
		lblNewLabel_4_1.setBounds(570, 362, 167, 25);
		panel.add(lblNewLabel_4_1);
		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
                Connection con = conn.connDB();
                if(add_BransID.getText().isEmpty()||add_DoctorName.getText().isEmpty()||add_DoctorSurname.getText().isEmpty()||add_DoctorTC.getText().isEmpty()||add_HospitalID.getText().isEmpty()||add_Password.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen Tüm Alanları Eksiksiz Doldurunuz.", "Hata", JOptionPane.ERROR_MESSAGE);

                }
                else {
                try {
                	String query = "{call AddDoctor(?, ?, ?, ?, ?, ?)}";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, add_DoctorTC.getText());
                    pst.setString(2, add_DoctorName.getText());
                    pst.setString(3, add_DoctorSurname.getText());
                    pst.setString(4, add_BransID.getText());
                    pst.setString(5, add_HospitalID.getText());
                    pst.setString(6, add_Password.getText()); 
                    int result = pst.executeUpdate();
                    if(result > 0) {
                        refreshTable();
                        
                        JOptionPane.showMessageDialog(null, "Doktor başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Doktor eklenemedi.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e1.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
		 }
        });
		
		JButton btnNewButton_1 = new JButton("Çıkış Yap");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(702, 10, 91, 32);
		contentPane.add(btnNewButton_1);
		
		 JButton editButton = new JButton("Düzenle");
		 editButton.setBackground(new Color(255, 255, 255));
         editButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 isEditable = !isEditable; // Düzenlenebilirliği değiştir
                 ((DefaultTableModel) table_Doctor.getModel()).fireTableDataChanged(); // Tabloyu güncelle
                 editButton.setText(isEditable ? "Düzenlemeyi Bitir" : "Düzenle"); // Buton metnini güncelle
             }
         });
		editButton.setBounds(455, 10, 200, 32);
		contentPane.add(editButton);
		
		JButton btnNewButton_2 = new JButton("<---");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminScreen asc = new AdminScreen();
				asc.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(10, 10, 126, 32);
		contentPane.add(btnNewButton_2);
		
		table_Doctor.getModel().addTableModelListener(new TableModelListener() {	
			@Override
			public void tableChanged(TableModelEvent e) {
			    int selectedRow = table_Doctor.getSelectedRow();

			    if (selectedRow == -1) {
			        return; // Eğer geçerli bir satır seçilmemişse işlemi sonlandır
			    }

			    try {
			        // Seçilen satırdaki tüm sütunların verilerini doğru şekilde alıyoruz
			        int selectid = Integer.parseInt(table_Doctor.getValueAt(selectedRow, 0).toString());
			        String selecttc = table_Doctor.getValueAt(selectedRow, 1).toString();
			        String selectname = table_Doctor.getValueAt(selectedRow, 2).toString();
			        String selectsurname = table_Doctor.getValueAt(selectedRow, 3).toString();
			        String selectbransid = table_Doctor.getValueAt(selectedRow, 4).toString();
			        String selecthospitalid = table_Doctor.getValueAt(selectedRow, 5).toString();
			        String selectdoctorpw = table_Doctor.getValueAt(selectedRow, 6).toString();

			        // Doktor bilgilerini güncelleme
			        admin.updateDoctor(selectid, selecttc, selectname, selectsurname, selectbransid, selecthospitalid, selectdoctorpw);

			    } catch (Exception ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
			    }
			}

			
		});
		
		table_Doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		
			@Override
			public void valueChanged(ListSelectionEvent e) {
				  int selectedRow = table_Doctor.getSelectedRow();
			        if (selectedRow >= 0) 
			        { 
			            delete_DoctorID.setText(table_Doctor.getValueAt(selectedRow, 0).toString());
			        }		
			 }
		});
		 
	}
	 private void populateTableModel() throws SQLException {
	        ArrayList<Doctor> doctors = admin.getDoctorList();
	        for (Doctor doctor : doctors) {
	            Object[] rowData = new Object[7]; // 6 columns
	            rowData[0] = doctor.getDoctorID();
	            rowData[1] = doctor.getDoctorTC();
	            rowData[2] = doctor.getDoctorName();
	            rowData[3] = doctor.getDoctorSurname();
	            rowData[4] = doctor.getBransID();
	            rowData[5] = doctor.getHospitalID();
	            rowData[6] = doctor.getDoctorPw();

	            doctorModel.addRow(rowData);	            
	            }
	        }
	 public void refreshTable() throws SQLException {
	        table_Doctor.clearSelection(); // Seçimi sıfırla
	        doctorModel.setRowCount(0);
	        populateTableModel();
	    }
}
