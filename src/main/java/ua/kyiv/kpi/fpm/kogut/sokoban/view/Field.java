package ua.kyiv.kpi.fpm.kogut.sokoban.view;

import ua.kyiv.kpi.fpm.kogut.sokoban.controller.EventListener;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.Direction;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Admin on 06.02.2017.
 */
public class Field extends JPanel {

    public class KeyHandler extends KeyAdapter {

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
    }

    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;

        addKeyListener(new KeyHandler());
        setFocusable(true);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());

        for (GameObject gameObject : view.getGameObjects().getAll()) {
            gameObject.draw(g);
        }
    }
}