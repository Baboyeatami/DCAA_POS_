/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class POS_searchController implements Initializable {

    @FXML
    private TableView<ItemTypeModel> TableView;
    @FXML
    private TextField SearchTxt;
    @FXML
    private TableColumn<ItemTypeModel, String> Item_ID;
    @FXML
    private TableColumn<ItemTypeModel, String> Item_Name;
    @FXML
    private TableColumn<ItemTypeModel, String> Description;
    @FXML
    private TableColumn<ItemTypeModel, String> Price;
    POS_MainController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Item_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Item_Name.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));

        LoadItems();
        SearchTxt.requestFocus();

    }
    ObservableList<ItemTypeModel> tableData = FXCollections.observableArrayList();

    void LoadItems() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        try {
            ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price FROM dcaa_pos.items");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableData.add(new ItemTypeModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                System.out.println(rs.getString(1));
            }

            TableView.setItems(tableData);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Load_SearchItems() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;

        tableData.clear();
        try {
            ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price FROM dcaa_pos.items");
            ResultSet rs = ps.executeQuery();

            String SearchMode = "%" + SearchTxt.getText() + "%";

            ps = c.prepareStatement("SELECT idItems, Item_name, Description, Price FROM dcaa_pos.items where Item_name like '" + SearchMode + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                tableData.add(new ItemTypeModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                System.out.println(rs.getString(1));
            }

            TableView.setItems(tableData);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Load_search(InputMethodEvent event) {
        System.out.println("Load search");
    }

    @FXML
    private void Load_search(ActionEvent event) {

        Load_SearchItems();
    }

    @FXML
    private void on_Press(KeyEvent ev) {
        KeyCode kc = ev.getCode();
        if (kc == KeyCode.ENTER) {
            TablePosition pos = TableView.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            System.out.println(row + " row");
            ItemTypeModel Item = TableView.getItems().get(row);
            TableColumn col = pos.getTableColumn();
            //String Data = (String) col.getCellObservableValue(Item).getValue();
            //System.out.println(Data);
            System.out.println(TableView.getItems().get(row).getID());
            //FXMLLoader loader = FXMLLoader.load(getClass().getResource("/dcaa_pos_/POS_Main.fxml"));
            //Parent pane = (Parent) loader.load();
            // controller = loader.getController();
            controller.Data_transfer(TableView.getItems().get(row).getID());
            //controller.Update_Label();
            Stage close = (Stage) SearchTxt.getScene().getWindow();
            close.close();

        } else if (kc == KeyCode.ESCAPE) {
            Stage close = (Stage) SearchTxt.getScene().getWindow();
            close.close();
        }

    }

    void set_Fucos() {
        SearchTxt.requestFocus();

    }

}
