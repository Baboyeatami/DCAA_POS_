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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Student_infoController implements Initializable {

    @FXML
    private TextField Student_ID;
    @FXML
    private TextField F_name;
    @FXML
    private TextField Middle;
    @FXML
    private TextField Last;
    @FXML
    private Button Close;
    @FXML
    private Button Scan_Card;
    @FXML
    private TextField NFC_textbox;
    boolean update = false;
    @FXML
    private Button Btn_Update;
    @FXML
    private Button Btn_dave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Btn_Update.setDisable(update);
        NFC_textbox.setDisable(true);
    }

    @FXML
    private void SaveData(ActionEvent event) {
        save_Student_info();
    }

    private void update_disable() {

    }

    void save_Student_info() {

        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;
            ResultSet rs = null;

            Calendar cal = Calendar.getInstance();
            cal.getTime();
            // SimpleDateFormat date2 = new SimpleDateFormat("yyyy/MM/d");
            //SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");

            //String Date = date2.format(cal.getTime());
            //String Time = time.format(cal.getTime());
            // SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/d");
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            ps = c.prepareStatement("SELECT count(Student_ID) FROM dcaa_pos.student_info where Student_ID='" + Student_ID.getText() + "'");
            rs = ps.executeQuery();

            if (!update) {
                while (rs.next()) {
                    int status = Integer.parseInt(rs.getString(1));

                    if (status != 0) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText("");
                        alert.setContentText("Student ID Already Exist!, Please Enter a valid Student ID ");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            Student_ID.setText("");
                            Student_ID.requestFocus();
                            break;
                        }

                    } else {

                        ps = c.prepareStatement("Insert into dcaa_pos.student_info (F_name, M_name, L_Name, Student_ID, createtime, userID,NFC_Card_No)values" + "('" + F_name.getText() + "','" + Middle.getText() + "','" + Last.getText() + "','" + Student_ID.getText() + "','" + timeStamp + "','test','" + NFC_textbox.getText() + "')");

                        if (!ps.execute()) {

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("New Data Status");
                            alert.setHeaderText("");
                            alert.setContentText("Data Successfully Added!");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                System.out.println("clear");
                                Clear();

                            }

                            break;

                        }

                    }

                }
            } else {
                ps = c.prepareStatement("Update dcaa_pos.student_info Set F_name='" + F_name.getText() + "', M_name='" + Middle.getText() + "', L_Name='" + Last.getText() + "', Student_ID='" + Student_ID.getText() + "', userID='test', NFC_Card_No='" + NFC_textbox.getText() + "' where Student_ID='" + Student_ID.getText() + "' ");
                //rs = ps.executeQuery();
                if (!ps.execute()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Student Information Update");
                    alert.setHeaderText("");
                    alert.setContentText("Data Successfull Updated!");
                    update = false;
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        System.out.println("clear");
                        Clear();
                        student_CreditController.loaddata();
                    }

                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void Close(ActionEvent event) {
        Stage close = (Stage) Close.getScene().getWindow();
        close.close();
    }

    @FXML
    private void Scan_Card(ActionEvent event) throws SQLException {

        try {
            TerminalFactory terminalFactory = TerminalFactory.getDefault();
            CardTerminals terminals = terminalFactory.terminals();

            if (terminals.list().isEmpty()) {
                System.out.println("No card terminals found.");
                return;
            }

            CardTerminal terminal = terminals.list().get(0);
            System.out.println("Waiting for card...");

            if (terminal.isCardPresent()) {
                Card card = terminal.connect("*");
                CardChannel channel = card.getBasicChannel();

                // Select the NFC card's application (if applicable)
                // This might involve sending APDU commands
                // Example: byte[] selectAppCommand = {...};
                // ResponseAPDU response = channel.transmit(new CommandAPDU(selectAppCommand));
                // Send APDU command to get card UID
                byte[] getUidCommand = {(byte) 0xFF, (byte) 0xCA, 0x00, 0x00, 0x00};
                ResponseAPDU response = channel.transmit(new CommandAPDU(getUidCommand));

                byte[] uidBytes = response.getData();
                String uid = bytesToHex(uidBytes);
                System.out.println("Card UID: " + uid);
                NFC_textbox.setText(uid);
                card.disconnect(true);
                System.out.println("Card disconnected. Waiting for new card...");

                DBConnection.init();
                Connection c = DBConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                    ps = c.prepareStatement("SELECT count(NFC_Card_No) FROM dcaa_pos.student_info where NFC_Card_No='" + uid + "'");
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        int a = Integer.parseInt(rs.getString(1));
                        System.out.println("Card Count:" + rs.getString(1));
                        if (a == 0) {
                            System.out.println("card is valid");
                        } else {
                            System.out.println("card is invalid");

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Card Status");
                            alert.setHeaderText("");
                            alert.setContentText("Card Already Registered!\n Please try another card..");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                NFC_textbox.setText("");

                            }

                        }
                        a = 0;
                    }

                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Student_infoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (CardException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
    Student_CreditController student_CreditController;

    void update_Student(String Id, String F_name, String M_name, String L_name, String NFC, Student_CreditController a) {
        this.F_name.setText(F_name);
        Student_ID.setText(Id);
        Middle.setText(M_name);
        Last.setText(L_name);
        NFC_textbox.setText(NFC);
        update = true;
        Btn_dave.setDisable(true);
        student_CreditController = a;

    }

    void Clear() {
        this.F_name.setText("");
        Student_ID.setText("");
        Middle.setText("");
        Last.setText("");
        NFC_textbox.setText("");
        Student_ID.requestFocus();
        //update = false;

    }

}
