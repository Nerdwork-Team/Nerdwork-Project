package application.api;

public class FLoginResponse{
	public boolean isSuccess;
	public String userId;
	public String displayName;
	public String username;
	public int accountType;//Για φοιτητες accountType=0, για καθηγητες accountType = 1
	public int associatedProfessorId; 
	public int orientation; //Για ΕΤΥ = 0, Για ΠΣ = 1
	
	public FLoginResponse(boolean success) {
		isSuccess = success;
	}
	
	public FLoginResponse(boolean success, String id, String Name, String username, int accountType, int associatedProfessorId, int orientation) {
		isSuccess = success;
		userId = id;
		displayName = Name;
		this.username = username;
		this.accountType = accountType;
		this.associatedProfessorId = associatedProfessorId;
		this.orientation = orientation;
	}
}
