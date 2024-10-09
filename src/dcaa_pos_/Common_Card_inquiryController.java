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
import javafx.scene.text.Text;
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
public class Common_Card_inquiryController implements Initializable {

    @FXML
    private Button Button_scan;
    @FXML
    private TextField Card_number;
    @FXML
    private Text Credit_details;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Scan_Card(ActionEvent event) {
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
                Card_number.setText(uid);
                card.disconnect(true);
                System.out.println("Card disconnected. Waiting for new card...");

                DBConnection.init();
                Connection c = DBConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                    ps = c.prepareStatement("SELECT \n"
                            + "    SUM(CASE WHEN Transaction_type = 'credit' THEN Credit ELSE 0 END) AS total_credit,\n"
                            + "    SUM(CASE WHEN Transaction_type = 'debit' THEN Credit ELSE 0 END) AS total_debit,\n"
                            + "    SUM(CASE WHEN Transaction_type = 'credit' THEN Credit ELSE 0 END) - \n"
                            + "    SUM(CASE WHEN Transaction_type = 'debit' THEN Credit ELSE 0 END) AS balance\n"
                            + "FROM \n"
                            + "  dcaa_pos.credit_history where NFC_Card_No='" + uid + "'");
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        Credit_details.setText("Credit Available:" + rs.getString("balance"));
                    } else {
                        Credit_details.setText("Credit Not Available:");
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

}
