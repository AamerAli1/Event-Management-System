package mainApplication.registerEvent;

import authentication.homePage.Launcher;
import data.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.UUID;

public class RegisterEventController {
    private Stage stage;
    private Scene scene;

    @FXML
    TextField txtInput;
    @FXML
    Label lblName;
    @FXML
    Label lblLocation;
    @FXML
    Label lblDate;
    @FXML
    Label lblPerformer;
    @FXML
    Button btnRegister;
    @FXML
    Button btnunRegister;



    public void showInfo(ActionEvent event){
        int userUUID = Launcher.currentUser.getUUID();


        int UUIDKey = 0;
        if(txtInput.getText() == "")
            createAlertError("Error","UUID field is empty");
        else {
             UUIDKey = Integer.parseInt(txtInput.getText());
        }
        Event eventToRegister = Launcher.eventUUIDHash.get(UUIDKey);

       if((Launcher.eventUUIDHash.containsKey(UUIDKey) == false) ){
            createAlertError("Error","Event not found");
        }else{
            createAlertInfo("Success","Event Found");
            Event events = Launcher.eventUUIDHash.get(UUIDKey);
            String name = events.getName();
            String location = events.getLocation();
            String date = events.getDate();
            String performer = events.getPerformer();

            lblName.setText(name);
            lblLocation.setText(location);
            lblDate.setText(date);
            lblPerformer.setText(performer);

            lblName.setVisible(true);
            lblLocation.setVisible(true);
            lblDate.setVisible(true);
            lblPerformer.setVisible(true);

           if(Launcher.eventList.userExists(eventToRegister,userUUID)){
               btnunRegister.setVisible(true);
               btnRegister.setVisible(false);
           }else{
               Launcher.eventList.RegisterToEvent(eventToRegister,userUUID);
               btnRegister.setVisible(true);
               btnunRegister.setVisible(false);
           }

        }

    }

    public void registerToEvent(ActionEvent event){
        int UUIDKey = Integer.parseInt(txtInput.getText());
        int UUID = Launcher.currentUser.getUUID();
        Event eventToRegister = Launcher.eventUUIDHash.get(UUIDKey);

        if(eventToRegister.getInvitees().size() < eventToRegister.getMaxInvitees()){
                Launcher.eventList.RegisterToEvent(eventToRegister,UUID);
                createAlertInfo("Success","you have been registered to this event");
        }else
            createAlertError("Error","Sorry , The event is full");

        btnRegister.setVisible(false);
        btnunRegister.setVisible(true);
    }

    public void unregisterFromEvent(ActionEvent event){
        int UUIDKey = Integer.parseInt(txtInput.getText());
        int UUID = Launcher.currentUser.getUUID();
        Event eventToRegister = Launcher.eventUUIDHash.get(UUIDKey);
        Launcher.eventList.unRegisterFromEvent(eventToRegister,UUID);

        btnRegister.setVisible(true);
        btnunRegister.setVisible(false);
    }



    public void switchToMainUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../userMain/userMain.fxml"));
        String css = getClass().getResource("../userMain/userMain.css").toExternalForm();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void createAlertError(String title,String header){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
    public void createAlertInfo(String title,String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

}
