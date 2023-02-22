/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class ItemTypeModel {

    String ID, ItemName, Description, Price;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public ItemTypeModel() {
    }

    public ItemTypeModel(String ID, String ItemName, String Description) {
        this.ID = ID;
        this.ItemName = ItemName;
        this.Description = Description;
    }

    public ItemTypeModel(String ID, String ItemName, String Description, String Price) {
        this.ID = ID;
        this.ItemName = ItemName;
        this.Description = Description;
        this.Price = Price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

}
