package dcaa_pos_;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Inventory_ extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Inventory_.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.isAlwaysOnTop();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
