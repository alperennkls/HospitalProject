package user;


public class Patient {
	private String PatientName,PatientSurname,PatientTC,PatientPw,BransID,HospitalID;
	private int PatientID;
	 

	
	public Patient(int patientID,String patientTC,String patientName,String patientSurname, String patientPw) {
		PatientName = patientName;
		PatientSurname = patientSurname;
		PatientTC = patientTC;
		PatientPw = patientPw;
		PatientID = patientID;
	
	}
	public Patient() {}
	
 

	public String getPatientSurname() {
		return PatientSurname;
	}



	public void setPatientSurname(String patientSurname) {
		PatientSurname = patientSurname;
	}



	public String getPatientTC() {
		return PatientTC;
	}



	public void setPatientTC(String patientTC) {
		PatientTC = patientTC;
	}



	public String getPatientPw() {
		return PatientPw;
	}



	public void setPatientPw(String patientPw) {
		PatientPw = patientPw;
	}

	public int getPatientID() {
		return PatientID;
	}

	public void setPatientID(int patientID) {
		PatientID = patientID;
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
	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	
}
