package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Singleton
class LevelLoader {

    private final GameObjectFactory gameObjectFactory;
    private final Map<Integer, GameObjects> levels;

    @Inject
    public LevelLoader(GameObjectFactory gameObjectFactory, @Named("Path to properties file") String pathToPropertiesFile) {
        this.gameObjectFactory = gameObjectFactory;
        this.levels = new HashMap<>();

        initLevels(pathToPropertiesFile);
    }

    private String getPathToSavedLevels(final String pathToPropertiesFile) {
        try {
            final Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(pathToPropertiesFile));
            return (String) properties.get("path");
        } catch (IOException e) {
            throw new RuntimeException("Error during loading path to levels.", e);
        }
    }

    private void initLevels(final String pathToPropertiesFile) {
        try (InputStream inputStream = getClass().getResourceAsStream(getPathToSavedLevels(pathToPropertiesFile));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            final List<String> stringRows = new ArrayList<>();
            while (reader.ready()) {
                stringRows.add(reader.readLine());
            }

            for (int i = 0; i < stringRows.size(); i++) {
                if (stringRows.get(i).startsWith("Maze:")) {
                    Player player = null;
                    final Set<Wall> walls = new HashSet<>();
                    final Set<Box> boxes = new HashSet<>();
                    final Set<Home> homes = new HashSet<>();

                    final int level = Integer.valueOf(stringRows.get(i).split(" ")[1]);

                    int lengthX = Integer.parseInt(stringRows.get(i + 2).split(" ")[2]);
                    int lengthY = Integer.parseInt(stringRows.get(i + 3).split(" ")[2]);

                    int x = Model.FIELD_SELL_SIZE / 2;
                    int y = Model.FIELD_SELL_SIZE / 2;

                    for (int m = i + 7; m <= i + 6 + lengthY; m++) {
                        for (int n = 0; n < lengthX; n++) {
                            switch (stringRows.get(m).substring(n, n + 1)) {
                                case "X":
                                    walls.add(gameObjectFactory.getWall(x, y));
                                    break;
                                case "*":
                                    boxes.add(gameObjectFactory.getBox(x, y));
                                    break;
                                case ".":
                                    homes.add(gameObjectFactory.getHome(x, y));
                                    break;
                                case "&":
                                    homes.add(gameObjectFactory.getHome(x, y));
                                    boxes.add(gameObjectFactory.getBox(x, y));
                                    break;
                                case "@":
                                    player = gameObjectFactory.getPlayer(x, y);
                                    break;
                            }

                            x += Model.FIELD_SELL_SIZE;
                        }
                        x = Model.FIELD_SELL_SIZE / 2;
                        y += Model.FIELD_SELL_SIZE;
                    }

                    levels.put(level, new GameObjects(player, walls, homes, boxes));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error during loading levels", e);
        }
    }

    GameObjects getGameObjects(int level) {
        final int levelNumber = levels.size();
        if (level > levelNumber) {
            level %= levelNumber;
        }

        return levels.get(level);
    }
}