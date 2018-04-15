package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import java.awt.*;

class Home extends GameObject {

    private static final int DEFAULT_DIMENSION = 2;

    Home(int x, int y) {
        super(x, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);

        graphics.drawOval(getX(), getY(), getWidth(), getHeight());
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}