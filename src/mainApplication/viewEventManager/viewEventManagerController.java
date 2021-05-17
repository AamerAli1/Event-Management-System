package mainApplication.viewEventManager;

import authentication.homePage.Launcher;
import data.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class viewEventManagerController {
    private Stage stage;
    private Scene scene;

    @FXML
    TextField txtUUID;
    @FXML
    AnchorPane anchorInfo;
    @FXML
    AnchorPane anchorSearch;
    @FXML
    Label lblName;
    @FXML
    Label lblLocation;
    @FXML
    Label lblDate;
    @FXML
    Label lblPerformer;
    @FXML
    Label lblMax;
    @FXML
    Label lblUUID;



    public void search(ActionEvent event){

        int key = Integer.parseInt(txtUUID.getText());
        if(Launcher.eventHash.containsKey(key) == false){
            createAlertError("Error","UUID not found");
        }
        else{
            createAlertInfo("Success","Event Found");

            anchorSearch.setVisible(false);
            anchorInfo.setVisible(true);

            Event events = Launcher.eventHash.get(key);
            String name = events.getName();
            String Location = events.getLocation();
            String Date = events.getDate();
            String performer = events.getPerformer();
            String maxAttendees = String.valueOf(events.getMaxInvitees());
            String UUID = String.valueOf(events.getUUID());

            lblName.setText(name);
            lblLocation.setText(Location);
            lblDate.setText(Date);
            lblPerformer.setText(performer);
            lblMax.setText(maxAttendees);
            lblUUID.setText(UUID);

        }

    }

    public void switchToSearch(ActionEvent event){
    anchorInfo.setVisible(false);
    anchorSearch.setVisible(true);
    }

    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../managerMain/managerMain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../managerMain/managerMain.css").toExternalForm());
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

