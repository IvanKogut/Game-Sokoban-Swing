package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import java.awt.*;

public class Home extends GameObject {

    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);

        graphics.drawOval(getX(), getY(), getWidth(), getHeight());
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}