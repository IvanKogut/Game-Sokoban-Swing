package controller;

import model.Direction;
import model.GameObjects;
import model.Model;
import view.View;

/**
 * Created by Admin on 05.02.2017.
 */
public class Controller implements EventListener {

    private View view;
    private Model model;

    public Controller() {
        this.view = new View(this);
        this.model = new Model();

        model.restart();
        view.init();
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted(int level) {

    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
