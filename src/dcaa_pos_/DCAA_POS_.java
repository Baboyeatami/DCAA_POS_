/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcaa_pos_;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class DCAA_POS_ extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.isAlwaysOnTop();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DBConnection.ReadIPaddress();
        launch(args);
    }

}
