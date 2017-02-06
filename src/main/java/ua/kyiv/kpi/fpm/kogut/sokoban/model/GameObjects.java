package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 06.02.2017.
 */
public class GameObjects {

    private Player player;
    private Set<Wall> walls;
    private Set<Home> homes;
    private Set<Box> boxes;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player) {
        this.player = player;
        this.walls = walls;
        this.homes = homes;
        this.boxes = boxes;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Set<Wall> getWalls()
    {
        return walls;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> objects = new HashSet<>();
        objects.addAll(walls);
        objects.addAll(homes);
        objects.addAll(boxes);
        objects.add(player);
        return objects;
    }
}
