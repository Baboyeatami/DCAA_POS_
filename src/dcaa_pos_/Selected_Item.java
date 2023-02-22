package dcaa_pos_;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Selected_Item {

    String ItemID;
    String ItemTypeID;
    String ProductId;
    double Quantity;
    double Price;
    double Sub_Total;
    String Sub_total;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public void setItemTypeID(String ItemTypeID) {
        this.ItemTypeID = ItemTypeID;
    }

    public String getItemTypeID() {
        return ItemTypeID;
    }

    public Selected_Item() {
    }

    public Selected_Item(String ItemID, String ProductName, double Quantity, double Price) {
        this.ItemID = ItemID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.ProductId = ProductName;

    }

    public Selected_Item(String ItemID, String ItemTypeID, double Quantity, double Price, double Sub_Total) {
        this.ItemID = ItemID;
        this.ItemTypeID = ItemTypeID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Sub_Total = Sub_Total;
    }

    public Selected_Item(String ItemID, String ItemTypeID, double Quantity, double Price, String Sub_Total) {
        this.ItemID = ItemID;
        this.ItemTypeID = ItemTypeID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Sub_total = Sub_Total;
    }

    double GetSubtotal() {
        return Quantity * Price;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getSub_Total() {
        return Sub_Total;
    }

    public void setSub_Total(double Sub_Total) {
        this.Sub_Total = Sub_Total;
    }

    public String getSub_total() {
        Sub_Total = Quantity * Price;
        return String.format("%,.2f", Sub_Total);

    }

}
