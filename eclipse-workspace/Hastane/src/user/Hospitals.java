package user;


public class Hospitals {
	private int HospitalID;
	private String HospitalName;
	public Hospitals(int hospitalid, String hospitalname) {
		HospitalID=hospitalid;
		HospitalName=hospitalname;
	}
	
	public int getHospitalID() {
		return HospitalID;
	}

	public void setHospitalID(int hospitalID) {
		HospitalID = hospitalID;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return HospitalName;
	}
	
	

}
