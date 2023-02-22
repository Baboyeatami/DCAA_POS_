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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SaveData(ActionEvent event) {
        save_Student_info();
    }

    void save_Student_info() {

        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;
            ResultSet rs = null;

            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat date2 = new SimpleDateFormat("yyyy/MM/d");
            SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");

            String Date = date2.format(cal.getTime());
            String Time = time.format(cal.getTime());

            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/d");

            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            ps = c.prepareStatement("SELECT count(Student_ID) FROM dcaa_pos.student_info where Student_ID='" + Student_ID.getText() + "'");
            rs = ps.executeQuery();

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
                    //System.out.println("hahahahahhah 111");

                    ps = c.prepareStatement("Insert into dcaa_pos.student_info (F_name, M_name, L_Name, Student_ID, createtime, userID)values" + "('" + F_name.getText() + "','" + Middle.getText() + "','" + Last.getText() + "','" + Student_ID.getText() + "','" + timeStamp + "','test')");

                    if (!ps.execute()) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("New Data Status");
                        alert.setHeaderText("");
                        alert.setContentText("Data Successfully Added!");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {

                        }

                        break;

                    }

                }

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
