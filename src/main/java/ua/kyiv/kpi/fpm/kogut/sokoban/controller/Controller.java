package ua.kyiv.kpi.fpm.kogut.sokoban.controller;

import ua.kyiv.kpi.fpm.kogut.sokoban.model.Direction;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.GameObjects;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.Model;
import ua.kyiv.kpi.fpm.kogut.sokoban.view.View;

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

        view.setEventListener(this);
        model.setEventListener(this);
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
