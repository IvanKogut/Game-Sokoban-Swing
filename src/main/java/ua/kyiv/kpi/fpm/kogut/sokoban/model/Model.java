package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import ua.kyiv.kpi.fpm.kogut.sokoban.controller.EventListener;

/**
 * Created by Admin on 05.02.2017.
 */
public class Model {

    public static final int FIELD_SELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader("/resources/data.properties");

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction) || checkBoxCollision(direction)) {
            return;
        }

        moveDirection(gameObjects.getPlayer(), direction, Model.FIELD_SELL_SIZE);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject object : gameObjects.getWalls()) {
            if (gameObject.isCollision(object, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        Box collisionBox = null;

        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                collisionBox = box;
                if (checkWallCollision(box, direction)) {
                    return true;
                } else {
                    for (Box b : gameObjects.getBoxes()) {
                        if (box.isCollision(b, direction)) {
                            return true;
                        }
                    }
                    break;
                }
            }
        }

        if (collisionBox != null) {
            moveDirection(collisionBox, direction, Model.FIELD_SELL_SIZE);
        }

        return false;
    }

    public void checkCompletion() {
        boolean completion = true;

        for (Home home : gameObjects.getHomes()) {
            boolean inPlace = false;
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    inPlace = true;
                }
            }
            completion &= inPlace;
        }

        if (completion) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    private void moveDirection(GameObject gameObject, Direction direction, int value) {
        switch (direction) {
            case LEFT:
                gameObject.setX(gameObject.getX() - value);
                break;
            case RIGHT:
                gameObject.setX(gameObject.getX() + value);
                break;
            case UP:
                gameObject.setY(gameObject.getY() - value);
                break;
            case DOWN:
                gameObject.setY(gameObject.getY() + value);
                break;
        }
    }
}