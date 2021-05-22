package mainApplication.createEvent;

import authentication.homePage.Launcher;
import data.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tools.Tools;

import java.io.IOException;


public class CreateEventController extends Tools {


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
    @FXML
    TextField txtUUID;


    private String name;
    private String location;
    private String date;
    private String performer;
    private int maxAttendees;
    private int UUID;

    public void createEvent(ActionEvent event){
        if(validName() && validLocation() && validDate() && validPerformer() && validMax() && validUUID()){
            System.out.println("all fields are valid");
            Launcher.eventList.add(new Event(name,performer,location,date,maxAttendees,UUID));
            clear();
            Launcher.eventList.populateUUIDHashTable();
            Launcher.eventList.outputList();
            createAlertInfo("Success","Event added to the system");
        }



    }

    public void clear(){
        txtName.setText(null);
        txtLocation.setText(null);
        datefield.setValue(null);
        txtPerformer.setText(null);
        txtMaxAttendees.setText(null);
        txtUUID.setText(null);
    }

    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../managerMain/managerMain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../managerMain/managerMain.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public boolean validName(){
        String nameToCheck = txtName.getText();
        if(nameToCheck.equals("")){
            createAlertError("Error","Name field can't be empty");
            return false;
        }else{
            name = nameToCheck;
            return true;
        }
    }

    public boolean validLocation(){
        String locationToCheck = txtLocation.getText();
        if(locationToCheck.equals("")){
            createAlertError("Error","Location field can't be empty");
            return false;
        }else{
            location = locationToCheck;
            return true;
        }
    }

    public boolean validDate(){
        try{
            String dateToCheck = datefield.getValue().toString();
            date = dateToCheck;
            return true;
        }catch (Exception e){
            createAlertError("Error","date field can't be empty");
        }
      return false;
    }

    public boolean validPerformer(){
        String performerToCheck = txtPerformer.getText();
        if(performerToCheck.equals("")){
            createAlertError("Error","Performer field can't be empty");
            return false;
        }else{
            performer = performerToCheck;
            return true;
        }
    }

    public boolean validMax(){
        try{
            String maxToCheck = txtMaxAttendees.getText();
            if(txtMaxAttendees.getText().equals("")){
                createAlertError("Error","Max Attendees field can't be empty");
                return false;
            }
            else if(!(Integer.parseInt(maxToCheck) >= 2 && Integer.parseInt(maxToCheck) <= 10)){
                createAlertError("Error","Max Attendees field has to be between 2 and 10");
                return false;
            }
            else{
                maxAttendees = Integer.parseInt(maxToCheck);
                return true;
            }
        }catch (NumberFormatException e){
            createAlertError("Error","Max Attendees field can only contain numbers");
        }
     return false;
    }

    public boolean validUUID(){
        try{
            String UUIDToCheck = txtUUID.getText();
            if(txtUUID.getText().equals("")){
                createAlertError("Error","UUID field can't be empty");
                return false;
            }
            else if(Launcher.eventList.checkUUID(Integer.parseInt(UUIDToCheck))){
                createAlertError("Error","UUID already exists");
                return false;
            }
            else{
                UUID = Integer.parseInt(txtUUID.getText());
                return true;
            }
        }catch (Exception e){
            createAlertError("Error","UUID field can only contain numbers");
        }
        return false;
    }

}
