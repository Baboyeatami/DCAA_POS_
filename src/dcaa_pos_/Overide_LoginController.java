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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Overide_LoginController implements Initializable {

    @FXML
    private Button Login_ok;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    POS_MainController main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Verfication_(ActionEvent event) {
        try {
            check_Login();
        } catch (IOException ex) {
            Logger.getLogger(Overide_LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Overide_LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void check_Login() throws IOException, SQLException {

        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = null;
            try {
                ps = c.prepareStatement("SELECT idusers,UserName,Password,usertype FROM dcaa_pos.users");
            } catch (SQLException ex) {
                Logger.getLogger(Overide_LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                if (rs.getString("UserName").equals(this.username.getText()) && rs.getString("Password").equals(this.password.getText())) {
                    System.out.println(rs.getString("usertype"));
                    if (rs.getString("usertype").equals("Manager")) {
                        main.Manager_overide = true;
                    } else {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid Credentials");
                        alert.showAndWait();
                    }

                    Stage close = (Stage) Login_ok.getScene().getWindow();
                    close.close();

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Password_Verify(ActionEvent event) {
        try {
            check_Login();
        } catch (IOException ex) {
            Logger.getLogger(Overide_LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Overide_LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
