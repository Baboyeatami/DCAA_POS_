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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Button_SettingsController implements Initializable {

    @FXML
    private TableView<Button_Settings_DataModel> Item_list_table;
    @FXML
    private TableView<Button_Settings_DataModel> ButtonSettingsTable;
    @FXML
    private TableColumn<Button_Settings_DataModel, String> item_name;
    @FXML
    private TableColumn<Button_Settings_DataModel, String> description;
    @FXML
    private TableColumn<Button_Settings_DataModel, String> price;
    @FXML
    private TableColumn<Button_Settings_DataModel, String> item_id;
    @FXML
    private TableColumn<Button_Settings_DataModel, String> Button_number;
    @FXML
    private TableColumn<Button_Settings_DataModel, String> Product_assiment;

    @FXML
    private TableColumn<Button_Settings_DataModel, String> Item_ID;

    ObservableList<Button_Settings_DataModel> tableData_itemList = FXCollections.observableArrayList();

    ObservableList<Button_Settings_DataModel> Button_Settings_items = FXCollections.observableArrayList();

    ObservableList<Button_Settings_DataModel> tableData_ButtonSettings = FXCollections.observableArrayList();

    // int Button_Id[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    String Selected_Item_ID;

    @FXML
    private MenuItem Select_Item;
    @FXML
    private MenuItem Assign_item;
    @FXML
    private Label Item_selected;
    @FXML
    private Label Button_assignement;
    @FXML
    private Button Close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        item_name.setCellValueFactory(new PropertyValueFactory<>("item_names"));
        description.setCellValueFactory(new PropertyValueFactory<>("descriptions"));
        price.setCellValueFactory(new PropertyValueFactory<>("prices"));
        item_id.setCellValueFactory(new PropertyValueFactory<>("itm_id"));

        Button_number.setCellValueFactory(new PropertyValueFactory<>("ButtonNumber"));
        Product_assiment.setCellValueFactory(new PropertyValueFactory<>("Product"));
        Item_ID.setCellValueFactory(new PropertyValueFactory<>("itm_id"));

        Initialize_Buttons_();
        Item_List_tableLoad();
        Button_Settings();

    }

    void Item_List_tableLoad() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        try {
            ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price, Item_type_idItem_type FROM dcaa_pos.items");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableData_itemList.add(new Button_Settings_DataModel(rs.getString("Item_name"), rs.getString("Description"), rs.getString("Price"), rs.getString("idItems")));
                System.out.println(rs.getString(1));
            }

            Item_list_table.setItems(tableData_itemList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Button_Settings() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;

        Button_Settings_items.clear();
        try {
            ps = c.prepareStatement("SELECT ID,Product_Name,Button_name FROM dcaa_pos.shortcut_settings");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Button_Settings_items.add(new Button_Settings_DataModel(rs.getString("Button_name"), rs.getString("Product_Name"), rs.getString("ID")));
                System.out.println(rs.getString(1));
            }

            ButtonSettingsTable.setItems(Button_Settings_items);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Initialize_Buttons_() {
        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;

            ps = c.prepareStatement("select count('ID') from shortcut_settings");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = Integer.parseInt(rs.getString(1));

                if (count == 0) {
                    for (int i = 1; i < 21; i++) {
                        try {
                            ps = c.prepareStatement("Insert into shortcut_settings (Button_name) values (" + i + ")");
                            if (!ps.execute()) {
                                System.out.println("Injected data " + i);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(Button_SettingsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            ps.close();
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Button_SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String Selected_Item;

    @FXML
    private void Item_Selection(ActionEvent event) {
        TablePosition pos = Item_list_table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        System.out.println(row + " row");
        Button_Settings_DataModel Item = Item_list_table.getItems().get(row);
        TableColumn col = pos.getTableColumn();

        String Data = (String) col.getCellObservableValue(Item).getValue();
        System.out.println(Data);
        System.out.println(Item_list_table.getItems().get(row).getItm_id() + " Selected Id ");

        Selected_Item_ID = Item_list_table.getItems().get(row).getItm_id();
        Item_selected.setText("Selected Item: " + Data);

        Selected_Item = Item_list_table.getItems().get(row).getItm_id();
    }

    @FXML
    private void Assign_item(ActionEvent event) {

        try {
            TablePosition pos = ButtonSettingsTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            System.out.println(row + " row");
            Button_Settings_DataModel Item = ButtonSettingsTable.getItems().get(row);
            TableColumn col = pos.getTableColumn();

            String Data = (String) col.getCellObservableValue(Item).getValue();
            System.out.println(Data);
            System.out.println(ButtonSettingsTable.getItems().get(row).getItm_id());
            Button_assignement.setText("Assigned Item on Button " + ButtonSettingsTable.getItems().get(row).getButtonNumber() + ":  " + ButtonSettingsTable.getItems().get(row).getItm_id());

            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;
            System.out.println(Selected_Item + "Selected IDsss");
            ps = c.prepareStatement("Select idItems, Item_name,Description,Price, Item_type_idItem_type from dcaa_pos.items where idItems=" + Selected_Item + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps = c.prepareStatement("Update shortcut_settings set ID='" + rs.getString("idItems") + "', Price='" + rs.getString("Price") + "', Product_Name='" + rs.getString("Item_name") + "', Product_Description='" + rs.getString("Description") + "', item_type_id='" + rs.getString("Item_type_idItem_type") + "' where Button_name='" + ButtonSettingsTable.getItems().get(row).getButtonNumber() + "'");
                if (!ps.execute()) {
                    System.out.println("Item Assigned Successfully");
                }
            }

            Button_Settings();

        } catch (SQLException ex) {
            Logger.getLogger(Button_SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Close(ActionEvent event) {
        Stage close = (Stage) Close.getScene().getWindow();
        close.close();

    }

}
