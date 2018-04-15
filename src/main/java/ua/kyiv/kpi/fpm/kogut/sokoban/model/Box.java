package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import java.awt.*;

class Box extends CollisionObject implements Movable {

    Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);

        final int leftUpperCornerX = getX() - getWidth() / 2;
        final int leftUpperCornerY = getY() - getHeight() / 2;

        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());

        final int rightLowerCornerX = getX() + getWidth() / 2;
        final int rightLowerCornerY = getY() + getHeight() / 2;

        graphics.drawLine(leftUpperCornerX, leftUpperCornerY, rightLowerCornerX, rightLowerCornerY);
        graphics.drawLine(rightLowerCornerX, leftUpperCornerY, leftUpperCornerX, rightLowerCornerY);
    }
}
