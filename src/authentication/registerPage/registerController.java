package authentication.registerPage;

import java.util.regex.*;
import authentication.homePage.Launcher;
import data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;


public class registerController {
    public static String randomCode;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._+%-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_USER_NAME_REGEX =
            Pattern.compile("^[a-zA-Z0-9]*$");
    public static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[a-zA-Z ]*$");
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$");

    private Stage stage;
    private Scene scene;


    private String name;
    private String userName;
    private String password;
    private String email;
    private boolean isManager;


    @FXML
    private AnchorPane leftAnchor;
    @FXML
    private AnchorPane verificationAnchor;
    @FXML
    private Text emailPlaceHolder;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtFullName;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private PasswordField txtConfrimPassword;
    @FXML
    private TextField txtEmail;
    @FXML
    private CheckBox isManagercheck;


    public void signup(ActionEvent event) throws IOException, MessagingException {
        String email = txtEmail.getText();
        if( isValidUserName() && isValidName() && isValidPassword() && isValidEmail()){
            leftAnchor.setVisible(false);
            verificationAnchor.setVisible(true);
            emailPlaceHolder.setText(email);
            this.randomCode = ""+((int)(Math.random()*9000)+1000);
            String message = "Your random Email verification code is: " +  this.randomCode;
            SendEmail.sendMail(email,"Your Verification Code",message);
        }

        }

    public void verifyEmail(ActionEvent event) throws IOException {
        if(txtCode.getText().equals(randomCode)){
            name = txtFullName.getText();
            userName = txtUserName.getText();
            password = txtpassword.getText();
            email = txtEmail.getText();
            isManager = isManagercheck.isSelected();

            Launcher.userList.add(new User(name,userName,email,password,isManager));
            createAlert("Success","User is now registered");

            Parent root = FXMLLoader.load(getClass().getResource("../homePage/Launcher.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("../homePage/Launcher.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }else{
            createAlert("Error","Invalid verification code");
        }

    }

    public void changeEmail(ActionEvent event){
     verificationAnchor.setVisible(false);
     leftAnchor.setVisible(true);
    }


    public void switchToLauncher(ActionEvent event) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../homePage/Launcher.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("../homePage/Launcher.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void clear(ActionEvent event){
        txtUserName.setText(null);
        txtFullName.setText(null);
        txtpassword.setText(null);
        txtConfrimPassword.setText(null);
        txtEmail.setText(null);
        isManagercheck.setSelected(false);
    }

    public void createAlert(String title,String header){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    public boolean isValidUserName(){
        String usernameToCheck = txtUserName.getText();
        boolean b = false;
        if (Launcher.userList.findUserName(usernameToCheck)) {
            createAlert("Error", "Username already Exists");
        } else if (txtUserName.getText().equals("")) {
            createAlert("Error", "Please Enter username");
        } else {
            if(!userNameRegex(usernameToCheck))
                createAlert("Error","Invalid Username, Please check the rules");
            else{
                userName = txtUserName.getText();
                b = true;
            }
        }
        return b;
    }

    public boolean isValidName(){
        String nameToCheck = txtFullName.getText();
        boolean b = false;
        if (txtFullName.getText().equals(""))
            createAlert("Error", "Please enter your name");
        else{
            if(!nameRegex(nameToCheck))
                createAlert("Error","Invalid Full name, Please check the rules");
            else{
                name = txtFullName.getText();
                b = true;
            }
        }
        return b;
    }

    public boolean isValidPassword(){
        String passToCheck = txtpassword.getText();
        boolean b = false;
        if (passToCheck.equals("")){
            createAlert("Error", "Please Enter Password");
        }
        else if ((passToCheck).equals(txtConfrimPassword.getText())) {
            if(!passwordRegex(passToCheck))
                createAlert("Error", "Invalid Password, Please check the rules");
            else {
                password = txtpassword.getText();
                b = true;
            }
        } else {
            createAlert("Error", "Passwords do not match");
        }
     return b;
    }


    public boolean isValidEmail(){
        String emailtocheck = txtEmail.getText();
        boolean b = false;
        if (txtEmail.getText().equals("")) {
            createAlert("Error", "Please Enter Email");
        }
        else if (Launcher.userList.checkEmail(txtEmail.getText())){
            createAlert("Error", "Email Already Exists");
        }
        else if(!emailRegex(emailtocheck)){
            createAlert("Error","Invalid Email");
        }
        else
        {
            email = txtEmail.getText();
            b = true;
        }
            return b;
    }


    public static boolean emailRegex(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean nameRegex(String name){
        Matcher matcher = VALID_NAME_REGEX.matcher(name);
        return matcher.find();
    }

    public static boolean userNameRegex(String userName){
        Matcher matcher = VALID_USER_NAME_REGEX.matcher(userName);
        return matcher.find();
    }

    public static boolean passwordRegex(String password){
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }


}



