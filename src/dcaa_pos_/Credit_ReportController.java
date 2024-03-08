/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dcaa_pos_;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Credit_ReportController implements Initializable {

    @FXML
    private ComboBox<String> ComboSearch;
    @FXML
    private TextField Search;
    @FXML
    private Button Print;
    @FXML
    private Button Load;
    @FXML
    private Button Close;
    @FXML
    private TableView<CRDataModel> TableCredit;
    @FXML
    private Button LoadDebit;
    @FXML
    private TableColumn<CRDataModel, String> OR;
    @FXML
    private TableColumn<CRDataModel, String> ID;
    @FXML
    private TableColumn<CRDataModel, String> Name;
    @FXML
    private TableColumn<CRDataModel, String> Amount;
    @FXML
    private TableColumn<CRDataModel, String> Type;
    @FXML
    private TableColumn<CRDataModel, String> UserID;
    ObservableList<CRDataModel> tableDataTransaction = FXCollections.observableArrayList();
    @FXML
    private DatePicker DateFrom;
    @FXML
    private DatePicker DateTo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        OR.setCellValueFactory(new PropertyValueFactory<>("ORString"));
        ID.setCellValueFactory(new PropertyValueFactory<>("S_IDString"));
        Name.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Type.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        UserID.setCellValueFactory(new PropertyValueFactory<>("Type"));
        // TODO
        loaddata();
        loadcombo_data();
    }

    @FXML
    private void LoadSearch(ActionEvent event) {
        LoadSearchdata();
    }

    @FXML
    private void Print(ActionEvent event) {
        try {

            Print();
        } catch (JRException ex) {
            Logger.getLogger(Credit_ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Load(ActionEvent event) {
    }

    @FXML
    private void Close(ActionEvent event) {
    }

    void loaddata() {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps, ps1;
        tableDataTransaction.clear();
        String Name = null;
        try {
            ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ps1 = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where Student_ID='" + rs.getString("StudentID") + "' ");
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    Name = rs1.getString("L_Name") + ", " + rs1.getString("F_Name") + " " + rs1.getString("M_Name");
                }
                ps1.close();

                tableDataTransaction.add(new CRDataModel(rs.getString("OR_"), rs.getString("StudentID"), Name, rs.getString("Credit"), rs.getString("createtime"), rs.getString("Transaction_type")));
                //System.out.println(rs.getString("Student_ID"));
            }

            TableCredit.setItems(tableDataTransaction);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void LoadDebit(ActionEvent event) {
        LoadDataByDate();
    }

    void loadcombo_data() {

        ObservableList<String> data = FXCollections.observableArrayList();
        data.add("OR Number");
        data.add("Student ID");
        data.add("Type");
        data.add("User Id");
        ComboSearch.setItems(data);
        //OptionsCombo.setValue("Select User Level");

    }

    private void Search(ActionEvent event) {

    }

    void LoadSearchdata() {
        DBConnection.init();

        Connection c = DBConnection.getConnection();

        String ItemName = "";
        String Search = "%" + this.Search.getText() + "%";

        PreparedStatement ps;
        ResultSet rs = null;
        tableDataTransaction.clear();
        try {

            switch (this.ComboSearch.getSelectionModel().getSelectedIndex()) {
                case 0:
                    ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where OR_ like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                case 1:
                    ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where StudentID like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                case 2:
                    ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where Transaction_type like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                case 3:
                    ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where userID like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                default:
                    ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where OR_ like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
            }
            String Name_ = null;
            while (rs.next()) {
                PreparedStatement ps1 = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where Student_ID='" + rs.getString("StudentID") + "' ");
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    Name_ = rs1.getString("L_Name") + ", " + rs1.getString("F_Name") + " " + rs1.getString("M_Name");
                }
                ps1.close();
                tableDataTransaction.add(new CRDataModel(rs.getString("OR_"), rs.getString("StudentID"), Name_, rs.getString("Credit"), rs.getString("createtime"), rs.getString("Transaction_type")));

            }

            TableCredit.setItems(tableDataTransaction);

            c.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void LoadDataByDate() {

        LocalDate selectedDateFrom = DateFrom.getValue();
        LocalDate selectedDateTo = DateTo.getValue();

        if (selectedDateFrom == null && selectedDateTo == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Date Selection Error");
            alert.setHeaderText("An Error Occurred");
            alert.setContentText("Please Select Date to Proceed ");

            alert.showAndWait();

        } else {

// Convert LocalDate to java.sql.Date
            Date sqlDateFrom = Date.valueOf(selectedDateFrom);
            Date sqlDateTo = Date.valueOf(selectedDateTo);

            DBConnection.init();

            Connection c = DBConnection.getConnection();

            String ItemName = "";
            String Search = "%" + this.Search.getText() + "%";

            PreparedStatement ps;
            ResultSet rs = null;
            tableDataTransaction.clear();
            try {

                switch (this.ComboSearch.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where OR_ like '" + Search + "' AND createtime BETWEEN ? AND ? ");
                        ps.setDate(1, sqlDateFrom);
                        ps.setDate(2, sqlDateTo);
                        rs = ps.executeQuery();

                        break;
                    case 1:
                        ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where StudentID like '" + Search + "' AND createtime BETWEEN ? AND ? ");
                        ps.setDate(1, sqlDateFrom);
                        ps.setDate(2, sqlDateTo);
                        rs = ps.executeQuery();
                        break;
                    case 2:
                        ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where Transaction_type like '" + Search + "' AND createtime BETWEEN ? AND ? ");
                        ps.setDate(1, sqlDateFrom);
                        ps.setDate(2, sqlDateTo);
                        rs = ps.executeQuery();
                        break;
                    case 3:
                        ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where userID like '" + Search + "' AND createtime BETWEEN ? AND ? ");
                        ps.setDate(1, sqlDateFrom);
                        ps.setDate(2, sqlDateTo);
                        rs = ps.executeQuery();
                        break;
                    default:
                        ps = c.prepareStatement("SELECT * FROM dcaa_pos.credit_history where OR_ like '" + Search + "' AND createtime BETWEEN ? AND ? ");
                        ps.setDate(1, sqlDateFrom);
                        ps.setDate(2, sqlDateTo);
                        rs = ps.executeQuery();
                        break;
                }

                String Name_ = null;
                while (rs.next()) {
                    PreparedStatement ps1 = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where Student_ID='" + rs.getString("StudentID") + "' ");

                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        Name_ = rs1.getString("L_Name") + ", " + rs1.getString("F_Name") + " " + rs1.getString("M_Name");
                    }
                    ps1.close();
                    tableDataTransaction.add(new CRDataModel(rs.getString("OR_"), rs.getString("StudentID"), Name_, rs.getString("Credit"), rs.getString("createtime"), rs.getString("Transaction_type")));

                }

                TableCredit.setItems(tableDataTransaction);

                c.close();
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void Print() throws JRException {

        DBConnection.init();

        LocalDate selectedDateFrom = DateFrom.getValue();
        LocalDate selectedDateTo = DateTo.getValue();
        Date sqlDateFrom = Date.valueOf(selectedDateFrom);
        Date sqlDateTo = Date.valueOf(selectedDateTo);

        if (selectedDateFrom == null && selectedDateTo == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Date Selection Error");
            alert.setHeaderText("An Error Occurred");
            alert.setContentText("Please Select Date to Proceed ");

            alert.showAndWait();

        } else {

            JasperReport JR;
            JasperPrint JP;

            // String Q="%"+this.txtSearch.getText() +"%";
            // String Source="C:\\Users\\JAMIEXXX3\\Documents\\NetBeansProjects\\Phonelist\\src\\Forms\\report1.jrxml";
            JasperDesign Jd = JRXmlLoader.load(System.getProperty("user.dir") + "\\\\reports\\\\CreditReport.jrxml");

            String SQL = "SELECT * FROM dcaa_pos.credit_history where createtime BETWEEN '" + sqlDateFrom.toString() + "' AND '" + selectedDateTo.toString() + "'";
            String Location = System.getProperty("user.dir") + "\\\\reports\\\\";

            JRDesignQuery JQ = new JRDesignQuery();
            JQ.setText(SQL);
            Jd.setQuery(JQ);
            JR = JasperCompileManager.compileReport(Jd);

            HashMap m = new HashMap<>();
            //String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

            m.put("parameter1", sqlDateFrom.toString());
            m.put("parameter2", sqlDateFrom.toString());

            JP = JasperFillManager.fillReport(JR, m, DBConnection.getConnection());

            JasperViewer.viewReport(JP, false);

        }
    }

}
