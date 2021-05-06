package authentication.forgetPassword;

import authentication.homePage.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tools.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;

public class ForgetPasswordController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField txtUserName;

    public void retrievePassword(ActionEvent event) throws MessagingException {
        String username = txtUserName.getText();
    if(Launcher.UserList.findUserName(username)){
        String message = "Your password is: " +  Launcher.UserList.retrievePass(username);
        SendEmail.sendMail(Launcher.UserList.findEmail(username),"Your Event Management System Password",message);
        createAlertInfo("Password Found","Please check your email for the password");

    }
    else
        createAlertError("Error","Username does not exist");

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

    public void switchToLauncher(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../homePage/Launcher.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("../homePage/Launcher.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
