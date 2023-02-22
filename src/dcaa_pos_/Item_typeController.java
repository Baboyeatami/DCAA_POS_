package dcaa_pos_;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Item_typeController implements Initializable {

    @FXML
    private TextField Item_name;
    @FXML
    private TextField Item_description;
    @FXML
    private Button Save_Button;
    @FXML
    private Button Close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Save_new_item(ActionEvent event) {
        AddnewItemType();
    }

    @FXML
    private void Close(ActionEvent event) {
    }

    void AddnewItemType() {
        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;

            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            ps = c.prepareStatement("Insert into item_type (Item_type_Name, Description, update_time, create_time) values ('" + Item_name.getText() + "','" + Item_description.getText() + "','" + timeStamp + "','" + timeStamp + "')");

            if (!ps.execute()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("New Item Type");
                alert.setHeaderText("Item Type");
                alert.setContentText("New Item Type Created Successfully! ");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Item_name.setText("");
                    Item_description.setText("");
                    Item_name.requestFocus();
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
