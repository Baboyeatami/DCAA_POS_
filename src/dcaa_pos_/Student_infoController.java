/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dcaa_pos_;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import org.opencv.core.Mat;

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

    @FXML
    private ImageView imageView;
    @FXML
    private Button FileChooser;
    @FXML
    private Button ImageCapture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Btn_Update.setDisable(update);
        NFC_textbox.setDisable(true);
        imageView.setFitWidth(280);
        imageView.setFitHeight(300);

    }

    @FXML
    private void SaveData(ActionEvent event) throws FileNotFoundException {
        try {
            save_Student_info();
        } catch (IOException ex) {
            Logger.getLogger(Student_infoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void update_disable() {

    }

    void save_Student_info() throws FileNotFoundException, IOException {

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
            File file = new File(System.getProperty("user.dir") + "\\image.png");

            FileInputStream inputStream = new FileInputStream(file);
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

                        ps = c.prepareStatement("Insert into dcaa_pos.student_info (F_name, M_name, L_Name, Student_ID, createtime, userID,NFC_Card_No,image_data)values" + "('" + F_name.getText() + "','" + Middle.getText() + "','" + Last.getText() + "','" + Student_ID.getText() + "','" + timeStamp + "','test','" + NFC_textbox.getText() + "',?)");
                        ps.setBinaryStream(1, inputStream);
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
                FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\image.png");
                System.out.println("wow");
                ps = c.prepareStatement("Update dcaa_pos.student_info Set F_name='" + F_name.getText() + "', M_name='" + Middle.getText() + "', L_Name='" + Last.getText() + "', Student_ID='" + Student_ID.getText() + "', userID='test', NFC_Card_No='" + NFC_textbox.getText() + "',image_data=? where Student_ID='" + Student_ID.getText() + "' ");

                ps.setBinaryStream(1, fileInputStream, fileInputStream.available());

                if (!ps.execute()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Student Information Update");
                    alert.setHeaderText("");
                    alert.setContentText("Data Successfull Updated!");
                    update = false;
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        System.out.println("clear");

                        student_CreditController.loaddata();
                    }

                }

                try {
                    ps = c.prepareStatement("SET SQL_SAFE_UPDATES = 0;");
                    ps.execute();

                    ps = c.prepareStatement("Update dcaa_pos.credit_history Set NFC_Card_No=? where StudentID=?");
                    ps.setString(1, NFC_textbox.getText());
                    ps.setString(2, Student_ID.getText());
                    int rowsUpdated = ps.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Update successful. " + rowsUpdated + " rows updated.");
                    } else {
                        System.out.println("Update not successful. No rows matched the condition.");
                    }

                    ps = c.prepareStatement("SET SQL_SAFE_UPDATES = 1;");
                    ps.execute();
                    ps.close();
                    rs.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            Clear();

        } catch (SQLException ex) {
            ex.printStackTrace();
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

    void update_Student(String Id, String F_name, String M_name, String L_name, String NFC, Student_CreditController a) throws SQLException, IOException {
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
        imageView.setImage(null);
        //update = false;

    }

    void Load_image_data(String id) throws IOException {
        try {
            DBConnection.ReadIPaddress();
            DBConnection.init();
            PreparedStatement ps;
            ResultSet rs;
            Connection c = DBConnection.getConnection();
            ps = c.prepareStatement("SELECT image_data FROM dcaa_pos.student_info where Student_ID='" + id + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("image_data");
                InputStream inputStream;
                Image image;
                if (blob != null) {
                    inputStream = blob.getBinaryStream();

                    image = new Image(inputStream);
                    imageView.setImage(image);
                } else {
                    File file = new File(System.getProperty("user.dir") + "\\no-avatar.png");

                    inputStream = new FileInputStream(file);
                    image = new Image(inputStream);  // Replace with the actual path to your .png file

                    imageView.setImage(image);

                }

            }

            //System.out.println(ps.execute());
        } catch (SQLException ex) {
            Logger.getLogger(Student_infoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Set_captured_Image() throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "\\image.png");

        inputStream = new FileInputStream(file);
        Image image = new Image(inputStream);  // Replace with the actual path to your .png file

        imageView.setImage(image);

    }

    FileInputStream inputStream;

    @FXML
    private void SelectFile(ActionEvent event) throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg"),
                new ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(Btn_Update.getScene().getWindow());
        if (selectedFile != null) {
            File file = new File(selectedFile.getAbsolutePath());

            inputStream = new FileInputStream(file);
            Image image = new Image(inputStream);  // Replace with the actual path to your .png file

            // Convert the Image to a BufferedImage
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);

            try {
                // Save the BufferedImage to a file
                ImageIO.write(bImage, "png", new File(System.getProperty("user.dir") + "\\image.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            imageView.setImage(image);
        }
    }

    @FXML
    private void ImageCapture_(ActionEvent event) throws IOException {
        WebcamCaptureGUI1 captureGUI1 = null;
        if (captureGUI1 == null) {
            captureGUI1 = new WebcamCaptureGUI1();
            captureGUI1.initialize();
            captureGUI1.student_infoController = this;
        } else {
            captureGUI1 = null;
        }

    }

    private void saveImageToDatabase(String IdString) throws SQLException {
        try {
            // Implement database connectivity and insertion logic here
            // Use JDBC to connect to your MySQL database and store the image data
            // Make sure to handle exceptions and close the database connection properly

            DBConnection.ReadIPaddress();
            DBConnection.init();
            PreparedStatement ps;
            ResultSet rs;
            Connection c = DBConnection.getConnection();

            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\image.png");

            ps = c.prepareStatement("Update student_info set image_data=?  where Student_ID=?");
            ps.setBinaryStream(1, fileInputStream, fileInputStream.available());
            ps.setString(2, IdString);

            System.out.println(ps.execute());

        } catch (IOException ex) {

        }
    }

}
