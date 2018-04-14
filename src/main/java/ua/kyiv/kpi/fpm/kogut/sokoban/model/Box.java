package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);

        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;

        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());

        graphics.drawLine(leftUpperCornerX, leftUpperCornerY, getX() + getWidth()/2, getY() + getHeight()/2);
        graphics.drawLine(getX() + getWidth()/2, getY() - getHeight()/2, getX() - getWidth()/2, getY() + getHeight()/2);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
