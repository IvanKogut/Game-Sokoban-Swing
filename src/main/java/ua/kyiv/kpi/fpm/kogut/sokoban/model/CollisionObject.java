package ua.kyiv.kpi.fpm.kogut.sokoban.model;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {

        int xAfterMove = getX();
        int yAfterMove = getY();

        switch (direction) {
            case LEFT:
                xAfterMove -= Model.FIELD_SELL_SIZE;
                break;
            case RIGHT:
                xAfterMove += Model.FIELD_SELL_SIZE;
                break;
            case UP:
                yAfterMove -= Model.FIELD_SELL_SIZE;
                break;
            case DOWN:
                yAfterMove += Model.FIELD_SELL_SIZE;
                break;
        }

        return xAfterMove == gameObject.getX() && yAfterMove == gameObject.getY();
    }
}