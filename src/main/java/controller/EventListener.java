package controller;

import model.Direction;

/**
 * Created by Admin on 06.02.2017.
 */
public interface EventListener {
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);
}