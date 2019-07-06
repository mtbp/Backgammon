package sample;
import Model.*;

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

    public void event() {
        for (int i = 0; i < 24; i++) {
            if (view.homes[i].pieces.size() != 0) {
                final int index = i;
                this.view.homes[i].pieces.get(view.homes[i].pieces.size() - 1).setOnMousePressed(event -> {
                    oldMouseX = event.getSceneX();
                    oldMouseY = event.getSceneY();
                    int start;
                    start = view.homes[index].pieces.get(view.homes[index].pieces.size() - 1).pressed();
                    boolean t = model.pressed(start);
                    view.homes[index].pieces.get(view.homes[index].pieces.size()-1).test(t);

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
                });
            }
        }
    }
}