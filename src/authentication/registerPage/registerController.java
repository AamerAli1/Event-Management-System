package authentication.registerPage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.*;
import authentication.homePage.Launcher;
import data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.Tools;

import javax.mail.MessagingException;
import java.io.IOException;


public class registerController extends Tools{
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


    public void signup(ActionEvent event) throws IOException{
        String email = txtEmail.getText();
        if( isValidUserName() && isValidName() && isValidPassword() && isValidEmail()){
            leftAnchor.setVisible(false);
            verificationAnchor.setVisible(true);
            emailPlaceHolder.setText(email);
            this.randomCode = ""+((int)(Math.random()*9000)+1000);
            String message = "Your random Email verification code is: " +  this.randomCode;
            System.out.println(message);
            String content = String.format("Hello %s\nYour requested code for signing up is %s\n\nThanks" +
                    " for using event management system",name,this.randomCode);
            System.out.println(content);
            ExecutorService service = Executors.newFixedThreadPool(1);
            service.submit(new Runnable() {
                public void run() {
                    try {
                        sendMail(email,"Your Verification Code",content);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });

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
            createAlertInfo("Success","User is now registered");

            Parent root = FXMLLoader.load(getClass().getResource("../homePage/Launcher.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("../homePage/Launcher.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }else{
            createAlertError("Error","Invalid verification code");
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



    public boolean isValidUserName(){
        String usernameToCheck = txtUserName.getText();
        boolean b = false;
        if (Launcher.userList.findUserName(usernameToCheck)) {
            createAlertError("Error", "Username already Exists");
        } else if (txtUserName.getText().equals("")) {
            createAlertError("Error", "Please Enter username");
        } else {
            if(!userNameRegex(usernameToCheck))
                createAlertError("Error","Invalid Username, Please check the rules");
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
            createAlertError("Error", "Please enter your name");
        else{
            if(!nameRegex(nameToCheck))
                createAlertError("Error","Invalid Full name, Please check the rules");
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
            createAlertError("Error", "Please Enter Password");
        }
        else if ((passToCheck).equals(txtConfrimPassword.getText())) {
            if(!passwordRegex(passToCheck))
                createAlertError("Error", "Invalid Password, Please check the rules");
            else {
                password = txtpassword.getText();
                b = true;
            }
        } else {
            createAlertError("Error", "Passwords do not match");
        }
     return b;
    }


    public boolean isValidEmail(){
        String emailtocheck = txtEmail.getText();
        boolean b = false;
        if (txtEmail.getText().equals("")) {
            createAlertError("Error", "Please Enter Email");
        }
        else if (Launcher.userList.checkEmail(txtEmail.getText())){
            createAlertError("Error", "Email Already Exists");
        }
        else if(!emailRegex(emailtocheck)){
            createAlertError("Error","Invalid Email");
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



