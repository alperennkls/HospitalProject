package user;


public class Doctor {
	private String DoctorName,DoctorSurname,DoctorTC,DoctorPw,BransID,HospitalID;
	private int DoctorID;
	private int DoctorID2;
	private String DoctorName2;
		
	public int getDoctorID2() {
		return DoctorID2;
	}
	public void setDoctorID2(int doctorID2) {
		DoctorID2 = doctorID2;
	}
	public String getDoctorName2() {
		return DoctorName2;
	}
	public void setDoctorName2(String doctorName2) {
		DoctorName2 = doctorName2;
	}
	public Doctor(int doctorID,String doctorTC,String doctorName,String doctorSurname,String bransID,String hospitalID, String doctorPw) {
		DoctorName = doctorName;
		DoctorSurname = doctorSurname;
		DoctorTC = doctorTC;
		DoctorPw = doctorPw;
		DoctorID = doctorID;
		BransID = bransID;
		HospitalID = hospitalID;
	}
	public Doctor() {}
	
 

	public Doctor(int doctorID2, String doctorName2) {
		DoctorID2=doctorID2;
		DoctorName2=doctorName2;
	} 
	public String getDoctorSurname() {
		return DoctorSurname;
	}



	public void setDoctorSurname(String doctorSurname) {
		DoctorSurname = doctorSurname;
	}



	public String getDoctorTC() {
		return DoctorTC;
	}



	public void setDoctorTC(String doctorTC) {
		DoctorTC = doctorTC;
	}



	public String getDoctorPw() {
		return DoctorPw;
	}



	public void setDoctorPw(String doctorPw) {
		DoctorPw = doctorPw;
	}

	public int getDoctorID() {
		return DoctorID;
	}

	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
	}		
   
	public String getBransID() {
		return BransID;
	}
	public void setBransID(String bransID) {
		BransID = bransID;
	}
	public String getHospitalID() {
		return HospitalID;
	}
	public void setHospitalID(String hospitalID) {
		HospitalID = hospitalID;
	}
	public String getDoctorName() {
		return DoctorName;
	}

	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}

	public String toString() {
	    return DoctorName2; // JComboBox'da görünen metin
	}
	
}
