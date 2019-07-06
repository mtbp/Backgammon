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
import javafx.scene.text.Text;
import java.util.ArrayList;

public class Main extends Application {

    private static final float W = 680;
    private static final float H = 560;
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
        place[0] = 5;
        place[4] = 103;
        place[6] = 105;
        place[11] = 2;
        place[12] = 105;
        place[16] = 3;
        place[18] = 5;
        place[23] = 102;
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

        for(int homeId = 1; homeId <= 24; homeId++) {
            //final int temp1 = pnum;
            final int temp2 = homeId;
            homes[(homeId - 1)] = new Home(homeId, B, W, H);
            homes[(homeId - 1)].setPNum(place[homeId -1]);
            homeGroup.getChildren().addAll(homes[homeId - 1]);
            for(int pnum = 1; pnum <= (place[homeId - 1] > 100 ? place[homeId - 1] - 100 : place[homeId - 1]); pnum++) {
                homes[homeId - 1].addPiece(B, W, H, (pnum > 8)? 1 : 0, place[homeId - 1] < 100, homeId > 12);

                pieceGroup.getChildren().addAll(homes[homeId - 1].pieces.get(pnum - 1));
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
    public static void main(String[] args) {
        launch(args);
    }
}

class Home extends SVGPath{

    ArrayList<Piece> pieces =new ArrayList<>();
    private int pID = 0;
    private int pNum = 0;
    private float start = 0;
    private float hW;
    private int hID;

    private void setSVG(SVGPath svg, String path, String colorCode){
        svg.setContent(path);
        svg.setFill(Color.web(colorCode));
    }

    Home (int id, float B, float W, float H) {

        this.hID = id;
        int position = (id - 1) / 6; // 0 for up 1st half , 1 : up 2nd half, 2 : down 1st half, 3 : down 2nd half
        int color = id % 2; // 0 for red 1 for yellow

        this.hW = (W - 4 * B) / 12;
        float hH = (H - 2 * B - H / 5) / 2;

        switch (position){
            case 0 : this.start = (id - 1) * this.hW + B;
                break;
            case 1 : this.start = (id - 1) * this.hW + 3 * B;
                break;
            case 2 : this.start = (id - 13) * this.hW + B;
                break;
            case 3 : this.start = (id - 13) * this.hW + 3 * B;
                break;
        }

        if(position < 2)
            setSVG(this, "M " + this.start  + "," + B + " m " + this.hW + ",0 " + (-this.hW / 2) +
                     "," + hH + " " + (-this.hW / 2) + "," + (-hH) + " z", color == 0 ? "#da251d" : "#dedcae") ;
        else
            setSVG(this, "M " + this.start  + "," + (H - B) + " m " + this.hW + ",0 " + (-this.hW / 2) +
                    "," + (-hH) + " " + (-this.hW / 2) + "," + (hH) + " z", color == 1 ? "#da251d" : "#dedcae") ;
        setOnMouseClicked(event -> System.out.println("F"));

    }

    void addPiece (float B, float W, float H, int x2, boolean color, boolean down){
        Piece piece = new Piece(this.hID, this.pID, B, W, H, this.start, x2, color, this.hW,this.pNum, down);
        this.pieces.add(piece);
        this.pID++;
    }

    int getPnum(){
        return this.pNum;
    }

    void setPNum(int pNum){
        this.pNum = pNum;
    }
}

class Piece extends Group{

    private int pID;
    private int hID;

    private double mouseX, mouseY, oldMouseX, oldMouseY;;

    Piece(int homeID, int pieceID, float B, float W ,float H, float start, int x2, boolean color, float hW, int nTop
            ,boolean down) {


        float r1 = 22;
        float r2 = 19;
        float r3 = 16;
        float r4 = 13;
        float r5 = 8;
        double r6 = 22.5;
        this.pID = pieceID;
        this.hID = homeID;

        float xShift = start + hW / 2 ;
        double yShift = (x2 == 0) ?  r1 * (1 + 1.25 * (pieceID)) :  r1 * (1.5 + 1.25 * (pieceID - 8));
        yShift = down ? H - B - yShift: B + yShift;
        final double yS = yShift;
        Circle backCircle = new Circle(xShift, yShift, r1, color ? Color.WHEAT : Color.GRAY);
        backCircle.setStrokeWidth(0.5);
        backCircle.setStroke(Color.BLACK);
            Circle frontCircle1 = new Circle(xShift, yShift, r2, color ? Color.BEIGE : Color.BLACK);
        frontCircle1.setStrokeWidth(0.5);
        frontCircle1.setStroke(color ? Color.BLACK : Color.WHITE);
        Circle frontCircle2 = new Circle(xShift, yShift, r3, color ? Color.WHEAT : Color.GRAY);
        frontCircle2.setStrokeWidth(0.5);
        frontCircle2.setStroke(color ? Color.BLACK : Color.WHITE);
        Circle frontCircle3 = new Circle(xShift, yShift, r4, color ? Color.BEIGE : Color.BLACK);
        frontCircle3.setStrokeWidth(0.5);
        frontCircle3.setStroke(color ? Color.BLACK : Color.WHITE);
        Circle frontCircle4 = new Circle(xShift, yShift, r5, color ? Color.WHEAT : Color.GRAY);
        frontCircle4.setStrokeWidth(0.5);
        frontCircle4.setStroke(color ? Color.BLACK : Color.WHITE);
        getChildren().addAll(backCircle, frontCircle1, frontCircle2, frontCircle3, frontCircle4);
        Circle selected = new Circle(xShift, yShift, r6, color ? Color.CYAN : Color.YELLOWGREEN);
        selected.setStrokeWidth(0.5);
        selected.setStroke(color ? Color.BLACK : Color.WHITE);

        final int nMax = color ? nTop : nTop - 100;
        if(nMax == this.getID() + 1) {
            setOnMousePressed(event -> {
                oldMouseX = event.getSceneX();
                oldMouseY = event.getSceneY();
                getChildren().clear();
                getChildren().addAll(selected, frontCircle1, frontCircle2, frontCircle3, frontCircle4);
               // System.out.println(this.hID);
            });
            setOnMouseDragged(event -> relocate(event.getSceneX() - oldMouseX + xShift - r1,
                    event.getSceneY() - oldMouseY + yS - r1));
            setOnMouseReleased(event -> {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                getChildren().clear();
                getChildren().addAll(frontCircle1, frontCircle2, frontCircle3, frontCircle4);
                int x = 0;
                if(mouseX < 320)
                    x = (int) Math.floor(mouseX / 50 + 12);

                if((mouseX > 360) & (mouseX < 660))
                    x = (int) Math.floor((mouseX - 360)/ 50 + 18);

                System.out.println(x + 1);
            });
        }
    }
     int getID(){
        return this.pID;
     }

}
