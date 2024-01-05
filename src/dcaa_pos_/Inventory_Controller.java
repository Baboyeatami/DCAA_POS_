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
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    _Add_itemListController Update_itemListController;
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
    @FXML
    private MenuItem MenuItemUpdate;
    @FXML
    private Button refresh;
    @FXML
    private TableColumn<Inventory_Data_Model, String> Cost;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private TextField Search;

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
        Cost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        loaddata();
        loadcombo_data();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage close = (Stage) btnClose.getScene().getWindow();
        close.close();

    }

    void loadcombo_data() {

        ObservableList<String> data = FXCollections.observableArrayList();

        data.add("Item Id");
        data.add("Item Name");
        data.add("Description");

        combo.setItems(data);

        //OptionsCombo.setValue("Select User Level");
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

    @FXML
    void loaddata() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps, ps1 = null;
        tableData.clear();
        try {
            ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price,Cost, Item_type_idItem_type FROM dcaa_pos.items");
            ResultSet rs = ps.executeQuery();

            String ItemType = "";

            while (rs.next()) {
                ps1 = c.prepareStatement("Select Item_type_Name,idItem_type from dcaa_pos.item_type where idItem_type ='" + rs.getString("Item_type_idItem_type") + "'");
                ResultSet rs2 = ps1.executeQuery();
                while (rs2.next()) {
                    if (rs.getString("Item_type_idItem_type").equals(rs2.getString("idItem_type"))) {
                        ItemType = rs2.getString("Item_type_Name");
                        System.out.println("Item Name:" + rs2.getString("Item_type_Name"));
                        break;
                    }
                }
                System.out.println(rs.getString("Description"));
                tableData.add(new Inventory_Data_Model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), ItemType, rs.getString("Cost")));
                System.out.println(rs.getString(1));
            }

            Table.setItems(tableData);

            c.close();
            ps.close();
            ps1.close();

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

    boolean Update = false;

    @FXML

    private void UPDATE_ITEM(ActionEvent event) {
        Inventory_Data_Model model = Table.getSelectionModel().getSelectedItem();

        System.out.println(model.ID);
        //Button_Settings_DataModel Item = Item_list_table.getItems().get(row);
        //TableColumn col = pos.getTableColumn();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/_Add_itemList.fxml"));

            Parent root1 = loader.load();
            Update_itemListController = loader.getController();
            Update_itemListController.setInventory_(this);
            Update_itemListController.set_Update_data(model.ID, model.Item_name, model.Description, model.Price, model.ItemType, true);

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Update Item");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Inventory_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Load_search() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();
        String ItemName = "";
        String Search = "%" + this.Search.getText() + "%";
        PreparedStatement ps, ps1 = null;
        ResultSet rs;
        tableData.clear();
        try {
            switch (combo.getSelectionModel().getSelectedIndex()) {
                case 0: {
                    ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price,Cost, Item_type_idItem_type FROM dcaa_pos.items where idItems like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                }
                case 1: {
                    ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price,Cost, Item_type_idItem_type FROM dcaa_pos.items where Item_name like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                }
                case 2: {
                    ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price,Cost, Item_type_idItem_type FROM dcaa_pos.items where Description like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                }
                default:
                    ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price,Cost, Item_type_idItem_type FROM dcaa_pos.items");
                    rs = ps.executeQuery();
                    break;
            }

            String ItemType = "";

            while (rs.next()) {
                ps1 = c.prepareStatement("Select Item_type_Name,idItem_type from dcaa_pos.item_type where idItem_type ='" + rs.getString("Item_type_idItem_type") + "'");
                ResultSet rs2 = ps1.executeQuery();
                while (rs2.next()) {
                    if (rs.getString("Item_type_idItem_type").equals(rs2.getString("idItem_type"))) {
                        ItemType = rs2.getString("Item_type_Name");
                        System.out.println("Item Name:" + rs2.getString("Item_type_Name"));
                        break;
                    }
                }
                System.out.println(rs.getString("Description"));
                tableData.add(new Inventory_Data_Model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), ItemType, rs.getString("Cost")));
                System.out.println(rs.getString(1));
            }

            Table.setItems(tableData);

            c.close();
            ps.close();
            ps1.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Search_Itrm(ActionEvent event) {
        Load_search();
    }

}
