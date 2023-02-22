/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcaa_pos_;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Button_Settings_DataModel {

    String ButtonNumber, Product;
    String item_names, descriptions, prices, itm_id;

    public String getButtonNumber() {
        return ButtonNumber;
    }

    public void setButtonNumber(String ButtonNumber) {
        this.ButtonNumber = ButtonNumber;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }

    public String getItem_names() {
        return item_names;
    }

    public void setItem_names(String item_names) {
        this.item_names = item_names;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getItm_id() {
        return itm_id;
    }

    public void setItm_id(String itm_id) {
        this.itm_id = itm_id;
    }

    public Button_Settings_DataModel() {
    }

    public Button_Settings_DataModel(String ButtonNumber, String Product) {
        this.ButtonNumber = ButtonNumber;
        this.Product = Product;
    }

    public Button_Settings_DataModel(String ButtonNumber, String Product, String item_name, String description, String price, String item_id) {
        this.ButtonNumber = ButtonNumber;

        this.Product = Product;
        this.item_names = item_name;
        this.descriptions = description;
        this.prices = price;
        this.itm_id = item_id;
    }

    public Button_Settings_DataModel(String item_name, String description, String price, String item_id) {

        this.item_names = item_name;
        this.descriptions = description;
        this.prices = price;
        this.itm_id = item_id;
    }

    public Button_Settings_DataModel(String ButtonNumber, String Product, String itm_id) {
        this.ButtonNumber = ButtonNumber;
        this.Product = Product;
        this.itm_id = itm_id;
    }

}
