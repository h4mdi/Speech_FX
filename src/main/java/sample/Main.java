package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent anchorPane  = FXMLLoader.load(getClass().getResource("/sample.fxml"));

        primaryStage.setTitle("SESAME Recognition");
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
                System.exit(0);
            }
        });


    }

    public static void main(String[] args) {
        launch(args);

    }
}
