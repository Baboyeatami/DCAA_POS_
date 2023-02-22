/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Inventory_Controller implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button ADD_new_ItemType;
    @FXML
    private Button New_Inventory;
    @FXML
    private Button Item_type_List;
    _Add_itemListController add_itemListController;
    @FXML
    private TableView<Inventory_Data_Model> Table;
    @FXML
    private TableColumn<Inventory_Data_Model, String> Item_id;
    @FXML
    private TableColumn<Inventory_Data_Model, String> item_name;
    @FXML
    private TableColumn<Inventory_Data_Model, String> Description;
    @FXML
    private TableColumn<Inventory_Data_Model, String> price;
    @FXML
    private TableColumn<Inventory_Data_Model, String> Item_type;

    ObservableList<Inventory_Data_Model> tableData = FXCollections.observableArrayList();
    @FXML
    private Button Item_type_List1;
    @FXML
    private Button Shortcut_settings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Item_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        item_name.setCellValueFactory(new PropertyValueFactory<>("Item_name"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Item_type.setCellValueFactory(new PropertyValueFactory<>("ItemType"));
        loaddata();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage close = (Stage) btnClose.getScene().getWindow();
        close.close();

    }

    @FXML
    private void Add_new_item(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("/dcaa_pos_/Item_type.fxml"));

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add new Item");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void Add_new_inventory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/_Add_itemList.fxml"));
        Parent root1 = loader.load();
        add_itemListController = loader.getController();
        add_itemListController.inventory_ = this;

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add new Item");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void On_Item_ltype(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("/dcaa_pos_/Item_Type_list.fxml"));

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Item Type List");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    void LoadData() {
        System.out.println("Load data");

    }

    void loaddata() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        try {
            ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price, Item_type_idItem_type FROM dcaa_pos.items");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableData.add(new Inventory_Data_Model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                System.out.println(rs.getString(1));
            }

            Table.setItems(tableData);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void POSMain_(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/POS_Main.fxml"));
            Parent root1 = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("POS Main Window");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Inventory_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ShortcutSetting(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/Button_Settings.fxml"));
            Parent root1 = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Menu Short cut Settings");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Inventory_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
