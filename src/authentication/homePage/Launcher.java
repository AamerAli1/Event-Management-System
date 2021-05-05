package authentication.homePage;

import data.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import data.UserLinkedList;

import java.io.IOException;

public class Launcher extends Application {

    public static UserLinkedList UserList = new UserLinkedList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Launcher.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        String css = this.getClass().getResource("Launcher.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Event Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        UserList.add(new User("admin","admin","admin@gmail.com","admin123",true));
//        UserList.add(new User("user","user","user@gmail.com","user",false));
        //Get the data from text files to Run Time LinkedList
        UserList.readFromFile(UserList);
        UserList.outputList();
        launch(args);
        //save all data from runtime linked list to text file
        UserList.writeToFile();

    }



}
