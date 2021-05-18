package mainApplication.viewEventUser;

import authentication.homePage.Launcher;
import data.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewEventUserController implements Initializable {
    private Stage stage;
    private Scene scene;


    @FXML
    private TableView<Event> tableView;
    @FXML
    private TableColumn<Event, Integer> UUIDColumn;
    @FXML
    private TableColumn<Event, String> NameColumn;
    @FXML
    private TableColumn<Event, String> LocationColumn;
    @FXML
    private TableColumn<Event, String> DateColumn;
    @FXML
    private TableColumn<Event, String> PerformerColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UUIDColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("UUID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("Location"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        PerformerColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("performer"));

        tableView.setItems(Launcher.eventList.getEvents());
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


}
