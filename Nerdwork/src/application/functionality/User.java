/*
 * Class User is the superclass of both Student and Professor
 * subclasses. It contains mostly methods, that are commonly
 * used for both.
 */

package application.functionality;

import java.util.ArrayList;

public abstract class User {
	
	/*User attributes are here*/

	protected String userId; // Unique database key of the object.
	protected String username;
	protected String email;
	protected String displayName;
	private String bio;

	/*
	 * The above five attributes keep track of personal Student/Professor,
	 * concerning the profile they have built.
	 */
	protected int orientation;
	public static final String Orientation[] = {"ΕΤΥ", "ΠΣ", "ΕΤΥ-ΠΣ"};
	
	protected ArrayList<Course> myCourses; // Courses attended by Students or taught by Professors	
	protected ArrayList<Timeslot> requestedAppointments;
	private ArrayList<Timeslot> reservedAppointments; 
	
	public User(String userId, String username, String displayName, int orientation, String bio) {
		this.userId = userId;
		this.username = username;
		this.displayName = displayName;
		this.orientation = orientation;
		this.bio = bio;
		myCourses = new ArrayList<>();
		requestedAppointments = new ArrayList<Timeslot>();
		reservedAppointments = new ArrayList<Timeslot>();
	}
	
	public String toString() {
		return userId;
	}
	
	protected void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public void addRequestedAppointment(Timeslot appointment) {
		if (!appointment.checkOutdated())
			requestedAppointments.add(appointment);
	}
	
	public void removeRequestedAppointment(Timeslot appointment) {
		requestedAppointments.remove(appointment);
	}
	
	public void clearRequestedAppointments() {
		requestedAppointments.clear();
	}
	
	public ArrayList<Timeslot> getRequestedAppointments(){
		return requestedAppointments;
	}
	
	public ArrayList<Timeslot> getReservedAppointments() {
		return reservedAppointments;
	}
	
	public void addReservedAppointment(Timeslot appointment) {
		if (!appointment.checkOutdated())
			reservedAppointments.add(appointment);
	}
	
	public void removeReservedAppointment(Timeslot appointment) {
		reservedAppointments.remove(appointment);
	}
	
	public void clearReservedAppointments() {
		reservedAppointments.clear();
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getOrientation() {
		return orientation;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public ArrayList<Course> getMyCourses(){
		return myCourses;
	}
	
	public void clearMyCourses() {
		myCourses.clear();
	}
	
	public void addCourse(Course course) {
		myCourses.add(course);
	}
	
	public void removeCourse(Course course) {
		myCourses.remove(course);
	}
}
