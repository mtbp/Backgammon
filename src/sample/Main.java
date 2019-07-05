package  sample;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class Main extends Application {

    private static final float W = 680;
    private static final float H = 520;
    private static final float B = 20;
    private static Pane root = new Pane();
    private static Group homeGroup = new Group();
    private static Group pieceGroup = new Group();

    private void setRect(Rectangle rect, float X){
        rect.setX(X);
        rect.setY(B);
        rect.setWidth((W - (4 * B))/2);
        rect.setHeight((H - 2 * B));
        rect.setFill(Color.web("#164623"));
    }
    private void setSVG(SVGPath svg, String path, String colorCode){
        svg.setContent(path);
        svg.setFill(Color.web(colorCode));
    }
    private Parent createContent() {
        int[] place = new int[24];
        for(int i = 0; i < 24; i++)
            place[i] = 0;
        place[0] = 1;
        SVGPath border = new SVGPath();
        String path = " " + (W - (4 * B))/2 + " ,0 0,"
                + (H - 2 * B) +" " + (-(W - (4 * B))/2) + ",0 0," + (-(H - 2 * B)) +" z";
        setSVG(border, "M 0,0 0," + H + " " + W + "," + H + " " + W + ",0 0,0 z" +
                "m " + B + "," + B + path + "m " + W/2 + ",0" + path, "#552500");
        Rectangle half1 = new Rectangle();
        setRect(half1, B);
        Rectangle half2 = new Rectangle();
        setRect(half2, W/2 + B);
        SVGPath home = new SVGPath();
        float hW = (W - 4 * B) / 12;
        float hH = (H - 2 * B - H / 5) / 2;
        setSVG(home, "M " + B  + "," + B + " m " + hW + ",0 " + (-hW / 2) +
                "," + hH + " " + (-hW / 2) + "," + (-hH) + " z", "#da251d") ;
        root.setPrefSize(W ,H );
        Home[] homes = new Home[24];
        for(int id = 1; id <= 24; id++) {
            homes[(id - 1)] = new Home(id, B, W, H);
            homeGroup.getChildren().addAll(homes[id - 1]);
        }

        Piece[] pieces = new Piece[15];
        int k = 0;
        for(int homeId = 0; homeId <24; homeId++) {
            if(place[homeId] != 0){
                for(int pnum = 1; pnum <= place[homeId]; pnum++) {
                    pieces[k] = new Piece(homeId + 1, pnum, B, W, H);
                    k++;
                    pieceGroup.getChildren().addAll(pieces[k -1]);
                }
            }
        }
        root.getChildren().addAll(border, half1, half2, homeGroup, pieceGroup);
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
        int position = (id - 1) / 6; // 0 for up 1st half , 1 : up 2nd half, 2 : down 1st half, 3 : down 2nd half
        int color = id % 2; // 0 for red 1 for yellow
        float start = 0;
        float hW = (W - 4 * B) / 12;
        float hH = (H - 2 * B - H / 5) / 2;
        switch (position){
            case 0 : start = (id - 1) * hW + B;
                break;
            case 1 : start = (id - 1) * hW + 3 * B;
                break;
            case 2 : start = (id - 13) * hW + B;
                break;
            case 3 : start = (id - 13) * hW + 3 * B;
                break;
        }

        if(position < 2)
            setSVG(this, "M " + start  + "," + B + " m " + hW + ",0 " + (-hW / 2) +
                     "," + hH + " " + (-hW / 2) + "," + (-hH) + " z", color == 0 ? "#da251d" : "#dedcae") ;
        else
            setSVG(this, "M " + start  + "," + (H - B) + " m " + hW + ",0 " + (-hW / 2) +
                    "," + (-hH) + " " + (-hW / 2) + "," + (hH) + " z", color == 1 ? "#da251d" : "#dedcae") ;

    }


}
class Piece extends Pane {


    Piece(int homeID, int PieceID, float B, float W, float H){

        float hW = (W - 4 * B) / 12;
        float hH = (H - 2 * B - H / 5) / 2;

        float r1 = 22;
        float r2 = 20;
        float r3 = 15;
        float r4 = 10;
        float shift = hW / 2;
        Circle backCircle = new Circle(B + shift, B + r1, r1, Color.WHEAT);
        backCircle.setStrokeWidth(0.5);
        backCircle.setStroke(Color.BLACK);
        Circle frontCircle1 = new Circle(B + shift, B + r1, r2, Color.BEIGE);
        frontCircle1.setStrokeWidth(0.5);
        frontCircle1.setStroke(Color.BLACK);
        Circle frontCircle2 = new Circle(B + shift, B + r1, r3, Color.WHEAT);
        frontCircle2.setStrokeWidth(0.5);
        frontCircle2.setStroke(Color.BLACK);
        Circle frontCircle3 = new Circle(B + shift, B + r1, r4, Color.BEIGE);
        frontCircle3.setStrokeWidth(0.5);
        frontCircle3.setStroke(Color.BLACK);

        getChildren().addAll(backCircle, frontCircle1, frontCircle2, frontCircle3);
    }


}
