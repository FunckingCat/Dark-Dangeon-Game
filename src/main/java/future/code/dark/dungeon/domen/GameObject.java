package future.code.dark.dungeon.domen;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public abstract class GameObject {

    private final Image image;
    protected int xPosition;
    protected int yPosition;

    public GameObject(int xPosition, int yPosition, String imagePath) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        this.image = new ImageIcon(imagePath).getImage();
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(image, xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, null);
    }

}
