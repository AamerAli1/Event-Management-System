package mainApplication.viewEventManager;

import authentication.homePage.Launcher;
import data.Event;
import data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class viewEventManagerController implements Initializable{
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

    @FXML
    private TableView<Event> tableView;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<Event, String> NameColumn;
    @FXML
    private TableColumn<Event, Integer> UUIDColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> userUUIDColumn;
    @FXML
    private TableColumn<User, String> userUserNameColumn;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        NameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        UUIDColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("UUID"));
        tableView.setItems(Launcher.eventList.getEvents());

    }


    public void search(ActionEvent event){
        int key = 0;
        try{
            if (txtUUID.getText() == "")
                createAlertError("Error","UUID field is empty");
            else {
                key = Integer.parseInt(txtUUID.getText());
                if(Launcher.eventUUIDHash.containsKey(key) == false){
                    createAlertError("Error","UUID not found");
                }
                else{

                    anchorSearch.setVisible(false);
                    initializeTable();
                    anchorInfo.setVisible(true);

                    Event events = Launcher.eventUUIDHash.get(key);
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
        }catch (Exception e){
            createAlertError("Error","UUID field can only contain numbers");
        }


    }

    public void removeEvent(ActionEvent event){
        int key;
        key = Integer.parseInt(txtUUID.getText());
        Event events = Launcher.eventUUIDHash.get(key);
        Launcher.eventList.remove(events);

        createAlertInfo("Success","Event Successfully Removed");
        tableView.setItems(Launcher.eventList.getEvents());


        anchorInfo.setVisible(false);
        anchorSearch.setVisible(true);
        txtUUID.setText("");
    }

    public void initializeTable() {
        int key = Integer.parseInt(txtUUID.getText());
        Event events = Launcher.eventUUIDHash.get(key);

        userNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        userUUIDColumn.setCellValueFactory(new PropertyValueFactory<User,String>("UUID"));
        userUserNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));

        userTableView.setItems(Launcher.eventList.getUserinEvent(events));
    }



    public void switchToSearch(ActionEvent event){
    anchorInfo.setVisible(false);
    anchorSearch.setVisible(true);
    txtUUID.setText("");
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

