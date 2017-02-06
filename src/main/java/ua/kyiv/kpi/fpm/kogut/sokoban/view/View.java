package ua.kyiv.kpi.fpm.kogut.sokoban.view;

import ua.kyiv.kpi.fpm.kogut.sokoban.controller.Controller;
import ua.kyiv.kpi.fpm.kogut.sokoban.controller.EventListener;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.GameObjects;

import javax.swing.*;

/**
 * Created by Admin on 05.02.2017.
 */
public class View extends JFrame {

    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
        setVisible(true);
    }

    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();

        JOptionPane.showMessageDialog(
                this,
                "Рівень " + level + " пройдений!",
                "Вітання",
                JOptionPane.INFORMATION_MESSAGE);

        controller.startNextLevel();
    }
}