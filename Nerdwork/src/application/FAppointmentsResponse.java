/*
 * Class used in order to provide temporary storage
 * for information extracted from the database regarding
 * student appointments with professors. After that information
 * are passed to the Timeslot class???
 */

package application;

public class FAppointmentsResponse {
	public int id;
	public String studentId;
	public int professorId;
	public int dateTimestamp;
	public int status; //0 = Not Confirmed, 1 = Confirmed, 2 = Cancelled
	public String created_at;
	
	public FAppointmentsResponse(int id, String studentId, int professorId, int dateTimestamp, int status, String created_at) {
		this.id = id;
		this.studentId = studentId;
		this.professorId = professorId;
		this.dateTimestamp = dateTimestamp;
		this.status = status;
		this.created_at = created_at;
	}
}