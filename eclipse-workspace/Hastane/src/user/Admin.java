package user;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.codejava.sql.*;

public class Admin{
	private javaconnect conn = new javaconnect();
	private static String BransID2;
	private static String HospitalID2;

	 
	public static String getHospitalID2() {
		return HospitalID2;
	}

	public static void setHospitalID2(String hospitalID2) {
		HospitalID2 = hospitalID2;
	}
	private static String AdminName;

	public static String getAdminName() {
		return AdminName;
	}

	public static void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public ArrayList<Patient> getPatientList() throws SQLException{
		ArrayList<Patient> list= new ArrayList<>();
		Patient obj;
		String sql = "SELECT * FROM Patients";
		Connection con = conn.connDB();
		try {
			PreparedStatement pst= con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new Patient(rs.getInt("PatientID"),rs.getString("PatientTC"),
						rs.getString("PatientName"),rs.getString("PatientSurname"),
						rs.getString("PatientPw"));
				list.add(obj);
			}
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Hospitals> getHospitalList() throws SQLException{
		ArrayList<Hospitals> list= new ArrayList<>();
		Hospitals obj;
		String sql = "SELECT * FROM Hospitals";
		Connection con = conn.connDB();
		try {
			PreparedStatement pst= con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new Hospitals(rs.getInt("HospitalID"),rs.getString("HospitalName"));
				list.add(obj);
			}
			  			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Branslar> getBransList() throws SQLException{
		ArrayList<Branslar> list= new ArrayList<>();
		Branslar obj;
		String sql = "SELECT * FROM Branslar";
		Connection con = conn.connDB();
		try {
			PreparedStatement pst= con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new Branslar(rs.getInt("BransID"),rs.getString("Name"));
				list.add(obj);
			}
			  
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Doctor> getDoctorList2() throws SQLException{
		ArrayList<Doctor> list= new ArrayList<>();
		Doctor obj;
		String sql = "SELECT DoctorID,DoctorName FROM Doctors WHERE BransID=? AND HospitalID=?";
		Connection con = conn.connDB();
		try {
			PreparedStatement pst= con.prepareStatement(sql);
			pst.setString(1, BransID2);
			pst.setString(2, HospitalID2);

			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new Doctor(rs.getInt("DoctorID"),rs.getString("DoctorName"));
				list.add(obj);
			}
			  
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public static String getBransID2() {
		return BransID2;
	}

	public static void setBransID2(String bransID2) {
		BransID2 = bransID2;
	}

	public void updateActiveAppointments(int appointmentid,String patientid,String bransid,String doctorid,String hospitalid,String activeappointmentdate,String activeappointmenttime) {
        Connection con = conn.connDB();
		String query = "exec updateAppointment ?,?,?,?,?,?,?";
        try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, appointmentid);
			pst.setString(2, patientid);
			pst.setString(3, bransid);
			pst.setString(4, doctorid);
			pst.setString(5, hospitalid);
			pst.setString(6, activeappointmentdate);
			pst.setString(7, activeappointmenttime);

            int result = pst.executeUpdate();
            if(result == 0) {
                JOptionPane.showMessageDialog(null, "Randevu güncellenemedi.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
             
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void updateDoctor(int doctorid,String doctortc,String doctorname,String doctorsurname,String bransid,String hastaneid,String doctorpw) {
        Connection con = conn.connDB();
		String query = "exec updatedoctor ?,?,?,?,?,?,?";
        try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, doctorid);
			pst.setString(2, doctortc);
			pst.setString(3, doctorname);
			pst.setString(4, doctorsurname);
			pst.setString(5, bransid);
			pst.setString(6, hastaneid);
			pst.setString(7, doctorpw);
 
            int result = pst.executeUpdate();
            if(result == 0) {
                JOptionPane.showMessageDialog(null, "Doktor güncellenemedi.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void updatePatient(int patientid,String patienttc,String patientname,String patientsurname,String patientpw) {
        Connection con = conn.connDB();
		String query = "exec updatepatient ?,?,?,?,?";
        try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, patientid);
			pst.setString(2, patienttc);
			pst.setString(3, patientname);
			pst.setString(4, patientsurname);
			pst.setString(5, patientpw);
 
            int result = pst.executeUpdate();
            if(result == 0) {
                JOptionPane.showMessageDialog(null, "Hasta güncellenemedi.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Doctor> getDoctorList() throws SQLException{
		ArrayList<Doctor> list= new ArrayList<>();
		String sql = "SELECT * FROM Doctors";
		Connection con = conn.connDB();
		try {
			PreparedStatement pst= con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{			
				list.add(new Doctor(rs.getInt("DoctorID"),rs.getString("DoctorTC"),
						rs.getString("DoctorName"),rs.getString("DoctorSurname"),
						rs.getString("BransID"),rs.getString("HospitalID"),rs.getString("DoctorPw")));
			}
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
		
}
   
