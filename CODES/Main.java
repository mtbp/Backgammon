import sample.*;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{


        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);


        @Override
        public void start(Stage primaryStage) throws Exception{
            //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            //primaryStage.setTitle("Hello World");
            Scene scene = new Scene(view.createContent());
            primaryStage.setScene(scene);
            primaryStage.show();
            controller.event();
        }




        public static void main(String[] args) {
            launch(args);
        }


}
