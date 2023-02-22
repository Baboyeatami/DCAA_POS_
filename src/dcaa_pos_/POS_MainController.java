/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

import com.sun.applet2.preloader.event.UserDeclinedEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    int UserID;

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

        Total = 0;
        No_Items = 0;

        if (Quantity.getText().equals("0") || Quantity.getText().equals("")) {
            Main_Pane.requestFocus();
            Mode_label.setText("Mode: Select Item to continue..");

        } else {

            System.out.println("trasaction Calculation");
            DecimalFormat d = new DecimalFormat(("#,###.00"));

            Selected_Item.add(new Selected_Item(Button_List.get(Item_index).getID(), Button_List.get(Item_index).getProductName(), Integer.parseInt(Quantity.getText()), Button_List.get(Item_index).Price));
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

            TablePosition pos = new TablePosition(TablePOS, Selected_Item.size(), null);
            TablePOS.getFocusModel().focus(pos);
            TablePOS.requestFocus();

        }
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

                    ps = c.prepareStatement("Insert into credit_history (StudentID, Credit, createtime, userID, Transaction_type, OR_)values ('" + Student_Id.getText() + "','" + rs.getString(2) + "','" + rs.getString("date") + "','" + 1 + "','debit','" + rs.getString("OR_") + "') ");
                    if (!ps.execute()) {
                        System.out.println("debit transaction logged");
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(POS_MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    int OrNumber = 0;

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

    @FXML
    private void Student_ID_Action(ActionEvent event) {
        Main_Pane.requestFocus();
        System.out.println("Student ID ..");
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

    void Update_Label() {

    }

    @FXML
    private void item_0(ActionEvent event) {
    }

    @FXML
    private void item_14(ActionEvent event) {
    }

}
