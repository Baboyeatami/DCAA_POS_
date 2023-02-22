/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dcaa_pos_.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
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
        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;
            ResultSet rs = null;

            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat date2 = new SimpleDateFormat("yyyy/MM/d");
            SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");

            String Date = date2.format(cal.getTime());
            String Time = time.format(cal.getTime());

            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/d");

            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            ps = c.prepareStatement("Insert into item_type (idItem_type, Item_type_Name, Description, update_time, create_time)values" + "('" + Item_name.getText() + "','" + Item_description.getText() + "','" + timeStamp + "','" + timeStamp + "','" + timeStamp + "')");
            ps.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "new Item type saved! ", null);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void Close(ActionEvent event) {
    }

}
