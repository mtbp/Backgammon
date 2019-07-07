package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.ArrayList;

public class View  {

    Button button = new Button();
    static final float W = 680;
    static final float H = 560;
    static final float B = 25;
    private static Pane root = new Pane();
    private static Pane rootStart = new Pane();
    private static Group homeGroup = new Group();
    public static Group pieceGroup = new Group();
    Home[] homes = new Home[24];
    public Dice dice, dice2;
    TextField player2name = new TextField();
    TextField player1name = new TextField();
    Dice p1dice = new Dice((W + 27 * B) / 4, 97);
    Dice p2dice = new Dice((W + 27 * B) / 4, 147);
    Button rollBtn = new Button();
    CheckBox white = new CheckBox();
    CheckBox black = new CheckBox();
    Button startBtn = new Button();
    Button startrollBtn = new Button();

    public void getInit(){
        white.relocate(W - 8 * B, 100);
        white.setText("White");
        white.setTextFill(Color.WHITE);
        black.relocate(W - 8 * B, 130);
        black.setText("Black");
        black.setTextFill(Color.BLACK);
        startBtn.relocate(W - 7 * B,150);
        rootStart.getChildren().addAll(white, black, startBtn);
    }
    public void makeDest(boolean[] dest){
        for (int i = 0; i < 24; i++){
            if(dest[i])
                this.homes[i].setDest();
        }
    }
    public void makeMove(boolean[] canMove){
        for (int i = 1; i <= 24; i++){
            if(canMove[i])
                this.homes[i - 1].canMove();
        }
    }
    private void setRect(Rectangle rect, float X){
        rect.setX(X);
        rect.setY(B);
        rect.setWidth((W - (4 * B))/2);
        rect.setHeight((H - 2 * B));
        rect.setFill(Color.web("#303030"));
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(1);
    }
    private void setSVG(SVGPath svg, String path, String colorCode){
        svg.setContent(path);
        svg.setFill(Color.web(colorCode));
    }

    public Parent createStart(){

        Image image = new Image("file:bG.png");
        ImageView imageView = new ImageView(image);
        player1name.relocate((W + 2 * B) / 4,100);
        player1name.setPromptText("Enter 1st Player Name ");
        player1name.setFocusTraversable(false);
        player2name.relocate((W + 2 * B) / 4,150);
        player2name.setPromptText("Enter 2nd Player Name ");
        player2name.setFocusTraversable(false);
        startrollBtn.setText("Roll Dice for Turn");
        startrollBtn.relocate((W + 5.2 * B)/4 , 190);
        rootStart.getChildren().addAll(imageView, player1name, player2name, startrollBtn);
        rootStart.setPrefSize(W + 10 * B ,H );
        rootStart.getChildren().addAll(p1dice.d, p2dice.d);
        return rootStart;
    }

    public Parent createContent() {
        rollBtn.relocate(W + 5 * B, 500);
        rollBtn.setText("Roll!");
        Rectangle menuBG = new Rectangle(10 * B, H);
        menuBG.relocate(W,0);
        menuBG.setFill(Color.web("#333333"));

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
                "m " + B + "," + B + path + "m " + W/2 + ",0" + path, "#575757");
        Rectangle half1 = new Rectangle();
        setRect(half1, B);
        Rectangle half2 = new Rectangle();
        setRect(half2, W/2 + B);
        root.setPrefSize(W + 10 * B ,H - 11);

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
        root.getChildren().clear();
        root.getChildren().addAll(menuBG, border, half1, half2, homeGroup, pieceGroup, rollBtn);
        dice = new Dice((W - 4 * B)/4 - B, H/2);
        //  dice.setValue(5);
        dice2 = new Dice((W - 4 * B)/4 + B, H/2 - 2 *B);
        //dice2.setValue(4);
        root.getChildren().addAll(dice.d, dice2.d);
        return root;
    }
}

class Home extends Group{

    ArrayList<Piece> pieces =new ArrayList<>();
    private int pID = 0;
    private int pNum = 0;
    private float start = 0;
    private float hW;
    private int hID;
    SVGPath tri = new SVGPath();

    private void setSVG(SVGPath svg, String path, String colorCode){
        svg.setContent(path);
        svg.setFill(Color.web(colorCode));
        svg.setStrokeWidth(1);
        //svg.setStroke(Color.BLACK);
    }

    void setDest(){
        this.tri.setStroke(Color.GREEN);
        this.tri.setStrokeWidth(2);
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
            setSVG(tri, "M " + this.start  + "," + B + " m " + this.hW + ",0 " + (-this.hW / 2) +
                    "," + hH + " " + (-this.hW / 2) + "," + (-hH) + " z", color == 0 ? "#680707" : "#756E6E") ;
        else
            setSVG(tri, "M " + this.start  + "," + (H - B) + " m " + this.hW + ",0 " + (-this.hW / 2) +
                    "," + (-hH) + " " + (-this.hW / 2) + "," + (hH) + " z", color == 1 ? "#680707" : "#756E6E") ;

        this.getChildren().addAll(tri);
    }

    void addPiece (float B, float W, float H, int x2, boolean color, boolean down){
        Piece piece = new Piece(this.hID, this.pieces.size(), B, W, H, this.start, x2, color, this.hW,this.pNum, down);
        this.pieces.add(piece);
        this.pID++;
//        this.getChildren().addAll(pieces);
    }
    void removePiece(){
        this.pieces.remove(this.pieces.size()-1);
        this.pNum--;
      //  this.getChildren().clear();
       // this.getChildren().addAll(pieces);
    }

