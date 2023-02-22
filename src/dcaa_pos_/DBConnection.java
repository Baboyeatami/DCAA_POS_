package dcaa_pos_;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.AppUtil;

/**
 *
 * @author JAMIEXXX3
 */
public class DBConnection {

    private static Connection Myconnection;
    private static String IpAddress;

    public static void init() {
        try {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Class not found");
            }
            Myconnection = DriverManager.getConnection("jdbc:mysql://" + IpAddress + ":3306/dcaa_pos?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            //System.out.println(IpAddress + " ip read on init");
            // System.out.println("\"jdbc:mysql://" + IpAddress + ":3306/dcaa_databse?allowPublicKeyRetrieval=true&useSSL=false\", \"root\", \"root\"");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static Connection getConnection() {
        if (Myconnection != null) {
            return Myconnection;
        } else {
            try {
                JOptionPane.showMessageDialog(null, "Cannot Connect to " + IpAddress);

                String IPadd = JOptionPane.showInputDialog("Enter Database Server IP address");
                FileWriter fw = new FileWriter(AppUtil.CONFIG_PATH + File.separator + "IPAddress.ini");

                fw.write(IPadd);
                System.out.println("Writed successfulty");

                fw.close();

                FileReader fr = new FileReader(AppUtil.CONFIG_PATH + File.separator + "IPAddress.ini");
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();
                System.out.println("");

                while ((IPadd = br.readLine()) != null) {

                    sb.append(IPadd);
                }

                IPadd = sb.toString();
                System.out.println(IPadd);
                IpAddress = IPadd;

                fr.close();
                br.close();

                Myconnection = DriverManager.getConnection("jdbc:mysql://" + IpAddress + ":3306/dcaa_pos?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "root");

            } catch (Exception ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Myconnection;

    }

    public static void close(ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public void Destroy() {
        if (Myconnection != null) {

            try {
                Myconnection.close();
            } catch (Exception e) {
            }

        }

    }

    public static void ReadIPaddress() throws IOException {

        try {

            String line;
            FileReader fr = new FileReader(AppUtil.CONFIG_PATH + File.separator + "IPAddress.ini");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                //  System.out.println(line);
                sb.append(line);
            }

            line = sb.toString();
            System.out.println(line + " ip add reading");
            IpAddress = line;
            fr.close();
            br.close();

        } catch (IOException e) {
            File f = new File(AppUtil.CONFIG_PATH);
            if (!f.exists()) {
                f.mkdir();
                System.out.println("mkdir");
            }
            f = new File(AppUtil.CONFIG_PATH + File.separator + "IPAddress.ini");
            if (!f.exists()) {
                f.createNewFile();
                System.out.println("create file");
            }

            String IPadd = JOptionPane.showInputDialog("Enter Database Server IP address");
            FileWriter fw = new FileWriter(AppUtil.CONFIG_PATH + File.separator + "IPAddress.ini");

            fw.write(IPadd);
            System.out.println("Writed successfulty");

            fw.close();

            FileReader fr = new FileReader(AppUtil.CONFIG_PATH + File.separator + "IPAddress.ini");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            while ((IPadd = br.readLine()) != null) {

                sb.append(IPadd);
            }

            IPadd = sb.toString();
            System.out.println(IPadd);
            IpAddress = IPadd;

            fr.close();
            br.close();

        }

    }

    static void ChangeIP() {
        FileWriter fw = null;
        try {
            String IPadd = JOptionPane.showInputDialog("Enter Database Server IP address");
            fw = new FileWriter(AppUtil.CONFIG_PATH + File.separator + "IPAddress.ini");
            fw.write(IPadd);
            System.out.println("Writed successfulty");
            fw.close();
            FileReader fr = new FileReader(AppUtil.CONFIG_PATH + File.separator + "IPAddress.ini");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            while ((IPadd = br.readLine()) != null) {

                sb.append(IPadd);
            }
            IPadd = sb.toString();
            System.out.println(IPadd);
            IpAddress = IPadd;
            fr.close();
            br.close();

            JOptionPane.showMessageDialog(null, "Database IP Address updated to " + IpAddress);

        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    DatabaseMetaData getMetaData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
