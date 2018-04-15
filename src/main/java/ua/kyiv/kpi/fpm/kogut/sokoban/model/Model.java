package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import ua.kyiv.kpi.fpm.kogut.sokoban.controller.EventListener;

@Singleton
public class Model {

    public static final int FIELD_SELL_SIZE = 20;

    private final EventListener eventListener;
    private final LevelLoader levelLoader;

    private int currentLevel;
    private GameObjects gameObjects;

    @Inject
    public Model(EventListener eventListener, LevelLoader levelLoader, @Named("Current level") int currentLevel) {
        this.eventListener = eventListener;
        this.levelLoader = levelLoader;
        this.currentLevel = currentLevel;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    private void restartLevel(int level) {
        gameObjects = levelLoader.getGameObjects(level);
    }

    public void move(Direction direction) {
        final Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction) || checkBoxCollision(player, direction)) {
            return;
        }

        moveDirection(player, direction, Model.FIELD_SELL_SIZE);
        checkCompletion();
    }

    private boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject object : gameObjects.getWalls()) {
            if (gameObject.isCollision(object, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBoxCollision(CollisionObject collisionObject, Direction direction) {
        Box collisionBox = null;

        for (Box box : gameObjects.getBoxes()) {
            if (collisionObject.isCollision(box, direction)) {
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

    private void checkCompletion() {
        boolean completion = true;

        for (Home home : gameObjects.getHomes()) {
            boolean inPlace = false;
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    inPlace = true;
                    break;
                }
            }
            completion &= inPlace;
        }

        if (completion) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    private void moveDirection(Movable movableObject, Direction direction, int value) {
        int deltaX = 0;
        int deltaY = 0;

        switch (direction) {
            case LEFT:
                deltaX = -value;
                break;
            case RIGHT:
                deltaX = value;
                break;
            case UP:
                deltaY = -value;
                break;
            case DOWN:
                deltaY = value;
                break;
        }

        movableObject.move(deltaX, deltaY);
    }
}