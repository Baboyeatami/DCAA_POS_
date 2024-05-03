/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dcaa_pos_;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class User_AccountsController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> OptionsCombo;
    @FXML
    private TextField retypepassword;
    @FXML
    private Button Save;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button close;
    @FXML
    private TableView<User_datamodel> UsersTable;
    @FXML
    private TableColumn<User_datamodel, String> User_Id;
    @FXML
    private TableColumn<User_datamodel, String> user_name;
    @FXML
    private TableColumn<User_datamodel, String> User_level;
    @FXML
    private TextField Name;
    ObservableList<User_datamodel> tableUSersList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User_Id.setCellValueFactory(new PropertyValueFactory<>("userid"));
        user_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        User_level.setCellValueFactory(new PropertyValueFactory<>("userType"));
        LoadCombo();
        loaddata();
    }

    void Savedata() {

    }

    void LoadCombo() {
        ObservableList<String> data = FXCollections.observableArrayList();

        data.add("Casher");
        data.add("Manager");
        data.add("Card Loader");

        OptionsCombo.setItems(data);
        OptionsCombo.setValue("Select User Level");

    }

    @FXML
    private void Save_user(ActionEvent event) {
        Insert();
    }

    void Insert() {
        DBConnection.init();
        Connection c = DBConnection.getConnection();
        PreparedStatement ps;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            ps = c.prepareStatement("Insert into dcaa_pos.users (Name, UserName, Password, usertype, createtime) values('" + Name.getText() + "','" + username.getText() + "','" + password.getText() + "','" + OptionsCombo.getSelectionModel().getSelectedItem() + "','" + timeStamp + "')");
            if (!ps.execute()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Accounts");
                alert.setHeaderText("New User");
                alert.setContentText("New User Created!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Name.setText("");
                    password.setText("");
                    username.setText("");
                    retypepassword.setText("");
                    loaddata();

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_AccountsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loaddata() {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        tableUSersList.clear();
        try {
            ps = c.prepareStatement("SELECT idusers, Name, UserName, usertype FROM dcaa_pos.users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableUSersList.add(new User_datamodel(rs.getString("idusers"), rs.getString("UserName"), rs.getString("userType")));
                // System.out.println(rs.getString("Student_ID"));
            }

            UsersTable.setItems(tableUSersList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
