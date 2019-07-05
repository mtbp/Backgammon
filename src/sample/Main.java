package  sample;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class Main extends Application {

    private static final float W = 680;
    private static final float H = 520;
    private static final float B = 20;
    private static Pane root = new Pane();
    private static Group homeGroup = new Group();

    private void setRect(Rectangle rect, float X, float Y, float Width, float Height, String colorCode){
        rect.setX(X);
        rect.setY(Y);
        rect.setWidth(Width);
        rect.setHeight(Height);
        rect.setFill(Color.web(colorCode));
    }
    private void setSVG(SVGPath svg, String path, String colorCode){
        svg.setContent(path);
        svg.setFill(Color.web(colorCode));
    }
    private Parent createContent(){
        SVGPath border = new SVGPath();
        String path = " " + (W - (4 * B))/2 + " ,0 0,"
                + (H - 2 * B) +" " + (-(W - (4 * B))/2) + ",0 0," + (-(H - 2 * B)) +" z";
        setSVG(border, "M 0,0 0," + H + " " + W + "," + H + " " + W + ",0 0,0 z" +
                "m " + B + "," + B + path + "m " + W/2 + ",0" + path, "#552500");
        Rectangle half1 = new Rectangle();
        setRect(half1, B, B, (W - (4 * B))/2, (H - 2 * B), "#164623");
        Rectangle half2 = new Rectangle();
        setRect(half2, W/2 + B, B, (W - (4 * B))/2, (H - 2 * B), "#164623");
        SVGPath home = new SVGPath();
        float hW = (W - 4 * B) / 12;
        float hH = (H - 2 * B - H / 5) / 2;
        setSVG(home, "M " + B  + "," + B + " m " + hW + ",0 " + (-hW / 2) + "," + hH + " " + (-hW / 2) + "," + (-hH) + " z", "#da251d") ;
        root.setPrefSize(W ,H );
        Home home1 = new Home(1, B, W, H);
        homeGroup.getChildren().addAll(home1);
        root.getChildren().addAll(border, half1, half2, homeGroup);
        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class Home extends SVGPath{

    private int id;

     private void setSVG(SVGPath svg, String path, String colorCode){
        svg.setContent(path);
        svg.setFill(Color.web(colorCode));
    }

    Home(int id, float B, float W, float H){
        this.id = id;
        int position = this.id / 12; // 0 for up 1 for down
        int color = this.id % 2; // 0 for red 1 for yellow

        float hW = (W - 4 * B) / 12;
        float hH = (H - 2 * B - H / 5) / 2;
        setSVG(this, "M " + B  + "," + B + " m " + hW + ",0 " + (-hW / 2) +
                "," + hH + " " + (-hW / 2) + "," + (-hH) + " z", "#da251d") ;

    }


}
