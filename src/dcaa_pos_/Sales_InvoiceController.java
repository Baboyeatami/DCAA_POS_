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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Sales_InvoiceController implements Initializable {

    @FXML
    private TableColumn<Sales_Invoice_data_model, String> Item_name;
    @FXML
    private TableColumn<Sales_Invoice_data_model, String> Quantity;
    @FXML
    private TableColumn<Sales_Invoice_data_model, String> Price;
    @FXML
    private TableColumn<Sales_Invoice_data_model, String> Or_number;
    @FXML
    private TableColumn<Sales_Invoice_data_model, String> Student_id;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private TextField search;
    @FXML
    private DatePicker datetime;
    @FXML
    private TableView<Sales_Invoice_data_model> table;

    ObservableList<Sales_Invoice_data_model> tableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Sales_Invoice_data_model, String> SubTotal;
    @FXML
    private Button refresh;
    @FXML
    private TableColumn<Sales_Invoice_data_model, String> date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Item_name.setCellValueFactory(new PropertyValueFactory<>("Item_name"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Or_number.setCellValueFactory(new PropertyValueFactory<>("ORnumber"));
        Student_id.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        SubTotal.setCellValueFactory(new PropertyValueFactory<>("SubTotal"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        loaddata();
        loadcombo_data();

        // TODO
    }

    void loadcombo_data() {

        ObservableList<String> data = FXCollections.observableArrayList();

        data.add("Student Id");
        data.add("OR Number");
        data.add("Item Code");

        combo.setItems(data);
        //OptionsCombo.setValue("Select User Level");

    }

    void loaddata() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps, ps2;
        ResultSet rs, rs2;
        String ItemName = "";
        tableData.clear();
        try {
            ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM dcaa_pos.invoice order by date DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                ps2 = c.prepareCall("SELECT Item_name FROM dcaa_pos.items where iditems=" + rs.getString(1) + "");
                rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    ItemName = rs2.getString(1);
                }

                tableData.add(new Sales_Invoice_data_model(ItemName, rs.getString("quantity"), rs.getString("price"), rs.getString("subTotal"), rs.getString("OR_"), rs.getString("StudentID"), rs.getString("date")));
                System.out.println(rs.getString(1));
            }

            table.setItems(tableData);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Refresh(ActionEvent event) {
        loaddata();
    }

    @FXML
    private void LoadSerachDAta(ActionEvent event) {
        loadSearch();
    }

    void loadSearch() {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps = null, ps2;
        ResultSet rs, rs2;
        String ItemName = "";
        String Search = "%" + search.getText() + "%";
        tableData.clear();
        try {
            if (combo.getSelectionModel().getSelectedIndex() == 0) {
                ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM dcaa_pos.invoice where StudentID like '" + Search + "'  order by date DESC");

            } else if (combo.getSelectionModel().getSelectedIndex() == 1) {
                ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM dcaa_pos.invoice where OR_ like '" + Search + "'  order by date DESC");

            } else if (combo.getSelectionModel().getSelectedIndex() == 2) {
                ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM dcaa_pos.invoice where Item_name like '" + Search + "'  order by date DESC");

            }

            rs = ps.executeQuery();
            while (rs.next()) {
                ps2 = c.prepareCall("SELECT Item_name FROM dcaa_pos.items where iditems=" + rs.getString(1) + "");
                rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    ItemName = rs2.getString(1);
                }

                tableData.add(new Sales_Invoice_data_model(ItemName, rs.getString("quantity"), rs.getString("price"), rs.getString("subTotal"), rs.getString("OR_"), rs.getString("StudentID"), rs.getString("date")));
                System.out.println(rs.getString(1));
            }

            table.setItems(tableData);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
