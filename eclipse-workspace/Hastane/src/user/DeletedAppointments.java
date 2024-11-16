package user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


import net.codejava.sql.javaconnect;

public class DeletedAppointments {
	private int DeletedAppointmentID;
	private String PatientID,DoctorID,HospitalID,BransID;
	private Date AppointmentDate;
	private Time AppointmentTime;
	private static int DoctorID2; 
	javaconnect conn = new javaconnect(); 
public DeletedAppointments() {}
	public DeletedAppointments(int deletedappointmentID,String patientID,String bransid, String doctorID, String hospitalID, Date appointmentDate,Time appointmentTime) {
	PatientID=patientID;
	DeletedAppointmentID = deletedappointmentID;
	BransID=bransid;
	DoctorID = doctorID;
	HospitalID = hospitalID;
	AppointmentDate = appointmentDate;
	AppointmentTime=appointmentTime;
}
	
	public DeletedAppointments(int deletedappointmentID,String patientID,String bransid, String hospitalID, Date appointmentDate,Time appointmentTime) {
		PatientID=patientID;
		DeletedAppointmentID = deletedappointmentID;
		HospitalID = hospitalID;
		BransID=bransid;
		AppointmentDate = appointmentDate;
		AppointmentTime=appointmentTime;
	}
	public String getBransID() {
		return BransID;
	}
	public void setBransID(String bransID) {
		BransID = bransID;
	}
	Doctor dc= new Doctor();
	public ArrayList<DeletedAppointments> getDeletedAppointmentList() throws SQLException{
		ArrayList<DeletedAppointments> list= new ArrayList<>();
		DeletedAppointments obj;
		String sql = "SELECT * FROM DeletedAppointments WHERE DoctorID=?";
		Connection con = conn.connDB();
		PreparedStatement pst= con.prepareStatement(sql);
		pst.setInt(1,DoctorID2);
		try {
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new DeletedAppointments(rs.getInt("DeletedAppointmentID"),
						rs.getString("PatientID"),rs.getString("BransID"),
						rs.getString("HospitalID"),rs.getDate("AppointmentDate"),rs.getTime("AppointmentTime")
						);
				list.add(obj);
			}
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<DeletedAppointments> getDeletedAppointmentList2() throws SQLException{
		ArrayList<DeletedAppointments> list2= new ArrayList<>();
		DeletedAppointments obj;
		String sql = "SELECT * FROM DeletedAppointments";
		Connection con = conn.connDB();
		PreparedStatement pst= con.prepareStatement(sql);
		try {
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				obj = new DeletedAppointments(rs.getInt("DeletedAppointmentID"),rs.getString("PatientID"),rs.getString("BransID"),
						rs.getString("DoctorID"),
						rs.getString("HospitalID"),rs.getDate("AppointmentDate"),rs.getTime("AppointmentTime")
						);
				list2.add(obj);
			}
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list2;
	}

	public Time getAppointmentTime() {
		return AppointmentTime;
	}
	public void setAppointmentTime(Time appointmentTime) {
		AppointmentTime = appointmentTime;
	}
	public static int getDoctorID2() {
		return DoctorID2;
	}
	public static void setDoctorID2(int doctorID2) {
		DoctorID2 = doctorID2;
	}
	public String getPatientID() {
		return PatientID;
	}
	public void setPatientID(String patientID) {
		PatientID = patientID;
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

	public int getDeletedAppointmentID() {
		return DeletedAppointmentID;
	}
	public void setDeletedAppointmentID(int deletedAppointmentID) {
		DeletedAppointmentID = deletedAppointmentID;
	}
	public void setAppointmentDate(Date appointmentDate) {
		AppointmentDate = appointmentDate;
	}

	
	
	
	
	
	
	
	
	
}
