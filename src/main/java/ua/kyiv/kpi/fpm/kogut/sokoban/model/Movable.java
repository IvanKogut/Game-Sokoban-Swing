package ua.kyiv.kpi.fpm.kogut.sokoban.model;

interface Movable {

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    default void move(int deltaX, int deltaY) {
        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }
}
