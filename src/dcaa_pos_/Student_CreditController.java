package dcaa_pos_;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import dcaa_pos_.DBConnection;
import dcaa_pos_.FXMLDocumentController;
import dcaa_pos_.Inventory_Data_Model;
import dcaa_pos_.Student_Credit_history_data_Model;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class Student_CreditController implements Initializable {

    @FXML
    private MenuItem Select_student;
    @FXML
    private TableColumn<Student_Credit_history_data_Model, String> Student_Id_table;
    @FXML
    private TableColumn<Student_Credit_history_data_Model, String> StudentName;
    //private TableColumn<Student_Credit_history_data_Model, String> transaction_ID;
    @FXML
    private TableColumn<Student_Credit_history_data_Model, String> OR;
    @FXML
    private TableColumn<Student_Credit_history_data_Model, String> Amount;
    @FXML
    private TableColumn<Student_Credit_history_data_Model, String> TransactionType;
    @FXML
    private TableColumn<Student_Credit_history_data_Model, String> Date;
    @FXML
    private Label Student_ID;
    @FXML
    private Label Student_name;
    @FXML
    private TableView<Student_Credit_history_data_Model> TableStudent;
    @FXML
    private TableView<Student_Credit_history_data_Model> TableTransaction;

    ObservableList<Student_Credit_history_data_Model> tableDataTransaction = FXCollections.observableArrayList();
    ObservableList<Student_Credit_history_data_Model> tableDataStudentList = FXCollections.observableArrayList();
    @FXML
    private Label Total_credit;
    @FXML
    private Button AddCredit;
    @FXML
    private Label labelCredit;
    @FXML
    private Button AddStudent;
    @FXML
    private TextField AddCredit_;
    @FXML
    private Button Close_;
    @FXML
    private MenuItem Update_menu;
    @FXML
    private ComboBox<String> Search;
    @FXML
    private TextField SearchBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Student_Id_table.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        StudentName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        //transaction_ID.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
        OR.setCellValueFactory(new PropertyValueFactory<>("OR"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        TransactionType.setCellValueFactory(new PropertyValueFactory<>("TransactionType"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        loaddata();
        //loaddata_Credit();
        labelCredit.setVisible(false);
        AddCredit_.setVisible(false);
        loadcombo_data();
    }
    String Data, NFC_Card_No;

    @FXML
    private void Load_Selected_Student(ActionEvent event) {
        try {
            TablePosition pos = TableStudent.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            System.out.println(row + " row");
            Student_Credit_history_data_Model item = TableStudent.getItems().get(row);
            TableColumn col = pos.getTableColumn();

            Data = (String) col.getCellObservableValue(item).getValue();
            System.out.println(Data);

            DBConnection.init();

            Connection c = DBConnection.getConnection();

            PreparedStatement ps = c.prepareStatement("SELECT  NFC_Card_No,F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where Student_ID='" + Data + "' ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student_ID.setText("Student ID: " + rs.getString("Student_ID") + "   " + "NFC:" + rs.getString("NFC_Card_No"));
                Student_name.setText("Student Name: " + rs.getString("L_name") + ", " + rs.getString("F_name") + " " + rs.getString("M_name"));
                NFC_Card_No = rs.getString("NFC_Card_No");
                loaddata_Credit(Data);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student_CreditController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void loaddata() {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        tableDataStudentList.clear();
        try {
            ps = c.prepareStatement("SELECT  F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableDataStudentList.add(new Student_Credit_history_data_Model(rs.getString("Student_ID"), rs.getString("L_Name") + ", " + rs.getString("F_Name") + " " + rs.getString("M_Name")));
                System.out.println(rs.getString("Student_ID"));
            }

            TableStudent.setItems(tableDataStudentList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loaddata_Credit(String StudentID) {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;
        tableDataTransaction.clear();
        Total_credit.setText("Total Credit:  Php " + 0);
        try {
            ps = c.prepareStatement("SELECT  idCredit_history, StudentID, Credit, createtime, userID, Transaction_type, OR_ FROM dcaa_pos.credit_history where StudentID='" + StudentID + "' order by createtime DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableDataTransaction.add(new Student_Credit_history_data_Model(rs.getString("idCredit_history"), rs.getString("OR_"), rs.getString("Credit"), rs.getString("Transaction_type"), rs.getString("createtime")));
                System.out.println(rs.getString(1));
            }

            TableTransaction.setItems(tableDataTransaction);

            ps = c.prepareStatement("SELECT   sum(Credit) FROM dcaa_pos.credit_history where StudentID='" + Data + "' and Transaction_type='debit'");
            rs = ps.executeQuery();
            if (rs.next()) {
                double debit;
                if (rs.getString(1) == null) {
                    debit = 0;
                } else {
                    debit = Double.parseDouble(rs.getString(1));
                }

                ps = c.prepareStatement("SELECT   sum(Credit) FROM dcaa_pos.credit_history where StudentID='" + Data + "' and Transaction_type='credit'");
                rs = ps.executeQuery();
                double Credit = 0;
                if (rs.next()) {
                    if (rs.getString(1) == null) {
                        Credit = 0;
                    } else {
                        Credit = Double.parseDouble(rs.getString(1));
                    }
                }

                double totalCredit = Credit - debit;

                Total_credit.setText("Total Credit:  Php " + totalCredit);

            }

            ps.close();
            rs.close();
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void AddCredit(ActionEvent event) {
        AddCredit_to_Student();
    }

    @FXML
    private void View_AddCredit(ActionEvent event) {
        labelCredit.setVisible(true);
        AddCredit_.setVisible(true);
        AddCredit_.requestFocus();

    }

    void AddCredit_to_Student() {

        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT count(OR_) FROM dcaa_pos.credit_history where OR_ like '%C%'");
            ResultSet rs = ps.executeQuery();
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            if (rs.next()) {
                String OR_Credit = "C" + String.format("%09d", Integer.parseInt(rs.getString(1)) + 1);
                System.out.println(OR_Credit);

                ps = c.prepareStatement("Insert into credit_history (StudentID, Credit, createtime, userID, Transaction_type, OR_,NFC_Card_No)values ('" + Data + "','" + AddCredit_.getText() + "','" + timeStamp + "','" + 1 + "','credit','" + OR_Credit + "','" + NFC_Card_No + "') ");
                if (!ps.execute()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Student Credit ");
                    alert.setHeaderText(AddCredit_.getText() + " Pesos has been Credited to Student ID:" + Data);
                    alert.setContentText("");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        AddCredit_.setText("");
                        AddCredit_.requestFocus();
                        NFC_Card_No = "";

                    }
                }
                loaddata_Credit(Data);
                labelCredit.setVisible(false);
                AddCredit_.setVisible(false);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Student_CreditController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Close(ActionEvent event) {
        Stage close = (Stage) AddCredit.getScene().getWindow();
        close.close();
    }

    @FXML
    private void Add_STudent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/Student_info.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Student Information");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Inventory_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Student_infoController studentinfo;

    @FXML
    private void Update_info(ActionEvent event) throws SQLException {
        try {

            TablePosition pos = TableStudent.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            System.out.println(row + " row");
            Student_Credit_history_data_Model item = TableStudent.getItems().get(row);
            TableColumn col = pos.getTableColumn();

            Data = (String) col.getCellObservableValue(item).getValue();
            System.out.println(Data);

            DBConnection.init();

            Connection c = DBConnection.getConnection();

            PreparedStatement ps = c.prepareStatement("SELECT  NFC_Card_No,F_name, M_name, L_Name, Student_ID FROM dcaa_pos.student_info where Student_ID='" + Data + "' ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/Student_info.fxml"));
                Parent root1 = loader.load();
                studentinfo = loader.getController();
                studentinfo.update_Student(rs.getString("Student_ID"), rs.getString("F_name"), rs.getString("M_name"), rs.getString("L_name"), rs.getString("NFC_Card_No"), this);
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Student Information Update");
                stage.setScene(new Scene(root1));
                stage.show();

            }

        } catch (IOException ex) {
            Logger.getLogger(Student_CreditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Search_(ActionEvent event) {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        String ItemName = "";
        String Search = "%" + SearchBox.getText() + "%";

        PreparedStatement ps;
        ResultSet rs = null;
        tableDataStudentList.clear();
        try {

            switch (this.Search.getSelectionModel().getSelectedIndex()) {
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
                tableDataStudentList.add(new Student_Credit_history_data_Model(rs.getString("Student_ID"), rs.getString("L_Name") + ", " + rs.getString("F_Name") + " " + rs.getString("M_Name")));
                System.out.println(rs.getString("Student_ID"));
            }

            TableStudent.setItems(tableDataStudentList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadcombo_data() {

        ObservableList<String> data = FXCollections.observableArrayList();

        data.add("Student Id");
        data.add("Student Last Name");
        data.add("Student First Name");
        data.add("Student Middle Name");
        Search.setItems(data);
        //OptionsCombo.setValue("Select User Level");

    }
}
