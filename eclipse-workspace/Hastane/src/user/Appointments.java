package user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


import net.codejava.sql.javaconnect;

public class Appointments {
	private int AppointmentID;
	private String PatientID,DoctorID,HospitalID,BransID,DoctorName,DoctorSurname,Brans,Hospital;
	private Date AppointmentDate;
	private Time AppointmentTime;
	private static int DoctorID2;
	private static String AppointmentDate2;
	private static String PatientID2;


	public static String getPatientID2() {
		return PatientID2;
	}
	public static void setPatientID2(String patientID2) {
		PatientID2 = patientID2;
	}


	javaconnect conn = new javaconnect();
public Appointments() {}
	public Appointments(int appointmentID,String patientID,String bransid, String doctorID, String hospitalID, Date appointmentDate,Time appointmenttime) {
	PatientID=patientID;
	AppointmentID = appointmentID;
	DoctorID = doctorID;
	HospitalID = hospitalID;
	AppointmentDate = appointmentDate;
	AppointmentTime = appointmenttime;
	BransID=bransid;
}  
	
	public Appointments(int appointmentID,String patientID,String bransid, String hospitalID, Date appointmentDate,Time appointmentTime) {
		PatientID=patientID;
		AppointmentID = appointmentID;
		HospitalID = hospitalID;
		AppointmentDate = appointmentDate;
		AppointmentTime=appointmentTime;
		BransID=bransid;

	}

	
	public String getBransID() {
		return BransID;
	}
	public void setBransID(String bransID) {
		BransID = bransID;
	}
	public Time getAppointmentTime() {
		return AppointmentTime;
	}
	public Appointments(Time appointmenttime) {
		AppointmentTime=appointmenttime;
	}
	public void setAppointmentTime(Time appointmentTime) {
		AppointmentTime = appointmentTime;
	}
	public String getdoctorname(String doctorid) throws SQLException {
		Connection con = conn.connDB();
		String sql = "SELECT DoctorName FROM Doctors WHERE DoctorID=?";
		PreparedStatement pst= con.prepareStatement(sql);
		pst.setString(1, doctorid);
		ResultSet rs=pst.executeQuery();
		 String doctorname = null;
		    if (rs.next()) {
		        doctorname = rs.getString("DoctorName");
		    }
		    rs.close();
		    pst.close();
		    con.close();
		return doctorname;				
	}
	public String getdoctorsurname(String doctorid) throws SQLException {
		Connection con = conn.connDB();
		String sql = "SELECT DoctorSurname FROM Doctors WHERE DoctorID=?";
		PreparedStatement pst= con.prepareStatement(sql);
		pst.setString(1, doctorid);
		ResultSet rs=pst.executeQuery();
		 String doctorsurname = null;
		    if (rs.next()) {
		    	doctorsurname = rs.getString("DoctorSurname");
		    }
		    
		    rs.close();
		    pst.close();
		    con.close();

		
		return doctorsurname;				
	}
	public String getBrans(String bransid) throws SQLException {
		Connection con = conn.connDB();
		String sql = "SELECT Name FROM Branslar WHERE BransID=?";
		PreparedStatement pst= con.prepareStatement(sql);
		pst.setString(1, bransid);
		ResultSet rs=pst.executeQuery();
		String bransname = null;
	    if (rs.next()) {
	    	bransname = rs.getString("Name");
	    }
	    
	    rs.close();
	    pst.close();
	    con.close();

	
	return bransname;			
	}
	public String getHospital(String hospitalid) throws SQLException {
		Connection con = conn.connDB();
		String sql = "SELECT HospitalName FROM Hospitals WHERE HospitalID=?";
		PreparedStatement pst= con.prepareStatement(sql);
		pst.setString(1, hospitalid);
		ResultSet rs=pst.executeQuery();
		String hospitalname = null;
	    if (rs.next()) {
	    	hospitalname = rs.getString("HospitalName");
	    }
	    
	    rs.close();
	    pst.close();
	    con.close();
		return hospitalname;				
	}
	
Appointments(String doctorname,String doctorsurname,String brans,String hospital,Date appointmentdate,Time appointmenttime){
		DoctorName=doctorname;
		DoctorSurname=doctorsurname;
		Brans=brans;
		Hospital=hospital;
		AppointmentDate=appointmentdate;
		AppointmentTime=appointmenttime;
	}
	public String getDoctorName() {
	return DoctorName;
}
public void setDoctorName(String doctorName) {
	DoctorName = doctorName;
}
public String getDoctorSurname() {
	return DoctorSurname;
}
public void setDoctorSurname(String doctorSurname) {
	DoctorSurname = doctorSurname;
}
public String getBrans() {
	return Brans;
}
public void setBrans(String brans) {
	Brans = brans;
}
public String getHospital() {
	return Hospital;
}
public void setHospital(String hospital) {
	Hospital = hospital;
}
	public ArrayList<Appointments> getAppointmentList3() throws SQLException{
		ArrayList<Appointments> list= new ArrayList<>();
		Appointments obj;		
		Connection con = conn.connDB();
		try {
			String sql = "SELECT * FROM Appointments WHERE PatientID=?";
			PreparedStatement pst= con.prepareStatement(sql);
			pst.setString(1, PatientID2);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new Appointments(getdoctorname(rs.getString("DoctorID")),getdoctorsurname(rs.getString("DoctorID")),getBrans(rs.getString("BransID")),getHospital(rs.getString("HospitalID")),rs.getDate("AppointmentDate"),rs.getTime("AppointmentTime"));
				list.add(obj);
			}	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<Appointments> getAppointmentList() throws SQLException{
		ArrayList<Appointments> list= new ArrayList<>();
		Appointments obj;		
		Connection con = conn.connDB();
		try {
			String sql = "SELECT * FROM Appointments WHERE DoctorID=?";
			PreparedStatement pst= con.prepareStatement(sql);
			pst.setInt(1, DoctorID2);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new Appointments(rs.getInt("AppointmentID"),rs.getString("PatientID"),rs.getString("BransID"),rs.getString("HospitalID"),rs.getDate("AppointmentDate"),rs.getTime("AppointmentTime"));
				list.add(obj);
			}	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	Branslar branslar = new Branslar();
	
	
	
	
	public static int getDoctorID2() {
		return DoctorID2;
	}
	public static void setDoctorID2(int doctorID2) {
		DoctorID2 = doctorID2;
	} 
	
	public static String getAppointmentDate2() {
		return AppointmentDate2;
	}
	public static void setAppointmentDate2(String appointmentDate2) {
		AppointmentDate2 = appointmentDate2;
	}
	public ArrayList<Appointments> getAppointmentList2() throws SQLException{
		ArrayList<Appointments> list= new ArrayList<>();
		Appointments obj;
		String sql = "SELECT * FROM Appointments";
		Connection con = conn.connDB();
		try {
			PreparedStatement pst= con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new Appointments(rs.getInt("AppointmentID"),rs.getString("PatientID"),rs.getString("BransID"),
						rs.getString("DoctorID"),
						rs.getString("HospitalID"),rs.getDate("AppointmentDate"),rs.getTime("AppointmentTime")
						);
				list.add(obj);
			}
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getPatientID() {
		return PatientID;
	}
	public void setPatientID(String patientID) {
		PatientID = patientID;
	}
	public int getAppointmentID() {
		return AppointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		AppointmentID = appointmentID;
	}

	public String getDoctorID() {
		return DoctorID;
	}

	public void setDoctorID(String doctorID) {
		DoctorID = doctorID;
	}

	public String getHospitalID() {
		return HospitalID;
	}

	public void setHospitalID(String hospitalID) {
		HospitalID = hospitalID;
	}

	public Date getAppointmentDate() {
		return AppointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		AppointmentDate = appointmentDate;
	}

	
	public String toString() {
	    return String.valueOf(AppointmentTime); // JComboBox'da görünen metin
	}
	
	
	
	
	
	
	
}
