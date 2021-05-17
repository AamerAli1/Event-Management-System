package mainApplication.createEvent;

import authentication.homePage.Launcher;
import data.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;


public class CreateEventController {


    private Stage stage;
    private Scene scene;


    @FXML
    TextField txtName;
    @FXML
    TextField txtLocation;
    @FXML
    DatePicker datefield;
    @FXML
    TextField txtPerformer;
    @FXML
    TextField txtMaxAttendees;


    public void createEvent(ActionEvent event){
        String name = txtName.getText();
        String location = txtLocation.getText();
        String date = datefield.getValue().toString();
        String performer = txtPerformer.getText();
        int maxAttendess = Integer.parseInt(txtMaxAttendees.getText()) ;


        Launcher.eventList.add(new Event(name,performer,location,date,maxAttendess));
        Launcher.eventList.populateHashTable();
        Launcher.eventList.outputList();
    }

    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../managerMain/managerMain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../managerMain/managerMain.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


}
