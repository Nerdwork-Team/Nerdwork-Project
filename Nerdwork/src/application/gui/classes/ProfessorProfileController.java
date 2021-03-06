package application.gui.classes;

import java.io.IOException;

import org.controlsfx.control.Rating;
import org.json.simple.parser.ParseException;

import application.functionality.Course;
import application.functionality.GuiController;
import application.functionality.Professor;
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

public class ProfessorProfileController {
	
	private Professor professor;
	private GuiController controller;
	
	@FXML
	private ImageView profilePicture;
	@FXML
	private Label name;
	@FXML
	private VBox courseList;
	@FXML
	private Label phoneNumber;
	@FXML
	private Label email;
	@FXML
	private Label office;
	@FXML
	private TextFlow description;
	@FXML
	private Rating rating;

	
	
	private void setProfileDescription() throws IOException, ParseException {
		
		//Professor profile picture
		Image img = new Image(GuiController.dbURL + professor.getProfilePhoto());
		profilePicture.setImage(img);
		
		
		//Professor Description
		name.setText(professor.getDisplayName());
		phoneNumber.setText(professor.getPhone());
		email.setText(professor.getEmail());
		office.setText("Γραφείο " + professor.getOffice());
		Text bio = new Text(professor.getBio());
		bio.setFont(new Font(28));
		description.getChildren().add(bio);
		
		
		//Disables the Rating component if the User has already rated the Professor
		rating.setRating(professor.getRating());
		if(controller.getMyProfessorRating(professor) != -1)
			rating.setDisable(true);	
		
		
		//Loads all Courses taught by the Professor
		courseList.getChildren().clear();
		for(Course c : professor.getCoursesTaught(controller.getAllCourses())) {
			ImageView picture = new ImageView();
			picture.setFitWidth(32);
			picture.setPreserveRatio(true);
			
			Label name = new Label(c.getName());
			name.setFont(new Font(18));
			
			HBox box = new HBox();
			box.getChildren().add(picture);
			box.getChildren().add(name);
			box.setId(c.getId());
			box.setStyle("-fx-cursor: hand");
			
			
			//Loads selected Course profile 
			box.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
				try {
					Course selectedCourse = c;
					new CourseProfileController().switchToCourseProfile(event, selectedCourse);
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
			});
			
			courseList.getChildren().add(box);
		}
	}
	
	
	
	public void rateProfessor() throws IOException, ParseException {
		controller.rateProfessor((int) rating.getRating(), professor);
		
		setProfileDescription();
	}
	
	
	public void load(Professor p) throws IOException, ParseException {
		this.professor = p;
		this.controller = GuiController.getInstance();
		
		setProfileDescription();
	}
	
	public void switchToProfessorProfile(Event event, Professor p) throws IOException, ParseException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.ProfessorProfile));
		Navigator.setCenter(loader);
		ProfessorProfileController professorProfileController = loader.getController();
		professorProfileController.load(p);
	}
}
