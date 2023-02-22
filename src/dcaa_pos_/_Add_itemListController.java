/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class _Add_itemListController implements Initializable {

    @FXML
    private Button AddNewButton;
    @FXML
    private Button close;
    @FXML
    private TextField Item_name;
    @FXML
    private TextField Description;
    @FXML
    private TextField price;
    @FXML
    private ComboBox<String> comboItemType;
    List<String> types, IDtypes;
    Inventory_Controller inventory_;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loaddata_Combobox();

    }

    @FXML
    private void AddNewButtoon(ActionEvent event) {
        New_Inventory();
    }

    @FXML
    private void Close(ActionEvent event) {
        System.out.println(comboItemType.getSelectionModel().getSelectedIndex());
    }

    void loaddata_Combobox() {
        DBConnection.init();
        types = new ArrayList<String>();
        IDtypes = new ArrayList<String>();
        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        try {
            ps = c.prepareStatement("SELECT idItem_type,Item_type_Name FROM dcaa_pos.item_type");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                types.add(rs.getString("Item_type_Name"));
                IDtypes.add(rs.getString("idItem_type"));
            }

            //ObservableList<String> options = FXCollections.observableArrayList(types);
            comboItemType.getItems().setAll(types);
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void New_Inventory() {
        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;

            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            ps = c.prepareStatement("Insert into dcaa_pos.items (Item_name, Description, Price, create_time, update_time, Item_type_idItem_type) values ('" + Item_name.getText() + "','" + Description.getText() + "','" + price.getText() + "','" + timeStamp + "','" + timeStamp + "','" + IDtypes.get(comboItemType.getSelectionModel().getSelectedIndex()) + "')");

            if (!ps.execute()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inventory");
                alert.setHeaderText("new Item ");
                alert.setContentText("New Item in Inventory is Created Successfully! ");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Item_name.setText("");
                    Description.setText("");
                    price.setText("");
                    price.requestFocus();

                }
            }

            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/Inventory_.fxml"));
            //inventory_ = loader.getController();
            inventory_.LoadData();

            c.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
