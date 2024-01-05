/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class DCAA_POS_ extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        try {

            Add_Buttons();
            check_Card_Coloum();
            check_Card_Credithistory();
            check_invoice_ramakrs();

        } catch (SQLException ex) {
            Logger.getLogger(DCAA_POS_.class.getName()).log(Level.SEVERE, null, ex);
        }

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.isAlwaysOnTop();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DBConnection.ReadIPaddress();
        launch(args);

    }

    static void check_invoice_ramakrs() throws SQLException {

        try {
            DBConnection.ReadIPaddress();
            DBConnection.init();
            PreparedStatement ps;
            ResultSet rs;
            Connection c = DBConnection.getConnection();
            ps = c.prepareStatement("Show columns from dcaa_pos.items where field ='Cost'");
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = c.prepareStatement("ALTER TABLE dcaa_pos.items ADD COLUMN Cost Double(15,2)");
                ps.execute();
                System.out.println("Alter table completed ");
            } else {
                System.out.println("Remarks coloum exist: no table altered");
            }

        } catch (IOException ex) {
            //Logger.getLogger(DCAA_BILLING.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static void check_Card_Coloum() throws SQLException {

        try {
            DBConnection.ReadIPaddress();
            DBConnection.init();
            PreparedStatement ps;
            ResultSet rs;
            Connection c = DBConnection.getConnection();
            System.out.println("Card Check");
            ps = c.prepareStatement("Show columns from dcaa_pos.student_info where field ='NFC_Card_No'");
            rs = ps.executeQuery();

            if (!rs.next()) {
                //System.out.println(rs.getString(1) + " Card Reading  check_Card_Coloum();");
                ps = c.prepareStatement("ALTER TABLE dcaa_pos.student_info ADD COLUMN NFC_Card_No varchar(100)");
                ps.execute();
                System.out.println("Alter table NFC_Card_No completed ");
            } else {
                System.out.println("NFC_Card_No coloum exist: no table altered");
            }

        } catch (IOException ex) {
            //Logger.getLogger(DCAA_BILLING.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static void check_Card_Credithistory() throws SQLException {

        try {
            DBConnection.ReadIPaddress();
            DBConnection.init();
            PreparedStatement ps;
            ResultSet rs;
            Connection c = DBConnection.getConnection();
            System.out.println("Card Check");
            ps = c.prepareStatement("Show columns from dcaa_pos.credit_history where field ='NFC_Card_No'");
            rs = ps.executeQuery();

            if (!rs.next()) {
                //System.out.println(rs.getString(1) + " Card Reading  check_Card_Coloum();");
                ps = c.prepareStatement("ALTER TABLE dcaa_pos.credit_history ADD COLUMN NFC_Card_No varchar(100)");
                ps.execute();
                System.out.println("Alter table NFC_Card_No in Credit History completed ");
            } else {
                System.out.println("NFC_Card_No coloum exist: no table altered");
            }

        } catch (IOException ex) {
            //Logger.getLogger(DCAA_BILLING.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Add_Buttons() throws SQLException {

        try {
            DBConnection.ReadIPaddress();
            DBConnection.init();
            PreparedStatement ps, ps1;
            ResultSet rs;
            Connection c = DBConnection.getConnection();

            ps = c.prepareStatement("SELECT count(Button_name) FROM dcaa_pos.shortcut_settings");
            rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getString(1).equals("60")) {
                    System.out.println("Button Complete");

                } else {

                    int a = Integer.parseInt(rs.getString(1));

                    for (int i = a + 1; i <= 60; i++) {
                        ps1 = c.prepareStatement("Insert into dcaa_pos.shortcut_settings (Button_name) values('" + i + "')");
                        if (!ps1.execute()) {
                            System.out.println(i);
                        }

                    }

                }

            }

            ps.close();

        } catch (IOException ex) {
            Logger.getLogger(DCAA_POS_.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
