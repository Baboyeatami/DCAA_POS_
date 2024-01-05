package dcaa_pos_;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;

public class TimeUpdateTask extends Task<Void> {

    MainController main;

    public void setMain(MainController main) {
        this.main = main;
    }

    @Override
    protected Void call() throws Exception {

        while (true) {
            // Update the time and date here

            String currentDate = getCurrentDate();

            // Update the UI on the JavaFX Application Thread
            Platform.runLater(() -> {
                try {

                    System.out.println(getCurrentDate());
                    main.DateandTime(getCurrentDate());

                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TimeUpdateTask.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        }

    }

    private String getCurrentDate() {

        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat date = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");

        return date.format(cal.getTime()) + " " + time.format(cal.getTime());

    }

}
