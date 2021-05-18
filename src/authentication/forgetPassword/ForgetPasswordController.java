package authentication.forgetPassword;

import authentication.homePage.Launcher;
import authentication.registerPage.registerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tools.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;

public class ForgetPasswordController {
    static String randomCode;
    static String username;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtNewPass;

    @FXML
    private TextField txtCode;

    public void retrievePassword(ActionEvent event) throws MessagingException, IOException {
        this.randomCode = ""+((int)(Math.random()*9000)+1000);
        this.username = txtUserName.getText();
        String test = "lala";
    if(Launcher.userList.findUserName(username)){
        switchToEmailVerification(event);
        String message = "Your random verification code is: " +  this.randomCode;
        System.out.println(message);
        SendEmail.sendMail(Launcher.userList.findEmail(username),"Your Verification Code",message);
    }
    else
        createAlertError("Error","Username does not exist");
    }

    public void confirmPassword(ActionEvent event) {
        String codeToCheck = txtCode.getText();
        String newPass = txtNewPass.getText();
        if (!randomCode.equals(codeToCheck)){
            createAlertError("Error","Invalid Code");
        }else if (!registerController.passwordRegex(newPass)){
            createAlertError("Error", "Invalid Password, Please check the rules");
        }else{
            Launcher.userList.changePass(username,newPass);
            createAlertInfo("Success","Password changed");
        }

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

    public void switchToEmailVerification(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ForgetPasswordEmailCode.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
