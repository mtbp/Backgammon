package Model;

public class Checker {

    Color color;
    int pointNo;
    int newPointNo;
    boolean canMove;

    public Checker(Color color, int arrayIndex){
        this.color = color;
        switch (arrayIndex){
            case 0 :{
                if(color == Color.WHITE)
                    this.pointNo = 24;
                else
                    this.pointNo = 1;
                break;
            }
            case 1 :{
                if(color == Color.WHITE)
                    this.pointNo = 24;
                else
                    this.pointNo = 1;
                break;
            }
            case 2 :{
                if(color == Color.WHITE)
                    this.pointNo = 13;
                else
                    this.pointNo = 12;
                break;
            }
            case 3 :{
                if(color == Color.WHITE)
                    this.pointNo = 13;
                else
                    this.pointNo = 12;
                break;
            }
            case 4 :{
                if(color == Color.WHITE)
                    this.pointNo = 13;
                else
                    this.pointNo = 12;
                break;
            }
            case 5 :{
                if(color == Color.WHITE)
                    this.pointNo = 13;
                else
                    this.pointNo = 12;
                break;
            }
            case 6 :{
                if(color == Color.WHITE)
                    this.pointNo = 13;
                else
                    this.pointNo = 12;
                break;
            }
            case 7 :{
                if(color == Color.WHITE)
                    this.pointNo = 8;
                else
                    this.pointNo = 17;
                break;
            }
            case 8 :{
                if(color == Color.WHITE)
                    this.pointNo = 8;
                else
                    this.pointNo = 17;
                break;
            }
            case 9 :{
                if(color == Color.WHITE)
                    this.pointNo = 13;
                else
                    this.pointNo = 12;
                break;
            }
            case 10 :{
                if(color == Color.WHITE)
                    this.pointNo = 6;
                else
                    this.pointNo = 19;
                break;
            }
            case 11 :{
                if(color == Color.WHITE)
                    this.pointNo = 6;
                else
                    this.pointNo = 19;
                break;
            }
            case 12 :{
                if(color == Color.WHITE)
                    this.pointNo = 6;
                else
                    this.pointNo = 19;
                break;
            }
            case 13 :{
                if(color == Color.WHITE)
                    this.pointNo = 6;
                else
                    this.pointNo = 19;
                break;
            }
            case 14 :{
                if(color == Color.WHITE)
                    this.pointNo = 6;
                else
                    this.pointNo = 19;
                break;
            }
        }
        this.newPointNo = pointNo;
    }



}
