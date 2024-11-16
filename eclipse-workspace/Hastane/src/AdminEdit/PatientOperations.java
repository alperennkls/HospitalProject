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
import user.Patient;

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

public class PatientOperations extends JFrame {
	 @SuppressWarnings("unused")
	private ListSelectionListener selectionListener;
	    @SuppressWarnings("unused")
		private TableModelListener tableModelListener;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_Patient;
	@SuppressWarnings("serial")
	private DefaultTableModel PatientModel=new DefaultTableModel() {
		 public boolean isCellEditable(int row, int column) {
             return isEditable; // Düzenlenebilirliği kontrol eder
         }
	};  
	private Object[] Patientdata=null; 
	Admin admin=new Admin();
	private JTextField add_PatientTC;
	private JTextField add_PatientName;
	private JTextField add_PatientSurname;
	private JTextField add_Password;
	private JTextField delete_PatientID;
    private static boolean isEditable = false; // Düzenlenebilirliği kontrol eden değişken

	 javaconnect conn=new javaconnect();
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientOperations frame = new PatientOperations();
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
	 
	public PatientOperations() throws SQLException {
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
		Object[] colPatientList=new Object[5];
		colPatientList[0]="ID";
		colPatientList[1]="TC";
		colPatientList[2]="PatientName";
		colPatientList[3]="PatientSurname";
		colPatientList[4]="PatientPw";
		PatientModel.setColumnIdentifiers(colPatientList);
		Patientdata=new Object[5];
		for(int i=0;i<admin.getPatientList().size();i++) {
			Patientdata[0]=admin.getPatientList().get(i).getPatientID();
			Patientdata[1]=admin.getPatientList().get(i).getPatientTC();
			Patientdata[2]=admin.getPatientList().get(i).getPatientName();
			Patientdata[3]=admin.getPatientList().get(i).getPatientSurname();
			Patientdata[4]=admin.getPatientList().get(i).getPatientPw();
			PatientModel.addRow(Patientdata);

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
		tabbedPane.setBounds(10, 75, 752, 488);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Hastalar", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 23, 529, 433);
		panel.add(scrollPane);
		
		
		

		scrollPane.setViewportView(table_Patient);
		
		table_Patient = new JTable(PatientModel);
		scrollPane.setViewportView(table_Patient);
		 
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(602, 220, 98, 32);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 
		
		JButton btnDoktorkar = new JButton("Çıkar");
		btnDoktorkar.setBackground(new Color(255, 255, 255));
		btnDoktorkar.setBounds(602, 332, 98, 32);
		panel.add(btnDoktorkar);
		btnDoktorkar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Connection con = conn.connDB();
				 if(delete_PatientID.getText().isEmpty())
             	{
                     JOptionPane.showMessageDialog(null, "PatientID yi Doldurunuz.", "Hata", JOptionPane.ERROR_MESSAGE);
             	}
				 else {
					
				
	                try {
	                	
	                	
	                	String query = "{call DeletePatient(?)}";
	                    PreparedStatement pst = con.prepareStatement(query);
	                 
	                    pst.setInt(1, Integer.parseInt(delete_PatientID.getText()));
	                
	                    int result = pst.executeUpdate();
	                    if(result>0) {
	                    	refreshTable();
	                        JOptionPane.showMessageDialog(null, "Hasta başarıyla Çıkartıldı.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Hasta Çıkartılamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e1.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
	                }
	            }}
	        });
		
			table_Patient.getModel().addTableModelListener(new TableModelListener() {			
			@Override
			public void tableChanged(TableModelEvent e) {
			    int selectedRow = table_Patient.getSelectedRow();

			    if (selectedRow == -1) {
			        return;
			    }

			    try {
			        int selectid = Integer.parseInt(table_Patient.getValueAt(selectedRow, 0).toString());
			        String selecttc = table_Patient.getValueAt(selectedRow, 1).toString();
			        String selectname = table_Patient.getValueAt(selectedRow, 2).toString();
			        String selectsurname = table_Patient.getValueAt(selectedRow, 3).toString();
			        String selectpatientpw = table_Patient.getValueAt(selectedRow, 4).toString();
			        Admin admin = new Admin();
			        admin.updatePatient(selectid, selecttc, selectname, selectsurname, selectpatientpw);			       
			    } catch (Exception ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
			    }
			}
			
		});
		btnDoktorkar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLabel = new JLabel("PatientTC");
		lblNewLabel.setBounds(570, 10, 167, 25);
		panel.add(lblNewLabel);
		
		add_PatientTC = new JTextField();
		add_PatientTC.setColumns(10);
		add_PatientTC.setBounds(570, 35, 167, 25);
		panel.add(add_PatientTC);
		
		JLabel lblNewLabel_1 = new JLabel("Patient Name");
		lblNewLabel_1.setBounds(570, 60, 167, 25);
		panel.add(lblNewLabel_1);
		
		add_PatientName = new JTextField();
		add_PatientName.setColumns(10);
		add_PatientName.setBounds(570, 85, 167, 25);
		panel.add(add_PatientName);
		
		JLabel lblNewLabel_2 = new JLabel("Patient Surname");
		lblNewLabel_2.setBounds(570, 110, 167, 25);
		panel.add(lblNewLabel_2);
		
		add_PatientSurname = new JTextField();
		add_PatientSurname.setColumns(10);
		add_PatientSurname.setBounds(570, 135, 167, 25);
		panel.add(add_PatientSurname);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setBounds(570, 160, 167, 25);
		panel.add(lblNewLabel_5);
		
		add_Password = new JTextField();
		add_Password.setColumns(10);
		add_Password.setBounds(570, 185, 167, 25);
		panel.add(add_Password);
		
		delete_PatientID = new JTextField();
		delete_PatientID.setColumns(10);
		delete_PatientID.setBounds(570, 297, 167, 25);
		panel.add(delete_PatientID);
		
		JLabel lblNewLabel_4_1 = new JLabel("PatientID");
		lblNewLabel_4_1.setBounds(570, 262, 167, 25);
		panel.add(lblNewLabel_4_1);
		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
                Connection con = conn.connDB();
                if(add_PatientName.getText().isEmpty()||add_PatientSurname.getText().isEmpty()||add_PatientTC.getText().isEmpty()||add_Password.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen Tüm Alanları Eksiksiz Doldurunuz.", "Hata", JOptionPane.ERROR_MESSAGE);

                }
                else {
                try {
                	String query = "{call AddPatient(?, ?, ?, ?)}";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, add_PatientTC.getText());
                    pst.setString(2, add_PatientName.getText());
                    pst.setString(3, add_PatientSurname.getText());
                    pst.setString(4, add_Password.getText()); 
                    int result = pst.executeUpdate();
                    if(result > 0) {
                        refreshTable();
                        JOptionPane.showMessageDialog(null, "Hasta başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
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
                ((DefaultTableModel) table_Patient.getModel()).fireTableDataChanged(); // Tabloyu güncelle
                editButton.setText(isEditable ? "Düzenlemeyi Bitir" : "Düzenle"); // Buton metnini güncelle
            }
        });
		editButton.setBounds(477, 10, 187, 32);
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
		btn_back.setBounds(10, 16, 121, 29);
		contentPane.add(btn_back);
		
		table_Patient.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				  int selectedRow = table_Patient.getSelectedRow();
			        if (selectedRow >= 0) 
			        { 
			            delete_PatientID.setText(table_Patient.getValueAt(selectedRow, 0).toString());
			        }		
			 }
		});
		 
	}
	 private void populateTableModel() throws SQLException {
	        ArrayList<Patient> Patients = admin.getPatientList();
	        for (Patient Patient : Patients) {
	            Object[] rowData = new Object[5]; // 6 columns
	            rowData[0] = Patient.getPatientID();
	            rowData[1] = Patient.getPatientTC();
	            rowData[2] = Patient.getPatientName();
	            rowData[3] = Patient.getPatientSurname();
	            rowData[4] = Patient.getPatientPw();
	            PatientModel.addRow(rowData);	            
	            }
	        }
	 public void refreshTable() throws SQLException {
	        table_Patient.clearSelection(); // Seçimi sıfırla
	        PatientModel.setRowCount(0);
	        populateTableModel();
	    }
}
