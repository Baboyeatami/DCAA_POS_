package dcaa_pos_;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class POS_Main_DataModel {

    String Item_ID;
    String Item_name;
    String Price;
    String Quantity;
    String SubTotal;

    public POS_Main_DataModel() {
    }

    public POS_Main_DataModel(String Item_ID, String Item_name, String Price, String Quantity, String SubTotal) {
        this.Item_ID = Item_ID;
        this.Item_name = Item_name;
        this.Price = Price;
        this.Quantity = Quantity;
        this.SubTotal = SubTotal;
    }

    public String getItem_ID() {
        return Item_ID;
    }

    public void setItem_ID(String Item_ID) {
        this.Item_ID = Item_ID;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String Item_name) {
        this.Item_name = Item_name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(String SubTotal) {
        this.SubTotal = SubTotal;
    }

}
