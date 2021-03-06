package application.gui.classes;

import java.io.IOException;

import org.controlsfx.control.Rating;
import org.json.simple.parser.ParseException;

import application.functionality.Course;
import application.functionality.GuiController;
import application.functionality.Professor;
import application.functionality.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CourseProfileController {
	
	private Course course;
	private GuiController controller;
	
	@FXML
	private ImageView profilePicture;
	@FXML
	private Label name;
	@FXML
	private VBox professorList;
	@FXML
	private Label semester;
	@FXML
	private Label orientation;
	@FXML
	private Label ects;
	@FXML
	private TextFlow description;
	@FXML
	private Rating rating;
	
	
	
	private void setProfileDescription() throws IOException, ParseException {
		
		//Course Description
		name.setText(course.getName());
		rating.setRating(course.getRating());
		semester.setText(Integer.toString(course.getSemester()) + "ο Εξάμηνο");;
		orientation.setText(User.Orientation[course.getOrientation()]);
		ects.setText(Integer.toString(course.getECTS()) + " ECTS");
		Text bio = new Text(""); //Sample Bio
		bio.setFont(new Font(32));
		description.getChildren().add(bio);
		
		
		//Disables the Rating component if the User has already rated the Course
		rating.setRating(course.getRating());
		if(controller.getMyCourseRating(course) != -1)
			rating.setDisable(true);	
		
		
		//Loads all Professors teaching the Course
		professorList.getChildren().clear();
		for(Professor p : course.getProfessors()) {
			Image img = new Image(GuiController.dbURL + p.getProfilePhoto());
			ImageView picture = new ImageView(img);
			
			picture.setFitWidth(28);
			picture.setPreserveRatio(true);
			
			Label name = new Label(p.getDisplayName());
			name.setFont(new Font(18));
			
			HBox box = new HBox();
			box.getChildren().add(picture);
			box.getChildren().add(name);
			box.setStyle("-fx-cursor: hand");

			
			//Loads selected Professor profile
			box.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
				try {
					Professor selectedProfessor = p;
					new ProfessorProfileController().switchToProfessorProfile(event, selectedProfessor);
				} catch (NumberFormatException | IOException | ParseException e) {
					e.printStackTrace();
				}
			});
			
			professorList.getChildren().add(box);
		}
	}
	
	
	
	public void rateCourse() throws IOException, ParseException {
		controller.rateCourse((int) rating.getRating(), course);
		
		setProfileDescription();
	}
	
	
	public void load(Course c) throws IOException, ParseException {
		this.course = c;
		this.controller = GuiController.getInstance();
		
		setProfileDescription();
	}
	
	
	public void switchToCourseProfile(Event event, Course c) throws IOException, ParseException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.CourseProfile));
		Navigator.setCenter(loader);
		CourseProfileController courseProfileController = loader.getController();
		courseProfileController.load(c);
	}
}
