package sample;
import Model.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }
    double oldMouseX;
    double oldMouseY;
    double newMouseX;
    double newMouseY;
    double mouseX;
    double mouseY;
    int start;
    public Parent viewroot;

    public void rootinit(){
        this.viewroot = view.createStart();
    }

    public void startevent(){
        view.startBtn.setOnMouseClicked(event->{
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(view.createContent()));
            if(view.white.isSelected())
                model.setProfile(0);
            else
                model.setProfile(1);
            event();
        });
        view.startrollBtn.setOnMouseClicked(event -> {
            int[] val = model.rollDice();
            if(view.player1name.getText().length()==0 | view.player2name.getText().length()==0){
                view.startrollBtn.setText("Please Enter Names!");
            }
            else {
                view.p1dice.roll(val[0]);
                view.p2dice.roll(val[1]);
                System.out.println("yes");
                if (val[0] != val[1]) {
                    view.startrollBtn.setDisable(true);
                    view.player1name.setDisable(true);
                    view.player2name.setDisable(true);
                    view.getInit();
                } else
                    view.startrollBtn.setText("REROLL!");
            }
        });

        view.white.setOnMouseClicked(event -> {
            view.black.setDisable(true);

        });

        view.black.setOnMouseClicked(event -> {
            view.white.setDisable(true);

        });
    }

    public void event() {
        // event for dice button
        /*
        dice = model.rollDiceForStart();
        dice -> view : draw dice
                       if unequal : show message and mouse event -> model.setProfiles
                                                                    view.setBoard()


        */
        view.rollBtn.setOnMouseClicked(event->{
            System.out.println("hello");
            int[] val;
            val = model.rollDice();
            view.dice.roll(val [0]);
            view.dice2.roll(val[1]);
            boolean[] a = model.checkCheckers(1);
            boolean[] b = new boolean[26];
            b[0] = a[0];
            b[25] = a[25];

            for(int i = 1; i <= 24; i++){
                // System.out.print(a[i] + " ");
                if(i<=12)    b[i] = a[i + 12];
                if(i>12)   b[i] = a[25 - i];
            }

            view.makeMove(b);
            //view.makeDest(b);
        });


        for (int i = 0; i < 24; i++) {
            if (view.homes[i].pieces.size() != 0) {
                final int index = i;

                this.view.homes[i].pieces.get(view.homes[i].pieces.size() - 1).setOnMousePressed(event -> {
                    oldMouseX = event.getSceneX();
                    oldMouseY = event.getSceneY();
                    //int start;
                    this.start = view.homes[index].pieces.get(view.homes[index].pieces.size() - 1).pressed();
                    boolean[] dest = model.checkPoint(start);
                    boolean[] d = new boolean[24];
                    for(int i3 = 0; i3 < 24; i3++){
                        // System.out.print(a[i] + " ");
                        if(i3<12)   d[i3] = dest[i3 + 12];
                        if(i3>=12)   d[i3] = dest[23 - i3];
                    }
                    for(int i2 = 0; i2 < 24; i2++){
                        System.out.print(dest[i2] + " ");
                    }
                    System.out.println(" ");
                    for(int i2 = 0; i2 < 24; i2++){
                        System.out.print(d[i2] + " ");
                    }
                    view.makeDest(d);
                    //boolean t = model.pressed(start);
                    //view.homes[index].pieces.get(view.homes[index].pieces.size()-1).test(t);

                });
                this.view.homes[i].pieces.get(view.homes[i].pieces.size() - 1).setOnMouseDragged(event -> {
                    newMouseX = event.getSceneX();
                    newMouseY = event.getSceneY();
                    view.homes[index].pieces.get(view.homes[index].pieces.size() - 1).dragged(oldMouseX, oldMouseY,
                            newMouseX, newMouseY);
                });
                this.view.homes[i].pieces.get(view.homes[i].pieces.size() - 1).setOnMouseReleased(event -> {
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    int end;
                    end = view.homes[index].pieces.get(view.homes[index].pieces.size() - 1).released(mouseX, mouseY);
                    System.out.println((start - 1)+" "+(end-1));
                    boolean validmove =  model.isMovePossible(start -1 , end-1);
                    //validmove = true;
                    System.out.println((start-1) + " "+ (end-1)+ " "+validmove);
                    if(validmove){
                        view.homes[start - 1].removePiece();
                        view.homes[end -1].addPiece(view.B, view.W, view.H,0, false, end > 12);
                    }

                });
            }
        }
    }
}