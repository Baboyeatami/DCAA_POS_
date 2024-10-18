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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    @FXML
    private Button LoadData;
    @FXML
    private Label subtotal;
    @FXML
    private DatePicker datetime1;
    @FXML
    private Button Btn_close;

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
        data.add("User ID ");

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

        PreparedStatement ps = null, ps2, ps_sum = null;
        ResultSet rs, rs2, rs_sum;
        String ItemName = "";
        String Search = "%" + search.getText() + "%";
        tableData.clear();
        try {
            if (combo.getSelectionModel().getSelectedIndex() == 0) {
                ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM dcaa_pos.invoice where StudentID like '" + Search + "'  order by date DESC");
                ps_sum = c.prepareStatement("SELECT sum(subTotal) as total  FROM  dcaa_pos.invoice where StudentID='" + search.getText() + "'");

            } else if (combo.getSelectionModel().getSelectedIndex() == 1) {
                ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM dcaa_pos.invoice where OR_ like '" + Search + "'  order by date DESC");
                ps_sum = c.prepareStatement("SELECT sum(subTotal) as total  FROM  dcaa_pos.invoice where OR_='" + search.getText() + "'");

            } else if (combo.getSelectionModel().getSelectedIndex() == 2) {
                ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM dcaa_pos.invoice where Item_name like '" + Search + "'  order by date DESC");
                ps_sum = c.prepareStatement("SELECT sum(subTotal) as total  FROM  dcaa_pos.invoice where Item_name ='" + search.getText() + "'");

            } else if (combo.getSelectionModel().getSelectedIndex() == 3) {
                ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM dcaa_pos.invoice where user_id like '" + Search + "'  order by date DESC");
                ps_sum = c.prepareStatement("SELECT sum(subTotal) as total  FROM  dcaa_pos.invoice where user_id ='" + search.getText() + "'");

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

            rs_sum = ps_sum.executeQuery();

            if (rs_sum.next()) {
                System.out.println(rs_sum.getString("total") + "  sum");
                double amount;
                if (rs_sum.getString(1) == null) {
                    amount = 0;
                } else {
                    amount = Double.parseDouble(rs_sum.getString(1));
                }

                DecimalFormat decimalFormatter = new DecimalFormat("#,##0.00");
                String formattedAmount = decimalFormatter.format(amount);

                System.out.println("Formatted amount: " + formattedAmount);
                subtotal.setText("Sub Total:  " + formattedAmount);
            }

            c.close();

            //c.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Load_Data_day(ActionEvent event) {

        Date date = new Date();
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        // System.out.println(date + "\n" + instant + "\n" + localDate);

        LocalDate localDate2 = datetime.getValue();
        LocalDate localDate3 = datetime1.getValue();
        System.out.println(localDate2 + " and " + localDate3);
        Instant instant2 = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date2 = Date.from(instant);
        System.out.println(localDate2 + "\n" + instant2 + "\n" + date2 + " date picker");

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps, ps2, ps_sum;
        ResultSet rs, rs2, rs_sum;
        String ItemName = "";
        tableData.clear();
        try {
            ps = c.prepareStatement("SELECT  Item_name, quantity, price, subTotal, OR_, StudentID,date FROM  dcaa_pos.invoice where date(date) between '" + localDate3.toString() + "' AND '" + localDate2.toString() + "'  order by date DESC");
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

            ps_sum = c.prepareStatement("SELECT sum(subTotal) as total  FROM  dcaa_pos.invoice where date(date) between '" + localDate3.toString() + "' AND '" + localDate2.toString() + "'");
            rs_sum = ps_sum.executeQuery();

            if (rs_sum.next()) {
                System.out.println(rs_sum.getString("total") + "  sum");
                double amount = 0;
                if (rs_sum.getString(1) == null) {
                    amount = 0;
                } else {
                    amount = Double.parseDouble(rs_sum.getString(1));
                }

                DecimalFormat decimalFormatter = new DecimalFormat("#,##0.00");
                String formattedAmount = decimalFormatter.format(amount);

                System.out.println("Formatted amount: " + formattedAmount);
                subtotal.setText("Sub Total:  " + formattedAmount);
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Close_Sales_invoice(ActionEvent event) {
        Stage close = (Stage) Btn_close.getScene().getWindow();
        close.close();

    }

}
