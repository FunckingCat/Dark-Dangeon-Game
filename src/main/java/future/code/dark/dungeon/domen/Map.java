package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.util.FileUtils;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collector;

import static future.code.dark.dungeon.config.Configuration.EXIT_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.EXIT_SPRITE;
import static future.code.dark.dungeon.config.Configuration.LAND_SPRITE;
import static future.code.dark.dungeon.config.Configuration.PLAYER_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;
import static future.code.dark.dungeon.config.Configuration.WALL_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.WALL_SPRITE;

public class Map {

    private static final Image wallImage = new ImageIcon(WALL_SPRITE).getImage();
    private static final Image landImage = new ImageIcon(LAND_SPRITE).getImage();
    private static final Image exitImage = new ImageIcon(EXIT_SPRITE).getImage();
    private final char[][] map;

    public Map(String mapPath) throws FileNotFoundException {
        List<String> lines = FileUtils.readFile(mapPath);

        if (!validate(lines)) {
            throw new RuntimeException("Map is not valid");
        }

        this.map = createMap(lines);

    }

    private char[][] createMap(List<String> lines) {
        char[][] map = new char[lines.size()][lines.get(0).length()];
        int rowNumber = 0;

        for (String line : lines) {
            map[rowNumber++] = line.toCharArray();
        }

        return map;
    }

    private Boolean validate(List<String> lines) {
        char[] leftColumn = lines.stream().map(item -> item.toCharArray()[0]).collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString)).toCharArray();
        char[] rightColumn = lines.stream().map(item -> item.toCharArray()[item.length()-1]).collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString)).toCharArray();
        if (!containsOnlyWalls(lines.get(0).toCharArray()) ||
                !containsOnlyWalls(lines.get(lines.size() - 1).toCharArray()) ||
                !containsOnlyWalls(leftColumn) ||
                !containsOnlyWalls(rightColumn)
        ) {
            return false;
        }
        return containsExit(lines) && containsPlayer(lines) && lines
                .stream().allMatch(line -> line.length() == lines.get(0).length());
    }

    private Boolean containsOnlyWalls(char[] line) {
        for (char ch : line) {
            if (ch != WALL_CHARACTER) {
                return false;
            }
        }
        return true;
    }

    private Boolean containsExit(List<String> lines) {
        for (String line : lines) {
            for (char ch : line.toCharArray()) {
                if (ch == EXIT_CHARACTER) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean containsPlayer(List<String> lines) {
        for (String line : lines) {
            for (char ch : line.toCharArray()) {
                if (ch == PLAYER_CHARACTER) {
                    return true;
                }
            }
        }
        return false;
    }

    public void render(Graphics graphics) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == WALL_CHARACTER) {
                    graphics.drawImage(wallImage, j * SPRITE_SIZE, i * SPRITE_SIZE, null);
                } else {
                    graphics.drawImage(landImage, j * SPRITE_SIZE, i * SPRITE_SIZE, null);
                }
            }
        }
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public char[][] getMap() {
        return map;
    }

}
