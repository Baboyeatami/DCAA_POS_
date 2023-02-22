/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcaa_pos_;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Sales_Invoice_data_model {

    String Item_name, Quantity, Price, SubTotal, ORnumber, StudentID, date;

    public Sales_Invoice_data_model() {
    }

    public Sales_Invoice_data_model(String Item_name, String Quantity, String Price, String SubTotal, String ORnumber, String StudentID, String date) {
        this.Item_name = Item_name;
        this.Quantity = Quantity;
        this.Price = Price;
        this.SubTotal = SubTotal;
        this.ORnumber = ORnumber;
        this.StudentID = StudentID;
        this.date = date;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String Item_name) {
        this.Item_name = Item_name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getORnumber() {
        return ORnumber;
    }

    public void setORnumber(String ORnumber) {
        this.ORnumber = ORnumber;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(String SubTotal) {
        this.SubTotal = SubTotal;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

}
