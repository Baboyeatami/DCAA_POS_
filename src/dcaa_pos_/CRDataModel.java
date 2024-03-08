/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcaa_pos_;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class CRDataModel {

    String ORString, S_IDString, StudentName, Amount, Type, UserId;

    public CRDataModel() {
    }

    public CRDataModel(String ORString, String S_IDString, String StudentName, String Amount, String Type, String UserId) {
        this.ORString = ORString;
        this.S_IDString = S_IDString;
        this.StudentName = StudentName;
        this.Amount = Amount;
        this.Type = Type;
        this.UserId = UserId;
    }

    public String getORString() {
        return ORString;
    }

    public void setORString(String ORString) {
        this.ORString = ORString;
    }

    public String getS_IDString() {
        return S_IDString;
    }

    public void setS_IDString(String S_IDString) {
        this.S_IDString = S_IDString;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

}
