package ua.kyiv.kpi.fpm.kogut.sokoban.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private static <T extends GameObject> Set<T> copy(Set<T> objects, Function<T, T> copyFunction) {
        return objects
                .stream()
                .map(copyFunction)
                .collect(Collectors.toSet());
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

        return getCopyOf(levels.get(level));
    }

    private GameObjects getCopyOf(GameObjects gameObjects) {
        final Player player = gameObjects.getPlayer();
        final Player copiedPlayer = gameObjectFactory.getPlayer(player.getX(), player.getY());

        final Set<Box> copiedBoxes = copy(
                gameObjects.getBoxes(), box -> gameObjectFactory.getBox(box.getX(), box.getY())
        );

        final Set<Wall> copiedWalls = copy(
                gameObjects.getWalls(), wall -> gameObjectFactory.getWall(wall.getX(), wall.getY())
        );

        final Set<Home> copiedHomes = copy(
                gameObjects.getHomes(), home -> gameObjectFactory.getHome(home.getX(), home.getY())
        );

        return new GameObjects(copiedPlayer, copiedWalls, copiedHomes, copiedBoxes);
    }
}