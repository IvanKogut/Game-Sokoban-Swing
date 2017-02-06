package model;

import java.awt.*;

/**
 * Created by Admin on 06.02.2017.
 */
public class Wall extends CollisionObject {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);

        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;

        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }
}
