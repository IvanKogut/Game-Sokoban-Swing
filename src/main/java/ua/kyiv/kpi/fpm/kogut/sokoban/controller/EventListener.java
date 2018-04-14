package ua.kyiv.kpi.fpm.kogut.sokoban.controller;

import ua.kyiv.kpi.fpm.kogut.sokoban.model.Direction;

public interface EventListener {

    void move(Direction direction);

    void restart();

    void startNextLevel();

    void levelCompleted(int level);
}