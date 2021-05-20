package mainApplication.registerEvent;

import authentication.homePage.Launcher;
import data.Event;
import data.User;
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
import tools.SendEmail;


import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    private static int UUIDInput;
    private static User currentUser;

    public void showInfo(ActionEvent event){
        this.currentUser = Launcher.currentUser;

        try{
            if(txtInput.getText() == ""){
                clearLabels();
                createAlertError("Error","UUID field is empty");
            }
            else {
                int UUIDKey = Integer.parseInt(txtInput.getText());
                Event eventToRegister = Launcher.eventUUIDHash.get(UUIDKey);
                this.UUIDInput = UUIDKey;

                if((Launcher.eventUUIDHash.containsKey(UUIDKey) == false) ){
                    clearLabels();
                    createAlertError("Error","Event not found");
                }else{
                    String name = eventToRegister.getName();
                    String location = eventToRegister.getLocation();
                    String date = eventToRegister.getDate();
                    String performer = eventToRegister.getPerformer();

                    lblName.setText(name);
                    lblLocation.setText(location);
                    lblDate.setText(date);
                    lblPerformer.setText(performer);

                    lblName.setVisible(true);
                    lblLocation.setVisible(true);
                    lblDate.setVisible(true);
                    lblPerformer.setVisible(true);

                    if(Launcher.eventList.userExistsInEvent(eventToRegister,this.currentUser.getUUID())){
                        btnunRegister.setVisible(true);
                        btnRegister.setVisible(false);
                    }else{
                        btnRegister.setVisible(true);
                        btnunRegister.setVisible(false);
                    }
                }
            }
        }catch (Exception e){
            clearLabels();
            createAlertError("Error","UUID field can only contain numbers");
        }
    }

    public void registerToEvent(ActionEvent event) {

        Event eventToRegister = Launcher.eventUUIDHash.get(this.UUIDInput);

        if(eventToRegister.getInvitees().size() < eventToRegister.getMaxInvitees()){
                Launcher.eventList.RegisterToEvent(eventToRegister,this.currentUser.getUUID());
                createAlertInfo("Success","you have been registered to this event");
                btnRegister.setVisible(false);
                btnunRegister.setVisible(true);
            String content = String.format("Hello %s\nYou have successfully registered to %s\n\nThanks" +
                    " for using event management system",currentUser.getName(),eventToRegister.getName());
            ExecutorService service = Executors.newFixedThreadPool(1);
            service.submit(new Runnable() {
                public void run() {
                    try {
                        SendEmail.sendMail(currentUser.getEmail(),"Event Registration",content);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });

        }else
            createAlertError("Error","Sorry , The event is full");


    }

    public void unregisterFromEvent(ActionEvent event){
        Event eventToRegister = Launcher.eventUUIDHash.get(this.UUIDInput);
        Launcher.eventList.unRegisterFromEvent(eventToRegister,this.currentUser.getUUID());

        createAlertInfo("Success","You unregistered from the event");

        String content = String.format("Hello %s\nYou have successfully unregistered from %s\n\nThanks" +
                " for using event management system",currentUser.getName(),eventToRegister.getName());
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(new Runnable() {
            public void run() {
                try {
                    SendEmail.sendMail(currentUser.getEmail(),"Event unRegistration",content);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });

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

    public void clearLabels(){
        lblName.setText("");
        lblLocation.setText("");
        lblDate.setText("");
        lblPerformer.setText("");
        btnRegister.setVisible(false);
        btnunRegister.setVisible(false);
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
