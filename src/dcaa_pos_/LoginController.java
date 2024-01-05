/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.rsa.RSACore;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class LoginController implements Initializable {

    @FXML
    private Button Login;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    POS_MainController POS_Main;
    MainController Main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void check_Login() throws IOException {

        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT idusers,UserName,Password,usertype FROM dcaa_pos.users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                if (rs.getString("UserName").equals(this.UserName.getText()) && rs.getString("Password").equals(this.Password.getText())) {
                    if (rs.getString("usertype").equals("Casher")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/POS_Main.fxml"));
                        Parent root1 = loader.load();
                        POS_Main = loader.getController();
                        //POS_Main.setUserID(rs.getString("idusers"));
                        System.out.println(rs.getString("idusers") + "Login user ID");
                        POS_Main.setUserID(rs.getString("idusers"));

                        Stage stage = new Stage();
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initStyle(StageStyle.DECORATED);
                        stage.setTitle("POS Main Window");
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } else if (rs.getString("usertype").equals("Manager")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/Main.fxml"));
                        Parent root1 = loader.load();
                        Main = loader.getController();
                        Main.setUserId(rs.getString("idusers"));
                        System.out.println(Main.getUserId() + "login user id");

                        Stage stage = new Stage();
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setTitle("Main Window");
                        stage.setScene(new Scene(root1));
                        stage.show();
                    }

                }

                Stage close = (Stage) Login.getScene().getWindow();
                close.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Login_Via_Paswword(ActionEvent event) {
        try {
            check_Login();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Login(KeyEvent event) {
    }

    @FXML
    private void Login(MouseEvent event) {
    }

    @FXML
    private void Login_Button(ActionEvent event) {
        try {
            check_Login();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
