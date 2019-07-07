package sample;

import javafx.scene.layout.Pane;
import sample.*;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{


    View view = new View();
    Model model = new Model();
    Controller controller = new Controller(view, model);


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Backgammon");
        primaryStage.getIcons().add(new Image(("file:icon.png")));
        // Parent root = controller.rootinit();
        // Scene scene = new Scene(controller.viewroot);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        //controller.rootinit();
        primaryStage.setScene(new Scene(view.createStart()));
        primaryStage.show();
//        controller.event();
        controller.startevent();
    }




    public static void main(String[] args) {
        launch(args);
    }


}