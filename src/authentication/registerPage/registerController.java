package authentication.registerPage;

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
import javafx.stage.Stage;

import java.io.IOException;



public class registerController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private String name;
    private String userName;
    private String password;
    private String email;
    private boolean isManager;

    private boolean suitable;


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

    public void signup(ActionEvent event) throws IOException {
        if( isValidUserName() && isValidName() && isValidPassword() && isValidEmail()){
            name = txtFullName.getText();
            userName = txtUserName.getText();
            password = txtpassword.getText();
            email = txtEmail.getText();
            isManager = isManagercheck.isSelected();

            Launcher.UserList.add(new User(name,userName,email,password,isManager));
            createAlert("Success","User is now registered");


            Parent root = FXMLLoader.load(getClass().getResource("../userLogin/userLogin.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


        }

    public void getBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../userLogin/userLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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
        boolean b = false;
        if (Launcher.UserList.findUser(txtUserName.getText())) {
            createAlert("Error", "UserName already Exists");
        } else if (txtUserName.getText().equals("")) {
            createAlert("Error", "Please Enter username");
        } else {
            userName = txtUserName.getText();
            b = true;
        }
        return b;
    }

    public boolean isValidName(){
        boolean b = false;
        if (txtFullName.getText().equals(""))
            createAlert("Error", "Please enter your name");
        else{
            name = txtFullName.getText();
            b = true;
        }
        return b;
    }

    public boolean isValidPassword(){
        boolean b = false;
        if (txtpassword.getText().equals("")){
            createAlert("Error", "Please Enter Password");
        }
        else if ((txtpassword.getText()).equals(txtConfrimPassword.getText())) {
            password = txtpassword.getText();
            b = true;
        } else {
            createAlert("Error", "Passwords do not match");
        }
     return b;
    }

    public boolean isValidEmail(){
        boolean b = false;
        if (txtEmail.getText().equals("")) {
            createAlert("Error", "Please Enter Email");
        }
        else if (Launcher.UserList.findEmail(txtEmail.getText())){
            createAlert("Error", "Email Already Exists");
        }
        else
        {
            email = txtEmail.getText();
            b = true;
        }
            return b;
    }



}
