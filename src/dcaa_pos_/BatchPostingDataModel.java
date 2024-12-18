/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcaa_pos_;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class BatchPostingDataModel {

    String StudentName;
    String StudentID;
    private String NFC_Card_No;
    double Amount;

    public BatchPostingDataModel(String StudentName, String StudentID, double Amount, String NFC_Card_No) {
        this.StudentName = StudentName;
        this.StudentID = StudentID;
        this.Amount = Amount;
        this.NFC_Card_No = NFC_Card_No;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    public BatchPostingDataModel() {
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getNFC_Card_No() {
        return NFC_Card_No;
    }

    public void setNFC_Card_No(String NFC_Card_No) {
        this.NFC_Card_No = NFC_Card_No;
    }

}
