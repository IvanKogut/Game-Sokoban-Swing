package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 31.01.2017.
 */
public class LevelLoader {

    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        int levelValue = level;

        if (levelValue > 60) {
            levelValue %= 60;
        }

        Player player = null;
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();

        try {
            List<String> strings = Files.readAllLines(levels);
            int i;
            for (i = 0; i < strings.size(); i++) {
                if (strings.get(i).startsWith("Maze:") && strings.get(i).split(" ")[1].equals("" + levelValue)) {
                    break;
                }
            }
            int lengthX = Integer.parseInt(strings.get(i+2).split(" ")[2]);
            int lengthY = Integer.parseInt(strings.get(i+3).split(" ")[2]);

            int x = Model.FIELD_SELL_SIZE / 2;
            int y = Model.FIELD_SELL_SIZE / 2;
            for (int m = i + 7; m <= i + 6 + lengthY; m++) {
                for (int n = 0; n < lengthX; n++) {
                    switch (strings.get(m).substring(n, n + 1)) {
                        case "X":
                            Wall wall = new Wall(x, y);
                            walls.add(wall);
                            break;
                        case "*":
                            Box box = new Box(x, y);
                            boxes.add(box);
                            break;
                        case ".":
                            Home home = new Home(x, y);
                            homes.add(home);
                            break;
                        case "&":
                            home = new Home(x, y);
                            box = new Box(x, y);
                            homes.add(home);
                            boxes.add(box);
                            break;
                        case "@":
                            player = new Player(x, y);
                            break;
                    }

                    x += Model.FIELD_SELL_SIZE;
                }
                x = Model.FIELD_SELL_SIZE / 2;
                y += Model.FIELD_SELL_SIZE;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}