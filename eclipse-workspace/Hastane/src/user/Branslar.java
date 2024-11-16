package user;

public class Branslar {
private int BransID;
private String BransName;

public Branslar(int bransid, String bransname) {
	BransID=bransid;
	BransName=bransname;
}

	public Branslar() {
	// TODO Auto-generated constructor stub
}

	public int getBransID() {
	return BransID;
}

 
 
public void setBransID(int bransID) {
	BransID = bransID;
}



public String getBransName() {
	return BransName;
}



public void setBransName(String bransName) {
	BransName = bransName;
}

@Override
public String toString() {
    return BransName; // JComboBox'da görünen metin
}
	

}
