package ua.kyiv.kpi.fpm.kogut.sokoban.model;

public interface GameObjectFactory {

    Player getPlayer(int x, int y);

    Wall getWall(int x, int y);

    Box getBox(int x, int y);

    Home getHome(int x, int y);
}