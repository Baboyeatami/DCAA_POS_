/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcaa_pos_;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Student_Credit_history_data_Model {

    private String TransactionID;
    private String OR;
    private String Amount;

    private String TransactionType;
    private String Date;
    private String StudentID;
    private String Name;

    public Student_Credit_history_data_Model(String StudentID, String Name) {

        this.StudentID = StudentID;
        this.Name = Name;
    }

    public Student_Credit_history_data_Model(String TransactionID, String OR, String Amount, String TransactionType, String Date) {
        this.TransactionID = TransactionID;
        this.OR = OR;
        this.Amount = Amount;
        this.TransactionType = TransactionType;
        this.Date = Date;

    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String TransactionID) {
        this.TransactionID = TransactionID;
    }

    public String getOR() {
        return OR;
    }

    public void setOR(String OR) {
        this.OR = OR;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String TransactionType) {
        this.TransactionType = TransactionType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getStudent() {
        return getStudentID();
    }

    public void setStudent(String Student) {
        this.setStudentID(Student);
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the StudentID
     */
    public String getStudentID() {
        return StudentID;
    }

    /**
     * @param StudentID the StudentID to set
     */
    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

}
