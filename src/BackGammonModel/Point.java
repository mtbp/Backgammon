package BackGammonModel;

import java.util.Collection;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Point {
    int checkersNumber = 0;
    Color color;
    int colomnNo;
    //int blackColomnNo;
    PointStatus status;
    int lastCheckerIndex;
    boolean mark;

    public Point(int checkersNumber, PointStatus status, int colomnNo, Color color){
        this.checkersNumber = checkersNumber;
        this.status = status;
        this.colomnNo = colomnNo;
        this.color = color;
        //this.whiteColomnNo = whiteColomnNo;
        //this.blackColomnNo = blackColomnNo;
    }

    public boolean checkMove(Color color){
        boolean move = FALSE;
        if(color == Color.WHITE) {
            if (this.status == PointStatus.WHITE | this.status == PointStatus.BLACKBLOT | this.status == PointStatus.FREE){
                move = TRUE;
            }
        }
        else if(color == Color.BLACK) {
            if (this.status == PointStatus.BLACK | this.status == PointStatus.WHITEBLOT | this.status == PointStatus.FREE){
                move = TRUE;
            }
        }
        return move;
    }





}
