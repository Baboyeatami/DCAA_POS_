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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Item_Type_listController implements Initializable {

    @FXML
    private TableView<ItemTypeModel> ItemTypeTable;
    @FXML
    private TableColumn<ItemTypeModel, String> ID;
    @FXML
    private TableColumn<ItemTypeModel, String> Item_name;
    @FXML
    private TableColumn<ItemTypeModel, String> Description;
    @FXML
    private ContextMenu ContextMenu;
    @FXML
    private MenuItem menuJamie1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Item_name.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        loaddata();
    }
    ObservableList<ItemTypeModel> tableData = FXCollections.observableArrayList();

    void loaddata() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        try {
            ps = c.prepareStatement("SELECT * FROM dcaa_pos.item_type");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableData.add(new ItemTypeModel(rs.getString(1), rs.getString(2), rs.getString(3)));
                System.out.println(rs.getString(1));
            }

            ItemTypeTable.setItems(tableData);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ContextMenu(ActionEvent event) {
    }

    @FXML
    private void JamieMenuAction(ActionEvent event) {
        TablePosition pos = ItemTypeTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        System.out.println(row + " row");
        ItemTypeModel Item = ItemTypeTable.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String Data = (String) col.getCellObservableValue(Item).getValue();
        System.out.println(Data);
        System.out.println(ItemTypeTable.getItems().get(row).getID());

    }

}
