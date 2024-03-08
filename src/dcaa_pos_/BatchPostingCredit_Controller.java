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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class BatchPostingCredit_Controller implements Initializable {

    @FXML
    private TextField Search;
    @FXML
    private ComboBox<String> select;
    @FXML
    private TextField Amount;
    @FXML
    private Button Close;
    @FXML
    private TableView<Student_dataModel> Name_table;
    @FXML
    private TableView<BatchPostingDataModel> BatchPostingTable;
    @FXML
    private TableColumn<Student_dataModel, String> L_StudentID;
    @FXML
    private TableColumn<Student_dataModel, String> L_Name;
    @FXML
    private TableColumn<BatchPostingDataModel, String> B_TableID;
    @FXML
    private TableColumn<BatchPostingDataModel, String> B_Name;
    ObservableList<Student_dataModel> tableStudent = FXCollections.observableArrayList();
    ObservableList<BatchPostingDataModel> tableDataBatchPosting = FXCollections.observableArrayList();
    @FXML
    private TableColumn<BatchPostingDataModel, String> Amount_;
    @FXML
    private MenuItem Add_to_Batch;
    @FXML
    private Button PostCredit;
    @FXML
    private MenuItem RemovedEntry;
    @FXML
    private Button Clear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        L_StudentID.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        L_Name.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        B_Name.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        B_TableID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        Amount_.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        loadcombo_data();
        loaddata();
    }

    @FXML
    private void Close(ActionEvent event) {
    }

    void loaddata() {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        tableStudent.clear();
        try {
            ps = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableStudent.add(new Student_dataModel(rs.getString("Student_ID"), rs.getString("L_Name") + ", " + rs.getString("F_Name") + " " + rs.getString("M_Name")));
                System.out.println(rs.getString("Student_ID"));
            }

            Name_table.setItems(tableStudent);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Add_toBatch_Posting(ActionEvent event) {

        TablePosition pos = Name_table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        System.out.println(row + " row");
        Student_dataModel item = Name_table.getItems().get(row);
        System.out.println(item);
        TableColumn col = pos.getTableColumn();
        String Data = (String) col.getCellObservableValue(item).getValue();
        System.out.println(Data);
        Student_dataModel model = tableStudent.get(row);
        System.out.println(model.getStudentID() + " " + model.getStudentName());
        String Amount_ = Amount.getText();

        if (Amount_.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);

            // Set the title of the alert box
            alert.setTitle("Error Dialog");

            // Set the header of the alert box
            alert.setHeaderText("An error has occurred!");

            // Set the content of the alert box
            alert.setContentText("Credit Amount Empty");

            // Show the alert box
            alert.showAndWait();
        } else if (Amount_.matches("-?\\d+(\\.\\d+)?")) {
            tableDataBatchPosting.add(new BatchPostingDataModel(model.getStudentID(), model.getStudentName(), Double.parseDouble(Amount.getText())));
            BatchPostingTable.setItems(tableDataBatchPosting);

        } else {

        }
    }

    @FXML
    private void Post_Credit(ActionEvent event) {

        Alert alert1 = new Alert(AlertType.CONFIRMATION,
                "Items on the Credit Batch Posting will be posted, do you want to continue?", // Prompt message
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

        alert1.showAndWait();

        if (alert1.getResult() == ButtonType.YES) {
            boolean status = false;
            try {
                DBConnection.init();
                Connection c = DBConnection.getConnection();
                for (int i = 0; i < tableDataBatchPosting.size(); i++) {
                    PreparedStatement ps = c.prepareStatement("SELECT count(OR_) FROM dcaa_pos.credit_history where OR_ like '%C%'");
                    ResultSet rs = ps.executeQuery();
                    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

                    if (rs.next()) {
                        String OR_Credit = "C" + String.format("%09d", Integer.parseInt(rs.getString(1)) + 1);
                        System.out.println(OR_Credit);

                        BatchPostingDataModel post = tableDataBatchPosting.get(i);
                        ps = c.prepareStatement("Insert into credit_history (StudentID, Credit, createtime, userID, Transaction_type, OR_)values ('" + post.StudentID + "','" + post.getAmount() + "','" + timeStamp + "','" + 1 + "','credit','" + OR_Credit + "') ");
                        if (!ps.execute()) {
                            System.out.println("Posted " + post.StudentID + " " + post.getAmount());
                            status = true;
                        } else {
                            System.out.println("Not Posted" + post.getStudentID() + " " + post.getAmount());
                        }
                    }

                }

                if (status) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Student Credit ");
                    alert.setHeaderText("Batch Posting of credit is successful!");
                    alert.setContentText("");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        tableDataBatchPosting.clear();
                        BatchPostingTable.setItems(tableDataBatchPosting);
                        Amount.clear();
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Student_CreditController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Item deleted.");
        } else {
            // Handle other cases (NO or CANCEL)
            System.out.println("Action canceled.");
        }

    }

    void loadcombo_data() {

        ObservableList<String> data = FXCollections.observableArrayList();

        data.add("Student Id");
        data.add("Student Last Name");
        data.add("Student First Name");
        data.add("Student Middle Name");
        select.setItems(data);
        //OptionsCombo.setValue("Select User Level");

    }

    @FXML
    private void Search(ActionEvent event) {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        String ItemName = "";
        String Search = "%" + this.Search.getText() + "%";

        PreparedStatement ps;
        ResultSet rs = null;
        tableStudent.clear();
        try {

            switch (this.select.getSelectionModel().getSelectedIndex()) {
                case 0:
                    ps = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where Student_ID like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                case 1:
                    ps = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where L_Name like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                case 2:
                    ps = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where F_name like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                case 3:
                    ps = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where M_name like '" + Search + "'");
                    rs = ps.executeQuery();
                    break;
                default:
                    ps = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info");
                    rs = ps.executeQuery();
                    break;
            }

            while (rs.next()) {
                tableStudent.add(new Student_dataModel(rs.getString("Student_ID"), rs.getString("L_Name") + ", " + rs.getString("F_Name") + " " + rs.getString("M_Name")));
                System.out.println(rs.getString("Student_ID"));
            }

            Name_table.setItems(tableStudent);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RemovedEntry(ActionEvent event) {
        TablePosition pos = BatchPostingTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        System.out.println(row + " row");
        tableDataBatchPosting.remove(row);
        Name_table.setItems(tableStudent);
    }

    @FXML
    private void Clear(ActionEvent event) {
        tableDataBatchPosting.clear();
        Name_table.setItems(tableStudent);
        Amount.clear();

    }
}
