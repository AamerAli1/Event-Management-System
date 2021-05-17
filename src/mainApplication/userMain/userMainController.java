package mainApplication.userMain;

import authentication.homePage.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class userMainController implements Initializable {
    @FXML
    Label lblWelcome;

    private Stage stage;
    private Scene scene;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String welcomeMessage = "Welcome " + Launcher.currentUser.getName();
        lblWelcome.setText(welcomeMessage);
    }


    public void switchToRegisterEvent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../registerEvent/RegisterEvent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToViewEvent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../viewEventUser/ViewEventUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToLauncher(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../authentication/homePage/Launcher.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("../../authentication/homePage/Launcher.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