    void canMove(){
        pieces.get(pieces.size() - 1).setMoveable();
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
    Circle backCircle;
    Circle frontCircle1;
    Circle frontCircle2;
    Circle frontCircle3;
    Circle frontCircle4;
    Circle selected;
    float r1;
    float r2;
    float r3;
    float r4;
    float r5;
    double r6;
    double xShift;
    final double yS;
    double yShift;

    public void setMoveable(){
        this.backCircle.setStroke(Color.RED);
        this.backCircle.setStrokeWidth(2);
    }
    Piece(int homeID, int pieceID, float B, float W ,float H, float start, int x2, boolean color, float hW, int nTop
            ,boolean down) {


        r1 = 22;
        r2 = 19;
        r3 = 16;
        r4 = 13;
        r5 = 8;
        r6 = 24;
        this.pID = pieceID;
        this.hID = homeID;
        xShift = start + hW / 2 ;
        yShift = (x2 == 0) ?  r1 * (1 + 1.25 * (pieceID)) :  r1 * (1.5 + 1.25 * (pieceID - 8));
        yShift = down ? H - B - yShift: B + yShift;
        yS = yShift;
        backCircle = new Circle(xShift, yShift, r1, color ? Color.WHEAT : Color.GRAY);
        backCircle.setStrokeWidth(0.5);
        backCircle.setStroke(Color.BLACK);
        frontCircle1 = new Circle(xShift, yShift, r2, color ? Color.BEIGE : Color.BLACK);
        frontCircle1.setStrokeWidth(0.5);
        frontCircle1.setStroke(color ? Color.BLACK : Color.WHITE);
        frontCircle2 = new Circle(xShift, yShift, r3, color ? Color.WHEAT : Color.GRAY);
        frontCircle2.setStrokeWidth(0.5);
        frontCircle2.setStroke(color ? Color.BLACK : Color.WHITE);
        frontCircle3 = new Circle(xShift, yShift, r4, color ? Color.BEIGE : Color.BLACK);
        frontCircle3.setStrokeWidth(0.5);
        frontCircle3.setStroke(color ? Color.BLACK : Color.WHITE);
        frontCircle4 = new Circle(xShift, yShift, r5, color ? Color.WHEAT : Color.GRAY);
        frontCircle4.setStrokeWidth(0.5);
        frontCircle4.setStroke(color ? Color.BLACK : Color.WHITE);
        getChildren().addAll(backCircle, frontCircle1, frontCircle2, frontCircle3, frontCircle4);
        selected = new Circle(xShift, yShift, r6, color ? Color.CYAN : Color.YELLOWGREEN);
        selected.setStrokeWidth(0.5);
        selected.setStroke(color ? Color.BLACK : Color.WHITE);

    }

    public int pressed(/*double oldMouseX, double oldMouseY*/){
        getChildren().clear();
        this.toFront();
        getChildren().addAll(this.selected, backCircle, frontCircle1, frontCircle2, frontCircle3, frontCircle4);
        int start;
        if (this.hID <= 12)
            start = this.hID + 12;
        else
            start = (25 - this.hID);

        System.out.println("start : " + start);

        return start;

    }

    public void test(boolean t){
        if(t){
            this.relocate(100,100);
        }
    }


    public void dragged(double oldMouseX, double oldMouseY, double newMouseX, double newMouseY){
        relocate(newMouseX - oldMouseX + xShift - r1,
                newMouseY - oldMouseY + yS - r1);
    }

    public int released(double mouseX, double mouseY){
        getChildren().clear();
        getChildren().addAll(backCircle, frontCircle1, frontCircle2, frontCircle3, frontCircle4);
        int x = 0;
        if(mouseX < 320 & mouseX > 20 & mouseY > 20 & mouseY < 228)
            x = (int) Math.floor((mouseX - 20)/ 50 + 12);

        if((mouseX > 360) & (mouseX < 660) & mouseY > 20 & mouseY < 228)
            x = (int) Math.floor((mouseX - 360)/ 50 + 18);

        if((mouseX > 20) & (mouseX < 320) & mouseY > 332 & mouseY < 540)
            x = (int) Math.floor(-(mouseX - 20)/ 50 + 12);

        if((mouseX > 360) & (mouseX < 660) & mouseY > 332 & mouseY < 540)
            x = (int) Math.floor(-(mouseX - 360)/ 50 + 6);

        System.out.println("  end : " + (x + 1));
        return (x+1);
    }

    /*int getID(){
        return this.pID;
    }*/
}
class Dice extends StackPane {

    public static final int MAX_VALUE = 6;
    public static final int MIN_VALUE = 1;
    ImageView d;
    //  int value;
    final Animation animation;
    public final SimpleIntegerProperty valueProperty = new SimpleIntegerProperty();

    public Dice(double xShift, double yShift) {

        Image diceImage = new Image("file:dice7.png");
        d = new ImageView(diceImage);
        //Rectangle rect = new Rectangle(50, 50);
        Rectangle2D window = new Rectangle2D(0,0,30,30);
        d.setViewport(window);
        d.relocate(xShift, yShift);

        animation = new SpriteAnimation(
                d,
                Duration.millis(1200),
                6, 6,
                0, 0,
                30, 30
        );
        animation.setCycleCount(Animation.INDEFINITE);
        //animation.play();
        /*this.setAlignment(Pos.CENTER);*/
        //  d.setOpacity(0.5);
        getChildren().addAll(d);
        //this.relocate(200,200);
    }

    public void roll(int value) {
        //  System.out.println(value + " ");
        RotateTransition rt = new RotateTransition(Duration.millis(1400), d);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        animation.play();
        rt.play();
        rt.setOnFinished(event -> {
            animation.stop();
            final int x = ((value - 1) % 6) * 30  + 0;
            final int y = ((value - 1) / 6) * 30 + 0;
            d.setViewport(new Rectangle2D(x, y, 30, 30));
        });

    }
}

class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}