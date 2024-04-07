/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

import com.sun.applet2.preloader.event.UserDeclinedEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class POS_MainController implements Initializable {

    @FXML
    private MenuItem item_1;
    @FXML
    private MenuItem Item_2;
    @FXML
    private MenuItem item_3;
    @FXML
    private Button Button_8;
    @FXML
    private Button Button_7;
    @FXML
    private Button Button_0;
    @FXML
    private Button Button_3;
    @FXML
    private Button Button_1;
    @FXML
    private Button Button_2;
    @FXML
    private Button Button_4;
    @FXML
    private Button Button_5;
    @FXML
    private Button Button_6;
    @FXML
    private Button Button_9;
    @FXML
    private Button Button_10;
    @FXML
    private Button Button_11;
    @FXML
    private Button Button_12;
    @FXML
    private Button Button_13;
    @FXML
    private Button Button_14;

    ArrayList<Item_data_> Button_List = new ArrayList<>();
    ArrayList<Selected_Item> Selected_Item = new ArrayList<>();
    @FXML
    private TextField Quantity;
    int Item_index;
    double Current = 0.0;
    @FXML
    private Label Item_selected_label;
    @FXML
    private TableColumn<Selected_Item, String> Item_id;
    @FXML
    private TableColumn<Selected_Item, String> Item_name;
    @FXML
    private TableColumn<Selected_Item, Double> Price_;
    @FXML
    private TableColumn<Selected_Item, Double> Quantity_;
    @FXML
    private TableColumn<Selected_Item, String> Sub_total_;
    @FXML
    private Menu Menu_items;
    @FXML
    private AnchorPane Main_Pane;
    ObservableList<Selected_Item> tableData = FXCollections.observableArrayList();
    @FXML
    private TableView<Selected_Item> TablePOS;
    @FXML
    private Label Grand_Total;
    @FXML
    private MenuItem Item_13;
    @FXML
    private MenuItem Item_4;
    @FXML
    private MenuItem item_5;
    @FXML
    private MenuItem item_6;
    @FXML
    private MenuItem item_7;
    @FXML
    private MenuItem item_8;
    @FXML
    private MenuItem item_9;
    @FXML
    private MenuItem item_10;
    @FXML
    private MenuItem item_11;
    @FXML
    private MenuItem item_12;
    @FXML
    private MenuItem Payment;
    @FXML
    private TextField CASH;
    @FXML
    private Label Grand_Total1;
    @FXML
    private TextField Student_Id;
    @FXML
    private Label OR_Number;
    @FXML
    private Label Change;
    @FXML
    private Label Mode_label;
    @FXML
    private Label Items_Label;
    @FXML
    private MenuItem Student_id;
    @FXML
    private MenuItem Item_Search;

    POS_searchController POS_searchController;
    POS_ReadBarcode POS_Barcode;
    private String UserID;

    @FXML
    private Label label_sales;
    @FXML
    private Button Button_15;
    @FXML
    private Button Button_16;
    @FXML
    private Button Button_17;
    @FXML
    private Button Button_31;
    @FXML
    private Button Button_21;
    @FXML
    private Button Button_41;
    @FXML
    private Button Button_51;
    @FXML
    private Button Button_23;
    @FXML
    private Button Button_22;
    @FXML
    private Button Button_18;
    @FXML
    private Button Button_19;
    @FXML
    private Button Button_20;
    @FXML
    private Button Button_24;
    @FXML
    private Button Button_25;
    @FXML
    private Button Button_26;
    @FXML
    private Button Button_27;
    @FXML
    private Button Button_28;
    @FXML
    private Button Button_29;
    @FXML
    private Button Button_38;
    @FXML
    private Button Button_37;
    @FXML
    private Button Button_30;
    @FXML
    private Button Button_33;
    @FXML
    private Button Button_32;
    @FXML
    private Button Button_34;
    @FXML
    private Button Button_35;
    @FXML
    private Button Button_36;
    @FXML
    private Button Button_39;
    @FXML
    private Button Button_40;
    @FXML
    private Button Button_42;
    @FXML
    private Button Button_43;
    @FXML
    private Button Button_44;
    @FXML
    private Button Button_53;
    @FXML
    private Button Button_52;
    @FXML
    private Button Button_45;
    @FXML
    private Button Button_48;
    @FXML
    private Button Button_46;
    @FXML
    private Button Button_47;
    @FXML
    private Button Button_49;
    @FXML
    private Button Button_50;
    @FXML
    private Button Button_54;
    @FXML
    private Button Button_55;
    @FXML
    private Button Button_56;
    @FXML
    private Button Button_57;
    @FXML
    private Button Button_59;
    @FXML
    private Button Button_58;
    @FXML
    private Label StudentCredit;
    @FXML
    private Button Scan_NFC;

    String NFC_no;

    @FXML
    private Button PayCard_;

    @FXML
    private MenuItem removed_Item;
    @FXML
    private ContextMenu yyyyy;
    @FXML
    private ImageView Imageview;
    @FXML
    private Label LabelName;
    @FXML
    private MenuItem Overide_;
    @FXML
    private MenuItem R_Barcode;

    public void setUserID(String UserID) {
        this.UserID = UserID;
        System.out.println(this.UserID + " User ID for POS Main");
        Compute_Collection(getUserID());
    }

    public String getUserID() {
        return this.UserID;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Item_id.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        Item_name.setCellValueFactory(new PropertyValueFactory<>("ItemTypeID"));
        Price_.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Quantity_.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        Sub_total_.setCellValueFactory(new PropertyValueFactory<>("Sub_total"));

        Button_0.setText("Button 0");
        Button_setup();
        Quantity.requestFocus();
        Main_Pane.requestFocus();
        System.out.println(Button_List.get(0).ProductName);

        LOAD_OR_Number();
        System.out.println(getUserID() + "USER ID for POS");

    }

    void Load_Data() {
        DBConnection.init();
        Connection c = DBConnection.getConnection();
        PreparedStatement ps;

    }

    private void item1(ActionEvent event) {
        System.out.println("Item 0 Selected");
        Item_index = 0;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
    }

    @FXML
    private void item_1(ActionEvent event) {
        Item_index = 0;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 1 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_2(ActionEvent event) {
        Item_index = 1;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 2 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_3(ActionEvent event) {
        Item_index = 2;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 3__");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    void Button_setup() {

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = c.prepareStatement("SELECT * FROM dcaa_pos.shortcut_settings");
            ResultSet rs = ps.executeQuery();

            double price = 0.0;
            while (rs.next()) {
                if (rs.getString("Price") != null) {
                    price = Double.parseDouble(rs.getString("Price"));
                } else {
                    price = 0.0;
                }
                Button_List.add(new Item_data_(rs.getString("idShortcut_Settings"), rs.getString("ID"), rs.getString("Product_Name"), rs.getString("item_type_id"), rs.getString("Button_name"), price));
                System.out.println(rs.getString("ID"));
            }

            c.close();
            ps.close();

            Button_0.setText(Button_List.get(0).ProductName + "  F1");
            Button_1.setText(Button_List.get(1).ProductName + "  F2");
            Button_2.setText(Button_List.get(2).ProductName + "  F3");
            Button_3.setText(Button_List.get(3).ProductName + "  F4");
            Button_4.setText(Button_List.get(4).ProductName + "  F5");
            Button_5.setText(Button_List.get(5).ProductName + "  F6");
            Button_6.setText(Button_List.get(6).ProductName + "  F7");
            Button_7.setText(Button_List.get(7).ProductName + "  F8");
            Button_8.setText(Button_List.get(8).ProductName + "  F9");
            Button_9.setText(Button_List.get(9).ProductName + "  F10");
            Button_10.setText(Button_List.get(10).ProductName + "  F11");
            Button_11.setText(Button_List.get(11).ProductName + "  F12");
            Button_12.setText(Button_List.get(12).ProductName + "  1");
            Button_13.setText(Button_List.get(13).ProductName + "  2");
            Button_14.setText(Button_List.get(14).ProductName + "  3");
            Button_15.setText(Button_List.get(15).ProductName + " 15");
            Button_16.setText(Button_List.get(16).ProductName + " 16");
            Button_17.setText(Button_List.get(17).ProductName + " 17");
            Button_18.setText(Button_List.get(18).ProductName + " 18");
            Button_19.setText(Button_List.get(19).ProductName + " 19");
            Button_20.setText(Button_List.get(20).ProductName + " 20");
            Button_21.setText(Button_List.get(21).ProductName + " 21");
            Button_22.setText(Button_List.get(22).ProductName + " 22");
            Button_23.setText(Button_List.get(23).ProductName + " 23");
            Button_24.setText(Button_List.get(24).ProductName + " 24");
            Button_25.setText(Button_List.get(25).ProductName + " 25");
            Button_26.setText(Button_List.get(26).ProductName + " 26");
            Button_27.setText(Button_List.get(27).ProductName + " 27");
            Button_28.setText(Button_List.get(28).ProductName + " 28");
            Button_29.setText(Button_List.get(29).ProductName + " 29");
            Button_30.setText(Button_List.get(30).ProductName + " 30");
            Button_31.setText(Button_List.get(31).ProductName + " 31");
            Button_32.setText(Button_List.get(32).ProductName + " 32");
            Button_33.setText(Button_List.get(33).ProductName + " 33");
            Button_34.setText(Button_List.get(34).ProductName + " 34");
            Button_35.setText(Button_List.get(35).ProductName + " 35");
            Button_36.setText(Button_List.get(36).ProductName + " 36");
            Button_37.setText(Button_List.get(37).ProductName + " 37");
            Button_38.setText(Button_List.get(38).ProductName + " 38");
            Button_39.setText(Button_List.get(39).ProductName + " 39");
            Button_40.setText(Button_List.get(40).ProductName + " 40");
            Button_41.setText(Button_List.get(41).ProductName + " 41");
            Button_42.setText(Button_List.get(42).ProductName + " 42");
            Button_43.setText(Button_List.get(43).ProductName + " 43");
            Button_44.setText(Button_List.get(44).ProductName + " 44");
            Button_45.setText(Button_List.get(45).ProductName + " 45");
            Button_46.setText(Button_List.get(46).ProductName + " 46");
            Button_47.setText(Button_List.get(47).ProductName + " 47");
            Button_48.setText(Button_List.get(48).ProductName + " 48");
            Button_49.setText(Button_List.get(49).ProductName + " 49");
            Button_50.setText(Button_List.get(50).ProductName + " 50");
            Button_51.setText(Button_List.get(51).ProductName + " 51");
            Button_52.setText(Button_List.get(52).ProductName + " 52");
            Button_53.setText(Button_List.get(53).ProductName + " 53");
            Button_54.setText(Button_List.get(54).ProductName + " 54");
            Button_55.setText(Button_List.get(55).ProductName + " 55");
            Button_56.setText(Button_List.get(56).ProductName + " 56");
            Button_57.setText(Button_List.get(57).ProductName + " 57");
            Button_58.setText(Button_List.get(58).ProductName + " 58");
            Button_59.setText(Button_List.get(59).ProductName + " 59");

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void loaddata() {

    }

    double Total = 0;
    int No_Items = 0;

    @FXML
    private void Calculate_selected_item(ActionEvent event) {
        calculate_selected();
    }

    boolean Manager_overide = false;

    void Set_Quantity(String input) {

        Quantity.setText(input);
    }

    void calculate_selected() {
        try {

            Total = 0;
            No_Items = 0;

            if (Quantity.getText().equals("0") || Quantity.getText().equals("")) {
                Main_Pane.requestFocus();
                Mode_label.setText("Mode: Select Item to continue..");

            } else {

                String input = Quantity.getText();

                if (input.matches("\\d+(\\.\\d+)?")) {

                    System.out.println("trasaction Calculation Manager Overide :" + Manager_overide);
                    DecimalFormat d = new DecimalFormat(("#,###.00"));

                    if (Manager_overide) {
                        Selected_Item.add(new Selected_Item(Button_List.get(Item_index).getID(), Button_List.get(Item_index).getProductName(), Double.parseDouble(input), Button_List.get(Item_index).Price));

                    } else {
                        Selected_Item.add(new Selected_Item(Button_List.get(Item_index).getID(), Button_List.get(Item_index).getProductName(), Integer.parseInt(Quantity.getText()), Button_List.get(Item_index).Price));
                    }

                    //Selected_Item.add(new Selected_Item(Button_List.get(Item_index).getID(), Button_List.get(Item_index).getProductName(), Integer.parseInt(Quantity.getText()), Button_List.get(Item_index).Price));
                    System.out.println(Button_List.get(Item_index).getItemTypeID());
                    tableData.clear();
                    for (int i = 0; i < Selected_Item.size(); i++) {
                        tableData.add(new Selected_Item(Selected_Item.get(i).getItemID(), Selected_Item.get(i).getProductId(), Selected_Item.get(i).getQuantity(), Selected_Item.get(i).getPrice(), Selected_Item.get(i).getSub_total()));
                        Total = Total + Selected_Item.get(i).GetSubtotal();
                        No_Items = No_Items + (int) (Selected_Item.get(i).getQuantity());
                        System.out.println(Selected_Item.get(i).getItemID());
                        System.out.println(No_Items + "Items ..no.");
                    }
                    Items_Label.setText("No.of items: " + No_Items);
                    TablePOS.setItems(tableData);

                    Grand_Total.setText(String.format("%,.2f", Total));
                    Main_Pane.requestFocus();
                    Mode_label.setText("Mode: Select Item");
                    Quantity.setText("");

                    @SuppressWarnings("unchecked")
                    TablePosition pos = new TablePosition(TablePOS, Selected_Item.size(), null);
                    TablePOS.getFocusModel().focus(pos);
                    TablePOS.requestFocus();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("The input is not numerical ");
                    alert.showAndWait();

                }
            }
            Manager_overide = false;

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Quantity value invalid ");
            alert.showAndWait();

        }
    }

    void Refresh_list() {
        Total = 0;
        No_Items = 0;

        System.out.println("trasaction Calculation");
        DecimalFormat d = new DecimalFormat(("#,###.00"));

        // Selected_Item.add(new Selected_Item(Button_List.get(Item_index).getID(), Button_List.get(Item_index).getProductName(), Integer.parseInt(Quantity.getText()), Button_List.get(Item_index).Price));
        // System.out.println(Button_List.get(Item_index).getItemTypeID());
        tableData.clear();
        for (int i = 0; i < Selected_Item.size(); i++) {
            tableData.add(new Selected_Item(Selected_Item.get(i).getItemID(), Selected_Item.get(i).getProductId(), Selected_Item.get(i).getQuantity(), Selected_Item.get(i).getPrice(), Selected_Item.get(i).getSub_total()));
            Total = Total + Selected_Item.get(i).GetSubtotal();
            No_Items = No_Items + (int) (Selected_Item.get(i).getQuantity());
            System.out.println(Selected_Item.get(i).getItemID());
            System.out.println(No_Items + "Items ..no.");
        }
        Items_Label.setText("No.of items: " + No_Items);
        TablePOS.setItems(tableData);

        Grand_Total.setText(String.format("%,.2f", Total));
        Main_Pane.requestFocus();
        Mode_label.setText("Mode: Select Item");
        Quantity.setText("");

        @SuppressWarnings("unchecked")
        TablePosition pos = new TablePosition(TablePOS, Selected_Item.size(), null);
        TablePOS.getFocusModel().focus(pos);
        TablePOS.requestFocus();

    }

    @FXML
    private void item_13(ActionEvent event) {

        Item_index = 12;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 13 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_14(ActionEvent event) {
        Item_index = 13;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 14 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_15(ActionEvent event) {
        Item_index = 14;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 15__");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_4(ActionEvent event) {
        Item_index = 3;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 4 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_5(ActionEvent event) {
        Item_index = 5;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 5 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_6(ActionEvent event) {
        Item_index = 5;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 6 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_7(ActionEvent event) {
        Item_index = 6;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 7 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_8(ActionEvent event) {
        Item_index = 8;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 8 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_9(ActionEvent event) {
        Item_index = 8;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 9 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_10(ActionEvent event) {
        Item_index = 9;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 10 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_11(ActionEvent event) {
        Item_index = 10;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 11 __");
    }

    @FXML
    private void item_12(ActionEvent event) {
        Item_index = 11;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 12 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    double Cash = 0.0;

    @FXML
    private void Payment(ActionEvent event) {
        Mode_label.setText("Mode: Payment mode Enter Cash..");
        CASH.requestFocus();
        CASH.setText("");
        CASH.setText("");
        CASH.requestFocus();

    }

    @FXML
    private void Cash_(ActionEvent event) {

        boolean StringChecker = CASH.getText().isEmpty();
        System.out.println(StringChecker + " hahahahh");
        System.out.println("Input cash mode ");

        if (StringChecker) {
            Main_Pane.requestFocus();
            System.out.println("Cash input Invalid");
            Mode_label.setText("Mode: Select Item..");

        }

        try {

            double cash = Double.parseDouble(CASH.getText());
            System.out.println("Current Credits:" + Current);
            System.out.println("Total:" + Total);
            // System.out.println("Cash Validity:" + CashValidity);
            if (cash >= Total) {
                double change = cash - Total;
                Compute_Invoice(Selected_Item);

                DecimalFormat d = new DecimalFormat(("#,###.00"));

                Change.setText("Change: " + d.format(change));
                OrNumber++;
                OR_Number.setText("Official Receipt No." + String.format("%010d", OrNumber));

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Transaction Status");
                alert.setHeaderText("Transaction Success!");
                alert.setContentText("Proceed to next Transaction");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {

                    tableData.clear();
                    TablePOS.setItems(tableData);
                    Grand_Total.setText(String.format(""));
                    CASH.setText("");
                    Change.setText("Change: ");
                    Quantity.setText("");
                    Main_Pane.requestFocus();
                    Mode_label.setText("Mode: Select Item to continue..");
                    Items_Label.setText("");
                    Student_Id.setText("");
                }

            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Transaction Status");
                alert.setHeaderText("Cash Insufficient");
                alert.setContentText("Please Input sufficient Cash");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    CASH.requestFocus();

                }

            }

        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cash Input Error ");
            alert.setHeaderText("Invalid Cash Input");
            alert.setContentText("");
        }

        System.out.println(CASH.getText());
    }

    void Clear_() {

    }

    void Compute_Invoice(ArrayList<Selected_Item> Items) {
        DBConnection.init();
        Connection c = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        for (int i = 0; i < Selected_Item.size(); i++) {
            try {
                ps = c.prepareStatement("Insert into dcaa_pos.invoice (Item_name, quantity, price, subTotal, OR_, user_id, date, StudentID, Items_idItems) values ('" + Selected_Item.get(i).getItemID() + "','" + Selected_Item.get(i).getQuantity() + "','" + Selected_Item.get(i).getPrice() + "','" + Selected_Item.get(i).getSub_Total() + "','" + String.format("%010d", OrNumber) + "','" + UserID + "','" + timeStamp + "','" + Student_Id.getText() + "','" + Selected_Item.get(i).getItemID() + "') ");
                if (!ps.execute()) {
                    System.out.println("Invoice Logged");
                }
            } catch (SQLException ex) {
                Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        Selected_Item.clear();

        if (!Student_Id.getText().equals("")) {
            try {

                ps = c.prepareStatement("SELECT OR_,sum(subTotal),date from invoice where StudentID='" + Student_Id.getText() + "' and OR_='" + String.format("%010d", OrNumber) + "'");
                rs = ps.executeQuery();

                if (rs.next()) {

                    ps = c.prepareStatement("Insert into credit_history (NFC_Card_No,StudentID, Credit, createtime, userID, Transaction_type, OR_)values ('" + NFC_no + "','" + StudentID + "','" + rs.getString(2) + "','" + rs.getString("date") + "','" + 1 + "','debit','" + rs.getString("OR_") + "') ");
                    if (!ps.execute()) {
                        System.out.println("debit transaction logged");
                    }

                }

                NFC_no = "";
                StudentID = "";

            } catch (SQLException ex) {
                Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        Compute_Collection(UserID);

    }
    private int OrNumber = 0;

    void LOAD_OR_Number() {
        try {

            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            ps = c.prepareStatement("SELECT OR_ from invoice order by idInvoice desc limit 1");
            rs = ps.executeQuery();
            while (rs.next()) {
                OrNumber = rs.getInt(1) + 1;
            }
            OR_Number.setText("Official Receipt No." + String.format("%010d", OrNumber));
            System.out.println("OR Number " + OrNumber);
            ps.close();
            c.close();

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Please load Manually the OR number");
        } catch (NumberFormatException ex) {
            // JOptionPane.showMessageDialog(this, "Please load Manually the OR number");
        }
    }

    @FXML
    private void Student_ID___(ActionEvent event) {

        Student_Id.requestFocus();;
        Mode_label.setText("Mode: Enter Student ID");

    }
    String StudentID;

    @FXML
    private void Student_ID_Action(ActionEvent event) throws SQLException {
        try {
            Main_Pane.requestFocus();
            System.out.println("Student ID ..");
            Load_image_data(Student_Id.getText());
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps, ps1;
            ResultSet rs, rs1;
            Double debit = 0.0, credit = 0.0;
            String ID = Student_Id.getText();
            ps = c.prepareStatement("SELECT sum(Credit),StudentID FROM dcaa_pos.credit_history where Transaction_type='credit' and  NFC_Card_No='" + ID + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString(1) == null) {
                    credit = 0.0;
                } else {
                    credit = Double.valueOf(rs.getString(1));
                    //LabelName.setText(rs.getString("F_name") + " " + rs.getString("M_name") + " " + rs.getString("L_name"));
                    System.out.println("debit:" + debit);
                }

                StudentID = rs.getString("StudentID");
            }
            ps1 = c.prepareStatement("SELECT sum(Credit),StudentID FROM dcaa_pos.credit_history where Transaction_type='debit' and  NFC_Card_No='" + ID + "'");
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                if (rs1.getString(1) == null) {
                    debit = 0.0;
                } else {
                    debit = Double.valueOf(rs1.getString(1));
                    System.out.println("debit:" + debit);
                }
                StudentID = rs1.getString("StudentID");
            }
            Current = credit - debit;
            StudentCredit.setText("Student Credit:" + Current);
            ps.close();
            ps1.close();
            rs.close();
            rs1.close();

            // inputBox();
        } catch (IOException ex) {
            Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Item_Search(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/POS_search.fxml"));
            Parent root1 = loader.load();
            POS_searchController = loader.getController();
            POS_searchController.controller = this;
            POS_searchController.set_Fucos();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Search Item");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Data_transfer(String a) {
        //Item_index = Integer.parseInt(a);

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = c.prepareStatement("SELECT * FROM dcaa_pos.items where idItems=" + a + " ");
            ResultSet rs = ps.executeQuery();

            double price = 0.0;
            if (rs.next()) {
                if (rs.getString("Price") != null) {
                    price = Double.parseDouble(rs.getString("Price"));
                } else {
                    price = 0.0;
                }
                Button_List.add(new Item_data_(rs.getString("idItems"), rs.getString("idItems"), rs.getString("Item_name"), rs.getString("Item_type_idItem_type"), "Searched Item", price));
                System.out.println(rs.getString("idItems"));

            }

            c.close();
            ps.close();

            Item_selected_label.setText(Button_List.get(Button_List.size() - 1).ProductName);
            Quantity.requestFocus();
            System.out.println("Test search:" + Button_List.get(Button_List.size() - 1).ProductName);
            Item_index = Button_List.size() - 1;
            System.out.println(Button_List.size() - 1);
            Mode_label.setText("Mode: Item Selection Enter Quantity");

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Barcode_Data_transfer(String a) {
        //Item_index = Integer.parseInt(a);

        DBConnection.init();

        Connection c = DBConnection.getConnection();

        PreparedStatement ps;

        try {
            ps = c.prepareStatement("SELECT * FROM dcaa_pos.items where barcode=" + a + " ");
            ResultSet rs = ps.executeQuery();

            double price = 0.0;
            if (rs.next()) {
                if (rs.getString("Price") != null) {
                    price = Double.parseDouble(rs.getString("Price"));
                } else {
                    price = 0.0;
                }
                Button_List.add(new Item_data_(rs.getString("idItems"), rs.getString("idItems"), rs.getString("Item_name"), rs.getString("Item_type_idItem_type"), "Searched Item", price));
                System.out.println(rs.getString("idItems"));

            }

            c.close();
            ps.close();

            Item_selected_label.setText(Button_List.get(Button_List.size() - 1).ProductName);
            Quantity.requestFocus();
            System.out.println("Test search:" + Button_List.get(Button_List.size() - 1).ProductName);
            Item_index = Button_List.size() - 1;
            System.out.println(Button_List.size() - 1);
            Mode_label.setText("Mode: Item Selection Enter Quantity");

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Update_Label() {

    }

    void Compute_Collection(String UserID_) {
        try {
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps;

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

            System.out.println(timeStamp + " date formated");
            System.out.println(UserID_ + " User id for collection");
            ps = c.prepareStatement("SELECT sum(subTotal) FROM dcaa_pos.invoice where user_id='" + UserID_ + "' AND date(date)='" + timeStamp + "' ");
            ResultSet rs = ps.executeQuery();

            DecimalFormat decimalFormatter = new DecimalFormat("#,##0.00");

            while (rs.next()) {
                if (rs.getString(1) != null) {
                    System.out.println(rs.getString(1) + " Collection for Today");
                    String formattedAmount = decimalFormatter.format(Double.parseDouble(rs.getString(1)));
                    System.out.println(formattedAmount);
                    label_sales.setText("Sales Invoice: " + formattedAmount);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void item_16(ActionEvent event) {
        Item_index = 16;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 16 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_38(ActionEvent event) {
        Item_index = 37;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_37(ActionEvent event) {
        Item_index = 36;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_33(ActionEvent event) {
        Item_index = 32;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_31(ActionEvent event) {
        Item_index = 30;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_32(ActionEvent event) {
        Item_index = 31;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_34(ActionEvent event) {
        Item_index = 33;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_35(ActionEvent event) {
        Item_index = 34;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_36(ActionEvent event) {
        Item_index = 35;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_39(ActionEvent event) {
        Item_index = 38;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_40(ActionEvent event) {
        Item_index = 39;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_41(ActionEvent event) {
        Item_index = 40;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_42(ActionEvent event) {
        Item_index = 41;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_44(ActionEvent event) {
        Item_index = 43;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_53(ActionEvent event) {
        Item_index = 52;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_52(ActionEvent event) {
        Item_index = 51;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_48(ActionEvent event) {
        Item_index = 47;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_46(ActionEvent event) {
        Item_index = 45;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_47(ActionEvent event) {
        Item_index = 46;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_51(ActionEvent event) {
        Item_index = 50;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_54(ActionEvent event) {
        Item_index = 53;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_55(ActionEvent event) {
        Item_index = 54;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_56(ActionEvent event) {
        Item_index = 55;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_57(ActionEvent event) {
        Item_index = 56;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_59(ActionEvent event) {
        Item_index = 58;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_24(ActionEvent event) {
        Item_index = 23;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_23(ActionEvent event) {
        Item_index = 22;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_19(ActionEvent event) {
        Item_index = 18;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_17(ActionEvent event) {
        Item_index = 16;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_18(ActionEvent event) {
        Item_index = 17;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_20(ActionEvent event) {
        Item_index = 19;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_21(ActionEvent event) {
        Item_index = 20;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_22(ActionEvent event) {
        Item_index = 21;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_25(ActionEvent event) {
        Item_index = 24;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_26(ActionEvent event) {
        Item_index = 25;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_27(ActionEvent event) {
        Item_index = 26;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_28(ActionEvent event) {
        Item_index = 27;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_29(ActionEvent event) {
        Item_index = 28;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_30(ActionEvent event) {
        Item_index = 29;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_43(ActionEvent event) {
        Item_index = 42;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_45(ActionEvent event) {
        Item_index = 44;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_49(ActionEvent event) {
        Item_index = 48;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_50(ActionEvent event) {
        Item_index = 49;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void item_58(ActionEvent event) {

        Item_index = 57;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Item_60(ActionEvent event) {

        Item_index = 59;
        Item_selected_label.setText(Button_List.get(Item_index).ProductName);
        Quantity.requestFocus();
        System.out.println("Test 60 __");
        Mode_label.setText("Mode: Item Selection Enter Quantity");
    }

    @FXML
    private void Touch_Press(TouchEvent event) {

        System.out.println("test test touch");
    }

    @FXML
    private void Scan_NFC(ActionEvent event) {

        try {
            Student_Id.clear();
            TerminalFactory terminalFactory = TerminalFactory.getDefault();
            CardTerminals terminals = terminalFactory.terminals();

            if (terminals.list().isEmpty()) {
                System.out.println("No card terminals found.");
                Student_Id.clear();
                return;
            }

            CardTerminal terminal = terminals.list().get(0);
            System.out.println("Waiting for card...");
            Current = 0;
            StudentCredit.setText("");

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
                // Student_Id.setText(uid);
                card.disconnect(true);
                System.out.println("Card disconnected. Waiting for new card...");
                Current = 0;
                StudentCredit.setText("");

                ViewCredit(uid);

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

    void ViewCredit(String cardString) {
        try {
            Main_Pane.requestFocus();
            System.out.println("Student ID ..");
            DBConnection.init();
            Connection c = DBConnection.getConnection();
            PreparedStatement ps, ps1;
            ResultSet rs, rs1;
            Double debit = 0.0, credit = 0.0;
            NFC_no = cardString;

            ps = c.prepareStatement("SELECT Student_ID,F_name, M_name, L_Name FROM dcaa_pos.student_info where NFC_Card_No='" + cardString + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                StudentID = rs.getString("Student_ID");
                LabelName.setText(rs.getString("F_name") + " " + rs.getString("M_name") + " " + rs.getString("L_name"));
            }
            rs.close();
            ps.close();

            Student_Id.setText(StudentID);

            ps = c.prepareStatement("SELECT sum(Credit) FROM dcaa_pos.credit_history where Transaction_type='credit' and  NFC_Card_No='" + cardString + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString(1) == null) {
                    credit = 0.0;
                } else {
                    credit = Double.valueOf(rs.getString(1));
                    System.out.println("debit:" + debit);
                }
            }
            ps1 = c.prepareStatement("SELECT sum(Credit) FROM dcaa_pos.credit_history where Transaction_type='debit' and  NFC_Card_No='" + cardString + "'");
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                if (rs1.getString(1) == null) {
                    debit = 0.0;
                } else {
                    debit = Double.valueOf(rs1.getString(1));
                    System.out.println("debit:" + debit);
                }
            }
            Current = credit - debit;

            //CashValidity = Current > 0;
            StudentCredit.setText("Student Credit:" + Current);

            ps.close();
            ps1.close();
            rs.close();
            rs1.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("NFC Card Status");
            alert.setHeaderText("NFC Card Scanned");
            alert.setContentText("Please Removed Card and return to the owner.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("Card Status");
            }

            try {
                Load_image_data(StudentID);
            } catch (IOException ex) {
                Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(POS_MainController.class.
                    getName()).log(Level.SEVERE, null, ex);
        }
    }

    void PayCard() {

        if (Current >= Total) {

            Compute_Invoice(Selected_Item);

            DecimalFormat d = new DecimalFormat(("#,###.00"));

            // Change.setText("Change: " + d.format(change));
            OrNumber++;
            OR_Number.setText("Official Receipt No." + String.format("%010d", OrNumber));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Transaction Status");
            alert.setHeaderText("Transaction Success!");
            alert.setContentText("Proceed to next Transaction");
            Current = 0.0;

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                tableData.clear();
                TablePOS.setItems(tableData);
                Grand_Total.setText(String.format(""));
                CASH.setText("");
                Change.setText("Change: ");
                Quantity.setText("");
                Main_Pane.requestFocus();
                Mode_label.setText("Mode: Select Item to continue..");
                Items_Label.setText("");
                Student_Id.setText("");
                Scan_NFC.setText("Scan Card");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Card Credit Insuffient");
            alert.setHeaderText("The Current card credit is insufficient to proceed on this transaction.\nYou may replenish your credits at the Business office");
            alert.setContentText("Proceed to next Transaction");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Current = 0.0;
                Student_Id.setText("");
                // CashValidity = false;
                CASH.clear();
                StudentCredit.setText("Credit Unavailable.");
            }

        }
    }

    @FXML
    private void PayCard_(ActionEvent event) {

        PayCard();
    }

    @FXML
    private void remove_Item_() {
        TablePosition pos = TablePOS.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        System.out.println(row + " row");
        Selected_Item.remove(row);

        Refresh_list();

        // seTablePOS.getItems().get(row);
        //TableColumn col = pos.getTableColumn();
        //String Data = (String) col.getCellObservableValue(item).getValue();
        // System.out.println(Data);
    }

    @FXML
    private void Credit_Replenish(ActionEvent event) {
        if (NFC_no != null) {

            try {
                JOptionPane.showMessageDialog(null, "Load Credit to Card:" + NFC_no);
                DBConnection.init();
                Connection c = DBConnection.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT count(OR_) FROM dcaa_pos.credit_history where OR_ like '%C%'");
                ResultSet rs = ps.executeQuery();
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

                if (rs.next()) {
                    String OR_Credit = "C" + String.format("%09d", Integer.parseInt(rs.getString(1)) + 1);
                    System.out.println(OR_Credit);
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Amount"));
                    ps = c.prepareStatement("Insert into credit_history (StudentID, Credit, createtime, userID, Transaction_type, OR_,NFC_Card_No)values ('" + StudentID + "','" + amount + "','" + timeStamp + "','" + UserID + "','credit','" + OR_Credit + "','" + NFC_no + "') ");
                    if (!ps.execute()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Student Credit ");
                        alert.setHeaderText(" Pesos has been Credited to Student ID:" + StudentID);
                        alert.setContentText("");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            System.out.println(amount + " credited to:" + NFC_no);
                            amount = 0;

                        }
                    }

                }
            } catch (SQLException ex) {
                System.out.println(ex);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Scan a Valid Card to proceed.");
        }

    }

    void Load_image_data(String id) throws IOException {

        try {
            DBConnection.ReadIPaddress();
            DBConnection.init();
            PreparedStatement ps = null;
            ResultSet rs;
            Connection c = DBConnection.getConnection();
            ps = c.prepareStatement("SELECT image_data, F_name, M_name, L_Name FROM dcaa_pos.student_info where Student_ID='" + id + "'");
            rs = ps.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("image_data");
                InputStream inputStream;
                Image image;
                if (blob != null) {
                    inputStream = blob.getBinaryStream();

                    image = new Image(inputStream);
                    LabelName.setText(rs.getString("F_name") + " " + rs.getString("M_name") + " " + rs.getString("L_name"));
                    Imageview.setImage(image);
                } else {
                    File file = new File(System.getProperty("user.dir") + "\\no-avatar.png");

                    inputStream = new FileInputStream(file);
                    image = new Image(inputStream);  // Replace with the actual path to your .png file

                    Imageview.setImage(image);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void inputBox() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("Enter a number:");

        // Show the dialog and get the user's input
        String input = dialog.showAndWait().orElse("");

        // Check if the input is numerical
        if (input.matches("\\d+(\\.\\d+)?")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("The input is numerical.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("The input is not numerical.");
            alert.showAndWait();
        }
    }
    Overide_LoginController manager_overide;

    @FXML
    private void Overide_(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/Overide_Login.fxml"));
            Parent root1 = loader.load();
            manager_overide = loader.getController();
            manager_overide.main = this;
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Manager Overide");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Read_Barcode(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dcaa_pos_/POS_ReadBarcode.fxml"));
            Parent root1 = loader.load();
            POS_Barcode = loader.getController();
            POS_Barcode.controller = this;
            POS_Barcode.set_Fucos();

            Stage stage = new Stage();
            stage.setX(380);
            stage.setY(320);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Barcode Read");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
