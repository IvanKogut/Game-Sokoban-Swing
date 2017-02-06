package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Admin on 06.02.2017.
 */
public class LevelLoader {

    private String pathToPropertiesFile;

    public LevelLoader(String pathToPropertiesFile) {
        this.pathToPropertiesFile = pathToPropertiesFile;
    }

    public GameObjects getLevel(int level) {

        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(pathToPropertiesFile));
        } catch (IOException e) {
            System.exit(0);
        }

        int levelValue = level;

        if (levelValue > 60) {
            levelValue %= 60;
        }

        Player player = null;
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();

        try (InputStream inputStream =  getClass().getResourceAsStream((String) properties.get("path"));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            List<String> strings = new ArrayList<>();
            while (reader.ready()) {
                strings.add(reader.readLine());
            }

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
            System.exit(0);
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}