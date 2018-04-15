package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class GameObjects {

    private Player player;
    private Set<Wall> walls;
    private Set<Home> homes;
    private Set<Box> boxes;

    Player getPlayer()
    {
        return player;
    }

    Set<Wall> getWalls()
    {
        return walls;
    }

    Set<Home> getHomes()
    {
        return homes;
    }

    Set<Box> getBoxes()
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
