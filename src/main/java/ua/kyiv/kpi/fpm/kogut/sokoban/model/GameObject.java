package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
@AllArgsConstructor
public abstract class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;

    GameObject(int x, int y) {
        this.x = x;
        this.y = y;

        this.width = this.height = Model.FIELD_SELL_SIZE;
    }

    public abstract void draw(Graphics graphics);
}
