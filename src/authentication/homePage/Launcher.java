package authentication.homePage;

import data.Event;
import data.EventLinkedList;
import data.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import data.UserLinkedList;

import java.io.IOException;
import java.util.Hashtable;

public class Launcher extends Application {

    public static UserLinkedList userList = new UserLinkedList();
    public static EventLinkedList eventList = new EventLinkedList();
    public static Hashtable<Integer, Event> eventUUIDHash = new Hashtable();
    public static User currentUser = null;



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
        //Get the data from text files to Run Time LinkedList
        userList.readFromFile(userList);
        System.out.println("Current Users in the system: ");
        userList.outputList();
        eventList.readFromFile(eventList);
        System.out.println("Current events in the system: ");
        eventList.outputList();
        //populate hashtable to increase run time search speed
        eventList.populateUUIDHashTable();
        launch(args);
        //save all data from runtime linked list to text file
        System.out.println("writing users and events to external files");
        userList.writeToFile();
        eventList.writeToFile();
        System.out.println("Done");

    }



}
