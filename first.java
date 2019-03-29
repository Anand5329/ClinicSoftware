package ClinicSoftware;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.awt.*;


public class first extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btn1=new Button("Hello World");
        btn1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg)
            {
                System.out.println("Hello World");
            }
        });

        StackPane root=new StackPane();
        root.getChildren().add(btn1);
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Button");
        primaryStage.show();
    }

}
