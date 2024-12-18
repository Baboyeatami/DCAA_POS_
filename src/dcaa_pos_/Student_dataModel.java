/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcaa_pos_;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Student_dataModel {

    private String StudentName;
    private String StudentID;
    private String NFC_Card_No;

    public Student_dataModel() {
    }

    public Student_dataModel(String StudentName, String StudentID, String NFC_Card_No) {
        this.StudentName = StudentName;
        this.StudentID = StudentID;
        this.NFC_Card_No = NFC_Card_No;
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
