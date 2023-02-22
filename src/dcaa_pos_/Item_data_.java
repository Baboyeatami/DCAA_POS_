/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcaa_pos_;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Item_data_ {

    String Button_ID;
    String ID;
    String ProductName;

    String ItemTypeID;
    String Button_name;
    double Price;

    public double getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public Item_data_() {
    }

    public Item_data_(String Button_ID, String ID, String ProductName, String ItemTypeID, String Button_name) {
        this.Button_ID = Button_ID;
        this.ID = ID;
        this.ProductName = ProductName;
        this.ItemTypeID = ItemTypeID;
        this.Button_name = Button_name;
    }

    public Item_data_(String Button_ID, String ID, String ProductName, String ItemTypeID, String Button_name, double Price) {
        this.Button_ID = Button_ID;
        this.ID = ID;
        this.ProductName = ProductName;
        this.Price = Price;
        this.ItemTypeID = ItemTypeID;
        this.Button_name = Button_name;
    }

    public String getButton_ID() {
        return Button_ID;
    }

    public void setButton_ID(String Button_ID) {
        this.Button_ID = Button_ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getItemTypeID() {
        return ItemTypeID;
    }

    public void setItemTypeID(String ItemTypeID) {
        this.ItemTypeID = ItemTypeID;
    }

    public String getButton_name() {
        return Button_name;
    }

    public void setButton_name(String Button_name) {
        this.Button_name = Button_name;
    }

}
