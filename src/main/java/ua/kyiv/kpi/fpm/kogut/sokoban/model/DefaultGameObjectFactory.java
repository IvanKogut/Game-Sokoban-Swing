package ua.kyiv.kpi.fpm.kogut.sokoban.model;

public class DefaultGameObjectFactory implements GameObjectFactory {

    @Override
    public Player getPlayer(int x, int y) {
        return new Player(x, y);
    }

    @Override
    public Wall getWall(int x, int y) {
        return new Wall(x, y);
    }

    @Override
    public Box getBox(int x, int y) {
        return new Box(x, y);
    }

    @Override
    public Home getHome(int x, int y) {
        return new Home(x, y);
    }
}