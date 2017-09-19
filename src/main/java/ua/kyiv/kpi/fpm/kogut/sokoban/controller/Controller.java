package ua.kyiv.kpi.fpm.kogut.sokoban.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import ua.kyiv.kpi.fpm.kogut.sokoban.controller.module.SokobanModule;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.Direction;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.GameObjects;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.Model;
import ua.kyiv.kpi.fpm.kogut.sokoban.view.View;

import javax.inject.Inject;

/**
 * Created by Admin on 05.02.2017.
 */
@Singleton
public class Controller implements EventListener {

    private View view;
    private Model model;

    @Inject
    public Controller(View.ViewFactory viewFactory, Model model) {
        this.view = viewFactory.create(this);
        this.model = model;

        model.restart();
        view.init();
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

        Injector injector = Guice.createInjector(new SokobanModule());
        Controller controller = injector.getInstance(Controller.class);
    }
}