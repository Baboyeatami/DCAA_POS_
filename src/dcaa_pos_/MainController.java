/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

import com.sun.applet2.preloader.event.UserDeclinedEvent;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class MainController implements Initializable {

    @FXML
    private Button POS_;
    @FXML
    private Button Sales_;
    @FXML
    private Button User_Accounts;
    @FXML
    private Button Items_;
    @FXML
    private Label Username;
    @FXML
    private Label Date_time;
    @FXML
    private MenuItem Button_shortcut;
    @FXML
    private MenuBar sales_invoice;
    @FXML
    private Button Student;
    @FXML
    private Button StudentCreit;
    int UserId;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void MenuItemTest(ActionEvent event) {
        System.out.println("test test tes ");
    }

    @FXML
    private void Open_POS(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/POS_Main.fxml"));
            Parent root1 = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("POS Main Window");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Inventory_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Open_Sales(ActionEvent event) {

        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/dcaa_pos_/Sales_Invoice.fxml"));

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Item Inventory");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Open_UserAccounts(ActionEvent event) {

        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/dcaa_pos_/User_Accounts.fxml"));

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Item Inventory");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Open_Item_Inventory(ActionEvent event) {

        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/dcaa_pos_/Inventory_.fxml"));

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Item Inventory");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Button_Shortcuts_(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/Button_Settings.fxml"));
            Parent root1 = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Menu Short cut Settings");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Inventory_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void sales_invoice(MouseEvent event) {
    }

    @FXML
    private void Add_Student(ActionEvent event) {

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

    @FXML
    private void OpenCreditHistory(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/Student_Credit.fxml"));
            Parent root1 = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Student Credit");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Inventory_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
