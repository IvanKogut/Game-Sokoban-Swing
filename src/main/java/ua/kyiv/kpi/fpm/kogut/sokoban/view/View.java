package ua.kyiv.kpi.fpm.kogut.sokoban.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.Assisted;
import ua.kyiv.kpi.fpm.kogut.sokoban.controller.Controller;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.GameObjects;

import javax.swing.*;

@Singleton
public class View extends JFrame {

    private final Controller controller;
    private final Field field;

    @Inject
    public View(@Assisted Controller controller, Field.FieldFactory fieldFactory) {
        this.controller = controller;
        this.field = fieldFactory.create(this);
    }

    public void init() {
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

    GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        JOptionPane.showMessageDialog(
                this,
                "Рівень " + level + " пройдений!",
                "Вітання",
                JOptionPane.INFORMATION_MESSAGE);

        controller.startNextLevel();
    }

    public interface ViewFactory {
        View create(Controller controller);
    }
}