package ua.kyiv.kpi.fpm.kogut.sokoban.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.Assisted;
import ua.kyiv.kpi.fpm.kogut.sokoban.controller.EventListener;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.Direction;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Singleton
public class Field extends JPanel {

    private final View view;

    @Inject
    public Field(@Assisted View view, EventListener eventListener) {
        this.view = view;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        eventListener.move(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        eventListener.move(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        eventListener.move(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        eventListener.move(Direction.DOWN);
                        break;
                    case KeyEvent.VK_R:
                        eventListener.restart();
                        break;
                }
            }
        });

        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());

        for (GameObject gameObject : view.getGameObjects().getAll()) {
            gameObject.draw(g);
        }
    }

    public interface FieldFactory {
        Field create(View view);
    }
}